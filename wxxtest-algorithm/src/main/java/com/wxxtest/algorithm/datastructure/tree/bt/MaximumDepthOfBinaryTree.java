package com.wxxtest.algorithm.datastructure.tree.bt;

public class MaximumDepthOfBinaryTree {

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  public static void main(String[] args) {
    MaximumDepthOfBinaryTree tree = new MaximumDepthOfBinaryTree();

    TreeNode tn = new TreeNode(3, new TreeNode(9), new TreeNode(20));
    tn.right.left = new TreeNode();
    tn.right.left.val = 15;
    tn.right.right = new TreeNode(7);
    System.out.println(tree.maxDepth(tn));
  }
}
