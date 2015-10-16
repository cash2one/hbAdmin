package com.manager.util;

import org.apache.log4j.Logger;

/**
 * 日志类
 * 
 */
public class PMSLog {
	private static Logger logger = Logger.getLogger("A1");

	public static void error(Class class1, Exception e) {
		logger.error(e.getMessage(), e);
	}

	public static void error(String Log4jobj, String error) {
		logger.error(error);
	}

	public static void debug(Class class1, Exception e) {
		logger.debug(e.getMessage(), e);
	}

	public static void debug(String Log4jobj1, String debug) {
		logger.debug(debug);
	}

	public static void info(Class class1, Exception e) {
		logger.info(e.getMessage(), e);
	}

	public static void info(String Log4jobj, String info) {
		logger.info(info);
	}

	public static void warn(Class class1, Exception e) {
		logger.warn(e.getMessage(), e);
	}

	public static void warn(String Log4jobj, String warn) {
		logger.warn(warn);
	}

	public static final void println(Class class1, String println) {
		//testSystem.out.println("[" + class1.getName() + "] -> " + println);
	}
}
