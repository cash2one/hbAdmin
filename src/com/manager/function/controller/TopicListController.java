package com.manager.function.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
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
import com.manager.function.service.TopicListService;
import com.manager.function.service.TopicTypeService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("topiclist")
@Controller
public class TopicListController {

	private Logger logger = Logger.getLogger(TopicListController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private TopicListService topicListService;
	
	@Autowired
	private TopicTypeService topicTypeService;
	
	/**
	 * 进入主贴修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatetopiclist")
	public String toUpdateTopicList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			TopicList topicList=this.topicListService.findTopicListOne(id);
			if(topicList!=null){
				if(CollectionUtil.checkNull(topicList.getAffix())){
					topicList.setAffix(CollectionUtil.tobereplace(topicList.getAffix(), 0));
				}
				request.setAttribute("topicList", topicList);
				this.topicTypeService.NoAbolish_TopicTypeList(request);
				return "function/topic/topic_list_update";
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入主贴查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("totopiclistlist")
	public String toTopicListList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/topic/topic_list_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入主贴添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinserttopiclist")
	public String toInsertTopicList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			this.topicTypeService.NoAbolish_TopicTypeList(request);
			return "function/topic/topic_list_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 主贴添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinserttopiclist",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertTopicList(Model model,HttpServletRequest request,HttpServletResponse response
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
			//String id=(String)request.getParameter("id");
			String topic_typeId =(String)request.getParameter("topic_typeId");
			String title =(String)request.getParameter("title");
			String content =(String)request.getParameter("content");
			String ip =(String)request.getParameter("ip");
//			String ip_address =(String)request.getParameter("ip_address");
			String uid =(String)request.getParameter("uid");
//			String createtime =(String)request.getParameter("createtime");
			String countback =(String)request.getParameter("countback");
			String label =(String)request.getParameter("label");
			String countbrowse =(String)request.getParameter("countbrowse");
			String affix =(String)request.getParameter("affix");
			String status =(String)request.getParameter("status");
			
			
			if(!CollectionUtil.checkNull(topic_typeId)){
				jsonObj.put("res", "请选择帖子类型！");
				return null;
			}
			if(!CollectionUtil.checkNull(title)){
				jsonObj.put("res", "帖子标题不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(content)){
				jsonObj.put("res", "帖子内容不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(ip)){
				jsonObj.put("res", "IP不能为空！");
				return null;
			}
//			if(!CollectionUtil.checkNull(ip_address)){
//				jsonObj.put("res", "IP所在地不能为空！");
//				return null;
//			}
			if(!CollectionUtil.checkNull(uid)){
				jsonObj.put("res", "发帖用户ID不能为空！");
				return null;
			}
//			if(!CollectionUtil.checkNull(createtime)){
//				jsonObj.put("res", "发帖时间不能为空！");
//				return null;
//			}
			if(!CollectionUtil.checkNull(countback)){
				countback="0";
			}
			if(!CollectionUtil.checkNull(label)){
				jsonObj.put("res", "标签不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(countbrowse)){
				countbrowse="0";
			}
			String img_size=null;
			if(!CollectionUtil.checkNull(affix)){
				affix=null;
			}else{
				affix=CollectionUtil.tobereplace(affix, 1);
				BufferedImage sourceImg =ImageIO.read(new File(affix));
				int width = sourceImg.getWidth();
				int height = sourceImg.getHeight();
				img_size=width+"|"+height;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			TopicList topicList=new TopicList();
			topicList.setTopic_typeId(topic_typeId);
			topicList.setTitle(title);
			topicList.setContent(content);
			topicList.setIp(ip);
//			topicList.setIp_address(ip_address);
			topicList.setUid(uid);
//			topicList.setCreatetime(createtime);
			topicList.setCountback(countback);
			topicList.setLabel(label);
			topicList.setCountbrowse(countbrowse);
			topicList.setAffix(affix);
			topicList.setStatus(status);
			topicList.setImg_size(img_size);
			
			int in=this.topicListService.insertTopicList(topicList);
			if(in>0){
				state=1;
				memo+="添加主贴title："+title+"，成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="添加主贴title："+title+"，失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="主贴添加 异常："+e.getMessage();
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
	 * 主贴修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatetopiclist",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateTopicList(Model model,HttpServletRequest request,HttpServletResponse response
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
			String topic_typeId =(String)request.getParameter("topic_typeId");
			String title =(String)request.getParameter("title");
			String content =(String)request.getParameter("content");
			String ip =(String)request.getParameter("ip");
			String ip_address =(String)request.getParameter("ip_address");
			String uid =(String)request.getParameter("uid");
			String createtime =(String)request.getParameter("createtime");
			String countback =(String)request.getParameter("countback");
			String label =(String)request.getParameter("label");
			String countbrowse =(String)request.getParameter("countbrowse");
			String affix =(String)request.getParameter("affix");
			String status =(String)request.getParameter("status");
			String type_name =(String)request.getParameter("type_name");
			
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			//获得该主贴信息
			TopicList topicList=this.topicListService.findTopicListOne(id);
			if(topicList==null){
				jsonObj.put("res", "主贴获取失败或主贴序列号错误！");
				return null;
			}
			topicList.setLabel(label);
			topicList.setStatus(status);
			
			int in=this.topicListService.updateTopicList(topicList);
			if(in>0){
				state=1;
				memo+="修改主贴"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改主贴"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="主贴修改 异常："+e.getMessage();
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
	 * 主贴删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletetopiclist",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteTopicList(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			int in=this.topicListService.deleteTopicList(id);
			if(in>0){
				state=1;
				memo+="删除主贴"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除主贴"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="主贴删除 异常："+e.getMessage();
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
	 * 主贴状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatustopiclist",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusTopicListType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			TopicList topicList=new TopicList();
			topicList.setId(id);
			topicList.setStatus(status);
			
			int in=this.topicListService.updateTopicListStatus(topicList);
			if(in>0){
				state=1;
				memo+="修改主贴"+id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改主贴"+id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="主贴状态修改 异常："+e.getMessage();
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
	 * 主贴状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatelabeltopiclist",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateLabelTopicListType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String label=(String)request.getParameter("label");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(label)){
				jsonObj.put("res", "标签不能为空！");
				return null;
			}
			
			List<TopicList> topiclistList=new ArrayList<TopicList>();
			
			String[] id_arry=id.split(",");
			String[] label_arry=label.split(",");
			TopicList topicList=null;
			for(int i=0;i<id_arry.length;i++){
				topicList=new TopicList();
				if(CollectionUtil.checkNull(id_arry[i]) && CollectionUtil.checkNull(label_arry[i])){
					topicList.setId(id_arry[i]);
					topicList.setLabel(label_arry[i]);
					topiclistList.add(topicList);
				}
			}
			
			int in=this.topicListService.updateBatchupdateLabel(topiclistList);
			if(in>0){
				state=1;
				memo+="修改主贴"+id+"标签"+label+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改主贴"+id+"标签"+label+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="主贴标签修改 异常："+e.getMessage();
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
	 * 主贴查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dotopiclistlist")
	public String doTopicListList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String topic_typeId =(String)request.getParameter("topic_typeId");
			String title =(String)request.getParameter("title");
			String content =(String)request.getParameter("content");
			String ip =(String)request.getParameter("ip");
			String uid =(String)request.getParameter("uid");
			String label =(String)request.getParameter("label");
			String status =(String)request.getParameter("status");
			String cIndex=request.getParameter("index");
			
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			title=title==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(title.getBytes("ISO-8859-1"),"UTF-8"):title,"UTF-8");
			content=content==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(content.getBytes("ISO-8859-1"),"UTF-8"):content,"UTF-8");
			ip=ip==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(ip.getBytes("ISO-8859-1"),"UTF-8"):ip,"UTF-8");
			uid=uid==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(uid.getBytes("ISO-8859-1"),"UTF-8"):uid,"UTF-8");
			
			
			topic_typeId=topic_typeId==null?"":topic_typeId;
			label=label==null?"":label;
			status=status==null?"":status;
			
			request.setAttribute("id", id);
			request.setAttribute("topic_typeId", topic_typeId);
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("ip", ip);
			request.setAttribute("uid", uid);
			request.setAttribute("label", label);
			request.setAttribute("status", status);
			
			
			TopicList topicList=new TopicList();
			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
				topicList.setStatus(status);
			}
			if(CollectionUtil.checkNull(topic_typeId) && !"all".equals(topic_typeId)){
				topicList.setTopic_typeId(topic_typeId);
			}
			if(CollectionUtil.checkNull(label) && !"all".equals(label)){
				topicList.setLabel(label);
			}
			if(CollectionUtil.checkNull(id)){
				topicList.setId(id);
			}
			if(CollectionUtil.checkNull(title)){
				topicList.setTitle(title);
			}
			if(CollectionUtil.checkNull(content)){
				topicList.setContent(content);
			}
			if(CollectionUtil.checkNull(ip)){
				topicList.setIp(ip);
			}
			if(CollectionUtil.checkNull(uid)){
				topicList.setUid(uid);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/topiclist/dotopiclistlist?status="+status
			+"&id="+java.net.URLEncoder.encode(id,"UTF-8")
			+"&title="+java.net.URLEncoder.encode(title,"UTF-8")
			+"&content="+java.net.URLEncoder.encode(content,"UTF-8")
			+"&ip="+java.net.URLEncoder.encode(ip,"UTF-8")
			+"&uid="+java.net.URLEncoder.encode(uid,"UTF-8")
			+"&topic_typeId="+topic_typeId
			+"&label="+label;
			//总行数
			int dataCount=this.topicListService.findTopicListCount(topicList);
			//获得该页集合
			List<TopicList> topicListList=this.topicListService.findTopicListList(topicList,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(topicListList!=null && topicListList.size()>0){
				request.setAttribute("topiclistList", topicListList);
			}
			
			this.topicTypeService.NoAbolish_TopicTypeList(request);
			
			//记录日志
			memo+="主贴查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
			
		}catch(Exception e){
			memo+="主贴查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}
		return "function/topic/topic_list_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the TopicListService
	 */
	public TopicListService getTopicListService() {
		return topicListService;
	}


	/**
	 * @param TopicListService the TopicListService to set
	 */
	public void setTopicListService(TopicListService topicListService) {
		this.topicListService = topicListService;
	}


	/**
	 * @return the topicTypeService
	 */
	public TopicTypeService getTopicTypeService() {
		return topicTypeService;
	}


	/**
	 * @param topicTypeService the topicTypeService to set
	 */
	public void setTopicTypeService(TopicTypeService topicTypeService) {
		this.topicTypeService = topicTypeService;
	}
	
}
