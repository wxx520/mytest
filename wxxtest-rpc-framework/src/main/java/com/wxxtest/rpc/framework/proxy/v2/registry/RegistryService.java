package com.wxxtest.rpc.framework.proxy.v2.registry;

import java.util.Map;

public interface RegistryService {

  Map<String, Integer> getProviderHosts(String serviceInfo);
}
