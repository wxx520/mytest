package com.wxxtest.algorithm.datastructure.list;

public class RemoveNthFromEnd {

  /**
   * 快慢指针，slow比fast慢n步，当fast为null时，slow就是倒数第n个
   * 用此方法找到倒数第n+1个节点pre，
   * 则倒数第n个节点 kNode = pre.next;
   * pre.next = kNode.next;
   * kNode.next = null;
   * 删除成功
   *
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;
    //pre为倒数第n+1个节点
    ListNode pre = dummyHead, fast = head;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }
    while (fast != null) {
      pre = pre.next;
      fast = fast.next;
    }

    ListNode kNode = pre.next;
    pre.next = kNode.next;
    kNode.next = null;
    return dummyHead.next;
  }

  public static void main(String[] args) {
    RemoveNthFromEnd n = new RemoveNthFromEnd();
    System.out.println(n.removeNthFromEnd(
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
            2
    ));

    System.out.println(n.removeNthFromEnd(new ListNode(1), 1));
    System.out.println(n.removeNthFromEnd(new ListNode(1, new ListNode(2)), 1));
  }
}
