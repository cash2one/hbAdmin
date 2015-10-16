package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.UserBillingDao;
import com.manager.function.entity.UserBilling;
import com.manager.util.DataSourceContextHolder;

public class UserBillingDaoImpl  extends SqlSessionDaoSupport implements UserBillingDao {

	public void add(UserBilling ub) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("UserBillingSql.add", ub);
		DataSourceContextHolder.clearDbType();
	}

	public UserBilling findOne(String user_id) {
		DataSourceContextHolder.setDbType("0");
		List<UserBilling> ls = this.getSqlSession().selectList("UserBillingSql.findOne", user_id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public void addScore(UserBilling ub) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("UserBillingSql.addScore",ub);
		DataSourceContextHolder.clearDbType();
	}

}
