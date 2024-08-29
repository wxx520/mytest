package com.wxxtest.multi.thread.pc;

import java.util.LinkedList;
import java.util.Queue;

public class BufferResourcesQueue implements BufferResources {

  private int maxSize;

  private final Queue<String> bufferQueue = new LinkedList<>();

  public BufferResourcesQueue(int maxSize) {
    this.maxSize = maxSize;
  }

  public synchronized void consume() {

    try {
      while (bufferQueue.size() == 0) {
        System.out.println(Thread.currentThread().getName() + " 当前缓冲区为空，等待生产中...");
        wait();
      }
      String s = bufferQueue.poll();
      System.out.println("消费到产品: " + s);
      notifyAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public synchronized void product(String str) {
    try {
      while (bufferQueue.size() == maxSize) {
        System.out.println(Thread.currentThread().getName() + " 当前缓冲区已满，等待消费中...");
        wait();
      }
      bufferQueue.add(str);
      System.out.println("生产到产品 : " + str);
      notifyAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
