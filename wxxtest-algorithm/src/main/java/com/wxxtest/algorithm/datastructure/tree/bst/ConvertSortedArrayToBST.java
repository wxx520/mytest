package com.wxxtest.algorithm.datastructure.tree.bst;

import com.wxxtest.algorithm.datastructure.tree.bt.TreeNode;

public class ConvertSortedArrayToBST {

  public TreeNode sortedArrayToBST(int[] nums) {
    int n = nums.length;
    return sortedArrayToBST(nums, 0, n - 1);
  }

  private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
    if (l > r) {
      return null;
    } else if (l == r) {
      return new TreeNode(nums[l]);
    }
    int mid = (r - l) / 2 + l;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = sortedArrayToBST(nums, l, mid - 1);
    root.right = sortedArrayToBST(nums, mid + 1, r);
    return root;
  }

  public static void main(String[] args) {
    ConvertSortedArrayToBST c = new ConvertSortedArrayToBST();
    System.out.println(c.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
  }
}
