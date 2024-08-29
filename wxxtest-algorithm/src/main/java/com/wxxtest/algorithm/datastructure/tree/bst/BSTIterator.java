package com.wxxtest.algorithm.datastructure.tree.bst;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树迭代器
 */
public class BSTIterator {
  private final List<Integer> dummyRoot;
  private int index;

  public BSTIterator(TreeNode root) {
    dummyRoot = new ArrayList<>();
    dfs(root);
    index = 0;
  }

  private void dfs(TreeNode node) {
    if (node == null) {
      return;
    }
    dfs(node.left);
    dummyRoot.add(node.val);
    dfs(node.right);
  }

  public int next() {
    return dummyRoot.get(index++);
  }

  public boolean hasNext() {
    return index < dummyRoot.size();
  }

  public static void main(String[] args) {

    TreeNode root = new TreeNode(
            7,
            new TreeNode(3),
            new TreeNode(15,new TreeNode(9),new TreeNode(20))
    );
    BSTIterator it = new BSTIterator(root);
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(it.hasNext());
    System.out.println(it.next());
    System.out.println(it.hasNext());
    System.out.println(it.next());
    System.out.println(it.hasNext());
    System.out.println(it.next());
    System.out.println(it.hasNext());
  }
}
