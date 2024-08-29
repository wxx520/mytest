package com.wxxtest.algorithm.datastructure.tree.bt;

public class InsufficientNodesInRootToLeafPaths {

  public TreeNode sufficientSubset(TreeNode root, int limit) {
    boolean rootDeleted = dfs(root, 0, limit);
    if (rootDeleted) {
      return null;
    }
    return root;
  }

  /**
   * 因为左右子节点要先告诉父节点自己是否会因为不足被删除，使用后序遍历
   * @param node 当前节点
   * @param s 到达该节点前的路径和
   * @param limit 不足的值
   * @return 返回 node 结点是否被删除（注意：这个返回值的意义，直接影响整个逻辑。）
   */
  private boolean dfs(TreeNode node, int s, int limit) {
    if (node.left == null && node.right == null) {
      return s + node.val < limit;
    }

    // 注意：如果 dfs 的返回值的意义是这个结点是否被删除，它们的默认值应该设置为 true
    boolean lTreeDeleted = true;
    boolean rTreeDeleted = true;

    if (node.left != null) {
      lTreeDeleted = dfs(node.left, s + node.val, limit);
    }

    if (node.right != null) {
      rTreeDeleted = dfs(node.right, s + node.val, limit);
    }

    // 左右子树是否保留的结论得到了，由自己来执行是否删除它们
    if (lTreeDeleted) {
      node.left = null;
    }
    if (rTreeDeleted) {
      node.right = null;
    }

    // 只有左右子树都被删除了，自己才没有必要保留
    return lTreeDeleted && rTreeDeleted;
  }

  public static void main(String[] args) {
    InsufficientNodesInRootToLeafPaths i = new InsufficientNodesInRootToLeafPaths();
    TreeNode r1 = new TreeNode(1,
            new TreeNode(2,
                    new TreeNode(4,
                            new TreeNode(8),
                            new TreeNode(9)),
                    new TreeNode(-99,
                            new TreeNode(-99),
                            new TreeNode(-99))),
            new TreeNode(3,
                    new TreeNode(-99,
                            new TreeNode(12),
                            new TreeNode(13)),
                    new TreeNode(7,
                            new TreeNode(-99),
                            new TreeNode(14))));
    System.out.println(i.sufficientSubset(r1, 1));

    TreeNode r2 = new TreeNode(5,
            new TreeNode(4, null,
                    new TreeNode(11,
                            new TreeNode(7),
                            new TreeNode(1))),
            new TreeNode(8,
                    new TreeNode(17),
                    new TreeNode(4,
                            new TreeNode(5),
                            new TreeNode(3))));
    System.out.println(i.sufficientSubset(r2, 22));

    TreeNode r3 = new TreeNode(1,
            new TreeNode(2, new TreeNode(-5), null),
            new TreeNode(-3, new TreeNode(4), null));
    System.out.println(i.sufficientSubset(r3, -1));

  }
}
