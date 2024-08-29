package com.wxxtest.algorithm.datastructure.list;

public class ListNode {
  public int val;
  public ListNode next;

 public ListNode(int x) {
    val = x;
    next = null;
  }

  public ListNode(int x, ListNode next) {
    val = x;
    this.next = next;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    ListNode cur = this;
    while (cur != null) {
      sb.append(cur.val);
      cur = cur.next;
    }
    return sb.toString();
  }
}
