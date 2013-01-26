package com.william.demo.jdon.util;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import org.apache.log4j.Logger;
import com.william.demo.jdon.util.PropsUtil;
import com.william.demo.jdon.util.Debug;
import com.william.demo.jdon.util.ObjectCreator;

public class XmlUtil {

  public final static String module = XmlUtil.class.getName();
  private final static PropsUtil propsUtil = PropsUtil.getInstance();

  public static  Map loadMapping(String fileName, String nodeName, String keyName,
                          String valueName) {
    Map map = new HashMap();
    try {
      String xmlFile = propsUtil.getConfFile(fileName);

      Debug.logVerbose(" mapping file:" + xmlFile, module);

      SAXBuilder builder = new SAXBuilder();
      Document doc = builder.build(new File(xmlFile));

      Debug.logVerbose(" got mapping file ", module);

      // Get the root element
      Element root = doc.getRootElement();

      List mappings = root.getChildren(nodeName);
      Iterator i = mappings.iterator();
      while (i.hasNext()) {
        Element mapping = (Element) i.next();
        String key = mapping.getChild(keyName).getTextTrim();
        String value = mapping.getChild(valueName).getTextTrim();
         Debug.logVerbose(" get the " + key + "=" + value, module);
        map.put(key, value);

      }
      Debug.logVerbose(" read finished", module);

    } catch (Exception ex) {
      Debug.logError(" error: " + ex, module);
    }

    return map;

  }



}