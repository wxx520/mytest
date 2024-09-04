package com.wxxtest.algorithm.datastructure.stack;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/binary-search-tree-iterator/">173. 二叉搜索树迭代器</a>
 * 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
 */
public class BSTIteratorII {

  private final Deque<TreeNode> stack;

  public BSTIteratorII(TreeNode root) {
    stack = new ArrayDeque<>();
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  public int next() {
    TreeNode top = stack.pop();
    TreeNode p = top.right;
    while (p != null) {
      stack.push(p);
      p = p.left;
    }
    return top.val;
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(7,
            new TreeNode(3),
            new TreeNode(15,
                    new TreeNode(9),
                    new TreeNode(20)));
    BSTIteratorII iterator = new BSTIteratorII(root);
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
