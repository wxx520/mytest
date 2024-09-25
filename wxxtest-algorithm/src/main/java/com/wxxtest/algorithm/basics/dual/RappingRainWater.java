package com.wxxtest.algorithm.basics.dual;

public class RappingRainWater {

  /**
   * height[i]能接的雨水为
   * min[leftMax,rightMax] - height[i]
   */
  public int trap(int[] height) {
    int n = height.length;
    if (n <= 2) {
      return 0;
    }
    int ans = 0;
    int left = 0;
    int right = n - 1;
    int leftMax = 0;
    int rightMax = 0;
    while (left < right) {
      leftMax = Math.max(leftMax, height[left]);
      rightMax = Math.max(rightMax, height[right]);
      if (leftMax <= rightMax) {
        ans += leftMax - height[left];
        left++;
      } else {
        ans += rightMax - height[right];
        right--;
      }

    }
    return ans;
  }

  /**
   * 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，
   * 下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]。
   */
  public int trapBDP(int[] height) {
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
      rightMax[i] = Math.max(height[i], rightMax[i + 1]);
    }

    int ans = 0;
    for (int i = 0; i < n; i++) {
      ans += Math.min(leftMax[i], rightMax[i]) - height[i];
    }
    return ans;
  }

  public static void main(String[] args) {
    RappingRainWater r = new RappingRainWater();
    System.out.println(r.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    System.out.println(r.trap(new int[]{4, 2, 0, 3, 2, 5}));

    System.out.println(r.trapBDP(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    System.out.println(r.trapBDP(new int[]{4, 2, 0, 3, 2, 5}));
  }
}
