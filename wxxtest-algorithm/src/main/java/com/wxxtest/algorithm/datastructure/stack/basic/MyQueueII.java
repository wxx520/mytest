package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用两个栈实现队列,可以将每种操作的时间复杂度平均到O(1)
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/">232. 用栈实现队列</a>
 */
public class MyQueueII {

  /**
   * 输入栈
   */
  private final Deque<Integer> inStack;


  /**
   * 对输入进行逆向处理后的，输出栈
   */
  private final Deque<Integer> outStack;

  public MyQueueII() {
    inStack = new ArrayDeque<>();
    outStack = new ArrayDeque<>();
  }

  public void push(int x) {
    inStack.push(x);
  }

  public int pop() {
    //说明还未进行逆序处理
    if (outStack.isEmpty()) {
      in2out();
    }
    //outStack是已经对输入进行逆向处理，符合先进先出的规则
    return outStack.pop();
  }

  /**
   * 和pop操作类型
   */
  public int peek() {
    if (outStack.isEmpty()) {
      in2out();
    }
    return outStack.peek();
  }

  public boolean empty() {
    return inStack.isEmpty() && outStack.isEmpty();
  }

  private void in2out() {
    while (!inStack.isEmpty()) {
      outStack.push(inStack.pop());
    }
  }

  public static void main(String[] args) {
    MyQueueII mq = new MyQueueII();
    mq.push(1);
    mq.push(2);
    System.out.println(mq.peek());
    System.out.println(mq.pop());
    System.out.println(mq.empty());
  }
}
