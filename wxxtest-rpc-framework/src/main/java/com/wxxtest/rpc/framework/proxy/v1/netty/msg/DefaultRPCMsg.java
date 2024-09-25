package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.msg;

import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.msg.RPCIdRequestTag;

public class DefaultRPCMsg<T> implements RPCIdRequestTag {
  private long requestId;

  private T msg;

  private DefaultRPCMsg(long reqId, T msg) {
    this.requestId = reqId;
    this.msg = msg;
  }

  @Override
  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public T getMsg() {
    return msg;
  }

  public void setMsg(T msg) {
    this.msg = msg;
  }
}
