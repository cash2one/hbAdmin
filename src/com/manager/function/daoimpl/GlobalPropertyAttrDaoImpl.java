package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalPropertyAttrDao;
import com.manager.function.entity.GlobalPropertyAttr;
import com.manager.util.DataSourceContextHolder;

public class GlobalPropertyAttrDaoImpl extends SqlSessionDaoSupport implements GlobalPropertyAttrDao {

	public void deleteBatch(List<GlobalPropertyAttr> ls) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("GlobalPropertyAttrSql.batch_delete", ls);
		DataSourceContextHolder.clearDbType();
	}

	public List<GlobalPropertyAttr> get(String property_id) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalPropertyAttr> ls = this.getSqlSession().selectList("GlobalPropertyAttrSql.get", property_id);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public void insertBatch(List<GlobalPropertyAttr> ls) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("GlobalPropertyAttrSql.batch_insert", ls);
		DataSourceContextHolder.clearDbType();
	}

}
