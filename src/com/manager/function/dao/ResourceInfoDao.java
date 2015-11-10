package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.ResourceInfo;


public interface ResourceInfoDao {

	public List<ResourceInfo> findResourceInfoList(ResourceInfo ResourceInfo,int pageNo,int pageSize);
	
	public int findResourceInfoCount(ResourceInfo ResourceInfo);
	
	public int updateResourceInfo(ResourceInfo ResourceInfo);
	
	public int updateResourceInfoStatus(ResourceInfo ResourceInfo);
	
	public int deleteResourceInfo(ResourceInfo ResourceInfo);
	
	public int delete2ResourceInfo(ResourceInfo ResourceInfo);
	
	public int deleteResourceInfoByResource(ResourceInfo ResourceInfo);
	
	public int insertResourceInfo(ResourceInfo ResourceInfo);
	
	public List<ResourceInfo> findResourceInfoList(ResourceInfo ResourceInfo);
	
	public List<ResourceInfo> checkboxResourceInfoList(ResourceInfo ResourceInfo);
	
	public int insertBatchResourceInfo(List<ResourceInfo> ResourceInfoList,int n);
	
	public List<ResourceInfo> publicresource(ResourceInfo ResourceInfo);
	
	public List<ResourceInfo> publicresource1(ResourceInfo ResourceInfo);
	
	public List<ResourceInfo> findByResourceId(String resource_id);
	
	public int getResLanguage(String resource_id);
	
	public List<ResourceInfo> findByResourceId1(String resource_id);
	
	public int deleteBatchResourceInfo(List<ResourceInfo> ResourceInfoList,int n);
	
	public  List<ResourceInfo>  get_check_info(ResourceInfo ResourceInfo);
	
	public List<ResourceInfo> findPropertyId(String resource_id);
	
}
