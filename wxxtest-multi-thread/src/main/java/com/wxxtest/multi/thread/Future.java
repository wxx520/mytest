package com.wxxtest.multi.thread;

/**
 * 线程间传参
 * @param <V> 需要传递的参数类型
 */
public interface Future<V> {

  /**
   * 检查计算结果是否完成，没有完成挂起，完成则返回结果
   *
   * @return 返回计算的结果
   */
  V get() throws InterruptedException;

  /**
   * 设置计算结果，并唤醒等待在改对象上的线程
   *
   */
  void complete(V result);

}
