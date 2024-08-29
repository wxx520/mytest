package com.wxxtest.algorithm.datastructure.tree.bt;

/**
 * 判断平衡二叉树，左右子树树高差值绝对值不超过1
 */
public class BalancedBT {

  public boolean isBalanced(TreeNode root) {
    return helper(root) != -1;
  }

  /**
   * 获取树高
   * 1、若node为null，则返回0
   * 2、若左右树高之差大于1，则返回-1
   * 3、若左右子树树高为-1，即子树非平衡，直接返回-1
   * 否则返回左右子树中树高最高的+1
   */
  public int helper(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int left = helper(node.left);
    int right = helper(node.right);
    if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
      return -1;
    }
    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {
    BalancedBT bt = new BalancedBT();
    TreeNode root = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20,
                    new TreeNode(15),
                    new TreeNode(7)));
    System.out.println(bt.isBalanced(root));
    TreeNode r1 = new TreeNode(1,
            new TreeNode(2,
                    new TreeNode(3,
                            new TreeNode(4),
                            new TreeNode(4)),
                    new TreeNode(3)),
            new TreeNode(2));
    System.out.println(bt.isBalanced(r1));

    System.out.println(bt.isBalanced(null));
  }
}
