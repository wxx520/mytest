package com.wxxtest.rpc.service.mahjong;

import java.io.Serializable;
import java.util.List;

public class MahjongInvitationResponse implements Serializable {

  private long id;

  private String msg;

  private String reply;

  private long time;

  private long useTime;

  private String playmates;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getPlaymates() {
    return playmates;
  }

  public void setPlaymates(String playmates) {
    this.playmates = playmates;
  }

  public long getUseTime() {
    return useTime;
  }

  public void setUseTime(long useTime) {
    this.useTime = useTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "MahjongInvitationResponse{" +
            "id=" + id +
            ", msg='" + msg + '\'' +
            ", reply='" + reply + '\'' +
            ", time=" + time +
            ", useTime=" + useTime +
            ", playmates=" + playmates +
            '}';
  }
}
