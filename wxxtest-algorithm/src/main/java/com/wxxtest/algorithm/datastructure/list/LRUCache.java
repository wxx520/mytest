package com.wxxtest.algorithm.datastructure.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class LRUCache {

  static class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;

    public DLinkedNode() {
    }

    public DLinkedNode(int _key, int _value) {
      key = _key;
      value = _value;
    }
  }

  private final Map<Integer, DLinkedNode> cache;

  private final DLinkedNode head, tail;

  private final int capacity;
  private int size;

  public LRUCache(int capacity) {
    cache = new HashMap<>();
    this.capacity = capacity;

    head = new DLinkedNode();
    tail = new DLinkedNode();

    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if (capacity <= 0) {
      return -1;
    }
    DLinkedNode node = cache.get(key);
    if (node == null) {
      return -1;
    }
    moveToHead(node);

    return node.value;
  }

  public void put(int key, int value) {
    if (capacity <= 0) {
      return;
    }
    DLinkedNode node = cache.get(key);
    if (node == null) {
      node = new DLinkedNode(key, value);
      addToHead(node);
      cache.put(key, node);
      size++;

      if (size > capacity) {
        DLinkedNode tailNode = removeTail();
        cache.remove(tailNode.key);
        size--;
      }

    } else {
      node.value = value;
      moveToHead(node);
    }

  }

  private void moveToHead(DLinkedNode node) {
    removeNode(node);

    addToHead(node);

  }

  private void addToHead(DLinkedNode node) {
    //将当前节点添加到队头
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(DLinkedNode node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private DLinkedNode removeTail() {
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  public static void main(String[] args) {
    LRUCache c1 = new LRUCache(2);
    c1.put(1, 1);
    c1.put(2, 2);
    System.out.println(c1.get(1));
    c1.put(3, 3);
    System.out.println(c1.get(2));
    c1.put(4, 4);
    System.out.println(c1.get(1));
    System.out.println(c1.get(3));
    System.out.println(c1.get(4));
  }
}
