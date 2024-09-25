package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3;
/**
 * 使用NIO实现单个线程创建多个连接
 * 1、创建连接时不能阻塞当前线程
 * 2、数据到达连接，当前线程如何获取
 * nio实现单个线程创建多个链接
 */