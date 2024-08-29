package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，
 * 请你返回该数组所有可能的子集(幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
public class SubsetII {

  private List<List<Integer>> ans;

  private List<Integer> path;

  public List<List<Integer>> subsetsWithDupByRecur(int[] nums) {
    Arrays.sort(nums);
    ans = new LinkedList<>();
    path = new ArrayList<>();
    dfs(false, 0, nums);
    return ans;
  }

  /**
   * @param choosePre 前一个元素是否已经被选择
   * @param cur       当前位置需要被确定是否选择
   * @param nums      从nums中能产生不同组合的可能性
   */
  private void dfs(boolean choosePre, int cur, int[] nums) {
    if (cur == nums.length) {
      ans.add(new ArrayList<>(path));
      return;
    }
    //1、当前位置的元素不被选择
    dfs(false, cur + 1, nums);
    if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
      return;
    }

    //2、当前元素被选择，继续向后确定下一个元素
    path.add(nums[cur]);
    dfs(true, cur + 1, nums);
    path.remove(path.size() - 1);
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    ans = new LinkedList<>();
    path = new ArrayList<>();
    int n = nums.length;
    for (int mask = 0; mask < (1 << n); mask++) {
      path.clear();
      boolean flag = true;
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
          if(i>0&&((mask>>(i-1)&1) == 0)&& nums[i] == nums[i - 1]){
            flag = false;
            break;
          }
          path.add(nums[i]);
        }
      }
      if (flag) {
        ans.add(new ArrayList<>(path));
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    SubsetII s2 = new SubsetII();
    int[] n1 = new int[]{1, 2, 2};
    System.out.println(s2.subsetsWithDupByRecur(n1));
    System.out.println(s2.subsetsWithDup(n1));
    System.out.println(s2.subsetsWithDupByRecur(new int[]{0}));
    System.out.println(s2.subsetsWithDup(new int[]{0}));
  }
}
