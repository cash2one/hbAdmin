package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.WeekdayResourceDao;
import com.manager.function.entity.WeekdayResource;
import com.manager.util.DataSourceContextHolder;

public class WeekdayResourceDaoImpl extends SqlSessionDaoSupport implements WeekdayResourceDao {
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int pageNo,int pageSize,int n){
		DataSourceContextHolder.setDbType("0");
		List<WeekdayResource> list=this.getSqlSession().selectList("WeekdayResourceSql.get_"+n, WeekdayResource,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource,int n){
		DataSourceContextHolder.setDbType("0");
		List<WeekdayResource> list=this.getSqlSession().selectList("WeekdayResourceSql.get_"+n, WeekdayResource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<WeekdayResource> findWeekdayResourceList(WeekdayResource WeekdayResource) {
		DataSourceContextHolder.setDbType("0");
		List<WeekdayResource> list=this.getSqlSession().selectList("WeekdayResourceSql.get2", WeekdayResource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<WeekdayResource> SelectWeekdayResourceList(){
		DataSourceContextHolder.setDbType("0");
		List<WeekdayResource> list=this.getSqlSession().selectList("WeekdayResourceSql.select_weekday");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public WeekdayResource findWeekday(WeekdayResource WeekdayResource){
		DataSourceContextHolder.setDbType("0");
		WeekdayResource w=this.getSqlSession().selectOne("WeekdayResourceSql.get_weekday", WeekdayResource);
		DataSourceContextHolder.clearDbType();
		return w;
	}
	
	public int findWeekdayResourceCount(WeekdayResource WeekdayResource,int n) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("WeekdayResourceSql.count_"+n, WeekdayResource);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertWeekdayResource(WeekdayResource WeekdayResource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("WeekdayResourceSql.insert", WeekdayResource);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int batchInsertWeekdayResource(List<WeekdayResource> WeekdayResourceList){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("WeekdayResourceSql.batch_insert", WeekdayResourceList);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteWeekdayResource(WeekdayResource WeekdayResource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("WeekdayResourceSql.delete", WeekdayResource);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void add(WeekdayResource WeekdayResource) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("WeekdayResourceSql.add", WeekdayResource);
		DataSourceContextHolder.clearDbType();
	}

	public int getId() {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("WeekdayResourceSql.getId");
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public void update(WeekdayResource WeekdayResource) {
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().update("WeekdayResourceSql.update", WeekdayResource);
		DataSourceContextHolder.clearDbType();
	}

	public int wdCount(WeekdayResource WeekdayResource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("WeekdayResourceSql.wdCount",WeekdayResource);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
