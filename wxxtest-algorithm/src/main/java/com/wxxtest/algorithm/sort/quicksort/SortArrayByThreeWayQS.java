package com.wxxtest.algorithm.sort.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * 使用三路快排快速排序对数组排序
 */
public class SortArrayByThreeWayQS {

  /**
   * 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
   */
  private static final int INSERTION_SORT_THRESHOLD = 7;

  private static final Random random = new Random(System.currentTimeMillis());

  public int[] sortArray(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
    return nums;
  }

  /**
   * 使用三路快排是为了避免下面这种情况：
   * 「切分」的时候有大量元素的值与 pivot 的值相同。
   * 「三路快排」把与 pivot 相同的元素划分到了未排定部分的「中间」。
   */
  private void quickSort(int[] nums, int left, int right) {
    if (right - left <= INSERTION_SORT_THRESHOLD) {
      insertionSort(nums, left, right);
      return;
    }
    int randomIndex = left + random.nextInt(right - left + 1);
    swap(nums, left, randomIndex);
    int pivot = nums[left];
    int lt = left;
    int gt = right;
    int i = left + 1;
    //循环不变量的定义
    //all in [left+1,lt)<pivot;
    //all in [lt,i)=pivot
    //all in (ge,right]>pivot
    while (i <= gt) {
      if (nums[i] < pivot) {
        lt++;
        swap(nums, lt, i);
        i++;
      } else if (nums[i] == pivot) {
        i++;
      } else {
        // nums[i] > pivot
        //i的值为nums[gt],还未比较，因此i不变
        swap(nums, gt, i);
        gt--;
      }
    }
    //交换后[left,lt-1]<pivot,[lt,i)=pivot,(gt,right]>pivot;
    swap(nums,left,lt);

    quickSort(nums,left,lt-1);
    quickSort(nums,gt+1,right);
  }


  private void swap(int[] nums, int j, int i) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void insertionSort(int[] arr, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
      int temp = arr[i];
      int j = i;
      while (j > left && arr[j - 1] > temp) {
        arr[j] = arr[j - 1];
        j--;
      }
      arr[j] = temp;
    }
  }

  public static void main(String[] args) {
    SortArrayByThreeWayQS ms = new SortArrayByThreeWayQS();
    System.out.println(Arrays.toString(ms.sortArray(
            new int[]{5, 2, 3, 1}
    )));
  }
}
