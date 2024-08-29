package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.LinkedList;

public class MinimumDepthOfBT {

  int ans;

  public int minDepth(TreeNode root) {
    ans = Integer.MAX_VALUE;
    dfs(root, 0);
    return ans;
  }

  /**
   * 广度优先遍历，发现叶子节点就可以直接返回
   * 从上到下一层层遍历，最先出现的叶子一定是高度最低的
   */
  public int minDepthByBFS(TreeNode root) {
    if (root == null) {
      return 0;
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addLast(root);
    int depth = 0;
    while (!queue.isEmpty()) {
      depth++;
      int size = queue.size();
      while (size > 0) {
        TreeNode node = queue.pollFirst();
        if (node.left == null && node.right == null) {
          return depth;
        }
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
        size--;
      }
    }
    return depth;
  }

  /**
   * 深度优先遍历
   * 如果发现比最小深度大，则直接返回
   * 若左右子树为空，则表示为最底层叶子节点，获得深度尝试更新最小深度
   * 否则对于非空左右子树深度+1，继续递归遍历至叶子节点
   */
  public void dfs(TreeNode root, int depth) {
    if (root == null) {
      ans = Math.min(ans, depth);
      return;
    }
    if (depth >= ans) {
      return;
    }

    if (root.left == null && root.right == null) {
      ans = Math.min(depth + 1, ans);
      return;
    }

    if (root.left != null) {
      dfs(root.left, depth + 1);
    }

    if (root.right != null) {
      dfs(root.right, depth + 1);
    }
  }

  public static void main(String[] args) {
    MinimumDepthOfBT d = new MinimumDepthOfBT();
    TreeNode r1 = new TreeNode(
            3,
            new TreeNode(9),
            new TreeNode(20,
                    new TreeNode(15),
                    new TreeNode(7))
    );
    System.out.println(d.minDepth(r1));
    System.out.println(d.minDepthByBFS(r1));

    TreeNode r2 = new TreeNode(1, null,
            new TreeNode(2, null,
                    new TreeNode(3, null,
                            new TreeNode(4, null,
                                    new TreeNode(5, null,
                                            new TreeNode(6))))));
    System.out.println(d.minDepth(r2));
    System.out.println(d.minDepthByBFS(r2));
  }
}
