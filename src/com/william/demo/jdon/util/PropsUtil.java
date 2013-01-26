package com.william.demo.jdon.util;

import java.io.*;
import java.util.*;
import java.net.URL;

import com.william.demo.jdon.util.jdom.*;

import org.apache.log4j.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Jdon.com Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author banq
 * @version 1.0
 */

public class PropsUtil {

  public final static String module = PropsUtil.class.getName();

  public final static String XML_CONFIG_FILE = "config.xml";

  public final static String LOG = "log.level";
  public final static String LOG4J = "log.log4j";

  public final static String SETUPNAME = "setup";
  public final static String SETUPVALUE = "true";

  public static String ENCODING = "UTF-8";

  private Map propMap = new HashMap();

  private final static PropsUtil propsUtil = new PropsUtil();
  public static PropsUtil getInstance() {
    return propsUtil;
  }

  /**
   * load default config file
   */
  private PropsUtil() {
    loadProperties(XML_CONFIG_FILE);
  }

  public void loadProperties(String configName) {

    String pathCongfgName = getConfFile(configName);
    if (pathCongfgName != null) {
      XMLProperties properties = new XMLProperties(pathCongfgName);
      propMap.put(configName, properties);
      return;
    }

    InputStream stream = getConfStream(configName);
    if (stream != null) {
      XMLProperties properties = new XMLProperties(stream);
      propMap.put(configName, properties);
      return;
    }

    System.err.println(" cann't load config file:-->" + configName);

  }


    public String getConfFile(String file) {

      URL confURL = getClass().getClassLoader().getResource(file);
      if (confURL == null)
        confURL = getClass().getClassLoader().getResource("META-INF/" +
            file);

      if (confURL == null)
        confURL = Thread.currentThread().getContextClassLoader().getResource(
            file);
      if (confURL == null) {
        System.err.println(" cann't find config file:-->" + file);
      } else {
        File file1 = new File(confURL.getFile());
        if (file1.isFile())
          return confURL.getFile();
      }

      return null;

    }

    public InputStream getConfStream(String file) {

      InputStream stream = getClass().getClassLoader().getResourceAsStream(file);
      if (stream == null)
        stream = getClass().getClassLoader().getResourceAsStream("META-INF/" +
            file);
      if (stream == null)
        stream = Thread.currentThread().getContextClassLoader().
            getResourceAsStream(
            file);
      if (stream == null) {
        System.err.println(" cann't find config file:-->" + file);
      }
      return stream;

    }


  public String getProperty(String name) {
    return getProperty(XML_CONFIG_FILE, name);
  }

  public String getProperty(String configName, String name) {
    XMLProperties properties = (XMLProperties) propMap.get(configName);
    String res = properties.getProperty(name);
    if (res == null)
      res = "";
    return res;
  }

  public void setProperty(String name, String value) {
    setProperty(XML_CONFIG_FILE, name, value);
  }

  public void setProperty(String configName, String name, String value) {
    XMLProperties properties = (XMLProperties) propMap.get(configName);
    properties.setProperty(name, value);
  }

}