package com.manager.function.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.manager.function.dao.GlobalHobbyAttrDao;
import com.manager.function.dao.GlobalHobbyDao;
import com.manager.function.entity.GlobalHobby;
import com.manager.function.entity.GlobalHobbyAttr;
import com.manager.function.service.GlobalHobbyService;

public class GlobalHobbyServiceImpl implements GlobalHobbyService {

	private GlobalHobbyDao globalHobbyDao;

	private GlobalHobbyAttrDao globalHobbyAttrDao;

	@Transactional
	public void add(GlobalHobby gh) {
		try {
			int id = this.globalHobbyDao.getId();
			gh.setId(id + "");
			this.globalHobbyDao.add(gh);
			String[] levels = gh.getLevel_id().split(",");
			List<GlobalHobbyAttr> ghaList = new ArrayList<GlobalHobbyAttr>();
			for (int i = 0; i < levels.length; i++) {
				GlobalHobbyAttr gha = new GlobalHobbyAttr();
				gha.setLevel_id(levels[i]);
				gha.setHobby_id(id + "");
				ghaList.add(gha);
			}
			this.globalHobbyAttrDao.insertBatch(ghaList);
		} catch (Exception e) {
			throw new RuntimeException("GlobalHobby添加不成功");
		}
	}

	public int count(GlobalHobby gh) {
		return this.globalHobbyDao.count(gh);
	}

	public GlobalHobbyAttrDao getGlobalHobbyAttrDao() {
		return globalHobbyAttrDao;
	}

	public void setGlobalHobbyAttrDao(GlobalHobbyAttrDao globalHobbyAttrDao) {
		this.globalHobbyAttrDao = globalHobbyAttrDao;
	}

	@Transactional
	public void delete(GlobalHobby gh, String reg) {
		try {
			if (reg.equals("2")) {
				String[] ids = gh.getId().split("-");
				for (int i = 0; i < ids.length; i++) {
					if (!ids[i].equals("on")) {
						GlobalHobby glModel = new GlobalHobby();
						glModel.setId(ids[i]);
						List<GlobalHobbyAttr> ghaList = this.globalHobbyAttrDao
								.get(ids[i]);
						if(ghaList!=null){
							this.globalHobbyAttrDao.deleteBatch(ghaList);
						}
						this.globalHobbyDao.delete(glModel);
					}
				}
			} else if (reg.equals("1")) {
				List<GlobalHobbyAttr> ghaList = this.globalHobbyAttrDao.get(gh
						.getId());
				if(ghaList!=null){
				this.globalHobbyAttrDao.deleteBatch(ghaList);
				}
				this.globalHobbyDao.delete(gh);
			}
		} catch (Exception e) {
			throw new RuntimeException("GlobalHobby删除不成功");
		}
	}

	public List<GlobalHobby> NoAbolish_GlobalHobbyList(
			HttpServletRequest request) {
		List<GlobalHobby> globalHobbyList = this.globalHobbyDao
				.NoAbolish_GlobalHobbyList();
		request.setAttribute("noabolish_globalhobby", globalHobbyList);
		return globalHobbyList;
	}

	public List<GlobalHobby> get(GlobalHobby gh, int pageNo, int pageSize) {
		return this.globalHobbyDao.get(gh, pageNo, pageSize);
	}

	public int getId() {
		return this.globalHobbyDao.getId();
	}

	public GlobalHobby getOne(String id) {
		return this.globalHobbyDao.getOne(id);
	}

	@Transactional
	public void update(GlobalHobby gh, String reg) {
		try {
			// 1：update一个，2：批量update
			if (reg.equals("2")) {
				String[] ids = gh.getId().split("-");
				for (int i = 0; i < ids.length; i++) {
					if (!ids[i].equals("on")) {
						GlobalHobby glModel = new GlobalHobby();
						glModel.setId(ids[i]);
						glModel.setHobby_status(gh.getHobby_status());
						glModel.setUpdate_adminuser(gh.getUpdate_adminuser());
						this.globalHobbyDao.update(glModel);
					}
				}
			} else if (reg.equals("1")) {
				//删除之前的中间表数据
				List<GlobalHobbyAttr> ghaList1 = this.globalHobbyAttrDao.get(gh
						.getId());
				if(ghaList1!=null){
				this.globalHobbyAttrDao.deleteBatch(ghaList1);
				}
				//添加新的中间表数据
				String[] levels = gh.getLevel_id().split(",");
				List<GlobalHobbyAttr> ghaList = new ArrayList<GlobalHobbyAttr>();
				for (int i = 0; i < levels.length; i++) {
					GlobalHobbyAttr gha = new GlobalHobbyAttr();
					gha.setLevel_id(levels[i]);
					gha.setHobby_id(gh.getId());
					ghaList.add(gha);
				}
				this.globalHobbyAttrDao.insertBatch(ghaList);
				
				this.globalHobbyDao.update(gh);
			}
		} catch (Exception e) {
			throw new RuntimeException("GlobalHobby修改不成功");
		}
	}

	public GlobalHobbyDao getGlobalHobbyDao() {
		return globalHobbyDao;
	}

	public void setGlobalHobbyDao(GlobalHobbyDao globalHobbyDao) {
		this.globalHobbyDao = globalHobbyDao;
	}

}
