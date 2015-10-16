package com.provide.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类 
 */
public class _BaseController {
	
	public String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}

}