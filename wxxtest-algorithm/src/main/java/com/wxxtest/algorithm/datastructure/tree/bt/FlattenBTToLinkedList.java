package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.LinkedList;
import java.util.List;

/**
 * 将二叉树按照先序遍历展开为链表
 */
public class FlattenBTToLinkedList {

  public void flatten1(TreeNode root) {
    if (root == null) {
      return;
    }
    TreeNode left = root.left;
    flatten1(left);
    TreeNode right = root.right;
    flatten1(right);
    root.left = null;
    if (left != null) {
      TreeNode last = left;
      while (last.right != null) {
        last = last.right;
      }
      last.right = right;
      root.right = left;
    }
  }

  private List<TreeNode> list;

  /**
   * @param root 原始二叉树
   */
  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    list = new LinkedList<>();
    preOrder(root);
    TreeNode cur = root;
    for (int i = 1; i < list.size(); i++) {
      cur.left = null;
      TreeNode node = list.get(i);
      cur.right = node;
      cur = node;
    }
  }

  private void preOrder(TreeNode node) {
    if (node == null) {
      return;
    }
    list.add(node);
    preOrder(node.left);
    preOrder(node.right);
  }

  public static void main(String[] args) {
    FlattenBTToLinkedList test = new FlattenBTToLinkedList();
    TreeNode t1 = new TreeNode(0);
    test.flatten(t1);
    System.out.println(t1);

    TreeNode t2 = new TreeNode(
            1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(5, null, new TreeNode(6))
    );
    test.flatten1(t2);
    System.out.println(t2);
  }
}
