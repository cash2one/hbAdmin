package com.manager.memcahed;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.jdom.JDOMException;

import com.manager.util.Constant;
import com.manager.util.Xml;

public class Init {

	public static void InitList() throws URISyntaxException, JDOMException, IOException{
		
		String path = Init.class.getClassLoader().getResource("").toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
		
		String memcached_url = Xml.getXmlTagValue(path, "memcachedurl");
		Constant.MEMCACHEDURL = memcached_url.split(";");
		
		 //前台显示路径
		Constant.domain=Xml.getXmlTagValue(path, "passportdomain");
		 //前台显示路径
		Constant.qdimagebereplace=Xml.getXmlTagValue(path, "qdimagebereplace");
	    //数据库保存占位符
		Constant.messageimagereplace=Xml.getXmlTagValue(path, "messageimagereplace");
		 //后台编辑器资讯图片访问路径
		Constant.messageimagebereplace=Xml.getXmlTagValue(path, "messageimagebereplace");
		
		Constant.bbs_path=Xml.getXmlTagValue(path, "bbs_path");
		
		Constant.bbs_url=Xml.getXmlTagValue(path, "bbs_url");
		//保存路径
		Constant.editorImageSave = Xml.getXmlTagValue(path, "editorImageSave");
		//头像路径
		Constant.baby_avatar = Xml.getXmlTagValue(path, "baby_avatar");
		//版本号
		Constant.version = Xml.getXmlTagValue(path, "version");
		//上传头像缓存地址
		Constant.tempPath = Xml.getXmlTagValue(path, "tempPath");
		
		String[] keys = Xml.getXmlTagValue(path, "key").split(":");
		Constant.APPID_KEY = new HashMap<String, String>();
		Constant.APPID_KEY.put(keys[0], keys[1]);
		
		//请求的接口帐号（系统分配）
		Constant.APPID = Xml.getXmlTagValue(path, "appid");
	}
}
