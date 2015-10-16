package com.manager.function.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.GlobalHobby;

@Service
public interface GlobalHobbyService {

	public void add(GlobalHobby gh);

	public int getId();

	public void delete(GlobalHobby gh,String reg);

	public void update(GlobalHobby gh,String reg);

	public GlobalHobby getOne(String id);

	public List<GlobalHobby> get(GlobalHobby gh, int pageNo, int pageSize);

	public int count(GlobalHobby gh);

	public List<GlobalHobby> NoAbolish_GlobalHobbyList(HttpServletRequest request);
}
