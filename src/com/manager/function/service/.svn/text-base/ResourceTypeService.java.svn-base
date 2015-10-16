package com.manager.function.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.ResourceType;

@Service
public interface ResourceTypeService {

	public List<ResourceType> findResourceTypeList(ResourceType ResourceType,int pageNo,int pageSize);
	
	public int findResourceTypeCount(ResourceType ResourceType);
	
	public int updateResourceType(ResourceType ResourceType);
	
	public int updateResourceTypeStatus(ResourceType ResourceType);
	
	public int deleteResourceType(String id);
	
	public int insertResourceType(ResourceType ResourceType);
	
	public List<ResourceType> findResourceTypeList();
	
	public ResourceType findResourceTypeOne(String id);
	
	public int checkResourceTypeTypeName(String type_name);
	
	public int checkResourceTypeTypeName(String id,String type_name);
	
	public List<ResourceType> NoAbolish_ResourceTypeList(HttpServletRequest request);
}
