package com.wxxtest.algorithm.datastructure.list;

public class ReverseBetween {

  /**
   * 若left = right,则表示不动；否则
   * 先找到pre,其中pre.next = leftNode
   * <p>
   * 然后开始反转left与right之间的节点
   * <p>
   * 并保存last，nodeRight.next = last
   * <p>
   * 翻转完成后，
   * pre.next = nodeRight；
   * nodeLeft.next = last;
   * <p>
   * 翻转完成
   */
  public ListNode reverseBetween(ListNode head, int left, int right) {
    if (left == right) {
      return head;
    }
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;
    ListNode pre = dummyHead;
    for (int i = 0; i < left - 1; i++) {
      pre = pre.next;
    }
    ListNode cur = pre.next;
    //开始翻转,next为翻转后的头节点，cur为right的后半段的头节点
    ListNode next = null;
    for (int i = 0; i < right - left; i++) {
      next = cur.next;
      cur.next = next.next;
      next.next = pre.next;
      pre.next = next;
    }

    return dummyHead.next;
  }

  public static void main(String[] args) {
    ReverseBetween rb = new ReverseBetween();
    System.out.println(rb.reverseBetween(
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
            2, 4
    ));
  }
}
