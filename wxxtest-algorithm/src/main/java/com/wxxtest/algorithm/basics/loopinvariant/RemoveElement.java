package com.wxxtest.algorithm.basics.loopinvariant;

public class RemoveElement {

  /**
   * 循环不变量[0,i)!=val
   */
  public int removeElement(int[] nums, int val) {
    int len = nums.length;
    if (len == 0) {
      return 0;
    }
    int i = 0;
    for (int j = 0; j < len; j++) {
      if (nums[j] != val) {
        nums[i++] = nums[j];
      }
    }
    return i;
  }

  public static void main(String[] args) {
    RemoveElement re = new RemoveElement();
    System.out.println(re.removeElement(new int[]{3,2,2,3},3));
  }
}
