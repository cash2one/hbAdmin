package com.manager.function.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.SearchKeywordDao;
import com.manager.function.entity.SearchKeyword;
import com.manager.function.service.SearchKeywordService;

public class SearchKeywordServiceImpl implements SearchKeywordService {
	
	@Autowired
	private SearchKeywordDao searchKeywordDao;
	

	public int deleteSearchKeyword(String id) {
		SearchKeyword SearchKeyword=new SearchKeyword();
		SearchKeyword.setId(id);
		return this.searchKeywordDao.deleteSearchKeyword(SearchKeyword);
	}

	public int findSearchKeywordCount(SearchKeyword SearchKeyword) {
		return this.searchKeywordDao.findSearchKeywordCount(SearchKeyword);
	}

	public List<SearchKeyword> findSearchKeywordList(SearchKeyword SearchKeyword, int pageNo, int pageSize) {
		return this.searchKeywordDao.findSearchKeywordList(SearchKeyword, pageNo, pageSize);
	}

	public List<SearchKeyword> findSearchKeywordList() {
		return this.searchKeywordDao.findSearchKeywordList(null);
	}
	
	public int insertSearchKeyword(SearchKeyword SearchKeyword) {
		return this.searchKeywordDao.insertSearchKeyword(SearchKeyword);
	}

	public int updateSearchKeyword(SearchKeyword SearchKeyword) {
		return this.searchKeywordDao.updateSearchKeyword(SearchKeyword);
	}

	public int updateSearchKeywordStatus(SearchKeyword SearchKeyword) {
		return this.searchKeywordDao.updateSearchKeywordStatus(SearchKeyword);
	}
	
	public SearchKeyword findSearchKeywordOne(String id){
		SearchKeyword SearchKeyword=new SearchKeyword();
		SearchKeyword.setId(id);
		List<SearchKeyword> SearchKeywordList=this.searchKeywordDao.findSearchKeywordList(SearchKeyword);
		if(SearchKeywordList!=null && SearchKeywordList.size()>0){
			return SearchKeywordList.get(0);
		}
		return null;
	}
	
	public int checkSearchKeywordKeywordName(String keyword_name){
		SearchKeyword SearchKeyword=new SearchKeyword();
		SearchKeyword.setKeyword_name(keyword_name);
		List<SearchKeyword> bewsTypeList=this.searchKeywordDao.findSearchKeywordList(SearchKeyword);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkSearchKeywordKeywordName(String id,String keyword_name){
		SearchKeyword SearchKeyword=new SearchKeyword();
		SearchKeyword.setKeyword_name(keyword_name);
		List<SearchKeyword> bewsTypeList=this.searchKeywordDao.findSearchKeywordList(SearchKeyword);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					SearchKeyword bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}
	
	public int checkSearchKeywordSort(String sort) {
		SearchKeyword SearchKeyword=new SearchKeyword();
		SearchKeyword.setSort(sort);
		List<SearchKeyword> bewsTypeList=this.searchKeywordDao.findSearchKeywordList(SearchKeyword);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}

	public int checkSearchKeywordSort(String id, String sort) {
		SearchKeyword SearchKeyword=new SearchKeyword();
		SearchKeyword.setSort(sort);
		List<SearchKeyword> bewsTypeList=this.searchKeywordDao.findSearchKeywordList(SearchKeyword);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					SearchKeyword bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}
	


	/**
	 * @return the SearchKeywordDao
	 */
	public SearchKeywordDao getSearchKeywordDao() {
		return searchKeywordDao;
	}

	/**
	 * @param SearchKeywordDao the SearchKeywordDao to set
	 */
	public void setSearchKeywordDao(SearchKeywordDao searchKeywordDao) {
		this.searchKeywordDao = searchKeywordDao;
	}
	
	
}
