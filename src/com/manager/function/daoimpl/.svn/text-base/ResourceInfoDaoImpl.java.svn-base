package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.ResourceInfoDao;
import com.manager.function.entity.ResourceInfo;
import com.manager.util.DataSourceContextHolder;

public class ResourceInfoDaoImpl extends SqlSessionDaoSupport implements ResourceInfoDao {
	
	public List<ResourceInfo> findResourceInfoList(ResourceInfo ResourceInfo,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.get", ResourceInfo,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<ResourceInfo> findResourceInfoList(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.get", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<ResourceInfo> checkboxResourceInfoList(ResourceInfo ResourceInfo){
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.get2",ResourceInfo);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public  List<ResourceInfo>  get_check_info(ResourceInfo ResourceInfo){
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.get_check_info",ResourceInfo);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<ResourceInfo> NoAbolish_ResourceInfoList(){
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.getNoAbolish");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	
	public int findResourceInfoCount(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("ResourceInfoSql.count", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertResourceInfo(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("ResourceInfoSql.insert", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertBatchResourceInfo(List<ResourceInfo> ResourceInfoList,int n){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("ResourceInfoSql.batch_insert_"+n, ResourceInfoList);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int deleteBatchResourceInfo(List<ResourceInfo> ResourceInfoList,int n){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("ResourceInfoSql.batch_delete_"+n, ResourceInfoList);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteResourceInfo(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("ResourceInfoSql.delete", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int delete2ResourceInfo(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("ResourceInfoSql.delete2", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int deleteResourceInfoByResource(ResourceInfo ResourceInfo){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("ResourceInfoSql.delete_by_resource_id", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateResourceInfo(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("ResourceInfoSql.update", ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateResourceInfoStatus(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("ResourceInfoSql.updatestatus" , ResourceInfo);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public List<ResourceInfo> publicresource(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.publicresource",ResourceInfo);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<ResourceInfo> publicresource1(ResourceInfo ResourceInfo) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.publicresource1",ResourceInfo);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<ResourceInfo> findByResourceId(String resource_id) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.findByResourceId",resource_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	public int getResLanguage(String resource_id)
	{
		DataSourceContextHolder.setDbType("0");
		int list=this.getSqlSession().selectOne("ResourceInfoSql.gerResLanguage",resource_id);
		DataSourceContextHolder.clearDbType();
		return list;
	}
	public List<ResourceInfo> findByResourceId1(String resource_id) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.findByResourceId1",resource_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			if (list.get(0)==null) {
				return null;
			}
			return list;
		}
		return null;
	}

	public List<ResourceInfo> findPropertyId(String resource_id) {
		DataSourceContextHolder.setDbType("0");
		List<ResourceInfo> list=this.getSqlSession().selectList("ResourceInfoSql.findPropertyId",resource_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
