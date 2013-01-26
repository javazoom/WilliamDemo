package com.william.demo.jdon.util;

import java.io.InputStream;
import java.util.Properties;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * 使用Hibernate需要两步：
 * 1. 创建hibernate.cfg.xml
 * 2. 配置mapping文件
 *
 * 配置文件可以和EJB的ejb-jar.xml一起，放在META-INF/目录下
 *
 * <p>Copyright: Jdon.com Copyright (c) 2003</p>
 * <p>Company: 上海解道计算机技术有限公司</p>
 * @author banq
 * @version 1.0
 */
public class HibernateUtil {

  private Properties hbmProps = new Properties();

  private static HibernateUtil _instance;

  private SessionFactory _sessionFactory;

  private static HibernateUtil _getInstance() {
    if (_instance == null) {
      synchronized (HibernateUtil.class) {
        if (_instance == null) {
          _instance = new HibernateUtil();
        }
      }
    }
    return _instance;
  }

  public Properties getProperties(){
      return hbmProps;
  }

  public static Session openSession() throws HibernateException {
    return _getInstance()._openSession();
  }

  public static void closeSession(Session session) throws HibernateException {
    try {
      if (session != null) {
        session.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private HibernateUtil() {
    try {
        try {
            _sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Session _openSession() throws HibernateException {
    return _sessionFactory.openSession();
  }

}
