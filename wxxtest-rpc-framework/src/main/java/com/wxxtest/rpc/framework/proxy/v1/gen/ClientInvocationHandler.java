package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.gen;

import com.wxxtest.rpc.registration.center.client.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.msg.MessageSender;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.pool.NettyClientManagerService;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

public class ClientInvocationHandler implements InvocationHandler {
  private final MessageSender messageSender;

  public ClientInvocationHandler(Map<String, Integer> hosts) {
    NettyClientManagerService pool = new NettyClientManagerService(hosts);
    messageSender = new MessageSender(pool);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //因为请求共用一条长连接，为请求生成一个唯一的id，服务端返回时会带上这个与请求形成映射关系
    long reqId = UUID.randomUUID().getLeastSignificantBits();

    //序列化请求对象
    ByteBuf reqBytes = ByteBufUtil.objectToByte(args[0], reqId, method.getName());

    //发送请求,并同步等待序列化好的响应结果
    return messageSender.sendAndGetAck(reqId, reqBytes, 3000, method.getReturnType());
  }
}
