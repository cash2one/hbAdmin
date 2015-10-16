package com.manager.admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.manager.admin.dao.AdminRoleDao;
import com.manager.admin.entity.AdminRole;
import com.manager.admin.entity.AdminRoleRight;
import com.manager.admin.service.AdminRoleService;

public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	private AdminRoleDao adminRoleDao;
	
	public List<AdminRole> findAdminRoleList(AdminRole adminRole) {
		return this.adminRoleDao.findAdminRoleList(adminRole);
	}
	
	
	/**
	 * 根据条件分页查询角色
	 * @param adminRole
	 * @return
	 */
	public List<AdminRole> findAdminRoleList(AdminRole adminRole,int pageNo,int pageSize){
		return this.adminRoleDao.findAdminRoleList(adminRole,pageNo,pageSize);
	}
	
	/**
	 * 根据条件导出角色数据
	 * @param adminRole
	 * @param expnum
	 * @return
	 */
	public List<Map<String,String>> expAdminRoleList(AdminRole adminRole,int expnum){
		return this.adminRoleDao.expAdminRoleList(adminRole,expnum);
	}
	
	/**
	 * 根据条件查询角色
	 * @param adminRole
	 * @return
	 */
	public int findAdminRoleCount(AdminRole adminRole){
		return this.adminRoleDao.findAdminRoleCount(adminRole);
	}
	
	/**
	 * 根据角色名称查询角色
	 * @param roleName
	 * @return
	 */
	public AdminRole findAdminRole(String roleName){
		AdminRole adminRole=new AdminRole();
		adminRole.setRoleName(roleName);
		List<AdminRole> list=this.adminRoleDao.findAdminRoleList(adminRole);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@Transactional
	public int deleteAdminRole(String roleId){
		try{
			int in1=this.adminRoleDao.deleteAdminRole(roleId);
			AdminRoleRight adminRoleRight=new AdminRoleRight();
			adminRoleRight.setRoleId(roleId);
			//删除原有的权限
			this.adminRoleDao.deleteAdminRoleRightList(adminRoleRight);
			if(in1>0){
				return 1;
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 修改角色
	 * @param roleId
	 * @param adminAccount
	 * @param roleName
	 * @param roleState
	 * @param right_id
	 * @return
	 */
	@Transactional
	public int updateAdminRole(String roleId,String adminAccount,String roleName,String roleState,String[] right_id){
		try{
			AdminRole adminRole=new AdminRole();
			adminRole.setRoleName(roleName);
			adminRole.setModifyAdmin(adminAccount);
			adminRole.setRoleId(roleId);
			adminRole.setRoleState(roleState);
			List<AdminRoleRight> arrList=new ArrayList<AdminRoleRight>();
			AdminRoleRight arr;
			for(int i=0;i<right_id.length;i++){
				arr=new AdminRoleRight();
				arr.setRoleId(roleId);
				arr.setRightId(right_id[i]);
				arrList.add(arr);
			}
			//修改角色信息
			int in1=this.adminRoleDao.updateAdminRole(adminRole);
			if(in1>0){
				AdminRoleRight adminRoleRight=new AdminRoleRight();
				adminRoleRight.setRoleId(roleId);
				//删除原有的权限
				this.adminRoleDao.deleteAdminRoleRightList(adminRoleRight);
				//新增权限
				int in2=this.adminRoleDao.addAdminRoleRightList(arrList);
				if(in1>0 && in2>0){
					return 1;
				}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 修改角色状态
	 * @param roleId
	 * @param roleState
	 * @return
	 */
	public int updateAdminRole(String roleId,String roleState){
		AdminRole adminRole=new AdminRole();
		adminRole.setRoleId(roleId);
		adminRole.setRoleState(roleState);
		return this.adminRoleDao.updateAdminRole(adminRole);
	}
	
	/**
	 * 添加角色
	 * @param adminAccount
	 * @param roleName
	 * @param roleState
	 * @param right_id
	 * @param maxroleid
	 * @return
	 */
	@Transactional
	public int addAdminRole(String adminAccount,String roleName,String roleState,String[] right_id,String maxroleid){
		try{
			AdminRole adminRole=new AdminRole();
			adminRole.setRoleName(roleName);
			adminRole.setCreateAdmin(adminAccount);
			adminRole.setRoleId(maxroleid);
			adminRole.setRoleState(roleState);
			List<AdminRoleRight> arrList=new ArrayList<AdminRoleRight>();
			AdminRoleRight arr;
			for(int i=0;i<right_id.length;i++){
				arr=new AdminRoleRight();
				arr.setRoleId(maxroleid);
				arr.setRightId(right_id[i]);
				arrList.add(arr);
			}
			int in1=this.adminRoleDao.addAdminRole(adminRole);
			if(in1>0){
				AdminRoleRight adminRoleRight=new AdminRoleRight();
				adminRoleRight.setRoleId(maxroleid);
				int in2=this.adminRoleDao.addAdminRoleRightList(arrList);
				if(in1>0 && in2>0){
					return 1;
				}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 获取角色对应权限
	 * @param adminRoleRight
	 * @return
	 */
	public List<AdminRoleRight> findadminRoleRightList(AdminRoleRight adminRoleRight){
		return this.adminRoleDao.findAdminRoleRightList(adminRoleRight);
	}
	
	/**
	 * 获得最大的角色id
	 */
	public String get_MaxroleId() throws Exception{
		return this.adminRoleDao.get_MaxroleId()+"";
		
	}
	
	/**
	 * 判断角色名是否存在
	 * @param roleName
	 * @return
	 */
	public int checkAdminRole(String roleName,String roleId){
		AdminRole adminRole=new AdminRole();
		adminRole.setRoleName(roleName);
		List<AdminRole> list =this.adminRoleDao.findAdminRoleList(adminRole);
		int ii=0;
		if(list!=null && list.size()>0){
			ii=list.size();
			if(roleId!=null){
				for(int i=0;i<list.size();i++){
					AdminRole ar=list.get(i);
					if(roleId.equals(ar.getRoleId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}

	/**
	 * @return the adminRoleDao
	 */
	public AdminRoleDao getAdminRoleDao() {
		return adminRoleDao;
	}

	/**
	 * @param adminRoleDao the adminRoleDao to set
	 */
	public void setAdminRoleDao(AdminRoleDao adminRoleDao) {
		this.adminRoleDao = adminRoleDao;
	}

	
}
