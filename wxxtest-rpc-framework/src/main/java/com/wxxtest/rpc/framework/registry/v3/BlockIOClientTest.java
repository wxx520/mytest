package com.wxxtest.rpc.framework.registry.v3;

import com.wxxtest.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.framework.codec.ByteToRegistryResponseDecoder;
import com.wxxtest.rpc.framework.codec.RegistryRequestToByteEncoder;
import com.wxxtest.rpc.framework.constant.RequestType;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static com.wxxtest.rpc.framework.registry.v1.LongConnectionService.REGISTRY_SERVICE_HOST;
import static com.wxxtest.rpc.framework.registry.v1.LongConnectionService.REGISTRY_SERVICE_PORT;

public class BlockIOClientTest {
  public static void main(String[] args) {
    try (Socket registerConn = new Socket(REGISTRY_SERVICE_HOST,
            REGISTRY_SERVICE_PORT)) {
      RegistryRequestInfo req = new RegistryRequestInfo();
      req.setRequestId(1);
      req.setRequestType(RequestType.REGISTRY_SERVICE.getCode());
      req.setServiceName("myFirstNettyServiceName");
      req.setAddress("2.2.2.2");
      req.setPort(2222);

      ByteBuf reqBytes = RegistryRequestToByteEncoder.encode(req);
      OutputStream os = registerConn.getOutputStream();
      os.write(reqBytes.array(),0,reqBytes.readableBytes());
      os.flush();

      InputStream is = registerConn.getInputStream();
      byte[] respLenBytes = new byte[4];
      is.read(respLenBytes);
      int respLen = respLenBytes[3];
      byte[] resp = new byte[respLen];
      is.read(resp);
      byte[] ans = new byte[respLen+4];
      System.arraycopy(respLenBytes,0,ans,0,4);
      System.arraycopy(resp,0,ans,4,respLen);
      System.out.println(ByteToRegistryResponseDecoder.decode(ans));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
