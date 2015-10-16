package com.manager.function.serviceimpl;

import java.util.List;

import com.manager.function.dao.GlobalPropertyAttrDao;
import com.manager.function.entity.GlobalPropertyAttr;
import com.manager.function.service.GlobalPropertyAttrService;

public class GlobalPropertyAttrServiceImpl implements GlobalPropertyAttrService {

	private GlobalPropertyAttrDao globalPropertyAttrDao;
	
	public GlobalPropertyAttrDao getGlobalPropertyAttrDao() {
		return globalPropertyAttrDao;
	}

	public void setGlobalPropertyAttrDao(GlobalPropertyAttrDao globalPropertyAttrDao) {
		this.globalPropertyAttrDao = globalPropertyAttrDao;
	}

	public void deleteBatch(List<GlobalPropertyAttr> ls) {
		this.globalPropertyAttrDao.deleteBatch(ls);
	}

	public List<GlobalPropertyAttr> get(String property_id) {
		return this.globalPropertyAttrDao.get(property_id);
	}

	public void insertBatch(List<GlobalPropertyAttr> ls) {
		this.globalPropertyAttrDao.insertBatch(ls);
	}

}
