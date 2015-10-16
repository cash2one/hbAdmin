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
import com.manager.function.entity.TopicReply;
import com.manager.function.service.TopicListService;
import com.manager.function.service.TopicReplyService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("topicreply")
@Controller
public class TopicReplyController {

	private Logger logger = Logger.getLogger(TopicReplyController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private TopicReplyService topicReplyService;
	
	@Autowired
	private TopicListService topicListService;
	
	/**
	 * 进入回帖修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatetopicreply")
	public String toUpdateTopicReply(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			TopicReply topicReply=this.topicReplyService.findTopicReplyOne(id);
			if(topicReply!=null){
				request.setAttribute("topicReply", topicReply);
			}
			
			return "function/topic/topic_reply_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入回帖查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("totopicreplylist")
	public String toTopicReplyList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/topic/topic_reply_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入回帖添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinserttopicreply")
	public String toInsertTopicReply(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/topic/topic_reply_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 回帖添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinserttopicreply",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertTopicReply(Model model,HttpServletRequest request,HttpServletResponse response
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
				jsonObj.put("res", "回帖名称不能为空！");
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
			TopicReply topicReply=new TopicReply();
			
			topicReply.setSort(sort);
			topicReply.setStatus(status);
			
			int in=this.topicReplyService.insertTopicReply(topicReply);
			if(in>0){
				state=1;
				memo+="回帖name："+name+",添加成功！";
				jsonObj.put("res", "回帖添加成功！");
			}else{
				memo+="回帖添加失败！";
				jsonObj.put("res", "回帖添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="回帖添加 异常："+e.getMessage();
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
	 * 回帖修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatetopicReply",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateTopicReply(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			//获得该回帖信息
			TopicReply topicReply=this.topicReplyService.findTopicReplyOne(id);
			if(topicReply==null){
				jsonObj.put("res", "回帖获取失败或回帖序列号错误！");
				return null;
			}
			
			topicReply.setSort(sort);
			topicReply.setStatus(status);
			
			int in=this.topicReplyService.updateTopicReply(topicReply);
			if(in>0){
				state=1;
				memo+="修改回帖"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改回帖"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="回帖修改 异常："+e.getMessage();
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
	 * 回帖删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletetopicreply",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteTopicReply(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			int in=this.topicReplyService.deleteTopicReply(id);
			if(in>0){
				state=1;
				memo+="删除回帖"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除回帖"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="回帖删除 异常："+e.getMessage();
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
	 * 回帖状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatustopicreply",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusTopicReplyType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			TopicReply topicReply=new TopicReply();
			topicReply.setId(id);
			topicReply.setStatus(status);
			
			int in=this.topicReplyService.updateTopicReplyStatus(topicReply);
			if(in>0){
				state=1;
				memo+="修改回帖"+id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改回帖"+id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}catch(Exception e){
			memo+="回帖状态修改 异常："+e.getMessage();
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
	 * 回帖查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dotopicreplylist")
	public String doTopicReplyList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String topic_id=(String)request.getParameter("topic_id");
			String sort=(String)request.getParameter("sort");
			String uid=(String)request.getParameter("uid");
			String content=(String)request.getParameter("content");
			String status=(String)request.getParameter("status");
			String cIndex=request.getParameter("index");

			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			topic_id=topic_id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(topic_id.getBytes("ISO-8859-1"),"UTF-8"):topic_id,"UTF-8");
			sort=sort==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(sort.getBytes("ISO-8859-1"),"UTF-8"):sort,"UTF-8");
			uid=uid==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(uid.getBytes("ISO-8859-1"),"UTF-8"):uid,"UTF-8");
			content=content==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(content.getBytes("ISO-8859-1"),"UTF-8"):content,"UTF-8");
			status=status==null?"":status;
			
			request.setAttribute("id", id);
			request.setAttribute("topic_id", topic_id);
			request.setAttribute("sort", sort);
			request.setAttribute("uid", uid);
			request.setAttribute("content", content);
			request.setAttribute("status", status);
			
			
			TopicReply topicReply=new TopicReply();
			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
				topicReply.setStatus(status);
			}
			if(CollectionUtil.checkNull(id)){
				topicReply.setId(id);
			}
			if(CollectionUtil.checkNull(topic_id)){
				topicReply.setTopic_id(topic_id);
			}else{
				return null;
			}
			if(CollectionUtil.checkNull(sort)){
				topicReply.setSort(sort);
			}
			if(CollectionUtil.checkNull(uid)){
				topicReply.setUid(uid);
			}
			if(CollectionUtil.checkNull(content)){
				topicReply.setContent(content);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/topicreply/dotopicreplylist?status="+status
			+"&id="+java.net.URLEncoder.encode(id,"UTF-8")
			+"&topic_id="+java.net.URLEncoder.encode(topic_id,"UTF-8")
			+"&content="+java.net.URLEncoder.encode(content,"UTF-8")
			+"&sort="+java.net.URLEncoder.encode(sort,"UTF-8")
			+"&uid="+java.net.URLEncoder.encode(uid,"UTF-8");
			//总行数
			int dataCount=this.topicReplyService.findTopicReplyCount(topicReply);
			//获得该页集合
			List<TopicReply> topicReplyList=this.topicReplyService.findTopicReplyList(topicReply,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(topicReplyList!=null && topicReplyList.size()>0){
				request.setAttribute("topicreplyList", topicReplyList);
			}
			
			TopicList topicList=this.topicListService.findTopicListOne(topic_id);
			if(topicList!=null){
				if(CollectionUtil.checkNull(topicList.getAffix())){
					topicList.setAffix(CollectionUtil.tobereplace(topicList.getAffix(), 0));
				}
				request.setAttribute("topicList", topicList);
			}
			
			//记录日志
			memo+="回帖查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
			
		}catch(Exception e){
			memo+="回帖查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_tzgl, memo, state, request);
		}
		return "function/topic/topic_reply_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the TopicReplyService
	 */
	public TopicReplyService getTopicReplyService() {
		return topicReplyService;
	}


	/**
	 * @param TopicReplyService the TopicReplyService to set
	 */
	public void setTopicReplyService(TopicReplyService topicReplyService) {
		this.topicReplyService = topicReplyService;
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
