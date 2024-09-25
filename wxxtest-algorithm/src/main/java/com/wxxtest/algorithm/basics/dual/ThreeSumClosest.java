package com.wxxtest.algorithm.basics.dual;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/3sum-closest/description/">16. 最接近的三数之和</a>
 */
public class ThreeSumClosest {
  public int threeSumClosest(int[] nums, int target) {
    int n = nums.length;
    if (n == 3) {
      return get3Sum(0, nums);
    }
    Arrays.sort(nums);

    if (nums[0] >= target) {
      return get3Sum(0, nums);
    }
    if ((target <= 0 && nums[n - 1] <= target) || (target >= 0 && target >= get3Sum(n - 3, nums))) {
      return get3Sum(n - 3, nums);
    }

    int best = get3Sum(0, nums);

    //枚举a
    for (int first = 0; first < n - 2; first++) {
      // 保证和上一次枚举的元素不相等
      if (first > 0 && nums[first] == nums[first - 1]) {
        continue;
      }
      //使用双指针枚举b、c
      int second = first + 1;
      int third = n - 1;
      while (second < third) {
        int sum = nums[first] + nums[second] + nums[third];

        // 如果和为 target 直接返回答案
        if (sum == target) {
          return target;
        }
        // 根据差值的绝对值来更新答案
        if (Math.abs(sum - target) < Math.abs(best - target)) {
          best = sum;
        }

        if (sum > target) {
          // 如果和大于 target，移动 c 对应的指针
          int c0 = third - 1;
          // 移动到下一个不相等的元素
          while (second < c0 && nums[c0] == nums[third]) {
            --c0;
          }
          third = c0;
        } else {
          // 如果和小于 target，移动 b 对应的指针
          int b0 = second + 1;
          // 移动到下一个不相等的元素
          while (b0 < third && nums[b0] == nums[second]) {
            ++b0;
          }
          second = b0;
        }
      }
    }
    return best;
  }

  private int get3Sum(int firstIndex, int[] nums) {
    return nums[firstIndex] + nums[firstIndex + 1] + nums[firstIndex + 2];
  }

  public static void main(String[] args) {
    ThreeSumClosest t = new ThreeSumClosest();
    System.out.println(t.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    System.out.println(t.threeSumClosest(new int[]{0, 0, 0}, 1));
  }

}
