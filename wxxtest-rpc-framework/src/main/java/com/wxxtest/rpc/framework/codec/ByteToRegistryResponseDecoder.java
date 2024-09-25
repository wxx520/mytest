package com.wxxtest.rpc.registration.center.client.rpc.framework.codec;

import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryResponseInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ByteToRegistryResponseDecoder {

  public static RegistryResponseInfo decode(ByteBuf byteBuf) {
    int respLen = byteBuf.readInt();
    System.out.printf("注册Response报文长度 %s\n", respLen);
    return decodeFromBody(byteBuf);
  }

  public static RegistryResponseInfo decodeFromBody(ByteBuf byteBuf) {
    RegistryResponseInfo resp = new RegistryResponseInfo();
    resp.setRequestId(byteBuf.readLong());

    int serviceNameBytesLen = byteBuf.readInt();
    CharSequence serviceName = byteBuf.readCharSequence(serviceNameBytesLen, StandardCharsets.UTF_8);
    resp.setServiceName(serviceName.toString());
    if (byteBuf.readableBytes() > 0) {
      Map<String, Integer> hosts = new HashMap<>();
      while (byteBuf.readableBytes() > 0) {
        int hostLen = byteBuf.readInt();
        String host = byteBuf.readCharSequence(hostLen, StandardCharsets.UTF_8).toString();
        int port = byteBuf.readInt();
        hosts.put(host, port);
      }
      resp.setAddresses(hosts);
    }

    return resp;
  }

  public static RegistryResponseInfo decode(byte[] bytes) {
    ByteBuf buf = Unpooled.buffer();
    buf.writeBytes(bytes);
    return decode(buf);
  }
}
