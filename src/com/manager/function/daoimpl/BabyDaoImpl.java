package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.BabyDao;
import com.manager.function.entity.Baby;
import com.manager.util.DataSourceContextHolder;

public class BabyDaoImpl  extends SqlSessionDaoSupport implements BabyDao {

	public void add(Baby baby) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("BabySql.add", baby);
		DataSourceContextHolder.clearDbType();
	}

	public List<Baby> findByUserId(String user_id) {
		DataSourceContextHolder.setDbType("0");
		List<Baby> ls = this.getSqlSession().selectList("BabySql.getByUserId", user_id);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public Baby findOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<Baby> ls = this.getSqlSession().selectList("BabySql.findOne", id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("BabySql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void update(Baby baby) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("BabySql.update", baby);
		DataSourceContextHolder.clearDbType();
	}
	public int getRank(String id)
	{
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("BabySql.getRank",id);
		DataSourceContextHolder.clearDbType();
		return in;
	}
}
