package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/sum-of-total-strength-of-wizards/description/?envType=problem-list-v2&envId=cIOgJFon">2281. 巫师的总力量和</a>
 */
public class SumOfTotalStrengthOfWizards {

  /**
   * 令sum[i]为[0,i]的范围巫师的力量和，则
   * 任意子区间[i,j]的力量和为s[j]-s[i-1]
   * <p>
   * 令f[i][j]为区间[i,j]的最小值，
   * n−1 n-1
   * ∑   ∑ f[i][j]*(s[j]-s[i-1])
   * i=0 j=i
   */
  public int totalStrengthByBF(int[] strength) {
    int n = strength.length;
    if (n == 0) {
      return 0;
    }
    long[] sum = new long[n + 1];
    sum[0] = 0;
    for (int i = 1; i <= n; i++) {
      sum[i] = sum[i - 1] + strength[i - 1];
    }

    long ans = 0;
    for (int i = 0; i < n; i++) {
      int min = Integer.MAX_VALUE;
      for (int j = i; j < n; j++) {
        min = Math.min(min, strength[j]);
        ans += min * (sum[j + 1] - sum[i]);
      }
    }

    final int MOD = 1000000007;
    return (int) (ans % MOD);
  }

  public int totalStrength(int[] strength) {
    int n = strength.length;
    int[] left = new int[n];  // left[i] 为左侧严格小于 strength[i] 的最近元素位置（不存在时为 -1）
    int[] right = new int[n]; // right[i] 为右侧小于等于 strength[i] 的最近元素位置（不存在时为 n）
    Arrays.fill(right, n);
    Deque<Integer> st = new ArrayDeque<>();
    st.addLast(-1);
    for (int i = 0; i < n; i++) {
      while (st.size() > 1 && strength[st.peekLast()] >= strength[i]) {
        right[st.removeLast()] = i;
      }
      left[i] = st.peekLast();
      st.addLast(i);
    }

    long s = 0L;//前缀和
    long[] ss = new long[n + 2];//前缀和的和
    int mode = (int) 1e9 + 7;
    for (int i = 1; i <= n; i++) {
      s += strength[i - 1];
      ss[i + 1] = (ss[i] + s) % mode;
    }

    long ans = 0L;
    for (int i = 0; i < n; i++) {
      int l = left[i] + 1;
      int r = right[i] - 1; // [l,r] 左闭右闭
      long tot = ((i - l + 1) * (ss[r + 2] - ss[i + 1]) - (r - i + 1) * (ss[i + 1] - ss[l])) % mode;
      ans = (ans + strength[i] * tot) % mode;
    }
    return (int) (ans + mode) % mode; // 防止算出负数
  }

  public static void main(String[] args) {
    SumOfTotalStrengthOfWizards s = new SumOfTotalStrengthOfWizards();
    System.out.println(s.totalStrength(new int[]{1, 3, 1, 2}));
    System.out.println(s.totalStrength(new int[]{5, 4, 6}));
  }
}
