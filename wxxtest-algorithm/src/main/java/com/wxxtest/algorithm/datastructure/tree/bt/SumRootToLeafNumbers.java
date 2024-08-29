package com.wxxtest.algorithm.datastructure.tree.bt;

public class SumRootToLeafNumbers {

  private int ans = 0;

  public int sumNumbers(TreeNode root) {
    ans = 0;
    dfs(root, 0);
    return ans;
  }

  private void dfs(TreeNode root, int sum) {
    if (root == null) {
      ans += sum;
      return;
    }
    sum = 10 * sum + root.val;
    if (root.left == null && root.right == null) {
      ans += sum;
      return;
    }
    if (root.left != null) {
      dfs(root.left, sum);
    }
    if (root.right != null) {
      dfs(root.right, sum);
    }
  }

  public static void main(String[] args) {
    SumRootToLeafNumbers st = new SumRootToLeafNumbers();
    TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    System.out.println(st.sumNumbers(root));

    TreeNode root1 = new TreeNode(
            4,
            new TreeNode(9, new TreeNode(5), new TreeNode(1)),
            new TreeNode(0));
    System.out.println(st.sumNumbers(root1));

    TreeNode r2 = new TreeNode(1,new TreeNode(0),null);
    System.out.println(st.sumNumbers(r2));

  }
}
