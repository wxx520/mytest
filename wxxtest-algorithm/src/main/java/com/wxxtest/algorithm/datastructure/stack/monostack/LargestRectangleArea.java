package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/">84. 柱状图中最大的矩形</a>
 */
public class LargestRectangleArea {

  /**
   * 对于每一个高度h[i]，
   * 左边连续不小于自己高度的个数(包括自己)leftSpanner[i]
   * 右边连续不小于自己高度的个数(包括自己)rightSpanner[i]
   * 则对于当前高度能组成的最大面积为
   * area[i] = h[i]*(leftSpanner[i]+rightSpanner[i]-1);
   * 所有高度能组成的最大面积为max(area[i])
   */
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return heights[0];
    }
    Deque<Integer> leftStack = new ArrayDeque<>();
    int[] leftSpanner = new int[n];
    int curH;
    int curS;
    for (int i = 0; i < n; i++) {
      curH = heights[i];
      curS = 1;
      while (!leftStack.isEmpty() && heights[leftStack.peek()] >= curH) {
        curS += leftSpanner[leftStack.pop()];
      }
      leftSpanner[i] = curS;
      leftStack.push(i);
    }

    Deque<Integer> rightStack = new ArrayDeque<>();
    int[] rightSpanner = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      curH = heights[i];
      curS = 1;
      while (!rightStack.isEmpty() && heights[rightStack.peek()] >= curH) {
        curS += rightSpanner[rightStack.pop()];
      }
      rightSpanner[i] = curS;
      rightStack.push(i);
    }

    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      maxArea = Math.max(maxArea, heights[i] * (leftSpanner[i] + rightSpanner[i] - 1));
    }
    return maxArea;
  }

  public int largestRectangleAreaByOneStack(int[] heights) {
    int n = heights.length;
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return heights[0];
    }

    //首位加上哨兵，免去栈的非空判断，和最后一个元素的边界处理
    int len = n + 2;
    int[] newHeights = new int[len];
    System.arraycopy(heights, 0, newHeights, 1, n);
    heights = newHeights;

    //heights[monoStack.pop()]非严格单调递增
    Deque<Integer> monoStack = new ArrayDeque<>();
    monoStack.addLast(0);
    int area = 0;
    int curH;
    int height;
    int width;
    for (int i = 1; i < len; i++) {
      curH = heights[i];
      //若当前高度比栈顶小，则栈顶组成的最大面积就只能向左看了
      //表示当前栈顶的最大面积可以确定了
      while (heights[monoStack.peekLast()] > curH) {
        height = heights[monoStack.removeLast()];
        //单调栈，上一个栈顶对应的高度一定不会大于当前栈顶
        width = i - monoStack.peekLast() - 1;
        area = Math.max(area, height * width);
      }
      monoStack.addLast(i);
    }

    return area;
  }

  public static void main(String[] args) {
    LargestRectangleArea lr = new LargestRectangleArea();
    System.out.println(lr.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    System.out.println(lr.largestRectangleAreaByOneStack(new int[]{2, 1, 5, 6, 2, 3}));
    System.out.println(lr.largestRectangleArea(new int[]{2, 4}));
    System.out.println(lr.largestRectangleAreaByOneStack(new int[]{2, 4}));
    System.out.println(lr.largestRectangleArea(new int[]{999, 999, 999, 999}));
    System.out.println(lr.largestRectangleAreaByOneStack(new int[]{999, 999, 999, 999}));

  }
}
