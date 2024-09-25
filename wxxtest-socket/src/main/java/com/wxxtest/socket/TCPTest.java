package com.wxxtest.rpc.registration.center.client.rpc.server.com.wxxtest.socket;

public class TCPTest {
  public static void main(String[] args) {
    MySocketRequest request = new MySocketRequest();
    request.setMsg("test");
    request.setTime(System.currentTimeMillis());
    request.setAge(23);
    request.setName("小鲜肉");
    System.out.println(request);
  }
}
