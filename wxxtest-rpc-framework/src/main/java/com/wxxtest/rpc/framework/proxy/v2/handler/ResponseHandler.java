package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.handler;

import com.wxxtest.rpc.registration.center.client.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.msg.MsgWrapper;
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

  private final Map<Long, MsgWrapper> requestInfoMap;

  private final CompletableFuture<Channel> channelCarrier;

  public ResponseHandler(CompletableFuture<Channel> channelCarrier, Map<Long, MsgWrapper> responseMap) {
    this.requestInfoMap = responseMap;
    this.channelCarrier = channelCarrier;
  }

  /**
   * 1、检查消息类型，是否为MsgWrapper
   * 2、
   */
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    if (msg instanceof MsgWrapper msgWrapper) {
      Object[] params = msgWrapper.getParams();

      //TODO 对请求对象序列化时，这里只简单处理第一个参数
      ByteBuf reqBytes = ByteBufUtil.objectToByte(params[0], msgWrapper.getRequestId(), msgWrapper.getServiceName());

      requestInfoMap.put(msgWrapper.getRequestId(), msgWrapper);
      super.write(ctx, reqBytes, promise);
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

