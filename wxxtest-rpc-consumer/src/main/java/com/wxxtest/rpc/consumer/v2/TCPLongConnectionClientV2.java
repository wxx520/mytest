package com.wxxtest.rpc.consumer.v2;

import com.wxxtest.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TCPLongConnectionClientV2 {
  Map<Long, MahjongInvitationResponse> responseMap = new ConcurrentHashMap<>();

  ArrayBlockingQueue<MahjongInvitationRequest> requestsQueue = new ArrayBlockingQueue<>(100);

  /**
   * 1、和服务端建立长连接
   * 2、不断从队列中取出请求序列化，发送给服务端
   * 3、接收服务端返回后，将结果和id关联
   *
   * @param realServiceInfo 真实服务的相关信息，出入参类型等
   */
  public void connect(Object realServiceInfo) {
    System.out.printf("真实服务信息，%s", realServiceInfo);
    try (Socket client = new Socket("0.0.0.0", 2988)) {
      while (true) {
        if (requestsQueue.isEmpty()) {
          continue;
        }
        System.out.printf("请求队列大小%s\n", requestsQueue.size());
        MahjongInvitationRequest request = requestsQueue.poll();
        ObjectOutputStream dos = new ObjectOutputStream(client.getOutputStream());
        dos.writeObject(request);
        ObjectInputStream dis = new ObjectInputStream(client.getInputStream());
        MahjongInvitationResponse response = (MahjongInvitationResponse) dis.readObject();
        responseMap.put(response.getId(), response);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param request 发送的请求
   * @return 收到的服务端响应
   */
  public MahjongInvitationResponse send(MahjongInvitationRequest request) {
    long ct = System.currentTimeMillis();
    requestsQueue.add(request);
    MahjongInvitationResponse response = responseMap.get(request.getId());
    while (response == null) {
      response = responseMap.get(request.getId());
      long useTime = System.currentTimeMillis() - ct;
      if (useTime > 3000) {
        throw new RuntimeException("超时=" + useTime);
      }
    }

    response.setUseTime(System.currentTimeMillis() - ct);
    responseMap.remove(request.getId());
    return response;
  }
}
