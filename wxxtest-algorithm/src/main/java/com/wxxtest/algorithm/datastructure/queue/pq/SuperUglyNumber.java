package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < m; i++) {
            q.add(new int[]{primes[i], i, 0});
        }
        int[] ans = new int[n];
        ans[0] = 1;
        for (int j = 1; j < n; ) {
            int[] poll = q.poll();
            int val = poll[0], i = poll[1], idx = poll[2];
            if (val != ans[j - 1])
                ans[j++] = val;
            q.add(new int[]{ans[idx + 1] * primes[i], i, idx + 1});
        }
        return ans[n - 1];
    }

    /**
     * 使用动态规划求解
     */
    public int nthSuperUglyNumberByDP(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }

        int pLen = primes.length;
        int[] indexes = new int[pLen];

        //使用long防止在做乘法运算时整型溢出，虽然用例保证结果不溢出
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            // 因为选最小值，先假设一个最大值
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < pLen; j++) {
                dp[i] = Math.min(dp[i], dp[indexes[j]] * primes[j]);
            }

            // dp[i] 是之前的哪个丑数乘以对应的 primes[j] 选出来的，给它加 1
            for (int j = 0; j < pLen; j++) {
                if (dp[i] == dp[indexes[j]] * primes[j]) {
                    // 注意：这里不止执行一次，例如选出 14 的时候，2 和 7 对应的最小丑数下标都要加 1，大家可以打印 indexes 和 dp 的值加以验证
                    indexes[j]++;
                }
            }
        }
        return (int) dp[n - 1];
    }

    public static void main(String[] args) {
        SuperUglyNumber un = new SuperUglyNumber();
        System.out.println(un.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        System.out.println(un.nthSuperUglyNumberByDP(12, new int[]{2, 7, 13, 19}));
        System.out.println(un.nthSuperUglyNumber(1, new int[]{2, 3, 5}));
        System.out.println(un.nthSuperUglyNumberByDP(1, new int[]{2, 3, 5}));
    }
}
