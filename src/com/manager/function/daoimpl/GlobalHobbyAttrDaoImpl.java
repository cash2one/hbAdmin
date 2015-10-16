package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalHobbyAttrDao;
import com.manager.function.entity.GlobalHobbyAttr;
import com.manager.util.DataSourceContextHolder;

public class GlobalHobbyAttrDaoImpl extends SqlSessionDaoSupport implements GlobalHobbyAttrDao {

	public void deleteBatch(List<GlobalHobbyAttr> ls) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("GlobalHobbyAttrSql.batch_delete", ls);
		DataSourceContextHolder.clearDbType();
	}

	public List<GlobalHobbyAttr> get(String hobby_id) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalHobbyAttr> ls = this.getSqlSession().selectList("GlobalHobbyAttrSql.get", hobby_id);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public void insertBatch(List<GlobalHobbyAttr> ls) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("GlobalHobbyAttrSql.batch_insert", ls);
		DataSourceContextHolder.clearDbType();
	}

}
