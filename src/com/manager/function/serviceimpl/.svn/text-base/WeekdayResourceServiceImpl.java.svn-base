package com.manager.function.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.WeekdayResourceDao;
import com.manager.function.entity.WeekdayResource;
import com.manager.function.service.WeekdayResourceService;

public class WeekdayResourceServiceImpl implements WeekdayResourceService {
	
	@Autowired
	private WeekdayResourceDao weekdayResourceDao;
	

	public int deleteWeekdayResource(String id) {
		WeekdayResource WeekdayResource=new WeekdayResource();
		WeekdayResource.setId(id);
		return this.weekdayResourceDao.deleteWeekdayResource(WeekdayResource);
	}

	public int findWeekdayResourceCount(WeekdayResource WeekdayResource,int n) {
		return this.weekdayResourceDao.findWeekdayResourceCount(WeekdayResource,n);
	}

	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource, int pageNo, int pageSize,int n) {
		return this.weekdayResourceDao.findWeekdayResourceList(WeekdayResource, pageNo, pageSize,n);
	}
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int n) {
		return this.weekdayResourceDao.findWeekdayResourceList(WeekdayResource, n);
	}

	public List<WeekdayResource> findWeekdayResourceList() {
		return this.weekdayResourceDao.findWeekdayResourceList(null);
	}
	
	public int insertWeekdayResource(WeekdayResource WeekdayResource) {
		return this.weekdayResourceDao.insertWeekdayResource(WeekdayResource);
	}
	
	public int batchInsertWeekdayResource(List<WeekdayResource> WeekdayResourceList){
		return this.weekdayResourceDao.batchInsertWeekdayResource(WeekdayResourceList);
	}

	public List<WeekdayResource> SelectWeekdayResourceList(){
		return this.weekdayResourceDao.SelectWeekdayResourceList();
	}
	
	public WeekdayResource findWeekdayResourceOne(String id){
		WeekdayResource WeekdayResource=new WeekdayResource();
		WeekdayResource.setId(id);
		List<WeekdayResource> WeekdayResourceList=this.weekdayResourceDao.findWeekdayResourceList(WeekdayResource);
		if(WeekdayResourceList!=null && WeekdayResourceList.size()>0){
			return WeekdayResourceList.get(0);
		}
		return null;
	}
	
	public int checkWeekday(String weekday_id){
		WeekdayResource WeekdayResource=new WeekdayResource();
		WeekdayResource.setWeekday_id(weekday_id);
		WeekdayResource w=this.weekdayResourceDao.findWeekday(WeekdayResource);
		if(w!=null && !"".equals(w.getStart_date())){
			return 1;
		}
		return 0;
	}
	
	public int checkWeekdayResourceTypeName(String type_name){
		WeekdayResource WeekdayResource=new WeekdayResource();
//		WeekdayResource.setType_name(type_name);
		List<WeekdayResource> bewsTypeList=this.weekdayResourceDao.findWeekdayResourceList(WeekdayResource);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkWeekdayResourceTypeName(String id,String type_name){
		WeekdayResource WeekdayResource=new WeekdayResource();
//		WeekdayResource.setType_name(type_name);
		List<WeekdayResource> bewsTypeList=this.weekdayResourceDao.findWeekdayResourceList(WeekdayResource);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					WeekdayResource bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}


	/**
	 * @return the WeekdayResourceDao
	 */
	public WeekdayResourceDao getWeekdayResourceDao() {
		return weekdayResourceDao;
	}

	/**
	 * @param WeekdayResourceDao the WeekdayResourceDao to set
	 */
	public void setWeekdayResourceDao(WeekdayResourceDao weekdayResourceDao) {
		this.weekdayResourceDao = weekdayResourceDao;
	}
	
}
