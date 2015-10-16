package com.manager.function.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.UserLearnPlanDao;
import com.manager.function.entity.UserLearnplan;
import com.manager.function.service.UserLearnplanService;

public class UserLearnplanServiceImpl implements UserLearnplanService {
	
	@Autowired
	private UserLearnPlanDao UserLearnPlanDao;
	
	public List<UserLearnplan> get_statistics(String num,
			UserLearnplan UserLearnplan, int pageNo, int pageSize) {
		return this.UserLearnPlanDao.get_statistics(num, UserLearnplan, pageNo, pageSize);
	}
	
	public List<UserLearnplan> get_statistics(String num,UserLearnplan UserLearnplan) {
		return this.UserLearnPlanDao.get_statistics(num,UserLearnplan);
	}
	
	public int get_statistics_count(String num, UserLearnplan UserLearnplan) {
		return this.UserLearnPlanDao.get_statistics_count(num, UserLearnplan);
	}
	
	public List<UserLearnplan> get_statistics_date() {
		return this.UserLearnPlanDao.get_statistics_date();
	}
	
	

	/**
	 * @return the UserLearnplanDao
	 */
	public UserLearnPlanDao getUserLearnplanDao() {
		return UserLearnPlanDao;
	}

	/**
	 * @param UserLearnplanDao the UserLearnplanDao to set
	 */
	public void setUserLearnplanDao(UserLearnPlanDao UserLearnPlanDao) {
		this.UserLearnPlanDao = UserLearnPlanDao;
	}

}
