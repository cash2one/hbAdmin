package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalScoreDao;
import com.manager.function.entity.GlobalScore;
import com.manager.util.DataSourceContextHolder;

public class GlobalScoreDaoImpl extends SqlSessionDaoSupport implements GlobalScoreDao {

	public GlobalScore findOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalScore> ls = this.getSqlSession().selectList("GlobalScoreSql.findOne",id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

}
