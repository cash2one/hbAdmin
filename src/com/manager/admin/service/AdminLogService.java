package com.manager.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.admin.entity.AdminLog;

@Service
public interface AdminLogService {

	/**
	 * 添加日志
	 * @param adminLog
	 * @param request
	 * @return
	 */
	public int addAdminLog(AdminLog adminLog,HttpServletRequest request);
	
	/**
	 * 添加日志
	 * @param operService
	 * @param operMemo
	 * @param openState
	 * @param request
	 * @return
	 */
	public int addAdminLog(String operService,String operMemo,int openState,HttpServletRequest request);
	
	/**
	 * 添加日志
	 * @param adminAccount
	 * @param operService
	 * @param operMemo
	 * @param openState
	 * @param request
	 * @return
	 */
	public int addAdminLog(String adminAccount,String operService,String operMemo,int openState,HttpServletRequest request);
	
	/**
	 * 添加多条日志
	 * @param allist
	 * @return
	 */
	public int addAdminLogList(List<AdminLog> allist);
	
	/**
	 * 查询
	 * @param adminLog
	 * @return
	 */
	public List<AdminLog> findAdminLogList(AdminLog adminLog,int pageNo,int pageSize);
	
	/**
	 * 查询
	 * @param adminLog
	 * @return
	 */
	public List<AdminLog> findAdminLogList(AdminLog adminLog);
	
	/**
	 * 查询
	 * @param adminLog
	 * @return
	 */
	public List<Map<String,String>> expAdminLogList(AdminLog adminLog,int expnum);
	
	/**
	 * 总行数
	 * @param adminLog
	 * @return
	 */
	public int findAdminLogCount(AdminLog adminLog);
	
	/**
	 * 查询日志业务类型
	 * @return
	 */
	public List<String> findAdminLogService();
}
