package com.wxxtest.multi.thread.pc;

public class Consumer implements Runnable{

  private final BufferResources br;

  public Consumer(BufferResources br) {
    this.br = br;
  }
  @Override
  public void run() {
    for (int i = 0; i < 200; i++) {
      br.consume();
    }
  }
}
