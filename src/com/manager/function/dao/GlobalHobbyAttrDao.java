package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.GlobalHobbyAttr;

public interface GlobalHobbyAttrDao {
	
	public List<GlobalHobbyAttr> get(String hobby_id);
	
	public void deleteBatch(List<GlobalHobbyAttr> ls);
	
	public void insertBatch(List<GlobalHobbyAttr> ls);
	
}
