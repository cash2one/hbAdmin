package com.manager.function.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.manager.function.dao.GlobalLevelDao;
import com.manager.function.entity.GlobalLevel;
import com.manager.function.service.GlobalLevelService;

public class GlobalLevelServiceImpl implements GlobalLevelService {
	
	private GlobalLevelDao globalLevelDao;

	public void add(GlobalLevel gl) {
		this.globalLevelDao.add(gl);
	}

	public int count(GlobalLevel gl) {
		return this.globalLevelDao.count(gl);
	}

	public void delete(GlobalLevel gl,String reg) {
		if(reg.equals("2")){
			String[] ids = gl.getId().split("-");
			for(int i = 0; i <ids.length;i++){
				if(!ids[i].equals("on")){
					GlobalLevel glModel = new GlobalLevel();
					glModel.setId(ids[i]);
					this.globalLevelDao.delete(glModel);
				}
			}
			}else if(reg.equals("1")){
				this.globalLevelDao.delete(gl);
			}
	}

	public List<GlobalLevel> get(GlobalLevel gl, int pageNo, int pageSize) {
		return this.globalLevelDao.get(gl, pageNo, pageSize);
	}
	
	public List<GlobalLevel> NoAbolish_GlobalLevelList(HttpServletRequest request){
		List<GlobalLevel> globalLevelList=this.globalLevelDao.NoAbolish_GlobalLevelList();
		request.setAttribute("noabolish_globallevel", globalLevelList);
		return globalLevelList;
	}
	
	public void get_select(HttpServletRequest request){
		List<GlobalLevel> gListall=this.globalLevelDao.get_select_hobby(0);
		List<GlobalLevel> gList2all=this.globalLevelDao.get_select_peoperty(0);
		List<GlobalLevel> gList=this.globalLevelDao.get_select_hobby(1);
		List<GlobalLevel> gList2=this.globalLevelDao.get_select_peoperty(1);
		request.setAttribute("select_globalhobby_all", gListall);
		request.setAttribute("select_globalproperty_all", gList2all);
		request.setAttribute("select_globalhobby", gList);
		request.setAttribute("select_globalproperty", gList2);
	}
	
	public List<GlobalLevel> get_select_all_hobby(){
		return this.globalLevelDao.get_select_hobby(0);
	}
	
	public List<GlobalLevel> get_select_all_peoperty(){
		return this.globalLevelDao.get_select_peoperty(0);
	}
	
	public List<GlobalLevel> get_div_peoperty(List<GlobalLevel> globalLevelList){
		return this.globalLevelDao.get_div_peoperty(globalLevelList);
	}
	
	public List<GlobalLevel> get_div_hobby(List<GlobalLevel> globalLevelList){
		return this.globalLevelDao.get_div_hobby(globalLevelList);
	}


	public int getId() {
		return this.globalLevelDao.getId();
	}

	public GlobalLevel getOne(String id) {
		return this.globalLevelDao.getOne(id);
	}

	public void update(GlobalLevel gl,String reg) {
		//1：update一个，2：批量update
		if(reg.equals("2")){
			String[] ids = gl.getId().split("-");
			for(int i = 0; i <ids.length;i++){
				if(!ids[i].equals("on")){
					GlobalLevel glModel = new GlobalLevel();
					glModel.setId(ids[i]);
					glModel.setLevel_status(gl.getLevel_status());
					glModel.setUpdate_adminuser(gl.getUpdate_adminuser());
					this.globalLevelDao.update(glModel);
				}
			}
			}else if(reg.equals("1")){
				this.globalLevelDao.update(gl);
			}
	}

	public GlobalLevelDao getGlobalLevelDao() {
		return globalLevelDao;
	}

	public void setGlobalLevelDao(GlobalLevelDao globalLevelDao) {
		this.globalLevelDao = globalLevelDao;
	}

	public int sortcount(String sort) {
		return this.globalLevelDao.sortcount(sort);
	}

	public List<GlobalLevel> get(GlobalLevel gl) {
		return this.globalLevelDao.get(gl);
	}

}
