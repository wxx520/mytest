package com.wxxtest.rpc.service.v3;

/**
 * Hello world!
 */
public class ServerTestV3 {

  public static void main(String[] args) {
    TCPLongConnectionSeverV3 connectionSever = new TCPLongConnectionSeverV3();
    connectionSever.accept(null);
  }
}
