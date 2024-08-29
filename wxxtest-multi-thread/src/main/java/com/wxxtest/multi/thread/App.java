package com.wxxtest.multi.thread;

import com.wxxtest.multi.thread.pc.BufferResourcesQueue;
import com.wxxtest.multi.thread.pc.BufferResourcesQueueWithLock;
import com.wxxtest.multi.thread.pc.Consumer;
import com.wxxtest.multi.thread.pc.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {
  public static void main(String[] args) {
    ThreadPoolExecutor ec1 = new ThreadPoolExecutor(5, 6, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(80));
    BufferResourcesQueueWithLock buffer = new BufferResourcesQueueWithLock(100);

    for (int i = 0; i < 20; i++) {
      ec1.execute(new Consumer(buffer));
    }

    ThreadPoolExecutor ep1 = new ThreadPoolExecutor(5, 6, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(80));

    for (int i = 0; i < 20; i++) {
      ep1.execute(new Producer(buffer));
    }

    BufferResourcesQueue queue = new BufferResourcesQueue(20);
    ThreadPoolExecutor ec2 = new ThreadPoolExecutor(5, 6, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(120));
    ThreadPoolExecutor ep2 = new ThreadPoolExecutor(5, 6, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(80));

    for (int i = 0; i < 20; i++) {
      ec2.execute(new Consumer(queue));
    }

    for (int i = 0; i < 20; i++) {
      ep2.execute(new Producer(queue));
    }
  }
}
