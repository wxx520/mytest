package com.wxxtest.algorithm.sort.mergesort;
/**
 * 归并排序的思想：分而治之，将原始数据分组，排好序后再合并排序，
 *               不断重复这个过程，直到最后只剩下一组。
 *               两组合并时，需要使用额外的空间保存排好的数据，也是空间换时间。
 * 归并排序的主体结构为：
 * private void mergeSort(int[] arr, int left, int right) {
 *    if(left>=right){
 *      return;
 *    }
 *    int mid = left+(right-left)/2;
 *    mergeSort(arr,left,mid);
 *    mergeSort(arr,mid+1,right)
 *    mergeTwoArray(arr,left,mid,right)
 * }
 * private void mergeTwoSortedArray(int[] nums, int left, int right, int[] temp, int mid) {
 *     // 首先计算出这个数组的长度
 *     // 因为是左边闭区间，右边闭区间，所以元素的个数就是：右边界 - 左边界 + 1
 *     int length = right - left + 1;
 *     // 新建一个数组，赋值，用于比较
 *     // 这里每进行一次比较，都要 new 一个数组，开销很大
 *     int[] temp = new int[length];
 *     // 为新数组赋值
 *     System.arraycopy(arr,left,temp,0,length);
 *     // 左边数组的起始位置
 *     int i = left;
 *
 *     // 右边数组的起始位置
 *     int j = mid + 1;
 *
 *     int k = left;
 *     while (i <= mid && j <= right) {
 *       if (temp[i] <= temp[j]) {
 *         nums[k] = temp[i];
 *         k++;
 *         i++;
 *       } else {
 *         nums[k] = temp[j];
 *         k++;
 *         j++;
 *       }
 *     }
 *
 *     while (i <= mid) {
 *       nums[k] = temp[i];
 *       k++;
 *       i++;
 *     }
 *
 *     while (j <= right) {
 *       nums[k] = temp[j];
 *       k++;
 *       j++;
 *     }
 *   }
 *
 * 因此可优化方向有：
 * 1、一开始就创建一个和排序数组相同的临时数组，进行「合并两个有序数组」的操作，避免创建临时数组和销毁的消耗，避免计算下标偏移量。
 * 2、合并两个已经排好序的数组时，如果发现前一个的最大值不大于后一个的最小值，说明两个数组已经有序
 * 3、对于分组后长度不大于8(right - left <= INSERTION_SORT_THRESHOLD/7)的数组，可以使用插入排序提高效率
 *
 * 因此优化后的结构为
 * private void mergeSort(int[] arr, int left, int right, int[] temp) {
 *    if(right - left <= INSERTION_SORT_THRESHOLD){
 *      insertionSort(nums, left, right);
 *      return;
 *    }
 *    int mid = left+(right-left)/2;
 *    mergeSort(arr,left,mid);
 *    mergeSort(arr,mid+1,right)
 *
 *    //两个数组已经有序
 *    if(arr[mid]<=arr[mid+1]){
 *      return;
 *    }
 *    mergeTwoArray(arr,left,mid,right)
 * }
 *
 * private void mergeTwoSortedArray(int[] nums, int left, int right, int[] temp, int mid) {
 *     //节省每次合并创建临时数组的开销
 *     System.arraycopy(nums, left, temp, left, right - left + 1);
 *     ...
 *   }
 */