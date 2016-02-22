package com.manager.function.controller;


import java.io.PrintWriter;
import java.net.URLEncoder;
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

import com.manager.admin.service.AdminLogService;
import com.manager.function.entity.Tiezi;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.HttpConnectionUtil;

@RequestMapping("official")
@Controller
public class OfficialController {

	private Logger logger = Logger.getLogger(OfficialController.class);
	
	@Autowired
	private AdminLogService adminLogService;
	
	/**
	 * 进入帖子类型修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateofficial")
	public String toUpdateofficial(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			String yonghuid=(String)request.getParameter("yonghuid");
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "ID不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(yonghuid)){
				request.setAttribute("msg", "用户ID不能为空！");
				return null;
			}
			String url=CollectionUtil.huoquXml("tie")+"?yonghuid="+yonghuid+"&tieziid="+id;
			String str=HttpConnectionUtil.getData(url);
			
			
			Tiezi t=null;
			if(str!=null){
				JSONObject json=JSONObject.fromObject(str);
				if(json.get("jsonObj")!=null){
					JSONObject j=json.getJSONObject("jsonObj");
					if(j!=null){
							t=new Tiezi();
							t.setId(j.getString("id"));
							t.setBuluoid(j.getString("buluoid"));
							t.setBiaoti(j.getString("biaoti"));
							t.setLeixing(j.getString("leixing"));
							t.setNeirong(j.getString("neirong")!=null?j.getString("neirong").replaceAll("\"","”").replaceAll(";","；"):"");
							t.setChakanshu(j.getString("chakanshu"));
							t.setPinglunshu(j.getString("pinglunshu"));
							t.setDidian(j.getString("didian"));
							t.setYonghuid(j.getString("yonghuid"));
							t.setZhuangtai(j.getString("zhuangtai"));
							t.setJinghua(j.getString("jinghua"));
							t.setCreated(j.getString("created"));
						}
				}
			}
			
			if(t!=null){
				request.setAttribute("tie", t);
				String neirong[] =  t.getNeirong().split("\\$\\$");
				
				request.setAttribute("neirong",neirong);
			}
			
			return "function/official/official_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
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
	@RequestMapping(value="doupdateofficial",method={RequestMethod.POST,RequestMethod.GET})
	public String doupdateofficial(Model model,HttpServletRequest request,HttpServletResponse response
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
			String yonghuid=(String)request.getParameter("yonghuid");
			String buluoid=(String)request.getParameter("buluoid");
			String biaoti=(String)request.getParameter("biaoti");
			String neirong=(String)request.getParameter("neirong").replaceAll("\"","”").replaceAll(";","；");
			String url=CollectionUtil.huoquXml("gaiguanfangtie")+"?tieziid="+id+"&yonghuid="+yonghuid+"&buluoid="+buluoid+"&biaoti="+URLEncoder.encode(biaoti,"UTF-8")+"&neirong="+URLEncoder.encode(neirong,"UTF-8")+"";
			String str=HttpConnectionUtil.getData(url);
			state=1;
			memo+="API请求返回结果为："+str;
			jsonObj.put("res", str);
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_gftgl, memo, state, request);
		}catch(Exception e){
			memo+="官方贴修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_gftgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
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
	@RequestMapping("toinsertofficial")
	public String toInsertTopicList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/official/official_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 官方贴查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doofficiallist")
	public String doofficiallist(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String yonghuid=(String)request.getParameter("yonghuid");
			String page=(String)request.getParameter("page");
			if(page==null || page=="")page="1";
			if(yonghuid==null)yonghuid="";

			request.setAttribute("yonghuid", yonghuid);
			request.setAttribute("page", page);
			
			String url=CollectionUtil.huoquXml("guanfangtie")+"?yonghuid="+yonghuid+"&page="+page;
			String str=HttpConnectionUtil.getData(url);
			
			List<Tiezi> tlist=null;
			if(str!=null){
				JSONObject json=JSONObject.fromObject(str);
				if(json.get("jsonObj")!=null){
					JSONArray arr=json.getJSONArray("jsonObj");
					if(arr!=null && arr.size()>0){
						tlist=new ArrayList<Tiezi>();
						Tiezi t=null;
						for(int i=0;i<arr.size();i++){
							JSONObject j=arr.getJSONObject(i);
							t=new Tiezi();
							t.setId(j.getString("id"));
							t.setBuluoid(j.getString("buluoid"));
							t.setBiaoti(j.getString("biaoti"));
							t.setLeixing(j.getString("leixing"));
							t.setNeirong(j.getString("neirong"));
							t.setChakanshu(j.getString("chakanshu"));
							t.setPinglunshu(j.getString("pinglunshu"));
							t.setDidian(j.getString("didian"));
							t.setYonghuid(j.getString("yonghuid"));
							t.setZhuangtai(j.getString("zhuangtai"));
							t.setJinghua(j.getString("jinghua"));
							t.setCreated(j.getString("created"));
							tlist.add(t);
						}
					}
				}
			}
			if(tlist!=null && tlist.size()>0){
				request.setAttribute("tieziList", tlist);
			}
			
			//记录日志
			memo+="官方贴查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_gftgl, memo, state, request);
			
		}catch(Exception e){
			memo+="官方贴查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_gftgl, memo, state, request);
		}
		return "function/official/official_list";
	}
	
	
	
	/**
	 * 主贴添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertofficial",method={RequestMethod.POST,RequestMethod.GET})
	public String doinsertofficial(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String yonghuid=(String)request.getParameter("yonghuid");
			String buluoid=(String)request.getParameter("buluoid");
			String biaoti=(String)request.getParameter("biaoti");
			String neirong=(String)request.getParameter("neirong");
			String url=CollectionUtil.huoquXml("faguanfangtie")+"?yonghuid="+yonghuid+"&buluoid="+buluoid+"&biaoti="+URLEncoder.encode(biaoti,"UTF-8")+"&neirong="+URLEncoder.encode(neirong,"UTF-8")+"";
			String str=HttpConnectionUtil.getData(url);
			state=1;
			memo+="API请求返回结果为："+str;
			jsonObj.put("res", str);
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_gftgl, memo, state, request);
		}catch(Exception e){
			memo+="官方贴添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_gftgl, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
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
	
	
}
