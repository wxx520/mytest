package com.wxxtest.registration.v1.handler;

import com.wxxtest.registration.ServiceManager;
import com.wxxtest.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.framework.codec.RegistryResponseToByteEncoder;
import com.wxxtest.rpc.framework.constant.RequestType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

public class RegistryRequestHandler extends ChannelInboundHandlerAdapter {
  private final ServiceManager serviceManager;

  public RegistryRequestHandler(ServiceManager serviceManager) {
    this.serviceManager = serviceManager;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    RegistryRequestInfo reqMsg = (RegistryRequestInfo) msg;

    RegistryResponseInfo resp = new RegistryResponseInfo();
    resp.setServiceName(reqMsg.getServiceName());
    resp.setRequestId(reqMsg.getRequestId());
    if (reqMsg.getRequestType() == RequestType.REGISTRY_FIND.getCode()) {
      Map<String, Integer> hosts = serviceManager.getService(reqMsg.getServiceName());
      resp.setAddresses(hosts);
    } else if (reqMsg.getRequestType() == RequestType.REGISTRY_SERVICE.getCode()) {
      serviceManager.register(reqMsg.getServiceName(), reqMsg.getAddress(), reqMsg.getPort());
    }

    ByteBuf respBuf = RegistryResponseToByteEncoder.encode(resp);
    ctx.writeAndFlush(respBuf).sync();
    System.out.printf("响应注册请求内容为，%s", respBuf);
    super.channelRead(ctx, msg);
  }

  /**
   * Netty异常捕捉流畅
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}

