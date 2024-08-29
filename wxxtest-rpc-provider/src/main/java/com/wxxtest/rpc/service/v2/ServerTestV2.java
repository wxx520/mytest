package com.wxxtest.rpc.service.v2;

/**
 * Hello world!
 */
public class ServerTestV2 {

  public static void main(String[] args) {
    TCPLongConnectionSeverV2 connectionSever = new TCPLongConnectionSeverV2();
    connectionSever.accept(null);
  }
}
