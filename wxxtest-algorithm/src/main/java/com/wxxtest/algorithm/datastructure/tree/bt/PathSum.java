package com.wxxtest.algorithm.datastructure.tree.bt;

public class PathSum {

  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return root.val == targetSum;
    }
    int remain = targetSum - root.val;
    return hasPathSum(root.left, remain) || hasPathSum(root.right, remain);
  }

  public static void main(String[] args) {
    PathSum ps = new PathSum();
    TreeNode root = new TreeNode(5,
            new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
            new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
    System.out.println(ps.hasPathSum(root, 22));
  }
}
