package com.wxxtest.rpc.framework;

import com.wxxtest.rpc.framework.registry.v1.LongConnectionService;

public class RPCFrameworkBootstrap {
  public static void main(String[] args) {
    new RPCFrameworkBootstrap().start();
  }

  public void start() {
    //建立注册中心的长连接
    try {
      LongConnectionService.instance.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
