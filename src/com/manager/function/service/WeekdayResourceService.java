package com.manager.function.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manager.function.entity.WeekdayResource;

@Service
public interface WeekdayResourceService {

	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int pageNo,int pageSize,int n);
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int n);
	
	public int findWeekdayResourceCount(WeekdayResource WeekdayResource,int n);
	
	public int deleteWeekdayResource(String id);
	
	public int insertWeekdayResource(WeekdayResource WeekdayResource);
	
	public List<WeekdayResource> findWeekdayResourceList();
	
	public WeekdayResource findWeekdayResourceOne(String id);
	
	public int checkWeekdayResourceTypeName(String type_name);
	
	public int checkWeekdayResourceTypeName(String id,String type_name);
	
	public int batchInsertWeekdayResource(List<WeekdayResource> WeekdayResourceList);
	
	public int checkWeekday(String weekday_id);
	
	public List<WeekdayResource> SelectWeekdayResourceList();
}
