package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.TopicListDao;
import com.manager.function.entity.TopicList;
import com.manager.util.DataSourceContextHolder;

public class TopicListDaoImpl extends SqlSessionDaoSupport implements TopicListDao {
	
	public List<TopicList> findTopicListList(TopicList TopicList,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<TopicList> list=this.getSqlSession().selectList("TopicListSql.get", TopicList,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<TopicList> findTopicListList(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		List<TopicList> list=this.getSqlSession().selectList("TopicListSql.get", TopicList);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findTopicListCount(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("TopicListSql.count", TopicList);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertTopicList(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("TopicListSql.add", TopicList);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteTopicList(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("TopicListSql.delete", TopicList);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateTopicList(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicListSql.update", TopicList);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateTopicListStatus(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicListSql.updatestatus" , TopicList);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int updateTopicListLabel(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicListSql.updatelabel" , TopicList);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void add(TopicList TopicList) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("TopicListSql.add" , TopicList);
		DataSourceContextHolder.clearDbType();
	}

	public List<TopicList> getByType(TopicList TopicList, int pageNo,
			int pageSize) {
		DataSourceContextHolder.setDbType("0");
		List<TopicList> list=this.getSqlSession().selectList("TopicListSql.getByType", TopicList,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public void updateCountback(String id) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicListSql.updateCountback" , id);
		DataSourceContextHolder.clearDbType();
	}

	public void updateCountbrowse(String id) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicListSql.updateCountbrowse" , id);
		DataSourceContextHolder.clearDbType();
	}

	public TopicList findOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<TopicList> list=this.getSqlSession().selectList("TopicListSql.findOne", id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<TopicList> findBabyId(String uid,int pageNo,int pageSize) {
		DataSourceContextHolder.setDbType("0");
		List<TopicList> list=this.getSqlSession().selectList("TopicListSql.findBabyId", uid,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	
	public int updateBatchupdateLabel(List<TopicList> topiclistList){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicListSql.batchupdate_label" , topiclistList);
		DataSourceContextHolder.clearDbType();
		return in;
	}
}
