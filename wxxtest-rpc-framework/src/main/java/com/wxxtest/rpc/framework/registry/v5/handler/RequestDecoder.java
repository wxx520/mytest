package com.wxxtest.rpc.framework.registry.v5.handler;

import com.wxxtest.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.framework.registry.v5.RegistryServiceManager;
import com.wxxtest.rpc.framework.registry.v5.msg.RequestWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 根据请求报文中的信息反序列化出请求对象
 */
public class RequestDecoder extends ByteToMessageDecoder {

  private final RegistryServiceManager requestTypeManagerService;

  public RequestDecoder(RegistryServiceManager requestTypeManagerService) {
    this.requestTypeManagerService = requestTypeManagerService;
  }

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
    //报文的首位是int类型，报文的长度
    //使用while，因为缓冲区中可能不止一份报文
    while (completeRequestReceived(byteBuf)) {

      int readIndex = byteBuf.readerIndex();
      int reqLen = byteBuf.readInt();
      long reqId = byteBuf.readLong();
      String methodName = byteBuf.readCharSequence(byteBuf.readInt(), StandardCharsets.UTF_8).toString();
      Class<?> firstRequestParaType = getRequestType(byteBuf, readIndex, reqLen, reqId, methodName);
      if (firstRequestParaType == null) continue;

      try {
        //根据参数类型反序列化请求对象
        Object requestObj = ByteBufUtil.byteToObject(byteBuf, firstRequestParaType);
        System.out.printf("receive registry msg: %s %n", requestObj);

        //请求对象序列化完成后交由下一个handler处理
        list.add(new RequestWrapper(reqId, requestObj, methodName));
      } catch (Exception e) {
        System.out.println("服务端反序列化请求对象异常");
        e.printStackTrace();
      }
    }
  }

  /**
   * TODO
   * 1、如果找不到该服务只是简单的做忽略处理，真实情况下应该将这种异常结果也告诉客户端
   * 2、入参认为只有一个，真实情况几个都有可能
   */
  private Class<?> getRequestType(ByteBuf byteBuf, int readIndex, int reqLen, long reqId, String methodName) {
    System.out.printf("服务端解析到请求体长度为:len=%s, reqId=%s, methodName=%s%n", reqLen, reqId, methodName);
    Method serviceMethod = requestTypeManagerService.getMethod(methodName);
    if (serviceMethod == null) {
      //找不到对应的方法处理，跳过本次请求
      byteBuf.readerIndex(readIndex + reqLen);
      System.out.printf("服务端找不到方法名对应的服务，methodName=%s%n", methodName);
      return null;
    }
    //此处简单处理，认为方法入参有且仅有一个
    return serviceMethod.getParameterTypes()[0];
  }

  private boolean completeRequestReceived(ByteBuf byteBuf) {
    if (byteBuf.readableBytes() <= 4) {
      return false;
    }
    int requestLen = byteBuf.getInt(byteBuf.readerIndex());
    return byteBuf.readableBytes() >= requestLen;
  }
}
