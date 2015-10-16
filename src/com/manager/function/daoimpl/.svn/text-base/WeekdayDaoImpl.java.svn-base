package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.WeekdayDao;
import com.manager.function.entity.Weekday;
import com.manager.util.DataSourceContextHolder;

public class WeekdayDaoImpl extends SqlSessionDaoSupport implements WeekdayDao {
	
	public List<Weekday> findWeekdayList(Weekday Weekday,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<Weekday> list=this.getSqlSession().selectList("WeekdaySql.get", Weekday,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	
	public List<Weekday> findWeekdayList(Weekday Weekday) {
		DataSourceContextHolder.setDbType("0");
		List<Weekday> list=this.getSqlSession().selectList("WeekdaySql.get2", Weekday);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Weekday> SelectWeekdayList(){
		DataSourceContextHolder.setDbType("0");
		List<Weekday> list=this.getSqlSession().selectList("WeekdaySql.get_select");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public Weekday findWeekday(Weekday Weekday){
		DataSourceContextHolder.setDbType("0");
		Weekday w=this.getSqlSession().selectOne("WeekdaySql.get2", Weekday);
		DataSourceContextHolder.clearDbType();
		return w;
	}
	
	public int findWeekdayCount(Weekday Weekday) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("WeekdaySql.count", Weekday);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertWeekday(Weekday Weekday) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("WeekdaySql.insert", Weekday);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int updateWeekday(Weekday Weekday) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("WeekdaySql.update", Weekday);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteWeekday(Weekday Weekday) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("WeekdaySql.delete", Weekday);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
