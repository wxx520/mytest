package com.wxxtest.rpc.framework.registry.v3.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ServiceInfoManager<T> {

  private final Map<Long, CompletableFuture<T>> responseMap;

  public ServiceInfoManager(){
    responseMap = new HashMap<>();
  }

  public synchronized void put(Long requestId, CompletableFuture<T> result){
    responseMap.put(requestId,result);
  }

  public synchronized CompletableFuture<T> remove(Long requestId){
    return responseMap.remove(requestId);
  }
}
