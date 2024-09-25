package com.wxxtest.registration.v1;

import com.wxxtest.registration.ServiceManager;
import com.wxxtest.registration.v1.pool.NettyServer;

public class App {
  public static void main(String[] args) {
    ServiceManager sm = new ServiceManager();
    NettyServer ns = new NettyServer(sm);
    ns.connect(2666);
  }
}
