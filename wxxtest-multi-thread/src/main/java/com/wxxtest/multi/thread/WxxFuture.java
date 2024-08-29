package com.wxxtest.multi.thread;

public class WxxFuture implements Future<Object> {

  private Object result;

  /**
   * TODO
   * InterruptedException的含义及其处理方式：
   * 关于泛型的知识
   */
  @Override
  public synchronized Object get() {
    while (result == null) {
      try {
        wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return result;
  }

  @Override
  public synchronized void complete(Object result) {
    if (this.result == null) {
      this.result = result;
      notifyAll();
    }
  }
}
