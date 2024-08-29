package com.wxxtest.algorithm.datastructure.tree.bt;

public class AverageOfSubtree {
  private int ans;

  public int averageOfSubtree(TreeNode root) {
    ans = 0;
    dfs(root);
    return ans;
  }

  /**
   * 深度优先遍历
   *
   * @param root 根节点
   * @return [节点数量，节点之和]
   */
  private int[] dfs(TreeNode root) {
    //叶子节点肯定是的
    if (root.left == null && root.right == null) {
      ans++;
      return new int[]{1, root.val};
    }
    int nodeNum = 1;
    int sum = root.val;
    if (root.left != null) {
      int[] l = dfs(root.left);
      nodeNum += l[0];
      sum += l[1];
    }

    if (root.right != null) {
      int[] r = dfs(root.right);
      nodeNum += r[0];
      sum += r[1];
    }

    //平均值满足条件也是
    if (sum / nodeNum == root.val) {
      ans++;
    }

    return new int[]{nodeNum, sum};
  }

  public static void main(String[] args) {
    AverageOfSubtree a = new AverageOfSubtree();
    TreeNode r1 = new TreeNode(4,
            new TreeNode(8,
                    new TreeNode(0),
                    new TreeNode(1)),
            new TreeNode(5, null, new TreeNode(6)));
    System.out.println(a.averageOfSubtree(r1));

    TreeNode r2 = new TreeNode(1);
    System.out.println(a.averageOfSubtree(r2));
  }
}
