package com.wxxtest.rpc.service;

import com.wxxtest.rpc.framework.RPCFrameworkBootstrap;
import com.wxxtest.rpc.framework.registry.v1.provider.ProviderService;

public class RPCProviderBootstrap {
  public static void main(String[] args) {
    RPCFrameworkBootstrap bt = new RPCFrameworkBootstrap();
    bt.start();
    ProviderService ps = new ProviderService();
    try {
      ps.register("wxx");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("注册失败");
    }
  }
}
