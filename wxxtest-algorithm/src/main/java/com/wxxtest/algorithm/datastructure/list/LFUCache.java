package com.wxxtest.algorithm.datastructure.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class LFUCache {

  int minFreq;
  final int capacity;
  final Map<Integer, LFUNode> keyTable;
  final Map<Integer, DoublyLinkedList> freqTable;

  public LFUCache(int capacity) {
    this.minFreq = 0;
    this.capacity = capacity;
    keyTable = new HashMap<>();
    freqTable = new HashMap<>();
  }

  public int get(int key) {
    if (capacity <= 0) {
      return -1;
    }
    LFUNode node = keyTable.get(key);
    if (node == null) {
      return -1;
    }
    int val = node.val, freq = node.freq;
    freqTable.get(freq).remove(node);
    //如果当前频次链表为空的情况，我们需要在哈希表中删除，且更新minFreq
    if (freqTable.get(freq).size == 0) {
      freqTable.remove(freq);
      if (minFreq == freq) {
        minFreq++;
      }
    }

    //插入到freq+1中
    DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
    LFUNode newNode = new LFUNode(key, val, freq + 1);
    list.addFirst(newNode);
    freqTable.put(freq + 1, list);
    keyTable.put(key, newNode);
    return val;
  }

  public void put(int key, int value) {
    if (capacity <= 0) {
      return;
    }
    LFUNode node = keyTable.get(key);
    if (node == null) {
      if (keyTable.size() == capacity) {
        // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
        LFUNode minFreqNode = freqTable.get(minFreq).getTail();
        keyTable.remove(minFreqNode.key);
        freqTable.get(minFreq).remove(minFreqNode);
        if (freqTable.get(minFreq).size == 0) {
          freqTable.remove(minFreq);
        }
      }
      node = new LFUNode(key, value, 1);
      DoublyLinkedList list = freqTable.getOrDefault(1,new DoublyLinkedList());
      list.addFirst(node);
      keyTable.put(key,list.getHead());
      freqTable.put(1,list);


      minFreq = 1;

    } else {
      int freq = node.freq;
      //删除freq
      freqTable.get(freq).remove(node);
      if (freqTable.get(freq).size == 0) {
        freqTable.remove(key);
        if (minFreq == freq) {
          minFreq++;
        }
      }

      //更新freq+1
      LFUNode newNode = new LFUNode(key, value, freq + 1);
      DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
      list.addFirst(newNode);
      keyTable.put(key, newNode);
      freqTable.put(freq + 1, list);

    }
  }

  public static void main(String[] args) {
    LFUCache c1 = new LFUCache(2);
    c1.put(1, 1);
    c1.put(2, 2);
    System.out.println(c1.get(1));
    c1.put(3, 3);
    System.out.println(c1.get(2));
    System.out.println(c1.get(3));
    c1.put(4, 4);
    System.out.println(c1.get(1));
    System.out.println(c1.get(3));
    System.out.println(c1.get(4));
  }
}

class LFUNode {
  int key, val, freq;
  LFUNode prev, next;

  LFUNode() {
    this(-1, -1, 0);
  }

  LFUNode(int key, int val, int freq) {
    this.key = key;
    this.val = val;
    this.freq = freq;
  }
}

class DoublyLinkedList {
  LFUNode dummyHead, dummyTail;
  int size;

  DoublyLinkedList() {
    dummyHead = new LFUNode();
    dummyTail = new LFUNode();
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
    size = 0;
  }

  public void addFirst(LFUNode node) {
    LFUNode prevHead = dummyHead.next;
    node.prev = dummyHead;
    dummyHead.next = node;
    node.next = prevHead;
    prevHead.prev = node;
    size++;
  }

  public void remove(LFUNode node) {
    LFUNode prev = node.prev, next = node.next;
    prev.next = next;
    next.prev = prev;
    size--;
  }

  public LFUNode getHead() {
    return dummyHead.next;
  }

  public LFUNode getTail() {
    return dummyTail.prev;
  }
}
