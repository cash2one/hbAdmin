package com.manager.function.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.ResourceInfoDao;
import com.manager.function.entity.ResourceInfo;
import com.manager.function.service.ResourceInfoService;

public class ResourceInfoServiceImpl implements ResourceInfoService {
	
	@Autowired
	private ResourceInfoDao resourceInfoDao;
	

	public int deleteResourceInfo(String id) {
		ResourceInfo ResourceInfo=new ResourceInfo();
		ResourceInfo.setId(id);
		return this.resourceInfoDao.deleteResourceInfo(ResourceInfo);
	}

	public int findResourceInfoCount(ResourceInfo ResourceInfo) {
		return this.resourceInfoDao.findResourceInfoCount(ResourceInfo);
	}
	
	public int check_set_number(String resource_id,String set_number){
		ResourceInfo resourceInfo=new ResourceInfo();
		resourceInfo.setResource_id(resource_id);
		resourceInfo.setSet_number(set_number);
		return this.resourceInfoDao.findResourceInfoCount(resourceInfo);
	}
	
	public int check_set_number(String resource_id,String set_number,String id){
		ResourceInfo resourceInfo=new ResourceInfo();
		resourceInfo.setResource_id(resource_id);
		resourceInfo.setSet_number(set_number);
		List<ResourceInfo> rlist=this.resourceInfoDao.findResourceInfoList(resourceInfo);
		int ii=0;
		if(rlist!=null && rlist.size()>0){
			ii=rlist.size();
			if(id!=null){
				for(int i=0;i<rlist.size();i++){
					ResourceInfo bmt=rlist.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}

	public List<ResourceInfo> findResourceInfoList(ResourceInfo ResourceInfo, int pageNo, int pageSize) {
		return this.resourceInfoDao.findResourceInfoList(ResourceInfo, pageNo, pageSize);
	}
	
	public List<ResourceInfo> checkboxResourceInfoList(String resource_id){
		ResourceInfo resourceInfo=new ResourceInfo();
		resourceInfo.setResource_id(resource_id);
		return this.resourceInfoDao.checkboxResourceInfoList(resourceInfo);
	}
	
	public List<ResourceInfo>  get_check_info(String resource_id){
		ResourceInfo resourceInfo=new ResourceInfo();
		resourceInfo.setResource_id(resource_id);
		return this.resourceInfoDao.get_check_info(resourceInfo);
	}

	public List<ResourceInfo> findResourceInfoList() {
		return this.resourceInfoDao.findResourceInfoList(null);
	}
	
	public int insertResourceInfo(ResourceInfo ResourceInfo) {
		return this.resourceInfoDao.insertResourceInfo(ResourceInfo);
	}
	
	public int insertBatchResourceInfo(List<ResourceInfo> ResourceInfoList,int n){
		return this.resourceInfoDao.insertBatchResourceInfo(ResourceInfoList,n);
	}

	public int updateResourceInfo(ResourceInfo ResourceInfo) {
		return this.resourceInfoDao.updateResourceInfo(ResourceInfo);
	}

	public int updateResourceInfoStatus(ResourceInfo ResourceInfo) {
		return this.resourceInfoDao.updateResourceInfoStatus(ResourceInfo);
	}
	
	public ResourceInfo findResourceInfoOne(String id){
		ResourceInfo ResourceInfo=new ResourceInfo();
		ResourceInfo.setId(id);
		List<ResourceInfo> ResourceInfoList=this.resourceInfoDao.findResourceInfoList(ResourceInfo);
		if(ResourceInfoList!=null && ResourceInfoList.size()>0){
			return ResourceInfoList.get(0);
		}
		return null;
	}
	

	/**
	 * @return the ResourceInfoDao
	 */
	public ResourceInfoDao getResourceInfoDao() {
		return resourceInfoDao;
	}

	/**
	 * @param ResourceInfoDao the ResourceInfoDao to set
	 */
	public void setResourceInfoDao(ResourceInfoDao resourceInfoDao) {
		this.resourceInfoDao = resourceInfoDao;
	}
	
}
