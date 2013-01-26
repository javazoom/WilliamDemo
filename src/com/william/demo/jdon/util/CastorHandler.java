package com.william.demo.jdon.util;

import java.io.*;
import java.util.*;
import java.net.*;

import org.exolab.castor.xml.*;
import org.exolab.castor.mapping.*;
import org.xml.sax.InputSource;

import org.exolab.castor.xml.Marshaller;

import com.william.demo.jdon.util.Debug;

import com.william.demo.jdon.util.SimpleCachePool;
import com.william.demo.jdon.util.PropsUtil;

/**
 *
 * <p>Title: </p>
 * <p>Description:
 * CastorHanlder操作类
 * 需要映射文件，映射文件名可以自己任意取名。
 * 映射文件可以和EJB的ejb-jar.xml一起，放在META-INF/目录下
 * 也可以包含在EJB的jar中。
 *
 * </p>
 * <p>Copyright: Jdon.com Copyright (c) 2003</p>
 * <p>Company: 上海解道计算机技术有限公司</p>
 * @author banq
 * @version 1.0
 */
public class CastorHandler {

  public final static String module = CastorHandler.class.getName();

  private static PropsUtil propsUtil = PropsUtil.getInstance();

  /**
   * 获得对象和XML之间映射关系
   */
  public static Mapping getMapping(String mappingFile) {

    Mapping mapping = (Mapping) SimpleCachePool.get(mappingFile);

    if (mapping == null) {
      try {

        mapping = new Mapping();
        String pathmappingFile = propsUtil.getConfFile(mappingFile);
        InputStream mappingIn = null;

        if (pathmappingFile == null) {
          Debug.logVerbose("get mapping from stream " + mappingFile, module);
          mappingIn = propsUtil.getConfStream(mappingFile);
          mapping.loadMapping(new InputSource(mappingIn));
        } else
          mapping.loadMapping(pathmappingFile);

        SimpleCachePool.put(mappingFile, mapping);
      } catch (Exception e) {
        Debug.logError("get mapping: " + mappingFile + " error " + e, module);
      }
    }
    return mapping;
  }

  /**
   * 获得反序列化的对象
   */
  private static Unmarshaller getUnmarshaller(String mappingFile,
                                              String className) throws
      Exception {

    Unmarshaller un = (Unmarshaller) SimpleCachePool.get(className);
    if (un == null) {
      try {
        Class c = Class.forName(className);
        un = new Unmarshaller(c);
        un.setMapping(getMapping(mappingFile));
        SimpleCachePool.put(className, un);
      } catch (Exception e) {
        Debug.logError(" getUnmarshaller error:  " + className + " " + e,
                       module);
        throw new Exception(e);
      }
    }

    return un;

  }

  /**
   * 反序列化 读取对象从Reader中
   * @param mappingFile
   * @param className
   * @param in
   * @return
   */
  private static Object readImp(String mappingFile, String className, Reader in) throws
      Exception {
    Object object = null;
    try {
      Unmarshaller un = getUnmarshaller(mappingFile, className);
      object = un.unmarshal(in);
      in.close();
    } catch (Exception e) {
      Debug.logError(" read " + className + e, module);
      throw new Exception(e);
    }
    return object;

  }

  /**
   * 对象序列化 将对象写入Writer中
   * @param mappingFile
   * @param object
   * @param out
   * @throws Exception
   */
  public static void writeImp(String mappingFile, Object object, Writer out) throws
      Exception {
    Debug.logVerbose("", module);
    try {
      Marshaller ms = new Marshaller(out);
      ms.setMapping(getMapping(mappingFile));
      ms.setEncoding(propsUtil.ENCODING);
      ms.marshal(object);
      out.close();
    } catch (Exception e) {
      Debug.logError("write object to file :" + e, module);
      throw new Exception(e);
    }

  }

  /**
   * 从XML文件中读取对象
   */
  public static Object read(String mappingFile, String className,
                            String xmlfile) throws Exception {
    FileReader in = new FileReader(xmlfile);
    return readImp(mappingFile, className, in);

  }

  public static Object readFromString(String mappingFile, String className,
                                      String s) throws Exception {
    Reader in = new StringReader(s);
    return readImp(mappingFile, className, in);
  }

  /**
   * 将对象序列化到XML文件中
   */
  public static void write(String mappingFile, Object object, String outfile) throws
      Exception {

    FileWriter out = new FileWriter(outfile);
    writeImp(mappingFile, object, out);
  }

  /**
   * 将对象序列化到字符串文本中
   */
  public static String writeToString(String mappingFile, Object object) throws
      Exception {
    String s = null;
    Writer out = new StringWriter();
    writeImp(mappingFile, object, out);
    if (out != null)
      s = out.toString();
    return s;

  }

}