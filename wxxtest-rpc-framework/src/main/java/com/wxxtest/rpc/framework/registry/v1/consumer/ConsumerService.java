package com.wxxtest.rpc.framework.registry.v1.consumer;

import com.wxxtest.rpc.framework.registry.v1.LongConnectionService;

import java.util.Map;

public class ConsumerService {

  public Map<String, Integer> getServiceAddress(String serviceName) {
    try {
      return LongConnectionService.instance.getService(serviceName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
