package com.wxxtest.rpc.server.com.wxxtest.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPSocketServer {
  static void connect() {
    try (ServerSocket server = new ServerSocket(2666)) {
      Socket socket = server.accept();
      System.out.println("服务端接收到客户端连接");
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      int age = dis.readInt();

      int nameLen = dis.readInt();
      byte[] nameBytes = new byte[nameLen];
      dis.readFully(nameBytes);
      String name = new String(nameBytes, StandardCharsets.UTF_8);

      int hiLen = dis.readInt();
      byte[] hiBytes = new byte[hiLen];
      dis.readFully(hiBytes);

      String hi = new String(hiBytes, StandardCharsets.UTF_8);

      System.out.println("服务端收到数据：age: " + age + ", name: " + name + ", hi: " + hi);

      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
      dos.writeLong(System.currentTimeMillis());
      String myName = "铁角角";
      byte[] myNameBytes = myName.getBytes(StandardCharsets.UTF_8);
      dos.writeInt(myNameBytes.length);
      dos.write(myNameBytes, 0, myNameBytes.length);
      String reply = "打广东麻将吗？";
      byte[] replyBytes = reply.getBytes(StandardCharsets.UTF_8);
      dos.writeInt(replyBytes.length);
      dos.write(replyBytes, 0, replyBytes.length);

      System.out.println("服务端响应成功");


    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    connect();
  }
}
