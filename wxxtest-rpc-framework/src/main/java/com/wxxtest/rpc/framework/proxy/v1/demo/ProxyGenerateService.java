package com.wxxtest.rpc.framework.proxy.v1.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ProxyGenerateService {

  public Object generateProxy(String fullClassName, String methodName) throws Exception {
    Class<?> clazz = Class.forName(fullClassName);
    return Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
            new Class[]{clazz},
            new InvocationHandler() {
              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals(methodName)) {
                  // send request to service provider and get response
                  // ......
                  return null;
                } else {
                  return method.invoke(proxy, args);
                }
              }
            });
  }

  protected Map<String, Integer> getHosts(String serviceName) {
    return null;
  }

}
