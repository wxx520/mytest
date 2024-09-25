package com.wxxtest.rpc.registration.center.client.rpc.framework.codec;


import com.wxxtest.rpc.registration.center.client.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.registration.center.client.rpc.framework.constant.RequestType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RegistryRequestToByteEncoder {

  /**
   * 序列化格式：
   * 1、int：报文长度
   * 2、int: 请求类型
   * 3、long：请求id
   * 4、int：服务名称长度
   * 5、bytes: 服务名称
   * 6、[hostLengthInt, hostString, portInt]
   */
  public static ByteBuf encode(RegistryRequestInfo request) {
    ByteBuf buf = Unpooled.buffer();
    buf.writeInt(1);

    buf.writeInt(request.getRequestType());

    buf.writeLong(request.getRequestId());

    byte[] serviceNameBytes = request.getServiceName().getBytes(StandardCharsets.UTF_8);
    buf.writeInt(serviceNameBytes.length);
    buf.writeBytes(serviceNameBytes);

    if (request.getRequestType() == RequestType.REGISTRY_SERVICE.getCode()) {
      byte[] hostBytes = request.getAddress().getBytes(StandardCharsets.UTF_8);
      buf.writeInt(hostBytes.length);
      buf.writeBytes(hostBytes);
      buf.writeInt(request.getPort());
    }

    buf.setInt(0, buf.readableBytes() - 4);
    return buf;
  }

  public static void main(String[] args) {
    int[] a1 = new int[]{1, 2, 3, 4};
    int[] a2 = new int[8];
    System.arraycopy(a1, 1, a2, 0, 3);
    System.out.println(Arrays.toString(a2));
  }
}
