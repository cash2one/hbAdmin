package com.manager.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.manager.admin.entity.AdminUser;

@Service
public interface AdminUserService {
	
	/**
	 * 登录
	 * @param adminUser
	 * @return
	 */
	public AdminUser loginAdminUser(AdminUser adminUser);
	
	/**
	 * 删除管理员
	 * @param adminAccount
	 * @return
	 */
	public int deleteAdminUser(String adminAccount);
	
	/**
	 * 修改管理员信息
	 * @param adminUser
	 * @return
	 */
	public int updateAdminUser(AdminUser adminUser);
	
	/**
	 * 修改管理员信息
	 * @param adminUser
	 * @return
	 */
	public int updateAdminUser2(AdminUser adminUser);
	
	/**
	 * 根据角色与管理员关联查询管理员信息
	 * @param adminUser
	 * @return
	 */
	public List<AdminUser> findAdminUserList2(AdminUser adminUser);
	
	/**
	 * 查询管理员
	 * @param adminUser
	 * @return
	 */
	public List<AdminUser> findAdminUserList(AdminUser adminUser);
	
	/**
	 * 翻页查询
	 * @param adminUser
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AdminUser> findAdminUserList(AdminUser adminUser,int pageNo,int pageSize);
	
	/**
	 * 导出管理员信息
	 * @param adminUser
	 * @param expnum
	 * @return
	 */
	public List<Map<String,String>> findAdminUserList(AdminUser adminUser,int expnum);
	
	/**
	 * 根据条件查询页数
	 * @param adminUser
	 * @return
	 */
	public int findAdminUserCount(AdminUser adminUser);
	
	/**
	 * 判断是否存在该角色的管理员
	 * @param roleId
	 * @return
	 */
	public String checkAdminUserRole(String roleId);
	
	/**
	 * 判断管理员帐号是否存在
	 * @param adminAccount
	 * @return
	 */
	public int checkAdminUser(String adminAccount);
	
	/**
	 * 添加管理员
	 * @param adminUser
	 * @return
	 */
	public int addAdminUser(AdminUser adminUser);
	
	/**
	 * 根据帐号获取所属的角色ID
	 * @param adminAccount
	 * @return
	 */
	public List<String> findAdminUserRoleRoleIdList(String adminAccount);
	
	/**
	 * 根据帐号查询详情
	 * @param adminAccount
	 * @return
	 */
	public AdminUser findAdminUser(String adminAccount);
}
