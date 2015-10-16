package com.manager.admin.dao;

import java.util.List;

import com.manager.admin.entity.AdminRight;

public interface AdminRightDao {

	public List<AdminRight> findAdminRightList(AdminRight adminRight);
	
	public List<AdminRight> findAdminRightList(AdminRight adminRight,int pageNo,int pageSize);
	
	public int findAdminRightCount(AdminRight adminRight);
	
	public List<AdminRight> findAdminRightList2(AdminRight adminRight);
	
	public int addAdminRight(AdminRight adminRight);
}
