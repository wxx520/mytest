package com.wxxtest.multi.thread;

import com.wxxtest.multi.thread.Future;

public class WxxFuture<T> implements Future<T> {

  private T result;

  /**
   * TODO
   * InterruptedException的含义及其处理方式：
   * 关于泛型的知识
   */
  @Override
  public synchronized T get() throws InterruptedException {
    while (result == null) {
      wait();
    }
    return result;
  }

  @Override
  public synchronized void complete(T result) {
    if (this.result == null) {
      this.result = result;
      notifyAll();
    }
  }
}
