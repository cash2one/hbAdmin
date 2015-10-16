package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.admin.page.PageUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.function.entity.GlobalLevel;
import com.manager.function.entity.GlobalProperty;
import com.manager.function.entity.GlobalPropertyAttr;
import com.manager.function.service.GlobalLevelService;
import com.manager.function.service.GlobalPropertyAttrService;
import com.manager.function.service.GlobalPropertyService;
import com.manager.util.CollectionUtil;

@RequestMapping("globalProperty")
@Controller
public class GlobalPropertyController {
	
	private Logger logger = Logger.getLogger(GlobalPropertyController.class);
	@Autowired
	private AdminLogService adminLogService;
	@Autowired
	private GlobalPropertyService globalPropertyService;
	@Autowired
	private GlobalLevelService globalLevelService;
	@Autowired
	private GlobalPropertyAttrService globalPropertyAttrService;

	public GlobalPropertyAttrService getGlobalPropertyAttrService() {
		return globalPropertyAttrService;
	}

	public void setGlobalPropertyAttrService(
			GlobalPropertyAttrService globalPropertyAttrService) {
		this.globalPropertyAttrService = globalPropertyAttrService;
	}

	/**
	 *  属性详情列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("search")
	public String search(Model model,GlobalProperty gp, HttpServletRequest request,
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
			String url = "/globalProperty/search?1=1";
			
			// 获得该页集合
			List<GlobalProperty> arlist = this.globalPropertyService.get( gp,(currentIndex - 1) * PageUtil.PAGECOUNT,
							PageUtil.PAGECOUNT);
			// 总行数
			int dataCount = this.globalPropertyService.count(gp);
			// 获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
			request.setAttribute("pageinfo", pageinfo);

			if (arlist != null && arlist.size() > 0) {
				request.setAttribute("gpList", arlist);
			}
		} catch (Exception e) {
			memo += " 属性查询异常：" + e.getMessage();
			logger.error(memo, e);
		}
		return "/function/config/globalProperty/globalProperty_list";
	}
	
	/**
	 * 进入属性添加页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toadd")
	public String toadd(GlobalLevel gl,Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String cIndex = (String)request.getParameter("index");
			request.setAttribute("index", cIndex);
			List<GlobalLevel> gllist = this.globalLevelService.get(gl);
			request.setAttribute("gllist", gllist);
			return "/function/config/globalProperty/globalProperty_add";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 属性添加
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

			String property_name = (String) request.getParameter("property_name");
			String property_status = (String) request.getParameter("property_status");
			String sort = (String) request.getParameter("sort");
			String level_id = (String) request.getParameter("level_id");
			String property_type = (String) request.getParameter("property_type");
			String property_num = (String) request.getParameter("property_num");
			if (!CollectionUtil.checkNull(property_name)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入属性名称！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_id)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择阶段！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(property_type)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择属性类型！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(property_status)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入属性状态！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(sort)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入排序！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(property_num)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入数量！'})");
				return null;
			}

			GlobalProperty gp = new GlobalProperty();
			gp.setProperty_name(property_name);
			gp.setProperty_status(property_status);
			gp.setCreate_adminuser(adminAccount);
			gp.setSort(sort);
			gp.setProperty_type(property_type);
			gp.setLevel_id(level_id);
			gp.setProperty_num(property_num);
			
			globalPropertyService.add(gp);
			
			memo += property_name + ":添加成功！";
			printWriter.write(jsoncallback + "({'res':'1',info:'" + memo + "'})");
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);

		} catch (Exception e) {
			memo = "属性添加异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'0',info:'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "属性：添加失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入属性修改页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdate")
	public String toupdate(GlobalLevel gl,Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String cIndex = (String)request.getParameter("index");
			request.setAttribute("index", cIndex);
			String id=(String) request.getParameter("id");
			GlobalProperty gp = globalPropertyService.getOne(id);
			model.addAttribute("gp", gp);
			List<GlobalLevel> gllist = this.globalLevelService.get(gl);
			request.setAttribute("gllist", gllist);
			return "/function/config/globalProperty/globalProperty_update";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 属性修改
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
			String property_name = (String) request.getParameter("property_name");
			String property_status = (String) request.getParameter("property_status");
			String sort = (String) request.getParameter("sort");
			String level_id = (String) request.getParameter("level_id");
			String property_type = (String) request.getParameter("property_type");
			String property_num = (String) request.getParameter("property_num");
			if (!CollectionUtil.checkNull(property_name)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入属性名称！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_id)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择阶段！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(property_type)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择属性类型！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(property_status)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入属性状态！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(sort)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入排序！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(property_num)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入数量！'})");
				return null;
			}

			GlobalProperty gp = new GlobalProperty();
			gp.setProperty_name(property_name);
			gp.setLevel_id(level_id);
			gp.setProperty_status(property_status);
			gp.setId(id);
			gp.setProperty_type(property_type);
			gp.setUpdate_adminuser(adminAccount);
			gp.setSort(sort);
			gp.setProperty_num(property_num);
			
			globalPropertyService.update(gp,"1");
			
			memo += property_name + ":修改成功！";
			printWriter.write(jsoncallback + "({'res':'1',info:'" + memo + "'})");
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);

		} catch (Exception e) {
			memo = "属性修改异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'0',info:'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "属性：修改失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 属性删除
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
			
			GlobalProperty gp = new GlobalProperty();
			gp.setId(id);
			
			globalPropertyService.delete(gp,reg);
			
			memo += id + ":删除成功！";
			printWriter.write(jsoncallback + "({'res':'1','su':'删除成功！'})");
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);

		} catch (Exception e) {
			memo = "属性删除异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "属性：删除失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 属性状态批量修改
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
			String adminAccount = (String) session.getAttribute("admin_account");
			if (!CollectionUtil.checkNull(adminAccount)) {
				return null;
			}

			String id = (String) request.getParameter("id");
			String property_status = (String) request.getParameter("property_status");

			if (!CollectionUtil.checkNull(id)) {
				printWriter.write(jsoncallback + "({'res':'请选择要修改的列！'})");
				return null;
			}
			
			GlobalProperty gp = new GlobalProperty();
			gp.setId(id);
			gp.setProperty_status(property_status);
			gp.setUpdate_adminuser(adminAccount);
			
			globalPropertyService.update(gp,"2");
			
			memo += id + ":修改成功！";
			printWriter.write(jsoncallback + "({'res':'1','su':'修改成功！'})");
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);

		} catch (Exception e) {
			memo = "属性修改异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "属性：修改失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("属性", memo, state, request);
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

	public GlobalPropertyService getGlobalPropertyService() {
		return globalPropertyService;
	}

	public void setGlobalPropertyService(GlobalPropertyService globalPropertyService) {
		this.globalPropertyService = globalPropertyService;
	}

	public GlobalLevelService getGlobalLevelService() {
		return globalLevelService;
	}

	public void setGlobalLevelService(GlobalLevelService globalLevelService) {
		this.globalLevelService = globalLevelService;
	}

}
