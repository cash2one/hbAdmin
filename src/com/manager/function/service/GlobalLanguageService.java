package com.manager.function.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.GlobalLanguage;

@Service
public interface GlobalLanguageService {

	public List<GlobalLanguage> findGlobalLanguageList(GlobalLanguage GlobalLanguage,int pageNo,int pageSize);
	
	public int findGlobalLanguageCount(GlobalLanguage GlobalLanguage);
	
	public int updateGlobalLanguage(GlobalLanguage GlobalLanguage);
	
	public int updateGlobalLanguageStatus(GlobalLanguage GlobalLanguage);
	
	public int deleteGlobalLanguage(String id);
	
	public int insertGlobalLanguage(GlobalLanguage GlobalLanguage);
	
	public List<GlobalLanguage> findGlobalLanguageList();
	
	public GlobalLanguage findGlobalLanguageOne(String id);
	
	public int checkGlobalLanguageName(String lan_level_content);
	
	public int checkGlobalLanguageName(String id,String lan_level_content);
	
	public List<GlobalLanguage> NoAbolish_GlobalLanguageList(HttpServletRequest request);
	
}
