package com.wxxtest.algorithm.datastructure.list;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    Set<ListNode> set = new HashSet<>();
    ListNode cur = head;
    while (cur != null) {
      if (set.contains(cur)) {
        return true;
      }
      set.add(cur);
      cur = cur.next;
    }
    return false;
  }

  public boolean hasCycleByFSP(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head.next.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
  }

  public boolean hasCycleByFloyd(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
  }

  /**
   * 耍流氓
   * -105 <= Node.val <= 105
   */
  public boolean hasCycleBySLM(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode cur = head;
    int resetVal = Integer.MAX_VALUE;
    while (cur != null) {
      if (cur.val == resetVal) {
        return true;
      }
      cur.val = resetVal;
      cur = cur.next;
    }
    return false;
  }

  public static void main(String[] args) {
    LinkedListCycle c = new LinkedListCycle();
    ListNode head = new ListNode(3);
    head.next = new ListNode(2, new ListNode(0, new ListNode(-4)));
    System.out.println(c.hasCycle(head));
    System.out.println(c.hasCycleByFSP(head));
    System.out.println(c.hasCycleByFloyd(head));
    System.out.println(c.hasCycleBySLM(head));
  }
}
