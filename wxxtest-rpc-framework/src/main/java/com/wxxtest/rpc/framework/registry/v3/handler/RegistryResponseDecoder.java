package com.wxxtest.rpc.framework.registry.v3.handler;

import com.wxxtest.rpc.framework.bean.RegistryResponseInfo;
import com.wxxtest.rpc.framework.codec.ByteToRegistryResponseDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RegistryResponseDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
    //报文的首位是int类型，报文的长度
    //使用while，因为缓冲区中可能不止一份报文
    while (byteBuf.readableBytes() > 4) {
      int respLen = byteBuf.getInt(byteBuf.readerIndex());
      if (byteBuf.readableBytes() < respLen) {
        break;
      }
      RegistryResponseInfo respInfo = ByteToRegistryResponseDecoder.decode(byteBuf);
      System.out.printf("receive registry msg: %s %n", respInfo);
      list.add(respInfo);
    }
  }
}