package com.wxxtest.rpc.registration.center.client.multi.thread.pc;

public class Producer implements Runnable {
  private final BufferResources br;

  public Producer(BufferResources br) {
    this.br = br;
  }

  @Override
  public void run() {
    for (int i = 0; i < 200; i++) {
      br.product("生产商品：" + Thread.currentThread().getName() + "_" + i);
    }
  }
}
