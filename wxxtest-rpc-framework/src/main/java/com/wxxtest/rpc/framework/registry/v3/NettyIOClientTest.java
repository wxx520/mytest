package com.wxxtest.rpc.framework.registry.v3;

import com.wxxtest.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.framework.codec.RegistryRequestToByteEncoder;
import com.wxxtest.rpc.framework.constant.RequestType;
import com.wxxtest.rpc.framework.registry.v3.msg.MessageSender;
import com.wxxtest.rpc.framework.registry.v3.pool.NettyClientManagerService;
import com.wxxtest.rpc.framework.registry.v3.pool.ServiceInfoManager;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.wxxtest.rpc.framework.registry.v1.LongConnectionService.REGISTRY_SERVICE_HOST;
import static com.wxxtest.rpc.framework.registry.v1.LongConnectionService.REGISTRY_SERVICE_PORT;

public class NettyIOClientTest {

  public static void main(String[] args) throws Exception {
    ServiceInfoManager<RegistryResponseInfo> sm = new ServiceInfoManager<>();
    Map<String, Integer> hosts = new HashMap<>();
    hosts.put(REGISTRY_SERVICE_HOST, REGISTRY_SERVICE_PORT);
    NettyClientManagerService<RegistryResponseInfo> pool = new NettyClientManagerService<>(hosts, sm);
    MessageSender<RegistryResponseInfo> sender = new MessageSender<>(pool);

    for (int i = 1; i < 30; i++) {
      RegistryRequestInfo request = gen(i);
      ByteBuf req = RegistryRequestToByteEncoder.encode(request);
      long ct = System.currentTimeMillis();
      RegistryResponseInfo resp = sender.sendAndGetAck(request.getRequestId(), req, 1000);
      System.out.printf("总耗时为：%s, reqId:%s %n", System.currentTimeMillis() - ct, resp.getRequestId());
    }
  }

  private static RegistryRequestInfo gen(int index) {
    RegistryRequestInfo req = new RegistryRequestInfo();
    req.setRequestId(index);
    if (index % 2 == 1) {
      req.setRequestType(RequestType.REGISTRY_SERVICE.getCode());
      req.setServiceName("wxx" + index % 5);
      req.setAddress("6.6.6." + index);
      req.setPort(index + 100);
    } else {
      req.setRequestType(RequestType.REGISTRY_FIND.getCode());
      req.setServiceName("wxx" + (index - 1) % 5);
    }
    return req;
  }
}
