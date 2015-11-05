package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.TopicTypeDao;
import com.manager.function.entity.TopicType;
import com.manager.function.service.TopicTypeService;
import com.manager.init.InitDataPool;
import com.manager.util.Constant;

public class TopicTypeServiceImpl implements TopicTypeService {
	
	private Logger logger = Logger.getLogger(TopicTypeServiceImpl.class);
	
	@Autowired
	private TopicTypeDao TopicTypeDao;
	private InitDataPool initDataPool;

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public int deleteTopicType(String id) {
		TopicType TopicType=new TopicType();
		TopicType.setId(id);
		return this.TopicTypeDao.deleteTopicType(TopicType);
	}

	public int findTopicTypeCount(TopicType TopicType) {
		return this.TopicTypeDao.findTopicTypeCount(TopicType);
	}

	public List<TopicType> findTopicTypeList(TopicType TopicType, int pageNo, int pageSize) {
		return this.TopicTypeDao.findTopicTypeList(TopicType, pageNo, pageSize);
	}

	public List<TopicType> findTopicTypeList() {
		return this.TopicTypeDao.findTopicTypeList(null);
	}
	
	public List<TopicType> NoAbolish_TopicTypeList(HttpServletRequest request){
		List<TopicType> TopicTypeList=this.TopicTypeDao.NoAbolish_TopicTypeList();
		request.setAttribute("noabolish_topictype", TopicTypeList);
		return TopicTypeList;
	}

	public int insertTopicType(TopicType TopicType) {
		return this.TopicTypeDao.insertTopicType(TopicType);
	}

	public int updateTopicType(TopicType TopicType) {
		return this.TopicTypeDao.updateTopicType(TopicType);
	}

	public int updateTopicTypeStatus(TopicType TopicType) {
		return this.TopicTypeDao.updateTopicTypeStatus(TopicType);
	}
	
	public TopicType findTopicTypeOne(String id){
		TopicType TopicType=new TopicType();
		TopicType.setId(id);
		List<TopicType> TopicTypeList=this.TopicTypeDao.findTopicTypeList(TopicType);
		if(TopicTypeList!=null && TopicTypeList.size()>0){
			return TopicTypeList.get(0);
		}
		return null;
	}
	
	public int checkTopicTypeName(String name){
		TopicType TopicType=new TopicType();
		TopicType.setName(name);
		List<TopicType> bewsTypeList=this.TopicTypeDao.findTopicTypeList(TopicType);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkTopicTypeName(String id,String name){
		TopicType TopicType=new TopicType();
		TopicType.setName(name);
		List<TopicType> bewsTypeList=this.TopicTypeDao.findTopicTypeList(TopicType);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					TopicType bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}
	
	
	public int checkTopicTypeSort(String sort){
		TopicType TopicType=new TopicType();
		TopicType.setSort(sort);
		List<TopicType> bewsTypeList=this.TopicTypeDao.findTopicTypeList(TopicType);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkTopicTypeSort(String id,String sort){
		TopicType TopicType=new TopicType();
		TopicType.setSort(sort);
		List<TopicType> bewsTypeList=this.TopicTypeDao.findTopicTypeList(TopicType);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					TopicType bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}


	/**
	 * @return the TopicTypeDao
	 */
	public TopicTypeDao getTopicTypeDao() {
		return TopicTypeDao;
	}

	/**
	 * @param TopicTypeDao the TopicTypeDao to set
	 */
	public void setTopicTypeDao(TopicTypeDao TopicTypeDao) {
		this.TopicTypeDao = TopicTypeDao;
	}

	public Map getTopicType(HttpServletRequest request) {
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		String result  = "0";
		String message = "";
		List<TopicType> ls = new ArrayList<TopicType>();
		Map hsm = new LinkedHashMap();
		try{
			 ls = this.TopicTypeDao.getTopicType();
			 result = "1";
			 message = initDataPool.getSP("2-4-214");
		}catch (Exception e) {
			e.printStackTrace();
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		hsm.put("version", Constant.version);
		hsm.put("result", result);
		hsm.put("message", message);
		hsm.put("data", ls);
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("TopicTypeServiceImpl.getTopicType执行了"+diff+"毫秒");
        
		return hsm;
	}
	
}
