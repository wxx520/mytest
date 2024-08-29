package com.wxxtest.algorithm.sort.quicksort;

import java.util.Arrays;

/**
 * 三路快排，把大量重复的元素放在中间
 */
public class SortColors {
  public void sortColors(int[] nums) {
    //循环不变量
    // [0,red)为红色
    //[red,i)为白色
    //(blue,len]为蓝色
    int len = nums.length;
    int red = 0;
    int i = 0;
    int blue = len - 1;
    while (i <= blue) {
      int curC = nums[i];
      if (curC == 0) {
        swap(nums, i, red);
        i++;
        red++;
      } else if (curC == 1) {
        i++;
      } else {
        swap(nums, blue, i);
        blue--;
      }
    }
  }

  private void swap(int[] nums, int j, int i) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    SortColors sc = new SortColors();
    int[] c1 = new int[]{2, 0, 2, 1, 1, 0};
    sc.sortColors(c1);
    System.out.println(Arrays.toString(c1));
  }

}
