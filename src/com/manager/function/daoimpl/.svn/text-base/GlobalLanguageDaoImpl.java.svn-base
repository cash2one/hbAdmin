package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.GlobalLanguageDao;
import com.manager.function.entity.GlobalLanguage;
import com.manager.util.DataSourceContextHolder;

public class GlobalLanguageDaoImpl extends SqlSessionDaoSupport implements GlobalLanguageDao {
	
	public List<GlobalLanguage> findGlobalLanguageList(GlobalLanguage GlobalLanguage,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLanguage> list=this.getSqlSession().selectList("GlobalLanguageSql.get", GlobalLanguage,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<GlobalLanguage> findGlobalLanguageList(GlobalLanguage GlobalLanguage) {
		DataSourceContextHolder.setDbType("0");
		List<GlobalLanguage> list=this.getSqlSession().selectList("GlobalLanguageSql.get2", GlobalLanguage);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<GlobalLanguage> NoAbolish_GlobalLanguageList(){
		DataSourceContextHolder.setDbType("0");
		List<GlobalLanguage> list=this.getSqlSession().selectList("GlobalLanguageSql.getNoAbolish");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	
	public int findGlobalLanguageCount(GlobalLanguage GlobalLanguage) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("GlobalLanguageSql.count", GlobalLanguage);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertGlobalLanguage(GlobalLanguage GlobalLanguage) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("GlobalLanguageSql.insert", GlobalLanguage);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteGlobalLanguage(GlobalLanguage GlobalLanguage) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("GlobalLanguageSql.delete", GlobalLanguage);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateGlobalLanguage(GlobalLanguage GlobalLanguage) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("GlobalLanguageSql.update", GlobalLanguage);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateGlobalLanguageStatus(GlobalLanguage GlobalLanguage) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("GlobalLanguageSql.updatestatus" , GlobalLanguage);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
