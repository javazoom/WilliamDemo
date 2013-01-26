package com.william.demo.jdon.jserver.application.connection;


import com.william.demo.jdon.util.Debug;
import java.io.*;
import java.util.Properties;

/**
 * 服务器参数配置
 *
 * <p>Copyright: Jdon.com Copyright (c) 2003</p>
 * <p>Company: 上海解道计算机技术有限公司</p>
 * @author banq
 * @version 1.0
 */
public class ServerCfg {

 private final static String module = ServerCfg.class.getName();

  private static int tcpPort = 81;
  private static int udpPort = 82;

  public int getTcpPort() {
    return tcpPort;
  }

  public int getUdpPort() {
    return udpPort;
  }

  private void getConfig() {
    Debug.logVerbose("begin to read config file ", module);

    InputStream in = null;
    try {
      Properties prop = new Properties();
      in = getClass().getClassLoader().getResourceAsStream("config.properities");
      prop.load(in);

      String portStr = prop.getProperty("server.tcpPort");
      Debug.logVerbose("Server Tcp Port=" + portStr, module);
      tcpPort = Integer.parseInt(portStr.trim());

      portStr = prop.getProperty("server.udpPort");
      Debug.logVerbose("Server Udp Port=" + portStr, module);
      udpPort = Integer.parseInt(portStr.trim());

      in.close();
    } catch (Exception e) {
      Debug.logError("config error:" + e, module);
    }
  }

}