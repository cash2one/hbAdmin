package com.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bbs.util.Constant;
import com.bbs.util.HttpConnectionUtil;

public class TieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(TieServlet.class);

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		JSONObject jsonObj=new JSONObject();
		try{
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			String tieziid = request.getParameter("tieziid");
			String url=Constant.TIEAPI+"?yonghuid=绘本树&tieziid="+tieziid;
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
