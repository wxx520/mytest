package com.wxxtest.rpc.registration.center.client.rpc.service.mahjong;

import java.io.Serializable;

public class MahjongInvitationRequest implements Serializable {

  private long id;

  private int age;
  private String name;
  private String msg;

  private long time;

  public MahjongInvitationRequest() {
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "MahjongInvitationRequest{" +
            "id=" + id +
            ", age=" + age +
            ", name='" + name + '\'' +
            ", msg='" + msg + '\'' +
            ", time=" + time +
            '}';
  }
}
