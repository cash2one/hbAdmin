package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.TopicReply;


public interface TopicReplyDao {

	public List<TopicReply> findTopicReplyList(TopicReply TopicReply,int pageNo,int pageSize);
	
	public int findTopicReplyCount(TopicReply TopicReply);
	
	public int updateTopicReply(TopicReply TopicReply);
	
	public int updateTopicReplyStatus(TopicReply TopicReply);
	
	public int deleteTopicReply(TopicReply TopicReply,int num);
	
	public int insertTopicReply(TopicReply TopicReply);
	
	public List<TopicReply> findTopicReplyList(TopicReply TopicReply);
	
	public void add(TopicReply TopicReply);
	
	public int getlouceng(String topic_id);
	
	public List<TopicReply> findTopicReplyList1(TopicReply TopicReply,int pageNo,int pageSize);
	
}
