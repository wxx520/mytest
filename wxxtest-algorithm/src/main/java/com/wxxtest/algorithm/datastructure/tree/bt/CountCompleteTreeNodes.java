package com.wxxtest.algorithm.datastructure.tree.bt;

public class CountCompleteTreeNodes {

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  public static void main(String[] args) {
    CountCompleteTreeNodes c = new CountCompleteTreeNodes();
    TreeNode r1 = new TreeNode(1,
            new TreeNode(2,
                    new TreeNode(4),
                    new TreeNode(5)),
            new TreeNode(3, new TreeNode(6), null)
    );
    System.out.println(c.countNodes(r1));

    System.out.println(c.countNodes(null));

    System.out.println(c.countNodes(new TreeNode(1)));
  }
}
