package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.BigeyeModuleDao;
import com.manager.function.entity.BigeyeModule;
import com.manager.util.DataSourceContextHolder;

public class BigeyeModuleDaoImpl extends SqlSessionDaoSupport implements BigeyeModuleDao {
	
	public List<BigeyeModule> findBigeyeModuleList(BigeyeModule BigeyeModule,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<BigeyeModule> list=this.getSqlSession().selectList("BigeyeModuleSql.get", BigeyeModule,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<BigeyeModule> findBigeyeModuleList(BigeyeModule BigeyeModule) {
		DataSourceContextHolder.setDbType("0");
		List<BigeyeModule> list=this.getSqlSession().selectList("BigeyeModuleSql.get2", BigeyeModule);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<BigeyeModule> NoAbolish_BigeyeModuleList(){
		DataSourceContextHolder.setDbType("0");
		List<BigeyeModule> list=this.getSqlSession().selectList("BigeyeModuleSql.getNoAbolish");
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	
	public int findBigeyeModuleCount(BigeyeModule BigeyeModule) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("BigeyeModuleSql.count", BigeyeModule);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertBigeyeModule(BigeyeModule BigeyeModule) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("BigeyeModuleSql.insert", BigeyeModule);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteBigeyeModule(BigeyeModule BigeyeModule) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("BigeyeModuleSql.delete", BigeyeModule);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateBigeyeModule(BigeyeModule BigeyeModule) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("BigeyeModuleSql.update", BigeyeModule);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateBigeyeModuleStatus(BigeyeModule BigeyeModule) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("BigeyeModuleSql.updatestatus" , BigeyeModule);
		DataSourceContextHolder.clearDbType();
		return in;
	}

}
