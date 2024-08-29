package com.wxxtest.rpc.consumer.v3;

import com.wxxtest.multi.thread.Future;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TCPLongClientConnectionV3 {
  Map<Long, Future<Object>> responseFMap = new ConcurrentHashMap<>();


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
        responseFMap.remove(response.getId()).complete(response);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void send(MahjongInvitationRequest request, Future<Object> result) {
    requestsQueue.add(request);
    responseFMap.put(request.getId(),result);
  }
}
