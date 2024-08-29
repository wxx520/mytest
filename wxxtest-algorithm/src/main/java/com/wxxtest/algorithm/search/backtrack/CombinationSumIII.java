package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */
public class CombinationSumIII {

  List<List<Integer>> ans;
  Deque<Integer> path;


  public List<List<Integer>> combinationSum3(int k, int n) {
    if (n < k) {
      return new ArrayList<>();
    }
    path = new ArrayDeque<>();
    ans = new ArrayList<>();
    dfs(k, n, 0, 1);
    return ans;
  }

  /**
   * @param k     满足条件路径的长度
   * @param n     满足条件的结果和 n1+..+nk = n
   * @param sum   当前路径的和
   * @param begin 当前路径开始的位置
   */
  private void dfs(int k, int n, int sum, int begin) {
    //当长度和和都满足是加入
    if (sum == n && path.size() == k) {
      ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = begin; i <= 9; i++) {
      //大减枝
      if (sum + i > n || path.size() >= k) {
        break;
      }
      path.addLast(i);
      //不能重复选择，所以是i+1
      dfs(k, n, sum + i, i + 1);
      path.removeLast();
    }
  }

  public static void main(String[] args) {
    CombinationSumIII cs3 = new CombinationSumIII();
    System.out.println(cs3.combinationSum3(3, 7));
    System.out.println(cs3.combinationSum3(3, 9));
    System.out.println(cs3.combinationSum3(4, 1));
  }
}
