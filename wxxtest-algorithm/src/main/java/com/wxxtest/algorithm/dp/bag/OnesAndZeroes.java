package com.wxxtest.algorithm.dp.bag;

public class OnesAndZeroes {

  public int findMaxForm(String[] strs, int m, int n) {
    int length = strs.length;
    int[][][] dp = new int[length + 1][m + 1][n + 1];
    //字符串不能重复使用，放在最外层
    for (int i = 1; i <= length; i++) {
      int[] zerosOnes = getZerosOnes(strs[i - 1]);
      int zeros = zerosOnes[0], ones = zerosOnes[1];
      for (int j = 0; j <= m; j++) {
        for (int k = 0; k <= n; k++) {
          dp[i][j][k] = dp[i - 1][j][k];
          if (j >= zeros && k >= ones) {
            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
          }
        }
      }
    }
    return dp[length][m][n];
  }

    public int[] getZerosOnes(String str) {
      int[] zerosOnes = new int[2];
      int length = str.length();
      for (int i = 0; i < length; i++) {
        zerosOnes[str.charAt(i) - '0']++;
      }
      return zerosOnes;
    }

  public static void main(String[] args) {
    OnesAndZeroes oz = new OnesAndZeroes();
    System.out.println(oz.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    System.out.println(oz.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
  }
}
