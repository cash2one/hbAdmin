package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manager.admin.page.PageUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.function.entity.ResourceType;
import com.manager.function.entity.User;
import com.manager.function.service.UserService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("user")
@Controller
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private UserService userService;
	
	/**
	 * 验证用户ID是否存在
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="checkuserid",method={RequestMethod.POST,RequestMethod.GET})
	public String checkUserID(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String id =(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "用户ID不能为空！");
				return null;
			}
			
			int in=this.userService.checkId(id);
			if(in>0){
				state=1;
				memo+="验证用户ID："+id+"，存在！";
				jsonObj.put("res", "1");
			}else{
				memo+="验证用户ID："+id+"，不存在！";
				jsonObj.put("res", "0");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_yhgl, memo, state, request);
		}catch(Exception e){
			memo+="验证用户ID 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_yhgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	 
	
	
	/**
	 * 进入注册用户统计查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("touserstatistics")
	public String toUserStatistics(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/statistics/user_statistics";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	/**
	 * 注册用户统计查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("douserstatistics")
	public String doUserStatistics(Model model,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		try{
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			
			String startDate=(String)request.getParameter("startDate");
			String endDate=(String)request.getParameter("endDate");
			String cIndex=request.getParameter("index");
			
			startDate=startDate==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(startDate.getBytes("ISO-8859-1"),"UTF-8"):startDate,"UTF-8");
			endDate=endDate==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(endDate.getBytes("ISO-8859-1"),"UTF-8"):endDate,"UTF-8");

			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			User User=new User();
			if(CollectionUtil.checkNull(startDate)){
				User.setStartDate(startDate);
			}
			if(CollectionUtil.checkNull(endDate)){
				User.setEndDate(endDate);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/user/douserstatistics?startDate="+java.net.URLEncoder.encode(startDate,"UTF-8")
			+"&endDate="+java.net.URLEncoder.encode(endDate,"UTF-8");
			//总行数
			int dataCount=this.userService.statistics_count(User);
			//获得该页集合
			List<User> UserList=this.userService.statistics(User,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(UserList!=null && UserList.size()>0){
				request.setAttribute("userList", UserList);
			}
			
			//记录日志
			memo+="注册用户统计 查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_qjtj, memo, state, request);
			
		}catch(Exception e){
			memo+="注册用户统计 查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_qjtj, memo, state, request);
		}
		return "function/statistics/user_statistics";
	}
	
	
	/**
	 * 注册帐号状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatususer",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusUser(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String user_id=(String)request.getParameter("user_id");
			String user_status=(String)request.getParameter("user_status");
			
			if(!CollectionUtil.checkNull(user_id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(user_status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			User User=new User();
			User.setUser_id(user_id);
			User.setUser_status(user_status);
			
			int in=this.userService.updateUserStatus(User);
			if(in>0){
				state=1;
				memo+="修改注册帐号"+user_id+"状态"+user_status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改注册帐号"+user_id+"状态"+user_status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
		}catch(Exception e){
			memo+="注册帐号状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入注册用户查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("touserlist")
	public String toUserList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/user/user_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入注册用户修改页面/用户详情查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateuser")
	public String toUpdateUser(Model model,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		try{
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			
			String user_id=(String)request.getParameter("user_id");
			
			if(!CollectionUtil.checkNull(user_id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			User User=this.userService.findUserOne(user_id);
			if(User!=null){
				request.setAttribute("user", User);
			}
			//记录日志
			memo+="注册用户"+user_id+"详情 查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
			
			return "function/user/user_update";
		}catch(Exception e){
			memo+="注册用户详情 查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
		}
		return null;
	}
	
	
	/**
	 * 注册帐号修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateuser",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateUser(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String user_id=(String)request.getParameter("user_id");
			String user_status=(String)request.getParameter("user_status");
			String backup=(String)request.getParameter("backup");
			
			if(!CollectionUtil.checkNull(user_id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(user_status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(backup)){
				backup=null;
			}
			
			
			//获得该注册帐号信息
			User User=this.userService.findUserOne(user_id);
			if(User==null){
				jsonObj.put("res", "注册帐号获取失败或注册帐号序列号错误！");
				return null;
			}
			User.setUser_status(user_status);
			User.setBackup(backup);
			
			int in=this.userService.updateUser(User);
			if(in>0){
				state=1;
				memo+="修改注册帐号"+user_id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改注册帐号"+user_id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
		}catch(Exception e){
			memo+="注册帐号修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	
	/**
	 * 注册用户查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("douserlist")
	public String doUserList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String user_id=(String)request.getParameter("user_id");
			String open_id=(String)request.getParameter("open_id");
			String user_email=(String)request.getParameter("user_email");
			String user_nickname=(String)request.getParameter("user_nickname");
			String user_tel=(String)request.getParameter("user_tel");
			String user_truename=(String)request.getParameter("user_truename");
			String backup=(String)request.getParameter("backup");
			String reg_source=(String)request.getParameter("source");
			String user_status=(String)request.getParameter("user_status");
			String user_title=(String)request.getParameter("user_title");
			String min_age=(String)request.getParameter("min_age");
			String max_age=(String)request.getParameter("max_age");
			
			
			String startDate=(String)request.getParameter("startDate");
			String endDate=(String)request.getParameter("endDate");
			String cIndex=request.getParameter("index");
			
			user_id=user_id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_id.getBytes("ISO-8859-1"),"UTF-8"):user_id,"UTF-8");
			open_id=open_id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(open_id.getBytes("ISO-8859-1"),"UTF-8"):open_id,"UTF-8");
			user_email=user_email==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_email.getBytes("ISO-8859-1"),"UTF-8"):user_email,"UTF-8");
			user_nickname=user_nickname==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_nickname.getBytes("ISO-8859-1"),"UTF-8"):user_nickname,"UTF-8");
			user_tel=user_tel==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_tel.getBytes("ISO-8859-1"),"UTF-8"):user_tel,"UTF-8");
			user_truename=user_truename==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_truename.getBytes("ISO-8859-1"),"UTF-8"):user_truename,"UTF-8");
			backup=backup==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(backup.getBytes("ISO-8859-1"),"UTF-8"):backup,"UTF-8");
			reg_source=reg_source==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(reg_source.getBytes("ISO-8859-1"),"UTF-8"):reg_source,"UTF-8");
			user_status=user_status==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_status.getBytes("ISO-8859-1"),"UTF-8"):user_status,"UTF-8");
			user_title=user_title==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_title.getBytes("ISO-8859-1"),"UTF-8"):user_title,"UTF-8");
			min_age=min_age==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(min_age.getBytes("ISO-8859-1"),"UTF-8"):min_age,"UTF-8");
			max_age=max_age==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(max_age.getBytes("ISO-8859-1"),"UTF-8"):max_age,"UTF-8");
			startDate=startDate==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(startDate.getBytes("ISO-8859-1"),"UTF-8"):startDate,"UTF-8");
			endDate=endDate==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(endDate.getBytes("ISO-8859-1"),"UTF-8"):endDate,"UTF-8");

			request.setAttribute("user_id", user_id);
			request.setAttribute("open_id", open_id);
			request.setAttribute("user_email", user_email);
			request.setAttribute("user_nickname", user_nickname);
			request.setAttribute("user_tel", user_tel);
			request.setAttribute("user_truename", user_truename);
			request.setAttribute("backup", backup);
			request.setAttribute("source", reg_source);
			request.setAttribute("user_status", user_status);
			request.setAttribute("user_title", user_title);
			request.setAttribute("min_age", min_age);
			request.setAttribute("max_age", max_age);
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			User User=new User();
			if(CollectionUtil.checkNull(user_id)){
				User.setUser_id(user_id);
			}
			if(CollectionUtil.checkNull(open_id)){
				User.setOpen_id(open_id);
			}
			if(CollectionUtil.checkNull(user_email)){
				User.setUser_email(user_email);
			}
			if(CollectionUtil.checkNull(user_nickname)){
				User.setUser_nickname(user_nickname);
			}
			if(CollectionUtil.checkNull(user_tel)){
				User.setUser_tel(user_tel);
			}
			if(CollectionUtil.checkNull(user_truename)){
				User.setUser_truename(user_truename);
			}
			if(CollectionUtil.checkNull(backup)){
				User.setBackup(backup);
			}
			if(CollectionUtil.checkNull(reg_source) && !"all".equals(reg_source)){
				User.setReg_source(reg_source);
			}
			if(CollectionUtil.checkNull(user_status) && !"all".equals(user_status)){
				User.setUser_status(user_status);
			}
			if(CollectionUtil.checkNull(user_title) && !"all".equals(user_title)){
				User.setUser_title(user_title);
			}
			if(CollectionUtil.checkNull(min_age)){
				User.setMin_age(min_age);
			}
			if(CollectionUtil.checkNull(max_age)){
				User.setMax_age(max_age);
			}
			
			if(CollectionUtil.checkNull(startDate)){
				User.setStartDate(startDate);
			}
			if(CollectionUtil.checkNull(endDate)){
				User.setEndDate(endDate);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			
			
			//url
			String url="/user/douserlist?startDate="+java.net.URLEncoder.encode(startDate,"UTF-8")
			+"&endDate="+java.net.URLEncoder.encode(endDate,"UTF-8")
			+"&user_id="+java.net.URLEncoder.encode(user_id,"UTF-8")
			+"&open_id="+java.net.URLEncoder.encode(open_id,"UTF-8")
			+"&user_email="+java.net.URLEncoder.encode(user_email,"UTF-8")
			+"&user_nickname="+java.net.URLEncoder.encode(user_nickname,"UTF-8")
			+"&user_tel="+java.net.URLEncoder.encode(user_tel,"UTF-8")
			+"&user_truename="+java.net.URLEncoder.encode(user_truename,"UTF-8")
			+"&backup="+java.net.URLEncoder.encode(backup,"UTF-8")
			+"&user_status="+java.net.URLEncoder.encode(user_status,"UTF-8")
			+"&source="+java.net.URLEncoder.encode(reg_source,"UTF-8")
			+"&user_title="+java.net.URLEncoder.encode(user_title,"UTF-8")
			+"&min_age="+java.net.URLEncoder.encode(min_age,"UTF-8")
			+"&max_age="+java.net.URLEncoder.encode(max_age,"UTF-8");
			//总行数
			int dataCount=this.userService.findUserCount(User);
			//获得该页集合
			List<User> UserList=this.userService.findUserList(User,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(UserList!=null && UserList.size()>0){
				request.setAttribute("userList", UserList);
			}
			
			//记录日志
			memo+="注册用户 查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
			
		}catch(Exception e){
			memo+="注册用户 查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_zcyh, memo, state, request);
		}
		return "function/user/user_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the UserService
	 */
	public UserService getUserService() {
		return userService;
	}


	/**
	 * @param UserService the UserService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
