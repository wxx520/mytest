package com.wxxtest.algorithm.datastructure.list;

public class Node {
  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node cur = this;
    while (cur != null) {
      sb.append("[");
      sb.append(cur.val);
      if (cur.random != null) {
        sb.append(", " + cur.random.val);
      }
      sb.append("],");
      cur = cur.next;
    }
    return sb.toString();
  }
}
