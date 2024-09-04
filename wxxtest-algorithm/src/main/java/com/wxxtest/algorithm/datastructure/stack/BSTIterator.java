package com.wxxtest.algorithm.datastructure.stack;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/binary-search-tree-iterator/">173. 二叉搜索树迭代器</a>
 */
public class BSTIterator {

  private final Deque<Integer> queue;

  public BSTIterator(TreeNode root) {
    queue = new ArrayDeque<>();
    inorder(root);
    queue.addLast(-1);
  }

  private void inorder(TreeNode node) {
    if (node == null) {
      return;
    }
    inorder(node.left);
    queue.addLast(node.val);
    inorder(node.right);
  }

  public int next() {
    if (queue.size() <= 1) {
      return queue.peek();
    }
    return queue.pollFirst();
  }

  public boolean hasNext() {
    return queue.size() > 1;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(7,
            new TreeNode(3),
            new TreeNode(15,
                    new TreeNode(9),
                    new TreeNode(20)));
    BSTIterator iterator = new BSTIterator(root);
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.next());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.next());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.next());
    System.out.println(iterator.hasNext());
  }
}
