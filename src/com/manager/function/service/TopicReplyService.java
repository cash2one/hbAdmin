package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.TopicReply;

@Service
public interface TopicReplyService {

	public List<TopicReply> findTopicReplyList(TopicReply TopicReply,int pageNo,int pageSize);
	
	public int findTopicReplyCount(TopicReply TopicReply);
	
	public int updateTopicReply(TopicReply TopicReply);
	
	public int updateTopicReplyStatus(TopicReply TopicReply);
	
	public int deleteTopicReply(String id);
	
	public int insertTopicReply(TopicReply TopicReply);
	
	public List<TopicReply> findTopicReplyList();
	
	public TopicReply findTopicReplyOne(String id);
	
	public TopicReply findTopicReplyOne(TopicReply TopicReply);
	
	public Map findTopicReplyList(HttpServletRequest request);
	
	public Map addReplyList(HttpServletRequest request);
	
}
