package com.wxxtest.algorithm.basics.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked">128. 最长连续序列</a>
 */
public class LongestConsecutiveSequence {

  public int longestConsecutive(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return n;
    }
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
      seen.add(num);
    }
    int longestStreak = 1;
    for (int num : seen) {
      //如果不是最小值则继续寻找最小值
      if (!seen.contains(num - 1)) {
        int curNum = num;
        int curStreak = 1;
        while (seen.contains(curNum + 1)) {
          curStreak++;
          curNum++;
        }
        longestStreak = Math.max(curStreak, longestStreak);
      }
    }
    return longestStreak;
  }

  public int longestConsecutiveBySort(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return n;
    }
    Arrays.sort(nums);
    int ans = 1;
    int len = 1;
    int prev = nums[0];
    for (int i = 1; i < n; i++) {
      int cur = nums[i];
      if (prev == cur) {
        continue;
      }
      if (cur - prev == 1) {
        len++;
      } else {
        ans = Math.max(ans, len);
        len = 1;
      }
      prev = cur;
    }
    return Math.max(ans, len);
  }

  public static void main(String[] args) {
    LongestConsecutiveSequence l = new LongestConsecutiveSequence();
    System.out.println(l.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    System.out.println(l.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));

    System.out.println(l.longestConsecutiveBySort(new int[]{100, 4, 200, 1, 3, 2}));
    System.out.println(l.longestConsecutiveBySort(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
  }
}
