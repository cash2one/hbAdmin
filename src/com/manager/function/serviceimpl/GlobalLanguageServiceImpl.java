package com.manager.function.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.GlobalLanguageDao;
import com.manager.function.entity.GlobalLanguage;
import com.manager.function.service.GlobalLanguageService;

public class GlobalLanguageServiceImpl implements GlobalLanguageService {
	
	@Autowired
	private GlobalLanguageDao GlobalLanguageDao;

	public int deleteGlobalLanguage(String id) {
		GlobalLanguage GlobalLanguage=new GlobalLanguage();
		GlobalLanguage.setId(id);
		return this.GlobalLanguageDao.deleteGlobalLanguage(GlobalLanguage);
	}

	public int findGlobalLanguageCount(GlobalLanguage GlobalLanguage) {
		return this.GlobalLanguageDao.findGlobalLanguageCount(GlobalLanguage);
	}

	public List<GlobalLanguage> findGlobalLanguageList(GlobalLanguage GlobalLanguage, int pageNo, int pageSize) {
		return this.GlobalLanguageDao.findGlobalLanguageList(GlobalLanguage, pageNo, pageSize);
	}

	public List<GlobalLanguage> findGlobalLanguageList() {
		return this.GlobalLanguageDao.findGlobalLanguageList(null);
	}
	
	public List<GlobalLanguage> NoAbolish_GlobalLanguageList(HttpServletRequest request){
		List<GlobalLanguage> GlobalLanguageList=this.GlobalLanguageDao.NoAbolish_GlobalLanguageList();
		request.setAttribute("noabolish_globallanguage", GlobalLanguageList);
		return GlobalLanguageList;
	}

	public int insertGlobalLanguage(GlobalLanguage GlobalLanguage) {
		return this.GlobalLanguageDao.insertGlobalLanguage(GlobalLanguage);
	}

	public int updateGlobalLanguage(GlobalLanguage GlobalLanguage) {
		return this.GlobalLanguageDao.updateGlobalLanguage(GlobalLanguage);
	}

	public int updateGlobalLanguageStatus(GlobalLanguage GlobalLanguage) {
		return this.GlobalLanguageDao.updateGlobalLanguageStatus(GlobalLanguage);
	}
	
	public GlobalLanguage findGlobalLanguageOne(String id){
		GlobalLanguage GlobalLanguage=new GlobalLanguage();
		GlobalLanguage.setId(id);
		List<GlobalLanguage> GlobalLanguageList=this.GlobalLanguageDao.findGlobalLanguageList(GlobalLanguage);
		if(GlobalLanguageList!=null && GlobalLanguageList.size()>0){
			return GlobalLanguageList.get(0);
		}
		return null;
	}
	
	public int checkGlobalLanguageName(String lan_level_content){
		GlobalLanguage GlobalLanguage=new GlobalLanguage();
		GlobalLanguage.setLan_level_content(lan_level_content);
		List<GlobalLanguage> bewsTypeList=this.GlobalLanguageDao.findGlobalLanguageList(GlobalLanguage);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkGlobalLanguageName(String id,String lan_level_content){
		GlobalLanguage GlobalLanguage=new GlobalLanguage();
		GlobalLanguage.setLan_level_content(lan_level_content);
		List<GlobalLanguage> bewsTypeList=this.GlobalLanguageDao.findGlobalLanguageList(GlobalLanguage);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					GlobalLanguage bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}
	
	
	
	/**
	 * @return the GlobalLanguageDao
	 */
	public GlobalLanguageDao getGlobalLanguageDao() {
		return GlobalLanguageDao;
	}

	/**
	 * @param GlobalLanguageDao the GlobalLanguageDao to set
	 */
	public void setGlobalLanguageDao(GlobalLanguageDao GlobalLanguageDao) {
		this.GlobalLanguageDao = GlobalLanguageDao;
	}

}
