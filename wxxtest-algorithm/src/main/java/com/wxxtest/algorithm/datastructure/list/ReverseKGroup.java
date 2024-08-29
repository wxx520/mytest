package com.wxxtest.algorithm.datastructure.list;

public class ReverseKGroup {

  /**
   * 遍历链表，重复以下过程直至遍历结束
   * 1、记录翻转前的节点pre
   * 2、找到[1,k]需要待反转的链表；
   * 2.1 若不足k个则表示到达结尾，结束
   * 3、保存第k+1个节点kNext
   * 4、翻转链表，将翻转后的链表[l1,lk]链接
   * pre.next = l1;
   * lk.next = kNext;
   * pre = lk;
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    if (k == 1 || head == null || head.next == null) {
      return head;
    }

    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;
    ListNode pre = dummyHead, cur = head, l1, lk, temp;
    while (cur != null) {
      int count = k;
      //暂存翻转的第一个节点
      l1 = cur;
      while (count > 0 && cur != null) {
        cur = cur.next;
        count--;
      }
      //剩余链表长度不足k，表示已经结束，剩余部分不动，返回
      if (count > 0) {
        return dummyHead.next;
      }
      //开始翻转
      count = k;
      //暂存翻转前的头节点rk即翻转后的为节点
      lk = l1;
      //翻转后的新头节点
      ListNode newR1 = null;
      while (count > 0) {
        temp = l1.next;
        l1.next = newR1;
        newR1 = l1;
        l1 = temp;
        count--;
      }
      pre.next = newR1;
      lk.next = cur;
      pre = lk;
    }
    return dummyHead.next;
  }

  public static void main(String[] args) {
    ReverseKGroup k = new ReverseKGroup();
    System.out.println(k.reverseKGroup(
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
            2
    ));

    System.out.println(k.reverseKGroup(
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
            3
    ));
  }
}
