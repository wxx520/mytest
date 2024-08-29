package com.wxxtest.algorithm.datastructure.tree.bst;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class KthSmallest {

  private List<Integer> ans;
  private int k;

  public int kthSmallest(TreeNode root, int k) {
    this.k = k;
    ans = new LinkedList<>();
    inOrder(root);
    return ans.get(k - 1);
  }

  private void inOrder(TreeNode node) {
    if (node == null) {
      return;
    }
    inOrder(node.left);
    ans.add(node.val);
    if (ans.size() >= k) {
      return;
    }
    inOrder(node.right);
  }

  public static void main(String[] args) {
    KthSmallest k = new KthSmallest();
    TreeNode root = new TreeNode(
            3,
            new TreeNode(1, null, new TreeNode(2)),
            new TreeNode(4)
    );
    System.out.println(k.kthSmallest(root, 1));

    TreeNode r1 = new TreeNode(
            5,
            new TreeNode(3,
                    new TreeNode(2, new TreeNode(1), null),
                    new TreeNode(4)),
            new TreeNode(6)
    );
    System.out.println(k.kthSmallest(r1, 3));
  }
}
