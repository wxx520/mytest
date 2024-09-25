package com.wxxtest.rpc.registration.center.client.rpc.consumer.v3;

import com.wxxtest.rpc.registration.center.client.multi.thread.Future;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationRequest;

public class MahjongInvitationServiceProxy {
  private final TCPLongClientConnectionV3 connectionClient;

  public MahjongInvitationServiceProxy() {
    connectionClient = new TCPLongClientConnectionV3();
    new Thread(() -> connectionClient.connect(null)).start();
  }

  public void invite(MahjongInvitationRequest request, Future<Object> result) {
    connectionClient.send(request, result);
  }
}
