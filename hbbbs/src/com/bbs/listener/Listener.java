package com.bbs.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.bbs.util.Constant;
import com.bbs.util.Xml;


public class Listener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(Listener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			String path = Listener.class.getClassLoader().getResource("").toURI().getPath();
			path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
			
			String memcached_url = Xml.getXmlTagValue(path, "memcachedurl");
			Constant.MEMCACHEDURL = memcached_url.split(";");
			Constant.domain = Xml.getXmlTagValue(path, "passportdomain");
			//API key
			Constant.APIKEY = Xml.getXmlTagValue(path, "apikey");
			//请求的接口帐号（系统分配）
			Constant.APPID = Xml.getXmlTagValue(path, "appid");
			//版本号
			Constant.VERSION = Xml.getXmlTagValue(path, "version");
			//API URL
			Constant.APIURL = Xml.getXmlTagValue(path, "apiurl");
			
			//大眼睛模块
			Constant.EYE1 = Xml.getXmlTagValue(path, "eye1");
			Constant.EYE2 = Xml.getXmlTagValue(path, "eye2");
			Constant.EYE3 = Xml.getXmlTagValue(path, "eye3");
			
			//大眼睛图片数目
			Constant.SIZE1 = Xml.getXmlTagValue(path, "size1");
			Constant.SIZE2 = Xml.getXmlTagValue(path, "size2");
			Constant.SIZE3 = Xml.getXmlTagValue(path, "size3");
			
			Constant.TIEAPI = Xml.getXmlTagValue(path, "tie");
			Constant.IMGURL=Xml.getXmlTagValue(path, "img_url");
			Constant.USERAPI = Xml.getXmlTagValue(path, "userapi");
		} catch (Exception e) {
			logger.info("Config.xml读取异常："+e.getMessage());
		}
	}

}
