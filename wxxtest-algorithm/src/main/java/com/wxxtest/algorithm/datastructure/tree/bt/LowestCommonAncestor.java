package com.wxxtest.algorithm.datastructure.tree.bt;

public class LowestCommonAncestor {

  private TreeNode ans;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);

    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left == null) {
      return right;
    } else if (right == null) {
      return left;
    } else {
      return root;
    }
  }

  public static void main(String[] args) {
    LowestCommonAncestor an = new LowestCommonAncestor();
    TreeNode root = new TreeNode(1, new TreeNode(2), null);
    System.out.println(an.lowestCommonAncestor(root, root, root.left));
  }
}
