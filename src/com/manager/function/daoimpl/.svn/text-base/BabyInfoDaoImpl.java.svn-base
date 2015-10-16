package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.BabyInfoDao;
import com.manager.function.entity.Baby;
import com.manager.function.entity.BabyInfo;
import com.manager.util.DataSourceContextHolder;

public class BabyInfoDaoImpl extends SqlSessionDaoSupport implements BabyInfoDao {

	public void add(BabyInfo babyInfo) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("BabyInfoSql.add", babyInfo);
		DataSourceContextHolder.clearDbType();
	}

	public List<BabyInfo> findByBabyId(String baby_id) {
		DataSourceContextHolder.setDbType("0");
		List<BabyInfo> ls = this.getSqlSession().selectList("BabyInfoSql.findByBabyId", baby_id);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public BabyInfo findOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<BabyInfo> ls = this.getSqlSession().selectList("BabyInfoSql.getOne", id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

}
