package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v2;

import java.net.HttpURLConnection;
import java.net.URL;

public class HeartBeatCheckService {

  public boolean isServiceAlive(String urlString) {
    HttpURLConnection connection = null;
    try {
      URL url = new URL(urlString);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000); // 设置连接超时时间
      connection.setReadTimeout(5000); // 设置读取超时时间
      int responseCode = connection.getResponseCode();

      // 判断服务是否运行正常（例如HTTP响应码200表示正常）
      return (responseCode == HttpURLConnection.HTTP_OK);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      if (connection != null) {
        connection.disconnect(); // 断开连接
      }
    }
  }

}
