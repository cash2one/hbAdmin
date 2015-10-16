package com.manager.function.daoimpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.BigeyeDao;
import com.manager.function.entity.Bigeye;
import com.manager.util.DataSourceContextHolder;

public class BigeyeDaoImpl extends SqlSessionDaoSupport implements BigeyeDao {
	
	public List<Bigeye> findBigeyeList(Bigeye Bigeye,int pageNo,int pageSize){
		DataSourceContextHolder.setDbType("0");
		List<Bigeye> list=this.getSqlSession().selectList("BigeyeSql.get", Bigeye,new RowBounds(pageNo,pageSize));
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Bigeye> findBigeyeList(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		List<Bigeye> list=this.getSqlSession().selectList("BigeyeSql.get2", Bigeye);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	public int findBigeyeCount(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().selectOne("BigeyeSql.count", Bigeye);
		DataSourceContextHolder.clearDbType();
		return in;
	}
	
	public int insertBigeye(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().insert("BigeyeSql.insert", Bigeye);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public int deleteBigeye(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().delete("BigeyeSql.delete", Bigeye);
		DataSourceContextHolder.clearDbType();
		return in;
	}


	public int updateBigeye(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("BigeyeSql.update", Bigeye);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	
	public int updateBigeyeStatus(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		int in=this.getSqlSession().update("BigeyeSql.updatestatus" , Bigeye);
		DataSourceContextHolder.clearDbType();
		return in;
	}

	public List<Bigeye> getBigEyeList(Bigeye Bigeye) {
		DataSourceContextHolder.setDbType("0");
		List<Bigeye> list=this.getSqlSession().selectList("BigeyeSql.getBigEyeList", Bigeye);
		DataSourceContextHolder.clearDbType();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
