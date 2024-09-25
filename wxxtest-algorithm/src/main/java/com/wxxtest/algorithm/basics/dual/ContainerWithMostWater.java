package com.wxxtest.algorithm.basics.dual;

/**
 * <a href="https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-100-liked">11. 盛最多水的容器</a>
 */
public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int n = height.length;
    if (n == 2) {
      return Math.min(height[0], height[1]);
    }
    int ans = 0;
    int l = 0;
    int r = n - 1;
    while (l < r) {
      if (height[l] <= height[r]) {
        ans = Math.max(ans, (r - l) * height[l]);
        l++;
      } else {
        ans = Math.max(ans, (r - l) * height[r]);
        r--;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    ContainerWithMostWater c = new ContainerWithMostWater();
    System.out.println(c.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    System.out.println(c.maxArea(new int[]{1, 1}));
  }
}
