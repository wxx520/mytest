package com.wxxtest.rpc.registration.center.client.rpc.framework.ratelimiting;
/*
  rpc限流算法
  1、计数器限流算法：固定窗口计数，无法面处理时间窗口内流量激增的情况，以及跨窗口的流量超出
  2、滑动时间窗口限流算法：将窗口划分为更细粒度的单位，按照细粒度滑动，很大程度上缓解固定窗口的问题，但是不能完全避免，且实现复杂
  3、漏桶限流算法：无法应对一定程度的流量激增的情况
  4、令牌限流算法：漏桶的优化版本，可以应对一定程度的流量激增
  5、Sentinel系统自适应限流算法

详见文档
https://v0etqjz8nkv.feishu.cn/docx/Gv1udskIoo1y0JxKtbLchScLnrc
 */
