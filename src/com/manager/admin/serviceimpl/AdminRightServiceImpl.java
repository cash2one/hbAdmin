
package com.manager.admin.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.admin.dao.AdminRightDao;
import com.manager.admin.dao.AdminRightUrlDao;
import com.manager.admin.entity.AdminRight;
import com.manager.admin.service.AdminRightService;

public class AdminRightServiceImpl implements AdminRightService {
	@Autowired
	private AdminRightDao adminRightDao;
	@Autowired
	private AdminRightUrlDao adminRightUrlDao;
	
	public List<AdminRight> findAdminRightList(AdminRight adminRight,int pageNo,int pageSize){
		return this.adminRightDao.findAdminRightList(adminRight, pageNo, pageSize);
	}
	
	public int findAdminRightCount(AdminRight adminRight){
		return this.adminRightDao.findAdminRightCount(adminRight);
	}
	
	public List<AdminRight> findAdminRightList(AdminRight adminRight) {
		return this.adminRightDao.findAdminRightList(adminRight);
	}

	public int addAdminRight(AdminRight adminRight) {
		return this.adminRightDao.addAdminRight(adminRight);
	}
	
	public Map<String,Map<String, Map<String,String>>> get_Right() throws Exception {
		try{
			Map<String,Map<String, Map<String,String>>> ParMap =new HashMap<String,Map<String, Map<String,String>>>();
			AdminRight ar1=new AdminRight();
			ar1.setParentId("0");
			List<AdminRight> l=this.adminRightDao.findAdminRightList(ar1);
			if(l!=null){
				for(int i=0;i<l.size();i++){
					AdminRight adminRight1=l.get(i);
					//key为 SHOW_ORDER+RIGHT_ID+RIGHT_NAME
					String key1=adminRight1.getShowOrder()+";"+adminRight1.getRightId()+";"+adminRight1.getRightName();
					AdminRight ar2=new AdminRight();
					ar2.setParentId(adminRight1.getRightId());
					List<AdminRight> l1=this.adminRightDao.findAdminRightList(ar2);
					Map<String,Map<String, String>> map1=new HashMap<String, Map<String, String>>();
					if(null!=l1){
					//二级菜单
					for(int j=0;j<l1.size();j++){
						AdminRight adminRight2=l1.get(j);
						String key2=adminRight2.getRightId()+";"+adminRight2.getRightName();	//二级权限KEY=right_id;right_name
						
						AdminRight ar3=new AdminRight();
						ar3.setParentId(adminRight2.getRightId());
						List<AdminRight> l2=this.adminRightDao.findAdminRightList(ar3);
						Map<String,String> map2=new HashMap<String,String>();
						if(null!=l2){
						for(int k=0;k<l2.size();k++){
							AdminRight adminRight3=l2.get(k);
							String key3=adminRight3.getRightId()+"";
							String key3value=adminRight3.getRightName()+"";
							
							map2.put(key3, key3value);//(right_id,right_name)
						}
						}
						map1.put(key2, map2);
					}
					
					}
					ParMap.put(key1, map1);
				//	ParMap
					
				}					
			}
			return ParMap;
		}catch(Exception e){
			throw e;
		}
	}
	
	public Map<String,List<AdminRight>> get_RightMapList() throws Exception {
		try{
			Map<String,List<AdminRight>> ParMap =new HashMap<String,List<AdminRight>>();
			AdminRight ar1=new AdminRight();
			ar1.setRightLevel("0");
			List<AdminRight> l0=this.adminRightDao.findAdminRightList(ar1);
			
			ar1=new AdminRight();
			ar1.setRightLevel("1");
			
			List<AdminRight> l1=this.adminRightDao.findAdminRightList2(ar1);
			
			ar1=new AdminRight();
			ar1.setRightLevel("2");
			List<AdminRight> l2=this.adminRightDao.findAdminRightList2(ar1);
			
			ar1=new AdminRight();
			ar1.setRightLevel("3");
			List<AdminRight> l3=this.adminRightDao.findAdminRightList2(ar1);
			
			ParMap.put("r0", l0);
			ParMap.put("r1", l1);
			ParMap.put("r2", l2);
			ParMap.put("r3", l3);
			
			return ParMap;
		}catch(Exception e){
			throw e;
			}
	}
	
	/**
	 * 获得权限(限制用)
	 * @param adminUser
	 * @return
	 */
	public Map<String, Integer> findRightUrl(String adminUser){
		return this.adminRightUrlDao.findRightUrl(adminUser);
	}

	/**
	 * @return the adminRightDao
	 */
	public AdminRightDao getAdminRightDao() {
		return adminRightDao;
	}

	/**
	 * @param adminRightDao the adminRightDao to set
	 */
	public void setAdminRightDao(AdminRightDao adminRightDao) {
		this.adminRightDao = adminRightDao;
	}

	/**
	 * @return the adminRightUrlDao
	 */
	public AdminRightUrlDao getAdminRightUrlDao() {
		return adminRightUrlDao;
	}

	/**
	 * @param adminRightUrlDao the adminRightUrlDao to set
	 */
	public void setAdminRightUrlDao(AdminRightUrlDao adminRightUrlDao) {
		this.adminRightUrlDao = adminRightUrlDao;
	}

	
}
