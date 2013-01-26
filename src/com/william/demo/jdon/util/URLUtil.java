package com.william.demo.jdon.util;

import java.net.*;
import java.io.*;

public class URLUtil {

  private URLConnection connection = null;
  private URL url = null;
  private boolean timedOut = false;

  protected URLUtil() {}

  protected URLUtil(URL url) {
    this.url = url;
  }

  protected synchronized URLConnection openConnection(int timeout) throws
      IOException {
    Thread t = new Thread(new URLConnectorThread());
    t.start();

    try {
      this.wait(timeout);
    } catch (InterruptedException e) {
      if (connection == null)
        timedOut = true;
      else
        close(connection);
      throw new IOException("Connection never established");
    }

    if (connection != null) {
      return connection;
    } else {
      timedOut = true;
      throw new IOException("Connection timed out");
    }
  }

  public static URLConnection openConnection(URL url) throws IOException {
    return openConnection(url, 30000);
  }

  public static URLConnection openConnection(URL url, int timeout) throws
      IOException {
    URLUtil uc = new URLUtil(url);
    return uc.openConnection(timeout);
  }

  // special thread to open the connection
  private class URLConnectorThread implements Runnable {
    public void run() {
      URLConnection con = null;
      try {
        con = url.openConnection();
      } catch (IOException e) {}

      synchronized (URLUtil.this) {
        if (timedOut && con != null)
          close(con);
        else {
          connection = con;
          URLUtil.this.notify();
        }
      }
    }
  }

  // closes the HttpURLConnection does nothing to others
  private static void close(URLConnection con) {
    if (con instanceof HttpURLConnection) {
      ( (HttpURLConnection) con).disconnect();
    }
  }


}