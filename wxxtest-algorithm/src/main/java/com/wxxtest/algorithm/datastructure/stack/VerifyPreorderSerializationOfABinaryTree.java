package com.wxxtest.algorithm.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/description/">331. 验证二叉树的前序序列化</a>
 */
public class VerifyPreorderSerializationOfABinaryTree {

  /**
   * 我们可以定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
   * 二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
   * 若遇到了空节点，则要消耗一个槽位；
   * 若遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
   * 此外，还需要将根节点作为特殊情况处理。
   * 时间复杂度O(N)，空间复杂度O(N)
   */
  public boolean isValidSerialization(String preorder) {
    int n = preorder.length();
    int i = 0;
    int slots = 1;
    while (i < n) {
      if (slots == 0) {
        return false;
      }
      if (',' == preorder.charAt(i)) {
        i++;
        continue;
      }
      if (preorder.charAt(i) == '#') {
        slots--;
        i++;
        continue;
      }
      while (i < n && preorder.charAt(i) != ',') {
        i++;
      }
      slots++;
    }
    return slots == 0;
  }

  /**
   * 我们可以定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
   * 二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
   * 若遇到了空节点，则要消耗一个槽位；
   * 若遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
   * 此外，还需要将根节点作为特殊情况处理。
   * 时间复杂度O(N)，空间复杂度O(1)
   */
  public boolean isValidSerialization1(String preorder) {
    int n = preorder.length();
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(1);
    int i = 0;
    while (i < n) {
      if (stack.isEmpty()) {
        return false;
      }
      if (',' == preorder.charAt(i)) {
        i++;
        continue;
      }
      if (preorder.charAt(i) == '#') {
        int top = stack.pop() - 1;
        if (top > 0) {
          stack.push(top);
        }
        i++;
        continue;
      }
      while (i < n && preorder.charAt(i) != ',') {
        i++;
      }
      int top = stack.pop() - 1;
      if (top > 0) {
        stack.push(top);
      }
      stack.push(2);
    }
    return stack.isEmpty();
  }

  public boolean isValidSerialization2(String preorder) {
    String[] pres = preorder.split(",");
    int slots = 1;
    for (String s : pres) {
      if (slots == 0) {
        return false;
      }
      if ("#".equals(s)) {
        slots--;
        continue;
      }
      slots++;
    }
    return slots == 0;
  }

  public static void main(String[] args) {
    VerifyPreorderSerializationOfABinaryTree v = new VerifyPreorderSerializationOfABinaryTree();
    System.out.println(v.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    System.out.println(v.isValidSerialization1("9,3,4,#,#,1,#,#,2,#,6,#,#"));

    System.out.println(v.isValidSerialization("1,#"));
    System.out.println(v.isValidSerialization("9,#,#,1"));
    System.out.println(v.isValidSerialization("9,#,#"));
    System.out.println(v.isValidSerialization1("9,#,#"));
    System.out.println(v.isValidSerialization2("9,#,#"));

  }
}
