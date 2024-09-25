package com.wxxtest.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * socket程序写完
 * 转化有问题就先按照简单的写
 * DataOutputStream 怎么实现写不同类型的数据
 * 搞清楚后socket能做对象的序列化和反序列化
 */
public class SocketClient {
  static void connect() {
    MySocketRequest request = new MySocketRequest(222, "雀后星", "打麻将吗");
    try(Socket client = new Socket("0.0.0.0", 2666)) {
      DataOutputStream dos = new DataOutputStream(client.getOutputStream());
      dos.writeInt(request.getAge());
      byte[] nameBytes = request.getName().getBytes(StandardCharsets.UTF_8);
      byte[] msgBytes = request.getMsg().getBytes(StandardCharsets.UTF_8);
      dos.writeInt(nameBytes.length);
      dos.write(nameBytes, 0, nameBytes.length);
      dos.writeInt(msgBytes.length);
      dos.write(msgBytes, 0, msgBytes.length);
      dos.writeLong(request.getTime());
      System.out.println("客户端发送成功：" + request);


      DataInputStream dis = new DataInputStream(client.getInputStream());
      long useTime = dis.readLong() - request.getTime();
      int nameLen = dis.readInt();
      byte[] resName = new byte[nameLen];
      dis.readFully(resName);
      String name = new String(resName, StandardCharsets.UTF_8);

      int replyLen = dis.readInt();
      byte[] resReply = new byte[replyLen];
      dis.readFully(resReply);
      String reply = new String(resReply, StandardCharsets.UTF_8);

      System.out.println("收到服务端响应：useTime: " + useTime + ",name: " + name + ",reply: " + reply);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    connect();
  }
}
