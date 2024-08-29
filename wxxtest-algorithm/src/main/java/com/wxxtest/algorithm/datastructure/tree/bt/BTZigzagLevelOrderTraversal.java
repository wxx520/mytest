package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的Z形锯齿状遍历
 */
public class BTZigzagLevelOrderTraversal {

  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean reverse = true;
    while (!queue.isEmpty()) {
      int cSize = queue.size();
      LinkedList<Integer> cLevel = new LinkedList<>();
      if (reverse) {
        while (cSize > 0) {
          TreeNode node = queue.pollFirst();
          cLevel.addFirst(node.val);
          if (node.left != null) {
            queue.addLast(node.left);
          }
          if (node.right != null) {
            queue.addLast(node.right);
          }
          cSize--;
        }
      } else {
        while (cSize > 0) {
          TreeNode node = queue.pollFirst();
          cLevel.addLast(node.val);
          if (node.left != null) {
            queue.addLast(node.left);
          }
          if (node.right != null) {
            queue.addLast(node.right);
          }
          cSize--;
        }
      }
      reverse = !reverse;
      if (cLevel.size() > 0) {
        ans.add(cLevel);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20));
    root.left.left = new TreeNode(14);
    root.left.right = new TreeNode(8);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(zigzagLevelOrder(root));
  }
}
