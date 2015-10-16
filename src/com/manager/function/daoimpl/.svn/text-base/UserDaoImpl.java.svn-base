package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.UserDao;
import com.manager.function.entity.User;
import com.manager.util.DataSourceContextHolder;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	public void add(User user) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("UserSql.add", user);
		DataSourceContextHolder.clearDbType();
	}

	public User findById(String user_id) {
		DataSourceContextHolder.setDbType("0");
		List<User> ls = this.getSqlSession().selectList("UserSql.getOne", user_id);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public void updateaddress(User user) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("UserSql.updateaddress", user);
		DataSourceContextHolder.clearDbType();
	}

	public User isExcit(User user) {
		DataSourceContextHolder.setDbType("0");
		List<User> ls = this.getSqlSession().selectList("UserSql.isExcit", user);
		if(ls!=null && ls.size()>0){
			return ls.get(0);
		}
		return null;
	}
	
	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserSql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	/**
	 * 统计
	 * @param user
	 * @return
	 */
	public List<User> statistics(User user,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<User> list=this.getSqlSession().selectList("UserSql.statistics", user,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int statistics_count(User user){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserSql.statistics_count", user);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public List<User> findUserList(User User,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<User> list=this.getSqlSession().selectList("UserSql.get", User,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	/**
	 * 获取账号昵称头像
	 * @param ulist
	 * @return
	 */
	public List<User> getuserhead(List<User> ulist){
		DataSourceContextHolder.setDbType("0");
		List<User> list=this.getSqlSession().selectList("UserSql.getuserhead", ulist);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<User> findUserList(User User) {
		DataSourceContextHolder.setDbType("0");
		List<User> list=this.getSqlSession().selectList("UserSql.get", User);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findUserCount(User User) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("UserSql.count", User);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertUser(User User) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("UserSql.insert", User);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteUser(User User) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("UserSql.delete", User);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateUser(User User) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("UserSql.update", User);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	
	public int updateUserPwdByOpenId(User User){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("UserSql.updateUserPwdByOpenId", User);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateUserStatus(User User) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("UserSql.updatestatus" , User);
		DataSourceContextHolder.clearDbType();
		return in;
	}
}
