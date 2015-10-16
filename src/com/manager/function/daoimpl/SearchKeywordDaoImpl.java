package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.SearchKeywordDao;
import com.manager.function.entity.SearchKeyword;
import com.manager.util.DataSourceContextHolder;

public class SearchKeywordDaoImpl extends SqlSessionDaoSupport implements SearchKeywordDao {

	public List<SearchKeyword> findKeyword() {
		DataSourceContextHolder.setDbType("0");
		List<SearchKeyword> ls = this.getSqlSession().selectList("SearchKeywordSql.findKeyword");
		if(ls!=null && ls.size()>0){
			return ls;
		}
		return null;
	}

	public List<SearchKeyword> findSearchKeywordList(SearchKeyword SearchKeyword,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<SearchKeyword> list=this.getSqlSession().selectList("SearchKeywordSql.get", SearchKeyword,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<SearchKeyword> findSearchKeywordList(SearchKeyword SearchKeyword) {
		DataSourceContextHolder.setDbType("0");
		List<SearchKeyword> list=this.getSqlSession().selectList("SearchKeywordSql.get", SearchKeyword);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findSearchKeywordCount(SearchKeyword SearchKeyword) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("SearchKeywordSql.count", SearchKeyword);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertSearchKeyword(SearchKeyword SearchKeyword) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("SearchKeywordSql.insert", SearchKeyword);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteSearchKeyword(SearchKeyword SearchKeyword) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("SearchKeywordSql.delete", SearchKeyword);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateSearchKeyword(SearchKeyword SearchKeyword) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("SearchKeywordSql.update", SearchKeyword);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateSearchKeywordStatus(SearchKeyword SearchKeyword) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("SearchKeywordSql.updatestatus" , SearchKeyword);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
