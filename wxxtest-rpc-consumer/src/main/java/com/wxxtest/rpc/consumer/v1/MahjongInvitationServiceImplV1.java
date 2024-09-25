package com.wxxtest.rpc.registration.center.client.rpc.consumer.v1;

import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationResponse;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationService;

public class MahjongInvitationServiceImplV1 implements MahjongInvitationService {
  private final TCPLongConnectionClientV1 connectionClient;

  public MahjongInvitationServiceImplV1() {
    connectionClient = new TCPLongConnectionClientV1();
    new Thread(() -> connectionClient.connect(null)).start();
  }


  @Override
  public MahjongInvitationResponse invite(MahjongInvitationRequest request) {
    return connectionClient.send(request);
  }

}
