package com.wxxtest.algorithm.dp.tree;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍III
 * 求二叉树的最大和，直接相连的元素不能一起求和
 */
public class HouseRobberIII {

  /**
   * 最大和的可能性
   * 1、root.value + rob(root.left.left)+ rob(root.left.right)
   * + rob(root.right.left)+ rob(root.right.right)
   * 2、rob(root.left)+rob(root.right)
   *
   * @param root 二叉树
   * @return 最大和
   */
  public int robByRecursion(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int v1 = root.val;
    if (root.left != null) {
      v1 = v1 + robByRecursion(root.left.left) + robByRecursion(root.left.right);
    }
    if (root.right != null) {
      v1 = v1 + robByRecursion(root.right.left) + robByRecursion(root.right.right);
    }
    return Math.max(v1, robByRecursion(root.left) + robByRecursion(root.right));
  }

  Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
  Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

  public int rob(TreeNode root) {
    dfs(root);
    return Math.max(f.getOrDefault(root, 0),
            g.getOrDefault(root, 0));
  }

  private void dfs(TreeNode root) {
    if (root == null) {
      return;
    }
    dfs(root.left);
    dfs(root.right);
    f.put(root, root.val + g.getOrDefault(root.left, 0)
            + g.getOrDefault(root.right, 0));
    g.put(root, Math.max(f.getOrDefault(root.left, 0), g.getOrDefault(root.left, 0))
            + Math.max(f.getOrDefault(root.right, 0), g.getOrDefault(root.right, 0)));
  }


  public static void main(String[] args) {
    HouseRobberIII hr = new HouseRobberIII();
    TreeNode r1 = new TreeNode(3,
            new TreeNode(2, null, new TreeNode(3)),
            new TreeNode(3, null, new TreeNode(1)));
    System.out.println(hr.robByRecursion(r1));
    System.out.println(hr.rob(r1));

    TreeNode r2 = new TreeNode(3,
            new TreeNode(4, new TreeNode(1), new TreeNode(3)),
            new TreeNode(5, null, new TreeNode(1)));
    System.out.println(hr.robByRecursion(r2));
    System.out.println(hr.rob(r2));
  }
}
