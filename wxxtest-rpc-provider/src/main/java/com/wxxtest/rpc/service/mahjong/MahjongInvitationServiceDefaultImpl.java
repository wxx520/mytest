package com.wxxtest.rpc.service.mahjong;

import java.util.ArrayList;
import java.util.List;

public class MahjongInvitationServiceDefaultImpl implements MahjongInvitationService {

  @Override
  public MahjongInvitationResponse invite(MahjongInvitationRequest request) {
    MahjongInvitationResponse response = new MahjongInvitationResponse();
    response.setMsg(request.getMsg());
    response.setReply("周末打广东麻将吗");
    response.setTime(System.currentTimeMillis());
    response.setId(request.getId());
    List<String> mates = new ArrayList<>();
    mates.add("大表弟");
    mates.add("戴老板");
    mates.add("徐老板");
    response.setPlaymates(mates);
    return response;
  }
}
