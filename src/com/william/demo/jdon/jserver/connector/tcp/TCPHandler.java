package com.william.demo.jdon.jserver.connector.tcp;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;
import java.nio.charset.*;
import java.util.*;

import com.william.demo.jdon.util.Debug;

import com.william.demo.jdon.jserver.connector.SocketDataHandler;
import com.william.demo.jdon.jserver.connector.queue.QueueFactory;

/**
 * TCP数据包读和发送处理类
 * <p>Copyright: Jdon.com Copyright (c) 2003</p>
 * <p>Company: 上海解道计算机技术有限公司</p>
 * @author banq
 * @version 1.0
 */
public class TCPHandler implements Runnable {

  private final static String module = TCPHandler.class.getName();

  private SocketDataHandler socketDataHandler;

  private final SocketChannel sc;
  private final SelectionKey sk;

  private static final int READING = 0, SENDING = 1;
  private int state = READING;

  public TCPHandler(SelectionKey sk, SocketChannel sc) throws IOException {

    socketDataHandler = new SocketDataHandler(QueueFactory.TCP_QUEUE);
    this.sc = sc;
    this.sk = sk;
  }

  public void run() {
    try {
      if (state == READING)
        read();
      else if (state == SENDING)
        send();

    } catch (Exception ex) {
      Debug.logWarning("warn:" + ex, module);
      close();
    }
  }

  private void read() throws Exception {
    try {
      byte[] array = socketDataHandler.getByte();
      ByteBuffer buffer = ByteBuffer.wrap(array);

      int bytes = sc.read(buffer);
      if (bytes == -1) return;
      socketDataHandler.receiveRequest(array);

      state = SENDING;
      sk.interestOps(SelectionKey.OP_WRITE);
    } catch (Exception ex) {
      Debug.logError(ex, module);
      throw new Exception(ex);
    }

  }

  private void send() throws Exception {
    try {
      byte[] response = socketDataHandler.sendResponse();
      ByteBuffer buffer1 = ByteBuffer.wrap(response);
      sc.write(buffer1);

      state = READING;
      sk.interestOps(SelectionKey.OP_READ);

    } catch (Exception ex) {
      Debug.logError(ex, module);
      throw new Exception(ex);
    }

  }

  public void close() {
     if (sc != null) {
       try {
         sk.cancel();
         sc.close();
       } catch (Exception ignored) {
       }
     }
   }




}