package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.gen;

import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.msg.MsgWrapper;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v2.pool.NettyClientManagerService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


/**
 * 客户端代理类处理逻辑
 */
public class ClientInvocationHandler implements InvocationHandler {
  private final NettyClientManagerService nettyClients;

  private final String serviceName;

  public ClientInvocationHandler(String serviceName) {
    nettyClients = new NettyClientManagerService(serviceName);
    nettyClients.intServerManger();
    this.serviceName = serviceName;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //因为请求共用一条长连接，为请求生成一个唯一的id，服务端返回时会带上这个与请求形成映射关系
    long uniReqId = UUID.randomUUID().getLeastSignificantBits();

    MsgWrapper requestMsgWrapper = getMsgWrapper(method, args, uniReqId);

    //序列化请求对象，同步等待服务端返回并反序列化返回
    return nettyClients.sendAndGetAck(requestMsgWrapper, 3000);
  }

  private MsgWrapper getMsgWrapper(Method method, Object[] args, long uniReqId) {
    MsgWrapper msgWrapper = new MsgWrapper();
    msgWrapper.setRequestId(uniReqId);
    msgWrapper.setParams(args);
    msgWrapper.setServiceName(serviceName + "." + method.getName());

    CompletableFuture<Object> respAck = new CompletableFuture<>();
    msgWrapper.setAckFuture(respAck);
    msgWrapper.setReturnType(method.getReturnType());
    return msgWrapper;
  }
}
