package com.manager.admin.dao;

import java.util.List;
import java.util.Map;

import com.manager.admin.entity.AdminUser;
import com.manager.admin.entity.AdminUserRole;

public interface AdminUserDao {
	
	public AdminUser checkAdminUser(AdminUser adminUser);
	
	public List<AdminUser> findAdminUserList(AdminUser adminUser,int pageNo,int pageSize);
	
	public List<Map<String,String>> expAdminUserList(AdminUser adminUser,int expnum);
	
	public int findAdminUserCount(AdminUser adminUser);

	public List<AdminUser> findAdminUserList(AdminUser adminUser);
	
	public List<AdminUser> findAdminUserList2(AdminUser adminUser);
	
	public int addAdminUser(AdminUser adminUser);
	
	public int updateAdminUser(AdminUser adminUser);
	
	public int addAdminUserRoleList(List<AdminUserRole> adminUserRoleList);
	
	public int deleteAdminUserRole(String adminAccount);
	
	public int deleteAdminUser(String adminAccount);
	
	public List<String> findAdminUserRoleRoleIdList(String adminAccount);
}
