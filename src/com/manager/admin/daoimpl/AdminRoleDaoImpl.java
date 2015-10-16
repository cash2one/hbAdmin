package com.manager.admin.daoimpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.admin.dao.AdminRoleDao;
import com.manager.admin.entity.AdminRole;
import com.manager.admin.entity.AdminRoleRight;
import com.manager.util.DataSourceContextHolder;

public class AdminRoleDaoImpl extends SqlSessionDaoSupport implements AdminRoleDao {
	
	public List<AdminRoleRight> findAdminRoleRightList(AdminRoleRight adminRoleRight){
		DataSourceContextHolder.setDbType("0");
		List<AdminRoleRight> list=this.getSqlSession().selectList("AdminRoleRightSql.get", adminRoleRight);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<AdminRole> findAdminRoleList(AdminRole adminRole) {
		DataSourceContextHolder.setDbType("0");
		List<AdminRole> list=this.getSqlSession().selectList("AdminRoleSql.get", adminRole);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<AdminRole> findAdminRoleList(AdminRole adminRole,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<AdminRole> list=this.getSqlSession().selectList("AdminRoleSql.get", adminRole,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Map<String,String>> expAdminRoleList(AdminRole adminRole,int expnum){
		DataSourceContextHolder.setDbType("0");
		List<Map<String,String>> list=this.getSqlSession().selectList("AdminRoleSql.exp", adminRole,new RowBounds(0,expnum));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findAdminRoleCount(AdminRole adminRole){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("AdminRoleSql.count", adminRole);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public List<AdminRole> findAdminRoleList2(AdminRole adminRole) {
		DataSourceContextHolder.setDbType("0");
		List<AdminRole> list=this.getSqlSession().selectList("AdminRoleSql.get2", adminRole);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int addAdminRole(AdminRole adminRole){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("AdminRoleSql.add", adminRole);
		DataSourceContextHolder.clearDbType();
		if(in>0){
			return 1;
		}
		return 0;
	}
	
	public int deleteAdminRole(String roleId){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("AdminRoleSql.delete", roleId);
		DataSourceContextHolder.clearDbType();
		if(in>0){
			return 1;
		}
		return 0;
	}
	
	public int updateAdminRole(AdminRole adminRole){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("AdminRoleSql.update", adminRole);
		DataSourceContextHolder.clearDbType();
		if(in>0){
			return 1;
		}
		return 0;
	}

	public int get_MaxroleId(){
		DataSourceContextHolder.setDbType("0");
		int roleid=this.getSqlSession().selectOne("AdminRoleSql.maxroleid");
		DataSourceContextHolder.clearDbType();
		return roleid+1;
	}
	
	public int addAdminRoleRightList(List<AdminRoleRight> arrList){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("AdminRoleRightSql.addlist", arrList);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int deleteAdminRoleRightList(AdminRoleRight adminRoleRight){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("AdminRoleRightSql.delete", adminRoleRight);
		DataSourceContextHolder.clearDbType();
		return in;
	}
}
