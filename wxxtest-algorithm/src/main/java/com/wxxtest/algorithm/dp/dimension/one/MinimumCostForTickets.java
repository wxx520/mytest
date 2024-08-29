package com.wxxtest.algorithm.dp.dimension.one;

import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets {


  private Integer[] memo;

  private Set<Integer> daySet;

  /**
   * 定义f(i)完成第i天到最后一天旅行的最低消费，则有
   * f[365]=cost[1];
   * f[i] = min(f[i+j]+cost[j]), cost[j]表示j天需要的花费
   *
   * @param days  想要旅行的日期
   * @param costs 火车票三种不同的销售方式
   * @return 完成所有日期的最低消费
   */
  public int minCostTickets(int[] days, int[] costs) {
    this.costs = costs;
    daySet = new HashSet<>();
    for (int d : days) {
      daySet.add(d);
    }
    memo = new Integer[366];
    return dfs(1);
  }

  private int dfs(int i) {
    if (i > 365) {
      return 0;
    }
    if (memo[i] != null) {
      return memo[i];
    }
    if (daySet.contains(i)) {
      memo[i] = Math.min(Math.min(dfs(i + 1) + costs[0], dfs(i + 7) + costs[1]), dfs(i + 30) + costs[2]);
    } else {
      memo[i] = dfs(i + 1);
    }
    return memo[i];
  }

  private int[] costs, days;
  private Integer[] f;

  int[] durations = new int[]{1, 7, 30};

  public int mincostTickets(int[] days, int[] costs) {
    this.costs = costs;
    this.days = days;
    f = new Integer[days.length];
    return dp(0);
  }

  private int dp(int i) {
    if (i >= days.length) {
      return 0;
    }
    if (f[i] != null) {
      return f[i];
    }
    f[i] = Integer.MAX_VALUE;
    int j = i;
    for (int k = 0; k < costs.length; k++) {
      while (j < days.length && days[j] < days[i] + durations[k]) {
        j++;
      }
      f[i] = Math.min(f[i], dp(j) + costs[k]);
    }
    return f[i];
  }

  public static void main(String[] args) {
    MinimumCostForTickets tickets = new MinimumCostForTickets();
    System.out.println(tickets.minCostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    System.out.println(tickets.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));

    System.out.println(tickets.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    System.out.println(tickets.mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));

  }
}
