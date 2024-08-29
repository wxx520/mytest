package com.wxxtest.algorithm.datastructure.queue;

public class MyCircularDeque {

  DListNode font;
  DListNode rear;

  private int capacity;
  private int size;

  public MyCircularDeque(int k) {
    capacity = k;
    size = 0;
  }

  public boolean insertFront(int value) {
    if (isFull()) {
      return false;
    }
    DListNode node = new DListNode(value);
    if (font == null) {
      font = rear = node;
    } else {
      font.prev = node;
      node.next = font;
      font = node;
    }
    size++;
    return true;
  }

  public boolean insertLast(int value) {
    if (isFull()) {
      return false;
    }
    DListNode node = new DListNode(value);
    if (font == null) {
      font = rear = node;
    } else {
      node.prev = rear;
      rear.next = node;
      rear = node;
    }
    size++;
    return true;
  }

  public boolean deleteFront() {
    if (isEmpty()) {
      return false;
    }
    if (size == 1) {
      font = rear = null;
      size--;
      return true;
    }
    font = font.next;
    size--;
    return true;
  }

  public boolean deleteLast() {
    if (isEmpty()) {
      return false;
    }
    if (size == 1) {
      font = rear = null;
      size--;
      return true;
    }
    DListNode prev = rear.prev;
    prev.next = null;
    rear.prev = null;
    rear = prev;
    size--;
    return true;
  }

  public int getFront() {
    if (isEmpty()) {
      return -1;
    }
    return font.val;
  }

  public int getRear() {
    if (isEmpty()) {
      return -1;
    }
    return rear.val;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return capacity == size;
  }
}

class DListNode {
  DListNode next;
  DListNode prev;
  int val;

  public DListNode(int val) {
    this.val = val;
  }
}
