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
import com.manager.function.entity.GlobalLanguage;
import com.manager.function.entity.ResourceInfo;
import com.manager.function.service.GlobalLanguageService;
import com.manager.function.service.ResourceInfoService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("globallanguage")
@Controller
public class GlobalLanguageController {

	private Logger logger = Logger.getLogger(GlobalLanguageController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private GlobalLanguageService globalLanguageService;
	@Autowired
	private ResourceInfoService resourceInfoService;
	
	
	/**
	 * 进入语言难度修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdategloballanguage")
	public String toUpdateGlobalLanguage(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			GlobalLanguage globallanguage=this.globalLanguageService.findGlobalLanguageOne(id);
			if(globallanguage!=null){
				request.setAttribute("globallanguage", globallanguage);
			}
			
			return "function/config/globallanguage/global_language_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入语言难度查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("togloballanguagelist")
	public String toGlobalLanguageList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/config/globallanguage/global_language_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入语言难度添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertgloballanguage")
	public String toInsertGlobalLanguage(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/config/globallanguage/global_language_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 语言难度添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertgloballanguage",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertGlobalLanguage(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String lan_level_content=(String)request.getParameter("lan_level_content");
			String lan_level_summary=(String)request.getParameter("lan_level_summary");
			
			if(!CollectionUtil.checkNull(lan_level_content)){
				jsonObj.put("res", "语言难度名称不能为空！");
				return null;
			}
			GlobalLanguage GlobalLanguage=new GlobalLanguage();
			GlobalLanguage.setLan_level_content(lan_level_content);
			GlobalLanguage.setCreate_adminuser(adminAccount);
			if(CollectionUtil.checkNull(lan_level_summary)){
				GlobalLanguage.setLan_level_summary(lan_level_summary);
			}
			
			int ii=this.globalLanguageService.checkGlobalLanguageName(lan_level_content);
			if(ii>0){
				jsonObj.put("res", "语言难度名称已存在，请从新填写语言难度名称！");
				return null;
			}
			
			int in=this.globalLanguageService.insertGlobalLanguage(GlobalLanguage);
			if(in>0){
				state=1;
				memo+="语言难度lan_level_content："+lan_level_content+",添加成功！";
				jsonObj.put("res", "语言难度添加成功！");
			}else{
				memo+="语言难度添加失败！";
				jsonObj.put("res", "语言难度添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}catch(Exception e){
			memo+="语言难度添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 语言难度修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdategloballanguage",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateGlobalLanguage(Model model,HttpServletRequest request,HttpServletResponse response
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
			String id=(String)request.getParameter("id");
			String lan_level_content=(String)request.getParameter("lan_level_content");
			String lan_level_summary=(String)request.getParameter("lan_level_summary");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(lan_level_content)){
				jsonObj.put("res", "语言难度名称不能为空！");
				return null;
			}
			
			//获得该语言难度信息
			GlobalLanguage GlobalLanguage=this.globalLanguageService.findGlobalLanguageOne(id);
			if(GlobalLanguage==null){
				jsonObj.put("res", "语言难度获取失败或语言难度序列号错误！");
				return null;
			}
			
			int ii=this.globalLanguageService.checkGlobalLanguageName(id,lan_level_content);
			if(ii>0){
				jsonObj.put("res", "语言难度名称已存在，请从新填写语言难度名称！");
				return null;
			}
			GlobalLanguage.setLan_level_content(lan_level_content);
			if(CollectionUtil.checkNull(lan_level_summary)){
				GlobalLanguage.setLan_level_summary(lan_level_summary);
			}
			GlobalLanguage.setUpdate_adminuser(adminAccount);
			
			int in=this.globalLanguageService.updateGlobalLanguage(GlobalLanguage);
			if(in>0){
				state=1;
				memo+="修改语言难度"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改语言难度"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}catch(Exception e){
			memo+="语言难度修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 语言难度删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletegloballanguage",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteGlobalLanguage(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			
			ResourceInfo ResourceInfo=new ResourceInfo();
			ResourceInfo.setLanguage_level(id);
			int ii=this.resourceInfoService.findResourceInfoCount(ResourceInfo);
			if(ii>0){
				jsonObj.put("res", "该语言难度在资源信息里面引用到，删除失败！");
				return null;
			}
			
			int in=this.globalLanguageService.deleteGlobalLanguage(id);
			if(in>0){
				state=1;
				memo+="删除语言难度"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除语言难度"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}catch(Exception e){
			memo+="语言难度删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 语言难度状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatusgloballanguage",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusGlobalLanguageType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String id=(String)request.getParameter("id");
			String status=(String)request.getParameter("status");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			GlobalLanguage GlobalLanguage=new GlobalLanguage();
			GlobalLanguage.setId(id);
			//GlobalLanguage.setStatus(status);
			
			int in=this.globalLanguageService.updateGlobalLanguageStatus(GlobalLanguage);
			if(in>0){
				state=1;
				memo+="修改语言难度"+id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改语言难度"+id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}catch(Exception e){
			memo+="语言难度状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 语言难度查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dogloballanguagelist")
	public String doGlobalLanguageList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
//			String status=(String)request.getParameter("status");
			String cIndex=request.getParameter("index");

			request.setAttribute("id", id);
//			request.setAttribute("status", status);
			
			
			GlobalLanguage GlobalLanguage=new GlobalLanguage();
//			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
//				GlobalLanguage.setStatus(status);
//			}
			if(CollectionUtil.checkNull(id)){
				GlobalLanguage.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/globallanguage/dogloballanguagelist?id="+id;
			//总行数
			int dataCount=this.globalLanguageService.findGlobalLanguageCount(GlobalLanguage);
			//获得该页集合
			List<GlobalLanguage> GlobalLanguageList=this.globalLanguageService.findGlobalLanguageList(GlobalLanguage,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(GlobalLanguageList!=null && GlobalLanguageList.size()>0){
				request.setAttribute("globallanguageList", GlobalLanguageList);
			}
			
			//记录日志
			memo+="语言难度查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
			
		}catch(Exception e){
			memo+="语言难度查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_qjpz, memo, state, request);
		}
		return "function/config/globallanguage/global_language_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the GlobalLanguageService
	 */
	public GlobalLanguageService getGlobalLanguageService() {
		return globalLanguageService;
	}


	/**
	 * @param GlobalLanguageService the GlobalLanguageService to set
	 */
	public void setGlobalLanguageService(GlobalLanguageService GlobalLanguageService) {
		this.globalLanguageService = GlobalLanguageService;
	}


	/**
	 * @return the resourceInfoService
	 */
	public ResourceInfoService getResourceInfoService() {
		return resourceInfoService;
	}


	/**
	 * @param resourceInfoService the resourceInfoService to set
	 */
	public void setResourceInfoService(ResourceInfoService resourceInfoService) {
		this.resourceInfoService = resourceInfoService;
	}


	
	
}
