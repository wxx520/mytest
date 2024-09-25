package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.handler;

import com.wxxtest.rpc.registration.center.client.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.msg.ReflectInfoMsg;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.msg.ResponseWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

public class RequestHandler extends ChannelInboundHandlerAdapter {

  private final Map<String, ReflectInfoMsg> reflectMsgMap;

  public RequestHandler(Map<String, ReflectInfoMsg> reflectMsgMap) {
    this.reflectMsgMap = reflectMsgMap;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof ResponseWrapper<?> responseWrapper) {
      System.out.printf("服务端开始执行具体服务,methodName=%s%n", responseWrapper.getMethodName());
      ReflectInfoMsg reflectInfoMsg = reflectMsgMap.get(responseWrapper.getMethodName());

      //执行具体的方法
      Object respObj = reflectInfoMsg.getMethod().invoke(
              reflectInfoMsg.getMethodInstance(), responseWrapper.getRequest());

      //将对象按照指定规则反序列化并写出
      ByteBuf byteBuf = ByteBufUtil.objectToByte(respObj, responseWrapper.getRequestId(), null);
      ctx.writeAndFlush(byteBuf);

      System.out.printf("服务端响应客户端成功，respLen=%s", byteBuf.readableBytes());
    }
    super.channelRead(ctx, msg);
  }

  /**
   * Netty异常捕捉流畅
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    //将异常写会给客户端这样客户端才知道发生了什么
    //可以不在这儿写，在read中catch向客户端写
    cause.printStackTrace();
    //这里会将nettyServer关掉，需要思考是否合适
    ctx.close();
  }
}

