package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    List<Integer> list = new ArrayList<>();
    inOrder(list, this);
    return list.toString();
  }

  private void inOrder(List<Integer> list, TreeNode node) {
    if (node == null) {
      return;
    }
    list.add(node.val);
    inOrder(list, node.left);
    inOrder(list, node.right);
  }
}
