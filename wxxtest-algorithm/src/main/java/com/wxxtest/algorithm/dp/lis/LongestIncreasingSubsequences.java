package com.wxxtest.algorithm.dp.lis;

/**
 *
 */
public class LongestIncreasingSubsequences {

  /**
   * dp[i]表示以nums[i]结尾的最长最序列的长度
   * 对于dp[i],如果nums[i]>nums[j],则dp[i] = Max(dp[j])+1
   *
   * @param nums 原始数组
   * @return 最长子序列的长度
   */
  public static int lengthOfLISByDP(int[] nums) {
    int n = nums.length;
    int ans = 1;
    int[] dp = new int[n];
    dp[0] = 1;
    for (int i = 1; i < n; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      ans = Math.max(ans, dp[i]);
    }
    return ans;

  }

  /**
   * 贪心算法加二分查找
   * <p>
   * 贪心策略
   * 我们想最长上升子序列尽可能长，则要上升的尽可能慢，即序列最后加上的最大值尽可能小。
   * 基于以上贪心策略，我们可以维护一个数组
   * 最大上升序列长度为i的末尾元素的最小值为d[i],用len记录当前的最长上升子序列的长度，起始时，len=1
   * d[1] = nums[0];
   * <p>
   * 证明d[i]随着i/len 单调递增，反证法
   * 假设存在d[i]<=d[j],且i>j，则对于d[i]的最长子序列，减掉数量(i-j)个数，
   * 则重新得到长度为j的最长子序列d1[j],且d1[j]<d[i]<=d[j], 则d1[j]<d[j]。
   * 那么我们就找到了一个长度为 j的最长上升子序列，并且末尾元素比 d[j] 小，从而产生了矛盾。
   * 因此数组 d 的单调性得证。
   * <p>
   * <p>
   * 如何向d[n]中填值
   * 二分策略
   *
   * @param nums 原始数组
   * @return 最长递增子序列的长度
   */
  public static int lengthOfLISByMonotony(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return n;
    }
    int[] dp = new int[n + 1];
    int len = 1;

    dp[len] = nums[0];
    for (int i = 1; i < n; i++) {
      int curNum = nums[i];
      if (curNum > dp[len]) {
        dp[++len] = curNum;
      } else {
        for (int j = len - 1; j > 0; j--) {
          if (curNum > dp[j]) {
            dp[j + 1] = nums[i];
            break;
          }
        }
        if (curNum < dp[1]) {
          dp[1] = curNum;
        }
      }
    }
    return len;
  }

  public static int lengthOfLISByMonotonyAndBinary(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return n;
    }
    int[] dp = new int[n + 1];
    int len = 1;

    dp[len] = nums[0];
    for (int i = 1; i < n; i++) {
      int curNum = nums[i];
      if (curNum > dp[len]) {
        dp[++len] = curNum;
      } else {
        //根据单调递增的特性，将复杂度降为logN
        int l = 0, r = len, pos = 0;
        while (l <= r) {
          int mid = (r + l) >> 1;
          if (dp[mid] < curNum) {
            pos = mid;
            l = mid + 1;
          } else {
            r = mid - 1;
          }
        }
        dp[pos + 1] = curNum;
      }
    }
    return len;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLISByMonotony(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));

    System.out.println(lengthOfLISByDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));

    System.out.println(lengthOfLISByMonotonyAndBinary(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
  }
}
