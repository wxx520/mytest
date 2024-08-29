package com.wxxtest.algorithm.dp.tree;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UniqueBSTII {

  public List<TreeNode> generateTrees(int n) {
    List<TreeNode> ans = new ArrayList<>();
    if (n == 1) {
      TreeNode root = new TreeNode(1);
      ans.add(root);
      return ans;
    }
    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> allTrees = new LinkedList<>();
    if (start > end) {
      allTrees.add(null);
      return allTrees;
    }
    // 枚举可行根节点
    for (int i = start; i <= end; i++) {
      // 获得所有可行的左子树集合
      List<TreeNode> leftTrees = generateTrees(start, i - 1);

      // 获得所有可行的左子树集合
      List<TreeNode> rightTrees = generateTrees(i + 1, end);

      for (TreeNode left : leftTrees) {
        for (TreeNode right : rightTrees) {
          TreeNode root = new TreeNode(i);
          root.left = left;
          root.right = right;
          allTrees.add(root);
        }
      }
    }
    return allTrees;
  }

  public static void main(String[] args) {
    UniqueBSTII test = new UniqueBSTII();
    System.out.println(test.generateTrees(3));
    System.out.println(test.generateTrees(1));
  }
}
