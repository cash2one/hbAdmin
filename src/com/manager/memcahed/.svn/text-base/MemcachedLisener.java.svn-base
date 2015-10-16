package com.manager.memcahed;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.manager.util.CollectionUtil;


public class MemcachedLisener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(MemcachedLisener.class);
	
	
	private static ApplicationContext app;

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sc) {
		try {
			Init.InitList();//初始化memcached的配置文件
			app = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext()); // 获取spring上下文！
		} catch (Exception e) {
			logger.info("news添加到memcached异常："+e.getMessage());
		}
	}
	

	/**
	 * @return the app
	 */
	public static ApplicationContext getApp() {
		return app;
	}
	/**
	 * @param app the app to set
	 */
	public static void setApp(ApplicationContext app) {
		MemcachedLisener.app = app;
	}


	
}
