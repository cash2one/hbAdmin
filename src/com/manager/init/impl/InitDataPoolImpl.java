package com.manager.init.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;

import com.manager.init.InitDataPool;

public class InitDataPoolImpl implements InitDataPool, InitializingBean {
	
	private String languagePath;
	// 本地系统
	private Properties sysParameters;
	// 国际化
	private Properties lanParameters;

	private void init() {
		lanParameters = new Properties();
		initLocalParameters();
	}

	public String getSP(String parName) {
		return lanParameters.getProperty(parName);
	}
	
	public String getLP(String parName) {
		return sysParameters.getProperty(parName);
	}

	public int getIntSP(String parName) {
		String value = lanParameters.getProperty(parName);
		if (value != null) {
			return Integer.parseInt(value);
		} else {
			return 0;
		}
	}

	// 本地化系统参数
	public void initLocalParameters() {
		lanParameters.clear();
		String lanPath = this.getClass().getResource("/").getPath() + languagePath;
		try {
			InputStream isl = new FileInputStream(lanPath);
			lanParameters.loadFromXML(isl);
			isl.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void afterPropertiesSet() throws Exception {
		this.init();
	}

	public String getLanguagePath() {
		return languagePath;
	}

	public void setLanguagePath(String languagePath) {
		this.languagePath = languagePath;
	}
	
}