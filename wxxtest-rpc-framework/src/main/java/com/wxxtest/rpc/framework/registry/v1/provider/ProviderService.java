package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v1.provider;

import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v1.LongConnectionService;

public class ProviderService {
  public void register(String serviceName) throws Exception{
    LongConnectionService.instance.registerService(serviceName);
  }
}
