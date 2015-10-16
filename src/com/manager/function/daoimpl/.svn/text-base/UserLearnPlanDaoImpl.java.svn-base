package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.UserLearnPlanDao;
import com.manager.function.entity.UserLearnplan;
import com.manager.util.DataSourceContextHolder;

public class UserLearnPlanDaoImpl extends SqlSessionDaoSupport implements UserLearnPlanDao {

	public List<UserLearnplan> get_statistics_date(){
		DataSourceContextHolder.setDbType("0");
		List<UserLearnplan> list=this.getSqlSession().selectList("UserLearnplanSql.get_statistics_date");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<UserLearnplan> get_statistics(String num,UserLearnplan UserLearnplan,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<UserLearnplan> list=this.getSqlSession().selectList("UserLearnplanSql.get_statistics_"+num, UserLearnplan,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int get_statistics_count(String num,UserLearnplan UserLearnplan){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserLearnplanSql.get_statistics_"+num+"_count",UserLearnplan);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public List<UserLearnplan> get_statistics(String num,UserLearnplan UserLearnplan){
		DataSourceContextHolder.setDbType("0");
		List<UserLearnplan> list=this.getSqlSession().selectList("UserLearnplanSql.get_statistics_"+num,UserLearnplan);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<UserLearnplan> findByplanId(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		List<UserLearnplan> list=this.getSqlSession().selectList("UserLearnplanSql.get", ulp);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public int getNotFinushNum(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserLearnplanSql.getNotFinushNum", ulp);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int getPlanId(String baby_id) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserLearnplanSql.getPlanId", baby_id);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void add(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("UserLearnplanSql.add", ulp);
		DataSourceContextHolder.clearDbType();
	}

	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserLearnplanSql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void update(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("UserLearnplanSql.update", ulp);
		DataSourceContextHolder.clearDbType();
	}

	public UserLearnplan findOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<UserLearnplan> list=this.getSqlSession().selectList("UserLearnplanSql.findOne", id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void delete(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("UserLearnplanSql.delete",ulp);
		DataSourceContextHolder.clearDbType();
	}

	public int isFinush(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserLearnplanSql.isFinush",ulp);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void add1(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("UserLearnplanSql.add1", ulp);
		DataSourceContextHolder.clearDbType();
	}
	
	public void add2(UserLearnplan ulp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("UserLearnplanSql.add2", ulp);
		DataSourceContextHolder.clearDbType();
	}

}
