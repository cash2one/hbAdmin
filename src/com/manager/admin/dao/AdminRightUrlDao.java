package com.manager.admin.dao;

import java.util.Map;

public interface AdminRightUrlDao {

	/**
	 * 获得权限(限制用)
	 * @param adminUser
	 * @return
	 */
	public Map<String,Integer> findRightUrl(String adminUser);
}
