package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.LinkedList;

public class CousinsInBT {

  /**
   * 层序遍历，在此过程中记录x/y的父节点，
   * 仅父节点不同且在同一层返回true，
   * 否则返回false：不在同一层或者父节点相同
   */
  public boolean isCousins(TreeNode root, int x, int y) {
    if (x == y || root.val == x || root.val == y) {
      return false;
    }
    int xP = 0;
    int yP = 0;
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addLast(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeNode node = queue.pollFirst();
        TreeNode nL = node.left;
        TreeNode nR = node.right;
        if (nL != null) {
          if (nL.val == x) {
            xP = node.val;
          }
          if (nL.val == y) {
            yP = node.val;
          }
          queue.addLast(nL);
        }
        if (nR != null) {
          if (nR.val == x) {
            xP = node.val;
          }
          if (nR.val == y) {
            yP = node.val;
          }
          queue.addLast(nR);
        }
        size--;
      }

      if (xP != 0 || yP != 0) {
        return xP != yP && xP * yP != 0;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    CousinsInBT c = new CousinsInBT();
    System.out.println(c.isCousins(
            new TreeNode(1,
                    new TreeNode(2, new TreeNode(4), null),
                    new TreeNode(3)),
            4, 3));
    System.out.println(c.isCousins(
            new TreeNode(1,
                    new TreeNode(2, null, new TreeNode(4)),
                    new TreeNode(3, null, new TreeNode(5))),
            5, 4
    ));
    System.out.println(c.isCousins(
            new TreeNode(1,
                    new TreeNode(2, null, new TreeNode(4)),
                    new TreeNode(3)),
            2, 3
    ));
  }
}
