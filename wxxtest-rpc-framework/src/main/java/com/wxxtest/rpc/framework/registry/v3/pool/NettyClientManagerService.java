package com.wxxtest.rpc.framework.registry.v3.pool;

import com.wxxtest.rpc.framework.registry.v3.msg.RPCIdRequestTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * netty客户端连接管理服务
 */
public class NettyClientManagerService<T extends RPCIdRequestTag> {
  private final List<NettyClient<T>> nettyClients;

  private final ServiceInfoManager<T> serviceInfoManager;

  private int count;

  private final int clientsSize;

  public NettyClientManagerService(Map<String, Integer> hosts, ServiceInfoManager<T> serviceInfoManager) {
    nettyClients = new ArrayList<>();
    count = 0;
    this.serviceInfoManager = serviceInfoManager;
    for (Map.Entry<String, Integer> host : hosts.entrySet()) {
      try {
        nettyClients.add(createAndConnect(host.getKey(), host.getValue()));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    clientsSize = nettyClients.size();
  }

  public NettyClient<T> getClient() {
    if (clientsSize == 0) {
      throw new RuntimeException("无连接可用");
    }

    NettyClient<T> nc = nettyClients.get(count);
    count++;
    count = count % clientsSize;
    return nc;
  }

  private NettyClient<T> createAndConnect(String host, int port) throws Exception {
    System.out.println("start creating new netty client.......");
    NettyClient<T> nettyClient = new NettyClient<>(serviceInfoManager);
    nettyClient.connect(host, port);
    System.out.printf("create new netty client success: %s, %s%n", host, port);
    return nettyClient;
  }

  /**
   * TODO 怎么还
   */
  public void returnObject(NettyClient<T> nettyClient) {
  }
}
