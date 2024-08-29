package com.wxxtest.algorithm.datastructure.list;

public class SwapNodes {

  public ListNode swapNodes(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode kNode = head;
    for (int i = 1; i < k; i++) {
      kNode = kNode.next;
    }
    ListNode rKNode = head;
    ListNode cur = kNode;
    while (cur.next != null) {
      cur = cur.next;
      rKNode = rKNode.next;
    }
    int temp = kNode.val;
    kNode.val = rKNode.val;
    rKNode.val = temp;
    return head;
  }

  public static void main(String[] args) {
    SwapNodes s = new SwapNodes();
    System.out.println(s.swapNodes(
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
            2
    ));
  }
}
