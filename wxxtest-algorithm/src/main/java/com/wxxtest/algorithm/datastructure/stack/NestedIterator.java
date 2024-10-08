package com.wxxtest.algorithm.datastructure.stack;

import java.util.*;

/**
 * TODO
 * <a href="https://leetcode.cn/problems/flatten-nested-list-iterator/description/">341. 扁平化嵌套列表迭代器</a>
 */
public class NestedIterator implements Iterator<Integer> {
    // 存储列表的当前遍历位置
    private final Deque<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
      stack = new LinkedList<>();
      stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
      // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
      return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
      while (!stack.isEmpty()) {
        Iterator<NestedInteger> it = stack.peek();
        if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
          stack.pop();
          continue;
        }
        // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
        NestedInteger nest = it.next();
        if (nest.isInteger()) {
          List<NestedInteger> list = new ArrayList<>();
          list.add(nest);
          stack.push(list.iterator());
          return true;
        }
        stack.push(nest.getList().iterator());
      }
      return false;
    }
}

