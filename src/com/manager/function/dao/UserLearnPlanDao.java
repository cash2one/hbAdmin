package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.UserLearnplan;

public interface UserLearnPlanDao {
	
	public List<UserLearnplan> findByplanId(UserLearnplan ulp);
	
	public UserLearnplan findOne(String id);
	
	public int getNotFinushNum(UserLearnplan ulp);
	
	public int isFinush(UserLearnplan ulp);
	
	public int getId();

	public int getPlanId(String baby_id);
	
	public void add(UserLearnplan ulp);
	
	public void add1(UserLearnplan ulp);
	
	public void add2(UserLearnplan ulp);
	
	public void update(UserLearnplan ulp);
	
	public void delete(UserLearnplan ulp);
	
	public List<UserLearnplan> get_statistics_date();
	
	public List<UserLearnplan> get_statistics(String num,UserLearnplan UserLearnplan,int pageNo,int pageSize);
	
	public int get_statistics_count(String num,UserLearnplan UserLearnplan);
	
	public List<UserLearnplan> get_statistics(String num,UserLearnplan UserLearnplan);
	
}
