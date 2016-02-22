package com.manager.function.daoimpl;

//import java.util.List;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.MedalDao;
import com.manager.function.entity.Medal;
import com.manager.util.DataSourceContextHolder;

public class MedalDaoImpl extends SqlSessionDaoSupport implements MedalDao{
	
	public void add(Medal medal)
	{
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("MedalSql.add", medal);
		DataSourceContextHolder.clearDbType();
	}
	
	public int getMedal(Medal medal)
	{
		DataSourceContextHolder.setDbType("0");
		List<Medal> ls = this.getSqlSession().selectList("MedalSql.find", medal);
		DataSourceContextHolder.clearDbType();
		
		return ls.size();
	}
	
	public int getResCount(Medal medal)
	{
		DataSourceContextHolder.setDbType("0");
		List<Medal> ls = this.getSqlSession().selectList("MedalSql.getResReadCount", medal);
		DataSourceContextHolder.clearDbType();
		
		return ls.size();
	}
}
