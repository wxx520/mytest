package com.wxxtest.algorithm.basics.loopinvariant;

import java.util.Arrays;

public class MoveZeroes {

  /**
   * 循环不变量：nums[0..i) != 0，nums[i..j) == 0
   * 其中[0,i)为非零元素，[0,j]为已经遍历的元素
   * 初始条件i=0,j=0,
   * 若发现
   * nums[j]!=0,则swap(i,j),i++,j++；
   * 否则nums[j]==0，则直接遍历下一个元素，即j++;
   * 当j=len-1时遍历结束
   */
  public void moveZeroes(int[] nums) {
    int len = nums.length;
    if (len <= 1) {
      return;
    }
    int i = 0;
    for (int j = 0; j < len; j++) {
      if (nums[j] != 0) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++;
      }
    }
  }

  public static void main(String[] args) {
    MoveZeroes mz = new MoveZeroes();
    int[] arr = new int[]{0, 1, 0, 3, 12};
    mz.moveZeroes(arr);
    System.out.println(Arrays.toString(arr));
  }
}
