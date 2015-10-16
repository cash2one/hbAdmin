package com.manager.function.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.ResourceTypeDao;
import com.manager.function.entity.ResourceType;
import com.manager.function.service.ResourceTypeService;

public class ResourceTypeServiceImpl implements ResourceTypeService {
	
	@Autowired
	private ResourceTypeDao resourceTypeDao;
	

	public int deleteResourceType(String id) {
		ResourceType ResourceType=new ResourceType();
		ResourceType.setId(id);
		return this.resourceTypeDao.deleteResourceType(ResourceType);
	}

	public int findResourceTypeCount(ResourceType ResourceType) {
		return this.resourceTypeDao.findResourceTypeCount(ResourceType);
	}

	public List<ResourceType> findResourceTypeList(ResourceType ResourceType, int pageNo, int pageSize) {
		return this.resourceTypeDao.findResourceTypeList(ResourceType, pageNo, pageSize);
	}

	public List<ResourceType> findResourceTypeList() {
		return this.resourceTypeDao.findResourceTypeList(null);
	}
	
	public List<ResourceType> NoAbolish_ResourceTypeList(HttpServletRequest request){
		List<ResourceType> resourceTypeList=this.resourceTypeDao.NoAbolish_ResourceTypeList();
		request.setAttribute("noabolish_resourcetype", resourceTypeList);
		return resourceTypeList;
	}

	public int insertResourceType(ResourceType ResourceType) {
		return this.resourceTypeDao.insertResourceType(ResourceType);
	}

	public int updateResourceType(ResourceType ResourceType) {
		return this.resourceTypeDao.updateResourceType(ResourceType);
	}

	public int updateResourceTypeStatus(ResourceType ResourceType) {
		return this.resourceTypeDao.updateResourceTypeStatus(ResourceType);
	}
	
	public ResourceType findResourceTypeOne(String id){
		ResourceType ResourceType=new ResourceType();
		ResourceType.setId(id);
		List<ResourceType> ResourceTypeList=this.resourceTypeDao.findResourceTypeList(ResourceType);
		if(ResourceTypeList!=null && ResourceTypeList.size()>0){
			return ResourceTypeList.get(0);
		}
		return null;
	}
	
	public int checkResourceTypeTypeName(String type_name){
		ResourceType ResourceType=new ResourceType();
		ResourceType.setType_name(type_name);
		List<ResourceType> bewsTypeList=this.resourceTypeDao.findResourceTypeList(ResourceType);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkResourceTypeTypeName(String id,String type_name){
		ResourceType ResourceType=new ResourceType();
		ResourceType.setType_name(type_name);
		List<ResourceType> bewsTypeList=this.resourceTypeDao.findResourceTypeList(ResourceType);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					ResourceType bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}


	/**
	 * @return the ResourceTypeDao
	 */
	public ResourceTypeDao getResourceTypeDao() {
		return resourceTypeDao;
	}

	/**
	 * @param ResourceTypeDao the ResourceTypeDao to set
	 */
	public void setResourceTypeDao(ResourceTypeDao resourceTypeDao) {
		this.resourceTypeDao = resourceTypeDao;
	}
	
}
