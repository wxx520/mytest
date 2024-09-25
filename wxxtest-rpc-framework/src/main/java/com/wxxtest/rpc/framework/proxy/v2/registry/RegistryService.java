package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.registry;

import java.util.Map;

public interface RegistryService {

  Map<String, Integer> getProviderHosts(String serviceInfo);
}
