package com.wxxtest.rpc.service.registry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ServiceProviderTest {

  public static void main(String[] args) throws Exception {
    try (Socket client = new Socket("0.0.0.0", 2666)) {
      for (int i = 1; i < 250; i++) {
        ObjectOutputStream dos = new ObjectOutputStream(client.getOutputStream());
        dos.writeObject(new String[]{"wxx", "1.2.3." + i});
        ObjectInputStream dis = new ObjectInputStream(client.getInputStream());
        String[] response = (String[]) dis.readObject();
        System.out.println("注册服务成功：" + Arrays.toString(response));
        Thread.sleep(1000 * 10);
      }
    }
  }
}
