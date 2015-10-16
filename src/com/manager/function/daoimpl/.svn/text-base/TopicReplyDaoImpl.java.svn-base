package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.TopicReplyDao;
import com.manager.function.entity.TopicReply;
import com.manager.util.DataSourceContextHolder;

public class TopicReplyDaoImpl extends SqlSessionDaoSupport implements TopicReplyDao {
	
	public List<TopicReply> findTopicReplyList(TopicReply TopicReply,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<TopicReply> list=this.getSqlSession().selectList("TopicReplySql.get", TopicReply,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<TopicReply> findTopicReplyList1(TopicReply TopicReply,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<TopicReply> list=this.getSqlSession().selectList("TopicReplySql.get1", TopicReply,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<TopicReply> findTopicReplyList(TopicReply TopicReply) {
		DataSourceContextHolder.setDbType("0");
		List<TopicReply> list=this.getSqlSession().selectList("TopicReplySql.get", TopicReply);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findTopicReplyCount(TopicReply TopicReply) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("TopicReplySql.count", TopicReply);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertTopicReply(TopicReply TopicReply) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("TopicReplySql.insert", TopicReply);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteTopicReply(TopicReply TopicReply,int num) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("TopicReplySql.delete"+num, TopicReply);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateTopicReply(TopicReply TopicReply) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicReplySql.update", TopicReply);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateTopicReplyStatus(TopicReply TopicReply) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicReplySql.updatestatus" , TopicReply);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void add(TopicReply TopicReply) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("TopicReplySql.add", TopicReply);
		DataSourceContextHolder.clearDbType();
	}

	public int getlouceng(String topic_id) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("TopicReplySql.getlouceng", topic_id);
		DataSourceContextHolder.clearDbType();
		return in;
	}
}
