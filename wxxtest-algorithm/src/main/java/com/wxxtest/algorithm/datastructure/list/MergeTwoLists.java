package com.wxxtest.algorithm.datastructure.list;

public class MergeTwoLists {

  /**
   * 创建一个dummyHead = new ListNode(-1), c = dummyHead;
   * 从头遍历两个链表，c1 = l1, c2 = l2;
   * 当c1、c2均为空时遍历结束, 否则
   * 1、当c1 = null, c.next = c2, 遍历结束;
   * 2、当c2 = null, c.next = c1, 遍历结束;
   * 3、都不为空时
   * 3.1 c1.val<=c2.val, c.next = c1,c1 = c1.next;
   * 3.2 c1.val >c2.val, c.next = c2,c2 = c2.next;
   * 遍历结束后返回dummyHead.next为最终结果
   */
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode dummyHead = new ListNode(-1);
    ListNode c = dummyHead;
    ListNode c1 = list1;
    ListNode c2 = list2;
    while (c1 != null || c2 != null) {
      if (c1 == null) {
        c.next = c2;
        break;
      }
      if (c2 == null) {
        c.next = c1;
        break;
      }
      if (c1.val <= c2.val) {
        c.next = c1;
        c = c.next;
        c1 = c1.next;
      } else {
        c.next = c2;
        c = c.next;
        c2 = c2.next;
      }
    }
    return dummyHead.next;
  }

  /**
   * 根据函数定义递归
   */
  public ListNode mergeTwoListsByRecursive(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    } else if (list2 == null) {
      return list1;
    } else if (list1.val <= list2.val) {
      list1.next = mergeTwoListsByRecursive(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeTwoListsByRecursive(list1, list2.next);
      return list2;
    }
  }


  public static void main(String[] args) {
    MergeTwoLists m = new MergeTwoLists();
    System.out.println(m.mergeTwoLists(
            new ListNode(1, new ListNode(2, new ListNode(4))),
            new ListNode(1, new ListNode(3, new ListNode(4)))
    ));
    System.out.println(m.mergeTwoListsByRecursive(
            new ListNode(1, new ListNode(2, new ListNode(4))),
            new ListNode(1, new ListNode(3, new ListNode(4)))
    ));
  }
}
