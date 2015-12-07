package com.bbs.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bbs.memcahed.MemCached;
import com.bbs.util.Constant;
import com.bbs.util.CookieUtil;
import com.bbs.util.HttpConnectionUtil;
import com.bbs.util.MD5;
import com.bbs.util.Utils;

public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ApiServlet.class);

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		JSONObject jsonObj=new JSONObject();
		try{
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			Map<String, String[]> params =request.getParameterMap(); 
			Map<String, String> parmsmap=new HashMap<String,String>();
			for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            parmsmap.put(key, values==null?null:values[0]);
	        }  
			parmsmap.put("version", Constant.VERSION);
			parmsmap.put("appid", Constant.APPID);
			String method = parmsmap.get("do");
			String user_id = parmsmap.get("uid");
			if(method.equals("getTopicList")){
				if(user_id!=null){
					String uuid = java.util.UUID.randomUUID().toString();
					MemCached.setMccObject(uuid, user_id, 0);
					CookieUtil.setCookieValue("hbbbsmid", uuid, Constant.domain, response);
				}
			}
			String sign=MD5.md5Api(parmsmap);
			parmsmap.put("sign", sign);
			logger.info("sign==================="+sign);
			String url=Utils.apiMapToString(parmsmap);
			logger.info("url==================="+url);
			String json=HttpConnectionUtil.getData(url);
			logger.info("json==================="+json);
			jsonObj.put("res", "1");
			jsonObj.put("su", json);
			response.getWriter().print(jsonObj.toString());
		}catch(Exception e){
			logger.error("com.bbs.servlet.ApiServlet==========="+e);
			jsonObj.put("res", "-1");
			jsonObj.put("su", "请求异常："+e.getMessage());
			response.getWriter().print(jsonObj.toString());
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doGet(req, resp);
	}
}
