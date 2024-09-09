package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/trapping-rain-water/description/">42. 接雨水</a>
 */
public class TrappingRainWater {

  /**
   * 时间复杂度O(n),空间复杂度O(n)
   * <p>
   * 维护一个单调栈，单调栈存储的是下标，
   * 满足从栈底到栈顶的下标对应的数组 height 中的元素递减。
   * 从左到右遍历数组，遍历到下标i时，如果栈内至少有两个元素，
   * 记栈顶元素为top，top 的下面一个元素是 left，
   * 则一定有 height[left]≥height[top]。
   * 若height[i]>height[top],则得到一个可以接雨水的区域，
   * 该区域的宽度为i-left+1，高度为min(height[i],height[left])-height[top]
   * 根据高度和宽度可以得到接雨水的面积
   * 为了得到left，需要将top出栈。
   * 在对 top 计算能接的雨水量之后，left变成新的top，重复上述操作，
   * 直到栈变为空，或者栈顶下标对应的 height 中的元素大于或等于 height[i]。
   * <p>
   * <a href="https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/">详见</a>
   */
  public int trap(int[] height) {
    int n = height.length;
    if (n <= 2) {
      return 0;
    }
    Deque<Integer> monoStack = new ArrayDeque<>();
    int ans = 0;
    int curH;
    for (int i = 0; i < n; i++) {
      curH = height[i];
      while (!monoStack.isEmpty() && curH > height[monoStack.peekLast()]) {
        int top = monoStack.removeLast();
        if (monoStack.isEmpty()) {
          break;
        }
        int left = monoStack.peekLast();
        int curWidth = i - left - 1;
        int curHeight = Math.min(curH, height[left]) - height[top];
        ans += curWidth * curHeight;
      }
      monoStack.addLast(i);
    }
    return ans;
  }

  /**
   * 时间复杂度O(n),空间复杂度O(n)
   * <p>
   * 每根柱子的最大接雨量，为左右最大的柱子中最小的减去自身的高度
   * 则所有的柱子的接雨量即为总的接雨量
   * n = height.length
   * 使用暴力解法去找每个柱子的最大接雨量，每次查询的代价是n，有n根柱子
   * 则时间复杂度为O(n^2)
   * <p>
   * 使用动态规划，将找左右最大值的时间复杂度可以降低为O(n)
   * 定义left[i]表示下标 i 及其左边的位置中，height 的最大高度
   * 定义right[i]表示下标 i 及其右边的位置中，height 的最大高度
   * 则有，left[0] = height[0], right[n-1]=height[n-1]
   * left[i] = max(height[i], left[i-1]),1<=i<n;
   * right[j] = max(height[j], right[j+1]),0<=j<n-1
   * <p>
   * area = sum(min(left[k],right[k])-height[k]), 1=<k<=n-2
   * 注意k的取值范围：
   * 因为第一根柱子没有左边柱子，最后一根没有右边的柱子，不满足接雨水的条件
   */
  public int trapByDP(int[] height) {
    int n = height.length;
    if (n <= 2) {
      return 0;
    }
    int[] leftMax = new int[n];
    leftMax[0] = height[0];
    for (int i = 1; i < n; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], height[i]);
    }

    int[] rightMax = new int[n];
    rightMax[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], height[i]);
    }

    int ans = 0;
    for (int i = 1; i < n - 1; i++) {
      ans += (Math.min(leftMax[i], rightMax[i]) - height[i]);
    }
    return ans;
  }

  public int trapByDualPointers(int[] height) {
    int n = height.length;
    if (n <= 2) {
      return 0;
    }
    int left = 0;
    int right = n - 1;
    int leftMax = 0;
    int rightMax = 0;
    int ans = 0;
    while (left < right) {
      leftMax = Math.max(leftMax, height[left]);
      rightMax = Math.max(rightMax, height[right]);

      if (leftMax < rightMax) {
        //因为 area = min(leftMax[i],rightMax[i])-height[i]成立
        //因此当在确定height[i]左侧最大值leftMax的情况下，
        //若确定height[i]右边存在比leftMax时，即可确定
        //height[i]的接雨量，并不关心rightMax的具体值是什么
        //右侧同理
        ans += leftMax - height[left];
        left++;
      } else {
        //则表示height[right]的右边边最高点不大于其左边的最大值;
        //即min(leftMax[right],rightMax[right]) = rightMax[right]
        ans += rightMax - height[right];
        --right;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    TrappingRainWater water = new TrappingRainWater();
    System.out.println(water.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    System.out.println(water.trapByDualPointers(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    System.out.println(water.trapByDP(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    System.out.println(water.trap(new int[]{4, 2, 0, 3, 2, 5}));
    System.out.println(water.trapByDualPointers(new int[]{4, 2, 0, 3, 2, 5}));
    System.out.println(water.trapByDP(new int[]{4, 2, 0, 3, 2, 5}));
  }
}
