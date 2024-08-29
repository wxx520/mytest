package com.wxxtest.algorithm.datastructure.tree.bt;

public class FlipEquivalentBT {
  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1 == null || root2 == null || root1.val != root2.val) {
      return false;
    }
    return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left) ||
            flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
  }

  public static void main(String[] args) {
    FlipEquivalentBT b = new FlipEquivalentBT();
    TreeNode r1 = new TreeNode(1,
            new TreeNode(2,
                    new TreeNode(4),
                    new TreeNode(5,
                            new TreeNode(7),
                            new TreeNode(8))),
            new TreeNode(3, new TreeNode(6), null));
    TreeNode r2 = new TreeNode(1,
            new TreeNode(3, null, new TreeNode(6)),
            new TreeNode(2,
                    new TreeNode(4),
                    new TreeNode(5,
                            new TreeNode(8),
                            new TreeNode(7))));
    System.out.println(b.flipEquiv(r1, r2));
  }

}
