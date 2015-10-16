package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.TopicType;

@Service
public interface TopicTypeService {

	public List<TopicType> findTopicTypeList(TopicType TopicType,int pageNo,int pageSize);
	
	public int findTopicTypeCount(TopicType TopicType);
	
	public int updateTopicType(TopicType TopicType);
	
	public int updateTopicTypeStatus(TopicType TopicType);
	
	public int deleteTopicType(String id);
	
	public int insertTopicType(TopicType TopicType);
	
	public List<TopicType> findTopicTypeList();
	
	public TopicType findTopicTypeOne(String id);
	
	public int checkTopicTypeName(String name);
	
	public int checkTopicTypeName(String id,String name);
	
	public int checkTopicTypeSort(String sort);
	
	public int checkTopicTypeSort(String id,String sort);
	
	public List<TopicType> NoAbolish_TopicTypeList(HttpServletRequest request);
	
	public Map getTopicType(HttpServletRequest request);
}
