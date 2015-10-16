package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.GlobalProperty;

@Service
public interface GlobalPropertyService {

	public void add(GlobalProperty gp);

	public int getId();

	public void delete(GlobalProperty gp,String reg);

	public void update(GlobalProperty gp,String reg);

	public GlobalProperty getOne(String id);

	public List<GlobalProperty> get(GlobalProperty gp, int pageNo, int pageSize);

	public int count(GlobalProperty gp);
	
	public List<GlobalProperty> NoAbolish_GlobalPropertyList(HttpServletRequest request);
	
	public int sortcount(String sort);
	
	public Map getProperty(HttpServletRequest request);

}
