package com.wxxtest.algorithm.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class FrontMiddleBackQueue {

  Deque<Integer> left;
  Deque<Integer> right;

  public FrontMiddleBackQueue() {
    left = new ArrayDeque<>();
    right = new ArrayDeque<>();
  }

  public void pushFront(int val) {
    left.offerFirst(val);
    if (left.size() == right.size() + 2) {
      right.offerFirst(left.pollLast());
    }
  }

  public void pushMiddle(int val) {
    if (left.size() == right.size() + 1) {
      right.offerFirst(left.pollLast());
    }
    left.offerLast(val);
  }

  public void pushBack(int val) {
    right.offerLast(val);
    if (left.size() + 1 == right.size()) {
      left.offerLast(right.pollFirst());
    }
  }

  public int popFront() {
    if (left.isEmpty()) {
      return -1;
    }
    int val = left.pollFirst();
    if (left.size() + 1 == right.size()) {
      left.offerLast(right.pollFirst());
    }
    return val;
  }

  public int popMiddle() {
    if (left.isEmpty()) {
      return -1;
    }
    int val = left.pollLast();
    if (left.size() + 1 == right.size()) {
      left.offerLast(right.pollFirst());
    }
    return val;
  }

  public int popBack() {
    if (left.isEmpty()) {
      return -1;
    }
    int val = 0;
    if (right.isEmpty()) {
      val = left.pollLast();
    } else {
      val = right.pollLast();
      if (left.size() == right.size() + 2) {
        right.offerFirst(left.pollLast());
      }
    }
    return val;
  }

  public static void main(String[] args) {
    FrontMiddleBackQueue q = new FrontMiddleBackQueue();
    q.pushFront(1);
    q.pushBack(2);
    q.pushMiddle(3);
    q.pushMiddle(4);
    System.out.println(q.popFront());
    System.out.println(q.popMiddle());
    System.out.println(q.popMiddle());
    System.out.println(q.popBack());
    System.out.println(q.popFront());
  }
}
