package com.wxxtest.algorithm.datastructure.stack;

import java.util.List;

public class NestedInteger {

  private int num;
  private List<NestedInteger> list;

  public boolean isInteger() {
    return list == null || list.isEmpty();
  }

  public Integer getInteger() {
    return isInteger() ? num : null;
  }

  public List<NestedInteger> getList() {
    return list;
  }
}
