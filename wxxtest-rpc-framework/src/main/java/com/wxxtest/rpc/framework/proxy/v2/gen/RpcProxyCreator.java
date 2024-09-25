package com.wxxtest.rpc.framework.proxy.v2.gen;

import java.lang.reflect.Proxy;

public class RpcProxyCreator {

  /**
   * 根据服务的接口类型
   * 获取服务地址
   */
  @SuppressWarnings({"unchecked"})
  public <T> T create(Class<T> proxyInterfaceType) {
    return (T) Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
            new Class[]{proxyInterfaceType},
            new ClientInvocationHandler(proxyInterfaceType.getName()));
  }
}
