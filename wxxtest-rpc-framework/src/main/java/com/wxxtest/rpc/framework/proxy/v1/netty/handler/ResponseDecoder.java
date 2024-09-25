package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.handler;

import com.wxxtest.rpc.registration.center.client.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.msg.MsgWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Map;

public class ResponseDecoder extends ByteToMessageDecoder {

  /**
   * 和ResponseHandler，Channel每个节点都是单线程执行，不存在线程安全问题
   */
  private final Map<Long, MsgWrapper> responseMap;

  public ResponseDecoder(Map<Long, MsgWrapper> requests) {
    this.responseMap = requests;
  }

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
    //报文的首位是int类型，报文的长度
    //使用while，因为缓冲区中可能不止一份报文
    while (byteBuf.readableBytes() > 4) {
      int respLen = byteBuf.getInt(byteBuf.readerIndex());
      if (byteBuf.readableBytes() < respLen) {
        break;
      }
      int len = byteBuf.readInt();
      long reqId = byteBuf.readLong();
      MsgWrapper responseMsg = responseMap.remove(reqId);
      if (responseMsg == null) {
        //说明段请求无效，忽略
        byteBuf.readerIndex(byteBuf.readerIndex() + len - 8);
        continue;
      }
      try {
        Object resp = ByteBufUtil.byteToObject(byteBuf, responseMsg.getReturnType());
        responseMsg.getAckFuture().complete(resp);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}