package com.manager.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SorlUtil {
	
	/**
	 * 通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串
	 * 
	 * @param list
	 *            列表对象
	 * 
	 * @return String "[{},{}]"
	 */
	public static String listToJson(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(beanToJson(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * 传入任意一个 object对象生成一个指定规格的字符串
	 * 
	 * @param object
	 *            任意对象
	 * @return String
	 */
	public static String objectToJson(Object object) {
		StringBuilder json = new StringBuilder();
		if (object == null) {
			json.append("\"\"");
		} else if (object instanceof String || object instanceof Integer || object instanceof Double || object instanceof Float) {
			json.append("\"").append(object.toString().replace("\"", "\\\"")).append("\"");
		} else {
			json.append(beanToJson(object));
		}
		return json.toString();
	}

	/**
	 * 传入任意一个 Javabean对象生成一个指定规格的字符串
	 * 
	 * @param bean
	 *            bean对象
	 * @return String "{}"
	 */
	public static String beanToJson(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = objectToJson(props[i].getName());
					String value = objectToJson(props[i].getReadMethod().invoke(bean));
					Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		            Matcher m = p.matcher(value);
		            value = m.replaceAll("");
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

}
