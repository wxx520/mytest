package com.wxxtest.algorithm.dp.lcs;

/**
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * <p>
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
 * <p>
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * <p>
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * 其实就是找两个数组的最大公共子序列
 */
public class UncrossedLines {

  public static int maxUncrossedLines(int[] text1, int[] text2) {
    int n = text1.length, m = text2.length;
    // f[i][j]表示t1前i个字符,t2的前j个字符的最长公共子序列
    int[][] f = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      int c1 = text1[i - 1];
      for (int j = 1; j <= m; j++) {
        if (c1 == text2[j - 1]) {
          f[i][j] = f[i - 1][j - 1] + 1;
        } else {
          f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
        }
      }
    }
    return f[n][m];
  }

  public static void main(String[] args) {
    System.out.println(maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
  }
}
