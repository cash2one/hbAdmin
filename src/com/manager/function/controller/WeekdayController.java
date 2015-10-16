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
import com.manager.function.entity.Weekday;
import com.manager.function.service.WeekdayService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("weekday")
@Controller
public class WeekdayController {

	private Logger logger = Logger.getLogger(WeekdayController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private WeekdayService weekdayService;
	
	
	/**
	 * 进入周期添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertweekday")
	public String toInsertWeekday(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/weekday/weekday_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入周期修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateweekday")
	public String toUpdateWeekday(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			Weekday weekday=this.weekdayService.findWeekdayOne(id);
			request.setAttribute("weekday", weekday);
			return "function/weekday/weekday_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 周期添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertweekday",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertWeekday(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String start_date=(String)request.getParameter("start_date");
			String end_date=(String)request.getParameter("end_date");
			String listen=(String)request.getParameter("listen");
			String watch=(String)request.getParameter("watch");
			String read=(String)request.getParameter("read");
			String play=(String)request.getParameter("play");
			
			if(!CollectionUtil.checkNull(start_date)){
				jsonObj.put("res", "周期开始时间不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(end_date)){
				jsonObj.put("res", "周期结束时间不能为空！");
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
			
			//判断周期是否存在
			int ii=this.weekdayService.checkWeekday(start_date, end_date);
			if(ii>0){
				jsonObj.put("res", "该周期已存在，添加失败！");
				return null;
			}
			
			Weekday weekday=new Weekday();
			weekday.setStart_date(start_date);
			weekday.setEnd_date(end_date);
			weekday.setCreate_admin(adminAccount);
			weekday.setListen(listen);
			weekday.setPlay(play);
			weekday.setRead(read);
			weekday.setWatch(watch);
			
			
			int in=this.weekdayService.insertWeekday(weekday);
			if(in>0){
				state=1;
				memo+="周期添加成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="周期添加失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}catch(Exception e){
			memo+="周期添加 异常："+e.getMessage();
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
	 * 周期修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateweekday",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateWeekday(Model model,HttpServletRequest request,HttpServletResponse response
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
			String start_date=(String)request.getParameter("start_date");
			String end_date=(String)request.getParameter("end_date");
			String listen=(String)request.getParameter("listen");
			String watch=(String)request.getParameter("watch");
			String read=(String)request.getParameter("read");
			String play=(String)request.getParameter("play");
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(start_date)){
				jsonObj.put("res", "周期开始时间不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(end_date)){
				jsonObj.put("res", "周期结束时间不能为空！");
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
			//判断周期是否存在
			int ii=this.weekdayService.checkWeekday(id,start_date, end_date);
			if(ii>0){
				jsonObj.put("res", "该周期已存在，修改失败！");
				return null;
			}
			
			Weekday weekday=this.weekdayService.findWeekdayOne(id);
			weekday.setStart_date(start_date);
			weekday.setEnd_date(end_date);
			weekday.setUpdate_admin(adminAccount);
			weekday.setListen(listen);
			weekday.setPlay(play);
			weekday.setRead(read);
			weekday.setWatch(watch);
			
			int in=this.weekdayService.updateWeekday(weekday);
			if(in>0){
				state=1;
				memo+="周期"+id+"修改成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="周期"+id+"修改失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}catch(Exception e){
			memo+="周期修改 异常："+e.getMessage();
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
	 * 周期删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeleteweekday",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteWeekday(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			int in=this.weekdayService.deleteWeekday(id);
			if(in>0){
				state=1;
				memo+="删除周期"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除周期"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}catch(Exception e){
			memo+="周期删除 异常："+e.getMessage();
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
	 * 周期查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doweekdaylist")
	public String doWeekdayList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String cIndex=request.getParameter("index");
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			request.setAttribute("id", id);
			
			Weekday Weekday=new Weekday();
			
			if(CollectionUtil.checkNull(id)){
				Weekday.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/weekday/doweekdaylist?id="+java.net.URLEncoder.encode(id,"UTF-8");
			//总行数
			int dataCount=this.weekdayService.findWeekdayCount(Weekday);
			//获得该页集合
			List<Weekday> WeekdayList=this.weekdayService.findWeekdayList(Weekday,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(WeekdayList!=null && WeekdayList.size()>0){
				request.setAttribute("weekdayList", WeekdayList);
			}
			
			//记录日志
			memo+="周期查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
			
		}catch(Exception e){
			memo+="周期查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_mztj, memo, state, request);
		}
		return "function/weekday/weekday_list";
	}
	
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the WeekdayService
	 */
	public WeekdayService getWeekdayService() {
		return weekdayService;
	}


	/**
	 * @param WeekdayService the WeekdayService to set
	 */
	public void setWeekdayService(WeekdayService WeekdayService) {
		this.weekdayService = WeekdayService;
	}

}
