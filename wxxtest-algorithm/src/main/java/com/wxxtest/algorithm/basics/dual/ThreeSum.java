package com.wxxtest.algorithm.basics.dual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked">15. 三数之和</a>
 */
public class ThreeSum {

  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    for (int first = 0; first < n; first++) {
      // 需要和上一次枚举的数不相同
      if ((nums[first] > 0) || (first > 0 && nums[first] == nums[first - 1])) {
        continue;
      }
      // c 对应的指针初始指向数组的最右端
      int third = n - 1;
      //将问题拆分为两数之和
      int target = -nums[first];
      for (int second = first + 1; second < n; second++) {
        // 需要和上一次枚举的数不相同
        if (second > first + 1 && nums[second] == nums[second - 1]) {
          continue;
        }

        // 需要保证 b 的指针在 c 的指针的左侧
        while (second < third && nums[second] + nums[third] > target) {
          --third;
        }

        // 如果指针重合，随着 b 后续的增加
        // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
        if (second == third) {
          break;
        }

        if (nums[second] + nums[third] == target) {
          List<Integer> list = new ArrayList<>();
          list.add(nums[first]);
          list.add(nums[second]);
          list.add(nums[third]);
          ans.add(list);
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    ThreeSum ts = new ThreeSum();
    System.out.println(ts.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    System.out.println(ts.threeSum(new int[]{0, 1, 1}));
    System.out.println(ts.threeSum(new int[]{0, 0, 0}));

    System.out.println(ts.threeSum(new int[]{0, 0, 0, 0}));
  }
}
