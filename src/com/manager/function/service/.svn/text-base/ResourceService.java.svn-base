package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.manager.function.entity.Resource;
import com.manager.function.entity.ResourceInfo;

@Service
public interface ResourceService {

	public List<Resource> findResourceList(Resource Resource,int pageNo,int pageSize);
	
	public int findResourceCount(Resource Resource);
	
	public List<Resource> findResourceWeekdayList(Resource Resource,int pageNo,int pageSize);
	
	public int findResourceWeekdayCount(Resource Resource);
	
	public int updateResource(Resource Resource,List<ResourceInfo> addInfoList1,List<ResourceInfo> addInfoList2,List<ResourceInfo> addInfoList3
			,List<ResourceInfo> deleteInfoList);
	
	public int updateResourceStatus(Resource Resource);
	
	public int deleteResource(String id);
	
	public int insertResource(Resource Resource,List<ResourceInfo> resourceInfoList1,List<ResourceInfo> resourceInfoList2,List<ResourceInfo> resourceInfoList3);
	
	public List<Resource> findResourceList();
	
	public Resource findResourceOne(String id);
	
	public int checkResourceResourceContent(String resource_content,String resource_type_id);
	
	public int checkResourceResourceContent(String id,String resource_content,String resource_type_id);
	
	public String getid();
	
	public Resource findUseResourceInfo(String id);
	
	public Map publicresource(HttpServletRequest request);
	
	public Map updatepublicresource(HttpServletRequest request);
	
	public Map search(HttpServletRequest request);
	
	public Map searchpre(HttpServletRequest request);
	
	public Map getKeyword(HttpServletRequest request);
	
	public Map getKeywordpre(HttpServletRequest request);
	
	public Map fList(HttpServletRequest request);
	
	public Map fListpre(HttpServletRequest request);
	
	public Map getPlay(HttpServletRequest request);
	
	public JSONObject getResourceByID(String id) throws Exception;
}
