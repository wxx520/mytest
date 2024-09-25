package com.wxxtest.rpc.registration.center.client.rpc.consumer.v2;

import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationResponse;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationService;

public class MahjongInvitationServiceImplV2 implements MahjongInvitationService {
  private final TCPLongConnectionClientV2 connectionClient;

  public MahjongInvitationServiceImplV2() {
    connectionClient = new TCPLongConnectionClientV2();
    new Thread(() -> connectionClient.connect(null)).start();
  }


  @Override
  public MahjongInvitationResponse invite(MahjongInvitationRequest request) {
    return connectionClient.send(request);
  }

}
