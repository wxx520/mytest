package com.wxxtest.rpc.framework.registry.v3.pool;

import com.wxxtest.rpc.framework.registry.v3.handler.LoginHandler;
import com.wxxtest.rpc.framework.registry.v3.handler.RegistryResponseDecoder;
import com.wxxtest.rpc.framework.registry.v3.handler.RegistryResponseHandler;
import com.wxxtest.rpc.framework.registry.v3.msg.MsgWrapper;
import com.wxxtest.rpc.framework.registry.v3.msg.RPCIdRequestTag;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class NettyClient<T extends RPCIdRequestTag> {
  private static final AtomicLong COUNTER = new AtomicLong(1);
  public static final int CONNECT_TIMEOUT = 5000;

  private final long id;
  private final AtomicReference<Channel> channelHolder = new AtomicReference<>();

  private final ServiceInfoManager<T> serviceInfoManager;

  public NettyClient(ServiceInfoManager<T> serviceInfoManager) {
    this.id = COUNTER.getAndIncrement();
    this.serviceInfoManager = serviceInfoManager;
  }

  public synchronized void connect(String host, int port) throws Exception {
    System.out.printf("try starting a new connection %s, %s, %s", id, host, port);
    // connect should be done in a new Thread
    CompletableFuture<Channel> channelCarrier = new CompletableFuture<>();
    Thread connectThread = new Thread(() -> doConnect(channelCarrier, host, port));
    connectThread.start();

    Channel channel = channelCarrier.get(
            50000, TimeUnit.MILLISECONDS);
    channelHolder.set(channel);
    System.out.printf("start new connection success %s", id);
  }

  private void doConnect(CompletableFuture<Channel> channelCarrier,
                         String host, int port) {
    /*EventLoopGroup workerGroup = new NioEventLoopGroup(
            1, new DefaultThreadFactory("forwardMsg2GovPool"));*/
    System.out.printf("host=%s,port=%s", host, port);
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
          ch.pipeline().addLast(new RegistryResponseDecoder());
          ch.pipeline().addLast(new LoginHandler(channelCarrier));
          ch.pipeline().addLast(new RegistryResponseHandler<>(serviceInfoManager));
        }
      });

      ChannelFuture f = b.connect(host, port).sync();

      // Wait until the connection is closed
      f.channel().closeFuture().sync();
    } catch (InterruptedException ie) {
      System.out.printf("connect to server error %s, %s", id, ie);
      Thread.currentThread().interrupt();
      channelCarrier.completeExceptionally(ie);
    } finally {
      workerGroup.shutdownGracefully();
    }
  }

  public T sendAndGetAck(long requestId, ByteBuf requestMsg, long timeoutMillis) {
    try {
      CompletableFuture<T> ackFuture = new CompletableFuture<>();
      MsgWrapper<T> msgWrapper = new MsgWrapper<>();
      msgWrapper.setMsg(requestMsg);
      msgWrapper.setAckFuture(ackFuture);
      msgWrapper.setRequestId(requestId);

      channelHolder.get().writeAndFlush(msgWrapper).sync();

      // wait to get ack message
      return ackFuture.get(timeoutMillis, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    } catch (ExecutionException | TimeoutException e) {
      throw new RuntimeException(e);
    }
  }

  public void close() throws InterruptedException {
    channelHolder.get().close().sync();
  }

  public void send(ByteBuf requestMsg) throws InterruptedException {
    channelHolder.get().writeAndFlush(requestMsg).sync();
  }

  public long getId() {
    return id;
  }
}