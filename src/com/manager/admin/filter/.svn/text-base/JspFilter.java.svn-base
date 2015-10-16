package com.manager.admin.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class JspFilter implements Filter{

	private Logger logger=Logger.getLogger(JspFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		logger.info("================进入jsp过滤器=======================");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest hsr = (HttpServletRequest) request;
		HttpServletResponse hsrs = (HttpServletResponse) response;
		HttpSession session = hsr.getSession();
		String uri = hsr.getRequestURI();
		request.setCharacterEncoding("utf-8");
		String path = hsr.getContextPath();
		String basePath = hsr.getScheme()+"://"+hsr.getServerName()+":"+hsr.getServerPort()+path+"";
		
		//筛选出常用的jsp地址 
		if (checkCommonPage(uri)) {
			chain.doFilter(request, response);
			return;
		}
		
		String admin_account = (String) session.getAttribute("admin_account");
		if (null == admin_account){
			PrintWriter pw = hsrs.getWriter();
			pw.print("<script>");
			pw.print("window.parent.location='"+basePath+"/login.jsp';");
			pw.print("</script>");
			pw.flush();
			pw.close();
			return;
		}else{
			/*Map<String, Integer> ht = (Map<String, Integer>) session.getAttribute("admin_right");// 权限分类,通过SESSION获得进行比较
			if (null == ht){
				PrintWriter pw = hsrs.getWriter();
				pw.print("<script>");
				pw.print("window.parent.location='login.jsp';");
				pw.print("</script>");
				pw.flush();
				pw.close();
				return;
			}
			
			String key = uri;
			Object o=ht.get(key);
			logger.info("keyvalue="+o);
			if (null != ht.get(key)) {
				if (null == admin_account) {
					PrintWriter pw = hsrs.getWriter();
					pw.print("<script>");
					pw.print("window.parent.location='login.jsp';");
					pw.print("</script>");
					pw.flush();
					pw.close();
					return;
				}
				chain.doFilter(request, response);
				return;
			} else {
				logger.info("您的权限 不足以做此操作!");
				HttpServletResponseWrapper hsrw = new HttpServletResponseWrapper(hsrs);
				hsrw.setCharacterEncoding("utf-8");
				
				PrintWriter pw = hsrs.getWriter();
				pw.print("<script>");
				pw.print("alert('您的权限 不足以做此操作！');");
				pw.print("</script>");
				pw.flush();
				pw.close();
				return;
			}
			*/
			chain.doFilter(request, response);
			return;
		}
		
	}
	
	private boolean checkCommonPage(String uri) {
		if (uri.indexOf("/login.jsp") != -1) {
			return true;
		}
		if (uri.indexOf("/admin/admin_user_pwd_update.jsp") != -1) {
			return true;
		}
		if(uri.indexOf("/kindeditor/jsp/upload_json.jsp")!=-1){
			return true;
		}
		return false;
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

}
