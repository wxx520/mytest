package com.wxxtest.rpc.framework.registry.v2;

import com.wxxtest.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.framework.constant.RegistryServiceConstant;
import com.wxxtest.rpc.framework.constant.RequestType;
import com.wxxtest.rpc.framework.registry.v1.LongConnectionService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistryCenterProxyService {

  private int count;

  private final List<Socket> conns;

  private int connsNum;

  private Map<String, Integer> hosts;

  public RegistryCenterProxyService() {
    conns = new ArrayList<>();
    getHosts();
    createConnections();
    startHeartBeatCheckService();
  }

  private void startHeartBeatCheckService() {
    new Thread(() -> {
      while (!conns.isEmpty()) {
        for (Socket socket : conns) {
          try {
            RegistryRequestInfo req =
                    new RegistryRequestInfo(
                            RequestType.REGISTRY_FIND.getCode(),
                            "wxx", null);
            RegistryResponseInfo resp = findService(req, socket);
            System.out.printf("心跳正常：host=%s, resp=%s%n",
                    socket.getRemoteSocketAddress(), resp);
          } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("心跳检测失败");
          }
        }

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println("休眠异常，心跳检测失败");
        }
      }
    }).start();
  }

  private synchronized RegistryResponseInfo findService(RegistryRequestInfo req, Socket socket) throws IOException, ClassNotFoundException {
    ObjectOutputStream dos = new ObjectOutputStream(
            socket.getOutputStream());
    dos.writeObject(req);
    dos.flush();
    ObjectInputStream dis = new ObjectInputStream(
            socket.getInputStream());
    return (RegistryResponseInfo) dis.readObject();
  }


  /**
   * 建立连接
   */
  private void createConnections() {
    if (hosts == null || hosts.isEmpty()) {
      throw new RuntimeException("没有服务地址，创建连接失败");
    }
    for (Map.Entry<String, Integer> host : hosts.entrySet()) {
      try {
        Socket socket = new Socket(host.getKey(),
                host.getValue());
        conns.add(socket);
        System.out.println("创建连接成功：host=" + host);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("创建链接失败");
      }
    }
    connsNum = conns.size();
  }

  /**
   * 找到需要建立连接的服务方地址
   */
  private void getHosts() {
    try (Socket socket = new Socket(LongConnectionService.REGISTRY_SERVICE_HOST,
            LongConnectionService.REGISTRY_SERVICE_PORT)) {
      ObjectOutputStream dos = new ObjectOutputStream(
              socket.getOutputStream());
      dos.writeObject(new RegistryRequestInfo(RequestType.REGISTRY_FIND.getCode(), RegistryServiceConstant.SERVICE_NAME, null));
      dos.flush();
      ObjectInputStream dis = new ObjectInputStream(
              socket.getInputStream());
      RegistryResponseInfo response = (RegistryResponseInfo) dis.readObject();
      System.out.println("发现服务：" + response);
      hosts = response.getAddresses();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("获取服务地址失败");
    }
  }

  public RegistryResponseInfo sendRequest(RegistryRequestInfo request) throws IOException, ClassNotFoundException {
    count++;
    count = count % connsNum;
    return findService(request, conns.get(count));
  }
}
