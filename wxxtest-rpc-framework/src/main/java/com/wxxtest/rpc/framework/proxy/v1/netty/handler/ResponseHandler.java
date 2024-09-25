package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.handler;

import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.msg.MsgWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 获取链接Channel，发送信息
 */
public class ResponseHandler extends ChannelDuplexHandler {

  private final Map<Long, MsgWrapper> responseMap;

  private final CompletableFuture<Channel> channelCarrier;

  public ResponseHandler(CompletableFuture<Channel> channelCarrier, Map<Long, MsgWrapper> responseMap) {
    this.responseMap = responseMap;
    this.channelCarrier = channelCarrier;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    super.channelRead(ctx, msg);
  }

  /**
   * 1、检查消息类型，是否为MsgWrapper
   * 2、
   */
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    if (msg instanceof MsgWrapper msgWrapper) {
      ByteBuf reqByte = msgWrapper.getMsg();
      //缓存的对象只需要保存和响应结果相关的内容，不需要请求相关的
      msgWrapper.setMsg(null);
      responseMap.put(msgWrapper.getRequestId(), msgWrapper);
      super.write(ctx, reqByte, promise);
    }

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    channelCarrier.complete(ctx.channel());
    super.channelActive(ctx);
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

