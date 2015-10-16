package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.manager.function.entity.GlobalHobby;
import com.manager.function.entity.GlobalHobbyAttr;
import com.manager.function.entity.GlobalLevel;
import com.manager.function.service.GlobalHobbyAttrService;
import com.manager.function.service.GlobalHobbyService;
import com.manager.function.service.GlobalLevelService;
import com.manager.function.service.GlobalPropertyAttrService;
import com.manager.util.CollectionUtil;
import com.manager.util.Xml;

@RequestMapping("globalHobby")
@Controller
public class GlobalHobbyController {
	
	private Logger logger = Logger.getLogger(GlobalHobbyController.class);
	@Autowired
	private AdminLogService adminLogService;
	@Autowired
	private GlobalHobbyService globalHobbyService;
	@Autowired
	private GlobalLevelService globalLevelService;
	@Autowired
	private GlobalHobbyAttrService globalHobbyAttrService;
	
	public GlobalHobbyAttrService getGlobalHobbyAttrService() {
		return globalHobbyAttrService;
	}

	public void setGlobalHobbyAttrService(
			GlobalHobbyAttrService globalHobbyAttrService) {
		this.globalHobbyAttrService = globalHobbyAttrService;
	}

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public GlobalLevelService getGlobalLevelService() {
		return globalLevelService;
	}

	public void setGlobalLevelService(GlobalLevelService globalLevelService) {
		this.globalLevelService = globalLevelService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}

	public GlobalHobbyService getGlobalHobbyService() {
		return globalHobbyService;
	}

	public void setGlobalHobbyService(GlobalHobbyService globalHobbyService) {
		this.globalHobbyService = globalHobbyService;
	}

	/**
	 *  兴趣详情列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("search")
	public String search(Model model,GlobalHobby gh, HttpServletRequest request,
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
			String url = "/globalHobby/search?1=1";
			
			// 获得该页集合
			List<GlobalHobby> arlist = this.globalHobbyService.get( gh,(currentIndex - 1) * PageUtil.PAGECOUNT, PageUtil.PAGECOUNT);
			// 总行数
			int dataCount = this.globalHobbyService.count(gh);
			// 获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
			request.setAttribute("pageinfo", pageinfo);
			
			List<GlobalHobby> list = new ArrayList<GlobalHobby>();
			if(arlist!=null){
				for(GlobalHobby ghModel : arlist){
					String hobby_img = tobereplace(ghModel.getHobby_img(), 0);
					ghModel.setHobby_img(hobby_img);
					list.add(ghModel);
				}
			}

			if (arlist != null && arlist.size() > 0) {
				request.setAttribute("ghList", list);
			}
		} catch (Exception e) {
			memo += " 兴趣查询异常：" + e.getMessage();
			logger.error(memo, e);
		}
		return "/function/config/globalHobby/globalHobby_list";
	}
	
	/**
	 * 进入兴趣添加页面
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
			return "/function/config/globalHobby/globalHobby_add";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 兴趣添加
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

			String hobby_content = (String) request.getParameter("hobby_content");
			String hobby_summary = (String) request.getParameter("hobby_summary");
			String hobby_img = (String) request.getParameter("hobby_img");
			String hobby_status = (String) request.getParameter("hobby_status");
			String level_id = (String) request.getParameter("level_id");
			if (!CollectionUtil.checkNull(hobby_content)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入爱好内容！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_id)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择阶段！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(hobby_summary)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入爱好简介！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(hobby_img)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请上传图片说明！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(hobby_status)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入属性状态！'})");
				return null;
			}
			
			hobby_img = tobereplace(hobby_img, 1);

			GlobalHobby gh = new GlobalHobby();
			gh.setHobby_content(hobby_content);
			gh.setHobby_img(hobby_img);
			gh.setHobby_status(hobby_status);
			gh.setHobby_summary(hobby_summary);
			gh.setCreate_adminuser(adminAccount);
			gh.setLevel_id(level_id);
			
			globalHobbyService.add(gh);
			
			memo += hobby_content + ":添加成功！";
			printWriter.write(jsoncallback + "({'res':'1',info:'" + memo + "'})");
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);

		} catch (Exception e) {
			memo = "兴趣添加异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'0',info:'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "兴趣：添加失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 进入兴趣修改页面
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
			GlobalHobby gh = globalHobbyService.getOne(id);
			String hobby_img = tobereplace(gh.getHobby_img(), 0);
			gh.setHobby_img(hobby_img);
			model.addAttribute("gh", gh);
			List<GlobalLevel> gllist = this.globalLevelService.get(gl);
			request.setAttribute("gllist", gllist);
			List<GlobalHobbyAttr> ghaList = this.globalHobbyAttrService.get(gh.getId());
			request.setAttribute("ghaList", ghaList);
			return "/function/config/globalHobby/globalHobby_update";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 兴趣修改
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
			String hobby_content = (String) request.getParameter("hobby_content");
			String hobby_summary = (String) request.getParameter("hobby_summary");
			String hobby_img = (String) request.getParameter("hobby_img");
			String hobby_status = (String) request.getParameter("hobby_status");
			String level_id = (String) request.getParameter("level_id");
			if (!CollectionUtil.checkNull(hobby_content)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入爱好内容！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(level_id)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请选择阶段！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(hobby_summary)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入爱好简介！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(hobby_img)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请上传图片说明！'})");
				return null;
			}
			if (!CollectionUtil.checkNull(hobby_status)) {
				printWriter.write(jsoncallback + "({'res':'0',info:'请输入属性状态！'})");
				return null;
			}
			
			hobby_img = tobereplace(hobby_img, 1);

			GlobalHobby gh = new GlobalHobby();
			gh.setHobby_content(hobby_content);
			gh.setHobby_img(hobby_img);
			gh.setHobby_status(hobby_status);
			gh.setHobby_summary(hobby_summary);
			gh.setId(id);
			gh.setLevel_id(level_id);
			gh.setUpdate_adminuser(adminAccount);
			
			globalHobbyService.update(gh,"1");
			
			memo += hobby_content + ":修改成功！";
			printWriter.write(jsoncallback + "({'res':'1',info:'" + memo + "'})");
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);

		} catch (Exception e) {
			memo = "兴趣修改异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'0',info:'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "兴趣：修改失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 兴趣删除
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
			
			GlobalHobby gh = new GlobalHobby();
			gh.setId(id);
			
			globalHobbyService.delete(gh,reg);
			
			memo += id + ":删除成功！";
			printWriter.write(jsoncallback + "({'res':'1','su':'删除成功！'})");
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);

		} catch (Exception e) {
			memo = "兴趣删除异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "兴趣：删除失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 兴趣状态批量修改
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
			String hobby_status = (String) request.getParameter("hobby_status");

			if (!CollectionUtil.checkNull(id)) {
				printWriter.write(jsoncallback + "({'res':'请选择要修改的列！'})");
				return null;
			}
			
			GlobalHobby gh = new GlobalHobby();
			gh.setId(id);
			gh.setHobby_status(hobby_status);
			gh.setUpdate_adminuser(adminAccount);
			
			globalHobbyService.update(gh,"2");
			
			memo += id + ":修改成功！";
			printWriter.write(jsoncallback + "({'res':'1','su':'修改成功！'})");
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);

		} catch (Exception e) {
			memo = "兴趣修改异常：" + e.getMessage();
			printWriter.write(jsoncallback + "({'res':'" + memo + "'})");
			logger.error(e.getMessage(), e);
			memo = "兴趣：修改失败！";
			state = 0;
			// 记录日志
			this.adminLogService.addAdminLog("兴趣", memo, state, request);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	public String tobereplace(String message, int in) throws Exception {
		String path = GlobalHobbyController.class.getClassLoader().getResource("")
				.toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF/Config.xml";
		String bereplace = Xml.getXmlTagValue(path, "messageimagebereplace");
		String replace = Xml.getXmlTagValue(path, "messageimagereplace");
		if (in == 0) {
			if(CollectionUtil.checkNull(message)){
			message = message.replaceAll(replace, bereplace);
			}
		}
		if (in == 1) {
			if(CollectionUtil.checkNull(message)){
				message = message.replaceAll(bereplace, replace);
			}
		}
		return message;
	}

}
