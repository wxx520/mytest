package com.wxxtest.rpc.consumer.v4;

import com.wxxtest.rpc.framework.proxy.v1.gen.RpcProxyCreator;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationResponse;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationService;

import java.util.HashMap;
import java.util.Map;

public class ClientV4Test {
  public static void main(String[] args) throws ClassNotFoundException {
    //这里不去注册中心找服务地址，直接从map中找
    Map<String, Map<String, Integer>> map = new HashMap<>();
    Map<String, Integer> mjHosts = new HashMap<>();
    mjHosts.put("0.0.0.0", 2988);
    String mjServiceName = MahjongInvitationService.class.getName();
    map.put(mjServiceName, mjHosts);

    RpcProxyCreator creator = new RpcProxyCreator();


    MahjongInvitationRequest req = new MahjongInvitationRequest();
    req.setId(234);
    req.setTime(System.currentTimeMillis());
    req.setMsg("i want to play");
    req.setName("GUANGDONG");
    req.setAge(18);

    Class<MahjongInvitationService> clazz = (Class<MahjongInvitationService>) Class.forName(mjServiceName);
    MahjongInvitationService proxy = creator.create(clazz, map.get(mjServiceName));
    MahjongInvitationResponse resp = proxy.invite(req);
    System.out.println("完成一次代理测试：" + resp);
  }
}
