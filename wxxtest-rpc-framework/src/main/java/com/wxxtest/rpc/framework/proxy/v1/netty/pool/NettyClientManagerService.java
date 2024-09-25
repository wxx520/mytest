package com.wxxtest.rpc.framework.proxy.v1.netty.pool;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * netty客户端连接管理服务
 */
public class NettyClientManagerService {
  private final List<NettyClient> nettyClients;

  private int count;

  private final int clientsSize;

  public NettyClientManagerService(Map<String, Integer> hosts) {
    nettyClients = new ArrayList<>();
    count = 0;
    for (Map.Entry<String, Integer> host : hosts.entrySet()) {
      try {
        nettyClients.add(createAndConnect(host.getKey(), host.getValue()));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    clientsSize = nettyClients.size();
  }

  public NettyClient getClient() {
    if (clientsSize == 0) {
      throw new RuntimeException("无连接可用");
    }

    NettyClient nc = nettyClients.get(count);
    count++;
    count = count % clientsSize;
    return nc;
  }

  private NettyClient createAndConnect(String host, int port) throws Exception {
    System.out.println("start creating new netty client.......");
    NettyClient nettyClient = new NettyClient();
    nettyClient.connect(host, port);
    System.out.printf("create new netty client success: %s, %s%n", host, port);
    return nettyClient;
  }
}
