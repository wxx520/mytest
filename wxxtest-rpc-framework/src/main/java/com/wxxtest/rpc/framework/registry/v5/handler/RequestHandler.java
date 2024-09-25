package com.wxxtest.rpc.framework.registry.v5.handler;

import com.wxxtest.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.framework.registry.v5.RegistryServiceManager;
import com.wxxtest.rpc.framework.registry.v5.msg.RequestWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 调用真实的服务
 * 若成功并将结果写回给客户端
 * 若失败也将失败的原因写给客户端
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {

  private final RegistryServiceManager realServiceManager;

  public RequestHandler(RegistryServiceManager realServiceManager) {
    this.realServiceManager = realServiceManager;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof RequestWrapper requestWrapper) {
      System.out.printf("v5服务端开始执行具体服务,methodName=%s%n", requestWrapper.getMethodName());
      //执行具体的方法
      Object respObj = realServiceManager.invokeRealService(requestWrapper.getMethodName(), requestWrapper.getRequest());

      //将对象按照指定规则反序列化并写出
      ByteBuf byteBuf = ByteBufUtil.objectToByte(respObj, requestWrapper.getRequestId());
      ctx.writeAndFlush(byteBuf);

      System.out.printf("v5服务端响应客户端成功，respLen=%s%n", byteBuf.readableBytes());
    }
    super.channelRead(ctx, msg);
  }

  /**
   * Netty异常捕捉流畅
   * TODO
   * 1、将异常写会给客户端这样客户端才知道发生了什么
   * 2、关闭客户端一般只在NettyServer出现内部异常，服务提供链接服务是关闭，不会因为业务逻辑异常而关闭
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
