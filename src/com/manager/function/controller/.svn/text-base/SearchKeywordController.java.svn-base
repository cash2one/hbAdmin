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
import com.manager.function.entity.SearchKeyword;
import com.manager.function.service.SearchKeywordService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("searchkeyword")
@Controller
public class SearchKeywordController {

	private Logger logger = Logger.getLogger(SearchKeywordController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private SearchKeywordService searchKeywordService;
	
	/**
	 * 进入搜索关键字修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatesearchkeyword")
	public String toUpdateSearchKeyword(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			SearchKeyword SearchKeyword=this.searchKeywordService.findSearchKeywordOne(id);
			if(SearchKeyword!=null){
				request.setAttribute("searchkeyword", SearchKeyword);
			}
			
			return "function/search/search_keyword_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入搜索关键字查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tosearchkeywordlist")
	public String toSearchKeywordList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/search/search_keyword_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入搜索关键字添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertsearchkeyword")
	public String toInsertSearchKeyword(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/search/search_keyword_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 搜索关键字添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertsearchkeyword",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertSearchKeyword(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String keyword_name=(String)request.getParameter("keyword_name");
			String status=(String)request.getParameter("status");
			String sort=(String)request.getParameter("sort");
			
			if(!CollectionUtil.checkNull(keyword_name)){
				jsonObj.put("res", "搜索关键字名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(sort)){
				jsonObj.put("res", "排序不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			SearchKeyword SearchKeyword=new SearchKeyword();
			SearchKeyword.setKeyword_name(keyword_name);
			SearchKeyword.setSort(sort);
			SearchKeyword.setStatus(status);
			
			int ii=this.searchKeywordService.checkSearchKeywordKeywordName(keyword_name);
			if(ii>0){
				jsonObj.put("res", "搜索关键字已存在，请从新填写搜索关键字！");
				return null;
			}
			int ii2=this.searchKeywordService.checkSearchKeywordSort(sort);
			if(ii2>0){
				jsonObj.put("res", "排序已存在，请从新填写排序！");
				return null;
			}
			
			int in=this.searchKeywordService.insertSearchKeyword(SearchKeyword);
			if(in>0){
				state=1;
				memo+="搜索关键字keyword_name："+keyword_name+",添加成功！";
				jsonObj.put("res", "搜索关键字添加成功！");
			}else{
				memo+="搜索关键字添加失败！";
				jsonObj.put("res", "搜索关键字添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}catch(Exception e){
			memo+="搜索关键字添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 搜索关键字修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatesearchkeyword",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateSearchKeyword(Model model,HttpServletRequest request,HttpServletResponse response
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
			String keyword_name=(String)request.getParameter("keyword_name");
			String status=(String)request.getParameter("status");
			String sort=(String)request.getParameter("sort");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(keyword_name)){
				jsonObj.put("res", "搜索关键字不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(sort)){
				jsonObj.put("res", "排序不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			
			//获得该搜索关键字信息
			SearchKeyword SearchKeyword=this.searchKeywordService.findSearchKeywordOne(id);
			if(SearchKeyword==null){
				jsonObj.put("res", "搜索关键字获取失败或搜索关键字序列号错误！");
				return null;
			}
			int ii=this.searchKeywordService.checkSearchKeywordKeywordName(id,keyword_name);
			if(ii>0){
				jsonObj.put("res", "搜索关键字已存在，请从新填写搜索关键字！");
				return null;
			}
			int ii2=this.searchKeywordService.checkSearchKeywordSort(id,sort);
			if(ii2>0){
				jsonObj.put("res", "排序已存在，请从新填写排序！");
				return null;
			}
			
			SearchKeyword.setKeyword_name(keyword_name);
			SearchKeyword.setStatus(status);
			SearchKeyword.setSort(sort);
			
			int in=this.searchKeywordService.updateSearchKeyword(SearchKeyword);
			if(in>0){
				state=1;
				memo+="修改搜索关键字"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改搜索关键字"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}catch(Exception e){
			memo+="搜索关键字修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 搜索关键字删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletesearchkeyword",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteSearchKeyword(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			
			int in=this.searchKeywordService.deleteSearchKeyword(id);
			if(in>0){
				state=1;
				memo+="删除搜索关键字"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除搜索关键字"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}catch(Exception e){
			memo+="搜索关键字删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 搜索关键字状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatussearchkeyword",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusSearchKeywordType(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			SearchKeyword SearchKeyword=new SearchKeyword();
			SearchKeyword.setId(id);
			SearchKeyword.setStatus(status);
			
			int in=this.searchKeywordService.updateSearchKeywordStatus(SearchKeyword);
			if(in>0){
				state=1;
				memo+="修改搜索关键字"+id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改搜索关键字"+id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}catch(Exception e){
			memo+="搜索关键字状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 搜索关键字查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dosearchkeywordlist")
	public String doSearchKeywordList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String status=(String)request.getParameter("status");
			String cIndex=request.getParameter("index");
			
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			status=status==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(status.getBytes("ISO-8859-1"),"UTF-8"):status,"UTF-8");
			

			request.setAttribute("id", id);
			request.setAttribute("status", status);
			
			SearchKeyword SearchKeyword=new SearchKeyword();
			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
				SearchKeyword.setStatus(status);
			}
			if(CollectionUtil.checkNull(id)){
				SearchKeyword.setId(id);
			}else{
				id="";
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/searchkeyword/dosearchkeywordlist?status="+java.net.URLEncoder.encode(status,"UTF-8")
			+"&id="+java.net.URLEncoder.encode(id,"UTF-8");
			//总行数
			int dataCount=this.searchKeywordService.findSearchKeywordCount(SearchKeyword);
			//获得该页集合
			List<SearchKeyword> SearchKeywordList=this.searchKeywordService.findSearchKeywordList(SearchKeyword,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(SearchKeywordList!=null && SearchKeywordList.size()>0){
				request.setAttribute("searchKeywordList", SearchKeywordList);
			}
			
			//记录日志
			memo+="搜索关键字查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
			
		}catch(Exception e){
			memo+="搜索关键字查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_ssgl, memo, state, request);
		}
		return "function/search/search_keyword_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the SearchKeywordService
	 */
	public SearchKeywordService getSearchKeywordService() {
		return searchKeywordService;
	}


	/**
	 * @param SearchKeywordService the SearchKeywordService to set
	 */
	public void setSearchKeywordService(SearchKeywordService SearchKeywordService) {
		this.searchKeywordService = SearchKeywordService;
	}
	
}
