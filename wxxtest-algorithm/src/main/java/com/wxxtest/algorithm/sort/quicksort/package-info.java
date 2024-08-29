package com.wxxtest.algorithm.sort.quicksort;
/**
 * 快速排序也是分而治之的算法思想，相较于归并没有明显的分合的过程
 * 每次会先选出一个分割点元素pivot去切分元素，使得
 * 左边的比他小，右边的比他大，
 * 不断进行这个过程(这个过程会不断减小问题的规模，所以也叫减而治之)，
 * 直到所有的元素都满足这个性质，就完成了全部元素的排序
 * <p>
 * 快排的优点：
 * 1、算法本身的复杂度优秀O(NlgN)
 * 2、对任何特点的数据的排序，时间复杂度基本稳定
 *
 * 快排算法的结构
 * private void quickSort(int[] nums, int left, int right) {
 *         if (left >= right) {
 *             return;
 *         }
 *
 *         int pivotIndex = partition(nums, left, right);
 *         quickSort(nums, left, pivotIndex - 1);
 *         quickSort(nums, pivotIndex + 1, right);
 *}
 * private int partition(int[] nums, int left, int right) {
 *         int pivot = nums[left];
 *
 *         int j = left;
 *         // all in nums[left + 1..j] <= pivot
 *         // all in nums(j..i) > pivot
 *         for (int i = left + 1; i <= right; i++){
 *             if (nums[i] <= pivot) {
 *                 j++;
 *                 swap(nums, i, j);
 *             }
 *         }
 *         swap(nums, left, j);
 *         return j;
 *     }
 *
 * <p>
 * 优化点：
 * 1、锚点选择的优化：随机选择锚点破坏原始数据的有序性(顺序、逆序、接近有序)
 * 防止对于逆序或顺序数组，每次选择第一个，会让问题的规模
 * 每次只少1，树严重倾斜，树高回退到链表的长度n，算法会回退到n方
 * 2、当重复数据较多时：避免重复排序
 * 2.1选择双路快排（left,le]<=pivot,（ge,right]>=pivot
 *    注意将相同的元素均匀的分两两边
 * 2.2三路快排，(left,lt]<pivot,(lt,e]=pivot,(gt,right]>pivot
 *    使用三路快排是为了避免下面这种情况：
 *    「切分」的时候有大量元素的值与 pivot 的值相同。
 *      「三路快排」把与 pivot 相同的元素划分到了未排定部分的「中间」。
 */