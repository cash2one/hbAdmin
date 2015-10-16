package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.ResourceTypeDao;
import com.manager.function.entity.ResourceType;
import com.manager.util.DataSourceContextHolder;

public class ResourceTypeDaoImpl extends SqlSessionDaoSupport implements ResourceTypeDao {
	
	public List<ResourceType> findResourceTypeList(ResourceType ResourceType,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<ResourceType> list=this.getSqlSession().selectList("ResourceTypeSql.get", ResourceType,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<ResourceType> findResourceTypeList(ResourceType ResourceType) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceType> list=this.getSqlSession().selectList("ResourceTypeSql.get2", ResourceType);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<ResourceType> NoAbolish_ResourceTypeList(){
		DataSourceContextHolder.setDbType("0");
		List<ResourceType> list=this.getSqlSession().selectList("ResourceTypeSql.getNoAbolish");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	
	public int findResourceTypeCount(ResourceType ResourceType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("ResourceTypeSql.count", ResourceType);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertResourceType(ResourceType ResourceType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("ResourceTypeSql.insert", ResourceType);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteResourceType(ResourceType ResourceType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("ResourceTypeSql.delete", ResourceType);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateResourceType(ResourceType ResourceType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("ResourceTypeSql.update", ResourceType);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateResourceTypeStatus(ResourceType ResourceType) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("ResourceTypeSql.updatestatus" , ResourceType);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
