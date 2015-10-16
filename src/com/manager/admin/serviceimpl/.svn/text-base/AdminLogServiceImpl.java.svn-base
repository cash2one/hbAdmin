package com.manager.admin.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.manager.admin.dao.AdminLogDao;
import com.manager.admin.entity.AdminLog;
import com.manager.admin.service.AdminLogService;
import com.manager.util.CollectionUtil;

public class AdminLogServiceImpl implements AdminLogService {
	
	private AdminLogDao adminLogDao;
	
	/**
	 * 查询
	 * @param adminLog
	 * @return
	 */
	public List<AdminLog> findAdminLogList(AdminLog adminLog){
		return this.adminLogDao.findAdminLogList(adminLog);
	}
	
	/**
	 * 查询
	 * @param adminLog
	 * @return
	 */
	public List<Map<String,String>> expAdminLogList(AdminLog adminLog,int expnum){
		return this.adminLogDao.expAdminLogList(adminLog,expnum);
	}
	

	public int addAdminLog(AdminLog adminLog, HttpServletRequest request) {
		if(!CollectionUtil.checkNull(adminLog.getOperAdmin()) && request.getSession().getAttribute("admin_account")!=null){
			adminLog.setOperAdmin((String) request.getSession().getAttribute("admin_account"));
		}
		adminLog.setOperIp(getIpAddr(request));
		return this.adminLogDao.addAdminLog(adminLog);
	}
	
	/**
	 * 添加日志
	 * @param adminAccount
	 * @param operService
	 * @param operMemo
	 * @param openState
	 * @param request
	 * @return
	 */
	public int addAdminLog(String adminAccount,String operService,String operMemo,int openState,HttpServletRequest request){
		AdminLog adminLog=new AdminLog();
		adminLog.setOperAdmin(adminAccount);
		adminLog.setOperIp(getIpAddr(request));
		adminLog.setOperService(operService);
		adminLog.setOperMemo(operMemo);
		adminLog.setOpenState(openState);
		adminLog.setOperLang("中文");
		return this.adminLogDao.addAdminLog(adminLog);
	}

	public int addAdminLog(String operService, String operMemo,
			int openState, HttpServletRequest request) {
		AdminLog adminLog=new AdminLog();
		if(request.getSession().getAttribute("admin_account")!=null){
			adminLog.setOperAdmin((String) request.getSession().getAttribute("admin_account"));
		}
		adminLog.setOperIp(getIpAddr(request));
		adminLog.setOperService(operService);
		adminLog.setOperMemo(operMemo);
		adminLog.setOpenState(openState);
		adminLog.setOperLang("中文");
		return this.adminLogDao.addAdminLog(adminLog);
	}

	public int addAdminLogList(List<AdminLog> allist) {
		return this.adminLogDao.addAdminLogList(allist);
	}

	public List<AdminLog> findAdminLogList(AdminLog adminLog,int pageNo,int pageSize) {
		return this.adminLogDao.findAdminLogList(adminLog,pageNo,pageSize);
	}
	
	/**
	 * 总行数
	 * @param adminLog
	 * @return
	 */
	public int findAdminLogCount(AdminLog adminLog){
		return this.adminLogDao.findAdminLogCount(adminLog);
	}
	
	public List<String> findAdminLogService(){
		return this.adminLogDao.findAdminLogService();
	}

	 public String getIpAddr(HttpServletRequest request) {      
	        String ip = request.getHeader("x-forwarded-for");      
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	            ip = request.getHeader("Proxy-Client-IP");      
	        }      
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	            ip = request.getHeader("WL-Proxy-Client-IP");      
	        }      
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	            ip = request.getRemoteAddr();      
	        }      
	        return ip;      
	   }

	/**
	 * @return the adminLogDao
	 */
	public AdminLogDao getAdminLogDao() {
		return adminLogDao;
	}

	/**
	 * @param adminLogDao the adminLogDao to set
	 */
	public void setAdminLogDao(AdminLogDao adminLogDao) {
		this.adminLogDao = adminLogDao;
	} 
	 
	 
}
