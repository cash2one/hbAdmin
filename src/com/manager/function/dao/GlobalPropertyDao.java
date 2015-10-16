package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.GlobalProperty;

public interface GlobalPropertyDao {
	
	public void add(GlobalProperty gp);
	
	public int getId();
	
	public void delete(GlobalProperty gp);
	
	public void update(GlobalProperty gp);
	
	public GlobalProperty getOne(String id);
	
	public List<GlobalProperty> get(GlobalProperty gp,int pageNo,int pageSize);
	
	public List<GlobalProperty> get();
	
	public int count(GlobalProperty gp);
	
	public List<GlobalProperty> NoAbolish_GlobalPropertyList();
	
	public int sortcount(String sort);
	
	public List<GlobalProperty> findByLevel(GlobalProperty gp);
	
	public List<GlobalProperty> findByBabyId(GlobalProperty gp);
	
	public List<GlobalProperty> findByBabyId_buquan(GlobalProperty gp);
	
	public List<GlobalProperty> shudan(GlobalProperty gp);
}
