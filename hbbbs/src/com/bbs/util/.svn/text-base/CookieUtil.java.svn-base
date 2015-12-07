package com.bbs.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    /**
     * 根据KEY获得cookie里值
     * 
     * @param Key
     * @param request
     * @return
     */
    public static String getCookieValue(String Key, HttpServletRequest request) {
	// 获得cookie集合
	Cookie[] cookies = request.getCookies();
	String value = "";

	// 获得指定的cookie
	if (cookies != null) {
	    // Cookie种子
	    for (int i = 0; i < cookies.length; i++) {
		Cookie c = cookies[i];
		if (c.getName().equalsIgnoreCase(Key)) {
		    value = c.getValue();
		    break;
		} else {
		    value = "";
		}
	    }
	}
	return value;
    }

    /**
     * 设置cookei�?
     * 
     * @param Key
     * @param value
     * @param dmain
     * @param response
     */
    public static void setCookieValue(String Key, String value, String dmain, HttpServletResponse response) {
	// 设置用户角色cookie
	Cookie rolecookie = new Cookie(Key, value);
	rolecookie.setDomain(dmain);
	rolecookie.setPath("/");
	response.addCookie(rolecookie);
    }
}
