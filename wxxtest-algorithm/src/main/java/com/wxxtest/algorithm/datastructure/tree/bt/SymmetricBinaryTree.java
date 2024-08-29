package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.LinkedList;

public class SymmetricBinaryTree {

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmetric(root.left, root.right);
  }

  /**
   * 递归的方式，也是深度优先遍历
   */
  private boolean isSymmetric(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) {
      return true;
    }
    if (t1 == null || t2 == null) {
      return false;
    }
    return t1.val == t2.val && isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
  }

  /**
   * 使用迭代的方式实现对称树,广度优先遍历
   */
  public boolean isSymmetricByBFS(TreeNode root) {
    if (root == null) {
      return true;
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addFirst(root.left);
    queue.addLast(root.right);
    while (!queue.isEmpty()) {
      TreeNode leftNode = queue.pollFirst();
      TreeNode rightNode = queue.pollLast();
      if (leftNode == null && rightNode == null) {
        continue;
      }
      if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
        return false;
      }
      queue.addFirst(leftNode.left);
      queue.addFirst(leftNode.right);
      queue.addLast(rightNode.right);
      queue.addLast(rightNode.left);
    }
    return true;
  }

  public static void main(String[] args) {
    SymmetricBinaryTree sTree = new SymmetricBinaryTree();
    TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
    System.out.println(sTree.isSymmetric(root));
    System.out.println(sTree.isSymmetricByBFS(root));
  }
}
