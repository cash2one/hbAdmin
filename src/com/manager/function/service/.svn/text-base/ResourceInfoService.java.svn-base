package com.manager.function.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manager.function.entity.ResourceInfo;

@Service
public interface ResourceInfoService {

	public List<ResourceInfo> findResourceInfoList(ResourceInfo ResourceInfo,int pageNo,int pageSize);
	
	public int findResourceInfoCount(ResourceInfo ResourceInfo);
	
	public int updateResourceInfo(ResourceInfo ResourceInfo);
	
	public int updateResourceInfoStatus(ResourceInfo ResourceInfo);
	
	public int deleteResourceInfo(String id);
	
	public int insertResourceInfo(ResourceInfo ResourceInfo);
	
	public List<ResourceInfo> findResourceInfoList();
	
	public ResourceInfo findResourceInfoOne(String id);
	
	public int insertBatchResourceInfo(List<ResourceInfo> ResourceInfoList,int n);
	
	public List<ResourceInfo> checkboxResourceInfoList(String resource_id);
	
	public List<ResourceInfo>  get_check_info(String resource_id);
	
	public int check_set_number(String resource_id,String set_number);
	
	public int check_set_number(String resource_id,String set_number,String id);
}
