package com.wxxtest.rpc.service.v4;

import com.wxxtest.rpc.framework.registry.v4.pool.NettyServer;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationServiceDefaultImpl;

import java.lang.reflect.InvocationTargetException;

public class ServerV4Test {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    NettyServer nettyServer = new NettyServer();
    nettyServer.connect(2988, MahjongInvitationServiceDefaultImpl.class);
    //一个端口对应多个服务的设计
    //网络端口的监听应该和服务注册分开
  }
}
