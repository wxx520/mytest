package com.wxxtest.rpc.consumer.v2;

import com.wxxtest.rpc.service.mahjong.MahjongInvitationRequest;
import com.wxxtest.rpc.service.mahjong.MahjongInvitationResponse;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientTestV2 {
  public static void main(String[] args) {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(90));
    MahjongInvitationServiceImplV2 service = new MahjongInvitationServiceImplV2();
    for (int i = 0; i < 1000; i++) {
      int finalI = i;
      //相较于javascript的closure(闭包:类似于跨线程的变量传递)的实现:
      //变量不再是在栈上，而是在堆上
      //java对于类似场景的实现有点像阉割版的闭包实现，基础类型还是值传递，
      // 只是不允许后续再改，编译器层面就会报错
      executor.submit(() -> {
        for (int j = 0; j < 10; j++) {
          MahjongInvitationResponse response = service.invite(gen(finalI * 10 + j));
          System.out.printf("收到客户端响应：%s\n", response);
        }
      });
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
