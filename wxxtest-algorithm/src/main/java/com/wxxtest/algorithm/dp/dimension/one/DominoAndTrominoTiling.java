package com.wxxtest.algorithm.dp.dimension.one;

public class DominoAndTrominoTiling {

  /**
   * 考虑这么一种平铺的方式：在第 i 列前面的正方形都被瓷砖覆盖，在第 i 列后面的正方形都没有被瓷砖覆盖（i 从 1 开始计数）。那么第 i
   * 列的正方形有四种被覆盖的情况：
   * <p>
   * 一个正方形都没有被覆盖，记为状态 0；
   * <p>
   * 只有上方的正方形被覆盖，记为状态 1；
   * <p>
   * 只有下方的正方形被覆盖，记为状态 2；
   * <p>
   * 上下两个正方形都被覆盖，记为状态 3。
   * <p>
   * 使用 dp[i][s] 表示平铺到第 i 列时，各个状态 s 对应的平铺方法数量。考虑第 i−1 列和第 i
   * 列正方形，它们之间的状态转移如下图（红色条表示新铺的瓷砖）：
   * dp[i][0]=dp[i−1][3]
   * dp[i][1]=dp[i−1][0]+dp[i−1][2]
   * dp[i][2]=dp[i−1][0]+dp[i−1][1]
   * dp[i][3]=dp[i−1][0]+dp[i−1][1]+dp[i−1][2]+dp[i−1][3]
   */
  public static int numTilings(int n) {
    if (n == 1) {
      return 1;
    }
    int MOD = 1000000007;
    int[][] dp = new int[n + 1][4];
    dp[0][3] = 1;
    for (int i = 1; i <= n; i++) {
      dp[i][0] = dp[i - 1][3];
      dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
      dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
      dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;
    }
    return dp[n][3];
  }

  public static void main(String[] args) {
    System.out.println(numTilings(3));
  }
}
