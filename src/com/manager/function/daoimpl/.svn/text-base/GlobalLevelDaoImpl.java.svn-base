package com.manager.function.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalLevelDao;
import com.manager.function.entity.GlobalLevel;
import com.manager.util.DataSourceContextHolder;

public class GlobalLevelDaoImpl extends SqlSessionDaoSupport implements GlobalLevelDao {

	public void add(GlobalLevel gl) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("GlobalLevelSql.add", gl);
		DataSourceContextHolder.clearDbType();
	}

	public int count(GlobalLevel gl) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalLevelSql.count", gl);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void delete(GlobalLevel gl) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("GlobalLevelSql.delete", gl);
		DataSourceContextHolder.clearDbType();
	}

	public List<GlobalLevel> get(GlobalLevel gl, int pageNo, int pageSize) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls = this.getSqlSession().selectList("GlobalLevelSql.get", gl,new RowBounds(pageNo, pageSize));
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalLevel> NoAbolish_GlobalLevelList(){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls = this.getSqlSession().selectList("GlobalLevelSql.getNoAbolish");
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalLevelSql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public GlobalLevel getOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls = this.getSqlSession().selectList("GlobalLevelSql.getOne", id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public void update(GlobalLevel gl) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("GlobalLevelSql.update", gl);
		DataSourceContextHolder.clearDbType();
	}

	public int sortcount(String sort) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalLevelSql.sortcount", sort);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public List<GlobalLevel> get(GlobalLevel gl) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls = this.getSqlSession().selectList("GlobalLevelSql.get1", gl);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalLevel> get_div_peoperty(List<GlobalLevel> globalLevelList){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls = this.getSqlSession().selectList("GlobalLevelSql.get_div_peoperty", globalLevelList);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalLevel> get_div_hobby(List<GlobalLevel> globalLevelList){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls = this.getSqlSession().selectList("GlobalLevelSql.get_div_hobby", globalLevelList);
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalLevel> get_select_peoperty(int ii){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls =new ArrayList<GlobalLevel>();
		if(ii==0) {
			ls = this.getSqlSession().selectList("GlobalLevelSql.get_select_peoperty0");
		}else{
			ls = this.getSqlSession().selectList("GlobalLevelSql.get_select_peoperty1");
		}
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalLevel> get_select_hobby(int ii){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLevel> ls =new ArrayList<GlobalLevel>();
		if(ii==0) {
			ls = this.getSqlSession().selectList("GlobalLevelSql.get_select_hobby0");
		}else{
			ls = this.getSqlSession().selectList("GlobalLevelSql.get_select_hobby1");
		}
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

}
