package com.manager.admin.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

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

import com.manager.admin.entity.AdminLog;
import com.manager.admin.entity.AdminRole;
import com.manager.admin.entity.AdminUser;
import com.manager.admin.export.Export;
import com.manager.admin.page.PageUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.admin.service.AdminRightService;
import com.manager.admin.service.AdminRoleService;
import com.manager.admin.service.AdminUserService;
import com.manager.util.CollectionUtil;
import com.manager.util.DateUtil;

@RequestMapping("adminUser")
@Controller
public class AdminUserController {

	private Logger logger=Logger.getLogger(AdminUserController.class);
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRightService adminRightService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminLogService adminLogService;
	
	/**
	 * 管理员状态修改
	 * @param adminUser
	 * @param model
	 * @param response
	 * @param request
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateadminuserstate",method={RequestMethod.POST})
	public String doUpdateAdminUserState(AdminUser adminUser,Model model,HttpServletResponse response,
			HttpServletRequest request,PrintWriter printWriter){
		String jsoncallback="";
		jsoncallback=request.getParameter("callback");
		int state=0;
		String memo="";
		try{
			if(!CollectionUtil.checkNull(adminUser.getAdminAccount())){
				printWriter.write(jsoncallback+"({'res':'管理员帐号不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminState())){
				printWriter.write(jsoncallback+"({'res':'管理员状态不能为空！'})");
				return null;
			}
			int in=this.adminUserService.updateAdminUser(adminUser);
			if(in>0){
				printWriter.write(jsoncallback+"({'res':'1'})");
				memo+="修改管理员:"+adminUser.getAdminAccount()+"，状态改为"+adminUser.getAdminState()+"成功！";
				state=1;
			}else{
				printWriter.write(jsoncallback+"({'res':'修改状态失败!'})");
				memo+="修改管理员:"+adminUser.getAdminAccount()+"，状态改为"+adminUser.getAdminState()+"失败！";
			}
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="修改管理员:"+adminUser.getAdminAccount()+"，异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback+"({'res:'"+memo+"'})");
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}finally{
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 管理员删除
	 * @param adminUser
	 * @param model
	 * @param response
	 * @param request
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="todeleteadminuser",method={RequestMethod.POST})
	public String toDeleteAdminUser(AdminUser adminUser,Model model,HttpServletResponse response,
			HttpServletRequest request,PrintWriter printWriter){
		String jsoncallback="";
		jsoncallback=request.getParameter("callback");
		int state=0;
		String memo="";
		try{
			if(!CollectionUtil.checkNull(adminUser.getAdminAccount())){
				printWriter.write(jsoncallback+"({'res':'管理员帐号不能为空！'})");
				return null;
			}
			int in=this.adminUserService.deleteAdminUser(adminUser.getAdminAccount());
			if(in>0){
				printWriter.write(jsoncallback+"({'res':'1'})");
				memo+="删除管理员："+adminUser.getAdminAccount()+",成功！";
				state=1;
			}else{
				printWriter.write(jsoncallback+"({'res':'管理员删除失败!'})");
				memo+="删除管理员："+adminUser.getAdminAccount()+",失败！";
			}
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="删除管理员："+adminUser.getAdminAccount()+",异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback+"({'res:'"+memo+"'})");
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}finally{
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 管理员重置密码
	 * @param adminUser
	 * @param model
	 * @param response
	 * @param request
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateadminuserpwd",method={RequestMethod.POST})
	public String doUpdateAdminUserPwd(AdminUser adminUser,Model model,HttpServletResponse response,
			HttpServletRequest request,PrintWriter printWriter){
		String jsoncallback="";
		jsoncallback=request.getParameter("callback");
		int state=0;
		String memo="";
		try{
			if(!CollectionUtil.checkNull(adminUser.getAdminAccount())){
				printWriter.write(jsoncallback+"({'res':'管理员帐号不能为空！'})");
				return null;
			}
			adminUser.setAdminPwd("123456");
			int in=this.adminUserService.updateAdminUser(adminUser);
			if(in>0){
				printWriter.write(jsoncallback+"({'res':'密码重置为:123456'})");
				memo+="重置管理员"+adminUser.getAdminAccount()+"密码,成功！";
				state=1;
			}else{
				printWriter.write(jsoncallback+"({'res':'修改密码失败!'})");
				memo+="重置管理员"+adminUser.getAdminAccount()+"密码,失败！";
			}
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="重置管理员"+adminUser.getAdminAccount()+"密码,异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback+"({'res:'"+memo+"'})");
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}finally{
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 登录
	 * @param adminUser
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="tologin",method={RequestMethod.POST,RequestMethod.GET})
	public String toLogin(AdminUser adminUser,Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			if(!CollectionUtil.checkNull(adminUser.getAdminAccount())||!CollectionUtil.checkNull(adminUser.getAdminPwd())){
				jsonObj.put("res", "-1");
				return null;
			}
			AdminUser au=this.adminUserService.loginAdminUser(adminUser);
			if(au!=null){
				if("0".equals(au.getAdminState())){
					jsonObj.put("res", "2");
					memo+="管理员:"+adminUser.getAdminAccount()+"登录失败，该帐号被停用！";
					this.adminLogService.addAdminLog(adminUser.getAdminAccount(),"登录", memo, state, request);
					return null;
				}
				session.setAttribute("admin_account", au.getAdminAccount());
				session.setAttribute("last_cpassword_date", au.getLastCpasswordDate()==null?"":au.getLastCpasswordDate().substring(0, 19));
				//获得管理员所有角色ID
				List<String> userrolelist = this.adminUserService.findAdminUserRoleRoleIdList(adminUser.getAdminAccount());
				if(userrolelist!=null && userrolelist.size()>0){
					for(int i=0;i<userrolelist.size();i++){
						if("1".equals(userrolelist.get(i))){
							session.setAttribute("admin_role_admintype",true);
						}
					}
				}
				
				//保存用户权限
				Map<String,Integer> map=adminRightService.findRightUrl(adminUser.getAdminAccount());
				session.setAttribute("admin_right", map);
				jsonObj.put("res", "1");
				memo+="管理员:"+adminUser.getAdminAccount()+"登录成功！";
				state=1;
				this.adminLogService.addAdminLog("登录", memo, state, request);
				return null;
			}else{
				jsonObj.put("res", "0");
				memo+="管理员:"+adminUser.getAdminAccount()+"登录失败,帐号或密码错误！";
				this.adminLogService.addAdminLog(adminUser.getAdminAccount(),"登录", memo, state, request);
			}
		} catch (Exception e) {
			memo+="管理员:"+adminUser.getAdminAccount()+"登录，异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", "-2");
			jsonObj.put("su", e.getMessage());
			this.adminLogService.addAdminLog(adminUser.getAdminAccount(),"登录", memo, state, request);
		} finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 注销/登出
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dologout")
	public String doLogout(Model model,HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Cache-Control","no-store");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires",0);
		HttpSession session = request.getSession();
		//session
		this.adminLogService.addAdminLog("登录", "帐号"+request.getSession().getAttribute("admin_account")+"登出", 1, request);
		Enumeration e = session.getAttributeNames();
		while(e.hasMoreElements()){
			session.removeAttribute((String)e.nextElement());
		}
		return "login";
	}
	
	
	/**
	 * 管理员密码修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="updatepwd",method={RequestMethod.POST,RequestMethod.GET})
	public String updateAdminUserPwd(Model model,HttpServletRequest request,HttpServletResponse response,
			PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		try{
			String adminAccount=(String) session.getAttribute("admin_account");
			String oldPwd=request.getParameter("oldPwd");
			String adminUserPwd=request.getParameter("adminUserPwd");
			String adminUserPwds=request.getParameter("adminUserPwds");
			
			if(!CollectionUtil.checkNull(adminAccount)){
				printWriter.write(jsoncallback + "({'res':'帐号不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(oldPwd)){
				printWriter.write(jsoncallback + "({'res':'旧密码不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUserPwd)){
				printWriter.write(jsoncallback + "({'res':'新密码不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUserPwds)){
				printWriter.write(jsoncallback + "({'res':'确认密码不能为空！'})");
				return null;
			}
			if(!adminUserPwd.equals(adminUserPwds)){
				printWriter.write(jsoncallback + "({'res':'新密码与确认密码不一致！'})");
				return null;
			}
			AdminUser au1=new AdminUser();
			au1.setAdminAccount(adminAccount);
			au1.setAdminPwd(oldPwd);
			AdminUser au2=this.getAdminUserService().loginAdminUser(au1);
			if(au2==null){
				printWriter.write(jsoncallback + "({'res':'旧密码不正确！'})");
				return null;
			}
			AdminUser au3=new AdminUser();
			au3.setAdminAccount(adminAccount);
			au3.setAdminPwd(adminUserPwd);
			au3.setLastCpasswordDate(DateUtil.formatDate6(new Date()));
			int in=this.getAdminUserService().updateAdminUser(au3);
			if(in>0){
				printWriter.write(jsoncallback + "({'res':'1'})");
				memo+="管理员："+adminAccount+",修改自己的密码成功！";
				state=1;
			}else{
				printWriter.write(jsoncallback + "({'res':'修改失败！'})");
				memo+="管理员："+adminAccount+",修改自己的密码失败！";
			}
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="管理员修改自己的密码,异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({res:'" +memo + "'})");
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 进入管理员列表页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toadminuserlist")
	public String toAdminUserList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			
//			HttpSession session=request.getSession();
//			List l=(List)session.getAttribute("rolelist1");//
//			if(l==null){
//				l=this.adminRoleService.findAdminRoleList(null);
//				session.setAttribute("rolelist1", l);
//			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return "/admin/admin_user_list";
	}
	
	/**
	 * 查询管理员显示到列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doadminuserlist")
	public String doAdminUserList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String adminAccount=request.getParameter("adminAccount");
			request.setAttribute("adminAccount", adminAccount);
			
			AdminUser adminUser=new AdminUser();
			if(CollectionUtil.checkNull(adminAccount)){
				adminUser.setAdminAccount(adminAccount);
			}
			
			//当前页数
			String cIndex=request.getParameter("index");
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/adminUser/doadminuserlist?adminAccount="+adminAccount;
			//获得该页集合数据
			List<AdminUser> adminUserList=this.adminUserService.findAdminUserList(adminUser, (currentIndex-1)*PageUtil.PAGECOUNT, PageUtil.PAGECOUNT);
			//数据总行数
			int dataCount=this.adminUserService.findAdminUserCount(adminUser);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex,url,request);
		    request.setAttribute("pageinfo", pageinfo);
			
			if(adminUserList!=null && adminUserList.size()>0){
				request.setAttribute("adminUserList", adminUserList);
			}
			memo+="查询管理员显示到列表";
			state=1;
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="查询管理员显示到列表，异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}
		return "/admin/admin_user_list";
	}
	
	/**
	 * 管理员显示到列表导出
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("expadminuserlist")
	public String expAdminUserList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String adminAccount=request.getParameter("adminAccount");
			request.setAttribute("adminAccount", adminAccount);
			
			AdminUser adminUser=new AdminUser();
			if(CollectionUtil.checkNull(adminAccount)){
				adminUser.setAdminAccount(adminAccount);
			}
			
			//获得集合数据
			List<Map<String,String>> adminUserList=this.adminUserService.findAdminUserList(adminUser, Export.EXPNUM);
			
			if(adminUserList!=null && adminUserList.size()>0){
				//导出
				Export ex=new Export("AdminUser");
				
				String[] keys={"ADMIN_USER","ADMIN_REALNAME","ADMIN_STATE","CREATED_DATE","MODIFY_DATE"};
				String[] titleobj={"管理员帐号","姓名","状态","创建时间","修改时间"};
				ex.exportXLS(adminUserList, keys, titleobj, request, response);
				
				//记录日志
				state=1;
				memo+="管理员列表导出AdminUser.xls";
				this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
			}else{
				request.setAttribute("msg", "查询为空");
			}
			
		}catch(Exception e){
			memo+="管理员列表导出，异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}
		return null;
	}
	
	/**
	 * 添加管理员
	 * @param adminUser
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doaddadminuser",method={RequestMethod.POST,RequestMethod.GET})
	public String doAddAdminUser(AdminUser adminUser,Model model,HttpServletRequest request,HttpServletResponse response,
			PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		int state=0;
		String memo="";
		try{
			if(!CollectionUtil.checkNull(adminUser.getAdminAccount())){
				printWriter.write(jsoncallback + "({'res':'管理员帐号不能为空！'})");
				return null;
			}
			int in=this.adminUserService.checkAdminUser(adminUser.getAdminAccount());
			if(in>0){
				printWriter.write(jsoncallback + "({'res':'管理员帐号已存在，请重新输入帐号！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminPwd())){
				printWriter.write(jsoncallback + "({'res':'密码不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminRealName())){
				printWriter.write(jsoncallback + "({'res':'管理员姓名不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminEmail())){
				printWriter.write(jsoncallback + "({'res':'邮箱不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminRole())){
				printWriter.write(jsoncallback + "({'res':'请选择管理员角色！'})");
				return null;
			}
			int in2=this.adminUserService.addAdminUser(adminUser);
			if(in2>0){
				printWriter.write(jsoncallback + "({'res':'管理员添加成功！'})");
				memo+="添加管理员："+adminUser.getAdminAccount()+",成功！";
				state=1;
			}else{
				printWriter.write(jsoncallback + "({'res':'管理员添加失败！'})");
				memo+="添加管理员："+adminUser.getAdminAccount()+",失败！";
			}
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="添加管理员："+adminUser.getAdminAccount()+",异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({res:'异常：" + e.getMessage()+ "'})");
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 管理员修改
	 * @param adminUser
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateadminuser",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateAdminUser(AdminUser adminUser,Model model,HttpServletRequest request,HttpServletResponse response,
			PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		int state=0;
		String memo="";
		try{
			if(!CollectionUtil.checkNull(adminUser.getAdminAccount())){
				printWriter.write(jsoncallback + "({'res':'管理员帐号不能为空！'})");
				return null;
			}
			
			if(!CollectionUtil.checkNull(adminUser.getAdminRealName())){
				printWriter.write(jsoncallback + "({'res':'管理员姓名不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminEmail())){
				printWriter.write(jsoncallback + "({'res':'邮箱不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminUser.getAdminRole())){
				printWriter.write(jsoncallback + "({'res':'请选择管理员角色！'})");
				return null;
			}
			int in2=this.adminUserService.updateAdminUser2(adminUser);
			if(in2>0){
				printWriter.write(jsoncallback + "({'res':'管理员修改成功！'})");
				memo+="修改管理员："+adminUser.getAdminAccount()+",成功！";
				state=0;
			}else{
				printWriter.write(jsoncallback + "({'res':'管理员修改失败！'})");
				memo+="修改管理员："+adminUser.getAdminAccount()+",失败！";
			}
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}catch(Exception e){
			memo+="修改管理员："+adminUser.getAdminAccount()+",异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({res:'异常：" + e.getMessage()+ "'})");
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入日志页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toadminloglist")
	public String toAdminLogList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			List<String> servicelist=this.adminLogService.findAdminLogService();
			if(servicelist!=null && servicelist.size()>0){
				request.setAttribute("servicelist", servicelist);
			}
			return "admin/admin_log_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 导出日志显示到列表
	 * @param adminLog
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("expadminloglist")
	public String expAdminLogList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String adminAccount=request.getParameter("adminAccount");
			String operService=request.getParameter("operService");
			String openState=request.getParameter("openState");
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			

			request.setAttribute("adminAccount", adminAccount);
			request.setAttribute("operService", operService);
			request.setAttribute("openState", openState);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			AdminLog adminLog=new AdminLog();
			adminLog.setOperAdmin(adminAccount);
			if(CollectionUtil.checkNull(operService) && !"all".equals(operService)){
				adminLog.setOperService(operService);
			}
			if(CollectionUtil.checkNull(openState) && !"all".equals(openState)){
				adminLog.setOpenState(Integer.parseInt(openState));
			}
			adminLog.setStartDate(startDate);
			adminLog.setEndDate(endDate);
			List<String> servicelist=this.adminLogService.findAdminLogService();
			if(servicelist!=null && servicelist.size()>0){
				request.setAttribute("servicelist", servicelist);
			}
			
			//获得集合
			List<Map<String,String>> adminLogList=this.adminLogService.expAdminLogList(adminLog,Export.EXPNUM);
			
			if(adminLogList!=null && adminLogList.size()>0){
				
				//导出
				Export ex=new Export("AdminLog");
				String[] keys={"OPER_ADMIN","OPER_SERVICE","OPER_IP","OPER_MEMO","OPER_DATE","OPEN_STATE_INFO"};
				String[] titleobj={"帐号名","操作业务","ip地址","操作描述","操作日期","操作结果"};
				ex.exportXLS(adminLogList, keys, titleobj, request, response);
				
				//记录日志
				state=1;
				memo+="操作日志导出AdminLog.xls";
				this.adminLogService.addAdminLog("操作日志", memo, state, request);
			}else{
				request.setAttribute("msg", "查询为空");
			}
		}catch(Exception e){
			memo+="操作日志导出AdminLog.xls,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("操作日志", memo, state, request);
		}
		return null;
	}
	
	/**
	 * 查询日志显示到列表
	 * @param adminLog
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doadminloglist")
	public String doAdminLogList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String adminAccount=request.getParameter("adminAccount");
			String operService=request.getParameter("operService");
			String openState=request.getParameter("openState");
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			String cIndex=request.getParameter("index");
			

			operService=java.net.URLDecoder.decode(cIndex!=null?new String(operService.getBytes("ISO-8859-1"),"UTF-8"):operService,"UTF-8");
			request.setAttribute("adminAccount", adminAccount);
			request.setAttribute("operService", operService);
			request.setAttribute("openState", openState);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			AdminLog adminLog=new AdminLog();
			adminLog.setOperAdmin(adminAccount);
			if(CollectionUtil.checkNull(operService) && !"all".equals(operService)){
				adminLog.setOperService(operService);
			}
			if(CollectionUtil.checkNull(openState) && !"all".equals(openState)){
				adminLog.setOpenState(Integer.parseInt(openState));
			}
			adminLog.setStartDate(startDate);
			adminLog.setEndDate(endDate);
			List<String> servicelist=this.adminLogService.findAdminLogService();
			if(servicelist!=null && servicelist.size()>0){
				request.setAttribute("servicelist", servicelist);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/adminUser/doadminloglist?adminAccount="+adminAccount+"&operService="+java.net.URLEncoder.encode(operService,"UTF-8")
			+"&startDate="+startDate+"&endDate="+endDate+"&openState="+openState;
			//总行数
			int dataCount=this.adminLogService.findAdminLogCount(adminLog);
			//获得该页集合
			List<AdminLog> adminLogList=this.adminLogService.findAdminLogList(adminLog,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex,url,request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(adminLogList!=null && adminLogList.size()>0){
				request.setAttribute("adminLogList", adminLogList);
			}
		}catch(Exception e){
			memo+="操作日志查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("操作日志", memo, state, request);
		}
		return "admin/admin_log_list";
	}
	
	/**
	 * 进入管理修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateadminuser")
	public String toUpdateAdminUser(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String adminAccount = request.getParameter("adminAccount");
			
			//查询到该帐号详情
			AdminUser adminUser=this.adminUserService.findAdminUser(adminAccount);
			if(null!=adminUser){
				request.setAttribute("AdminUser", adminUser);
			}
			
			//查询所有角色
			HttpSession session=request.getSession();
			List l=(List)session.getAttribute("rolelist1");
			if(l==null){
				AdminRole ar=new AdminRole();
				ar.setRoleState("1");
				l=this.adminRoleService.findAdminRoleList(ar);
				session.setAttribute("rolelist1", l);
			}
			
			//查询该账号的所有所属角色
			List<String> userrolelist = this.adminUserService.findAdminUserRoleRoleIdList(adminAccount);
			if(userrolelist!=null){
				request.setAttribute("userrolelist", userrolelist);
			}
			memo+="进入管理员："+adminAccount+",修改页面成功！";
			state=1;
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
			return "/admin/admin_user_update";
		}catch(Exception e){
			memo+="进入管理员修改页面,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("帐号与权限", memo, state, request);
		}
		return null;
	}
	
	
	/**
	 * 进入管理员添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toaddadminuser")
	public String toAddAdminUser(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			HttpSession session=request.getSession();
			List l=(List)session.getAttribute("rolelist1");
			if(l==null){
				AdminRole ar=new AdminRole();
				ar.setRoleState("1");
				l=this.adminRoleService.findAdminRoleList(ar);
				session.setAttribute("rolelist1", l);
			}
			return "/admin/admin_user_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * @return the adminUserService
	 */
	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	/**
	 * @param adminUserService the adminUserService to set
	 */
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	/**
	 * @return the adminRightService
	 */
	public AdminRightService getAdminRightService() {
		return adminRightService;
	}

	/**
	 * @param adminRightService the adminRightService to set
	 */
	public void setAdminRightService(AdminRightService adminRightService) {
		this.adminRightService = adminRightService;
	}

	/**
	 * @return the adminRoleService
	 */
	public AdminRoleService getAdminRoleService() {
		return adminRoleService;
	}

	/**
	 * @param adminRoleService the adminRoleService to set
	 */
	public void setAdminRoleService(AdminRoleService adminRoleService) {
		this.adminRoleService = adminRoleService;
	}


	/**
	 * @return the adminLogService
	 */
	public AdminLogService getAdminLogService() {
		return adminLogService;
	}


	/**
	 * @param adminLogService the adminLogService to set
	 */
	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	
	
}
