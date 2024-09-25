package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v5;

import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v5.handler.RequestDecoder;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v5.handler.RequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

  private final RegistryServiceManager registryServiceManager;

  public NettyServer(RegistryServiceManager registryServiceManager) {
    this.registryServiceManager = registryServiceManager;
  }

  public synchronized void initServer(int port) {
    System.out.printf("v5 try starting a new connection, %s%n", port);
    Thread connectThread = new Thread(() -> doConnect(port));
    connectThread.start();
    System.out.printf("v5 started new connection success%n");
  }

  private void doConnect(int port) {
    System.out.printf("v5 doConnect...port=%s%n", port);
    // 用于处理连接事件的线程组
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    // 用于处理读写事件的线程组
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      // Bootstrap是一个工具类，或者说容器，用于把各种配置组合起来
      ServerBootstrap b = new ServerBootstrap();
      // 配置用于处理事件的线程组
      b.group(bossGroup, workerGroup)
              // 指定要使用的IO类型，这里是NIO；Netty也支持传统的Blocking IO
              .channel(NioServerSocketChannel.class)
              //制定如何处理读写事件，对应业务逻辑
              .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                  ch.pipeline().addLast(new RequestDecoder(registryServiceManager));
                  ch.pipeline().addLast(new RequestHandler(registryServiceManager));
                }
              })
              //配置Server Socket的参数
              .option(ChannelOption.SO_BACKLOG, 128)
              //配置Client Socket的参数
              .childOption(ChannelOption.SO_KEEPALIVE, true);

      // Wait until the server socket is closed.
      ChannelFuture f = b.bind(port).sync();

      // Wait until the connection is closed
      f.channel().closeFuture().sync();
    } catch (InterruptedException ie) {
      System.out.printf("v5 connect to server error, %s%n", ie);
      Thread.currentThread().interrupt();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}
