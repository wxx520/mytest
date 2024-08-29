package com.wxxtest.rpc.service.v1;

import com.wxxtest.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationResponse;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationService;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationServiceDefaultImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPLongConnectionSeverV1 {

  private final MahjongInvitationService realService = new MahjongInvitationServiceDefaultImpl();

  /**
   * 1、等待客户端连接
   * 2、反序列化请求体
   * 3、调用具体服务获取返回结果
   * 4、序列化结果响应客户端
   *
   * @param realServiceInfo 真实服务的相关信息，需要调用的方法，出入参等
   */
  public void accept(Object realServiceInfo) {
    System.out.printf("尝试服务建立长连接，得到真实服务信息:%s", realServiceInfo);
    try (ServerSocket server = new ServerSocket(2988)) {
      Socket socket = server.accept();
      System.out.println("服务端接收到客户端 长 连接");

      while (true) {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        MahjongInvitationRequest request = (MahjongInvitationRequest) ois.readObject();
        MahjongInvitationResponse response = realService.invite(request);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(response);
        System.out.printf("服务端响应成功,req:%s\n", response.getId());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
