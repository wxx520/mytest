package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/">232. 用栈实现队列</a>
 */
public class MyQueue {

  private final Deque<Integer> stack;

  public MyQueue() {
    stack = new ArrayDeque<>();
  }

  public void push(int x) {
    stack.offer(x);
    int n = stack.size();
    for (int i = 0; i < n; i++) {
      stack.offer(stack.poll());
    }
  }

  public int pop() {
    return stack.pop();
  }

  public int peek() {
    return stack.peek();
  }

  public boolean empty() {
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    MyQueue mq = new MyQueue();
    mq.push(1);
    mq.push(2);
    System.out.println(mq.peek());
    System.out.println(mq.pop());
    System.out.println(mq.empty());
  }
}
