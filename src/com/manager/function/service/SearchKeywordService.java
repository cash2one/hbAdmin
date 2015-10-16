package com.manager.function.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.manager.function.entity.SearchKeyword;

@Service
public interface SearchKeywordService {

	public List<SearchKeyword> findSearchKeywordList(SearchKeyword SearchKeyword,int pageNo,int pageSize);
	
	public int findSearchKeywordCount(SearchKeyword SearchKeyword);
	
	public int updateSearchKeyword(SearchKeyword SearchKeyword);
	
	public int updateSearchKeywordStatus(SearchKeyword SearchKeyword);
	
	public int deleteSearchKeyword(String id);
	
	public int insertSearchKeyword(SearchKeyword SearchKeyword);
	
	public List<SearchKeyword> findSearchKeywordList();
	
	public SearchKeyword findSearchKeywordOne(String id);
	
	public int checkSearchKeywordKeywordName(String keyword_name);
	
	public int checkSearchKeywordKeywordName(String id,String keyword_name);
	
	public int checkSearchKeywordSort(String sort);
	
	public int checkSearchKeywordSort(String id,String sort);
}
