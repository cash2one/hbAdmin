package com.manager.admin.dao;

import java.util.List;
import java.util.Map;

import com.manager.admin.entity.AdminRole;
import com.manager.admin.entity.AdminRoleRight;

public interface AdminRoleDao {

	public List<AdminRole> findAdminRoleList(AdminRole adminRole);
	
	public List<AdminRole> findAdminRoleList(AdminRole adminRole,int pageNo,int pageSize);
	
	public List<Map<String,String>> expAdminRoleList(AdminRole adminRole,int expnum);
	
	public int findAdminRoleCount(AdminRole adminRole);
	
	public List<AdminRole> findAdminRoleList2(AdminRole adminRole);
	
	public int addAdminRole(AdminRole adminRole);
	
	public int updateAdminRole(AdminRole adminRole);
	
	public List<AdminRoleRight> findAdminRoleRightList(AdminRoleRight adminRoleRight);
	
	public int deleteAdminRoleRightList(AdminRoleRight adminRoleRight);
	
	public int addAdminRoleRightList(List<AdminRoleRight> arrList);
	
	public int deleteAdminRole(String roleId);
	
	public int get_MaxroleId();
}
