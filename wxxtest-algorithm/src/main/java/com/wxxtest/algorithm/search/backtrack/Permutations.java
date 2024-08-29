package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 */
public class Permutations {

  List<List<Integer>> ans;

  List<Integer> path;

  int[] nums;

  boolean[] used;

  int len;

  public List<List<Integer>> permute(int[] nums) {
    int len = nums.length;
    List<List<Integer>> ans = new ArrayList<>();
    if (len == 1) {
      List<Integer> path = new ArrayList<>();
      path.add(nums[0]);
      ans.add(path);
      return ans;
    }
    this.ans = ans;
    this.nums = nums;
    this.len = len;
    used = new boolean[len];
    path = new ArrayList<>();
    dfs();
    return ans;
  }

  private void dfs() {
    if (path.size() == len) {
      ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = 0; i < len; i++) {
      if (!used[i]) {
        used[i] = true;
        path.add(nums[i]);
        dfs();
        path.remove(path.size() - 1);
        used[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    Permutations p = new Permutations();
    System.out.println(p.permute(new int[]{1, 2, 3}));
    System.out.println(p.permute(new int[]{0, 1}));
    System.out.println(p.permute(new int[]{1}));
  }
}
