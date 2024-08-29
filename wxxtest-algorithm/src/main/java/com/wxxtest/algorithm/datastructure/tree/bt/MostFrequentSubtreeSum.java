package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.*;

public class MostFrequentSubtreeSum {

  Map<Integer, Integer> freq;
  int maxFrequent;

  public int[] findFrequentTreeSum(TreeNode root) {
    freq = new HashMap<>();
    maxFrequent = Integer.MIN_VALUE;

    dfs(root);
    List<Integer> res = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
      if (entry.getValue() == maxFrequent) {
        res.add(entry.getKey());
      }
    }

    int size = res.size();
    int[] result = new int[size];
    for (int i = 0; i < size; i++) {
      result[i] = res.get(i);
    }
    return result;

  }

  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      int count = freq.getOrDefault(root.val, 0);
      freq.put(root.val, count + 1);
      maxFrequent = Math.max(maxFrequent, count + 1);
      return root.val;
    }
    int sum = root.val + dfs(root.left) + dfs(root.right);
    int count = freq.getOrDefault(sum, 0);
    freq.put(sum, count + 1);
    maxFrequent = Math.max(maxFrequent, count + 1);
    return sum;
  }

  public static void main(String[] args) {
    MostFrequentSubtreeSum t = new MostFrequentSubtreeSum();
    TreeNode r1 = new TreeNode(5, new TreeNode(2), new TreeNode(-3));
    System.out.println(Arrays.toString(t.findFrequentTreeSum(r1)));

    TreeNode r2 = new TreeNode(5, new TreeNode(2), new TreeNode(-5));

    System.out.println(Arrays.toString(t.findFrequentTreeSum(r2)));
  }
}
