package com.wxxtest.rpc.registration.center.client.rpc.framework.codec;

import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.constant.RequestType;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class ByteToRegistryRequestDecoder {


  public static RegistryRequestInfo decode(ByteBuf byteBuf) {
    int reqType = byteBuf.readInt();
    if (RequestType.getType(reqType) == null) {
      throw new RuntimeException("非法请求类型");
    }

    RegistryRequestInfo requestInfo = new RegistryRequestInfo();
    requestInfo.setRequestType(reqType);

    requestInfo.setRequestId(byteBuf.readLong());

    int serviceNameLen = byteBuf.readInt();
    requestInfo.setServiceName(byteBuf.readCharSequence(serviceNameLen,StandardCharsets.UTF_8).toString());

    if (reqType == RequestType.REGISTRY_SERVICE.getCode()) {
      int hostLen = byteBuf.readInt();
      requestInfo.setAddress(byteBuf.readCharSequence(hostLen, StandardCharsets.UTF_8).toString());
      requestInfo.setPort(byteBuf.readInt());
    }
    return requestInfo;
  }
}
