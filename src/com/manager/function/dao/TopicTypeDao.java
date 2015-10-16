package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.TopicType;


public interface TopicTypeDao {

	public List<TopicType> findTopicTypeList(TopicType TopicType,int pageNo,int pageSize);
	
	public int findTopicTypeCount(TopicType TopicType);
	
	public int updateTopicType(TopicType TopicType);
	
	public int updateTopicTypeStatus(TopicType TopicType);
	
	public int deleteTopicType(TopicType TopicType);
	
	public int insertTopicType(TopicType TopicType);
	
	public List<TopicType> findTopicTypeList(TopicType TopicType);
	
	public List<TopicType> NoAbolish_TopicTypeList();
	
	public List<TopicType> getTopicType();
	
}
