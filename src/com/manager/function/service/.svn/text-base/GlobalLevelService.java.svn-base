package com.manager.function.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.GlobalLevel;

@Service
public interface GlobalLevelService {

	public void add(GlobalLevel gl);

	public int getId();

	public void delete(GlobalLevel gl,String reg);

	public void update(GlobalLevel gl,String reg);

	public GlobalLevel getOne(String id);

	public List<GlobalLevel> get(GlobalLevel gl, int pageNo, int pageSize);
	
	public List<GlobalLevel> get(GlobalLevel gl);

	public int count(GlobalLevel gl);
	
	public int sortcount(String sort);
	
	public List<GlobalLevel> NoAbolish_GlobalLevelList(HttpServletRequest request);

	public void get_select(HttpServletRequest request);
	
	public List<GlobalLevel> get_select_all_hobby();
	
	public List<GlobalLevel> get_select_all_peoperty();
	
	public List<GlobalLevel> get_div_peoperty(List<GlobalLevel> globalLevelList);
	
	public List<GlobalLevel> get_div_hobby(List<GlobalLevel> globalLevelList); 
}
