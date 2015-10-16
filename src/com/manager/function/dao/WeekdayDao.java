package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.Weekday;


public interface WeekdayDao {

	public List<Weekday> findWeekdayList(Weekday Weekday,int pageNo,int pageSize);
	
	public List<Weekday> findWeekdayList(Weekday Weekday);
	
	public int findWeekdayCount(Weekday Weekday);
	
	public int deleteWeekday(Weekday Weekday);
	
	public int insertWeekday(Weekday Weekday);
	
	public int updateWeekday(Weekday Weekday);
	
	public List<Weekday> SelectWeekdayList();
	
	public Weekday findWeekday(Weekday Weekday);
	
}
