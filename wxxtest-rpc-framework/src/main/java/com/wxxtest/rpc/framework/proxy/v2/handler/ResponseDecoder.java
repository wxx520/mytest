package com.wxxtest.rpc.framework.proxy.v2.handler;

import com.wxxtest.rpc.framework.codec.ByteBufUtil;
import com.wxxtest.rpc.framework.proxy.v2.msg.MsgWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Map;

public class ResponseDecoder extends ByteToMessageDecoder {

  /**
   * 和ResponseHandler，Channel每个节点都是单线程执行，不存在线程安全问题
   */
  private final Map<Long, MsgWrapper> requestMsgMap;

  public ResponseDecoder(Map<Long, MsgWrapper> requests) {
    requestMsgMap = requests;
  }

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
    //报文的首位是int类型，报文的长度
    //使用while，因为缓冲区中可能不止一份报文
    while (completeRequestReceived(byteBuf)) {
      MsgWrapper reqMsg = getRequestMsg(byteBuf);
      if (reqMsg == null) continue;
      try {
        Object resp = ByteBufUtil.byteToObject(byteBuf, reqMsg.getReturnType());
        reqMsg.getAckFuture().complete(resp);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private MsgWrapper getRequestMsg(ByteBuf byteBuf) {
    int readIndex = byteBuf.readerIndex();
    int reqLen = byteBuf.readInt();
    long reqId = byteBuf.readLong();
    System.out.printf("netty client解析到response长度为:len=%s, reqId=%s%n", reqLen, reqId);

    MsgWrapper reqMsg = requestMsgMap.remove(reqId);
    if (reqMsg == null) {
      //说明这段请求无效，做简单跳过，即忽略本次报文
      byteBuf.readerIndex(readIndex + reqLen);
      System.out.printf("客户端找不到请求对象，reqId=%s%n", reqId);
      return null;
    }
    return reqMsg;
  }

  private boolean completeRequestReceived(ByteBuf byteBuf) {
    if (byteBuf.readableBytes() <= 4) {
      return false;
    }
    int requestLen = byteBuf.getInt(byteBuf.readerIndex());
    return byteBuf.readableBytes() >= requestLen;
  }
}