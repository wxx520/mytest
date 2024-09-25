package com.wxxtest.rpc.framework.registry.v3.handler;

import com.wxxtest.rpc.framework.registry.v3.msg.MsgWrapper;
import com.wxxtest.rpc.framework.registry.v3.msg.RPCIdRequestTag;
import com.wxxtest.rpc.framework.registry.v3.pool.ServiceInfoManager;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.CompletableFuture;

public class RegistryResponseHandler<T extends RPCIdRequestTag> extends ChannelDuplexHandler {
  private final ServiceInfoManager<T> serviceManager;

  public RegistryResponseHandler(ServiceInfoManager<T> serviceManager) {
    this.serviceManager = serviceManager;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    T resp = (T) msg;

    CompletableFuture<T> respFuture = serviceManager.remove(resp.getRequestId());
    if (respFuture != null) {
      respFuture.complete(resp);
    }
    System.out.printf("响应注册请求内容为，%s", resp);
    super.channelRead(ctx, msg);
  }

  /**
   * 1、检查消息类型，是否为MsgWrapper
   * 2、
   */
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    if(msg instanceof MsgWrapper msgWrapper){
      serviceManager.put(msgWrapper.getRequestId(),msgWrapper.getAckFuture());
      super.write(ctx, msgWrapper.getMsg(), promise);
    }

  }

  /**
   * Netty异常捕捉流畅
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}

