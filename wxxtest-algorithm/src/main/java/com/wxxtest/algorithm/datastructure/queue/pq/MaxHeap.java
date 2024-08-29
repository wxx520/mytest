package com.wxxtest.algorithm.datastructure.queue.pq;

/**
 * 为了方便父子关系在数组下标的关系更加直观，数组下标0不存储数据
 */
public class MaxHeap {

  private final int[] data;
  // 当前堆中存储的元素的个数
  private int count;

  // 堆中能够存储的元素的最大数量（为简化问题，不考虑动态扩展）
  private final int capacity;

  // 初始化最大堆
  public MaxHeap(int capacity) {
    // 初始化底层数组元素（ 0 号下标位置不存数据，这是为了使得通过父结点获得左右孩子有更好的表达式）
    this.capacity = capacity;
    data = new int[capacity + 1];
    count = 0;
  }

  public MaxHeap(int[] arr) {
    capacity = count = arr.length;
    data = new int[capacity + 1];
    System.arraycopy(arr, 0, data, 1, capacity);
    for (int i = capacity / 2; i >= 1; i--) {
      siftDown(i);
    }
  }

  public void insert(int item) {
    //堆已满
    if (count == capacity) {
      return;
    }
    // 把新添加的元素放在数组的最后一位，对应于最大堆最后一个叶子结点
    data[++count] = item;

    // 考虑将它上移,保持最大堆的性质，根节点不小于其叶子节点
    siftUp(count);
  }

  private void siftUp(int k) {
    int temp = data[k];
    // 有下标就要考虑下标越界的情况，已经在下标 1 的位置，就没有必要上移了
    while (k > 1 && data[k / 2] < data[k]) {
      data[k] = data[k / 2];
      k = k / 2;
    }
    data[k] = temp;
  }

  /**
   * 取出最大堆中的根结点
   * 1、把最后一个元素和下标是 1 的元素进行交换
   * 2、从根结点开始逐层下移：下移的过程中将与左右孩子结点进行比较，把最大的那个跟自己交换
   *
   * @return 根结点的元素
   */
  public int extractMax() {
    if (count == 0) {
      return -1;
    }
    int ret = data[1];
    swap(data, 1, count);
    count--;
    siftDown(1);
    return ret;
  }

  /**
   * 只要有左右孩子，左右孩子只要比自己大，就交换
   */
  private void siftDown(int h) {
    // 如果这个元素有左边的孩子
    while (2 * h <= count) {
      int k = 2 * h;
      // 如果有右边的孩子，并且还大于左边的孩子，就好像左边的孩子不存在一样
      if (k + 1 <= count && data[k + 1] > data[k]) {
        k = k + 1;
      }
      //和最大的孩子比，如果不小于最大的孩子，则表示根节点不小于子节点的性质以满足
      if (data[h] >= data[k]) {
        break;
      }
      swap(data, h, k);
      h = k;
    }
  }

  private void swap(int[] data, int index1, int index2) {
    int temp = data[index1];
    data[index1] = data[index2];
    data[index2] = temp;
  }

  // 返回堆中的元素个数
  public int size() {
    return count;
  }

  // 返回一个布尔值，返回堆中是否为空
  public boolean isEmpty() {
    return count == 0;
  }

  public static void main(String[] args) {
    MaxHeap mh = new MaxHeap(100);
    for (int i = 1; i < 50; i++) {
      mh.insert(i);
    }
    for (int i = 0; i < 50; i++) {
      System.out.println(mh.extractMax());
    }
  }
}
