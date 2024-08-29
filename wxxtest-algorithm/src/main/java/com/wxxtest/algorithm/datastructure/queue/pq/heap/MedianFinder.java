package com.wxxtest.algorithm.datastructure.queue.pq.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

  //最大堆，小于等于中位数
  PriorityQueue<Integer> queueMin;

  //最小堆，大于中位数
  PriorityQueue<Integer> queueMax;

  public MedianFinder() {
    queueMin = new PriorityQueue<>(Comparator.reverseOrder());
    queueMax = new PriorityQueue<>();
  }

  /**
   * 始终保证：
   * 1、0<=queueMin.size-queueMax.size<=1
   * 2、queMin.peek()<=queMax.peek();
   */
  public void addNum(int num) {
    if (queueMin.isEmpty()||num<=queueMin.peek()) {
      queueMin.offer(num);
      if(queueMin.size()-queueMax.size()>1){
        queueMax.offer(queueMin.poll());
      }
      return;
    }
    queueMax.offer(num);
    if(queueMax.size()>queueMin.size()){
      queueMin.offer(queueMax.poll());
    }
  }

  public double findMedian() {
    if (queueMin.size() > queueMax.size()) {
      return queueMin.peek();
    } else {
      return (queueMin.peek() + queueMax.peek()) / 2.0;
    }
  }

  public static void main(String[] args) {
    MedianFinder mf = new MedianFinder();
    mf.addNum(1);
    mf.addNum(2);
    System.out.println(mf.findMedian());
    mf.addNum(3);
    System.out.println(mf.findMedian());
  }
}
