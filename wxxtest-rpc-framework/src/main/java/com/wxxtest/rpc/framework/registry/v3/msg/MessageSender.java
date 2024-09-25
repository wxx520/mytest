/*
 * Copyright (c) Jaguar Land Rover Ltd 2024. All rights reserved
 */

package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.msg;

import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.exception.BorrowNettyClientException;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.pool.NettyClient;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.pool.NettyClientManagerService;
import io.netty.buffer.ByteBuf;

public class MessageSender<T extends RPCIdRequestTag> {

  private final NettyClientManagerService<T> pool;

  public MessageSender(NettyClientManagerService<T> pool) {
    this.pool = pool;
  }

  public T sendAndGetAck(long requestId, ByteBuf requestMsg, long timeoutMillis){
    return sendInner(true, requestId, requestMsg, timeoutMillis);
  }

  public void send(ByteBuf requestMsg, long requestId){
    sendInner(false, requestId, requestMsg, null);
  }

  private T sendInner(boolean waitAck, long requestId, ByteBuf requestMsg, Long timeoutMillis) {
    long sendTime = System.currentTimeMillis();
    NettyClient<T> nettyClient;
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
        ackMsg = nettyClient.sendAndGetAck(requestId, requestMsg, timeoutMillis);
      } else {
        nettyClient.send(requestMsg);
      }

      System.out.printf("连接使用完毕 reqId: %s, 总耗时: %s ms%n",
              requestId, System.currentTimeMillis() - sendTime);
      pool.returnObject(nettyClient);
      return ackMsg;
    } catch (Exception e) {
      if (e instanceof InterruptedException) {
        Thread.currentThread().interrupt();
      }
      System.out.println("----------- 发送消息异常 --------------");

      throw new RuntimeException(e);
    }
  }
}
