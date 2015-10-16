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
import com.manager.function.entity.Bigeye;
import com.manager.function.entity.Resource;
import com.manager.function.entity.TopicList;
import com.manager.function.service.BigeyeModuleService;
import com.manager.function.service.BigeyeService;
import com.manager.function.service.ResourceService;
import com.manager.function.service.TopicListService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("bigeye")
@Controller
public class BigeyeController {

	private Logger logger = Logger.getLogger(BigeyeController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private BigeyeModuleService bigeyeModuleService;
	@Autowired
	private BigeyeService bigeyeService;
	
	@Autowired
	private TopicListService topicListService;
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 进入广告图修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatebigeye")
	public String toUpdateBigeye(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			Bigeye Bigeye=this.bigeyeService.findBigeyeOne(id);
			if(Bigeye!=null){
				if(CollectionUtil.checkNull(Bigeye.getImg_url())){
					Bigeye.setImg_url(CollectionUtil.tobereplace(Bigeye.getImg_url(), 0));
				}
				request.setAttribute("bigeye", Bigeye);
				this.bigeyeModuleService.NoAbolish_BigeyeModuleList(request);
			}
			
			return "function/bigeye/bigeye_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入广告图查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tobigeyelist")
	public String toBigeyeList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/bigeye/bigeye_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入广告图添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertbigeye")
	public String toInsertBigeye(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			this.bigeyeModuleService.NoAbolish_BigeyeModuleList(request);
			return "function/bigeye/bigeye_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 广告图添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertbigeye",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertBigeye(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String img_url=(String)request.getParameter("img_url");
			String link_url=(String)request.getParameter("link_url");
			String content=(String)request.getParameter("content");
			String module_id=(String)request.getParameter("module_id");
			String sort=(String)request.getParameter("sort");
			String status=(String)request.getParameter("status");
			String link_type=(String)request.getParameter("link_type");
			
			
			if(!CollectionUtil.checkNull(img_url)){
				jsonObj.put("res", "图片不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(module_id)){
				jsonObj.put("res", "模块不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			Bigeye Bigeye=new Bigeye();
			Bigeye.setImg_url(CollectionUtil.tobereplace(img_url, 1));
			Bigeye.setModule_id(module_id);
			Bigeye.setStatus(status);
			if(CollectionUtil.checkNull(link_url)){
				Bigeye.setLink_url(link_url);
			}
			if(CollectionUtil.checkNull(content)){
				Bigeye.setContent(content);
			}
			if(CollectionUtil.checkNull(sort)){
				Bigeye.setSort(sort);
			}
			if(CollectionUtil.checkNull(link_type)){
				Bigeye.setLink_type(link_type);
				if("1".equals(link_type)){
					TopicList top=this.topicListService.findTopicListOne(link_url);
					if(top==null || top.getId()==null){
						jsonObj.put("res", "该帖子ID找不到对应的帖子，请从新输入帖子ID！");
						return null;
					}
				}else if("2".equals(link_type)){
					Resource resource=this.resourceService.findResourceOne(link_url);
					if(resource==null || resource.getId()==null){
						jsonObj.put("res", "该资源ID找不到对应的资源，请从新输入资源ID！");
						return null;
					}
				}
			}else{
				Bigeye.setLink_type("0");
			}
			
			
			int in=this.bigeyeService.insertBigeye(Bigeye);
			if(in>0){
				state=1;
				memo+="广告图img_url："+img_url+",添加成功！";
				jsonObj.put("res", "广告图添加成功！");
			}else{
				memo+="广告图添加失败！";
				jsonObj.put("res", "广告图添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 广告图修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatebigeye",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateBigeye(Model model,HttpServletRequest request,HttpServletResponse response
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
			String img_url=(String)request.getParameter("img_url");
			String link_url=(String)request.getParameter("link_url");
			String content=(String)request.getParameter("content");
			String module_id=(String)request.getParameter("module_id");
			String sort=(String)request.getParameter("sort");
			String status=(String)request.getParameter("status");
			String link_type=(String)request.getParameter("link_type");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号ID不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(img_url)){
				jsonObj.put("res", "图片不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(module_id)){
				jsonObj.put("res", "模块不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			//获得该广告图信息
			Bigeye Bigeye=this.bigeyeService.findBigeyeOne(id);
			if(Bigeye==null){
				jsonObj.put("res", "广告图获取失败或广告图序列号错误！");
				return null;
			}
			
			Bigeye.setImg_url(CollectionUtil.tobereplace(img_url, 1));
			Bigeye.setModule_id(module_id);
			Bigeye.setStatus(status);
			if(CollectionUtil.checkNull(link_url)){
				Bigeye.setLink_url(link_url);
			}else{
				Bigeye.setLink_url(null);
			}
			if(CollectionUtil.checkNull(content)){
				Bigeye.setContent(content);
			}else{
				Bigeye.setContent(null);
			}
			if(CollectionUtil.checkNull(sort)){
				Bigeye.setSort(sort);
			}else{
				Bigeye.setSort(null);
			}
			if(CollectionUtil.checkNull(link_type)){
				Bigeye.setLink_type(link_type);
				if("1".equals(link_type)){
					TopicList top=this.topicListService.findTopicListOne(link_url);
					if(top==null || top.getId()==null){
						jsonObj.put("res", "该帖子ID找不到对应的帖子，请从新输入帖子ID！");
						return null;
					}
				}else if("2".equals(link_type)){
					Resource resource=this.resourceService.findResourceOne(link_url);
					if(resource==null || resource.getId()==null){
						jsonObj.put("res", "该资源ID找不到对应的资源，请从新输入资源ID！");
						return null;
					}
				}
			}
			
			int in=this.bigeyeService.updateBigeye(Bigeye);
			if(in>0){
				state=1;
				memo+="修改广告图"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改广告图"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 广告图删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletebigeye",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteBigeye(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			int in=this.bigeyeService.deleteBigeye(id);
			if(in>0){
				state=1;
				memo+="删除广告图"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除广告图"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 广告图状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatusbigeye",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusBigeyeType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			Bigeye Bigeye=new Bigeye();
			Bigeye.setId(id);
			Bigeye.setStatus(status);
			
			int in=this.bigeyeService.updateBigeyeStatus(Bigeye);
			if(in>0){
				state=1;
				memo+="修改广告图"+id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改广告图"+id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 广告图查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dobigeyelist")
	public String doBigeyeList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String module_id=(String)request.getParameter("module_id");
			String status=(String)request.getParameter("status");
			String cIndex=request.getParameter("index");
			String link_type=request.getParameter("link_type");
			

			request.setAttribute("id", id);
			request.setAttribute("status", status);
			request.setAttribute("module_id", module_id);
			request.setAttribute("link_type", link_type);
			
			
			
			Bigeye Bigeye=new Bigeye();
			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
				Bigeye.setStatus(status);
			}
			if(CollectionUtil.checkNull(module_id) && !"all".equals(module_id)){
				Bigeye.setModule_id(module_id);
			}
			if(CollectionUtil.checkNull(link_type) && !"all".equals(link_type)){
				Bigeye.setLink_type(link_type);
			}
			
			if(CollectionUtil.checkNull(id)){
				Bigeye.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/bigeye/dobigeyelist?status="+status+"&id="+id+"&module_id="+module_id+"&link_type="+link_type;
			//总行数
			int dataCount=this.bigeyeService.findBigeyeCount(Bigeye);
			//获得该页集合
			List<Bigeye> BigeyeList=this.bigeyeService.findBigeyeList(Bigeye,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(BigeyeList!=null && BigeyeList.size()>0){
				for(int i=0;i<BigeyeList.size();i++){
					BigeyeList.get(i).setImg_url(CollectionUtil.tobereplace(BigeyeList.get(i).getImg_url(), 0));
				}
				request.setAttribute("bigeyeList", BigeyeList);
			}
			this.bigeyeModuleService.NoAbolish_BigeyeModuleList(request);
			
			//记录日志
			memo+="广告图查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
			
		}catch(Exception e){
			memo+="广告图查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}
		return "function/bigeye/bigeye_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the bigeyeModuleService
	 */
	public BigeyeModuleService getBigeyeModuleService() {
		return bigeyeModuleService;
	}


	/**
	 * @param bigeyeModuleService the bigeyeModuleService to set
	 */
	public void setBigeyeModuleService(BigeyeModuleService bigeyeModuleService) {
		this.bigeyeModuleService = bigeyeModuleService;
	}


	/**
	 * @return the bigeyeService
	 */
	public BigeyeService getBigeyeService() {
		return bigeyeService;
	}


	/**
	 * @param bigeyeService the bigeyeService to set
	 */
	public void setBigeyeService(BigeyeService bigeyeService) {
		this.bigeyeService = bigeyeService;
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


	
	
	
	
}
