package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.GlobalHobby;

public interface GlobalHobbyDao {
	
	public void add(GlobalHobby gh);
	
	public int getId();
	
	public void delete(GlobalHobby gh);
	
	public void update(GlobalHobby gh);
	
	public GlobalHobby getOne(String id);
	
	public List<GlobalHobby> get(GlobalHobby gh,int pageNo,int pageSize);
	
	public int count(GlobalHobby gh);
	
	public List<GlobalHobby> NoAbolish_GlobalHobbyList();

}
