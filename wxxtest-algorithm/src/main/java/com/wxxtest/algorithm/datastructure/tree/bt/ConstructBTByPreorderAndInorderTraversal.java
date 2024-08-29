package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序遍历和中序遍历构造二叉树
 */
public class ConstructBTByPreorderAndInorderTraversal {

  int[] preorder;
  Map<Integer, Integer> inMap;

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 1) {
      return new TreeNode(preorder[0]);
    }
    int len = preorder.length;
    this.preorder = preorder;
    inMap = new HashMap<>();
    for (int i = 0; i < len; i++) {
      inMap.put(inorder[i], i);
    }
    return myBuildTree(0, len - 1, 0, len - 1);
  }

  private TreeNode myBuildTree(int preLeft, int preRight, int inLeft, int inRight) {
    if (preLeft > preRight || inLeft > inRight) {
      return null;
    }
    // 前序遍历中的第一个节点就是根节点
    int preorder_root = preorder[preLeft];

    //从中序遍历定位根节点
    int inorder_root_index = inMap.get(preorder_root);

    //先把根节点构造起来
    TreeNode root = new TreeNode(preorder_root);

    //得到左子树中的节点数目
    int size_left_subtree = inorder_root_index - inLeft;
    //递归的构造左子树，并连接到根节点
    root.left = myBuildTree(preLeft + 1,
            preLeft + size_left_subtree,
            inLeft, inorder_root_index - 1);

    //递归的构造右子树，并连接到根节点
    root.right = myBuildTree(preLeft + size_left_subtree + 1, preRight, inorder_root_index + 1, inRight);
    return root;
  }

  public static void main(String[] args) {
    ConstructBTByPreorderAndInorderTraversal test = new ConstructBTByPreorderAndInorderTraversal();
    System.out.println(test.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    System.out.println(test.buildTree(new int[]{-1}, new int[]{-1}));
  }
}
