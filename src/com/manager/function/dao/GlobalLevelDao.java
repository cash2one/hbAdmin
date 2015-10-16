package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.GlobalLevel;

public interface GlobalLevelDao {
	
	public void add(GlobalLevel gl);
	
	public int getId();
	
	public void delete(GlobalLevel gl);
	
	public void update(GlobalLevel gl);
	
	public GlobalLevel getOne(String id);
	
	public List<GlobalLevel> get(GlobalLevel gl,int pageNo,int pageSize);
	
	public List<GlobalLevel> get(GlobalLevel gl);
	
	public int count(GlobalLevel gl);
	
	public int sortcount(String sort);

	public List<GlobalLevel> NoAbolish_GlobalLevelList();
	
	public List<GlobalLevel> get_select_peoperty(int ii);
	
	public List<GlobalLevel> get_select_hobby(int ii);
	
	public List<GlobalLevel> get_div_peoperty(List<GlobalLevel> globalLevelList);
	
	public List<GlobalLevel> get_div_hobby(List<GlobalLevel> globalLevelList); 
}
