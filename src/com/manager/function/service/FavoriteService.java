package com.manager.function.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface FavoriteService {

	public Map add(HttpServletRequest request); 
	
	public Map delete(HttpServletRequest request);
	
	public Map delete1(HttpServletRequest request);
	
}
