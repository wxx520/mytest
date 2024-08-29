package com.wxxtest.algorithm.datastructure.tree.bt;

public class InvertBinaryTree {

  public static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode left = root.left;
    TreeNode right = root.right;
    root.left = invertTree(right);
    root.right = invertTree(left);
    return root;
  }

  public static void main(String[] args) {
    TreeNode t = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    System.out.println(invertTree(t));
  }
}
