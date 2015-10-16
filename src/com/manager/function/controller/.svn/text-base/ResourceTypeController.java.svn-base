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
import com.manager.function.service.ResourceTypeService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("resourcetype")
@Controller
public class ResourceTypeController {

	private Logger logger = Logger.getLogger(ResourceTypeController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private ResourceTypeService resourceTypeService;
	
	/**
	 * 进入资源类型修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateresourcetype")
	public String toUpdateResourceType(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			ResourceType resourceType=this.resourceTypeService.findResourceTypeOne(id);
			if(resourceType!=null){
				request.setAttribute("resourceType", resourceType);
			}
			
			return "function/resource/resource_type_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入资源类型查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toresourcetypelist")
	public String toResourceTypeList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/resource/resource_type_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入资源类型添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertresourcetype")
	public String toInsertResourceType(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/resource/resource_type_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 资源类型添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertresourcetype",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertResourceType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String type_name=(String)request.getParameter("type_name");
			String type_status=(String)request.getParameter("type_status");
			
			if(!CollectionUtil.checkNull(type_name)){
				jsonObj.put("res", "资源类型名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(type_status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			ResourceType ResourceType=new ResourceType();
			ResourceType.setType_name(type_name);
			
			int ii=this.resourceTypeService.checkResourceTypeTypeName(type_name);
			if(ii>0){
				jsonObj.put("res", "类型名称已存在，请从新填写类型名称！");
				return null;
			}
			ResourceType.setType_status(type_status);
			ResourceType.setCreate_adminuser(adminAccount);
			
			int in=this.resourceTypeService.insertResourceType(ResourceType);
			if(in>0){
				state=1;
				memo+="资源类型type_name："+type_name+",添加成功！";
				jsonObj.put("res", "资源类型添加成功！");
			}else{
				memo+="资源类型添加失败！";
				jsonObj.put("res", "资源类型添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}catch(Exception e){
			memo+="资源类型添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源类型修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateresourcetype",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateResourceType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String type_name=(String)request.getParameter("type_name");
			String type_status=(String)request.getParameter("type_status");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(type_name)){
				jsonObj.put("res", "类型名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(type_status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			
			//获得该资源类型信息
			ResourceType resourceType=this.resourceTypeService.findResourceTypeOne(id);
			if(resourceType==null){
				jsonObj.put("res", "资源类型获取失败或资源类型序列号错误！");
				return null;
			}
			resourceType.setType_name(type_name);
			resourceType.setType_status(type_status);
			resourceType.setUpdate_adminuser(adminAccount);
			
			int in=this.resourceTypeService.updateResourceType(resourceType);
			if(in>0){
				state=1;
				memo+="修改资源类型"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改资源类型"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}catch(Exception e){
			memo+="资源类型修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源类型删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeleteresourcetype",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteResourceType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
//			News news=new News();
//			news.setid(id);
//			int ii=this.newsService.findNewsCount(news);
//			if(ii>0){
//				jsonObj.put("res", "该类型在资源信息里面引用到，删除失败！");
//				return null;
//			}
			
			int in=this.resourceTypeService.deleteResourceType(id);
			if(in>0){
				state=1;
				memo+="删除资源类型"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除资源类型"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}catch(Exception e){
			memo+="资源类型删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源类型状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatusresourcetype",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusResourceTypeType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String type_status=(String)request.getParameter("type_status");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(type_status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			ResourceType ResourceType=new ResourceType();
			ResourceType.setId(id);
			ResourceType.setType_status(type_status);
			ResourceType.setUpdate_adminuser(adminAccount);
			
			int in=this.resourceTypeService.updateResourceTypeStatus(ResourceType);
			if(in>0){
				state=1;
				memo+="修改资源类型"+id+"状态"+type_status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改资源类型"+id+"状态"+type_status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}catch(Exception e){
			memo+="资源类型状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源类型查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doresourcetypelist")
	public String doResourceTypeList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String type_status=(String)request.getParameter("type_status");
			String cIndex=request.getParameter("index");

			request.setAttribute("id", id);
			request.setAttribute("type_status", type_status);
			
			
			ResourceType ResourceType=new ResourceType();
			if(CollectionUtil.checkNull(type_status) && !"all".equals(type_status)){
				ResourceType.setType_status(type_status);
			}
			if(CollectionUtil.checkNull(id)){
				ResourceType.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/resourcetype/doresourcetypelist?type_status="+type_status+"&id="+id;
			//总行数
			int dataCount=this.resourceTypeService.findResourceTypeCount(ResourceType);
			//获得该页集合
			List<ResourceType> resourceTypeList=this.resourceTypeService.findResourceTypeList(ResourceType,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(resourceTypeList!=null && resourceTypeList.size()>0){
				request.setAttribute("resourceTypeList", resourceTypeList);
			}
			
			//记录日志
			memo+="资源类型查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
			
		}catch(Exception e){
			memo+="资源类型查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_zylx, memo, state, request);
		}
		return "function/resource/resource_type_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the resourceTypeService
	 */
	public ResourceTypeService getResourceTypeService() {
		return resourceTypeService;
	}


	/**
	 * @param resourceTypeService the resourceTypeService to set
	 */
	public void setResourceTypeService(ResourceTypeService resourceTypeService) {
		this.resourceTypeService = resourceTypeService;
	}
	
}
