package com.wxxtest.algorithm.datastructure.tree.bst;

import com.wxxtest.algorithm.datastructure.tree.bt.ListNode;
import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

public class ConvertSortedListToBST {

  /**
   * 中序遍历构建，找到中间的节点，中间左边的构建左子树，右边的构建右子树，递归这个过程
   */
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return new TreeNode(head.val);
    }
    //pre为slow的前一个节点，为了找到左子树
    ListNode pre = null;
    //中间元素
    ListNode slow = head;
    //最后一个节点
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      pre = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    TreeNode root = new TreeNode(slow.val);

    //构造左子树
    if(pre!=null){
      pre.next = null;
      root.left = sortedListToBST(head);
    }

    //构造右子树
    root.right = sortedListToBST(slow.next);

    return root;
  }

  public static void main(String[] args) {
    ConvertSortedListToBST s = new ConvertSortedListToBST();
    ListNode head = new ListNode(-10,
            new ListNode(-3,
                    new ListNode(0,
                            new ListNode(5,
                                    new ListNode(9)
                            )))
    );
    System.out.println(s.sortedListToBST(head));
  }
}
