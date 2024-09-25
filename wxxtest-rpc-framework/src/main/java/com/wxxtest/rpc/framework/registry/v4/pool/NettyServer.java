package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.pool;

import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.handler.RequestDecoder;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.handler.RequestHandler;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.msg.ReflectInfoMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class NettyServer {

  public synchronized void connect(int port, Class<?> serviceClazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    final Map<String, ReflectInfoMsg> map = new HashMap<>();
    Object instance = serviceClazz.getDeclaredConstructor().newInstance();
    for (Method method : serviceClazz.getMethods()) {
      map.put(method.getName(), new ReflectInfoMsg(method, instance));
    }

    System.out.printf("try starting a new connection, %s%n", port);
    Thread connectThread = new Thread(() -> doConnect(port, map));
    connectThread.start();
    System.out.printf("started new connection success%n");
  }

  private void doConnect(int port, Map<String, ReflectInfoMsg> reflectMsgMap) {
    System.out.printf("doConnect...port=%s%n", port);
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
                  ch.pipeline().addLast(new RequestDecoder(reflectMsgMap));
                  ch.pipeline().addLast(new RequestHandler(reflectMsgMap));
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
      System.out.printf("connect to server error, %s%n", ie);
      Thread.currentThread().interrupt();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}