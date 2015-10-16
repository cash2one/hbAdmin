package com.manager.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestQueryStringUtil {
	
	/*
	 * 获得参数集合
	 */
	public static StringBuffer getRequestQueryString(HttpServletRequest request){
		StringBuffer returnurlparm = new StringBuffer();
		Map m = request.getParameterMap();
		Iterator iter = m.keySet().iterator();
		if(iter.hasNext())
			returnurlparm.append("?");
		while(iter.hasNext()){
			Object key = iter.next();
			returnurlparm.append(key.toString()+"="+request.getParameter(key.toString())+"&");
		}
		return returnurlparm;
	}
}
