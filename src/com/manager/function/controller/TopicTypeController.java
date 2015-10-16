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
import com.manager.function.entity.TopicList;
import com.manager.function.entity.TopicType;
import com.manager.function.service.TopicListService;
import com.manager.function.service.TopicTypeService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("topictype")
@Controller
public class TopicTypeController {

	private Logger logger = Logger.getLogger(TopicTypeController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private TopicTypeService topicTypeService;
	
	@Autowired
	private TopicListService topicListService;
	
	
	/**
	 * 进入帖子类型修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatetopictype")
	public String toUpdateTopicType(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			TopicType topicType=this.topicTypeService.findTopicTypeOne(id);
			if(topicType!=null){
				request.setAttribute("topicType", topicType);
			}
			
			return "function/topic/topic_type_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入帖子类型查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("totopictypelist")
	public String toTopicTypeList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/topic/topic_type_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入帖子类型添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinserttopictype")
	public String toInsertTopicType(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/topic/topic_type_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 帖子类型添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinserttopictype",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertTopicType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String name=(String)request.getParameter("name");
			String status=(String)request.getParameter("status");
			String sort=(String)request.getParameter("sort");
			
			if(!CollectionUtil.checkNull(name)){
				jsonObj.put("res", "帖子类型名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(sort)){
				jsonObj.put("res", "排序不能为空！");
				return null;
			}
			TopicType topicType=new TopicType();
			topicType.setName(name);
			
			int ii=this.topicTypeService.checkTopicTypeName(name);
			if(ii>0){
				jsonObj.put("res", "类型名称已存在，请从新填写类型名称！");
				return null;
			}
			
			int ii2=this.topicTypeService.checkTopicTypeSort(sort);
			if(ii2>0){
				jsonObj.put("res", "排序已存在，请从新填写排序！");
				return null;
			}
			topicType.setSort(sort);
			topicType.setStatus(status);
			
			int in=this.topicTypeService.insertTopicType(topicType);
			if(in>0){
				state=1;
				memo+="帖子类型name："+name+",添加成功！";
				jsonObj.put("res", "帖子类型添加成功！");
			}else{
				memo+="帖子类型添加失败！";
				jsonObj.put("res", "帖子类型添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="帖子类型添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 帖子类型修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatetopictype",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateTopicType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String name=(String)request.getParameter("name");
			String status=(String)request.getParameter("status");
			String sort=(String)request.getParameter("sort");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(name)){
				jsonObj.put("res", "类型名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(sort)){
				jsonObj.put("res", "排序不能为空！");
				return null;
			}
			
			//获得该帖子类型信息
			TopicType topicType=this.topicTypeService.findTopicTypeOne(id);
			if(topicType==null){
				jsonObj.put("res", "帖子类型获取失败或帖子类型序列号错误！");
				return null;
			}
			
			int ii=this.topicTypeService.checkTopicTypeName(id,name);
			if(ii>0){
				jsonObj.put("res", "类型名称已存在，请从新填写类型名称！");
				return null;
			}
			
			int ii2=this.topicTypeService.checkTopicTypeSort(id,sort);
			if(ii2>0){
				jsonObj.put("res", "排序已存在，请从新填写排序！");
				return null;
			}
			topicType.setSort(sort);
			topicType.setName(name);
			topicType.setStatus(status);
			
			int in=this.topicTypeService.updateTopicType(topicType);
			if(in>0){
				state=1;
				memo+="修改帖子类型"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改帖子类型"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="帖子类型修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 帖子类型删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletetopictype",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteTopicType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			TopicList TopicList=new TopicList();
			TopicList.setTopic_typeId(id);
			int ii=this.topicListService.findTopicListCount(TopicList);
			if(ii>0){
				jsonObj.put("res", "该类型在帖子信息里面引用到，删除失败！");
				return null;
			}
			
			int in=this.topicTypeService.deleteTopicType(id);
			if(in>0){
				state=1;
				memo+="删除帖子类型"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除帖子类型"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="帖子类型删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 帖子类型状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatustopictype",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusTopicTypeType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			TopicType topicType=new TopicType();
			topicType.setId(id);
			topicType.setStatus(status);
			
			int in=this.topicTypeService.updateTopicTypeStatus(topicType);
			if(in>0){
				state=1;
				memo+="修改帖子类型"+id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改帖子类型"+id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="帖子类型状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 帖子类型查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dotopictypelist")
	public String doTopicTypeList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String status=(String)request.getParameter("status");
			String cIndex=request.getParameter("index");

			request.setAttribute("id", id);
			request.setAttribute("status", status);
			
			
			TopicType topicType=new TopicType();
			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
				topicType.setStatus(status);
			}
			if(CollectionUtil.checkNull(id)){
				topicType.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/topictype/dotopictypelist?status="+status+"&id="+id;
			//总行数
			int dataCount=this.topicTypeService.findTopicTypeCount(topicType);
			//获得该页集合
			List<TopicType> topicTypeList=this.topicTypeService.findTopicTypeList(topicType,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(topicTypeList!=null && topicTypeList.size()>0){
				request.setAttribute("topicTypeList", topicTypeList);
			}
			
			//记录日志
			memo+="帖子类型查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
			
		}catch(Exception e){
			memo+="帖子类型查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}
		return "function/topic/topic_type_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the TopicTypeService
	 */
	public TopicTypeService getTopicTypeService() {
		return topicTypeService;
	}


	/**
	 * @param TopicTypeService the TopicTypeService to set
	 */
	public void setTopicTypeService(TopicTypeService topicTypeService) {
		this.topicTypeService = topicTypeService;
	}


	/**
	 * @return the topicListService
	 */
	public TopicListService getTopicListService() {
		return topicListService;
	}


	/**
	 * @param topicListService the topicListService to set
	 */
	public void setTopicListService(TopicListService topicListService) {
		this.topicListService = topicListService;
	}
	
}
