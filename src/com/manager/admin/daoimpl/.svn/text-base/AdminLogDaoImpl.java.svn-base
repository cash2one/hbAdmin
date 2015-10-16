package com.manager.admin.daoimpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.admin.dao.AdminLogDao;
import com.manager.admin.entity.AdminLog;
import com.manager.util.DataSourceContextHolder;

public class AdminLogDaoImpl extends SqlSessionDaoSupport implements AdminLogDao {

	public int addAdminLog(AdminLog adminLog) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("AdminLogSql.add", adminLog);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int addAdminLogList(List<AdminLog> adminLogList) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("AdminLogSql.addlist", adminLogList);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public List<AdminLog> findAdminLogList(AdminLog adminLog){
		DataSourceContextHolder.setDbType("0");
		List<AdminLog> list=this.getSqlSession().selectList("AdminLogSql.get", adminLog);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	public List<Map<String,String>> expAdminLogList(AdminLog adminLog,int expnum){
		DataSourceContextHolder.setDbType("0");
		List<Map<String,String>> list=this.getSqlSession().selectList("AdminLogSql.exp", adminLog,new RowBounds(0, expnum));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<AdminLog> findAdminLogList(AdminLog adminLog,int pageNo,int pageSize) {
		DataSourceContextHolder.setDbType("0");
		List<AdminLog> list=this.getSqlSession().selectList("AdminLogSql.get", adminLog,new RowBounds(pageNo, pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findAdminLogCount(AdminLog adminLog){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("AdminLogSql.count", adminLog);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public List<String> findAdminLogService(){
		DataSourceContextHolder.setDbType("0");
		List<String> list=this.getSqlSession().selectList("AdminLogSql.get2");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
