package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.LinkedList;

public class PopulateNextRightPointerInEachNodeII {

  /**
   * 层序遍历
   */
  public Node connect(Node root) {
    if (root == null) {
      return null;
    }
    LinkedList<Node> queue = new LinkedList<>();
    queue.addLast(root);
    while (!queue.isEmpty()) {

      Node pre = null;
      int cSize = queue.size();
      while (cSize > 0) {
        Node cur = queue.pollFirst();

        if (cur.left != null) {
          queue.addLast(cur.left);
        }
        if (cur.right != null) {
          queue.addLast(cur.right);
        }

        if (pre != null) {
          pre.next = cur;
        }
        pre = cur;

        cSize--;

      }
    }
    return root;
  }


  /**
   * 还是层序遍历的思路，构造一个dummy节点，
   * dummy.next = curLevelLeftFirstNode
   * 当前层遍历连接完成后，继续下一层
   *
   * @param root 根节点
   * @return 连接完成的节点
   */
  public Node connectByIteration(Node root) {
    if (root == null) {
      return null;
    }
    Node head = root;
    while (head != null) {
      Node dummy = new Node(0);
      //当前正在处理的节点
      Node temp = dummy;

      //当前层进行遍历
      for (Node cur = head; cur != null; cur = cur.next) {
        if (cur.left != null) {
          temp.next = cur.left;
          temp = temp.next;
        }
        if (cur.right != null) {
          temp.next = cur.right;
          temp = temp.next;
        }
      }

      //下一层的头节点
      head = dummy.next;
    }
    return root;
  }
}
