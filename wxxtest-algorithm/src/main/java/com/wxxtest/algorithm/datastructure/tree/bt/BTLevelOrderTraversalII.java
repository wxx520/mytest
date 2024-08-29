package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，
 * 返回其节点值 自底向上的层序遍历 。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 */
public class BTLevelOrderTraversalII {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if(root==null){
      return new ArrayList<>();
    }
    LinkedList<List<Integer>> ans = new LinkedList<>();
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addLast(root);
    while (!queue.isEmpty()) {
      int cSize = queue.size();
      LinkedList<Integer> cLevel = new LinkedList<>();
      while (cSize > 0) {
        TreeNode node = queue.pollFirst();
        cLevel.add(node.val);
        if (node.left != null) {
          queue.addLast(node.left);
        }
        if (node.right != null) {
          queue.addLast(node.right);
        }
        cSize--;
      }
      if (cLevel.size() > 0) {
        ans.addFirst(cLevel);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    BTLevelOrderTraversalII bt = new BTLevelOrderTraversalII();
    TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
    System.out.println(bt.levelOrderBottom(root));
  }
}
