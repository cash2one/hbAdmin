package com.manager.function.serviceimpl;

import java.util.List;

import com.manager.function.dao.GlobalHobbyAttrDao;
import com.manager.function.entity.GlobalHobbyAttr;
import com.manager.function.service.GlobalHobbyAttrService;

public class GlobalHobbyAttrServiceImpl implements GlobalHobbyAttrService {
	
	private GlobalHobbyAttrDao globalHobbyAttrDao;

	public GlobalHobbyAttrDao getGlobalHobbyAttrDao() {
		return globalHobbyAttrDao;
	}

	public void setGlobalHobbyAttrDao(GlobalHobbyAttrDao globalHobbyAttrDao) {
		this.globalHobbyAttrDao = globalHobbyAttrDao;
	}

	public void deleteBatch(List<GlobalHobbyAttr> ls) {
		this.globalHobbyAttrDao.deleteBatch(ls);
	}

	public List<GlobalHobbyAttr> get(String hobby_id) {
		return this.globalHobbyAttrDao.get(hobby_id);
	}

	public void insertBatch(List<GlobalHobbyAttr> ls) {
		this.globalHobbyAttrDao.insertBatch(ls);
	}

}
