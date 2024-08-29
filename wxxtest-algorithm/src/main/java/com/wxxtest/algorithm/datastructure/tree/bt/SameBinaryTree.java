package com.wxxtest.algorithm.datastructure.tree.bt;

public class SameBinaryTree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  public static void main(String[] args) {
    SameBinaryTree sb = new SameBinaryTree();
    TreeNode t1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    TreeNode t2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    System.out.println(sb.isSameTree(t1, t2));
  }

}
