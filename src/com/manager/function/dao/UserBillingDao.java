package com.manager.function.dao;

import com.manager.function.entity.UserBilling;

public interface UserBillingDao {
	
	public void add(UserBilling ub);
	
	public UserBilling findOne(String user_id);
	
	public void addScore(UserBilling ub);

}
