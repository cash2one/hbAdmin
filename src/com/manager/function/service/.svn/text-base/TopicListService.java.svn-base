package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.manager.function.entity.TopicList;

@Service
public interface TopicListService {

	public List<TopicList> findTopicListList(TopicList TopicList,int pageNo,int pageSize);
	
	public int findTopicListCount(TopicList TopicList);
	
	public int updateTopicList(TopicList TopicList);
	
	public int updateTopicListStatus(TopicList TopicList);
	
	public int updateTopicListLabel(TopicList TopicList);
	
	public int deleteTopicList(String id);
	
	public int insertTopicList(TopicList TopicList);
	
	public List<TopicList> findTopicListList();
	
	public TopicList findTopicListOne(String id);
	
	public TopicList findTopicListOne(TopicList TopicList);
	
	public Map add(HttpServletRequest request);
	
	public Map findTopicListListByType(HttpServletRequest request,HttpServletResponse response);
	
	public Map findByBabyId(HttpServletRequest request);
	
	public int updateBatchupdateLabel(List<TopicList> topiclistList);
	
}
