package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.TopicTypeDao;
import com.manager.function.entity.TopicType;
import com.manager.util.DataSourceContextHolder;

public class TopicTypeDaoImpl extends SqlSessionDaoSupport implements TopicTypeDao {
	
	public List<TopicType> findTopicTypeList(TopicType TopicType,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<TopicType> list=this.getSqlSession().selectList("TopicTypeSql.get", TopicType,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<TopicType> findTopicTypeList(TopicType TopicType) {
		DataSourceContextHolder.setDbType("0");
		List<TopicType> list=this.getSqlSession().selectList("TopicTypeSql.get2", TopicType);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<TopicType> NoAbolish_TopicTypeList(){
		DataSourceContextHolder.setDbType("0");
		List<TopicType> list=this.getSqlSession().selectList("TopicTypeSql.getNoAbolish");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	
	public int findTopicTypeCount(TopicType TopicType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("TopicTypeSql.count", TopicType);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertTopicType(TopicType TopicType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("TopicTypeSql.insert", TopicType);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteTopicType(TopicType TopicType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("TopicTypeSql.delete", TopicType);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateTopicType(TopicType TopicType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicTypeSql.update", TopicType);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateTopicTypeStatus(TopicType TopicType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("TopicTypeSql.updatestatus" , TopicType);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public List<TopicType> getTopicType() {
		DataSourceContextHolder.setDbType("0");
		List<TopicType> list=this.getSqlSession().selectList("TopicTypeSql.getTopicType");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
