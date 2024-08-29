package com.wxxtest.algorithm.sort.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * 使用快速排序对数组排序
 */
public class SortArrayByQS {

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
   * 快速排序，
   * 主要思路：每次选出一个元素所切分，使得左边的元素不大于它，右边的元素比他大
   * 然后对两边的元素递归这个规程，直到所有元素都满足这个性质完成排序
   * 优化点：
   * 1、切分点的选择：
   * 每次原则第一个元素做切分，对于顺序数组或者逆序数组，每次只能切分一个
   * 会让树高回退到链表的长度，影响算法效果，随机选择pivot，破坏顺序性
   */
  private void quickSort(int[] nums, int left, int right) {
    if (right - left <= INSERTION_SORT_THRESHOLD) {
      insertionSort(nums, left, right);
      return;
    }
    int pivotIndex = partition(nums, left, right);
    quickSort(nums, left, pivotIndex - 1);
    quickSort(nums, pivotIndex + 1, right);
  }

  /**
   * 选出一个元素，使得元素左边的小于它，元素的右边大于它，返回元素所在的位置
   */
  private int partition(int[] nums, int left, int right) {
    //[left,right]
    int randomIndex = left + random.nextInt(right - left + 1);
    swap(nums, left, randomIndex);
    int pivot = nums[left];
    int j = left;
    //循环不变量:
    // all in（left,j]<=pivot,
    // all in (j,i)>pivot
    for (int i = left + 1; i <= right; i++) {
      if (nums[i] <= pivot) {
        j++;
        swap(nums, j, i);
      }
    }
    //交换后使得all in [left,j]<=pivot,(j,right]>pivot;
    swap(nums, left, j);
    return j;
  }

  private int partitionByTwoWay(int[] nums, int left, int right) {
    //[left,right]
    int randomIndex = left + random.nextInt(right - left + 1);
    swap(nums, left, randomIndex);
    int pivot = nums[left];
    int lt = left + 1;
    int gt = right;
    //循环不变量:
    // all in（left,lt)<=pivot,
    // all in (gt,right]>=pivot
    while (true) {
      while (lt <= right && nums[lt] < pivot) {
        lt++;
      }
      while (gt > left && nums[gt] > pivot) {
        gt--;
      }
      //le来到第一个大于等于pivot的位置
      //ge来到第一个小于等于pivot的位置
      if (lt >= gt) {
        break;
      }
      // 细节：相等的元素通过交换，等概率分到数组的两边
      swap(nums, gt, lt);
      gt--;
      lt++;
    }
    //交换后使得all in [left,ge]<=pivot,(ge,right]>=pivot;
    swap(nums, left, gt);
    return gt;
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
    SortArrayByQS ms = new SortArrayByQS();
    System.out.println(Arrays.toString(ms.sortArray(
            new int[]{5, 2, 3, 1}
    )));
  }
}
