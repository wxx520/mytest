package com.wxxtest.rpc.framework.registry.v3.exception;

/**
 * TODO 异常定义
 */
public class BorrowNettyClientException extends RuntimeException {
  public BorrowNettyClientException(Exception e) {
    addSuppressed(e);
  }
}
