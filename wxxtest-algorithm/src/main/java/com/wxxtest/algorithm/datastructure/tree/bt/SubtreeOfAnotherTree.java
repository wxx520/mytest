package com.wxxtest.algorithm.datastructure.tree.bt;

public class SubtreeOfAnotherTree {

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null) {
      return true;
    }
    if (root == null || subRoot == null) {
      return false;
    }
    return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  public boolean isSameTree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null) {
      return true;
    }
    if (root == null || subRoot == null) {
      return false;
    }
    return root.val == subRoot.val && isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
  }

  public static void main(String[] args) {
    SubtreeOfAnotherTree s = new SubtreeOfAnotherTree();
    TreeNode root = new TreeNode(3,
            new TreeNode(4,
                    new TreeNode(1),
                    new TreeNode(2,
                            new TreeNode(0),null)),
            new TreeNode(5));
    TreeNode sub = new TreeNode(4,
            new TreeNode(1),
            new TreeNode(2));
    System.out.println(s.isSubtree(root, sub));
  }
}
