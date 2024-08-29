package com.wxxtest.algorithm.dp.tree;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

public class BTMaximumPathSum {

  private int ans = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    maxGain(root);
    return ans;
  }

  private int maxGain(TreeNode node) {
    if (node == null) {
      return 0;
    }
    //左右的最大贡献值
    int leftGain = Math.max(0, maxGain(node.left));
    int rightGain = Math.max(0, maxGain(node.right));

    //经过当前当前节点的最大路径和为
    int priceNewPath = node.val + leftGain + rightGain;

    ans = Math.max(ans, priceNewPath);

    //当前节点的最大贡献值
    return node.val + Math.max(leftGain, rightGain);
  }

  public static void main(String[] args) {
    BTMaximumPathSum test = new BTMaximumPathSum();
    System.out.println(test.maxPathSum(
            new TreeNode(1,
                    new TreeNode(2),
                    new TreeNode(3))));
    System.out.println(test.maxPathSum(
            new TreeNode(-10,
                    new TreeNode(9),
                    new TreeNode(20,
                            new TreeNode(15),
                            new TreeNode(7)))
    ));

  }
}
