package com.wxxtest.rpc.framework.registry.v4.handler;

import com.wxxtest.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.framework.registry.v4.msg.ReflectInfoMsg;
import com.wxxtest.rpc.framework.registry.v4.msg.ResponseWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class RequestDecoder extends ByteToMessageDecoder {
  private final Map<String, ReflectInfoMsg> reflectMsgMap;

  public RequestDecoder(Map<String, ReflectInfoMsg> reflectMsgMap) {
    this.reflectMsgMap = reflectMsgMap;
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
      ReflectInfoMsg reflectMsg = getReflectInfoMsg(byteBuf, readIndex, reqLen, reqId, methodName);
      if (reflectMsg == null) continue;

      //根据读到的方法名找到对应的方法，根据方法的入参反序列化对象
      try {
        //此处简单处理，认为方法入参有且仅有一个
        Class<?> firstParaType = reflectMsg.getMethod().getParameterTypes()[0];

        //根据参数类型反序列化请求对象
        Object requestObj = ByteBufUtil.byteToObject(byteBuf, firstParaType);
        System.out.printf("receive registry msg: %s %n", requestObj);

        //请求对象序列化完成后交由下一个handler处理
        list.add(new ResponseWrapper<>(reqId, requestObj, methodName));
      } catch (Exception e) {
        System.out.println("服务端反序列化请求对象异常");
        e.printStackTrace();
      }
    }
  }

  private ReflectInfoMsg getReflectInfoMsg(ByteBuf byteBuf, int readIndex, int reqLen, long reqId, String methodName) {
    System.out.printf("服务端解析到请求体长度为:len=%s, reqId=%s, methodName=%s%n", reqLen, reqId, methodName);
    ReflectInfoMsg reflectMsg = reflectMsgMap.get(methodName);
    if (reflectMsg == null) {
      //找不到对应的方法处理，跳过本次请求
      byteBuf.readerIndex(readIndex + reqLen);
      System.out.printf("服务端找不到方法名对应的服务，methodName=%s, %s", methodName, reflectMsgMap);
      return null;
    }
    return reflectMsg;
  }

  private boolean completeRequestReceived(ByteBuf byteBuf) {
    if (byteBuf.readableBytes() <= 4) {
      return false;
    }
    int requestLen = byteBuf.getInt(byteBuf.readerIndex());
    return byteBuf.readableBytes() >= requestLen;
  }
}