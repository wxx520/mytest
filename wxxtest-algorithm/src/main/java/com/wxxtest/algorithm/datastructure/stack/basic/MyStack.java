package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/">225. 用队列实现栈</a>
 */
public class MyStack {
  private Queue<Integer> q1;

  private Queue<Integer> q2;

  public MyStack() {
    q1 = new LinkedList<>();
    q2 = new LinkedList<>();
  }

  public void push(int x) {
    //此时q2为空,此时x为队首元素
    q2.offer(x);
    while (!q1.isEmpty()) {
      q2.offer(q1.poll());
    }
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
  }

  public int pop() {
    return q1.poll();
  }

  public int top() {
    return q1.peek();
  }

  public boolean empty() {
    return q1.isEmpty();
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
