package com.manager.function.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.admin.page.PageUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.function.entity.SearchLogs;
import com.manager.function.service.SearchLogsService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("searchlogs")
@Controller
public class SearchLogsController {

	private Logger logger = Logger.getLogger(SearchLogsController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private SearchLogsService searchLogsService;
	
	
	/**
	 * 进入搜索日志查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tosearchlogslist")
	public String toSearchLogsList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/search/search_logs_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	/**
	 * 搜索日志查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dosearchlogslist")
	public String doSearchLogsList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String search_ip=(String)request.getParameter("search_ip");
			String user_id=(String)request.getParameter("user_id");
			String startDate=(String)request.getParameter("startDate");
			String endDate=(String)request.getParameter("endDate");
			String cIndex=request.getParameter("index");
			
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			search_ip=search_ip==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(search_ip.getBytes("ISO-8859-1"),"UTF-8"):search_ip,"UTF-8");
			user_id=user_id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(user_id.getBytes("ISO-8859-1"),"UTF-8"):user_id,"UTF-8");
			startDate=startDate==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(startDate.getBytes("ISO-8859-1"),"UTF-8"):startDate,"UTF-8");
			endDate=endDate==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(endDate.getBytes("ISO-8859-1"),"UTF-8"):endDate,"UTF-8");

			request.setAttribute("id", id);
			request.setAttribute("user_id", user_id);
			request.setAttribute("search_ip", search_ip);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			SearchLogs SearchLogs=new SearchLogs();
			if(CollectionUtil.checkNull(search_ip)){
				SearchLogs.setSearch_ip(search_ip);
			}
			if(CollectionUtil.checkNull(user_id)){
				SearchLogs.setUser_id(user_id);
			}
			if(CollectionUtil.checkNull(startDate)){
				SearchLogs.setStartDate(startDate);
			}
			if(CollectionUtil.checkNull(endDate)){
				SearchLogs.setEndDate(endDate);
			}
			if(CollectionUtil.checkNull(id)){
				SearchLogs.setId(id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/searchlogs/dosearchlogslist?search_ip="+java.net.URLEncoder.encode(search_ip,"UTF-8")
			+"&id="+java.net.URLEncoder.encode(id,"UTF-8")
			+"&startDate="+java.net.URLEncoder.encode(startDate,"UTF-8")
			+"&endDate="+java.net.URLEncoder.encode(endDate,"UTF-8")
			+"&user_id="+java.net.URLEncoder.encode(user_id,"UTF-8");
			//总行数
			int dataCount=this.searchLogsService.findSearchLogsCount(SearchLogs);
			//获得该页集合
			List<SearchLogs> SearchLogsList=this.searchLogsService.findSearchLogsList(SearchLogs,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(SearchLogsList!=null && SearchLogsList.size()>0){
				request.setAttribute("searchLogsList", SearchLogsList);
			}
			
			//记录日志
			memo+="搜索日志查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
			
		}catch(Exception e){
			memo+="搜索日志查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}
		return "function/search/search_logs_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the SearchLogsService
	 */
	public SearchLogsService getSearchLogsService() {
		return searchLogsService;
	}


	/**
	 * @param SearchLogsService the SearchLogsService to set
	 */
	public void setSearchLogsService(SearchLogsService SearchLogsService) {
		this.searchLogsService = SearchLogsService;
	}
	
}
