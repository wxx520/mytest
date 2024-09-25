/*
 * Copyright (c) Jaguar Land Rover Ltd 2024. All rights reserved
 */

package com.wxxtest.rpc.framework.proxy.v1.netty.msg;

import com.wxxtest.rpc.framework.proxy.v1.netty.exeception.SendMessageException;
import com.wxxtest.rpc.framework.proxy.v1.netty.pool.NettyClientManagerService;
import com.wxxtest.rpc.framework.proxy.v1.netty.pool.NettyClient;
import com.wxxtest.rpc.framework.registry.v3.exception.BorrowNettyClientException;
import io.netty.buffer.ByteBuf;

public class MessageSender {

  private final NettyClientManagerService pool;

  public MessageSender(NettyClientManagerService pool) {
    this.pool = pool;
  }

  public <T> T sendAndGetAck(long requestId, ByteBuf requestMsg, long timeoutMillis, Class<T> returnType) {
    return sendInner(true, requestId, requestMsg, timeoutMillis, returnType);
  }

  public void send(ByteBuf requestMsg, long requestId) {
    sendInner(false, requestId, requestMsg, null, null);
  }

  private <T> T sendInner(boolean waitAck, long requestId, ByteBuf requestMsg, Long timeoutMillis, Class<T> returnType) {
    long sendTime = System.currentTimeMillis();
    NettyClient nettyClient;
    try {
      nettyClient = pool.getClient();
      assert nettyClient != null;
      System.out.printf("%n使用netty连接成功reqId: %s, costs: %s ms%n",
              requestId, System.currentTimeMillis() - sendTime);
    } catch (Exception e) {
      System.out.println("----------- 借NettyClient连接异常--------------");
      throw new BorrowNettyClientException(e);
    }

    try {
      T ackMsg = null;
      if (waitAck) {
        ackMsg = nettyClient.sendAndGetAck(requestId, requestMsg, timeoutMillis, returnType);
      } else {
        nettyClient.send(requestMsg);
      }

      System.out.printf("连接使用完毕 reqId: %s, 总耗时: %s ms%n",
              requestId, System.currentTimeMillis() - sendTime);
      //pool.returnObject(nettyClient);
      return ackMsg;
    } catch (Exception e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      System.out.println("----------- 发送消息异常 --------------");

      throw new SendMessageException(e);
    }
  }
}
