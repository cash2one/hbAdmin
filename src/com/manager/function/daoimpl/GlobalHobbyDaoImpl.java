package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalHobbyDao;
import com.manager.function.entity.GlobalHobby;
import com.manager.util.DataSourceContextHolder;

public class GlobalHobbyDaoImpl extends SqlSessionDaoSupport implements GlobalHobbyDao {

	public void add(GlobalHobby gh) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("GlobalHobbySql.add", gh);
		DataSourceContextHolder.clearDbType();

	}

	public int count(GlobalHobby gh) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalHobbySql.count", gh);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void delete(GlobalHobby gh) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("GlobalHobbySql.delete", gh);
		DataSourceContextHolder.clearDbType();
	}

	public List<GlobalHobby> get(GlobalHobby gh, int pageNo, int pageSize) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalHobby> ls = this.getSqlSession().selectList("GlobalHobbySql.get", gh,new RowBounds(pageNo, pageSize));
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<GlobalHobby> NoAbolish_GlobalHobbyList(){
		DataSourceContextHolder.setDbType("0");
		List<GlobalHobby> ls = this.getSqlSession().selectList("GlobalHobbySql.getNoAbolish");
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalHobbySql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public GlobalHobby getOne(String id) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalHobby> ls = this.getSqlSession().selectList("GlobalHobbySql.getOne", id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public void update(GlobalHobby gh) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("GlobalHobbySql.update", gh);
		DataSourceContextHolder.clearDbType();
	}

}
