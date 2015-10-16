package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.LearnplanDao;
import com.manager.function.entity.Learnplan;
import com.manager.util.DataSourceContextHolder;

public class LearnplanDaoImpl extends SqlSessionDaoSupport implements LearnplanDao {

	public Learnplan findOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<Learnplan> list=this.getSqlSession().selectList("LearnplanSql.findOne", id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Learnplan> findLearnplanList(Learnplan Learnplan,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<Learnplan> list=this.getSqlSession().selectList("LearnplanSql.get", Learnplan,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	
	public List<Learnplan> findLearnplanList(Learnplan Learnplan) {
		DataSourceContextHolder.setDbType("0");
		List<Learnplan> list=this.getSqlSession().selectList("LearnplanSql.get2", Learnplan);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Learnplan> SelectLearnplanList(){
		DataSourceContextHolder.setDbType("0");
		List<Learnplan> list=this.getSqlSession().selectList("LearnplanSql.get_select");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public Learnplan findLearnplan(Learnplan Learnplan){
		DataSourceContextHolder.setDbType("0");
		Learnplan w=this.getSqlSession().selectOne("LearnplanSql.get2", Learnplan);
		DataSourceContextHolder.clearDbType();
		return w;
	}
	
	public int findLearnplanCount(Learnplan Learnplan) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("LearnplanSql.count", Learnplan);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertLearnplan(Learnplan Learnplan) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("LearnplanSql.insert", Learnplan);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int updateLearnplan(Learnplan Learnplan) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("LearnplanSql.update", Learnplan);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteLearnplan(Learnplan Learnplan) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("LearnplanSql.delete", Learnplan);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
