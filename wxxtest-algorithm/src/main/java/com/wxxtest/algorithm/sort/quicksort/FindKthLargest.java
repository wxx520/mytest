package com.wxxtest.algorithm.sort.quicksort;

import java.util.Random;

public class  FindKthLargest {

  private static final Random random = new Random(System.currentTimeMillis());

  public int findKthLargest(int[] nums, int k) {
    int len = nums.length;
    int left = 0;
    int right = len - 1;
    quickSortBy3Way(nums, left, right, len - k);
    return nums[len - k];
  }

  private void quickSortBy3Way(int[] nums, int left, int right, int target) {
    if (left >= right) {
      return;
    }
    int pivot = nums[left];
    int lt = left;
    int gt = right;
    int i = left + 1;
    //循环不变量
    //[left+1,lt]<pivot
    //(lt,i)=pivot
    //(gt,right]>pivot
    while (i <= gt) {
      if (nums[i] < pivot) {
        swap(nums, i++, ++lt);
      } else if (nums[i] == pivot) {
        i++;
      } else {
        //nums[i]>pivot
        swap(nums, i, gt--);
      }
    }
    //[left,lt-1]<pivot,[lt,i)=pivot,(gt,right]>pivot
    swap(nums, left, lt);
    if (target < lt) {
      quickSortBy3Way(nums, left, lt - 1, target);
    } else if (target >= i) {
      quickSortBy3Way(nums, i, right, target);
    }
    //否则target in [lt,i-1],这个区间段已经排好序
  }

  public int findKthLargest1(int[] nums, int k) {
    int len = nums.length;
    int target = len - k;
    int left = 0;
    int right = len - 1;
    while (true) {
      int pivotIndex = partition(nums, left, right);
      if (pivotIndex == target) {
        return nums[pivotIndex];
      } else if (pivotIndex > target) {
        right = pivotIndex - 1;
      } else {
        left = pivotIndex + 1;
      }
    }
  }

  /**
   * 找出pivot，左边比他小，右边不小于它
   */
  private int partition(int[] nums, int left, int right) {
    int randIndex = left + random.nextInt(right - left + 1);
    swap(nums, left, randIndex);
    int pivot = nums[left];
    //循环不变量
    // all in [left+1,lt]<pivot
    //all int [lt,i)>=pivot
    int lt = left;
    for (int i = left + 1; i <= right; i++) {
      if (nums[i] < pivot) {
        // j 的初值为 left，先右移，再交换，小于 pivot 的元素都被交换到前面
        swap(nums, ++lt, i);
      }
    }
    //交换后[left,lt)<=pivot,[lt,right]>pivot;
    swap(nums, left, lt);
    return lt;
  }

  private void swap(int[] nums, int j, int i) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    FindKthLargest fk = new FindKthLargest();
    System.out.println(fk.findKthLargest(
            new int[]{3, 2, 1, 5, 6, 4},
            2
    ));
    System.out.println(fk.findKthLargest1(
            new int[]{3, 2, 1, 5, 6, 4},
            2
    ));
    System.out.println('z'-'A');
  }
}
