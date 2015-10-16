package com.manager.admin.daoimpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.admin.dao.AdminUserDao;
import com.manager.admin.entity.AdminUser;
import com.manager.admin.entity.AdminUserRole;
import com.manager.util.DataSourceContextHolder;
import com.manager.util.MD5Util;

public class AdminUserDaoImpl extends SqlSessionDaoSupport implements AdminUserDao {
	
	public List<AdminUser> findAdminUserList(AdminUser adminUser,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<AdminUser> ls=this.getSqlSession().selectList("AdminUserSql.get", adminUser,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<Map<String,String>> expAdminUserList(AdminUser adminUser,int expnum){
		DataSourceContextHolder.setDbType("0");
		List<Map<String,String>> ls=this.getSqlSession().selectList("AdminUserSql.exp", adminUser,new RowBounds(0,expnum));
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<AdminUser> findAdminUserList(AdminUser adminUser) {
		DataSourceContextHolder.setDbType("0");
		List<AdminUser> ls=this.getSqlSession().selectList("AdminUserSql.get", adminUser);
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public int findAdminUserCount(AdminUser adminUser){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("AdminUserSql.count", adminUser);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public List<AdminUser> findAdminUserList2(AdminUser adminUser) {
		DataSourceContextHolder.setDbType("0");
		List<AdminUser> ls=this.getSqlSession().selectList("AdminUserSql.get2", adminUser);
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<String> findAdminUserRoleRoleIdList(String adminUser){
		DataSourceContextHolder.setDbType("0");
		List<String> ls=this.getSqlSession().selectList("AdminUserRoleSql.get", adminUser);
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	
	public int addAdminUser(AdminUser adminUser) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("AdminUserSql.add", adminUser);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int addAdminUserRoleList(List<AdminUserRole> item){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("AdminUserRoleSql.addlist", item);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateAdminUser(AdminUser adminUser) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("AdminUserSql.update", adminUser);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int deleteAdminUser(String adminAccount){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("AdminUserSql.delete", adminAccount);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int deleteAdminUserRole(String adminAccount){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("AdminUserRoleSql.delete", adminAccount);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public AdminUser checkAdminUser(AdminUser adminUser) {
		DataSourceContextHolder.setDbType("0");
		adminUser.setAdminPwd(MD5Util.MD5Encode(adminUser.getAdminPwd() + "123456"));
		AdminUser au=this.getSqlSession().selectOne("AdminUserSql.getlogin", adminUser);
		DataSourceContextHolder.clearDbType();
		if(au!=null && au.getAdminAccount()!=null){
			return au;
		}else{
			return null;
		}
	}

}
