package com.wxxtest.rpc.registration.center.client.rpc.consumer.v5;

import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.gen.RpcProxyCreator;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationResponse;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationService;

public class ClientV5Test {
  public static void main(String[] args) {
    MahjongInvitationRequest req = getReq();

    RpcProxyCreator creator = new RpcProxyCreator();
    MahjongInvitationService proxy = creator.create(MahjongInvitationService.class);
    MahjongInvitationResponse resp = proxy.invite(req);
    System.out.println("v5 完成一次客户端代理测试：" + resp);
  }

  private static MahjongInvitationRequest getReq() {
    MahjongInvitationRequest req = new MahjongInvitationRequest();
    req.setId(234);
    req.setTime(System.currentTimeMillis());
    req.setMsg("i want to play");
    req.setName("GUANGDONG");
    req.setAge(18);
    return req;
  }


}
