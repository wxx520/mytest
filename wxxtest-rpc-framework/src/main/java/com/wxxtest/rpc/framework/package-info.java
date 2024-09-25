package com.wxxtest.rpc.registration.center.client.rpc.framework;
/**
 * 1、核心就是让用户能像调用本地服务一样调用远程服务
 * 2、https://v0etqjz8nkv.feishu.cn/docx/TnJRdcHQJoT0e2xs03uc7LpMnJc?from=from_copylink
 *
 * 目标：
 * 对所有的rpc服务如下操作统一处理：
 * 1、请求对象/响应对象的序列化反序列化
 * 2、远程调用
 *    1、网络连接管理
 *    2、请求响应的发送与接收
 *
 * 实现：
 * 1、通过代理对象实现
 * 2、项目启动时对指定(可以先代码写死)的对象生成代理对象
 *     在代理对象中指定代理方法的逻辑
 *     2.1 网络连接网络
 *     2.2 发送请求/接收请求
 *     2.3 超时管理
 * 3、在方法调用时，
 *      通过反射调用代理对象的方法
 *      完成方法调用并返回结果
 *
 */