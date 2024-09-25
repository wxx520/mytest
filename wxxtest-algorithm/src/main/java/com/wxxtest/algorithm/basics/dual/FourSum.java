package com.wxxtest.algorithm.basics.dual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    int n = nums.length;
    if (n < 4) {
      return ans;
    }
    Arrays.sort(nums);
    if (nums[0] >= 0 && nums[0] > target) {
      return ans;
    }
    for (int a = 0; a < n; a++) {
      if (a > 0 && nums[a - 1] == nums[a]) {
        continue;
      }
      int remainA = target - nums[a];
      for (int b = a + 1; b < n; b++) {
        if (b > a + 1 && nums[b - 1] == nums[b]) {
          continue;
        }
        int remainB = remainA - nums[b];
        int d = n - 1;
        for (int c = b + 1; c < n; c++) {
          if (c > b + 1 && nums[c - 1] == nums[c]) {
            continue;
          }
          while (c < d && nums[c] + nums[d] > remainB) {
            d--;
          }
          if (c == d) {
            break;
          }
          if (nums[c] + nums[d] == remainB) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[a]);
            list.add(nums[b]);
            list.add(nums[c]);
            list.add(nums[d]);
            ans.add(list);
          }
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    FourSum f = new FourSum();
    System.out.println(f.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    System.out.println(f.fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    System.out.println(f.fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
  }

}
