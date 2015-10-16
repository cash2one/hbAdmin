package com.manager.function.daoimpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.ScoreLogDao;
import com.manager.function.entity.ScoreLog;
import com.manager.util.DataSourceContextHolder;

public class ScoreLogDaoImpl extends SqlSessionDaoSupport implements ScoreLogDao {

	public void add(ScoreLog scoreLog) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("ScoreLogSql.add", scoreLog);
		DataSourceContextHolder.clearDbType();
	}

}
