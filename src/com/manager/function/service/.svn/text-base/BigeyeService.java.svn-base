package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.Bigeye;

@Service
public interface BigeyeService {

	public List<Bigeye> findBigeyeList(Bigeye Bigeye,int pageNo,int pageSize);
	
	public int findBigeyeCount(Bigeye Bigeye);
	
	public int updateBigeye(Bigeye Bigeye);
	
	public int updateBigeyeStatus(Bigeye Bigeye);
	
	public int deleteBigeye(String id);
	
	public int insertBigeye(Bigeye Bigeye);
	
	public List<Bigeye> findBigeyeList();
	
	public Bigeye findBigeyeOne(String id);
	
	public Map getBigEyeList(HttpServletRequest request);
	
	public Map getMainImg(HttpServletRequest request);
	
}
