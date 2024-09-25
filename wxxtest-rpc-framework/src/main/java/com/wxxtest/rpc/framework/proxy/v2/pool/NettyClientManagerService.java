package com.wxxtest.rpc.framework.proxy.v2.pool;


import com.wxxtest.rpc.framework.proxy.v2.msg.MsgWrapper;
import com.wxxtest.rpc.framework.proxy.v2.registry.RegistryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * netty客户端连接管理服务
 */
public class NettyClientManagerService {
  private final List<NettyClient> nettyClients;

  private final RegistryService registryService;

  private final String serviceName;

  private int count;

  private static final long GET_TIMEOUT = 3000;

  public NettyClientManagerService(String serviceName) {
    nettyClients = new ArrayList<>();
    registryService = serviceInfo -> {
      Map<String, Integer> serviceHosts = new HashMap<>();
      serviceHosts.put("0.0.0.0", 2988);
      return serviceHosts;
    };
    count = 0;
    this.serviceName = serviceName;
  }

  /**
   * 根据服务地址创建所有链接
   */
  public void intServerManger() {
    Map<String, Integer> hosts = registryService.getProviderHosts(serviceName);
    for (Map.Entry<String, Integer> host : hosts.entrySet()) {
      try {
        NettyClient client = createAndConnect(host.getKey(), host.getValue());
        nettyClients.add(client);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * 根据制定的负载均衡的规则获取链接
   */
  public NettyClient getClient() {
    if (nettyClients.isEmpty()) {
      throw new RuntimeException("无连接可用");
    }
    count = count % nettyClients.size();
    return nettyClients.get(count++);
  }

  private NettyClient createAndConnect(String host, int port) throws Exception {
    System.out.println("manager start creating new netty client.......");
    NettyClient nettyClient = new NettyClient();
    nettyClient.connect(host, port);
    System.out.printf("manager create new netty client success: %s, %s%n", host, port);
    return nettyClient;
  }

  public Object sendAndGetAck(MsgWrapper requestMsg) throws ExecutionException, InterruptedException, TimeoutException {
    return sendAndGetAck(requestMsg, GET_TIMEOUT);
  }

  /**
   * TODO 无异常处理逻辑，只是简单抛出
   */
  public Object sendAndGetAck(MsgWrapper requestMsg, long timeoutMillis) throws ExecutionException, InterruptedException, TimeoutException {
    long sendTime = System.currentTimeMillis();
    NettyClient nettyClient = getClient();
    assert nettyClient != null;
    Object respMsg = nettyClient.sendAndGetAck(requestMsg, timeoutMillis);
    System.out.printf("manager发送请求成功，共计耗时%s, 返回结果为%s%n", System.currentTimeMillis() - sendTime, respMsg);
    return respMsg;
  }
}
