package com.manager.admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.manager.admin.dao.AdminUserDao;
import com.manager.admin.entity.AdminUser;
import com.manager.admin.entity.AdminUserRole;
import com.manager.admin.service.AdminUserService;
import com.manager.util.CollectionUtil;
import com.manager.util.MD5Util;

public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	
	/**
	 * 修改管理员信息
	 * @param adminUser
	 * @return
	 */
	@Transactional
	public int updateAdminUser(AdminUser adminUser){
		try{
			if(CollectionUtil.checkNull(adminUser.getAdminPwd())){
				adminUser.setAdminPwd(MD5Util.MD5Encode(adminUser.getAdminPwd() + "123456"));
			}
			int in=this.adminUserDao.updateAdminUser(adminUser);
			if(in>0){
				return 1;
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 删除管理员
	 * @param adminAccount
	 * @return
	 */
	@Transactional
	public int deleteAdminUser(String adminAccount){
		try{
			int in=this.adminUserDao.deleteAdminUserRole(adminAccount);
			if(in>0){
				this.adminUserDao.deleteAdminUser(adminAccount);
				return 1;
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 修改管理员信息
	 * @param adminUser
	 * @return
	 */
	@Transactional
	public int updateAdminUser2(AdminUser adminUser){
		try{
			if(CollectionUtil.checkNull(adminUser.getAdminPwd())){
				adminUser.setAdminPwd(MD5Util.MD5Encode(adminUser.getAdminPwd() + "123456"));
			}
			int in=this.adminUserDao.updateAdminUser(adminUser);
			if(in>0){
				String[] roleid=adminUser.getAdminRole().split(";");
				List<AdminUserRole> adminUserRoleList=new ArrayList<AdminUserRole>();
				AdminUserRole aur=null;
				for(int i=0;i<roleid.length;i++){
					aur=new AdminUserRole();
					aur.setAdminUser(adminUser.getAdminAccount());
					aur.setRoleId(roleid[i]);
					adminUserRoleList.add(aur);
				}
				this.adminUserDao.deleteAdminUserRole(adminUser.getAdminAccount());
				int in2=this.adminUserDao.addAdminUserRoleList(adminUserRoleList);
				if(in2>0) return 1;
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 添加管理员
	 * @param adminUser
	 * @return
	 */
	@Transactional
	public int addAdminUser(AdminUser adminUser){
		try{
			int in=this.adminUserDao.addAdminUser(adminUser);
			if(in>0){
				String[] roleid=adminUser.getAdminRole().split(";");
				List<AdminUserRole> adminUserRoleList=new ArrayList<AdminUserRole>();
				AdminUserRole aur=null;
				for(int i=0;i<roleid.length;i++){
					aur=new AdminUserRole();
					aur.setAdminUser(adminUser.getAdminAccount());
					aur.setRoleId(roleid[i]);
					adminUserRoleList.add(aur);
				}
				int in2=this.adminUserDao.addAdminUserRoleList(adminUserRoleList);
				if(in2>0) return 1;
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	/**
	 * 根据帐号查询详情
	 * @param adminAccount
	 * @return
	 */
	public AdminUser findAdminUser(String adminAccount){
		AdminUser adminUser=new AdminUser();
		adminUser.setAdminAccount(adminAccount);
		List<AdminUser> list=this.adminUserDao.findAdminUserList(adminUser);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 判断管理员帐号是否存在
	 * @param adminAccount
	 * @return
	 */
	public int checkAdminUser(String adminAccount){
		AdminUser adminUser=new AdminUser();
		adminUser.setAdminAccount(adminAccount);
		List<AdminUser> list=this.adminUserDao.findAdminUserList(adminUser);
		if(list!=null && list.size()>0){
			return 1;
		}
		return 0;
	}
	
	/**
	 * 根据帐号获取所属的角色ID
	 * @param adminUser
	 * @return
	 */
	public List<String> findAdminUserRoleRoleIdList(String adminAccount){
		return this.adminUserDao.findAdminUserRoleRoleIdList(adminAccount);
	}
	
	/**
	 * 判断是否存在该角色的管理员
	 * @param roleId
	 * @return
	 */
	public String checkAdminUserRole(String roleId){
		AdminUser adminUser=new AdminUser();
		adminUser.setAdminRole(roleId);
		List<AdminUser> list=this.adminUserDao.findAdminUserList2(adminUser);
		String str="";
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				str=str+list.get(i).getAdminAccount()+",";
			}
		}
		return str;
	}
	
	/**
	 * 登录
	 * @param adminUser
	 * @return
	 */
	public AdminUser loginAdminUser(AdminUser adminUser) {
		
		return this.adminUserDao.checkAdminUser(adminUser);
	}
	
	/**
	 * 查询管理员
	 * @param adminUser
	 * @return
	 */
	public List<AdminUser> findAdminUserList(AdminUser adminUser){
		return this.adminUserDao.findAdminUserList(adminUser);
	}
	
	/**
	 * 翻页查询
	 * @param adminUser
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AdminUser> findAdminUserList(AdminUser adminUser,int pageNo,int pageSize){
		return this.adminUserDao.findAdminUserList(adminUser, pageNo, pageSize);
	}
	
	/**
	 * 导出管理员信息
	 * @param adminUser
	 * @param expnum
	 * @return
	 */
	public List<Map<String,String>> findAdminUserList(AdminUser adminUser,int expnum){
		return this.adminUserDao.expAdminUserList(adminUser, expnum);
	}
	
	/**
	 * 根据条件查询页数
	 * @param adminUser
	 * @return
	 */
	public int findAdminUserCount(AdminUser adminUser){
		return this.adminUserDao.findAdminUserCount(adminUser);
	}
	
	/**
	 * 根据角色与管理员关联查询管理员信息
	 * @param adminUser
	 * @return
	 */
	public List<AdminUser> findAdminUserList2(AdminUser adminUser){
		return this.adminUserDao.findAdminUserList2(adminUser);
	}

	/**
	 * @return the adminUserDao
	 */
	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	/**
	 * @param adminUserDao the adminUserDao to set
	 */
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
}
