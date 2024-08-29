package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层序遍历
 */
public class BTLevelOrderTraversal {

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> rootLevel = new ArrayList<>();
    rootLevel.add(root.val);
    ans.add(rootLevel);

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addFirst(root.left);
    queue.addLast(root.right);

    while (!queue.isEmpty()) {
      List<Integer> cLevel = new ArrayList<>();
      int cSize = queue.size();
      TreeNode cNode;
      while (cSize > 0) {
        cNode = queue.pollFirst();
        if (cNode != null) {
          cLevel.add(cNode.val);
          queue.addLast(cNode.left);
          queue.addLast(cNode.right);
        }
        cSize--;
      }
      if (!cLevel.isEmpty()) {
        ans.add(cLevel);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    BTLevelOrderTraversal bt = new BTLevelOrderTraversal();
    TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
    System.out.println(bt.levelOrder(root));
  }
}
