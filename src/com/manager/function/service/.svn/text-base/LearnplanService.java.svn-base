package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.Learnplan;

@Service
public interface LearnplanService {

	public Map learnplan(HttpServletRequest request) throws Exception;
	
	public Map relearnplan(HttpServletRequest request) throws Exception;
	
	public Map updatelearnplan(HttpServletRequest request);
	
	public List<Learnplan> findLearnplanList(Learnplan Learnplan,int pageNo,int pageSize);
	
	public List<Learnplan> findLearnplanList(Learnplan Learnplan);
	
	public int findLearnplanCount(Learnplan Learnplan);
	
	public int deleteLearnplan(String id);
	
	public int updateLearnplan(Learnplan Learnplan);
	
	public int insertLearnplan(Learnplan Learnplan);
	
	public List<Learnplan> findLearnplanList();
	
	public Learnplan findLearnplanOne(String id);
	
}
