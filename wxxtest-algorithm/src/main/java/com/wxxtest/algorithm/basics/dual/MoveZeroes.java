package com.wxxtest.algorithm.basics.dual;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked">283. 移动零</a>
 */
public class MoveZeroes {

  public void moveZeroes(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return;
    }
    //[0,i)为非0元素，[0,j)为已经已经遍历的元素
    int i = 0;
    int temp;
    for (int j = 0; j < n; j++) {
      if (nums[j] != 0) {
        temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
        i++;
      }
    }
  }

  public static void main(String[] args) {
    MoveZeroes mz = new MoveZeroes();
    int[] n1 = new int[]{0, 1, 0, 3, 12};
    mz.moveZeroes(n1);
    System.out.println(Arrays.toString(n1));

    int[] n2 = new int[]{0};
    mz.moveZeroes(n2);
    System.out.println(Arrays.toString(n2));
  }
}
