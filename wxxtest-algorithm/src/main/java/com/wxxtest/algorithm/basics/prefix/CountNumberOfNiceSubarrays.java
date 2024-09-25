package com.wxxtest.algorithm.basics.prefix;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/count-number-of-nice-subarrays/description/?envType=problem-list-v2&envId=5F9Kf6K8">1248. 统计「优美子数组」</a>
 */
public class CountNumberOfNiceSubarrays {

  public int numberOfSubarrays(int[] nums, int k) {
    List<Integer> oddIndexes = new ArrayList<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (nums[i] % 2 == 1) {
        oddIndexes.add(i);
      }
    }

    int oddsNum = oddIndexes.size();
    if (oddsNum < k) {
      return 0;
    }
    int ans = 0;
    int l = 0;
    int r = k - 1;
    int leftNum;
    int rightNum;
    while (r < oddsNum) {
      int leftIndex = oddIndexes.get(l);
      int rightIndex = oddIndexes.get(r);
      rightNum = (r == oddsNum - 1) ? (n - rightIndex) : (oddIndexes.get(r + 1) - rightIndex);
      leftNum = (l == 0) ? (leftIndex + 1) : (leftIndex - oddIndexes.get(l - 1));
      ans += rightNum * leftNum;
      l++;
      r++;
    }
    return ans;
  }

  public static void main(String[] args) {
    CountNumberOfNiceSubarrays c = new CountNumberOfNiceSubarrays();
    System.out.println(c.numberOfSubarrays(new int[]{
            1, 1, 2, 1, 1
    }, 3));
    System.out.println(c.numberOfSubarrays(new int[]{
            2, 4, 6
    }, 1));
    System.out.println(c.numberOfSubarrays(new int[]{
            2, 2, 2, 1, 2, 2, 1, 2, 2, 2
    }, 2));
  }
}
