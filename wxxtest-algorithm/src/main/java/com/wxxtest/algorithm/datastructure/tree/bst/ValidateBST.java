package com.wxxtest.algorithm.datastructure.tree.bst;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

public class ValidateBST {

  private Integer inOrder;
  private boolean ans;

  public boolean isValidBST(TreeNode root) {
    inOrder = null;
    ans = true;
    dfs(root);
    return ans;
  }

  private void dfs(TreeNode root) {
    if (root == null || !ans) {
      return;
    }
    dfs(root.left);
    if (inOrder != null && root.val <= inOrder) {
      ans = false;
      return;
    }
    inOrder = root.val;
    dfs(root.right);
  }

  public static void main(String[] args) {
    ValidateBST va = new ValidateBST();
    TreeNode r = new TreeNode(
            5,
            new TreeNode(4),
            new TreeNode(6,
                    new TreeNode(3),
                    new TreeNode(7))
    );
    System.out.println(va.isValidBST(r));

    TreeNode r1 = new TreeNode(
            5,
            new TreeNode(1),
            new TreeNode(4,
                    new TreeNode(3),
                    new TreeNode(6))
    );
    System.out.println(va.isValidBST(r1));

    System.out.println(va.isValidBST(new TreeNode(
            2,
            new TreeNode(1),
            new TreeNode(3)
    )));
  }

}
