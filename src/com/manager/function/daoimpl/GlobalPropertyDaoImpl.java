package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalPropertyDao;
import com.manager.function.entity.GlobalProperty;
import com.manager.util.DataSourceContextHolder;

public class GlobalPropertyDaoImpl extends SqlSessionDaoSupport implements GlobalPropertyDao {

	public void add(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("GlobalPropertySql.add", gp);
		DataSourceContextHolder.clearDbType();
	}

	public int count(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalPropertySql.count", gp);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void delete(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("GlobalPropertySql.delete", gp);
		DataSourceContextHolder.clearDbType();
	}

	public List<GlobalProperty> get(GlobalProperty gp, int pageNo, int pageSize) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.get", gp,new RowBounds(pageNo, pageSize));
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalProperty> NoAbolish_GlobalPropertyList(){
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.getNoAbolish");
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalPropertySql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public GlobalProperty getOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.getOne",id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public void update(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("GlobalPropertySql.update", gp);
		DataSourceContextHolder.clearDbType();
	}

	public int sortcount(String sort) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalPropertySql.sortcount",sort);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public List<GlobalProperty> get() {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.get1");
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public List<GlobalProperty> findByBabyId(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.findByBabyId",gp);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalProperty> findByLevel(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.findByLevel",gp);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public List<GlobalProperty> findByBabyId_buquan(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.findByBabyId_buquan",gp);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public List<GlobalProperty> shudan(GlobalProperty gp) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalProperty> ls = this.getSqlSession().selectList("GlobalPropertySql.shudan",gp);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

}
