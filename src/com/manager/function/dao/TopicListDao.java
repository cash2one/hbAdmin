package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.TopicList;


public interface TopicListDao {

	public List<TopicList> findTopicListList(TopicList TopicList,int pageNo,int pageSize);
	
	public List<TopicList> getByType(TopicList TopicList,int pageNo,int pageSize);
	
	public int findTopicListCount(TopicList TopicList);
	
	public int updateTopicList(TopicList TopicList);
	
	public int updateTopicListStatus(TopicList TopicList);
	
	public int updateTopicListLabel(TopicList TopicList);
	
	public int deleteTopicList(TopicList TopicList);
	
	public int insertTopicList(TopicList TopicList);
	
	public List<TopicList> findTopicListList(TopicList TopicList);
	
	public void add(TopicList TopicList);
	
	public void updateCountback(String id);
	
	public void updateCountbrowse(String id);
	
	public TopicList findOne(String id);
	
	public List<TopicList> findBabyId(String uid,int pageNo,int pageSize);
	
	public int updateBatchupdateLabel(List<TopicList> topiclistList);
	
}
