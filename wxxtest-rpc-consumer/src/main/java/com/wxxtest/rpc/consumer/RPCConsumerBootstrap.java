package com.wxxtest.rpc.registration.center.client.rpc.consumer;


import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.constant.RegistryServiceConstant;
import com.wxxtest.rpc.registration.center.client.rpc.framework.constant.RequestType;
import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v2.RegistryCenterProxyService;

import java.io.IOException;

public class RPCConsumerBootstrap {
  public static void main(String[] args) {
    RegistryCenterProxyService proxyService = new RegistryCenterProxyService();
    for (int i = 0; i < 100; i++) {
      try {
        RegistryResponseInfo resp = proxyService.sendRequest(new RegistryRequestInfo(RequestType.REGISTRY_FIND.getCode(), RegistryServiceConstant.SERVICE_NAME, null));
        System.out.printf("resp:%s%n", resp);
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
