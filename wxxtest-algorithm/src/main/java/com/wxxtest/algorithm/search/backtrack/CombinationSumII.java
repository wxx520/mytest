package com.wxxtest.algorithm.search.backtrack;

import java.util.*;

public class CombinationSumII {

  int len;
  int[] candidates;

  int target;

  List<List<Integer>> ans;
  Deque<Integer> path;

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    len = candidates.length;
    this.candidates = candidates;
    this.target = target;
    ans = new ArrayList<>();
    path = new ArrayDeque<>();
    dfs(0, 0);
    return ans;
  }

  private void dfs(int begin, int sum) {
    if (sum == target) {
      ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = begin; i < len; i++) {
      int cNum = candidates[i];
      // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
      if (sum + cNum > target) {
        break;
      }

      // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
      if (i > begin && cNum == candidates[i - 1]) {
        continue;
      }

      path.addLast(cNum);
      // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
      dfs(i + 1, sum + cNum);
      path.removeLast();
    }
  }

  public static void main(String[] args) {
    CombinationSumII cs2 = new CombinationSumII();
    System.out.println(cs2.combinationSum2(
            new int[]{10, 1, 2, 7, 6, 1, 5},
            8
    ));
    System.out.println(cs2.combinationSum2(
            new int[]{2, 5, 2, 1, 2},
            5
    ));
  }
}
