package com.wxxtest.rpc.registration.center.client.multi.thread.pc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferResourcesQueueWithLock implements BufferResources {
  private int maxSize = 20;

  private final Queue<String> bufferQueue = new LinkedList<>();

  private static final Lock lock = new ReentrantLock();

  private final Condition producerCon = lock.newCondition();

  private final Condition consumerCon = lock.newCondition();

  public BufferResourcesQueueWithLock(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  public void consume() {
    lock.lock();
    try {
      while (bufferQueue.size() == 0) {
        System.out.println(Thread.currentThread().getName() + " 当前缓冲区为空，等待生产中...");
        consumerCon.await();
      }
      String s = bufferQueue.poll();
      System.out.println("消费到产品: " + s);
      producerCon.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void product(String str) {
    lock.lock();
    try {
      while (bufferQueue.size() == maxSize) {
        System.out.println(Thread.currentThread().getName() + " 当前缓冲区已满，等待消费中...");
        producerCon.await();
      }
      bufferQueue.add(str);
      System.out.println("生产到产品 : " + str);
      consumerCon.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
