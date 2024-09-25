package com.wxxtest.rpc.registration.center.client.rpc.service.v3;

import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationResponse;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationService;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationServiceDefaultImpl;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MahjongInvitationServiceImplV3 implements MahjongInvitationService {

  ThreadPoolExecutor executor = new ThreadPoolExecutor(
          5, 5, 5,
          TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

  private final MahjongInvitationService realService = new MahjongInvitationServiceDefaultImpl();


  /**
   * 向线程池提交任务
   *
   * @param request 请求体
   * @return 响应体
   */
  @Override
  public MahjongInvitationResponse invite(MahjongInvitationRequest request) {
    try {
      return executor.submit(() -> realService.invite(request)).get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 多个线程需要向socket中写数据，使用Synchronized进行共享保护
   * @param socket 长连接
   * @param request 请求体
   */
  public void invite(Socket socket, MahjongInvitationRequest request) {
    executor.submit(() -> {
      MahjongInvitationResponse response = realService.invite(request);
      write(socket, response);
    });
  }

  private synchronized void write(Socket socket, MahjongInvitationResponse response) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(response);
      System.out.printf("服务端响应成功,req:%s\n", response.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
