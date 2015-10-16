package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.SearchLogsDao;
import com.manager.function.entity.SearchLogs;
import com.manager.util.DataSourceContextHolder;

public class SearchLogsDaoImpl extends SqlSessionDaoSupport implements SearchLogsDao {

	public void add(SearchLogs sl) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("SearchLogsSql.add", sl);
		DataSourceContextHolder.clearDbType();
	}
	
	public List<SearchLogs> findSearchLogsList(SearchLogs SearchLogs,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<SearchLogs> list=this.getSqlSession().selectList("SearchLogsSql.get", SearchLogs,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findSearchLogsCount(SearchLogs SearchLogs) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("SearchLogsSql.count", SearchLogs);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
