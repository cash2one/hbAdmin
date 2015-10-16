package com.manager.function.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.manager.function.entity.Resource;
import com.manager.function.entity.ResourceInfo;
import com.manager.function.service.ResourceInfoService;
import com.manager.function.service.ResourceService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("resourceinfo")
@Controller
public class ResourceInfoController {

	private Logger logger = Logger.getLogger(ResourceInfoController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private ResourceInfoService resourceInfoService;
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 进入资源详情修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toupdateresourceinfo")
	public String toUpdateResourceInfo(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=(String)request.getParameter("id");
			String resource_id=(String)request.getParameter("resource_id");
			
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "序列号不能为空！");
				return null;
			}
			
			ResourceInfo resourceInfo=this.resourceInfoService.findResourceInfoOne(id);
			if(resourceInfo!=null){
				Resource resource=this.resourceService.findUseResourceInfo(resource_id);
				request.setAttribute("resource", resource);
				if(resourceInfo.getResource_type().equals("1")||resourceInfo.getResource_type().equals("3")){
					if(CollectionUtil.checkNull(resourceInfo.getResource_url())){
						resourceInfo.setResource_url(CollectionUtil.tobereplace(resourceInfo.getResource_url(), 0));
					}
				}
				if(resourceInfo.getResource_type().equals("4")){
					if(CollectionUtil.checkNull(resourceInfo.getResource_url())){
						resourceInfo.setResource_url(CollectionUtil.toplaybereplace(resourceInfo.getResource_url(), 0));
					}
				}
				if(CollectionUtil.checkNull(resourceInfo.getResource_lyrics())){
					resourceInfo.setResource_lyrics(CollectionUtil.tobereplace(resourceInfo.getResource_lyrics(), 0));
				}
				if(resourceInfo.getResource_url()!=null && resourceInfo.getResource_url().indexOf(",")>0){
					String url=resourceInfo.getResource_url();
					int index=url.indexOf(",");
					resourceInfo.setResource_url(url.substring(0, index));
					resourceInfo.setResource_url2(url.substring(index+1, url.length()));
				}
				request.setAttribute("resourceinfo", resourceInfo);
				
			}
			
			return "function/resource/resource_info_update";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 进入资源详情查询页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toresourceinfolist")
	public String toResourceInfoList(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			return "function/resource/resource_info_list";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入资源详情添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toinsertresourceinfo")
	public String toInsertResourceInfo(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			String id=request.getParameter("id");
			if(!CollectionUtil.checkNull(id)){
				request.setAttribute("msg", "资源序列号不能为空！");
				return null;
			}
			Resource resource=this.resourceService.findUseResourceInfo(id);
			request.setAttribute("resource", resource);
			String resource_type=request.getParameter("resource_type");
			request.setAttribute("more_resource_type", resource_type);
			
			return "function/resource/resource_info_add";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	/**
	 * 判断该资源是否能在添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="check_info_add",method={RequestMethod.POST,RequestMethod.GET})
	public String check_info_add(Model model,HttpServletRequest request,HttpServletResponse response
			,PrintWriter printWriter){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		String memo="";
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("res", "");
			jsonObj.put("su", "");
			jsonObj.put("resource_type", "");
			
			String adminAccount=(String) session.getAttribute("admin_account");
			if(!CollectionUtil.checkNull(adminAccount)){
				return null;
			}
			
			String resource_id=(String)request.getParameter("resource_id");
			if(!CollectionUtil.checkNull(resource_id)){
				jsonObj.put("res", "资源序列号不能为空！");
				return null;
			}
			Resource resource=this.resourceService.findUseResourceInfo(resource_id);
			//5-游戏、4-绘本、3-动画、2-视频、1-音频
			String type_id=resource.getResource_type_id();
			List<ResourceInfo> rlist=this.resourceInfoService.get_check_info(resource_id);
			if(rlist!=null && rlist.size()>0){
				if(type_id.equals("1")||type_id.equals("2")||type_id.equals("5")){
					jsonObj.put("res", "0");
					jsonObj.put("su", "该资源信息为1对1,一个资源信息不能上传多个资源,不能再次添加");
					return null;
				}
				for(ResourceInfo r:rlist){
					//(1-音频 2-视频 3-图片 4-HTML5)
					if(r.getResource_type().equals("1")|| r.getResource_type().equals("4")){
						jsonObj.put("res", "0");
						jsonObj.put("su", "该资源信息为1对1,一个资源信息不能上传多个资源,不能再次添加");
						return null;
					}
					if(type_id.equals("4")){
						if(r.getResource_type().equals("1")||r.getResource_type().equals("2")){
							jsonObj.put("res", "0");
							jsonObj.put("su", "该资源信息为1对1,一个资源信息不能上传多个资源,不能再次添加");
							return null;
						}
					}
					jsonObj.put("res", "1");
					jsonObj.put("resource_type", r.getResource_type());
					return null;
				}
			}else{
				jsonObj.put("res", "1");
				jsonObj.put("resource_type",null);
			}
			
		}catch(Exception e){
			memo+="资源详情添加 异常："+e.getMessage();
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
	 * 资源详情添加
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doinsertresourceinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String doInsertResourceInfo(Model model,HttpServletRequest request,HttpServletResponse response
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
			
			String resource_id=(String)request.getParameter("resource_id");
			String resource_type_id=(String)request.getParameter("resource_type_id");
			String resource_type=(String)request.getParameter("resource_type");
     		String set_number=(String)request.getParameter("set_number");
     		String resource_url_url=(String)request.getParameter("resource_url_url");
     		String resource_url_file=(String)request.getParameter("resource_url_file");
     		String resource_url_img=(String)request.getParameter("resource_url_img");
     		String resource_url_html=(String)request.getParameter("resource_url_html");
     		String resource_sort_cartoon=(String)request.getParameter("resource_sort_cartoon");
     		String resource_sort_img=(String)request.getParameter("resource_sort_img");
     		String resource_lyrics=(String)request.getParameter("resource_lyrics");
     		
     		if(!CollectionUtil.checkNull(resource_id)){
				jsonObj.put("res", "资源序列号不能为空！");
				return null;
			}
     		if(!CollectionUtil.checkNull(resource_type_id)){
				jsonObj.put("res", "资源类型不能为空！");
				return null;
			}
     		if(!CollectionUtil.checkNull(resource_type)){
				jsonObj.put("res", "资源详情类型不能为空！");
				return null;
			}
     		if("3".equals(resource_type_id)){
     			if(!CollectionUtil.checkNull(set_number)){
    				jsonObj.put("res", "动画集数不能为空！");
    				return null;
    			}
     			if(!CollectionUtil.checkNull(resource_sort_cartoon)){
    				jsonObj.put("res", "排序不能为空！");
    				return null;
    			}
     		}else{
     			set_number=null;
     			resource_sort_cartoon=null;
     		}
     		
     		if("1".equals(resource_type)){
     			if(!CollectionUtil.checkNull(resource_url_file)){
    				jsonObj.put("res", "资源音频文件不能为空！");
    				return null;
    			}else{
    				resource_url_file=CollectionUtil.tobereplace(resource_url_file, 1);
    			}
            }else if("2".equals(resource_type)){
            	if(!CollectionUtil.checkNull(resource_url_url)){
    				jsonObj.put("res", "视频连接地址不能为空！");
    				return null;
    			}
            }else if("3".equals(resource_type)){
            	if(!CollectionUtil.checkNull(resource_url_img)){
    				jsonObj.put("res", "图片不能为空！");
    				return null;
    			}else{
    				resource_url_img=CollectionUtil.tobereplace(resource_url_img, 1);
    			}
            	if(!CollectionUtil.checkNull(resource_sort_img) || resource_sort_img.contains("null")){
    				jsonObj.put("res", "图片排序不能为空！");
    				return null;
    			}
            }else if("4".equals(resource_type)){
            	if(!CollectionUtil.checkNull(resource_url_html)){
    				jsonObj.put("res", "HTML5不能为空！");
    				return null;
    			}else{
    				resource_url_html=CollectionUtil.toplaybereplace(resource_url_html, 1);
    			}
            }
     		
			if(!CollectionUtil.checkNull(resource_lyrics)){
				resource_lyrics=null;
			}else{
				resource_lyrics=CollectionUtil.tobereplace(resource_lyrics, 1);
			}
			if("3".equals(resource_type_id)){
				int ii=this.resourceInfoService.check_set_number(resource_id, set_number);
				if(ii>0){
    				jsonObj.put("res", "该动画片集数已存在，请从新输入！");
    				return null;
    			}
			}
			
			Resource resource=this.resourceService.findUseResourceInfo(resource_id);
			//5-游戏、4-绘本、3-动画、2-视频、1-音频
			String type_id=resource.getResource_type_id();
			List<ResourceInfo> rlist=this.resourceInfoService.get_check_info(resource_id);
			if(rlist!=null && rlist.size()>0){
				if(type_id.equals("1")||type_id.equals("2")){
					jsonObj.put("res", "该资源信息为1对1,一个资源信息不能上传多个资源,不能再次添加");
					return null;
				}
				for(ResourceInfo r:rlist){
					//(1-音频 2-视频 3-图片 4-HTML5)
					if(r.getResource_type().equals("1")|| r.getResource_type().equals("4")){
						jsonObj.put("res", "该资源信息为1对1,一个资源信息不能上传多个资源,不能再次添加");
						return null;
					}
					if(type_id.equals("4")){
						if(r.getResource_type().equals("1")||r.getResource_type().equals("2")){
							jsonObj.put("res", "该资源信息为1对1,一个资源信息不能上传多个资源,不能再次添加");
							return null;
						}
					}
				}
			}
			
     		
			int in=0;
			
			//图片类型
     		if("3".equals(resource_type)){
     			List<ResourceInfo> resourceInfoList=new ArrayList<ResourceInfo>();
     			ResourceInfo r=null;
     			String[] img_arr=resource_url_img.split(";");
     			String[] sort_arr=resource_sort_img.split(";");
     			for(int i=0;i<img_arr.length;i++){
     				r=new ResourceInfo();
     				r.setResource_id(resource_id);
     				r.setCreate_adminuser(adminAccount);
     				r.setResource_info_type("4");
     				r.setResource_type(resource_type);
     				r.setResource_url(img_arr[i]);
     				r.setResource_sort(sort_arr[i]);
     				resourceInfoList.add(r);
     			}
            	in=this.resourceInfoService.insertBatchResourceInfo(resourceInfoList, 4);
     		}else{
     			ResourceInfo ResourceInfo=new ResourceInfo();
     			ResourceInfo.setResource_id(resource_id);
     			ResourceInfo.setResource_info_type("4");
     			ResourceInfo.setResource_type(resource_type);
     			if("2".equals(resource_type)) {
     				ResourceInfo.setResource_url(resource_url_url);
     			} else if("1".equals(resource_type)) {
     				ResourceInfo.setResource_url(resource_url_file);
     			} else if("4".equals(resource_type)) {
     				ResourceInfo.setResource_url(resource_url_html);
     			}
     			if("3".equals(resource_type_id)){
     				ResourceInfo.setSet_number(set_number);
     				ResourceInfo.setResource_sort(resource_sort_cartoon);
     			}
     			if("1".equals(resource_type_id)){
     				ResourceInfo.setResource_lyrics(resource_lyrics);
     			}
     			ResourceInfo.setCreate_adminuser(adminAccount);
     			in=this.resourceInfoService.insertResourceInfo(ResourceInfo);
     		}
			
     		if(in>0){
				state=1;
				memo+="资源详情添加成功！";
				jsonObj.put("res", "资源详情添加成功！");
				jsonObj.put("su", "1");
			}else{
				memo+="资源详情添加失败！";
				jsonObj.put("res", "资源详情添加失败！");
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}catch(Exception e){
			memo+="资源详情添加 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源详情修改
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="doupdateresourceinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String doUpdateResourceInfo(Model model,HttpServletRequest request,HttpServletResponse response
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
			String resource_id=(String)request.getParameter("resource_id");
			String resource_type_id=(String)request.getParameter("resource_type_id");
			String resource_type=(String)request.getParameter("resource_type");
     		String set_number=(String)request.getParameter("set_number");
     		String resource_url_url=(String)request.getParameter("resource_url_url");
     		String resource_url_file=(String)request.getParameter("resource_url_file");
     		String resource_url_img=(String)request.getParameter("resource_url_img");
     		String resource_url_html=(String)request.getParameter("resource_url_html");
     		String resource_sort_cartoon=(String)request.getParameter("resource_sort_cartoon");
     		String resource_sort_img=(String)request.getParameter("resource_sort_img");
     		String resource_lyrics=(String)request.getParameter("resource_lyrics");
     		
     		
     		if(!CollectionUtil.checkNull(id)){
				jsonObj.put("res", "资源详情序列号不能为空！");
				return null;
			}
     		
     		if(!CollectionUtil.checkNull(resource_type_id)){
				jsonObj.put("res", "资源类型不能为空！");
				return null;
			}
     		if(!CollectionUtil.checkNull(resource_type)){
				jsonObj.put("res", "资源详情类型不能为空！");
				return null;
			}
     		if("3".equals(resource_type_id)){
     			if(!CollectionUtil.checkNull(set_number)){
    				jsonObj.put("res", "动画集数不能为空！");
    				return null;
    			}
     			if(!CollectionUtil.checkNull(resource_sort_cartoon)){
    				jsonObj.put("res", "排序不能为空！");
    				return null;
    			}
     		}else{
     			set_number=null;
     			resource_sort_cartoon=null;
     		}
     		
     		if("1".equals(resource_type)){
     			if(!CollectionUtil.checkNull(resource_url_file)){
    				jsonObj.put("res", "资源音频文件不能为空！");
    				return null;
    			}else{
    				resource_url_file=CollectionUtil.tobereplace(resource_url_file, 1);
    			}
            }else if("2".equals(resource_type)){
            	if(!CollectionUtil.checkNull(resource_url_url)){
    				jsonObj.put("res", "视频连接地址不能为空！");
    				return null;
    			}
            }else if("3".equals(resource_type)){
            	if(!CollectionUtil.checkNull(resource_url_img)){
    				jsonObj.put("res", "图片不能为空！");
    				return null;
    			}else{
    				resource_url_img=CollectionUtil.tobereplace(resource_url_img, 1);
    			}
            	if(!CollectionUtil.checkNull(resource_sort_img) || resource_sort_img.contains("null")){
    				jsonObj.put("res", "图片排序不能为空！");
    				return null;
    			}
            }else if("4".equals(resource_type)){
            	if(!CollectionUtil.checkNull(resource_url_html)){
    				jsonObj.put("res", "HTML5不能为空！");
    				return null;
    			}else{
    				resource_url_html=CollectionUtil.toplaybereplace(resource_url_html, 1);
    			}
            }
     		
			if(!CollectionUtil.checkNull(resource_lyrics)){
				resource_lyrics=null;
			}else{
				resource_lyrics=CollectionUtil.tobereplace(resource_lyrics, 1);
			}
			
			//获得该资源详情信息
			ResourceInfo ResourceInfo=this.resourceInfoService.findResourceInfoOne(id);
			if(ResourceInfo==null){
				jsonObj.put("res", "资源详情获取失败或资源详情序列号错误！");
				return null;
			}
			
			if("3".equals(resource_type_id)){
				int ii=this.resourceInfoService.check_set_number(resource_id, set_number,id);
				if(ii>0){
    				jsonObj.put("res", "该动画片集数已存在，请从新输入！");
    				return null;
    			}
			}
			
			//图片类型
 			ResourceInfo.setResource_type(resource_type);
 			if("2".equals(resource_type)) {
 				ResourceInfo.setResource_url(resource_url_url);
 			} else if("1".equals(resource_type)) {
 				ResourceInfo.setResource_url(resource_url_file);
 			}else if("3".equals(resource_type)) {
 				ResourceInfo.setResource_url(resource_url_img);
 				ResourceInfo.setResource_sort(resource_sort_img);
 			}else if("4".equals(resource_type)) {
 				ResourceInfo.setResource_url(resource_url_html);
 			}
 			if("3".equals(resource_type_id)){
 				ResourceInfo.setSet_number(set_number);
 				ResourceInfo.setResource_sort(resource_sort_cartoon);
 			}
 			if("1".equals(resource_type_id)){
 				ResourceInfo.setResource_lyrics(resource_lyrics);
 			}
			ResourceInfo.setUpdate_adminuser(adminAccount);
			
			int in=this.resourceInfoService.updateResourceInfo(ResourceInfo);
			if(in>0){
				state=1;
				memo+="修改资源详情"+id+"成功！";
				jsonObj.put("res", memo);
			}else{
				memo+="修改资源详情"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}catch(Exception e){
			memo+="资源详情修改 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	/**
	 * 资源详情删除
	 * @param model
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping(value="dodeleteresourceinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String doDeleteResourceInfo(Model model,HttpServletRequest request,HttpServletResponse response
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
//				jsonObj.put("res", "该详情在资源信息里面引用到，删除失败！");
//				return null;
//			}
			
			int in=this.resourceInfoService.deleteResourceInfo(id);
			if(in>0){
				state=1;
				memo+="删除资源详情"+id+"成功！";
				jsonObj.put("res", "1");
				jsonObj.put("su", memo);
			}else{
				memo+="删除资源详情"+id+"失败！";
				jsonObj.put("res", memo);
			}
			//记录日志
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}catch(Exception e){
			memo+="资源详情删除 异常："+e.getMessage();
			logger.error(memo, e);
			jsonObj.put("res", memo);
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}finally {
			printWriter.write(jsonObj.toString());
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	/**
	 * 资源详情查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doresourceinfolist")
	public String doResourceInfoList(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String resource_id=(String)request.getParameter("resource_id");
			String cIndex=request.getParameter("index");
			
			Resource resource=this.resourceService.findUseResourceInfo(resource_id);
			if(resource==null){
				request.setAttribute("msg", "该资源未找到！");
				return null;
			}
			request.setAttribute("resource", resource);
			
			ResourceInfo ResourceInfo=new ResourceInfo();
			ResourceInfo.setResource_id(resource_id);
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/resourceinfo/doresourceinfolist?resource_id="+resource_id;
			//总行数
			int dataCount=this.resourceInfoService.findResourceInfoCount(ResourceInfo);
			//获得该页集合
			List<ResourceInfo> resourceInfoList=this.resourceInfoService.findResourceInfoList(ResourceInfo,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(resourceInfoList!=null && resourceInfoList.size()>0){
				//(1-音频 2-视频 3-图片 4-HTML5)
				for(ResourceInfo r:resourceInfoList){
					if(r.getResource_type().equals("1")||r.getResource_type().equals("3")){
						if(CollectionUtil.checkNull(r.getResource_url())){
							r.setResource_url(CollectionUtil.tobereplace(r.getResource_url(), 0));
						}
					}
					if(r.getResource_type().equals("4")){
						if(CollectionUtil.checkNull(r.getResource_url())){
							r.setResource_url(CollectionUtil.toplaybereplace(r.getResource_url(), 0));
						}
					}
					if(CollectionUtil.checkNull(r.getResource_lyrics())){
						r.setResource_lyrics(CollectionUtil.tobereplace(r.getResource_lyrics(), 0));
					}
					
				}
				request.setAttribute("resourceinfoList", resourceInfoList);
			}
			
			Resource Resource=this.resourceService.findUseResourceInfo(resource_id);
			request.setAttribute("resource", Resource);
			
			//记录日志
			memo+="资源详情查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
			
		}catch(Exception e){
			memo+="资源详情查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_zyxq, memo, state, request);
		}
		return "function/resource/resource_info_list";
	}
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
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
	
}
