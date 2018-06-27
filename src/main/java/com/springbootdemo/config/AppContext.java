/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.config;

import org.springframework.context.ApplicationContext;

/**
 * 上下文.
 *
 * @author kexin.ding
 * @date 2018-06-27 15:52
 **/
public class AppContext {

  private AppContext() {}

  private static ApplicationContext applicationContext;

  public static void setApplicationContext(ApplicationContext context) {
    applicationContext = context;
  }

  public static Object getBean(String beanId) {
    return applicationContext.getBean(beanId);
  }

  /**
   * 获取yml文件对应key值
   * @param key 键
   * @return 值
   */
  public static String getProperty(String key) {
    return applicationContext.getEnvironment().getProperty(key);
  }

}
