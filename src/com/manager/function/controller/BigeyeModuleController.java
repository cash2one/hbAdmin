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
import com.manager.function.entity.BigeyeModule;
import com.manager.function.service.BigeyeModuleService;
import com.manager.function.service.BigeyeService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("bigeyemodule")
@Controller
public class BigeyeModuleController {

	private Logger logger = Logger.getLogger(BigeyeModuleController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private BigeyeModuleService bigeyeModuleService;
	@Autowired
	private BigeyeService bigeyeService;
	
	/**
	 * 进入广告图模块修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdatebigeyemodule")
	public String toUpdateBigeyeModule(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String module_id=(String)request.getParameter("module_id");
			
			if(!CollectionUtil.checkNull(module_id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			BigeyeModule BigeyeModule=this.bigeyeModuleService.findBigeyeModuleOne(module_id);
			if(BigeyeModule!=null){
				request.setAttribute("bigeyemodule", BigeyeModule);
			}
			
			return "function/bigeye/bigeye_module_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入广告图模块查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tobigeyemodulelist")
	public String toBigeyeModuleList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/bigeye/bigeye_module_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入广告图模块添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertbigeyemodule")
	public String toInsertBigeyeModule(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/bigeye/bigeye_module_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 广告图模块添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertbigeyemodule",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertBigeyeModule(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String module_name=(String)request.getParameter("module_name");
			String status=(String)request.getParameter("status");
			String img_width=(String)request.getParameter("img_width");
			String img_height=(String)request.getParameter("img_height");
			
			if(!CollectionUtil.checkNull(module_name)){
				jsonObj.put("res", "广告图模块名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			BigeyeModule BigeyeModule=new BigeyeModule();
			BigeyeModule.setModule_name(module_name);
			
			
			int ii=this.bigeyeModuleService.checkBigeyeModuleName(module_name);
			if(ii>0){
				jsonObj.put("res", "类型名称已存在，请从新填写类型名称！");
				return null;
			}
			if(CollectionUtil.checkNull(img_width)){
				BigeyeModule.setImg_width(img_width);
			}
			if(CollectionUtil.checkNull(img_height)){
				BigeyeModule.setImg_height(img_height);
			}
			BigeyeModule.setStatus(status);
			
			int in=this.bigeyeModuleService.insertBigeyeModule(BigeyeModule);
			if(in>0){
				state=1;
				memo+="广告图模块module_name："+module_name+",添加成功！";
				jsonObj.put("res", "广告图模块添加成功！");
			}else{
				memo+="广告图模块添加失败！";
				jsonObj.put("res", "广告图模块添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图模块添加 异常："+e.getMessage();
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
	 * 广告图模块修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatebigeyemodule",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateBigeyeModule(Model model,HttpServletRequest request,HttpServletResponse response
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
			String module_id=(String)request.getParameter("module_id");
			String module_name=(String)request.getParameter("module_name");
			String img_width=(String)request.getParameter("img_width");
			String img_height=(String)request.getParameter("img_height");
			String status=(String)request.getParameter("status");
			
			if(!CollectionUtil.checkNull(module_id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(module_name)){
				jsonObj.put("res", "类型名称不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			//获得该广告图模块信息
			BigeyeModule BigeyeModule=this.bigeyeModuleService.findBigeyeModuleOne(module_id);
			if(BigeyeModule==null){
				jsonObj.put("res", "广告图模块获取失败或广告图模块序列号错误！");
				return null;
			}
			
			int ii=this.bigeyeModuleService.checkBigeyeModuleName(module_id,module_name);
			if(ii>0){
				jsonObj.put("res", "类型名称已存在，请从新填写类型名称！");
				return null;
			}
			if(CollectionUtil.checkNull(img_width)){
				BigeyeModule.setImg_width(img_width);
			}
			if(CollectionUtil.checkNull(img_height)){
				BigeyeModule.setImg_height(img_height);
			}
			BigeyeModule.setModule_name(module_name);
			BigeyeModule.setStatus(status);
			
			int in=this.bigeyeModuleService.updateBigeyeModule(BigeyeModule);
			if(in>0){
				state=1;
				memo+="修改广告图模块"+module_id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改广告图模块"+module_id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图模块修改 异常："+e.getMessage();
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
	 * 广告图模块删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeletebigeyemodule",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteBigeyeModule(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String module_id=(String)request.getParameter("module_id");
			
			if(!CollectionUtil.checkNull(module_id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			
			Bigeye Bigeye=new Bigeye();
			Bigeye.setModule_id(module_id);
			int ii=this.bigeyeService.findBigeyeCount(Bigeye);
			if(ii>0){
				jsonObj.put("res", "该模版在广告图里面引用到，删除失败！");
				return null;
			}
			
			int in=this.bigeyeModuleService.deleteBigeyeModule(module_id);
			if(in>0){
				state=1;
				memo+="删除广告图模块"+module_id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除广告图模块"+module_id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图模块删除 异常："+e.getMessage();
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
	 * 广告图模块状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatusbigeyemodule",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusBigeyeModuleType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String module_id=(String)request.getParameter("module_id");
			String status=(String)request.getParameter("status");
			
			if(!CollectionUtil.checkNull(module_id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			BigeyeModule BigeyeModule=new BigeyeModule();
			BigeyeModule.setModule_id(module_id);
			BigeyeModule.setStatus(status);
			
			int in=this.bigeyeModuleService.updateBigeyeModuleStatus(BigeyeModule);
			if(in>0){
				state=1;
				memo+="修改广告图模块"+module_id+"状态"+status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改广告图模块"+module_id+"状态"+status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}catch(Exception e){
			memo+="广告图模块状态修改 异常："+e.getMessage();
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
	 * 广告图模块查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dobigeyemodulelist")
	public String doBigeyeModuleList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String module_id=(String)request.getParameter("module_id");
			String status=(String)request.getParameter("status");
			String cIndex=request.getParameter("index");

			request.setAttribute("module_id", module_id);
			request.setAttribute("status", status);
			
			
			BigeyeModule BigeyeModule=new BigeyeModule();
			if(CollectionUtil.checkNull(status) && !"all".equals(status)){
				BigeyeModule.setStatus(status);
			}
			if(CollectionUtil.checkNull(module_id)){
				BigeyeModule.setModule_id(module_id);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/bigeyemodule/dobigeyemodulelist?status="+status+"&module_id="+module_id;
			//总行数
			int dataCount=this.bigeyeModuleService.findBigeyeModuleCount(BigeyeModule);
			//获得该页集合
			List<BigeyeModule> BigeyeModuleList=this.bigeyeModuleService.findBigeyeModuleList(BigeyeModule,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(BigeyeModuleList!=null && BigeyeModuleList.size()>0){
				request.setAttribute("bigeyemoduleList", BigeyeModuleList);
			}
			
			//记录日志
			memo+="广告图模块查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
			
		}catch(Exception e){
			memo+="广告图模块查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_dyzgl, memo, state, request);
		}
		return "function/bigeye/bigeye_module_list";
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


	
	
}
