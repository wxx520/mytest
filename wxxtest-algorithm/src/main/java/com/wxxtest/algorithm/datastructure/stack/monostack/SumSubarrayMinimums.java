package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumSubarrayMinimums {

  /**
   * 1、计算以 arr[i] 为最小值的子数组的个数,即在开区间(L,R)中，arr[i]为最小值
   * 2、修改边界定义，避免重复统计子数组:左小右小于等于 或 左小于等于右小于
   * 3、高效计算边界
   */
  public int sumSubarrayMins(int[] arr) {
    int n = arr.length;

    // 左边界 left[i] 为左侧严格小于 arr[i] 的最近元素位置（不存在时为 -1）
    int[] left = new int[n];
    Deque<Integer> st = new ArrayDeque<>(); // 注：推荐用 ArrayDeque 实现栈
    st.push(-1); // 方便赋值 left
    for (int i = 0; i < n; i++) {
      while (st.size() > 1 && arr[st.peekLast()] >= arr[i]) {
        st.removeLast();
      }
      left[i] = st.peekLast();
      st.addLast(i);
    }

    // 左边界 right[i] 为右侧小于等于 arr[i] 的最近元素位置（不存在时为 n）
    int[] right = new int[n];
    st.clear(); //
    st.push(n); // 方便赋值 right
    for (int i = n - 1; i >= 0; i--) {
      while (st.size() > 1 && arr[st.peekLast()] > arr[i]) {
        st.removeLast();
      }
      right[i] = st.peekLast();
      st.addLast(i);
    }

    long ans = 0;
    for (int i = 0; i < n; i++) {
      ans += (long) arr[i] * (i - left[i]) * (right[i] - i);//累加贡献

    }
    int mode = (int) 1e9 + 7;
    return (int) (ans % mode);
  }

  /**
   * 暴力解法，
   * 从左到右遍历，以第i个元素为起点，0<=i<n
   * 对[i,i]，[i,i+1]..[i,n]等连续数组计算最小值并求和
   * 则有 ans = sum(min[i,j]), 0<=i<=j<n,
   * 时间复杂度为0(n^n)
   */
  public int sumSubarrayMinsByBF(int[] arr) {
    int n = arr.length;
    long ans = 0;
    int min;
    long mode = (long) 1e9 + 7;
    for (int i = 0; i < n; i++) {
      min = Integer.MAX_VALUE;
      for (int j = i; j < n; j++) {
        min = Math.min(min, arr[j]);
        ans = ans + min;
      }
    }
    return (int) (ans % mode);
  }

  /**
   * 若s[j][i]表示子数组 [arr[j],arr[j+1],⋯,arr[i]] 的最小值，
   * 则可以推出所有连续子数组的最小值之和为
   * n−1 i
   * ∑   ∑ s[j][i]
   * i=0 j=0
   * <p>
   * 事实上我们可以观察到 s[j−1][i]=min(s[j][i],arr[j−1])。
   */
  public int sumSubarrayMinsByDP(int[] arr) {
    int n = arr.length;
    long ans = 0;
    final int MOD = 1000000007;
    Deque<Integer> monoStack = new ArrayDeque<>();
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      while (!monoStack.isEmpty() && arr[monoStack.peek()] > arr[i]) {
        monoStack.pop();
      }
      int k = monoStack.isEmpty() ? (i + 1) : (i - monoStack.peek());
      dp[i] = k * arr[i] + (monoStack.isEmpty() ? 0 : dp[i - k]);
      ans += dp[i];
      monoStack.push(i);
    }
    return (int) (ans % MOD);
  }

  public static void main(String[] args) {
    SumSubarrayMinimums s = new SumSubarrayMinimums();
    System.out.println(s.sumSubarrayMinsByBF(new int[]{3, 1, 2, 4}));
    System.out.println(s.sumSubarrayMins(new int[]{3, 1, 2, 4}));

    System.out.println(s.sumSubarrayMins(new int[]{1, 2, 4, 2, 3, 1}));
    System.out.println(s.sumSubarrayMinsByDP(new int[]{1, 2, 4, 2, 3, 1}));
  }

}
