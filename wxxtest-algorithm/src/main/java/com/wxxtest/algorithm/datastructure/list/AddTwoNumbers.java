package com.wxxtest.algorithm.datastructure.list;

public class AddTwoNumbers {

  /**
   * 保存两数之和以及进位
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode ans = new ListNode(-1);
    int carryBit = 0;
    int sum = 0;
    ListNode c1 = l1;
    ListNode c2 = l2;
    ListNode c = ans;
    while (c1 != null || c2 != null) {
      if (c1 != null) {
        sum += c1.val;
        c1 = c1.next;
      }

      if (c2 != null) {
        sum += c2.val;
        c2 = c2.next;
      }

      sum += carryBit;

      c.next = new ListNode(sum % 10);
      carryBit = sum / 10;
      sum = 0;
      c = c.next;
    }
    if (carryBit == 1) {
      c.next = new ListNode(1);
    }
    return ans.next;
  }

  public static void main(String[] args) {
    AddTwoNumbers add = new AddTwoNumbers();
    System.out.println(add.addTwoNumbers(
            new ListNode(2, new ListNode(4, new ListNode(3))),
            new ListNode(5, new ListNode(6, new ListNode(4)))
    ));

    System.out.println(add.addTwoNumbers(new ListNode(0), new ListNode(0)));
    System.out.println(add.addTwoNumbers(
            new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))))),
            new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))
    ));
  }
}
