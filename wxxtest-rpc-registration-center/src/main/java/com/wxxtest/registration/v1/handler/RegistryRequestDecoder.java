package com.wxxtest.registration.v1.handler;

import com.wxxtest.rpc.framework.bean.RegistryRequestInfo;
import com.wxxtest.rpc.framework.codec.ByteToRegistryRequestDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RegistryRequestDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
    int originIndex = byteBuf.readerIndex();
    //报文的首位是int类型，报文的长度
    //使用while，因为缓冲区中可能不止一份报文
    while (byteBuf.readableBytes()>4){
      int reqLen = byteBuf.readInt();
      if(byteBuf.readableBytes()<reqLen){
        byteBuf.readerIndex(originIndex);
        break;
      }
      RegistryRequestInfo requestInfo = ByteToRegistryRequestDecoder.decode(byteBuf);
      System.out.printf("receive registry msg: %s", requestInfo);
      list.add(requestInfo);
    }
  }
}