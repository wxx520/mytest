package com.wxxtest.rpc.registration.center.client.rpc.server.com.wxxtest.socket;

import java.io.Serializable;

public class MySocketRequest implements Serializable {

  private int age;
  private String name;
  private String msg;

  private long time;

  public MySocketRequest(){}

  public MySocketRequest(int age, String name, String msg) {
    this.age = age;
    this.name = name;
    this.msg = msg;
    this.time = System.currentTimeMillis();
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

  @Override
  public String toString() {
    return "MySocketRequest{" +
            "age=" + age +
            ", name='" + name + '\'' +
            ", msg='" + msg + '\'' +
            ", time=" + time +
            '}';
  }
}
