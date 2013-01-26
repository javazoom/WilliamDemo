package com.william.demo.jdon.jserver.connector;

/**
 * 数据包装工厂
 * 缺省是使用Http协议包装数据，也可以拓展成其它协议。
 * <p>Copyright: Jdon.com Copyright (c) 2003</p>
 * <p>Company: 上海解道计算机技术有限公司</p>
 * @author banq
 * @version 1.0
 */
public abstract class WrapFactory {

  private static WrapFactory factory = new com.william.demo.jdon.jserver.http.
      HttpWrapFactory();
  public static WrapFactory getInstance() {
    return factory;
  }

  public abstract byte[] getRequest(byte[] bytes);

  public abstract byte[] getResponse(byte[] bytes);

  public abstract byte[] getContentFromRequest(byte[] bytes) throws Exception;

  public abstract byte[] getContentFromResponse(byte[] bytes) throws Exception;

}