package com.wxxtest.algorithm.search.backtrack;


import java.util.*;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class CombinationSum {
  List<List<Integer>> ans;
  Deque<Integer> path;

  int[] candidates;

  int target;

  int len;

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    if (target <= 1) {
      return new ArrayList<>();
    }
    //排序是减枝的前提
    Arrays.sort(candidates);
    ans = new ArrayList<>();
    path = new ArrayDeque<>();
    this.candidates = candidates;
    this.target = target;
    len = candidates.length;

    dfs(0);
    return ans;
  }

  private void dfs(int sum) {
    if (sum == target) {
      ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = 0; i < len; i++) {
      int cNum = candidates[i];
      //后面的数字肯定更大，直接终止循环
      if (sum + cNum > target) {
        break;
      }

      //只选择不小于已选择的数
      if (!path.isEmpty() && path.peekLast() > cNum) {
        continue;
      }

      path.addLast(cNum);

      dfs(sum + cNum);

      path.removeLast();
    }
  }

  public static void main(String[] args) {
    CombinationSum cs = new CombinationSum();
    System.out.println(cs.combinationSum(
            new int[]{2, 3, 6, 7},
            7
    ));
    System.out.println(cs.combinationSum(
            new int[]{2, 3, 5},
            8
    ));
    System.out.println(cs.combinationSum(
            new int[]{2},
            1
    ));
  }


}
