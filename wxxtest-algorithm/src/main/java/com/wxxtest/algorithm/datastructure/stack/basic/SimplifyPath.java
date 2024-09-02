package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/simplify-path/">71. 简化路径</a>
 */
public class SimplifyPath {

  /**
   * 1、使用"\"先将路径分割
   * 2、需要简化的场景处理
   */
  public String simplifyPath(String path) {
    String[] strs = path.split("/");
    Deque<String> deque = new ArrayDeque<>();
    for (String s : strs) {
      switch (s) {
        case "..":
          //跳过上一级
          if (!deque.isEmpty()) {
            deque.pollLast();
          }
          break;
        case ".", "":
          break;
        default://"..."或"digits"
          deque.offerLast(s);
          break;
      }
    }

    //如果队列为空说明没有子目录直接返回根目录
    if (deque.isEmpty()) {
      return "/";
    }

    StringBuilder sb = new StringBuilder();
    //拼接子目录
    while (!deque.isEmpty()) {
      sb.append("/");
      sb.append(deque.pollFirst());
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    SimplifyPath sp = new SimplifyPath();
    System.out.println(sp.simplifyPath("/home/"));
    System.out.println(sp.simplifyPath("/home//foo/"));
    System.out.println(sp.simplifyPath("/home/user/Documents/../Pictures"));
    System.out.println(sp.simplifyPath("/../"));
    System.out.println(sp.simplifyPath("/.../a/../b/c/../d/./"));
  }
}
