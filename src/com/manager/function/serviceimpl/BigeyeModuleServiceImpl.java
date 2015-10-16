package com.manager.function.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.BigeyeModuleDao;
import com.manager.function.entity.BigeyeModule;
import com.manager.function.service.BigeyeModuleService;

public class BigeyeModuleServiceImpl implements BigeyeModuleService {
	
	@Autowired
	private BigeyeModuleDao BigeyeModuleDao;

	public int deleteBigeyeModule(String id) {
		BigeyeModule BigeyeModule=new BigeyeModule();
		BigeyeModule.setModule_id(id);
		return this.BigeyeModuleDao.deleteBigeyeModule(BigeyeModule);
	}

	public int findBigeyeModuleCount(BigeyeModule BigeyeModule) {
		return this.BigeyeModuleDao.findBigeyeModuleCount(BigeyeModule);
	}

	public List<BigeyeModule> findBigeyeModuleList(BigeyeModule BigeyeModule, int pageNo, int pageSize) {
		return this.BigeyeModuleDao.findBigeyeModuleList(BigeyeModule, pageNo, pageSize);
	}

	public List<BigeyeModule> findBigeyeModuleList() {
		return this.BigeyeModuleDao.findBigeyeModuleList(null);
	}
	
	public List<BigeyeModule> NoAbolish_BigeyeModuleList(HttpServletRequest request){
		List<BigeyeModule> BigeyeModuleList=this.BigeyeModuleDao.NoAbolish_BigeyeModuleList();
		request.setAttribute("noabolish_bigeyemodule", BigeyeModuleList);
		return BigeyeModuleList;
	}

	public int insertBigeyeModule(BigeyeModule BigeyeModule) {
		return this.BigeyeModuleDao.insertBigeyeModule(BigeyeModule);
	}

	public int updateBigeyeModule(BigeyeModule BigeyeModule) {
		return this.BigeyeModuleDao.updateBigeyeModule(BigeyeModule);
	}

	public int updateBigeyeModuleStatus(BigeyeModule BigeyeModule) {
		return this.BigeyeModuleDao.updateBigeyeModuleStatus(BigeyeModule);
	}
	
	public BigeyeModule findBigeyeModuleOne(String id){
		BigeyeModule BigeyeModule=new BigeyeModule();
		BigeyeModule.setModule_id(id);
		List<BigeyeModule> BigeyeModuleList=this.BigeyeModuleDao.findBigeyeModuleList(BigeyeModule);
		if(BigeyeModuleList!=null && BigeyeModuleList.size()>0){
			return BigeyeModuleList.get(0);
		}
		return null;
	}
	
	public int checkBigeyeModuleName(String name){
		BigeyeModule BigeyeModule=new BigeyeModule();
		BigeyeModule.setModule_name(name);
		List<BigeyeModule> bewsTypeList=this.BigeyeModuleDao.findBigeyeModuleList(BigeyeModule);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkBigeyeModuleName(String id,String name){
		BigeyeModule BigeyeModule=new BigeyeModule();
		BigeyeModule.setModule_name(name);
		List<BigeyeModule> bewsTypeList=this.BigeyeModuleDao.findBigeyeModuleList(BigeyeModule);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					BigeyeModule bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getModule_id())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}
	
	
	/**
	 * @return the BigeyeModuleDao
	 */
	public BigeyeModuleDao getBigeyeModuleDao() {
		return BigeyeModuleDao;
	}

	/**
	 * @param BigeyeModuleDao the BigeyeModuleDao to set
	 */
	public void setBigeyeModuleDao(BigeyeModuleDao BigeyeModuleDao) {
		this.BigeyeModuleDao = BigeyeModuleDao;
	}

}
