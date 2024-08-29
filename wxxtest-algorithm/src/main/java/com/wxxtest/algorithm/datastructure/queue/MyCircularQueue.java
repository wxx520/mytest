package com.wxxtest.algorithm.datastructure.queue;


import com.wxxtest.algorithm.datastructure.tree.bt.ListNode;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，
 * 其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。
 * 它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
 * 在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 */
public class MyCircularQueue {

  //保存链表头部
  private ListNode head;
  //保存链表尾部
  private ListNode tail;
  private int capacity;
  private int size;

  /**
   * @param k 缓冲区大小
   */
  public MyCircularQueue(int k) {
    size = 0;
    capacity = k;
  }

  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    }
    ListNode node = new ListNode(value);
    if (head == null) {
      tail = node;
      head = node;
    } else {
      tail.next = node;
      tail = node;
    }
    size++;
    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }
    head = head.next;
    size--;
    return true;
  }

  public int Front() {
    if (isEmpty()) {
      return -1;
    }
    return head.val;
  }

  public int Rear() {
    if (isEmpty()) {
      return -1;
    }
    return tail.val;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == capacity;
  }

  public static void main(String[] args) {
    t2();
  }

  public static void t1() {
    MyCircularQueue cq = new MyCircularQueue(3);
    System.out.println(cq.enQueue(1));
    System.out.println(cq.enQueue(2));
    System.out.println(cq.enQueue(3));
    System.out.println(cq.enQueue(4));
    System.out.println(cq.Rear());
    System.out.println(cq.isFull());
    System.out.println(cq.deQueue());
    System.out.println(cq.enQueue(4));
    System.out.println(cq.Rear());
  }

  public static void t2() {
    MyCircularQueue cq = new MyCircularQueue(2);
    System.out.println(cq.enQueue(1));
    System.out.println(cq.enQueue(2));
    System.out.println(cq.deQueue());
    System.out.println(cq.enQueue(3));
    System.out.println(cq.deQueue());
    System.out.println(cq.enQueue(3));
    System.out.println(cq.deQueue());
    System.out.println(cq.enQueue(3));
    System.out.println(cq.deQueue());
    System.out.println(cq.Front());
  }

}
