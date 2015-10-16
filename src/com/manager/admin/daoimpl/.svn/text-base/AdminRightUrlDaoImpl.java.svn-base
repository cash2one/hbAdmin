package com.manager.admin.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.admin.dao.AdminRightUrlDao;
import com.manager.util.DataSourceContextHolder;

public class AdminRightUrlDaoImpl extends SqlSessionDaoSupport implements AdminRightUrlDao {

	public Map<String, Integer> findRightUrl(String adminUser) {
		DataSourceContextHolder.setDbType("0");
		List<String> list=this.getSqlSession().selectList("AdminRightUrlSql.getadminurl", adminUser);
		DataSourceContextHolder.clearDbType();
		Map<String, Integer> ht = new HashMap<String, Integer>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ht.put(String.valueOf(list.get(i)), 0);
			}
			return ht;
		}
		return null;
	}

}
