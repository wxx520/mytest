package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v1;


import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.constant.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

public class LongConnectionService {

  public static final String REGISTRY_SERVICE_HOST = "0.0.0.0";

  public static final int REGISTRY_SERVICE_PORT = 2666;

  private Socket registerConn;

  public static final LongConnectionService instance = new LongConnectionService();

  private LongConnectionService() {
  }

  public void registerService(String serviceName) throws IOException {
    ObjectOutputStream dos = new ObjectOutputStream(registerConn.getOutputStream());
    dos.writeObject(new RegistryRequestInfo(RequestType.REGISTRY_SERVICE.getCode(), serviceName, InetAddress.getLocalHost().getHostAddress()));
    dos.flush();
    ObjectInputStream dis = new ObjectInputStream(registerConn.getInputStream());
    RegistryRequestInfo response = null;
    try {
      response = (RegistryRequestInfo) dis.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("注册服务成功：" + response);
  }

  public Map<String, Integer> getService(String serviceName)
          throws IOException, ClassNotFoundException {
    ObjectOutputStream dos = new ObjectOutputStream(
            registerConn.getOutputStream());
    dos.writeObject(new RegistryRequestInfo(RequestType.REGISTRY_FIND.getCode(), serviceName, null));
    dos.flush();
    ObjectInputStream dis = new ObjectInputStream(
            registerConn.getInputStream());
    RegistryResponseInfo response = (RegistryResponseInfo) dis.readObject();
    System.out.println("发现服务：" + response);
    return response.getAddresses();
  }

  public synchronized void start() throws Exception {
    if (registerConn == null) {
      registerConn = new Socket(REGISTRY_SERVICE_HOST,
              REGISTRY_SERVICE_PORT);
    }
  }
}