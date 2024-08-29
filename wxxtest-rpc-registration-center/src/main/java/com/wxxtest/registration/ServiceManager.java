package com.wxxtest.registration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManager {

  private final Map<String, Map<String, Integer>> services;


  public ServiceManager() {
    services = new ConcurrentHashMap<>();
  }

  public synchronized void register(String serviceName, String address, int port) {
    Map<String, Integer> addsInfo = services.computeIfAbsent(
            serviceName, k -> new HashMap<>());
    addsInfo.put(address, port);
  }

  public Map<String, Integer> getService(String serviceName) {
    return services.get(serviceName);
  }

}
