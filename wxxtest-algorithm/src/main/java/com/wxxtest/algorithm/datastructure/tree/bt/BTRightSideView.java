package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BTRightSideView {

  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<Integer> ans = new LinkedList<>();
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addLast(root);
    while (!queue.isEmpty()) {
      int cSize = queue.size();
      ans.add(queue.peekLast().val);
      while (cSize > 0) {
        TreeNode node = queue.pollFirst();
        if (node.left != null) {
          queue.addLast(node.left);
        }
        if (node.right != null) {
          queue.addLast(node.right);
        }
        cSize--;
      }
    }
    return ans;

  }

  public static void main(String[] args) {
    BTRightSideView view = new BTRightSideView();
    TreeNode root = new TreeNode(
            1,
            new TreeNode(2, null, new TreeNode(5)),
            new TreeNode(3, null, new TreeNode(4))
    );
    System.out.println(view.rightSideView(root));
    System.out.println(view.rightSideView(new TreeNode(1, null, new TreeNode(3))));
    System.out.println(view.rightSideView(new TreeNode(
            1,
            new TreeNode(2, new TreeNode(4), null),
            new TreeNode(3)
    )));
  }
}
