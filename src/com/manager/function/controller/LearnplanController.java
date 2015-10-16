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
import com.manager.function.entity.Learnplan;
import com.manager.function.service.LearnplanService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("learnplan")
@Controller
public class LearnplanController {

	private Logger logger = Logger.getLogger(LearnplanController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private LearnplanService learnplanService;
	
	
	/**
	 * 进入计划添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertlearnplan")
	public String toInsertLearnplan(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/learnplan/learnplan_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入计划修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatelearnplan")
	public String toUpdateLearnplan(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			Learnplan learnplan=this.learnplanService.findLearnplanOne(id);
			request.setAttribute("learnplan", learnplan);
			return "function/learnplan/learnplan_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 计划添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertlearnplan",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertLearnplan(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String plan_content=(String)request.getParameter("plan_content");
			String plan_summary=(String)request.getParameter("plan_summary");
			String plan_weekday=(String)request.getParameter("plan_weekday");
			String listen=(String)request.getParameter("listen");
			String watch=(String)request.getParameter("watch");
			String read=(String)request.getParameter("read");
			String play=(String)request.getParameter("play");
			
			if(!CollectionUtil.checkNull(plan_content)){
				jsonObj.put("res", "计划内容不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(plan_weekday)){
				jsonObj.put("res", "执行周期不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(listen)){
				listen="0";
			}
			if(!CollectionUtil.checkNull(watch)){
				watch="0";
			}
			if(!CollectionUtil.checkNull(read)){
				read="0";
			}
			if(!CollectionUtil.checkNull(play)){
				play="0";
			}
			
			
			Learnplan learnplan=new Learnplan();
			learnplan.setCreate_adminuser(adminAccount);
			learnplan.setListen(listen);
			learnplan.setPlay(play);
			learnplan.setRead(read);
			learnplan.setWatch(watch);
			learnplan.setPlan_content(plan_content);
			learnplan.setPlan_summary(plan_summary);
			learnplan.setPlan_weekday(plan_weekday);
			
			int in=this.learnplanService.insertLearnplan(learnplan);
			if(in>0){
				state=1;
				memo+="计划添加成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="计划添加失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}catch(Exception e){
			memo+="计划添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 计划修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatelearnplan",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateLearnplan(Model model,HttpServletRequest request,HttpServletResponse response
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
			String plan_content=(String)request.getParameter("plan_content");
			String plan_summary=(String)request.getParameter("plan_summary");
			String plan_weekday=(String)request.getParameter("plan_weekday");
			String listen=(String)request.getParameter("listen");
			String watch=(String)request.getParameter("watch");
			String read=(String)request.getParameter("read");
			String play=(String)request.getParameter("play");
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "ID不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(plan_content)){
				jsonObj.put("res", "计划内容不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(plan_weekday)){
				jsonObj.put("res", "执行周期不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(listen)){
				listen="0";
			}
			if(!CollectionUtil.checkNull(watch)){
				watch="0";
			}
			if(!CollectionUtil.checkNull(read)){
				read="0";
			}
			if(!CollectionUtil.checkNull(play)){
				play="0";
			}
			
			Learnplan learnplan=this.learnplanService.findLearnplanOne(id);
			learnplan.setUpdate_adminuser(adminAccount);
			learnplan.setListen(listen);
			learnplan.setPlay(play);
			learnplan.setRead(read);
			learnplan.setWatch(watch);
			learnplan.setPlan_content(plan_content);
			learnplan.setPlan_summary(plan_summary);
			learnplan.setPlan_weekday(plan_weekday);
			
			int in=this.learnplanService.updateLearnplan(learnplan);
			if(in>0){
				state=1;
				memo+="计划"+id+"修改成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="计划"+id+"修改失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}catch(Exception e){
			memo+="计划修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 计划删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletelearnplan",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteLearnplan(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			int in=this.learnplanService.deleteLearnplan(id);
			if(in>0){
				state=1;
				memo+="删除计划"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除计划"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}catch(Exception e){
			memo+="计划删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 计划查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dolearnplanlist")
	public String doLearnplanList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String cIndex=request.getParameter("index");
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			request.setAttribute("id", id);
			
			Learnplan learnplan=new Learnplan();
			
			if(CollectionUtil.checkNull(id)){
				learnplan.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/learnplan/dolearnplanlist?id="+java.net.URLEncoder.encode(id,"UTF-8");
			//总行数
			int dataCount=this.learnplanService.findLearnplanCount(learnplan);
			//获得该页集合
			List<Learnplan> learnplanList=this.learnplanService.findLearnplanList(learnplan,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(learnplanList!=null && learnplanList.size()>0){
				request.setAttribute("learnplanList", learnplanList);
			}
			
			//记录日志
			memo+="计划查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
			
		}catch(Exception e){
			memo+="计划查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_xxjh, memo, state, request);
		}
		return "function/learnplan/learnplan_list";
	}
	
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the LearnplanService
	 */
	public LearnplanService getLearnplanService() {
		return learnplanService;
	}


	/**
	 * @param LearnplanService the LearnplanService to set
	 */
	public void setLearnplanService(LearnplanService learnplanService) {
		this.learnplanService = learnplanService;
	}

}
