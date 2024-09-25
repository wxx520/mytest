package com.wxxtest.rpc.framework.proxy.v1.gen;

import java.lang.reflect.Proxy;
import java.util.Map;

public class RpcProxyCreator {

  public <T> T create(Class<T> clazz, Map<String, Integer> hosts) {
    return (T) Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
            new Class[]{clazz},
            new ClientInvocationHandler(hosts));
  }
}
