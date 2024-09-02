package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请你仅使用1个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/">225. 用队列实现栈</a>
 */
public class MyStackII {
  private Queue<Integer> queue;

  public MyStackII() {
    queue = new LinkedList<>();
  }

  public void push(int x) {
    int n = queue.size();
    queue.offer(x);
    for (int i = 0; i < n; i++) {
      queue.offer(queue.poll());
    }

  }

  public int pop() {
    return queue.poll();
  }

  public int top() {
    return queue.peek();
  }

  public boolean empty() {
    return queue.isEmpty();
  }

  public static void main(String[] args) {
    MyStack ms = new MyStack();
    ms.push(1);
    ms.push(2);
    System.out.println(ms.top());
    System.out.println(ms.pop());
    System.out.println(ms.empty());
    System.out.println(ms.pop());
    System.out.println(ms.empty());
  }
}
