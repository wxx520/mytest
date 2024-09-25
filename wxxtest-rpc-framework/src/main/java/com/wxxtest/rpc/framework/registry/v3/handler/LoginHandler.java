/*
 * Copyright (c) Jaguar Land Rover Ltd 2024. All rights reserved
 */

package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.handler;

import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.codec.RegistryRequestToByteEncoder;
import com.wxxtest.rpc.registration.center.client.rpc.framework.constant.RequestType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.CompletableFuture;

public class LoginHandler extends ChannelInboundHandlerAdapter {
  private final CompletableFuture<Channel> channelCarrier;

  private static final String loginTag = "wxxFirstNettyService";

  public LoginHandler(CompletableFuture<Channel> channelCarrier) {
    this.channelCarrier = channelCarrier;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("获取连接成功");
    RegistryResponseInfo resp = (RegistryResponseInfo)msg;
    if(resp.getServiceName().equals(loginTag)){
      ctx.pipeline().remove(this);
      channelCarrier.complete(ctx.channel());
    }

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    RegistryRequestInfo loginReq = new RegistryRequestInfo();
    loginReq.setServiceName(loginTag);
    loginReq.setRequestId(6666888);
    loginReq.setRequestType(RequestType.REGISTRY_FIND.getCode());
    ByteBuf loginMsg = RegistryRequestToByteEncoder.encode(loginReq);
    System.out.printf("\n向服务方发送服务登录信息 %s\n", loginReq);
    ctx.writeAndFlush(loginMsg);
    super.channelActive(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    System.out.printf("exceptionCaught in LoginHandler %s", cause.getMessage());
    channelCarrier.completeExceptionally(cause);
    ctx.close();
  }
}
