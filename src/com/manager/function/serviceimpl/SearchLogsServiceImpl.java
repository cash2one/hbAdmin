package com.manager.function.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.SearchLogsDao;
import com.manager.function.entity.SearchLogs;
import com.manager.function.service.SearchLogsService;

public class SearchLogsServiceImpl implements SearchLogsService {
	
	@Autowired
	private SearchLogsDao searchLogsDao;
	

	public int findSearchLogsCount(SearchLogs SearchLogs) {
		return this.searchLogsDao.findSearchLogsCount(SearchLogs);
	}

	public List<SearchLogs> findSearchLogsList(SearchLogs SearchLogs, int pageNo, int pageSize) {
		return this.searchLogsDao.findSearchLogsList(SearchLogs, pageNo, pageSize);
	}


	/**
	 * @return the SearchLogsDao
	 */
	public SearchLogsDao getSearchLogsDao() {
		return searchLogsDao;
	}

	/**
	 * @param SearchLogsDao the SearchLogsDao to set
	 */
	public void setSearchLogsDao(SearchLogsDao searchLogsDao) {
		this.searchLogsDao = searchLogsDao;
	}
	
	
}
