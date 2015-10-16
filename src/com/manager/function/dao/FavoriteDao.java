package com.manager.function.dao;

import com.manager.function.entity.Favorite;

public interface FavoriteDao {
	
	public void add(Favorite f);
	
	public void delete(String user_id);
	
	public void delete1(Favorite f);
	
	public int findNum(Favorite f);

}
