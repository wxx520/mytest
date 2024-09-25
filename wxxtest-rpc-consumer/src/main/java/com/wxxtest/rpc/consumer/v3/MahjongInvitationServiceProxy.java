package com.wxxtest.rpc.consumer.v3;

import com.wxxtest.multi.thread.Future;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationRequest;

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
