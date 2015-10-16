package com.manager.admin.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.admin.dao.AdminRightDao;
import com.manager.admin.entity.AdminRight;
import com.manager.util.DataSourceContextHolder;

public class AdminRightDaoImpl extends SqlSessionDaoSupport implements AdminRightDao {

	public List<AdminRight> findAdminRightList(AdminRight adminRight) {
		DataSourceContextHolder.setDbType("0");
		List<AdminRight> ls=this.getSqlSession().selectList("AdminRightSql.get", adminRight);
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<AdminRight> findAdminRightList(AdminRight adminRight,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<AdminRight> list=this.getSqlSession().selectList("AdminRightSql.get3", adminRight,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findAdminRightCount(AdminRight adminRight){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("AdminRightSql.count", adminRight);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public List<AdminRight> findAdminRightList2(AdminRight adminRight) {
		DataSourceContextHolder.setDbType("0");
		List<AdminRight> ls=this.getSqlSession().selectList("AdminRightSql.get2", adminRight);
		DataSourceContextHolder.clearDbType();
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}

	public int addAdminRight(AdminRight adminRight) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("AdminRightSql.add", adminRight);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
