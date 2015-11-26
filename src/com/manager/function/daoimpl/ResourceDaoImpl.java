package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.ResourceDao;
import com.manager.function.entity.Resource;
import com.manager.util.DataSourceContextHolder;

public class ResourceDaoImpl extends SqlSessionDaoSupport implements ResourceDao {
	
	public List<Resource> findResourceList(Resource Resource,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		String str="_2";
		if((Resource.getLevel_id()!=null && !"".equals(Resource.getLevel_id())) ||
		   (Resource.getProperty_id()!=null && !"".equals(Resource.getProperty_id()))	||
		   (Resource.getLanguage_level()!=null && !"".equals(Resource.getLanguage_level())) ){
			   str="_1";
		  }
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.get"+str, Resource,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Resource> findResourceWeekdayList(Resource Resource,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.get_weekday", Resource,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Resource> findResourceList(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.get2", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public Resource findUseResourceInfo(Resource Resource){
		DataSourceContextHolder.setDbType("0");
		Resource resource=this.getSqlSession().selectOne("ResourceSql.get_use_info", Resource);
		DataSourceContextHolder.clearDbType();
		if(resource!=null){
			return resource;
		}
		return null;
	}

	
	public int findResourceCount(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		String str="_2";
		if((Resource.getLevel_id()!=null && !"".equals(Resource.getLevel_id())) ||
		   (Resource.getProperty_id()!=null && !"".equals(Resource.getProperty_id()))	||
		   (Resource.getLanguage_level()!=null && !"".equals(Resource.getLanguage_level())) ){
			   str="_1";
		  }
		int in=this.getSqlSession().selectOne("ResourceSql.count"+str, Resource);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int findResourceWeekdayCount(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("ResourceSql.count_weekday", Resource);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public String getid(){
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("ResourceSql.getid");
		DataSourceContextHolder.clearDbType();
		return in+"";
	}
	
	public int insertResource(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("ResourceSql.insert", Resource);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteResource(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("ResourceSql.delete", Resource);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateResource(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("ResourceSql.update", Resource);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateResourceStatus(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("ResourceSql.updatestatus" , Resource);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public List<Resource> publicresource(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.publicresource", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Resource> publicresource1(String  baby_id) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.publicresource1", baby_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> learnplan(String  baby_id) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.learnplan", baby_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> getlearnplan(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.getlearnplan", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> getpublicresource(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.getpublicresource", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> search(String resource_content) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.search", resource_content);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> buquan(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.buquan", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Resource> buquanpre(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.buquanpre", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Resource> buquan1(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.buquan1", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> xuanshu(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.xuanshu", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> fList(String user_id) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.fList", user_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> huibenbuquan(Resource Resource) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.huibenbuquan", Resource);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<Resource> huiben(String baby_id) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.huiben", baby_id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public Resource getPlay() {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.getPlay");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public Resource getOneRes(String id) {
		DataSourceContextHolder.setDbType("0");
		List<Resource> list=this.getSqlSession().selectList("ResourceSql.getOneRes",id);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
