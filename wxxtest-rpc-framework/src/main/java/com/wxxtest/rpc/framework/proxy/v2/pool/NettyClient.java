package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.pool;

import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.handler.ResponseDecoder;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.handler.ResponseHandler;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.msg.MsgWrapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1、 建立NIO长连接
 * 2、使用长连接发送请求接收响应
 */
public class NettyClient {

  private final AtomicReference<Channel> channelHolder = new AtomicReference<>();

  public static final int CONNECT_TIMEOUT = 5000;

  public synchronized void connect(String host, int port) throws Exception {
    System.out.printf("netty client try starting a new connection, %s, %s%n", host, port);
    // connect should be done in a new Thread
    CompletableFuture<Channel> channelCarrier = new CompletableFuture<>();
    Thread connectThread = new Thread(() -> doConnect(channelCarrier, host, port));
    connectThread.start();

    Channel channel = channelCarrier.get(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
    channelHolder.set(channel);
    System.out.println("netty client start new connection success");
  }

  private void doConnect(CompletableFuture<Channel> channelCarrier, String host, int port) {
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(workerGroup);
      b.channel(NioSocketChannel.class);
      b.option(ChannelOption.SO_KEEPALIVE, true);
      b.option(ChannelOption.TCP_NODELAY, true);
      b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT);
      b.handler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) {
          Map<Long, MsgWrapper> requestIdAndMsgInfoMap = new HashMap<>();
          ch.pipeline().addLast(new ResponseDecoder(requestIdAndMsgInfoMap));
          ch.pipeline().addLast(new ResponseHandler(channelCarrier, requestIdAndMsgInfoMap));
        }
      });

      ChannelFuture f = b.connect(host, port).sync();
      // Wait until the connection is closed
      f.channel().closeFuture().sync();
    } catch (InterruptedException ie) {
      System.out.printf("netty client connect to server error, %s%n", ie);
      Thread.currentThread().interrupt();
      channelCarrier.completeExceptionally(ie);
    } finally {
      workerGroup.shutdownGracefully();
    }
  }

  public Object sendAndGetAck(MsgWrapper msg, long timeoutMills) throws ExecutionException, InterruptedException, TimeoutException {
    try {

      channelHolder.get().writeAndFlush(msg).sync();

      // wait to get ack message
      return msg.getAckFuture().get(timeoutMills, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw e;
    }
  }
}
