package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manager.admin.page.PageUtil;
import com.manager.util.CollectionUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.function.entity.GlobalLevel;
import com.manager.function.service.GlobalLevelService;

@RequestMapping("globalLevel")
@Controller
public class GlobalLevelController {

	private Logger logger = Logger.getLogger(GlobalLevelController.class);
	@Autowired
	private AdminLogService adminLogService;
	@Autowired
	private GlobalLevelService globalLevelService;
	
	
	/**
	 * 获取所有的兴趣
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="get_select_all_hobby",method={RequestMethod.POST,RequestMethod.GET})
	public String get_select_all_hobby(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			List<GlobalLevel> div_hobby=this.globalLevelService.get_select_all_hobby();
			if(div_hobby!=null && div_hobby.size()>0){
				jsonObj.put("res", "1");
				JSONArray array=JSONArray.fromObject(div_hobby);
				jsonObj.put("su", array);
			}else{
				jsonObj.put("res", "0");
			}
			
		}catch(Exception e){
			memo+="获取所有的兴趣 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 获取所有的属性
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="get_select_all_peoperty",method={RequestMethod.POST,RequestMethod.GET})
	public String get_select_all_peoperty(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			List<GlobalLevel> div_peoperty=this.globalLevelService.get_select_all_peoperty();
			if(div_peoperty!=null && div_peoperty.size()>0){
				jsonObj.put("res", "1");
				JSONArray array=JSONArray.fromObject(div_peoperty);
				jsonObj.put("su", array);
			}else{
				jsonObj.put("res", "0");
			}
			
		}catch(Exception e){
			memo+="获取所有的属性 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 根据阶段获取兴趣
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="get_div_hobby",method={RequestMethod.POST,RequestMethod.GET})
	public String get_div_hobby(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String level_id=(String)request.getParameter("level_id");
			if(!CollectionUtil.checkNull(level_id)){
				jsonObj.put("res", "请勾选阶段！");
				return null;
			}
			
			String[] levelObj=level_id.split(";");
			List<GlobalLevel> glist=new ArrayList<GlobalLevel>();
			GlobalLevel g=null;
			for(String l:levelObj){
				g=new GlobalLevel();
				g.setId(l);
				glist.add(g);
			}
			List<GlobalLevel> div_hobby=this.globalLevelService.get_div_hobby(glist);
			if(div_hobby!=null && div_hobby.size()>0){
				jsonObj.put("res", "1");
				JSONArray array=JSONArray.fromObject(div_hobby);
				jsonObj.put("su", array);
			}else{
				jsonObj.put("res", "0");
			}
			
		}catch(Exception e){
			memo+="根据阶段获取兴趣 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 根据阶段获取属性
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="get_div_peoperty",method={RequestMethod.POST,RequestMethod.GET})
	public String get_div_peoperty(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			String level_id=(String)request.getParameter("level_id");
			if(!CollectionUtil.checkNull(level_id)){
				jsonObj.put("res", "请勾选阶段！");
				return null;
			}
			
			String[] levelObj=level_id.split(";");
			List<GlobalLevel> glist=new ArrayList<GlobalLevel>();
			GlobalLevel g=null;
			for(String l:levelObj){
				g=new GlobalLevel();
				g.setId(l);
				glist.add(g);
			}
			List<GlobalLevel> div_hobby=this.globalLevelService.get_div_peoperty(glist);
			if(div_hobby!=null && div_hobby.size()>0){
				jsonObj.put("res", "1");
				JSONArray array=JSONArray.fromObject(div_hobby);
				jsonObj.put("su", array);
			}else{
				jsonObj.put("res", "0");
			}
			
		}catch(Exception e){
			memo+="根据阶段获取兴趣 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 *  阶段详情列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("search")
	public String search(Model model,GlobalLevel gl, HttpServletRequest request,
			HttpServletResponse response) {
		String memo = "";
		try {
			String cIndex = (String)request.getParameter("index");
			int currentIndex = 1;
			
			if (CollectionUtil.checkNull(cIndex)) {
				currentIndex = Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			// url
			String url = "/globalLevel/search?1=1";
			
			// 获得该页集合
			List<GlobalLevel> arlist = this.globalLevelService.get( gl,(currentIndex - 1) * PageUtil.PAGECOUNT,
							PageUtil.PAGECOUNT);
			// 总行数
			int dataCount = this.globalLevelService.count(gl);
			// 获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
			request.setAttribute("pageinfo", pageinfo);

			if (arlist != null && arlist.size() > 0) {
				request.setAttribute("glList", arlist);
			}
		} catch (Exception e) {
			memo += " 阶段查询异常：" + e.getMessage();
			logger.error(memo, e);
		}
		return "/function/config/globalLevel/globalLevel_list";
	}
	
	/**
	 * 进入阶段添加页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toadd")
	public String toadd(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String cIndex = (String)request.getParameter("index");
			request.setAttribute("index", cIndex);
			return "/function/config/globalLevel/globalLevel_add";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 阶段添加
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("add")
	public String add(Model model, HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state = 1;
		String memo = "";
		try {
			String adminAccount = (String) session
					.getAttribute("admin_account");
			if (!CollectionUtil.checkNull(adminAccount)) {
				return null;
			}
			
			String cIndex = (String)request.getParameter("index");
			request.setAttribute("index", cIndex);

			String level_content = (String) request.getParameter("level_content");
			String level_summary = (String) request.getParameter("level_summary");
			String level_status = (String) request.getParameter("level_status");
			String sort = (String) request.getParameter("sort");
			if (!CollectionUtil.checkNull(level_content)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入等级名称！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_summary)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入等级简介！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_status)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择状态！'})");
				return null;
			}
			if (CollectionUtil.checkNull(sort)) {
				int num = globalLevelService.sortcount(sort);
				if(num>0){
					printWriter.write(jsoncallback + "({'res':'0',info:'此排序ID已存在！！'})");
					return null;
				}
			}else{
				sort="-9999999";
			}

			GlobalLevel gl = new GlobalLevel();
			gl.setLevel_content(level_content);
			gl.setLevel_status(level_status);
			gl.setLevel_summary(level_summary);
			gl.setSort(sort);
			gl.setCreate_adminuser(adminAccount);
			
			globalLevelService.add(gl);
			
			memo += level_content + ":添加成功！";
			printWriter.write(jsoncallback + "({'res':'1',info:'" + memo + "'})");
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);

		} catch (Exception e) {
			memo = "阶段添加异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'0',info:'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "阶段：添加失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入阶段修改页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdate")
	public String toupdate(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String cIndex = (String)request.getParameter("index");
			request.setAttribute("index", cIndex);
			String id=(String) request.getParameter("id");
			GlobalLevel gl = globalLevelService.getOne(id);
			model.addAttribute("gl", gl);
			return "/function/config/globalLevel/globalLevel_update";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 阶段修改
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("update")
	public String update(Model model, HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state = 1;
		String memo = "";
		try {
			String adminAccount = (String) session
					.getAttribute("admin_account");
			if (!CollectionUtil.checkNull(adminAccount)) {
				return null;
			}
			
			String cIndex = (String)request.getParameter("index");
			request.setAttribute("index", cIndex);

			String id = (String) request.getParameter("id");
			String level_content = (String) request.getParameter("level_content");
			String level_summary = (String) request.getParameter("level_summary");
			String level_status = (String) request.getParameter("level_status");
			String sort = (String) request.getParameter("sort");
			if (!CollectionUtil.checkNull(level_content)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入等级名称！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_summary)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入等级简介！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_status)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择状态！'})");
				return null;
			}
			if (CollectionUtil.checkNull(sort)) {
				int num = globalLevelService.sortcount(sort);
				GlobalLevel glModel = globalLevelService.getOne(id);
				if(num>0&&!glModel.getSort().equals(sort)){
					printWriter.write(jsoncallback + "({'res':'0',info:'此排序ID已存在！！'})");
					return null;
				}
			}else{
				sort="-9999999";
			}

			GlobalLevel gl = new GlobalLevel();
			gl.setId(id);
			gl.setLevel_content(level_content);
			gl.setLevel_status(level_status);
			gl.setLevel_summary(level_summary);
			gl.setSort(sort);
			gl.setUpdate_adminuser(adminAccount);
			
			globalLevelService.update(gl,"1");
			
			memo += level_content + ":修改成功！";
			printWriter.write(jsoncallback + "({'res':'1',info:'" + memo + "'})");
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);

		} catch (Exception e) {
			memo = "阶段修改异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'0',info:'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "阶段：修改失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 阶段删除
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Model model, HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		int state = 1;
		String memo = "";
		try {

			String id = (String) request.getParameter("id");
			String reg = (String) request.getParameter("reg");//1：删除一个，2：批量删除

			if (!CollectionUtil.checkNull(id)) {
				printWriter.write(jsoncallback + "({'res':'请选择要删除的列！'})");
				return null;
			}
			
			GlobalLevel gl = new GlobalLevel();
			gl.setId(id);
			
			globalLevelService.delete(gl,reg);
			
			memo += id + ":删除成功！";
			printWriter.write(jsoncallback + "({'res':'1','su':'删除成功！'})");
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);

		} catch (Exception e) {
			memo = "阶段删除异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "阶段：删除失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 阶段状态批量修改
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateStatus")
	public String updateStatus(Model model, HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		String jsoncallback = "";
		jsoncallback = request.getParameter("callback");
		HttpSession session = request.getSession();
		int state = 1;
		String memo = "";
		try {
			String adminAccount = (String) session
			.getAttribute("admin_account");
			if (!CollectionUtil.checkNull(adminAccount)) {
				return null;
			}

			String id = (String) request.getParameter("id");
			String level_status = (String) request.getParameter("level_status");

			if (!CollectionUtil.checkNull(id)) {
				printWriter.write(jsoncallback + "({'res':'请选择要修改的列！'})");
				return null;
			}
			
			GlobalLevel gl = new GlobalLevel();
			gl.setId(id);
			gl.setLevel_status(level_status);
			gl.setUpdate_adminuser(adminAccount);
			
			globalLevelService.update(gl,"2");
			
			memo += id + ":修改成功！";
			printWriter.write(jsoncallback + "({'res':'1','su':'修改成功！'})");
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);

		} catch (Exception e) {
			memo = "阶段修改异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "阶段：修改失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("阶段", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}

	public GlobalLevelService getGlobalLevelService() {
		return globalLevelService;
	}

	public void setGlobalLevelService(GlobalLevelService globalLevelService) {
		this.globalLevelService = globalLevelService;
	}

}
