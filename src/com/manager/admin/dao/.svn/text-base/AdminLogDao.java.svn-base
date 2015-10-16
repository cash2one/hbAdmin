package com.manager.admin.dao;

import java.util.List;
import java.util.Map;

import com.manager.admin.entity.AdminLog;

public interface AdminLogDao {

	public List<AdminLog> findAdminLogList(AdminLog adminLog,int pageNo,int pageSize);
	
	public List<AdminLog> findAdminLogList(AdminLog adminLog); 
	
	public List<Map<String,String>> expAdminLogList(AdminLog adminLog,int expnum); 
	
	public int findAdminLogCount(AdminLog adminLog);
	
	public int addAdminLog(AdminLog adminLog);
	
	public int addAdminLogList(List<AdminLog> adminLogList);
	
	public List<String> findAdminLogService();
}
