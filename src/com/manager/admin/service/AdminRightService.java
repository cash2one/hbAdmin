package com.manager.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.manager.admin.entity.AdminRight;

@Service
public interface AdminRightService {

	public List<AdminRight> findAdminRightList(AdminRight adminRight);
	
	public int addAdminRight(AdminRight adminRight);
	
	public List<AdminRight> findAdminRightList(AdminRight adminRight,int pageNo,int pageSize);
	
	public int findAdminRightCount(AdminRight adminRight);
	
	/**
	 * 获得权限(限制用)
	 * @param adminUser
	 * @return
	 */
	public Map<String, Integer> findRightUrl(String adminUser);
	
	/**
	 * 权限分配用
	 * @return
	 * @throws Exception
	 */
	public Map<String,Map<String, Map<String,String>>> get_Right() throws Exception;
	
	/**
	 * 权限分配用
	 * @return
	 * @throws Exception
	 */
	public Map<String,List<AdminRight>> get_RightMapList() throws Exception ;
}
