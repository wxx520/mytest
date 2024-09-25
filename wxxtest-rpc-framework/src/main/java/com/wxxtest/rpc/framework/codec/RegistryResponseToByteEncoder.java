package com.wxxtest.rpc.framework.codec;

import com.wxxtest.rpc.framework.bean.RegistryResponseInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RegistryResponseToByteEncoder {

  /**
   * 序列化格式：
   * 1、int：报文长度
   * 2、long：请求id
   * 3、int：服务名称长度
   * 4、bytes: 服务名称
   * 5、List[hostLengthInt, hostString, portInt]
   */
  public static ByteBuf encode(RegistryResponseInfo resp) {
    ByteBuf buf = Unpooled.buffer();
    //提前将报文长度四个字节占位
    buf.writeInt(1);

    //请求id
    buf.writeLong(resp.getRequestId());

    //服务名称的长度和服务名称
    byte[] serviceNameBytes = resp.getServiceName().getBytes(StandardCharsets.UTF_8);
    buf.writeInt(serviceNameBytes.length);
    buf.writeBytes(serviceNameBytes);

    //hosts
    Map<String, Integer> hosts = resp.getAddresses();
    byte[] hostBytes;
    if (hosts != null && hosts.size() > 0) {
      for (Map.Entry<String, Integer> entry : hosts.entrySet()) {
        hostBytes = entry.getKey().getBytes(StandardCharsets.UTF_8);
        buf.writeInt(hostBytes.length);
        buf.writeBytes(hostBytes);

        buf.writeInt(entry.getValue());
      }
    }
    //报文的长度
    buf.setInt(0, buf.readableBytes() - 4);
    return buf;
  }
}
