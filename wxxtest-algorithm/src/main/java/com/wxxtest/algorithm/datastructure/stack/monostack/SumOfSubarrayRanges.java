package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/sum-of-subarray-ranges/?envType=problem-list-v2&envId=cIOgJFon">2104. 子数组范围和</a>
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * 返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列。
 */
public class SumOfSubarrayRanges {

  public long subArrayRanges(int[] nums) {
    int n = nums.length;

    //左边严格小于nums[i]最近的元素下标
    int[] minLeft = new int[n];
    //左边严格大于nums[i]的最近的元素下标
    int[] maxLeft = new int[n];

    Deque<Integer> minStack = new ArrayDeque<>();
    minStack.addLast(-1);
    Deque<Integer> maxStack = new ArrayDeque<>();
    maxStack.addLast(-1);
    for (int i = 0; i < n; i++) {
      int curNum = nums[i];

      while (minStack.size() > 1 && nums[minStack.peekLast()] >= curNum) {
        minStack.removeLast();
      }
      minLeft[i] = minStack.peekLast();
      minStack.addLast(i);

      while (maxStack.size() > 1 && nums[maxStack.peekLast()] <= curNum) {
        maxStack.removeLast();
      }
      maxLeft[i] = maxStack.peekLast();
      maxStack.addLast(i);
    }


    //nums[i]的右侧，不大于nums[i]最近的元素下标
    int[] minRight = new int[n];
    //nums[i]的右侧，不小于nums[i]最近的元素下标
    int[] maxRight = new int[n];
    minStack.clear();
    maxStack.clear();
    minStack.addLast(n);
    maxStack.addLast(n);
    for (int i = n - 1; i >= 0; i--) {
      int curNum = nums[i];

      while (minStack.size() > 1 && nums[minStack.peekLast()] > curNum) {
        minStack.removeLast();
      }
      minRight[i] = minStack.peekLast();
      minStack.addLast(i);

      while (maxStack.size() > 1 && nums[maxStack.peekLast()] < curNum) {
        maxStack.removeLast();
      }
      maxRight[i] = maxStack.peekLast();
      maxStack.addLast(i);
    }

    long ansMin = 0;
    long ansMax = 0;
    for (int i = 0; i < n; i++) {
      ansMin += (long) (i - minLeft[i]) * (minRight[i] - i) * nums[i];
      ansMax += (long) (i - maxLeft[i]) * (maxRight[i] - i) * nums[i];
    }
    return ansMax - ansMin;
  }

  /**
   * 时间复杂度为O(n^n)
   */
  public long subArrayRangesByBF(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return 0;
    }
    int min;
    int max;
    long ans = 0;
    for (int i = 0; i < n; i++) {
      min = max = nums[i];
      for (int j = i + 1; j < n; j++) {
        max = Math.max(max, nums[j]);
        min = Math.min(min, nums[j]);
        ans += (max - min);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    SumOfSubarrayRanges sum = new SumOfSubarrayRanges();
    System.out.println(sum.subArrayRanges(new int[]{1, 2, 3}));
    System.out.println(sum.subArrayRanges(new int[]{1, 3, 3}));
    System.out.println(sum.subArrayRanges(new int[]{4, -2, -3, 4, 1}));
    System.out.println(sum.subArrayRanges(new int[]{-37988, -14446, -34454, -85916, 44628, -63469, 2405, 76071, 43291, 499, -43933, -10950, 22587, 45756, 36078, 49794, 81866, -70327, 80649, 19025, 82130, -53646, 99394, 63520, 20667, 41291, 80388, -82451, -17666, 52744, -84498, 30104, 41847, 67932, -89959, -42134, -79079, 80796, -27089, 9691, -26248, -31934, -20681, 33506, 16422, -98706, -16321, 847, 55516, -85834, -3479, -58562, 77791, 62111, -15830, 33478, 79046, -47470, -54997, -56231, 11301, 3998, 73631, 47168, 66983, 98655, -31405, -11411, 50967, -15908, 37346, 73429, -95644, 83331, 74868, -23201, 70451, 73304, 38820, -32124, 80413, -23607, 65237, 88536, 29905, -35443, -36683, 64419, -25056, 73050, 17960, 16070, 54748, 76597, 74972, -73098, 74704, 55261, -38420, -42739, 15098, -8078, 82487, -34954, -38895, 39994, 35077, -36851, 87932, 7216, -87758, -27817, 66742, 77803, -16270, 41596, -14558, 28610, 4151, -2590, -73414, 56156, 93465, 31128, -19581, -44840, -87553, -79674, -2016, 3190, 62008}));


    System.out.println(sum.subArrayRangesByBF(new int[]{1, 2, 3}));
    System.out.println(sum.subArrayRangesByBF(new int[]{1, 3, 3}));
    System.out.println(sum.subArrayRangesByBF(new int[]{4, -2, -3, 4, 1}));
    System.out.println(sum.subArrayRangesByBF(new int[]{-37988, -14446, -34454, -85916, 44628, -63469, 2405, 76071, 43291, 499, -43933, -10950, 22587, 45756, 36078, 49794, 81866, -70327, 80649, 19025, 82130, -53646, 99394, 63520, 20667, 41291, 80388, -82451, -17666, 52744, -84498, 30104, 41847, 67932, -89959, -42134, -79079, 80796, -27089, 9691, -26248, -31934, -20681, 33506, 16422, -98706, -16321, 847, 55516, -85834, -3479, -58562, 77791, 62111, -15830, 33478, 79046, -47470, -54997, -56231, 11301, 3998, 73631, 47168, 66983, 98655, -31405, -11411, 50967, -15908, 37346, 73429, -95644, 83331, 74868, -23201, 70451, 73304, 38820, -32124, 80413, -23607, 65237, 88536, 29905, -35443, -36683, 64419, -25056, 73050, 17960, 16070, 54748, 76597, 74972, -73098, 74704, 55261, -38420, -42739, 15098, -8078, 82487, -34954, -38895, 39994, 35077, -36851, 87932, 7216, -87758, -27817, 66742, 77803, -16270, 41596, -14558, 28610, 4151, -2590, -73414, 56156, 93465, 31128, -19581, -44840, -87553, -79674, -2016, 3190, 62008}));
  }
}
