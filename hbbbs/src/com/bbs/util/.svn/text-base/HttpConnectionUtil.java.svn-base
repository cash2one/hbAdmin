package com.bbs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;

public class HttpConnectionUtil {

    private final static Logger logger = Logger.getLogger(HttpConnectionUtil.class);

    public static String getData(String urlStr) throws Exception {

	StringBuffer answer = new StringBuffer();
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;

	try {
	    if (urlStr == null || urlStr.equals(""))
		return "";
	    URL url = new URL(urlStr);

	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	    connection.setReadTimeout(30000);
	    connection.setRequestMethod("GET");
	    connection.setConnectTimeout(30000);

	    connection.setDoOutput(true);
	    connection.connect();

	    is = connection.getInputStream();
	    isr = new InputStreamReader(is, "UTF-8");
	    br = new BufferedReader(isr);

	    if (br != null) {
		for (String oneline = null; (oneline = br.readLine()) != null; answer.append("\n")) {
		    answer.append(oneline);
		    ;
		}
	    }

	    connection.disconnect();
	} catch (SocketTimeoutException e) {
	    logger.info("连接超时");
	    throw e;

	} catch (Exception e) {
	    throw e;
	} finally {
	    if (br != null) {
		try {
		    br.close();
		} catch (IOException e1) {
		    throw e1;
		}
	    }

	}
	return answer.toString().trim();
    }

    public static String getDataSSL(String urlStr, String keystorePath) throws Exception {

	StringBuffer answer = new StringBuffer();

	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;

	try {

	    if (urlStr == null || urlStr.equals(""))
		return "";

	    URL url = new URL(urlStr);

	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	    connection.setReadTimeout(30000);
	    connection.setRequestMethod("GET");
	    connection.setConnectTimeout(30000);

	    connection.setDoOutput(true);
	    connection.connect();

	    is = connection.getInputStream();
	    isr = new InputStreamReader(is);
	    br = new BufferedReader(isr);

	    if (br != null) {
		for (String oneline = null; (oneline = br.readLine()) != null; answer.append("\n")) {
		    answer.append(oneline);
		}
	    }
	    connection.disconnect();
	} catch (Exception e) {
	    throw e;
	} finally {
	    if (br != null) {
		try {
		    br.close();
		} catch (IOException e1) {
		    throw e1;
		}
	    }

	}
	return answer.toString().trim();
    }
    
    
    public static void main(String arg[]){
		try {
			
			
//			String str=getData("http://s1.mycs.17qiqu.com/rookiecard.php?sid=1&passport=abcde1&time=1413965582&sign=471bdbd2367337a752b9737f9eb32e23");
//			System.out.print(str);
			
			//String str=getData("http://s1.mycs.17qiqu.com/rookiecard.php?sid=1&passport=abcde1&time=1413965582&sign=471bdbd2367337a752b9737f9eb32e23");
			String str=getData("http://203.66.14.198:8080/cmsyh/custom/finduser?page=1&pageNo=10&type=1");
			
			System.out.print(str);
//			JSONObject jsonObject = JSONObject.fromObject(str); 
//			System.out.println(jsonObject);
//			System.out.println(jsonObject.get("status"));
//			System.out.println(jsonObject.get("total_regs"));
//			System.out.println(jsonObject.get("total_logins"));
//			System.out.println(jsonObject.get("data"));
//			JSONObject jsonObject2 = JSONObject.fromObject(jsonObject.get("data")); 
//			Iterator<String> iter=jsonObject2.keys();
//			while(iter.hasNext()){
//				String key=iter.next();
//				System.out.print(key+"\t\t");
//				JSONObject jsonobj=(JSONObject)jsonObject2.get(key);
//				Iterator<String> iterobj=jsonobj.keys();
//				while(iterobj.hasNext()){
//					System.out.print(jsonobj.get(iterobj.next())+"\t\t");
//				}
//				System.out.println();
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
