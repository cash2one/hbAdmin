package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.GlobalLanguage;


public interface GlobalLanguageDao {

	public List<GlobalLanguage> findGlobalLanguageList(GlobalLanguage GlobalLanguage,int pageNo,int pageSize);
	
	public int findGlobalLanguageCount(GlobalLanguage GlobalLanguage);
	
	public int updateGlobalLanguage(GlobalLanguage GlobalLanguage);
	
	public int updateGlobalLanguageStatus(GlobalLanguage GlobalLanguage);
	
	public int deleteGlobalLanguage(GlobalLanguage GlobalLanguage);
	
	public int insertGlobalLanguage(GlobalLanguage GlobalLanguage);
	
	public List<GlobalLanguage> findGlobalLanguageList(GlobalLanguage GlobalLanguage);
	
	public List<GlobalLanguage> NoAbolish_GlobalLanguageList();
	
}
