package com.william.demo.jdon.jserver.application.chat;

import java.io.*;
import com.william.demo.jdon.jserver.application.connection.ConnectionFactory;
import com.william.demo.jdon.jserver.application.Connection;

public class ChatTCPServer implements Runnable {
  private ConnectionFactory connFactory = ConnectionFactory.getInstance(
      ConnectionFactory.TCPSERVER);

  public void run() {
    try {
      while (true) {
        tcpConnect();
        Thread.sleep(300L);
      }
    } catch (Exception ex) {
    }
  }

  private void tcpConnect() throws Exception {
    Connection conn = connFactory.getTcpConnection(true);

    String clientMsg = conn.readString();
    System.out.println("clientMsg==" + clientMsg);

    ///////////////////////////////////////////
    StringBuffer buff = new StringBuffer(clientMsg);
    buff.append("come back again!");

    String newclientMsg = buff.toString();
    conn.writeString(newclientMsg);

    System.out.println("send :" + newclientMsg);

  }

  public static void main(String[] args) {
    Thread chat = new Thread(new ChatTCPServer());
    chat.start();

  }

}