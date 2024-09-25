package com.wxxtest.rpc.registration.center.client.rpc.consumer.v1;

import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.registration.center.client.rpc.service.mahjong.MahjongInvitationResponse;

public class ClientTest {
  public static void main(String[] args) {

    MahjongInvitationServiceImplV1 service = new MahjongInvitationServiceImplV1();
    for (int i = 0; i < 50; i++) {
      int finalI = i;
      //相较于javascript的closure(闭包:类似于跨线程的变量传递)的实现:
      //变量不再是在栈上，而是在堆上
      //java对于类似场景的实现有点像阉割版的闭包实现，基础类型还是值传递，
      // 只是不允许后续再改，编译器层面就会报错
      new Thread(()->{
        for (int j = 0; j < 1000; j++) {
          MahjongInvitationResponse response = service.invite(gen(finalI*1000+j));
          System.out.printf("收到客户端响应：%s\n", response);
        }
      }).start();
    }
    System.out.println("-----------------");
    System.out.println("请求结束");
  }

  public static MahjongInvitationRequest gen(int age) {
    MahjongInvitationRequest request = new MahjongInvitationRequest();
    request.setMsg("约广东麻将吗");
    request.setName("雀后星星" + age);
    request.setTime(System.currentTimeMillis());
    request.setAge(age);
    request.setId(age);
    return request;
  }
}
