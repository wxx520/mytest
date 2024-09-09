package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/description/?envType=problem-list-v2&envId=00n6fGiz">581. 最短无序连续子数组</a>
 */
public class ShortestUnsortedContinuousSubarray {

  public int findUnsortedSubarray(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return 0;
    }
    //从左到右找出第一个右边有比自己小的元素的下标
    Deque<Integer> incMonoStack = new ArrayDeque<>();
    int left = n - 1;
    for (int i = 0; i < n; i++) {
      int curNum = nums[i];
      while (!incMonoStack.isEmpty() && nums[incMonoStack.peekLast()] > curNum) {
        left = Math.min(left, incMonoStack.removeLast());
      }
      incMonoStack.addLast(i);
    }

    //说明最小的元素在最后一个
    if (incMonoStack.size() == 1) {
      return n;
    }
    //说明不存在右边比左边大的情况
    if (incMonoStack.size() == n) {
      return 0;
    }
    Deque<Integer> descMonoStack = new ArrayDeque<>();
    int right = -1;
    //从右到左找出第一个左边存在比自己大的元素的下标
    for (int i = n - 1; i >= 0; i--) {
      int curNum = nums[i];
      while (!descMonoStack.isEmpty() && nums[descMonoStack.peekLast()] < curNum) {
        right = Math.max(right, descMonoStack.removeLast());
      }
      descMonoStack.addLast(i);
    }
    return right - left + 1;
  }

  public int findUnsortedSubarrayByDualP(int[] nums) {
    int n = nums.length;
    if (n < 2) {
      return 0;
    }
    int rightBound = -1;
    int maxNum = Integer.MIN_VALUE;
    int curNum;

    //第一次遍历找出右边界 rightBound：关注自己的左边，如果存在数值比自己大，就记录自己的下标，所以遍历找最大值；
    for (int i = 0; i < n; i++) {
      curNum = nums[i];
      maxNum = Math.max(maxNum, curNum);
      if (curNum < maxNum) {
        rightBound = i;
      }
    }

    int leftBound = -1;
    int minNum = Integer.MAX_VALUE;
    //第二次遍历找出左边界 leftBound：关注自己的右边，如果存在数值比自己小，就记录自己的下标，所以遍历找最小值；
    for (int i = n - 1; i >= 0; i--) {
      curNum = nums[i];
      minNum = Math.min(curNum, minNum);
      if (curNum > minNum) {
        leftBound = i;
      }
    }

    if (leftBound >= rightBound) {
      return 0;
    }
    return rightBound - leftBound + 1;
  }


  public static void main(String[] args) {
    ShortestUnsortedContinuousSubarray s = new ShortestUnsortedContinuousSubarray();
    System.out.println(s.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    System.out.println(s.findUnsortedSubarrayByDualP(new int[]{2, 6, 4, 8, 10, 9, 15}));

    System.out.println(s.findUnsortedSubarray(new int[]{1, 2, 3, 4}));
    System.out.println(s.findUnsortedSubarray(new int[]{1}));
  }
}
