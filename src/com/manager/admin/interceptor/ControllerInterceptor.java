package com.manager.admin.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.manager.util.CollectionUtil;


public class ControllerInterceptor implements HandlerInterceptor{

	private Logger logger=Logger.getLogger(ControllerInterceptor.class);

	public ControllerInterceptor() {
		super();
	}

//	private String mappingURL;//利用正则映射到需要拦截的路径    
//    public void setMappingURL(String mappingURL) {    
//           this.mappingURL = mappingURL;    
//    }   
	
	/**
	 * preHandle()方法在业务处理器处理请求之前被调用  
	 * preHandle()：这个方法在业务处理器处理请求之前被调用，在该方法中对用户请求request进行处理。
	 * 如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去进行处理，则返回true；
	 * 如果程序员决定不需要再调用其他的组件去处理请求，则返回false。
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
			response.setContentType("text/html; charset=utf-8");
//		 	logger.info("==============执行顺序: 1、preHandle================");  
			logger.info("==============进入Controller拦截器================");  
	        String url=request.getServletPath();//获得路径
	        String path = request.getContextPath();
	        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	        logger.info("url="+url);
	        String strBackUrl = "http://" + request.getServerName() //服务器地址  
	        + ":"   
	        + request.getServerPort()           //端口号  
	        + request.getContextPath()      //项目名称  
	        + request.getServletPath()      //请求页面或其他地址  
	        + "?" + (request.getQueryString()); //参数  
		    logger.info("请求完整url："+strBackUrl);
	        /*********无需登录或权限判断的路径start*********/
	        //无需权限登录判断
	        if(url!=null ){
	        	if("/adminUser/tologin".equals(url)) return true;
	        	if("/domain".equals(url)) return true;
	        }
	        /***************end**********************/
	        
	        //获取admin_account用于判断是否登录
	        HttpSession session = request.getSession();
	        String admin_account=(String) session.getAttribute("admin_account");
	        
	        if(CollectionUtil.checkNull(admin_account)){
	        	/*************登录状态根据权限判断是否能访问******/
	        	
	        	/**登录情况下无需判断权限*/
	        	
	        	if("/adminUser/dologout".equals(url)) return true;
	        	if("/adminUser/updatepwd".equals(url)) return true;
	        	if("/scripts/uploadify".equals(url)) return true;
	        	if("/scripts/zipuploadify".equals(url)) return true;
	        	if("/advt/ajaxSelect".equals(url)) return true;
	        	if("/advt/ajaxGetSize".equals(url)) return true;
	        	if("/advt/ajaxMSelect".equals(url)) return true;
	        	if("/category/ajaxfindParent".equals(url)) return true;
	        	if("/category/ajaxSelect".equals(url)) return true;
	        	if("/advt/ajaxAdvtStatus".equals(url)) return true;
	        	if("/globalLevel/get_div_hobby".equals(url)) return true;
	        	if("/globalLevel/get_div_peoperty".equals(url)) return true;
	        	if("/globalLevel/get_select_all_hobby".equals(url)) return true;
	        	if("/globalLevel/get_select_all_peoperty".equals(url)) return true;
	        	if("/resourceinfo/check_info_add".equals(url)) return true;
	        	if("/user/checkuserid".equals(url)) return true;
	        	
	        	/**登录情况下无需判断权限*/
	        	
	        	
	        	Map<String, Integer> ht = (Map<String, Integer>) session.getAttribute("admin_right");// 权限分类,通过SESSION获得进行比较
				if (null == ht){
					PrintWriter pw = response.getWriter();
					pw.print("<script>");
					pw.print("window.parent.location='"+basePath+"/login.jsp';");
					pw.print("</script>");
					pw.flush();
					pw.close();
					return false;
				}
				
				String key = url;
				Object o=ht.get(key);
				logger.info("keyvalue="+o);
				if (null != ht.get(key)) {
					if (null == admin_account) {
						PrintWriter pw = response.getWriter();
						pw.print("<script>");
						pw.print("window.parent.location='"+basePath+"/login.jsp';");
						pw.print("</script>");
						pw.flush();
						pw.close();
						return false;
					}
					return true;
				}else{
					PrintWriter pw = response.getWriter();
					String  xhr =  request.getHeader("X-Requested-With");
					if("XMLHttpRequest".equals(xhr)){
						pw.print("alert('您没有权限访问该功能！');");
					}else{
						pw.print("<h1>您没有权限访问该功能！<h1>");
					}
					pw.flush();
					pw.close();
					return false;
				}
	        	/**************end***************************/
	        }else{
	        	/*************未登录状态下权限判断是否能访问******/
	        	
	        	/**************end***************************/
	        }
	        PrintWriter pw = response.getWriter();
		    pw.print("<script>");
			pw.print("window.parent.location='"+basePath+"/login.jsp';");
			pw.print("</script>");
			pw.flush();
			pw.close();
	      return false;
	}

	/**
	 * postHandle()方法在业务处理器处理请求之后被调用 
	 * postHandle()：这个方法在业务处理器处理完请求后，但是DispatcherServlet向客户端返回请求前被调用，在该方法中对用户请求request进行处理。
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
//		logger.info("==============执行顺序: 2、postHandle================");  
	}

	 
	/**
	 * afterCompletion()方法在DispatcherServlet完全处理完请求后被调用
	 * afterCompletion()：这个方法在DispatcherServlet完全处理完请求后被调用，可以在该方法中进行一些资源清理的操作。
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
//		logger.info("==============执行顺序: 3、afterCompletion================");  
	}


}
