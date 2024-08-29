package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.LinkedList;
import java.util.List;

public class BTPaths {

  List<String> ans;
  String split = "->";

  public List<String> binaryTreePaths(TreeNode root) {
    ans = new LinkedList<>();
    dfs(root, "");
    return ans;
  }

  private void dfs(TreeNode node, String path) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      ans.add(path + node.val);
      return;
    }
    path = path + node.val + split;
    dfs(node.left, path);
    dfs(node.right, path);
  }

  public static void main(String[] args) {
    BTPaths bt = new BTPaths();
    TreeNode r1 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(5)),
            new TreeNode(3));
    System.out.println(bt.binaryTreePaths(r1));

    System.out.println(bt.binaryTreePaths(new TreeNode(1)));
  }
}
