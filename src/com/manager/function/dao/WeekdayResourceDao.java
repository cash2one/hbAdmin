package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.WeekdayResource;


public interface WeekdayResourceDao {

	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int pageNo,int pageSize,int n);
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int n);
	
	public int findWeekdayResourceCount(WeekdayResource WeekdayResource,int n);
	
	public int deleteWeekdayResource(WeekdayResource WeekdayResource);
	
	public int insertWeekdayResource(WeekdayResource WeekdayResource);
	
	public int batchInsertWeekdayResource(List<WeekdayResource> WeekdayResourceList);
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource);
	
	public List<WeekdayResource> SelectWeekdayResourceList();
	
	public WeekdayResource findWeekday(WeekdayResource WeekdayResource);
	
	public void add(WeekdayResource WeekdayResource);
	
	public int getId();
	
	public void update(WeekdayResource WeekdayResource);
	
	public int wdCount(WeekdayResource WeekdayResource);
}
