package com.manager.function.daoimpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.FavoriteDao;
import com.manager.function.entity.Favorite;
import com.manager.util.DataSourceContextHolder;

public class FavoriteDaoImpl extends SqlSessionDaoSupport implements FavoriteDao {

	public void add(Favorite f) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("FavoriteSql.add", f);
		DataSourceContextHolder.clearDbType();
	}

	public void delete(String user_id) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("FavoriteSql.delete", user_id);
		DataSourceContextHolder.clearDbType();
	}

	public int findNum(Favorite f) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("FavoriteSql.findNum",f);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void delete1(Favorite f) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().delete("FavoriteSql.delete1", f);
		DataSourceContextHolder.clearDbType();
	}

}
