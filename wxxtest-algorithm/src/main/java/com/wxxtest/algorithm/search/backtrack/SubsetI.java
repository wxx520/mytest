package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class SubsetI {

  /**
   * nums的数字有n个，即有2的n次方种可能性
   * 0000....1111,当末尾为1时表示该位的数字被选中，则加入到当前集合中
   * @param nums 原始数组
   * @return 返回所有组合的可能性
   */
  public List<List<Integer>> subsets(int[] nums) {
    int n = nums.length;
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new LinkedList<>();
    for (int mask = 0; mask < (1 << n); mask++) {
      t.clear();
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0){
          t.add(nums[i]);
        }
      }
      ans.add(new ArrayList<>(t));
    }
    return ans;
  }

  private List<List<Integer>> ans;
  private List<Integer> path;
  public List<List<Integer>> subsetsByRecur(int[] nums) {
    path = new ArrayList<>();
    ans = new ArrayList<>();
    dfs(0,nums);
    return ans;
  }

  private void dfs(int cur, int[] nums) {
    //表示此处遍历中nums中每个元素是否被选择已经被确定
    if(cur==nums.length){
      ans.add(new ArrayList<>(path));
      return;
    }
    //1、当前cur位置的元素被选择，则加入结果集
    path.add(nums[cur]);
    dfs(cur+1,nums);
    //恢复现场
    path.remove(path.size()-1);

    //2、当前位置的元素不被选择，直接进入下一个位置的判断
    dfs(cur+1,nums);

  }

  public static void main(String[] args) {
    SubsetI s1 = new SubsetI();
    System.out.println(s1.subsets(new int[]{1, 2, 3}));
    System.out.println(s1.subsetsByRecur(new int[]{1, 2, 3}));
  }
}
