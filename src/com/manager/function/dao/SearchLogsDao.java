package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.SearchLogs;

public interface SearchLogsDao {
	
	public void add(SearchLogs sl);

	public List<SearchLogs> findSearchLogsList(SearchLogs SearchLogs,int pageNo,int pageSize);
	
	public int findSearchLogsCount(SearchLogs SearchLogs);
}
