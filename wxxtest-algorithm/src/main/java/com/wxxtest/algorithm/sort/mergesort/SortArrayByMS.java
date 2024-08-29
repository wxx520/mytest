package com.wxxtest.algorithm.sort.mergesort;

import java.util.Arrays;

/**
 * 使用归并排序对数组排序
 */
public class SortArrayByMS {

  /**
   * 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
   */
  private static final int INSERTION_SORT_THRESHOLD = 7;

  /**
   * 我们对于归并排序的定义是左闭右闭的，所以第 3 个参数应该使用数组的长度 -1
   */
  public int[] sortArray(int[] nums) {
    int len = nums.length;

    //全程使用一份临时数组进行「合并两个有序数组」的操作，避免创建临时数组和销毁的消耗，避免计算下标偏移量。
    int[] temp = new int[len];

    mergeSort(nums, 0, len - 1, temp);
    return nums;
  }

  /**
   * @param nums  原始数组
   * @param left  需要排序的起点
   * @param right 需要排序的重点
   */
  private void mergeSort(int[] nums, int left, int right, int[] temp) {
    if (right - left <= INSERTION_SORT_THRESHOLD) {
      //长度小于等于8的数组，使用插入排序提高效率
      insertionSort(nums, left, right);
      return;
    }
    // 使用一分为二的思路，一直递归下去
    int mid = left + (right - left) / 2;
    mergeSort(nums, left, mid, temp);
    mergeSort(nums, mid + 1, right, temp);

    //两个数组已经有序
    if (nums[mid] <= nums[mid + 1]) {
      return;
    }

    // 下面这几行代码关于边界值的处理要特别小心，要紧扣自己定义的变量的含义进行逻辑的编写
    mergeTwoSortedArray(nums, left, right, temp, mid);
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


  /**
   * 对两个有序数组[left,mid],[mid+1,right]进行合并排序；
   * 将原始数组保存在长度为right-left+1的数组temp中
   * 循环不变量的定义为：
   * 从左到右分别遍历两个数组，起始为i=left,j=mid+1；
   * 不断选出较小的元素从头放入nums中，保证nums[left,index]始终有序
   */
  private void mergeTwoSortedArray(int[] nums, int left, int right, int[] temp, int mid) {
    System.arraycopy(nums, left, temp, left, right - left + 1);
    // 左边数组的起始位置
    int i = left;

    // 右边数组的起始位置
    int j = mid + 1;

    int k = left;
    while (i <= mid && j <= right) {
      if (temp[i] <= temp[j]) {
        nums[k] = temp[i];
        k++;
        i++;
      } else {
        nums[k] = temp[j];
        k++;
        j++;
      }
    }

    while (i <= mid) {
      nums[k] = temp[i];
      k++;
      i++;
    }

    while (j <= right) {
      nums[k] = temp[j];
      k++;
      j++;
    }
  }

  public static void main(String[] args) {
    SortArrayByMS ms = new SortArrayByMS();
    System.out.println(Arrays.toString(ms.sortArray(
            new int[]{5, 2, 3, 1}
    )));
  }
}
