package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.manager.function.entity.Resource;
import com.manager.function.entity.WeekdayResource;
import com.manager.function.service.GlobalHobbyService;
import com.manager.function.service.GlobalLevelService;
import com.manager.function.service.GlobalPropertyService;
import com.manager.function.service.ResourceService;
import com.manager.function.service.ResourceTypeService;
import com.manager.function.service.WeekdayResourceService;
import com.manager.function.service.WeekdayService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("weekdayresource")
@Controller
public class WeekdayResourceController {

	private Logger logger = Logger.getLogger(WeekdayResourceController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private WeekdayResourceService weekdayResourceService;
	
	@Autowired
	private WeekdayService weekdayService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@Autowired
	private GlobalHobbyService globalHobbyService;
	
	@Autowired
	private GlobalLevelService globalLevelService;
	
	@Autowired
	private GlobalPropertyService globalPropertyService;
	
	/**
	 * 加载下拉框选项
	 */
	public void loadSelectOption(HttpServletRequest request){
		//资源类型
		this.resourceTypeService.NoAbolish_ResourceTypeList(request);
		//兴趣
		this.globalHobbyService.NoAbolish_GlobalHobbyList(request);
		//阶段
		this.globalLevelService.NoAbolish_GlobalLevelList(request);
		//属性
		this.globalPropertyService.NoAbolish_GlobalPropertyList(request);
		
	}
	
	
	
	/**
	 * 每周推荐添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertweekdayresource",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertWeekdayResource(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String resource_id_list=(String)request.getParameter("resource_id_list");
			String weekday_id=(String)request.getParameter("weekday_id");	
			if(!CollectionUtil.checkNull(weekday_id)){
				jsonObj.put("res", "周期不能为空！");
				return null;
			}
			//判断周期是否存在
			int ii=this.weekdayService.checkWeekday(weekday_id);
			if(ii==0){
				jsonObj.put("res", "该周期不存在，添加失败！");
				return null;
			}
			
			if(!CollectionUtil.checkNull(resource_id_list)){
				jsonObj.put("res", "请选择添加的资源!");
				return null;
			}
			//判断资源是否存在
			
			String[] resource_id_obj=resource_id_list.split(";");
			
			List<WeekdayResource> weekdayResourceList=new ArrayList<WeekdayResource>();
			if(resource_id_obj!=null && resource_id_obj.length>0){
				WeekdayResource w=null;
				for(String id:resource_id_obj){
					w=new WeekdayResource();
					w.setResource_id(id);
					w.setWeekday_id(weekday_id);
					w.setCreate_adminuser(adminAccount);
					weekdayResourceList.add(w);
				}
			}
			
			int in=this.weekdayResourceService.batchInsertWeekdayResource(weekdayResourceList);
			if(in>0){
				state=1;
				memo+="每周推荐添加成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="每周推荐添加失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}catch(Exception e){
			memo+="每周推荐添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 每周推荐删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeleteweekdayresource",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteWeekdayResource(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			int in=this.weekdayResourceService.deleteWeekdayResource(id);
			if(in>0){
				state=1;
				memo+="删除每周推荐"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除每周推荐"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}catch(Exception e){
			memo+="每周推荐删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源查询与每周推荐查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertweekdayresource")
	public String doResourceList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		try{
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String id=(String)request.getParameter("id");
			String resource_status=(String)request.getParameter("resource_status");
			String resource_type_id=(String)request.getParameter("resource_type_id");
			String hobby_id=(String)request.getParameter("hobby_id");
			String level_id=(String)request.getParameter("level_id");
			String property_id=(String)request.getParameter("property_id");
			String resource_content=(String)request.getParameter("resource_content");
			String weekday_id=(String)request.getParameter("weekday_id");
			String cIndex=request.getParameter("index");
			
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			weekday_id=weekday_id==null?"1":java.net.URLDecoder.decode(cIndex!=null?new String(weekday_id.getBytes("ISO-8859-1"),"UTF-8"):weekday_id,"UTF-8");
			resource_content=resource_content==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(resource_content.getBytes("ISO-8859-1"),"UTF-8"):resource_content,"UTF-8");

			resource_type_id=resource_type_id==null?"":resource_type_id;
			request.setAttribute("weekday_id", weekday_id);
			
			WeekdayResource WeekdayResource=new WeekdayResource();
			if(!CollectionUtil.checkNull(weekday_id)){
				request.setAttribute("msg", "周期不能为空");
			}else{
				WeekdayResource.setWeekday_id(weekday_id);
			}
			
			request.setAttribute("id", id);
			request.setAttribute("resource_status", resource_status);
			request.setAttribute("resource_type_id", resource_type_id);
			request.setAttribute("hobby_id", hobby_id);
			request.setAttribute("level_id", level_id);
			request.setAttribute("property_id", property_id);
			request.setAttribute("resource_content", resource_content);
			request.setAttribute("weekday_id", weekday_id);
			
			
			Resource Resource=new Resource();
			if(CollectionUtil.checkNull(resource_status) && !"all".equals(resource_status)){
				Resource.setResource_status(resource_status);
			}
			if(CollectionUtil.checkNull(resource_type_id) && !"all".equals(resource_type_id)){
				Resource.setResource_type_id(resource_type_id);
			}
			if(CollectionUtil.checkNull(hobby_id) && !"all".equals(hobby_id)){
				Resource.setLanguage_level(hobby_id);
			}
			if(CollectionUtil.checkNull(level_id) && !"all".equals(level_id)){
				Resource.setLevel_id(level_id);
			}
			if(CollectionUtil.checkNull(property_id) && !"all".equals(property_id)){
				Resource.setProperty_id(property_id);
			}
			if(CollectionUtil.checkNull(id)){
				Resource.setId(id);
			}
			if(CollectionUtil.checkNull(weekday_id)){
				Resource.setWeekday_id(weekday_id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/weekdayresource/toinsertweekdayresource?"
			+"resource_status="+resource_status
			+"&id="+java.net.URLEncoder.encode(id,"UTF-8")
			+"&resource_content="+java.net.URLEncoder.encode(resource_content,"UTF-8")
			+"&weekday_id="+java.net.URLEncoder.encode(weekday_id,"UTF-8")
			+"&resource_type_id="+resource_type_id
			+"&hobby_id="+hobby_id
			+"&level_id="+level_id
			+"&property_id="+property_id
			;
			//总行数
			int dataCount=this.resourceService.findResourceWeekdayCount(Resource);
			//获得该页集合
			List<Resource> resourceList=this.resourceService.findResourceWeekdayList(Resource,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(resourceList!=null && resourceList.size()>0){
				request.setAttribute("resourceList", resourceList);
			}
			
			//获得该页集合
			List<WeekdayResource> WeekdayResourceList=this.weekdayResourceService.findWeekdayResourceList(WeekdayResource,2);
			if(WeekdayResourceList!=null && WeekdayResourceList.size()>0){
				request.setAttribute("weekdayresourceList", WeekdayResourceList);
			}
			
			
			loadSelectOption(request);
			
			List<WeekdayResource> wrlist=this.weekdayResourceService.SelectWeekdayResourceList();
			request.setAttribute("wrlist", wrlist);
			
			//记录日志
			memo+="每周推荐 资源查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
			
		}catch(Exception e){
			memo+="每周推荐 资源查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}
		return "function/weekday/weekday_resource_add";
	}
	
	/**
	 * 每周推荐查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doweekdayresourceinfolist")
	public String doWeekdayResourceInfoList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String weekday_id=(String)request.getParameter("weekday_id");
			String resource_type_id=(String)request.getParameter("resource_type_id");
			String baby_id=(String)request.getParameter("baby_id");
			String cIndex=request.getParameter("index");

			weekday_id=weekday_id==null?"":weekday_id;
			baby_id=baby_id==null?"":baby_id;
			resource_type_id=resource_type_id==null?"":resource_type_id;
			request.setAttribute("weekday_id", weekday_id);
			request.setAttribute("resource_type_id", resource_type_id);
			request.setAttribute("baby_id", baby_id);
			
			WeekdayResource WeekdayResource=new WeekdayResource();
//			if(!CollectionUtil.checkNull(weekday_id)){
//				request.setAttribute("msg", "周期不能为空");
//			}else{
//				WeekdayResource.setWeekday_id(weekday_id);
//			}
			if(CollectionUtil.checkNull(baby_id)){
				WeekdayResource.setBaby_id(baby_id);
			}
			if(CollectionUtil.checkNull(weekday_id) && !"all".equals(weekday_id)){
				WeekdayResource.setWeekday_id(weekday_id);
			}
			if(CollectionUtil.checkNull(resource_type_id) && !"all".equals(resource_type_id)){
				WeekdayResource.setResource_type_id(resource_type_id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/weekdayresource/doweekdayresourceinfolist?weekday_id="+weekday_id+"&resource_type_id="+resource_type_id+"&baby_id="+baby_id;
			//总行数
			int dataCount=this.weekdayResourceService.findWeekdayResourceCount(WeekdayResource,2);
			//获得该页集合
			List<WeekdayResource> WeekdayResourceList=this.weekdayResourceService.findWeekdayResourceList(WeekdayResource,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT,2);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(WeekdayResourceList!=null && WeekdayResourceList.size()>0){
				request.setAttribute("weekdayresourceList", WeekdayResourceList);
			}
			
			//每周推荐
			this.resourceTypeService.NoAbolish_ResourceTypeList(request);
			
			List<WeekdayResource> wrlist=this.weekdayResourceService.SelectWeekdayResourceList();
			request.setAttribute("wrlist", wrlist);
			
			//记录日志
			memo+="每周推荐查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
			
		}catch(Exception e){
			memo+="每周推荐查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}
		return "function/weekday/weekday_resource_info_list";
	}
	
	/**
	 * 每周推荐统计查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doweekdaystatistics")
	public String doWeekdayStatistics(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String weekday_id=(String)request.getParameter("weekday_id");
			String cIndex=request.getParameter("index");

			weekday_id=weekday_id==null?"":weekday_id;
			request.setAttribute("weekday_id", weekday_id);
			
			
			WeekdayResource WeekdayResource=new WeekdayResource();
			if(CollectionUtil.checkNull(weekday_id) && !"all".equals(weekday_id)){
				WeekdayResource.setWeekday_id(weekday_id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/weekdayresource/doweekdaystatistics?weekday_id="+weekday_id;
			//总行数
			int dataCount=this.weekdayResourceService.findWeekdayResourceCount(WeekdayResource,3);
			//获得该页集合
			List<WeekdayResource> WeekdayResourceList=this.weekdayResourceService.findWeekdayResourceList(WeekdayResource,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT,3);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(WeekdayResourceList!=null && WeekdayResourceList.size()>0){
				request.setAttribute("weekdayresourceList", WeekdayResourceList);
			}
			
			List<WeekdayResource> wrlist=this.weekdayResourceService.SelectWeekdayResourceList();
			request.setAttribute("wrlist", wrlist);
			
			//记录日志
			memo+="每周推荐统计查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
			
		}catch(Exception e){
			memo+="每周推荐统计查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}
		return "function/statistics/weekday_statistics";
	}
	
	
	/**
	 * 每周推荐查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doweekdayresourcelist")
	public String doWeekdayResourceList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String weekday_id=(String)request.getParameter("weekday_id");
			String cIndex=request.getParameter("index");

			weekday_id=weekday_id==null?"":weekday_id;
			request.setAttribute("weekday_id", weekday_id);
			
			
			WeekdayResource WeekdayResource=new WeekdayResource();
			if(CollectionUtil.checkNull(weekday_id)){
				WeekdayResource.setWeekday_id(weekday_id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/weekdayresource/doweekdayresourcelist?weekday_id="+weekday_id;
			//总行数
			int dataCount=this.weekdayResourceService.findWeekdayResourceCount(WeekdayResource,1);
			//获得该页集合
			List<WeekdayResource> WeekdayResourceList=this.weekdayResourceService.findWeekdayResourceList(WeekdayResource,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT,1);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(WeekdayResourceList!=null && WeekdayResourceList.size()>0){
				request.setAttribute("weekdayresourceList", WeekdayResourceList);
			}
			
			//记录日志
			memo+="每周推荐查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
			
		}catch(Exception e){
			memo+="每周推荐查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}
		return "function/weekday/weekday_resource_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the WeekdayResourceService
	 */
	public WeekdayResourceService getWeekdayResourceService() {
		return weekdayResourceService;
	}


	/**
	 * @param WeekdayResourceService the WeekdayResourceService to set
	 */
	public void setWeekdayResourceService(WeekdayResourceService WeekdayResourceService) {
		this.weekdayResourceService = WeekdayResourceService;
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


	/**
	 * @return the resourceService
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}


	/**
	 * @param resourceService the resourceService to set
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}


	/**
	 * @return the globalHobbyService
	 */
	public GlobalHobbyService getGlobalHobbyService() {
		return globalHobbyService;
	}


	/**
	 * @param globalHobbyService the globalHobbyService to set
	 */
	public void setGlobalHobbyService(GlobalHobbyService globalHobbyService) {
		this.globalHobbyService = globalHobbyService;
	}


	/**
	 * @return the globalLevelService
	 */
	public GlobalLevelService getGlobalLevelService() {
		return globalLevelService;
	}


	/**
	 * @param globalLevelService the globalLevelService to set
	 */
	public void setGlobalLevelService(GlobalLevelService globalLevelService) {
		this.globalLevelService = globalLevelService;
	}


	/**
	 * @return the globalPropertyService
	 */
	public GlobalPropertyService getGlobalPropertyService() {
		return globalPropertyService;
	}


	/**
	 * @param globalPropertyService the globalPropertyService to set
	 */
	public void setGlobalPropertyService(GlobalPropertyService globalPropertyService) {
		this.globalPropertyService = globalPropertyService;
	}


	/**
	 * @return the weekdayService
	 */
	public WeekdayService getWeekdayService() {
		return weekdayService;
	}


	/**
	 * @param weekdayService the weekdayService to set
	 */
	public void setWeekdayService(WeekdayService weekdayService) {
		this.weekdayService = weekdayService;
	}
	
}
