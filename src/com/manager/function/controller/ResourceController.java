package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.manager.function.entity.Resource;
import com.manager.function.entity.ResourceInfo;
import com.manager.function.service.GlobalLanguageService;
import com.manager.function.service.GlobalLevelService;
import com.manager.function.service.GlobalPropertyService;
import com.manager.function.service.ResourceInfoService;
import com.manager.function.service.ResourceService;
import com.manager.function.service.ResourceTypeService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("resource")
@Controller
public class ResourceController {

	private Logger logger = Logger.getLogger(ResourceController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@Autowired
	private GlobalLanguageService globalLanguageService;
	
	@Autowired
	private GlobalLevelService globalLevelService;
	
	@Autowired
	private GlobalPropertyService globalPropertyService;
	
	@Autowired
	private ResourceInfoService resourceInfoService;
	
	/**
	 * 加载下拉框选项
	 */
	public void loadSelectOption(HttpServletRequest request){
		//资源类型
		this.resourceTypeService.NoAbolish_ResourceTypeList(request);
		//兴趣
		this.globalLanguageService.NoAbolish_GlobalLanguageList(request);
		//阶段
		this.globalLevelService.NoAbolish_GlobalLevelList(request);
		//属性
		this.globalPropertyService.NoAbolish_GlobalPropertyList(request);
		
	}
	
	/**
	 * 进入资源修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateresource")
	public String toUpdateResource(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			Resource resource=this.resourceService.findResourceOne(id);
			if(resource!=null){
				if(CollectionUtil.checkNull(resource.getImg_index())){
					resource.setImg_index(CollectionUtil.tobereplace(resource.getImg_index(), 0));
				}
				if(CollectionUtil.checkNull(resource.getImg_list())){
					resource.setImg_list(CollectionUtil.tobereplace(resource.getImg_list(), 0));
				}
				if(resource.getResource_type_id().equals("1")||resource.getResource_type_id().equals("4")||resource.getResource_type_id().equals("2")){
					if(CollectionUtil.checkNull(resource.getImg_main())){
						resource.setImg_main(CollectionUtil.tobereplace(resource.getImg_main(), 0));
					}
				}
				if(resource.getResource_type_id().equals("4")){
					if(CollectionUtil.checkNull(resource.getImg_book()))
					resource.setImg_book(CollectionUtil.tobereplace(resource.getImg_book(), 0));
					
					if(CollectionUtil.checkNull(resource.getRead_img())){
						resource.setRead_img(CollectionUtil.tobereplace(resource.getRead_img(), 0));
					}
					if(CollectionUtil.checkNull(resource.getLian_img())){
						resource.setLian_img(CollectionUtil.tobereplace(resource.getLian_img(), 0));
					}
					if(CollectionUtil.checkNull(resource.getStart_img())){
						resource.setStart_img(CollectionUtil.tobereplace(resource.getStart_img(), 0));
					}
				}
				
				
				List<ResourceInfo> resourceInfoList=this.resourceInfoService.checkboxResourceInfoList(id);
				request.setAttribute("resourceInfoList", resourceInfoList);
				if(resourceInfoList!=null && resourceInfoList.size()>0){
					String property_id="";
					for(ResourceInfo r:resourceInfoList){
						if(CollectionUtil.checkNull(r.getLanguage_level())){
							resource.setLanguage_level(r.getLanguage_level());
						}
						if(CollectionUtil.checkNull(r.getProperty_id())){
							property_id+=r.getProperty_id()+";";
						}
					}
					request.setAttribute("property_id", property_id);
				}
			}
			request.setAttribute("resource", resource);
			
			ResourceInfo ResourceInfo=new ResourceInfo();
			ResourceInfo.setResource_id(id);
			ResourceInfo.setResource_info_type("4");
			int ii=this.resourceInfoService.findResourceInfoCount(ResourceInfo);
			if(ii>0){
				request.setAttribute("status_info", "1");
			}else{
				request.setAttribute("status_info", "0");
			}
			
			loadSelectOption(request);
			
			return "function/resource/resource_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入资源查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toresourcelist")
	public String toResourceList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			loadSelectOption(request);
			return "function/resource/resource_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入资源添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertresource")
	public String toInsertResource(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			loadSelectOption(request);
			return "function/resource/resource_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	
	/**
	 * 资源添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertresource",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertResource(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int state=0;
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			jsonObj.put("id", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			
			String resource_content = (String)request.getParameter("resource_content");
			String resource_summary = (String)request.getParameter("resource_summary");
			String resource_type_id = (String)request.getParameter("resource_type_id");
			String img_index = (String)request.getParameter("img_index");
			String img_index_size = (String)request.getParameter("img_index_size");
			String img_list = (String)request.getParameter("img_list");
			String img_list_size = (String)request.getParameter("img_list_size");
			String img_main = (String)request.getParameter("img_main");
			String img_main_size = (String)request.getParameter("img_main_size");
			//String img_book = (String)request.getParameter("img_book");
			//String img_book_size = (String)request.getParameter("img_book_size");
			String resource_status = (String)request.getParameter("resource_status");
//			String create_date = (String)request.getParameter("create_date");
//			String create_adminuser = (String)request.getParameter("create_adminuser");
//			String update_date = (String)request.getParameter("update_date");
//			String update_adminuser = (String)request.getParameter("update_adminuser");
//			String type_name = (String)request.getParameter("type_name");
			String language_level = (String)request.getParameter("language_level");
			String level_id = (String)request.getParameter("level_id");
			String property_id = (String)request.getParameter("property_id");
			String resource_author=(String)request.getParameter("resource_author");
			String resource_keyword=(String)request.getParameter("resource_keyword");
			
			//拓展介绍字段
			String read_content=(String)request.getParameter("read_content");
			String read_img=(String)request.getParameter("read_img");
			String lian_content=(String)request.getParameter("lian_content");
			String lian_img=(String)request.getParameter("lian_img");
			String start_content=(String)request.getParameter("start_content");
			String start_img=(String)request.getParameter("start_img");
			
//			if(!CollectionUtil.checkNull(id)){jsonObj.put("res", "不能为空！");return null;}
			if(!CollectionUtil.checkNull(resource_content)){jsonObj.put("res", "资源名称不能为空！");return null;}
			if(!CollectionUtil.checkNull(resource_summary)){jsonObj.put("res", "资源简介不能为空！");return null;}
			if(!CollectionUtil.checkNull(resource_type_id)){jsonObj.put("res", "请选择资源类型！");return null;}
			if(!CollectionUtil.checkNull(img_index)){jsonObj.put("res", "请选择要上传的首页展示图片！");return null;}
			if(!CollectionUtil.checkNull(img_index_size)){jsonObj.put("res", "首页展示图片尺寸不能为空！");return null;}
			if(!CollectionUtil.checkNull(img_list )){jsonObj.put("res", "请选择要上传的列表展示图片！");return null;}
			if(!CollectionUtil.checkNull(img_list_size)){jsonObj.put("res", "列表展示图片尺寸不能为空！");return null;}
			if(resource_type_id.equals("1")||resource_type_id.equals("4")||resource_type_id.equals("2")){
				if(!CollectionUtil.checkNull(img_main )){jsonObj.put("res", "请选择要上传的主界面背景图片！");return null;}
				if(!CollectionUtil.checkNull(img_main_size)){jsonObj.put("res", "主界面背景图片尺寸不能为空！！");return null;}
			}
			
			//if(resource_type_id.equals("4")){
			//	if(!CollectionUtil.checkNull(img_book )){jsonObj.put("res", "请选择要上传的书架封面图片！");return null;}
			//	if(!CollectionUtil.checkNull(img_book_size)){jsonObj.put("res", "书架封面图片尺寸不能为空！！");return null;}
			//}
			if(!CollectionUtil.checkNull(resource_status)){jsonObj.put("res", "请选择资源状态");return null;}
			if(!CollectionUtil.checkNull(language_level )){jsonObj.put("res", "请选择语言难度！");return null;}
			if(!CollectionUtil.checkNull(level_id )){jsonObj.put("res", "请选择阶段！");return null;}
			if(!CollectionUtil.checkNull(property_id)){jsonObj.put("res", "请选择属性！");return null;}
			if(!CollectionUtil.checkNull(resource_author)){jsonObj.put("res", "作者不能为空！");return null;}
			
			Resource Resource=new Resource();
//			Resource.setId(id);
			Resource.setResource_content(resource_content);
			
			
			int ii=this.resourceService.checkResourceResourceContent(resource_content,resource_type_id);
			if(ii>0){
				jsonObj.put("res", "资讯名称已存在，请从新填写！");
				return null;
			}
			String id=this.resourceService.getid();
			Resource.setId(id);
			Resource.setResource_summary(resource_summary);
			Resource.setResource_type_id(resource_type_id);
			Resource.setImg_index(CollectionUtil.tobereplace(img_index, 1));
			Resource.setImg_index_size(img_index_size);
			Resource.setImg_list(CollectionUtil.tobereplace(img_list, 1));
			Resource.setImg_list_size(img_list_size);
			if(resource_type_id.equals("1")||resource_type_id.equals("4")||resource_type_id.equals("2")){
				Resource.setImg_main(CollectionUtil.tobereplace(img_main, 1));
				Resource.setImg_main_size(img_main_size);
			}
			if(resource_type_id.equals("4")){
				Resource.setImg_book(CollectionUtil.tobereplace(img_index, 1));
				Resource.setImg_book_size(img_index_size);
				
				
				//拓展介绍字段
				if(CollectionUtil.checkNull(read_content)){
					Resource.setRead_content(read_content);
				}
				if(CollectionUtil.checkNull(lian_content)){
					Resource.setLian_content(lian_content);
				}
				if(CollectionUtil.checkNull(start_content)){
					Resource.setStart_content(start_content);
				}
				if(CollectionUtil.checkNull(read_img)){
					Resource.setRead_img(CollectionUtil.tobereplace(read_img, 1));
				}
				if(CollectionUtil.checkNull(lian_img)){
					Resource.setLian_img(CollectionUtil.tobereplace(lian_img, 1));
				}
				if(CollectionUtil.checkNull(start_img)){
					Resource.setStart_img(CollectionUtil.tobereplace(start_img, 1));
				}
			}
			
			
			Resource.setResource_status(resource_status);
//			Resource.setCreate_date(create_date);
			Resource.setCreate_adminuser(adminAccount);
//			Resource.setUpdate_date(update_date);
//			Resource.setUpdate_adminuser(update_adminuser);
//			Resource.setType_name(type_name);
//			Resource.setHobby_id(hobby_id);
//			Resource.setLevel_id(level_id);
//			Resource.setProperty_id(property_id);
			Resource.setResource_author(resource_author);
			Resource.setResource_keyword(resource_keyword);
			
			List<ResourceInfo> resourceInfoList1=new ArrayList<ResourceInfo>();
			List<ResourceInfo> resourceInfoList2=new ArrayList<ResourceInfo>();
			List<ResourceInfo> resourceInfoList3=new ArrayList<ResourceInfo>();
			String[] hobby=language_level.split(",");
			String[] level=level_id.split(",");
			String[] property=property_id.split(",");
			ResourceInfo info=null;
			for(String l:level){
				info=new ResourceInfo();
				info.setResource_id(id);
				info.setLevel_id(l);
				info.setResource_info_type("1");
				info.setCreate_adminuser(adminAccount);
				resourceInfoList1.add(info);
			}
			for(String p:property){
				info=new ResourceInfo();
				info.setResource_id(id);
				info.setProperty_id(p);
				info.setResource_info_type("2");
				info.setCreate_adminuser(adminAccount);
				resourceInfoList2.add(info);
			}
			for(String h:hobby){
				info=new ResourceInfo();
				info.setResource_id(id);
				info.setLanguage_level(h);
				info.setCreate_adminuser(adminAccount);
				info.setResource_info_type("3");
				resourceInfoList3.add(info);
			}	
			
			int in=this.resourceService.insertResource(Resource,resourceInfoList1,resourceInfoList2,resourceInfoList3);
			if(in>0){
				state=1;
				memo+="资源resource_content："+resource_content+",添加成功！";
				jsonObj.put("res", "资源添加成功！");
				jsonObj.put("su", "1");
				jsonObj.put("id", id);
			}else{
				memo+="资源添加失败！";
				jsonObj.put("res", "资源添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}catch(Exception e){
			memo+="资源添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateresource",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateResource(Model model,HttpServletRequest request,HttpServletResponse response
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
			String resource_content = (String)request.getParameter("resource_content");
			String resource_summary = (String)request.getParameter("resource_summary");
			String resource_type_id = (String)request.getParameter("resource_type_id");
			String img_index = (String)request.getParameter("img_index");
			String img_index_size = (String)request.getParameter("img_index_size");
			String img_list = (String)request.getParameter("img_list");
			String img_list_size = (String)request.getParameter("img_list_size");
			String img_main = (String)request.getParameter("img_main");
			String img_main_size = (String)request.getParameter("img_main_size");
			//String img_book = (String)request.getParameter("img_book");
			//String img_book_size = (String)request.getParameter("img_book_size");
			String resource_status = (String)request.getParameter("resource_status");
//			String create_date = (String)request.getParameter("create_date");
//			String create_adminuser = (String)request.getParameter("create_adminuser");
//			String update_date = (String)request.getParameter("update_date");
//			String update_adminuser = (String)request.getParameter("update_adminuser");
//			String type_name = (String)request.getParameter("type_name");
			String language_level = (String)request.getParameter("language_level");
			String level_id = (String)request.getParameter("level_id");
			String property_id = (String)request.getParameter("property_id");
//			String all_hobby_id = (String)request.getParameter("all_hobby_id");
//			String all_level_id = (String)request.getParameter("all_level_id");
//			String all_property_id = (String)request.getParameter("all_property_id");
			String resource_author = (String)request.getParameter("resource_author");
			String resource_keyword=(String)request.getParameter("resource_keyword");
			
			
			//拓展介绍字段
			String read_content=(String)request.getParameter("read_content");
			String read_img=(String)request.getParameter("read_img");
			String lian_content=(String)request.getParameter("lian_content");
			String lian_img=(String)request.getParameter("lian_img");
			String start_content=(String)request.getParameter("start_content");
			String start_img=(String)request.getParameter("start_img");
			
			if(!CollectionUtil.checkNull(id)){jsonObj.put("res", "序列号不能为空！");return null;}
			if(!CollectionUtil.checkNull(resource_content)){jsonObj.put("res", "资源名称不能为空！");return null;}
			if(!CollectionUtil.checkNull(resource_summary)){jsonObj.put("res", "资源简介不能为空！");return null;}
			if(!CollectionUtil.checkNull(resource_type_id)){jsonObj.put("res", "请选择资源类型！");return null;}
			if(!CollectionUtil.checkNull(img_index)){jsonObj.put("res", "请选择要上传的首页展示图片！");return null;}
			if(!CollectionUtil.checkNull(img_index_size)){jsonObj.put("res", "首页展示图片尺寸不能为空！");return null;}
			if(!CollectionUtil.checkNull(img_list )){jsonObj.put("res", "请选择要上传的列表展示图片！");return null;}
			if(!CollectionUtil.checkNull(img_list_size)){jsonObj.put("res", "列表展示图片尺寸不能为空！");return null;}
			if(resource_type_id.equals("1")||resource_type_id.equals("4")){
				if(!CollectionUtil.checkNull(img_main )){jsonObj.put("res", "请选择要上传的主界面背景图片！");return null;}
				if(!CollectionUtil.checkNull(img_main_size)){jsonObj.put("res", "主界面背景图片尺寸不能为空！！");return null;}
			}
			//if(resource_type_id.equals("4")){
			//	if(!CollectionUtil.checkNull(img_book )){jsonObj.put("res", "请选择要上传的书架封面图片！");return null;}
			//	if(!CollectionUtil.checkNull(img_book_size)){jsonObj.put("res", "书架封面图片尺寸不能为空！！");return null;}
			//}
			if(!CollectionUtil.checkNull(resource_status)){jsonObj.put("res", "请选择资源状态");return null;}
			if(!CollectionUtil.checkNull(language_level )){jsonObj.put("res", "请选择语言难度！");return null;}
			if(!CollectionUtil.checkNull(level_id )){jsonObj.put("res", "请选择阶段！");return null;}
			if(!CollectionUtil.checkNull(property_id)){jsonObj.put("res", "请选择属性！");return null;}
			if(!CollectionUtil.checkNull(resource_author)){jsonObj.put("res", "作者不能为空！");return null;}
			
			int ii=this.resourceService.checkResourceResourceContent(id, resource_content,resource_type_id);
			if(ii>0){
				jsonObj.put("res", "资讯名称已存在，请从新填写！");
				return null;
			}
			
			//获得该资源信息
			Resource Resource=this.resourceService.findResourceOne(id);
			if(Resource==null){
				jsonObj.put("res", "资源获取失败或资源序列号错误！");
				return null;
			}
			Resource.setResource_content(resource_content);
			Resource.setResource_summary(resource_summary);
			Resource.setResource_type_id(resource_type_id);
			Resource.setImg_index(CollectionUtil.tobereplace(img_index, 1));
			Resource.setImg_index_size(img_index_size);
			Resource.setImg_list(CollectionUtil.tobereplace(img_list, 1));
			Resource.setImg_list_size(img_list_size);
			if(resource_type_id.equals("1")||resource_type_id.equals("4")||resource_type_id.equals("2")){
				Resource.setImg_main(CollectionUtil.tobereplace(img_main, 1));
				Resource.setImg_main_size(img_main_size);
			}
			if(resource_type_id.equals("4")){
				Resource.setImg_book(CollectionUtil.tobereplace(img_index, 1));
				Resource.setImg_book_size(img_index_size);
				
				//拓展介绍字段
				if(CollectionUtil.checkNull(read_content)){
					Resource.setRead_content(read_content);
				}else{
					Resource.setRead_content(null);
				}
				if(CollectionUtil.checkNull(lian_content)){
					Resource.setLian_content(lian_content);
				}else{
					Resource.setLian_content(null);
				}
				if(CollectionUtil.checkNull(start_content)){
					Resource.setStart_content(start_content);
				}else{
					Resource.setStart_content(null);
				}
				if(CollectionUtil.checkNull(read_img)){
					Resource.setRead_img(CollectionUtil.tobereplace(read_img, 1));
				}else{
					Resource.setRead_img(null);
				}
				if(CollectionUtil.checkNull(lian_img)){
					Resource.setLian_img(CollectionUtil.tobereplace(lian_img, 1));
				}else{
					Resource.setLian_img(null);
				}
				if(CollectionUtil.checkNull(start_img)){
					Resource.setStart_img(CollectionUtil.tobereplace(start_img, 1));
				}else{
					Resource.setStart_img(null);
				}
			}
			Resource.setResource_status(resource_status);
			Resource.setUpdate_adminuser(adminAccount);
			Resource.setResource_author(resource_author);
			Resource.setResource_keyword(resource_keyword);
			
			
			List<ResourceInfo> addInfo_level=new ArrayList<ResourceInfo>();
			List<ResourceInfo> addInfo_property=new ArrayList<ResourceInfo>();
			List<ResourceInfo> addInfo_hobby=new ArrayList<ResourceInfo>();
			
			List<String> deleteInfo_level=new ArrayList<String>();
			List<String> deleteInfo_property=new ArrayList<String>();
			List<String> deleteInfo_hobby=new ArrayList<String>();
			
			List<ResourceInfo> deleteInfo=new ArrayList<ResourceInfo>();
			
			String[] hobby=language_level.split(",");
			String[] level=level_id.split(",");
			String[] property=property_id.split(",");
			
			List<ResourceInfo> resourceInfoList=this.resourceInfoService.checkboxResourceInfoList(id);
			
			String hobby_id1="";
			String level_id1="";
			String property_id1="";
			ResourceInfo info=null;
			if(resourceInfoList!=null && resourceInfoList.size()>0){
				for(ResourceInfo r:resourceInfoList){
					if(CollectionUtil.checkNull(r.getLanguage_level())) hobby_id1+=r.getLanguage_level()+",";
					if(CollectionUtil.checkNull(r.getLevel_id())) level_id1+=r.getLevel_id()+",";
					if(CollectionUtil.checkNull(r.getProperty_id())) property_id1+=r.getProperty_id()+",";
				}
			
				String[] all_level=level_id1.split(",");
				String[] all_property=property_id1.split(",");
				String[] all_hobby=hobby_id1.split(",");
				
				//排序对比是否相等
				Arrays.sort(all_level);
				Arrays.sort(level);
				if(Arrays.equals(all_level, level)){
					addInfo_level=null;
					deleteInfo_level=null;
				}else{
					//获得交集数组
					String[] intersectLevel=intersect(all_level, level);
					//获得删除数据
					for(String l:all_level){
						if(Arrays.binarySearch(intersectLevel, l)<0){
							deleteInfo_level.add(l);
						}
					}
					//获得添加数据
					for(String l:level){
						if(Arrays.binarySearch(intersectLevel, l)<0){
							info=new ResourceInfo();
							info.setResource_id(id);
							info.setLevel_id(l);
							info.setResource_info_type("1");
							info.setCreate_adminuser(adminAccount);
							addInfo_level.add(info);
						}
					}
				}
				
				//排序对比是否相等
				Arrays.sort(all_property);
				Arrays.sort(property);
				if(Arrays.equals(all_property, property)){
					addInfo_property=null;
					deleteInfo_property=null;
				}else{
					
					//获得交集数组
					String[] intersectProperty=intersect(all_property, property);
					//获得删除数据
					for(String l:all_property){
						if(Arrays.binarySearch(intersectProperty, l)<0){
							deleteInfo_property.add(l);
						}
					}
					//获得添加数据
					for(String l:property){
						if(Arrays.binarySearch(intersectProperty, l)<0){
							info=new ResourceInfo();
							info.setResource_id(id);
							info.setProperty_id(l);
							info.setResource_info_type("2");
							info.setCreate_adminuser(adminAccount);
							addInfo_property.add(info);
						}
					}
					
				}
				
				//排序对比是否相等
				Arrays.sort(all_hobby);
				Arrays.sort(hobby);
				if(Arrays.equals(all_hobby, hobby)){
					addInfo_hobby=null;
					deleteInfo_hobby=null;
				}else{
					//获得交集数组
					String[] intersectHobby=intersect(all_hobby, hobby);
					
					//获得删除数据
					for(String l:all_hobby){
						if(Arrays.binarySearch(intersectHobby, l)<0){
							deleteInfo_hobby.add(l);
						}
					}
					//获得添加数据
					for(String l:hobby){
						if(Arrays.binarySearch(intersectHobby, l)<0){
							info=new ResourceInfo();
							info.setResource_id(id);
							info.setLanguage_level(l);
							info.setResource_info_type("3");
							info.setCreate_adminuser(adminAccount);
							addInfo_hobby.add(info);
						}
					}
					
				}
				
				if(deleteInfo_level!=null && deleteInfo_level.size()>0){
					for(String l:deleteInfo_level){
						for(ResourceInfo r:resourceInfoList){
							if(!CollectionUtil.checkNull(r.getLevel_id())){
								continue;
							}
							if(l.equals(r.getLevel_id())){
								deleteInfo.add(r);
							}
						}
					}
				}
				if(deleteInfo_property!=null && deleteInfo_property.size()>0){
					for(String l:deleteInfo_property){
						for(ResourceInfo r:resourceInfoList){
							if(!CollectionUtil.checkNull(r.getProperty_id())){
								continue;
							}
							if(l.equals(r.getProperty_id())){
								deleteInfo.add(r);
							}
						}
					}
				}
				if(deleteInfo_hobby!=null && deleteInfo_hobby.size()>0){
					for(String l:deleteInfo_hobby){
						for(ResourceInfo r:resourceInfoList){
							if(!CollectionUtil.checkNull(r.getLanguage_level())){
								continue;
							}
							if(l.equals(r.getLanguage_level())){
								deleteInfo.add(r);
							}
						}
					}
				}
			}else{
				//获得添加数据
				for(String l:level){
					info=new ResourceInfo();
					info.setResource_id(id);
					info.setLevel_id(l);
					info.setResource_info_type("1");
					info.setCreate_adminuser(adminAccount);
					addInfo_level.add(info);
				}
				
				//获得添加数据
				for(String l:property){
					info=new ResourceInfo();
					info.setResource_id(id);
					info.setProperty_id(l);
					info.setResource_info_type("2");
					info.setCreate_adminuser(adminAccount);
					addInfo_property.add(info);
				}
				
				//获得添加数据
				for(String l:hobby){
					info=new ResourceInfo();
					info.setResource_id(id);
					info.setLanguage_level(l);
					info.setResource_info_type("3");
					info.setCreate_adminuser(adminAccount);
					addInfo_hobby.add(info);
				}
				
			}
			
			int in=this.resourceService.updateResource(Resource,addInfo_level,addInfo_property,addInfo_hobby,deleteInfo);
			if(in>0){
				state=1;
				memo+="修改资源"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改资源"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}catch(Exception e){
			memo+="资源修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	  // 求两个数组的交集
	  public static String[] intersect(String[] arr1, String[] arr2){
		  Map<String, Boolean> map = new HashMap<String, Boolean>();   
	        LinkedList<String> list = new LinkedList<String>();   
	        for (String str : arr1) {   
	            if (!map.containsKey(str)) {   
	                map.put(str, Boolean.FALSE);   
	            }   
	        }   
	        for (String str : arr2) {   
	            if (map.containsKey(str)) {   
	                map.put(str, Boolean.TRUE);   
	            }   
	        }   
	  
	        for (Entry<String, Boolean> e : map.entrySet()) {   
	            if (e.getValue().equals(Boolean.TRUE)) {   
	                list.add(e.getKey());   
	            }   
	        }   
	  
	        String[] result = {};   
	        return list.toArray(result);  
	  }
	  
	
	
	/**
	 * 资源删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeleteresource",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteResource(Model model,HttpServletRequest request,HttpServletResponse response
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
			
//			News news=new News();
//			news.setid(id);
//			int ii=this.newsService.findNewsCount(news);
//			if(ii>0){
//				jsonObj.put("res", "该类型在资源信息里面引用到，删除失败！");
//				return null;
//			}
			
			int in=this.resourceService.deleteResource(id);
			if(in>0){
				state=1;
				memo+="删除资源"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除资源"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}catch(Exception e){
			memo+="资源删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源状态修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdatestatusresource",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateStatusResourceType(Model model,HttpServletRequest request,HttpServletResponse response
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
			String resource_status=(String)request.getParameter("resource_status");
			
			if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "序列号不能为空！");
				return null;
			}
			if(!CollectionUtil.checkNull(resource_status)){
				jsonObj.put("res", "状态不能为空！");
				return null;
			}
			
			ResourceInfo ResourceInfo=new ResourceInfo();
			ResourceInfo.setResource_id(id);
			ResourceInfo.setResource_info_type("4");
			int ii=this.resourceInfoService.findResourceInfoCount(ResourceInfo);
			if(ii==0 && resource_status.equals("1")){
				jsonObj.put("res", "修改失败，该资源没有资源详情，不可以对外！");
				return null;
			}
			
			
			Resource Resource=new Resource();
			Resource.setId(id);
			Resource.setResource_status(resource_status);
			Resource.setUpdate_adminuser(adminAccount);
			
			int in=this.resourceService.updateResourceStatus(Resource);
			if(in>0){
				state=1;
				memo+="修改资源"+id+"状态"+resource_status+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="修改资源"+id+"状态"+resource_status+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}catch(Exception e){
			memo+="资源状态修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 资源查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doresourcelist")
	public String doResourceList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String id=(String)request.getParameter("id");
			String resource_status=(String)request.getParameter("resource_status");
			String resource_type_id=(String)request.getParameter("resource_type_id");
			String level_id=(String)request.getParameter("level_id");
			String language_level=(String)request.getParameter("language_level");
			String property_id=(String)request.getParameter("property_id");
			//String hobby_id_2=(String)request.getParameter("hobby_id_2");
			//String property_id_2=(String)request.getParameter("property_id_2");
			String resource_content=(String)request.getParameter("resource_content");
			String cIndex=request.getParameter("index");
			
			id=id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(id.getBytes("ISO-8859-1"),"UTF-8"):id,"UTF-8");
			resource_content=resource_content==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(resource_content.getBytes("ISO-8859-1"),"UTF-8"):resource_content,"UTF-8");

			request.setAttribute("id", id);
			request.setAttribute("resource_status", resource_status);
			request.setAttribute("resource_type_id", resource_type_id);
			request.setAttribute("level_id", level_id);
			request.setAttribute("resource_content", resource_content);
			request.setAttribute("language_level", language_level);
			request.setAttribute("property_id", property_id);
			
			
			Resource Resource=new Resource();
			if(CollectionUtil.checkNull(resource_status) && !"all".equals(resource_status)){
				Resource.setResource_status(resource_status);
			}
			if(CollectionUtil.checkNull(resource_type_id) && !"all".equals(resource_type_id)){
				Resource.setResource_type_id(resource_type_id);
			}
			if(CollectionUtil.checkNull(language_level) && !"all".equals(language_level)){
				Resource.setLanguage_level(language_level);
			}
			if(CollectionUtil.checkNull(property_id) && !"all".equals(property_id)){
				Resource.setProperty_id(property_id);
			}
			if(CollectionUtil.checkNull(level_id) && !"all".equals(level_id)){
				Resource.setLevel_id(level_id);
			}
//			if(CollectionUtil.checkNull(level_id) && !"all".equals(level_id)){
//				Resource.setLevel_id(level_id);
//				if(CollectionUtil.checkNull(hobby_id) && !"all".equals(hobby_id)){
//					Resource.setHobby_id(hobby_id);
//					hobby_id=hobby_id.substring(hobby_id.indexOf("-")+1);
//					request.setAttribute("hobby_id", hobby_id);
//				}else{
//					request.setAttribute("hobby_id", hobby_id);
//				}
//				if(CollectionUtil.checkNull(property_id) && !"all".equals(property_id)){
//					Resource.setProperty_id(property_id);
//					property_id=property_id.substring(property_id.indexOf("-")+1);
//					request.setAttribute("property_id",property_id );
//				}else{
//					request.setAttribute("property_id", property_id);
//				}
//			}else{
//				hobby_id=hobby_id_2;
//				property_id=property_id_2;
//				request.setAttribute("hobby_id", hobby_id_2);
//				request.setAttribute("property_id", property_id_2);
//				if(CollectionUtil.checkNull(hobby_id_2) && !"all".equals(hobby_id_2)){
//					Resource.setHobby_id(hobby_id_2);
//				}
//				if(CollectionUtil.checkNull(property_id_2) && !"all".equals(property_id_2)){
//					Resource.setProperty_id(property_id_2);
//				}
//			}
			if(CollectionUtil.checkNull(id)){
				Resource.setId(id);
			}
			if(CollectionUtil.checkNull(resource_content)){
				Resource.setResource_content(resource_content);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/resource/doresourcelist?"
			+"resource_status="+resource_status
			+"&id="+java.net.URLEncoder.encode(id,"UTF-8")
			+"&resource_content="+java.net.URLEncoder.encode(resource_content,"UTF-8")
			+"&resource_type_id="+resource_type_id
			+"&language_level="+language_level
			+"&level_id="+level_id
			+"&property_id="+property_id
			;
			//总行数
			int dataCount=this.resourceService.findResourceCount(Resource);
			//获得该页集合
			List<Resource> resourceList=this.resourceService.findResourceList(Resource,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(resourceList!=null && resourceList.size()>0){
				request.setAttribute("resourceList", resourceList);
			}
			loadSelectOption(request);
			//this.globalLevelService.get_select(request);
			//记录日志
			memo+="资源查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
			
		}catch(Exception e){
			memo+="资源查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_zyxx, memo, state, request);
		}
		return "function/resource/resource_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the resourceService
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}


	/**
	 * @param resourceService the resourceService to set
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}


	/**
	 * @return the resourceTypeService
	 */
	public ResourceTypeService getResourceTypeService() {
		return resourceTypeService;
	}


	/**
	 * @param resourceTypeService the resourceTypeService to set
	 */
	public void setResourceTypeService(ResourceTypeService resourceTypeService) {
		this.resourceTypeService = resourceTypeService;
	}


	/**
	 * @return the globalLanguageService
	 */
	public GlobalLanguageService getGlobalLanguageService() {
		return globalLanguageService;
	}


	/**
	 * @param globalLanguageService the globalLanguageService to set
	 */
	public void setGlobalLanguageService(GlobalLanguageService globalLanguageService) {
		this.globalLanguageService = globalLanguageService;
	}


	/**
	 * @return the globalLevelService
	 */
	public GlobalLevelService getGlobalLevelService() {
		return globalLevelService;
	}


	/**
	 * @param globalLevelService the globalLevelService to set
	 */
	public void setGlobalLevelService(GlobalLevelService globalLevelService) {
		this.globalLevelService = globalLevelService;
	}


	/**
	 * @return the globalPropertyService
	 */
	public GlobalPropertyService getGlobalPropertyService() {
		return globalPropertyService;
	}


	/**
	 * @param globalPropertyService the globalPropertyService to set
	 */
	public void setGlobalPropertyService(GlobalPropertyService globalPropertyService) {
		this.globalPropertyService = globalPropertyService;
	}

	/**
	 * @return the resourceInfoService
	 */
	public ResourceInfoService getResourceInfoService() {
		return resourceInfoService;
	}

	/**
	 * @param resourceInfoService the resourceInfoService to set
	 */
	public void setResourceInfoService(ResourceInfoService resourceInfoService) {
		this.resourceInfoService = resourceInfoService;
	}
	
	
}
