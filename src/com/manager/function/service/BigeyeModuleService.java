package com.manager.function.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.BigeyeModule;

@Service
public interface BigeyeModuleService {

	public List<BigeyeModule> findBigeyeModuleList(BigeyeModule BigeyeModule,int pageNo,int pageSize);
	
	public int findBigeyeModuleCount(BigeyeModule BigeyeModule);
	
	public int updateBigeyeModule(BigeyeModule BigeyeModule);
	
	public int updateBigeyeModuleStatus(BigeyeModule BigeyeModule);
	
	public int deleteBigeyeModule(String id);
	
	public int insertBigeyeModule(BigeyeModule BigeyeModule);
	
	public List<BigeyeModule> findBigeyeModuleList();
	
	public BigeyeModule findBigeyeModuleOne(String id);
	
	public int checkBigeyeModuleName(String name);
	
	public int checkBigeyeModuleName(String id,String name);
	
	public List<BigeyeModule> NoAbolish_BigeyeModuleList(HttpServletRequest request);
	
}
