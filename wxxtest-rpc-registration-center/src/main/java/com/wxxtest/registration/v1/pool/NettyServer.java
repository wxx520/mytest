package com.wxxtest.registration.v1.pool;

import com.wxxtest.registration.ServiceManager;
import com.wxxtest.registration.v1.handler.RegistryRequestDecoder;
import com.wxxtest.registration.v1.handler.RegistryRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
  private final ServiceManager serviceManager;

  public NettyServer(ServiceManager serviceManager) {
    this.serviceManager = serviceManager;
  }

  public synchronized void connect(int port) {
    Thread connectedThread = new Thread(() -> doConnect(port));
    connectedThread.start();
  }


  /**
   * 端口2666
   */
  private void doConnect(int port) {
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
              // 制定如何处理读写事件，对应业务逻辑
              .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                  // 不同的ChannelHandler组成责任链（ChannelPipeline）
                  ch.pipeline().addLast(new RegistryRequestDecoder());
                  ch.pipeline().addLast(new RegistryRequestHandler(serviceManager));
                }
              })
              // 配置Server Socket的参数
              .option(ChannelOption.SO_BACKLOG, 128)
              // 配置Client Socket的参数
              .childOption(ChannelOption.SO_KEEPALIVE, true);

      // Bind and start to accept incoming connections.
      ChannelFuture f = b.bind(port).sync(); // (7)

      // Wait until the server socket is closed.
      // In this example, this does not happen, but you can do that to gracefully
      // shut down your server.
      f.channel().closeFuture().sync();
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
      ie.printStackTrace();
    } finally {
      // 优雅关闭
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}