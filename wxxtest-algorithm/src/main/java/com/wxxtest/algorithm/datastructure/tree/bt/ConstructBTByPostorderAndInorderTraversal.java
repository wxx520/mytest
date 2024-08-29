package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTByPostorderAndInorderTraversal {
  private int[] postorder;
  private Map<Integer, Integer> inMap;

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    int len = inorder.length;
    if (len == 1) {
      new TreeNode(postorder[len - 1]);
    }
    this.postorder = postorder;
    inMap = new HashMap<>();
    for (int i = 0; i < len; i++) {
      inMap.put(inorder[i], i);
    }
    return myBuildTree(0, len - 1, 0, len - 1);

  }

  public TreeNode myBuildTree(int inLeft, int inRight, int postLeft, int postRight) {
    if (inLeft > inRight) {
      return null;
    }
    //根节点是后序遍历的最后一个元素
    int post_root = postorder[postRight];

    //定位根节点在中序遍历的位置
    int in_root_index = inMap.get(post_root);

    //创建根节点
    TreeNode root = new TreeNode(post_root);

    //右子树的长度
    int size_left_subtree = in_root_index - inLeft;

    root.left = myBuildTree(inLeft, in_root_index - 1,
            postLeft, postLeft + size_left_subtree - 1);
    root.right = myBuildTree(in_root_index + 1, inRight, postLeft + size_left_subtree, postRight - 1);
    return root;
  }

  public static void main(String[] args) {
    ConstructBTByPostorderAndInorderTraversal test = new ConstructBTByPostorderAndInorderTraversal();
    System.out.println(test.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    System.out.println(test.buildTree(new int[]{-1}, new int[]{-1}));
  }
}
