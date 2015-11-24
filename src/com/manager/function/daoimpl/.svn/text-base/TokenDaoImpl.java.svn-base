package com.manager.function.daoimpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.manager.function.dao.TokenDao;
import com.manager.function.entity.Medal;
import com.manager.function.entity.Token;
import com.manager.util.DataSourceContextHolder;

public class TokenDaoImpl extends SqlSessionDaoSupport implements TokenDao{

	public void add(Token token)
	{
		DataSourceContextHolder.setDbType("0");
		this.getSqlSession().insert("TokenSql.add", token);
		DataSourceContextHolder.clearDbType();
	}
	
	public int getTokenCount(Token token)
	{
		DataSourceContextHolder.setDbType("0");
		List<Token> ls = this.getSqlSession().selectList("TokenSql.find", token);
		DataSourceContextHolder.clearDbType();
		
		return ls.size();
	}
	
	public List<Token> getToken()
	{
		DataSourceContextHolder.setDbType("0");
		List<Token> ls = this.getSqlSession().selectList("TokenSql.findall");
		DataSourceContextHolder.clearDbType();
		if(ls.size() > 0)
			return ls;
		
		return null;
	}
}
