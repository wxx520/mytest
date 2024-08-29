package com.wxxtest.algorithm.dp.lis;

public class NumberOfLongestIncreasingSubsequences {

  /**
   * dp[i]以nums[i]为结尾的最长子序列长度，则对于dp[i+1]来说
   * dp[i+1] = Max(nums[i+1]>nums[j]:dp[j]+1), 0<=j<=i;
   * <p>
   * 或dp[i+1]=1,表示0<=j<=i中不存在比nums[i+1]小的值
   * <p>
   * 如果dp[i+1]为所有dp中的最长子序列长度，则
   * numberOfLIS = all numbers j in dp[j]==dp[i+1]-1
   */
  public static int findNumberOfLISByDP(int[] nums) {
    int n = nums.length;
    int maxLen = 1, maxLenNum = 1;
    //以nums[i]为结尾的最长子序列的长度
    int[] lis = new int[n];
    //构成nums[i]为结尾的最长子序列的数量
    int[] numberOfLIS = new int[n];
    lis[0] = 1;
    numberOfLIS[0] = 1;
    for (int i = 1; i < n; i++) {
      lis[i] = 1;
      numberOfLIS[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {//产生一种构成最长子序列的可能性
          if (lis[j] + 1 > lis[i]) {//首次出现
            lis[i] = lis[j] + 1;
            numberOfLIS[i] = numberOfLIS[j];
          } else if (lis[j] + 1 == lis[i]) {//多次出现
            numberOfLIS[i] += numberOfLIS[j];
          }
        }
      }
      if (lis[i] > maxLen) {//首次出现
        maxLen = lis[i];
        maxLenNum = numberOfLIS[i];
      } else if (lis[i] == maxLen) {//多次出现
        maxLenNum = maxLenNum + numberOfLIS[i];
      }
    }

    return maxLenNum;

  }

  //TODO
  public static int findNumberOfLISByBinary(int[] nums) {
    int n = nums.length;
    int[] lis = new int[n + 1];
    int[] cnt = new int[n + 1];
    int len = 1;
    lis[len] = nums[0];
    cnt[len] = 1;
    for (int i = 1; i < n; i++) {
      int curNum = nums[i];
      if (curNum > lis[len]) {
        lis[++len] = curNum;//首次加长
        cnt[len] = 1;
      } else {
        for (int j = len - 1; j > 0; j--) {
          if (nums[i] > lis[j]) {
            lis[j + 1] = nums[i];
            break;
          }
        }
      }
    }
    return cnt[len];
  }

  public static void main(String[] args) {
    System.out.println(findNumberOfLISByDP(new int[]{1, 3, 5, 4, 7}));
    System.out.println(findNumberOfLISByDP(new int[]{9, 1, 1, 1, 1}));
    System.out.println(findNumberOfLISByBinary(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));

  }
}
