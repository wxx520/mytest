package com.wxxtest.rpc.registration.center.client.rpc.service.v5;


import com.wxxtest.rpc.framework.registry.v5.NettyServer;
import com.wxxtest.rpc.framework.registry.v5.RegistryServiceManager;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationService;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationServiceDefaultImpl;

public class ServerV5Test {

  public static void main(String[] args) {
    ServerV5Test v5Test = new ServerV5Test();
    v5Test.testServer();
  }

  private void testServer() {
    final RegistryServiceManager registryServiceManager = new RegistryServiceManager();
    NettyServer nettyServer = new NettyServer(registryServiceManager);
    nettyServer.initServer(2988);

    Class<MahjongInvitationService> testClazz = MahjongInvitationService.class;

    MahjongInvitationService instance = new MahjongInvitationServiceDefaultImpl();

    registryService(registryServiceManager, testClazz, instance);

    //closeService(registryServiceManager, testClazz);
  }

  private void closeService(RegistryServiceManager registryServiceManager, Class<?> clazz) {
    Thread removeServiceThread = new Thread(() -> {
      try {
        long sleepTime = (long) 60 * 1000;
        Thread.sleep(sleepTime);
        System.out.printf("下线服务前的休眠%s%n", sleepTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("test v5 下线服务前的休眠失败");
        Thread.currentThread().interrupt();
      }
      Class<?> offlineService = registryServiceManager.removeService(clazz);
      System.out.printf("test v5 下线服务成功%s%n", offlineService == null ? "null" : offlineService.getName());
    });
    removeServiceThread.start();
  }

  private <T> void registryService(RegistryServiceManager registryServiceManager, Class<T> clazz, T instance) {
    Thread registryThread = new Thread(() -> {
      registryServiceManager.registryService(clazz, instance);
      System.out.printf("test v5 注册服务成功%s%n", clazz.getName());
    });
    registryThread.start();
  }
}
