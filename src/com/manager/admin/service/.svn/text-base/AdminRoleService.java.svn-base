package com.manager.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.manager.admin.entity.AdminRole;
import com.manager.admin.entity.AdminRoleRight;

@Service
public interface AdminRoleService {
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	public int deleteAdminRole(String roleId);

	/**
	 * 根据条件查询角色
	 * @param adminRole
	 * @return
	 */
	public List<AdminRole> findAdminRoleList(AdminRole adminRole);
	
	/**
	 * 根据条件分页查询角色
	 * @param adminRole
	 * @return
	 */
	public List<AdminRole> findAdminRoleList(AdminRole adminRole,int pageNo,int pageSize);
	
	/**
	 * 根据条件导出角色数据
	 * @param adminRole
	 * @param expnum
	 * @return
	 */
	public List<Map<String,String>> expAdminRoleList(AdminRole adminRole,int expnum);
	
	/**
	 * 根据条件查询角色
	 * @param adminRole
	 * @return
	 */
	public int findAdminRoleCount(AdminRole adminRole);
	
	/**
	 * 修改角色
	 * @param roleId
	 * @param adminAccount
	 * @param roleName
	 * @param roleState
	 * @param right_id
	 * @return
	 */
	public int updateAdminRole(String roleId,String adminAccount,String roleName,String roleState,String[] right_id);
	
	/**
	 * 修改角色状态
	 * @param roleId
	 * @param roleState
	 * @return
	 */
	public int updateAdminRole(String roleId,String roleState);
	
	/**
	 * 添加角色
	 * @param adminAccount
	 * @param roleName
	 * @param roleState
	 * @param right_id
	 * @param maxroleid
	 * @return
	 */
	public int addAdminRole(String adminAccount,String roleName,String roleState,String[] right_id,String maxroleid);
	
	
	/**
	 * 根据角色名称查询角色
	 * @param roleName
	 * @return
	 */
	public AdminRole findAdminRole(String roleName);
	
	
	/**
	 * 获取角色对应权限
	 * @param adminRoleRight
	 * @return
	 */
	public List<AdminRoleRight> findadminRoleRightList(AdminRoleRight adminRoleRight);
	
	/**
	 * 获得最大的角色id
	 */
	public String get_MaxroleId() throws Exception;
	
	/**
	 * 判断角色名是否存在
	 * @param roleName
	 * @return
	 */
	public int checkAdminRole(String roleName,String roleId);
}
