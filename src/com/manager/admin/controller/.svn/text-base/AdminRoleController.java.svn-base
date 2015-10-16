package com.manager.admin.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manager.admin.entity.AdminRight;
import com.manager.admin.entity.AdminRole;
import com.manager.admin.entity.AdminRoleRight;
import com.manager.admin.export.Export;
import com.manager.admin.page.PageUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.admin.service.AdminRightService;
import com.manager.admin.service.AdminRoleService;
import com.manager.admin.service.AdminUserService;
import com.manager.util.CollectionUtil;

@RequestMapping("adminRole")
@Controller
public class AdminRoleController {

	private Logger logger=Logger.getLogger(AdminRoleController.class);
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminRightService adminRightService;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminLogService adminLogService;
	
	/**
	 * 进入权限列表查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toadminrightlist")
	public String toAdminRightList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "/admin/admin_right_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 查询权限显示到列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doadminrightlist")
	public String doAdminRightList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String rightId=request.getParameter("rightId");
			request.setAttribute("rightId", rightId);
			String cIndex=request.getParameter("index");
			AdminRight adminRight=new AdminRight();
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/adminRole/doadminrightlist?rightId="+rightId;
			//获得该页集合
			List<AdminRight> arlist=this.adminRightService.findAdminRightList(adminRight,(currentIndex-1)*PageUtil.PAGECOUNT, PageUtil.PAGECOUNT);
			//总行数
			int dataCount=this.adminRightService.findAdminRightCount(adminRight);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url ,request);
		    request.setAttribute("pageinfo", pageinfo);
			
			if(arlist!=null && arlist.size()>0){
				request.setAttribute("rightList", arlist);
			}
			memo+="权限查询显示列表";
			state=1;
			this.adminLogService.addAdminLog("权限管理", memo, state, request);
		}catch(Exception e){
			memo+="权限查询异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("权限管理", memo, state, request);
		}
		return "/admin/admin_right_list";
	}
	
	
	@RequestMapping(value="todeleteadminrole",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteAdminRole(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		int state=0;
		String memo="";
		try {
			String roleId=(String)request.getParameter("roleId");
			
			if(!CollectionUtil.checkNull(roleId)){
				printWriter.write(jsoncallback + "({'res':'角色ID不能为空！'})");
				return null;
			}
			
			String admin=this.adminUserService.checkAdminUserRole(roleId);
			if(CollectionUtil.checkNull(admin) && !",".equals(admin)){
				printWriter.write(jsoncallback + "({'res':'该角色存在管理员："+admin+"需要将管理员与该角色关系去除才可以删除该角色！'})");
				return null;
			}
			int in=this.adminRoleService.deleteAdminRole(roleId);
			if(in>0){
				state=1;
				printWriter.write(jsoncallback + "({'res':'1'})");
				memo+="删除角色:"+roleId+",成功！";
			}else{
				printWriter.write(jsoncallback + "({'res':'删除角色失败！'})");
				memo+="删除角色:"+roleId+",失败！";
			}
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}catch(Exception e){
			memo+="删除角色异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({'res':'"+memo+"'})");
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 进入角色修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateadminrole")
	public String toUpdateAdminRole(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String roleId=request.getParameter("roleId");
			String roleName=request.getParameter("roleName");
			roleName=java.net.URLDecoder.decode(roleName,"UTF-8");
			if(!CollectionUtil.checkNull(roleId)){
				request.setAttribute("msg", "角色ID不能为空!");
				return null;
			}
			if(!CollectionUtil.checkNull(roleName)){
				request.setAttribute("msg", "角色名不能为空!");
				return null;
			}
			AdminRole adminRole=this.adminRoleService.findAdminRole(roleName);
			if(null==adminRole){
				request.setAttribute("msg", "根据角色名:"+roleName+",为找到该角色!");
				return null;
			}
			request.setAttribute("AdminRole", adminRole);
			
			//根据角色id查询角色权限
			AdminRoleRight adminRoleRight=new AdminRoleRight();
			adminRoleRight.setRoleId(roleId);
			List<AdminRoleRight> arrlist=this.adminRoleService.findadminRoleRightList(adminRoleRight);
			request.setAttribute("rightlist", arrlist);
			
			//获得所有权限
			Map map=this.adminRightService.get_Right();
			request.setAttribute("rights_edit", map);
			
			Map<String,List<AdminRight>> mp = this.adminRightService.get_RightMapList();
			request.setAttribute("rights_mp", mp);
			
			String strRight="";
			//拼成字符串
			if(arrlist!=null){
				AdminRoleRight arr;
				for(int i=0;i<arrlist.size();i++){
					arr=new AdminRoleRight();
					arr=arrlist.get(i);
					strRight=strRight+arr.getRightId()+";";
				}
			}
			request.setAttribute("strRight", strRight);
			
			return "/admin/admin_role_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 角色状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateadminrolestatus",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateAdminRoleStatus(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		try {
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String roleId=(String)request.getParameter("roleId");
			String roleState=(String)request.getParameter("roleState");
			
			if(!CollectionUtil.checkNull(roleId)){
				printWriter.write(jsoncallback + "({'res':'角色ID不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(roleState)){
				printWriter.write(jsoncallback + "({'res':'角色状态不能为空！'})");
				return null;
			}
			
			int in=this.adminRoleService.updateAdminRole(roleId, roleState);
			if(in>0){
				printWriter.write(jsoncallback + "({'res':'1'})");
				state=1;
				memo+="修改角色:"+roleId+"状态"+roleState+"修改成功！";
			}else{
				printWriter.write(jsoncallback + "({'res':'状态失败！'})");
				memo+="修改角色:"+roleId+"状态"+roleState+"失败！";
			}
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}catch(Exception e){
			memo+="修改角色状态异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({'res':'"+memo+"'})");
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 角色修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateadminrole",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateAdminRole(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		try {
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String roleId=(String)request.getParameter("roleId");
			String roleName=(String)request.getParameter("roleName");
			String roleState=(String)request.getParameter("roleState");
			String adminRights=(String)request.getParameter("adminRights");
			
			if(!CollectionUtil.checkNull(roleId)){
				printWriter.write(jsoncallback + "({'res':'角色ID不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(roleName)){
				printWriter.write(jsoncallback + "({'res':'角色名不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(roleState)){
				printWriter.write(jsoncallback + "({'res':'角色状态不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminRights)){
				printWriter.write(jsoncallback + "({'res':'角色权限不能为空！'})");
				return null;
			}
			
			int ii=this.adminRoleService.checkAdminRole(roleName,roleId);
			if(ii>0){
				printWriter.write(jsoncallback + "({'res':'角色名已存在，请重新输入角色名！'})");
				return null;
			}
			
			String[] right_id=adminRights.split(";");
			int in=this.adminRoleService.updateAdminRole(roleId, adminAccount, roleName, roleState, right_id);
			if(in>0){
				printWriter.write(jsoncallback + "({'res':'修改角色信息成功！'})");
				state=1;
				memo+="修改角色:"+roleId+"信息成功！";
			}else{
				printWriter.write(jsoncallback + "({'res':'修改角色信息失败！'})");
				memo+="修改角色:"+roleId+"信息失败！";
			}
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}catch(Exception e){
			memo+="修改角色信息异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({'res':'"+memo+"'})");
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入角色添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toaddadminrole")
	public String toAddAdminRole(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			HttpSession session = request.getSession();
			Map map=(Map)session.getAttribute("map");
			if(map==null){
				map=this.adminRightService.get_Right();
				session.setAttribute("map", map);
			}
			request.setAttribute("rights_edit", map);
			
			Map<String,List<AdminRight>> mp = this.adminRightService.get_RightMapList();
			request.setAttribute("rights_mp", mp);
			return "/admin/admin_role_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 角色添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doaddadminrole",method={RequestMethod.POST,RequestMethod.GET})
	public String doAddAdminRole(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		try {
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String roleName=(String)request.getParameter("roleName");
			String roleState=(String)request.getParameter("roleState");
			String adminRights=(String)request.getParameter("adminRights");
			
			if(!CollectionUtil.checkNull(roleName)){
				printWriter.write(jsoncallback + "({'res':'角色名不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(roleState)){
				printWriter.write(jsoncallback + "({'res':'角色状态不能为空！'})");
				return null;
			}
			if(!CollectionUtil.checkNull(adminRights)){
				printWriter.write(jsoncallback + "({'res':'角色权限不能为空！'})");
				return null;
			}
			
			int ii=this.adminRoleService.checkAdminRole(roleName,"-1");
			if(ii>0){
				printWriter.write(jsoncallback + "({'res':'角色名已存在，请重新输入角色名！'})");
				return null;
			}
			
			String[] right_id=adminRights.split(";");
			String maxroleid=this.adminRoleService.get_MaxroleId();
			int in=this.adminRoleService.addAdminRole(adminAccount, roleName, roleState, right_id, maxroleid);
			if(in>0){
				printWriter.write(jsoncallback + "({'res':'添加角色成功！'})");
				memo+="添加角色:"+roleName+"成功！";
			}else{
				printWriter.write(jsoncallback + "({'res':'添加角色失败！'})");
				memo+="添加角色:"+roleName+"失败！";
			}
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}catch(Exception e){
			memo+="添加角色异常："+e.getMessage();
			logger.error(memo, e);
			printWriter.write(jsoncallback + "({'res':'"+memo+"'})");
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入角色查询列表页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toadminrolelist")
	public String toAdminRoleList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "/admin/admin_role_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 查询角色显示到列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doadminrolelist")
	public String doAdminroleList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String roleName=request.getParameter("roleName");
			request.setAttribute("roleName", roleName);
			String cIndex=request.getParameter("index");
			AdminRole ar=new AdminRole();
			if(CollectionUtil.checkNull(roleName)){
				ar.setRoleName(roleName);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/adminRole/doadminrolelist?roleName="+roleName;
			//获得该页集合
			List<AdminRole> arlist=this.adminRoleService.findAdminRoleList(ar,(currentIndex-1)*PageUtil.PAGECOUNT, PageUtil.PAGECOUNT);
			//总行数
			int dataCount=this.adminRoleService.findAdminRoleCount(ar);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
			
			
			if(arlist!=null && arlist.size()>0){
				request.setAttribute("roleList", arlist);
			}
			memo+="角色查询显示列表";
			state=1;
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}catch(Exception e){
			memo+="角色查询异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}
		return "/admin/admin_role_list";
	}
	
	/**
	 * 导出角色显示到列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("expadminrolelist")
	public String expAdminroleList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			String roleName=request.getParameter("roleName");
			request.setAttribute("roleName", roleName);
			AdminRole ar=new AdminRole();
			if(CollectionUtil.checkNull(roleName)){
				ar.setRoleName(roleName);
			}
			
			//获得该页集合
			List<Map<String,String>> arlist=this.adminRoleService.expAdminRoleList(ar,Export.EXPNUM);
			
			if(arlist!=null && arlist.size()>0){
				//导出
				Export ex=new Export("AdminRole");
				String[] keys={"ROLE_ID","ROLE_NAME","ROLE_STATE","CREATE_ADMIN","CREATE_DATE","MODIFY_ADMIN","MODIFY_DATE"};
				String[] titleobj={"角色ID","角色名","角色状态","创建者","创建时间","修改者","修改时间"};
				ex.exportXLS(arlist, keys, titleobj, request, response);
				
				//记录日志
				state=1;
				memo+="角色列表导出AdminRole.xls";
				this.adminLogService.addAdminLog("角色管理", memo, state, request);
			}else{
				request.setAttribute("msg", "查询为空");
			}
		}catch(Exception e){
			memo+="角色列表导出异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog("角色管理", memo, state, request);
		}
		return null;
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
