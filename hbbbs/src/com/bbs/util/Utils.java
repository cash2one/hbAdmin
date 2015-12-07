package com.bbs.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * 工具类
 */
public class Utils {

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	
	public static String apiMapToString(Map<String,String> parmsmap){
		StringBuffer urlparmsstr = new StringBuffer();
		parmsmap = Utils.sortMapByKey(parmsmap);
		for (Iterator iter = parmsmap.keySet().iterator(); iter.hasNext();) {
		     String key = (String) iter.next();
		    Object values = (Object) parmsmap.get(key);
			urlparmsstr.append(key).append("=").append(values).append("&");
		}
		String urlparmsall = urlparmsstr.substring(0, urlparmsstr.length()-1).toString(); //去掉末尾一个&符号
		return Constant.APIURL+"?"+urlparmsall.toString();
	}
}

