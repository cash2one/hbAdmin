package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.manager.function.dao.FavoriteDao;
import com.manager.function.dao.GlobalScoreDao;
import com.manager.function.dao.ResourceDao;
import com.manager.function.dao.ResourceInfoDao;
import com.manager.function.dao.ScoreLogDao;
import com.manager.function.dao.SearchKeywordDao;
import com.manager.function.dao.SearchLogsDao;
import com.manager.function.dao.UserBillingDao;
import com.manager.function.dao.WeekdayDao;
import com.manager.function.dao.WeekdayResourceDao;
import com.manager.function.entity.Favorite;
import com.manager.function.entity.GlobalScore;
import com.manager.function.entity.Resource;
import com.manager.function.entity.ResourceInfo;
import com.manager.function.entity.ScoreLog;
import com.manager.function.entity.SearchKeyword;
import com.manager.function.entity.SearchLogs;
import com.manager.function.entity.UserBilling;
import com.manager.function.entity.Weekday;
import com.manager.function.entity.WeekdayResource;
import com.manager.function.service.ResourceService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.EncoderHandler;
import com.manager.util.Utils;
import com.manager.util.Xml;

public class ResourceServiceImpl implements ResourceService {
	
	private Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private ResourceInfoDao resourceInfoDao;
	@Autowired
	private WeekdayDao weekdayDao;
	@Autowired
	private WeekdayResourceDao weekdayResourceDao;
	
	private InitDataPool initDataPool;
	
	private SearchLogsDao searchLogsDao;
	
	private SearchKeywordDao searchKeywordDao;
	
	private GlobalScoreDao globalScoreDao;
	
	private ScoreLogDao scoreLogDao;
	
	private UserBillingDao userBillingDao;
	
	private FavoriteDao favoriteDao;
	
	public FavoriteDao getFavoriteDao() {
		return favoriteDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public UserBillingDao getUserBillingDao() {
		return userBillingDao;
	}

	public void setUserBillingDao(UserBillingDao userBillingDao) {
		this.userBillingDao = userBillingDao;
	}

	public GlobalScoreDao getGlobalScoreDao() {
		return globalScoreDao;
	}

	public void setGlobalScoreDao(GlobalScoreDao globalScoreDao) {
		this.globalScoreDao = globalScoreDao;
	}

	public ScoreLogDao getScoreLogDao() {
		return scoreLogDao;
	}

	public void setScoreLogDao(ScoreLogDao scoreLogDao) {
		this.scoreLogDao = scoreLogDao;
	}

	public SearchLogsDao getSearchLogsDao() {
		return searchLogsDao;
	}

	public void setSearchLogsDao(SearchLogsDao searchLogsDao) {
		this.searchLogsDao = searchLogsDao;
	}

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	@Transactional
	public int deleteResource(String id) {
		ResourceInfo ResourceInfo=new ResourceInfo();
		ResourceInfo.setResource_id(id);
		this.resourceInfoDao.delete2ResourceInfo(ResourceInfo);
		Resource Resource=new Resource();
		Resource.setId(id);
		int in=this.resourceDao.deleteResource(Resource);
		if(in>0){
			return in;
		}
		return 0;
	}

	public int findResourceCount(Resource Resource) {
		return this.resourceDao.findResourceCount(Resource);
	}

	public List<Resource> findResourceList(Resource Resource, int pageNo, int pageSize) {
		return this.resourceDao.findResourceList(Resource, pageNo, pageSize);
	}
	
	public List<Resource> findResourceWeekdayList(Resource Resource,int pageNo,int pageSize){
		return this.resourceDao.findResourceWeekdayList(Resource, pageNo, pageSize);
	}
	
	public int findResourceWeekdayCount(Resource Resource){
		return this.resourceDao.findResourceWeekdayCount(Resource);
	}
	
	public Resource findUseResourceInfo(String id){
		Resource Resource=new Resource();
		Resource.setId(id);
		return this.resourceDao.findUseResourceInfo(Resource);
	}

	public List<Resource> findResourceList() {
		Resource Resource=new Resource();
		Resource.setResource_status("1");
		return this.resourceDao.findResourceList(Resource);
	}

	@Transactional
	public int insertResource(Resource Resource,List<ResourceInfo> resourceInfoList1,List<ResourceInfo> resourceInfoList2,List<ResourceInfo> resourceInfoList3) {
		int in=this.resourceDao.insertResource(Resource);
		if(in>0){
			this.resourceInfoDao.insertBatchResourceInfo(resourceInfoList1,1);
			this.resourceInfoDao.insertBatchResourceInfo(resourceInfoList2,2);
			this.resourceInfoDao.insertBatchResourceInfo(resourceInfoList3,3);
			return in;
		}
		return 0;
	}
	
	public String getid(){
		return this.resourceDao.getid();
	}
	

	@Transactional
	public int updateResource(Resource Resource,List<ResourceInfo> resourceInfoList1,List<ResourceInfo> resourceInfoList2,List<ResourceInfo> resourceInfoList3
			,List<ResourceInfo> deleteInfoList) {
		int in=this.resourceDao.updateResource(Resource);
		if(in>0){
			if(resourceInfoList1!=null && resourceInfoList1.size()>0){
				this.resourceInfoDao.insertBatchResourceInfo(resourceInfoList1,1);
			}
			if(resourceInfoList2!=null && resourceInfoList2.size()>0){
				this.resourceInfoDao.insertBatchResourceInfo(resourceInfoList2,2);
			}
			if(resourceInfoList3!=null && resourceInfoList3.size()>0){
				this.resourceInfoDao.insertBatchResourceInfo(resourceInfoList3,3);
			}
			if(deleteInfoList!=null && deleteInfoList.size()>0){
				this.resourceInfoDao.deleteBatchResourceInfo(deleteInfoList, 1);
			}
			return in;
		}
		return 0;
	}

	public int updateResourceStatus(Resource Resource) {
		return this.resourceDao.updateResourceStatus(Resource);
	}
	
	public Resource findResourceOne(String id){
		Resource Resource=new Resource();
		Resource.setId(id);
		List<Resource> ResourceList=this.resourceDao.findResourceList(Resource);
		if(ResourceList!=null && ResourceList.size()>0){
			return ResourceList.get(0);
		}
		return null;
	}
	
	public int checkResourceResourceContent(String resource_content,String resource_type_id){
		Resource Resource=new Resource();
		Resource.setResource_content(resource_content);
		Resource.setResource_type_id(resource_type_id);
		List<Resource> bewsTypeList=this.resourceDao.findResourceList(Resource);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkResourceResourceContent(String id,String resource_content,String resource_type_id){
		Resource Resource=new Resource();
		Resource.setResource_content(resource_content);
		Resource.setResource_type_id(resource_type_id);
		List<Resource> bewsTypeList=this.resourceDao.findResourceList(Resource);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					Resource bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}


	/**
	 * @return the ResourceDao
	 */
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	/**
	 * @param ResourceDao the ResourceDao to set
	 */
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * @return the resourceInfoDao
	 */
	public ResourceInfoDao getResourceInfoDao() {
		return resourceInfoDao;
	}

	/**
	 * @param resourceInfoDao the resourceInfoDao to set
	 */
	public void setResourceInfoDao(ResourceInfoDao resourceInfoDao) {
		this.resourceInfoDao = resourceInfoDao;
	}
	
	
	public JSONObject getResourceByID(String id) throws Exception{
		Resource r=this.findResourceOne(id);
		if(r!=null && r.getId()!=null){
			List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
			String type_id=r.getResource_type_id();
			
			JSONObject obj = new JSONObject();
			obj.put("resource_id", r.getId());
			obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
			obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
			obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
			obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
			obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
			obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
			obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
			obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
			obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
			
			obj.put("status", "0");
			if(type_id.equals("1")){
				obj.put("resource_type_id","1");
				if(list!=null){
					obj.put("url", list.get(0).getResource_url()!=null?tobereplace(list.get(0).getResource_url(), 0):"");
					obj.put("category", list.get(0).getResource_type());
					obj.put("lyrics", list.get(0).getResource_lyrics()!=null?tobereplace(list.get(0).getResource_lyrics(), 0):"");
				}else{
					obj.put("url", "");
					obj.put("category", "0");
					obj.put("lyrics", "");
				}
			}else if(type_id.equals("2")){
				obj.put("resource_type_id","2");
				JSONArray objModel = new JSONArray();
				if(list!=null&&!list.isEmpty()){
					for(ResourceInfo ri:list){
							JSONObject jb = new JSONObject();
							jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
							jb.put("category", ri.getResource_type());
							objModel.add(jb);
					}
				}else{
					JSONObject jb = new JSONObject();
					obj.put("url", "");
					obj.put("category","0");
					objModel.add(jb);
				}
				obj.put("data", !objModel.isEmpty()?objModel:"");
			}else if(type_id.equals("4")){
				obj.put("resource_type_id","3");
				JSONArray objModel = new JSONArray();
				if(list!=null&&!list.isEmpty()){
					for(ResourceInfo ri:list){
							JSONObject jb = new JSONObject();
							jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
							jb.put("category", ri.getResource_type());
							objModel.add(jb);
					}
				}else{
					JSONObject jb = new JSONObject();
					obj.put("url", "");
					obj.put("category","0");
					objModel.add(jb);
				}
				obj.put("data", !objModel.isEmpty()?objModel:"");
			}else if(type_id.equals("5")){
				obj.put("resource_type_id","4");
				if (list != null && !list.isEmpty()) {
					obj.put("url",list.get(0).getResource_url() != null ? CollectionUtil
											.toplaybereplace(list.get(0).getResource_url(), 0): "");
					obj.put("category", list.get(0).getResource_type());
				} else {
					obj.put("url", "");
					obj.put("category", "0");
				}
			}
			return obj;
			
		}
		return null;
	}

	@Transactional
	public Map publicresource(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		try{
			String baby_id = (String) request.getParameter("baby_id");
			String user_id = (String) request.getParameter("uid");
			
			boolean flag = false;
			if(baby_id==null||"".equals(baby_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-211");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("week", week+"");
		        hsm.put("data", "");
		        hsm.put("datasign", "");
				return hsm;
			}else{
				flag = true;
			}
			
			if(flag){
				
				List<Resource> ls = new ArrayList<Resource>();
				
				//获取配置
				Weekday wd = new Weekday();
				wd.setStart_date(date);
				wd.setEnd_date(date);
				
				Weekday weekday = this.weekdayDao.findWeekday(wd);
				if(weekday==null){
					result = "2";
					message = initDataPool.getSP("2-4-221");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("date", date);
					hsm.put("week", week+"");
					hsm.put("data", "");
					hsm.put("datasign","");
					return hsm;
				}
				
				//本周推荐LOG 中的数量
				WeekdayResource wrModel = new WeekdayResource();
				wrModel.setBaby_id(baby_id);
				wrModel.setWeekday_id(weekday.getId());
				int count = this.weekdayResourceDao.wdCount(wrModel);
				
				boolean res1 = false;
				
				if(count>0){
					res1 = false;
					Resource r = new Resource();
					r.setBaby_id(baby_id);
					r.setWeekday_id(weekday.getId());
					ls = this.resourceDao.getpublicresource(r);
				}else{
					res1 = true;;
					List<Resource> lsModel = this.resourceDao.publicresource1(baby_id);
					
					List<Resource> ls1 = new ArrayList<Resource>();
					List<Resource> ls2 = new ArrayList<Resource>();
					List<Resource> ls3 = new ArrayList<Resource>();
					List<Resource> ls4 = new ArrayList<Resource>();
					
					//分类
					if(lsModel!=null){
					for(Resource r : lsModel){
						if(r.getResource_type_id().equals("1")){
							ls1.add(r);
						}else if(r.getResource_type_id().equals("2")){
							ls2.add(r);
						}else if(r.getResource_type_id().equals("4")){
							ls3.add(r);
						}else if(r.getResource_type_id().equals("5")){
							ls4.add(r);
						}
					}
					}
					
					String config = weekday.getWeekday_config();
					JSONObject jo = JSONObject.fromObject(config);
					
					int listen = Integer.parseInt(jo.getString("listen"));
					int see = Integer.parseInt(jo.getString("watch"));
					int read = Integer.parseInt(jo.getString("read"));
					int play = Integer.parseInt(jo.getString("play"));
					
					String listen_id = "";
					String see_id = "";
					String read_id = "";
					String play_id = "";
					
					//生成阅读计划list
						for(int a = 0; a< listen;a++){
							if(ls1.isEmpty()){
								int len = listen - a;
								Resource r = new Resource();
								r.setId(listen_id);
								r.setResource_type_id("1");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquan1(r);
								if(list!=null){
									for(int c = 0;c<list.size();c++){
										Resource re = list.get(c);
										ls.add(re);
									}
								} 
								break;
							}
							Random rand = new Random();
							Resource re = ls1.get(rand.nextInt(ls1.size()));
							if(a==0){
								listen_id += re.getId();
							}else {
								listen_id +=","+re.getId();
							}
							ls.add(re);
							ls1.remove(re);
						}
					
						for(int a = 0; a< see;a++){
							if(ls2.isEmpty()){
								int len = see - a;
								Resource r = new Resource();
								r.setId(see_id);
								r.setResource_type_id("2");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquan1(r);
								if(list!=null){
									for(int c = 0;c<list.size();c++){
										Resource re = list.get(c);
										ls.add(re);
									}
								}
								break;
							}
							Random rand = new Random();
							Resource re = ls2.get(rand.nextInt(ls2.size()));
							if(a==0){
								see_id += re.getId();
							}else {
								see_id +=","+re.getId();
							}
							ls.add(re);
							ls2.remove(re);
						}
						for(int a = 0; a< read;a++){
							if(ls3.isEmpty()){
								int len = read - a;
								Resource r = new Resource();
								r.setId(read_id);
								r.setResource_type_id("4");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquan1(r);
								if(list!=null){
									for(int c = 0;c<list.size();c++){
										Resource re = list.get(c);
										ls.add(re);
									}
								}
								break;
							}
							Random rand = new Random();
							Resource re = ls3.get(rand.nextInt(ls3.size()));
							if(a==0){
								read_id += re.getId();
							}else {
								read_id +=","+re.getId();
							}
							ls.add(re);
							ls3.remove(re);
						}
						for(int a = 0; a< play;a++){
							if(ls4.isEmpty()){
								break;
							}
							Random rand = new Random();
							Resource re = ls4.get(rand.nextInt(ls4.size()));
							if(a==0){
								play_id += re.getId();
							}else {
								play_id +=","+re.getId();
							}
							ls.add(re);
							ls4.remove(re);
						}
				}
				
				if(ls!=null&&!ls.isEmpty()){
					JSONArray data1 = new JSONArray();
					JSONArray data2 = new JSONArray();
					JSONArray data3 = new JSONArray();
					JSONArray data4 = new JSONArray();
					for(int i = 0;i<ls.size();i++){
						Resource r = ls.get(i);
						type_id = r.getResource_type_id();
						String ulp_id = "";
						if(res1){
							WeekdayResource wr = new WeekdayResource();
							ulp_id = this.weekdayResourceDao.getId()+"";
							wr.setId(ulp_id);
							wr.setBaby_id(baby_id);
							wr.setResource_id(r.getId());
							wr.setWeekday_id(weekday.getId());
							wr.setStatus("0");
							this.weekdayResourceDao.add(wr);
						}else{
							ulp_id = r.getUlp_id();
						}
						
						Favorite f = new Favorite();
						f.setUser_id(user_id);
						f.setResource_id(r.getId());
						
						int fnum = this.favoriteDao.findNum(f);
						
						List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
						if(type_id.equals("1")){
							JSONObject obj = new JSONObject();
							obj.put("id", ulp_id);
							obj.put("resource_id", r.getId());
							obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
							obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
							obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
							obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
							obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
							obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
							obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
							obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
							obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
							if(list!=null){
								obj.put("url", list.get(0).getResource_url()!=null?tobereplace(list.get(0).getResource_url(), 0):"");
								obj.put("category", list.get(0).getResource_type());
								obj.put("lyrics", list.get(0).getResource_lyrics()!=null?tobereplace(list.get(0).getResource_lyrics(), 0):"");
							}else{
								obj.put("url", "");
								obj.put("category", "0");
								obj.put("lyrics", "");
							}
							obj.put("status", "0");
							obj.put("spend_minute", "");
							obj.put("fstatus", fnum>0?"1":"0");
							data1.add(obj);
						}else if(type_id.equals("2")){
							JSONObject obj = new JSONObject();
							obj.put("id", ulp_id);
							obj.put("resource_id", r.getId());
							obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
							obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
							obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
							obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
							obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
							obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
							obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
							obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
							obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
							JSONArray objModel = new JSONArray();
							if(list!=null&&!list.isEmpty()){
								for(ResourceInfo ri:list){
										JSONObject jb = new JSONObject();
										jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
										jb.put("category", ri.getResource_type());
										objModel.add(jb);
								}
							}else{
								JSONObject jb = new JSONObject();
								obj.put("url", "");
								obj.put("category","0");
								objModel.add(jb);
							}
							obj.put("status", "0");
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("spend_minute", "");
							obj.put("fstatus", fnum>0?"1":"0");
							data2.add(obj);
						}else if(type_id.equals("4")){
							JSONObject obj = new JSONObject();
							obj.put("id", ulp_id);
							obj.put("resource_id", r.getId());
							obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
							obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
							obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
							obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
							obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
							obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
							obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
							obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
							obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
							obj.put("img_book", r.getImg_book()!=null?tobereplace(r.getImg_book(), 0):"");
							obj.put("img_book_size", r.getImg_book_size()!=null?r.getImg_book_size():"");
							JSONArray objModel = new JSONArray();
							if(list!=null&&!list.isEmpty()){
								for(ResourceInfo ri:list){
										JSONObject jb = new JSONObject();
										jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
										jb.put("category", ri.getResource_type());
										objModel.add(jb);
								}
							}else{
								JSONObject jb = new JSONObject();
								obj.put("url", "");
								obj.put("category","0");
								objModel.add(jb);
							}
							obj.put("status", "0");
							obj.put("spend_minute", "");
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("fstatus", fnum>0?"1":"0");
							data3.add(obj);
						}else if(type_id.equals("5")){
							JSONObject obj = new JSONObject();
							obj.put("id", ulp_id);
							obj.put("resource_id", r.getId());
							obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
							obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
							obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
							obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
							obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
							obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
							obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
							obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
							obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
							obj.put("status", "0");
							JSONArray objModel = new JSONArray();
							if(list!=null&&!list.isEmpty()){
								for(ResourceInfo ri:list){
										JSONObject jb = new JSONObject();
										jb.put("url", ri.getResource_url() != null ? CollectionUtil
												.toplaybereplace(ri.getResource_url(), 0): "");
										jb.put("category", ri.getResource_type());
										objModel.add(jb);
								}
							}else{
								JSONObject jb = new JSONObject();
								obj.put("url", "");
								obj.put("category","0");
								objModel.add(jb);
							}
							obj.put("data", objModel);
							obj.put("spend_minute", "");
							obj.put("fstatus", fnum>0?"1":"0");
							data4.add(obj);
						}
						
					}
					data.put("audio", data1);
					data.put("video", data2);
					data.put("books", data3);
					data.put("games", data4);
					result = "1";
					message = initDataPool.getSP("2-4-214");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("date", date);
					hsm.put("week", week+"");
					hsm.put("data", data);
					hsm.put("datasign", EncoderHandler.encodeByMD5(data.toString()));
				}else{
					result = "2";
					message = initDataPool.getSP("2-4-213");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("date", date);
					hsm.put("week", week+"");
					hsm.put("data", "");
					hsm.put("datasign", "");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.publicresource执行了"+diff+"毫秒");
		
		return hsm;
	}
	
	@Transactional
	public Map updatepublicresource(HttpServletRequest request){
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		try{
			String id = (String) request.getParameter("id");
			String spend_minute = (String) request.getParameter("spend_minute");
			String user_id = (String) request.getParameter("uid");
			
			boolean flag = false;
			
			if(id==null||"".equals(id)){
				result = "2";
				message = initDataPool.getSP("2-4-217");
			}else if(spend_minute==null||"".equals(spend_minute)){
				result = "2";
				message = initDataPool.getSP("2-4-218");
			}else{
				flag = true;
			}
			
			if(flag){
				
				WeekdayResource WeekdayResource = new WeekdayResource();
				WeekdayResource.setId(id);
				
				WeekdayResource upModel = this.weekdayResourceDao.findWeekday(WeekdayResource);
				
				if(upModel.getStatus().equals("0")){
					WeekdayResource wr = new WeekdayResource();
					wr.setSpend_minute(spend_minute);
					wr.setStatus("1");
					wr.setId(id);
					this.weekdayResourceDao.update(wr);
					
					//获取积分
					GlobalScore gs = this.globalScoreDao.findOne("1");
					
					String get_score = gs.getScore();
					
					//记录log
					ScoreLog sl = new ScoreLog();
					sl.setGet_score(get_score);
					sl.setSource("1");
					sl.setUser_id(user_id);
					this.scoreLogDao.add(sl);
					
					//用户积分增加
					UserBilling ub = new UserBilling();
					ub.setUser_id(user_id);
					ub.setUser_score(get_score);
					this.userBillingDao.addScore(ub);
					message = initDataPool.getSP("2-4-219");
				}else{
					
					message = initDataPool.getSP("2-4-227");
				}
				result = "1";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			throw new RuntimeException("updatepublicresource---积分增加出现异常");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);

		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.updatepublicresource执行了"+diff+"毫秒");
        
		return hsm;
	}
	
	public String tobereplace(String message, int in) throws Exception {
		String path = ResourceServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF/Config.xml";
		String bereplace = Xml.getXmlTagValue(path, "messageimagebereplace");
		String replace = Xml.getXmlTagValue(path, "messageimagereplace");
		if (in == 0) {
			if(CollectionUtil.checkNull(message)){
			message = message.replaceAll(replace, bereplace);
			}
		}
		if (in == 1) {
			if(CollectionUtil.checkNull(message)){
				message = message.replaceAll(bereplace, replace);
			}
		}
		return message;
	}
	
	public String md5(Map parmsmap,String appkey){
		//签名校验
		StringBuffer urlparmsstr = new StringBuffer();
		parmsmap = Utils.sortMapByKey(parmsmap);
		for (Iterator iter = parmsmap.keySet().iterator(); iter.hasNext();) {
		     String key = (String) iter.next();
		     if("datasign".equals(key) )
		    	 continue;
		    Object values = (Object) parmsmap.get(key);
			urlparmsstr.append(key).append("=").append(values).append("&");
		}
		String urlparmsall = urlparmsstr.substring(0, urlparmsstr.length()-1).toString(); //去掉末尾一个&符号
		
		String serverSign1 = EncoderHandler.encodeByMD5(urlparmsall);
		String serverSign0 = EncoderHandler.encodeByMD5(urlparmsall + appkey);
		String serverSign = EncoderHandler.encodeByMD5(appkey + serverSign0 ); //全部小写md5
		return serverSign;
	}

	public WeekdayDao getWeekdayDao() {
		return weekdayDao;
	}

	public void setWeekdayDao(WeekdayDao weekdayDao) {
		this.weekdayDao = weekdayDao;
	}

	public WeekdayResourceDao getWeekdayResourceDao() {
		return weekdayResourceDao;
	}

	public void setWeekdayResourceDao(WeekdayResourceDao weekdayResourceDao) {
		this.weekdayResourceDao = weekdayResourceDao;
	}

	@Transactional
	public Map search(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		
		try{
			String search_key = (String) request.getParameter("search_key");
			String user_id = (String) request.getParameter("uid");
			
			if(search_key==null||"".equals(search_key)){
				result  = "2";
				message = initDataPool.getSP("2-4-222");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}
			
			if(user_id==null||"".equals(user_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-208");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}
			
			List<Resource> ls = this.resourceDao.search(search_key);
			
			SearchLogs sl = new SearchLogs();
			sl.setSearch_value(search_key);
			sl.setUser_id(user_id);
			sl.setSearch_ip(request.getRemoteAddr());
			this.searchLogsDao.add(sl);
			if(ls!=null){
				JSONArray data1 = new JSONArray();
				JSONArray data2 = new JSONArray();
				JSONArray data3 = new JSONArray();
				JSONArray data4 = new JSONArray();
				for(int i = 0;i<ls.size();i++){
					Resource r = ls.get(i);
					type_id = r.getResource_type_id();
					String ulp_id = r.getUlp_id();
					
					Favorite f = new Favorite();
					f.setUser_id(user_id);
					f.setResource_id(r.getId());
					
					int fnum = this.favoriteDao.findNum(f);
					
					List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
					if(type_id.equals("1")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						if(list!=null){
							obj.put("url", list.get(0).getResource_url()!=null?tobereplace(list.get(0).getResource_url(), 0):"");
							obj.put("category", list.get(0).getResource_type());
							obj.put("lyrics", list.get(0).getResource_lyrics()!=null?tobereplace(list.get(0).getResource_lyrics(), 0):"");
						}else{
							obj.put("url", "");
							obj.put("category", "0");
							obj.put("lyrics", "");
						}
						obj.put("status", "0");
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("rowno", r.getRowno()+"");
						data1.add(obj);
					}else if(type_id.equals("2")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						obj.put("status", "0");
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("rowno", r.getRowno()+"");
						data2.add(obj);
					}else if(type_id.equals("4")){
						List<ResourceInfo> list1 = this.resourceInfoDao.findByResourceId1(r.getId());
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						
						obj.put("img_start", r.getStart_img()!=null?tobereplace(r.getStart_img(), 0):"");
						obj.put("start_content", r.getStart_content()!=null?r.getStart_content():"");
						obj.put("img_read", r.getRead_img()!=null?tobereplace(r.getRead_img(), 0):"");
						obj.put("read_content", r.getRead_content()!=null?r.getRead_content():"");
						obj.put("img_practise", r.getLian_img()!=null?tobereplace(r.getLian_img(), 0):"");
						obj.put("practise_content", r.getLian_content()!=null?r.getLian_content():"");
						
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						obj.put("rowno", r.getRowno()+"");
						data3.add(obj);
					}else if(type_id.equals("5")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						obj.put("status", "0");
						if (list != null && !list.isEmpty()) {
							obj.put("url",list.get(0).getResource_url() != null ? CollectionUtil
													.toplaybereplace(list.get(0).getResource_url(), 0): "");
							obj.put("category", list.get(0).getResource_type());
						} else {
							obj.put("url", "");
							obj.put("category", "0");
						}
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("rowno", r.getRowno()+"");
						data4.add(obj);
					}
					
				}
				data.put("audio", !data1.isEmpty()?data1:"");
				data.put("video", !data2.isEmpty()?data2:"");
				data.put("books", !data3.isEmpty()?data3:"");
				data.put("games", !data4.isEmpty()?data4:"");
				result = "1";
				message = initDataPool.getSP("2-4-214");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", data!=null?data:"");
				hsm.put("datasign", EncoderHandler.encodeByMD5(data.toString()));
			}else{
				result = "2";
				message = initDataPool.getSP("2-4-213");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", "");
				hsm.put("datasign", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.search执行了"+diff+"毫秒");
		
		return hsm;
	}
	
	@Transactional
	public Map searchpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		
		try{
			String search_key = (String) request.getParameter("search_key");
			String user_id = (String) request.getParameter("uid");
			
			if(search_key==null||"".equals(search_key)){
				result  = "2";
				message = initDataPool.getSP("2-4-222");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}
			
			if(user_id==null||"".equals(user_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-208");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}
			
			List<Resource> ls = this.resourceDao.search(search_key);
			
			SearchLogs sl = new SearchLogs();
			sl.setSearch_value(search_key);
			sl.setUser_id(user_id);
			sl.setSearch_ip(request.getRemoteAddr());
			this.searchLogsDao.add(sl);
			if(ls!=null){
				JSONArray data1 = new JSONArray();
				JSONArray data2 = new JSONArray();
				JSONArray data3 = new JSONArray();
				JSONArray data4 = new JSONArray();
				for(int i = 0;i<ls.size();i++){
					Resource r = ls.get(i);
					type_id = r.getResource_type_id();
					String ulp_id = r.getUlp_id();
					
					Favorite f = new Favorite();
					f.setUser_id(user_id);
					f.setResource_id(r.getId());
					
					int fnum = this.favoriteDao.findNum(f);
					
					List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
					if(type_id.equals("1")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						if(list!=null){
							obj.put("url", list.get(0).getResource_url()!=null?tobereplace(list.get(0).getResource_url(), 0):"");
							obj.put("category", list.get(0).getResource_type());
							obj.put("lyrics", list.get(0).getResource_lyrics()!=null?tobereplace(list.get(0).getResource_lyrics(), 0):"");
						}else{
							obj.put("url", "");
							obj.put("category", "0");
							obj.put("lyrics", "");
						}
						obj.put("status", "0");
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("rowno", r.getRowno()+"");
						data1.add(obj);
					}else if(type_id.equals("2")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						obj.put("status", "0");
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("rowno", r.getRowno()+"");
						data2.add(obj);
					}else if(type_id.equals("4")){
						List<ResourceInfo> list1 = this.resourceInfoDao.findByResourceId1(r.getId());
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						
						obj.put("img_start", r.getStart_img()!=null?tobereplace(r.getStart_img(), 0):"");
						obj.put("start_content", r.getStart_content()!=null?r.getStart_content():"");
						obj.put("img_read", r.getRead_img()!=null?tobereplace(r.getRead_img(), 0):"");
						obj.put("read_content", r.getRead_content()!=null?r.getRead_content():"");
						obj.put("img_practise", r.getLian_img()!=null?tobereplace(r.getLian_img(), 0):"");
						obj.put("practise_content", r.getLian_content()!=null?r.getLian_content():"");
						
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						obj.put("rowno", r.getRowno()+"");
						data3.add(obj);
					}else if(type_id.equals("5")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						obj.put("status", "0");
						if (list != null && !list.isEmpty()) {
							obj.put("url",list.get(0).getResource_url() != null ? CollectionUtil
													.toplaybereplace(list.get(0).getResource_url(), 0): "");
							obj.put("category", list.get(0).getResource_type());
						} else {
							obj.put("url", "");
							obj.put("category", "0");
						}
						obj.put("fstatus", fnum>0?"1":"0");
						obj.put("rowno", r.getRowno()+"");
						data4.add(obj);
					}
					
				}
				data.put("audio", data1);
				data.put("video", data2);
				data.put("books", data3);
				data.put("games", data4);
				result = "1";
				message = initDataPool.getSP("2-4-214");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", data);
				hsm.put("datasign", EncoderHandler.encodeByMD5(data.toString()));
			}else{
				result = "2";
				message = initDataPool.getSP("2-4-213");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", "");
				hsm.put("datasign", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.search执行了"+diff+"毫秒");
		
		return hsm;
	}

	public Map getKeyword(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		List<SearchKeyword> ls = new ArrayList<SearchKeyword>();
		Map hsm = new LinkedHashMap();
		try{
			 ls = this.searchKeywordDao.findKeyword();
			 result = "1";
			 message = initDataPool.getSP("2-4-214");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
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
        logger.info("ResourceServiceImpl.getKeyword执行了"+diff+"毫秒");
		return hsm;
	}
	
public Map getKeywordpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		List<SearchKeyword> ls = new ArrayList<SearchKeyword>();
		Map hsm = new LinkedHashMap();
		try{
			List<SearchKeyword> lsl = this.searchKeywordDao.findKeyword();
			for(int i = 0;i<4;i++){
				ls.add(lsl.get(i));
			}
			 result = "1";
			 message = initDataPool.getSP("2-4-214");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
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
        logger.info("ResourceServiceImpl.getKeyword执行了"+diff+"毫秒");
		return hsm;
	}

	public SearchKeywordDao getSearchKeywordDao() {
		return searchKeywordDao;
	}

	public void setSearchKeywordDao(SearchKeywordDao searchKeywordDao) {
		this.searchKeywordDao = searchKeywordDao;
	}

	public Map fList(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		
		try{
			String user_id = (String) request.getParameter("uid");
			
			if(user_id==null||"".equals(user_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-208");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}
			
			List<Resource> ls = this.resourceDao.fList(user_id);
			
			if(ls!=null){
				JSONArray data1 = new JSONArray();
				JSONArray data2 = new JSONArray();
				JSONArray data3 = new JSONArray();
				JSONArray data4 = new JSONArray();
				for(int i = 0;i<ls.size();i++){
					Resource r = ls.get(i);
					type_id = r.getResource_type_id();
					String ulp_id = r.getUlp_id();
					
					List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
					if(type_id.equals("1")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						if(list!=null){
							obj.put("url", list.get(0).getResource_url()!=null?tobereplace(list.get(0).getResource_url(), 0):"");
							obj.put("category", list.get(0).getResource_type());
							obj.put("lyrics", list.get(0).getResource_lyrics()!=null?tobereplace(list.get(0).getResource_lyrics(), 0):"");
						}else{
							obj.put("url", "");
							obj.put("category", "0");
							obj.put("lyrics", "");
						}
						obj.put("status", "0");
						data1.add(obj);
					}else if(type_id.equals("2")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						obj.put("status", "0");
						data2.add(obj);
					}else if(type_id.equals("4")){
						List<ResourceInfo> list1 = this.resourceInfoDao.findByResourceId1(r.getId());
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						
						obj.put("img_start", r.getStart_img()!=null?tobereplace(r.getStart_img(), 0):"");
						obj.put("start_content", r.getStart_content()!=null?r.getStart_content():"");
						obj.put("img_read", r.getRead_img()!=null?tobereplace(r.getRead_img(), 0):"");
						obj.put("read_content", r.getRead_content()!=null?r.getRead_content():"");
						obj.put("img_practise", r.getLian_img()!=null?tobereplace(r.getLian_img(), 0):"");
						obj.put("practise_content", r.getLian_content()!=null?r.getLian_content():"");
						
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						data3.add(obj);
					}else if(type_id.equals("5")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						obj.put("status", "0");
						if (list != null && !list.isEmpty()) {
							obj.put("url",list.get(0).getResource_url() != null ? CollectionUtil
													.toplaybereplace(list.get(0).getResource_url(), 0): "");
							obj.put("category", list.get(0).getResource_type());
						} else {
							obj.put("url", "");
							obj.put("category", "0");
						}
						data4.add(obj);
					}
					
				}
				data.put("audio", !data1.isEmpty()?data1:"");
				data.put("video", !data2.isEmpty()?data2:"");
				data.put("books", !data3.isEmpty()?data3:"");
				data.put("games", !data4.isEmpty()?data4:"");
				result = "1";
				message = initDataPool.getSP("2-4-214");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", data!=null?data:"");
				hsm.put("datasign", EncoderHandler.encodeByMD5(data.toString()));
			}else{
				result = "2";
				message = initDataPool.getSP("2-4-213");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", "");
				hsm.put("datasign", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.fList执行了"+diff+"毫秒");
		
		return hsm;
	}

public Map fListpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		
		try{
			String user_id = (String) request.getParameter("uid");
			
			if(user_id==null||"".equals(user_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-208");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}
			
			List<Resource> ls = this.resourceDao.fList(user_id);
			
			if(ls!=null){
				JSONArray data1 = new JSONArray();
				JSONArray data2 = new JSONArray();
				JSONArray data3 = new JSONArray();
				JSONArray data4 = new JSONArray();
				for(int i = 0;i<ls.size();i++){
					Resource r = ls.get(i);
					type_id = r.getResource_type_id();
					String ulp_id = r.getUlp_id();
					
					List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
					if(type_id.equals("1")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						if(list!=null){
							obj.put("url", list.get(0).getResource_url()!=null?tobereplace(list.get(0).getResource_url(), 0):"");
							obj.put("category", list.get(0).getResource_type());
							obj.put("lyrics", list.get(0).getResource_lyrics()!=null?tobereplace(list.get(0).getResource_lyrics(), 0):"");
						}else{
							obj.put("url", "");
							obj.put("category", "0");
							obj.put("lyrics", "");
						}
						obj.put("status", "0");
						data1.add(obj);
					}else if(type_id.equals("2")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("data", !objModel.isEmpty()?objModel:"");
						obj.put("status", "0");
						data2.add(obj);
					}else if(type_id.equals("4")){
						List<ResourceInfo> list1 = this.resourceInfoDao.findByResourceId1(r.getId());
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						
						obj.put("img_start", r.getStart_img()!=null?tobereplace(r.getStart_img(), 0):"");
						obj.put("start_content", r.getStart_content()!=null?r.getStart_content():"");
						obj.put("img_read", r.getRead_img()!=null?tobereplace(r.getRead_img(), 0):"");
						obj.put("read_content", r.getRead_content()!=null?r.getRead_content():"");
						obj.put("img_practise", r.getLian_img()!=null?tobereplace(r.getLian_img(), 0):"");
						obj.put("practise_content", r.getLian_content()!=null?r.getLian_content():"");
						
						JSONArray objModel = new JSONArray();
						if(list!=null&&!list.isEmpty()){
							for(ResourceInfo ri:list){
									JSONObject jb = new JSONObject();
									jb.put("url", ri.getResource_url()!=null?tobereplace(ri.getResource_url(), 0):"");
									jb.put("category", ri.getResource_type());
									objModel.add(jb);
							}
						}else{
							JSONObject jb = new JSONObject();
							obj.put("url", "");
							obj.put("category","0");
							objModel.add(jb);
						}
						obj.put("status", "0");
						obj.put("data", objModel);
						data3.add(obj);
					}else if(type_id.equals("5")){
						JSONObject obj = new JSONObject();
						obj.put("resource_id", r.getId());
						obj.put("content", r.getResource_content()!=null?r.getResource_content():"");
						obj.put("summary", r.getResource_summary()!=null?r.getResource_summary():"");
						obj.put("author", r.getResource_author()!=null?r.getResource_author():"");
						obj.put("img_index", r.getImg_index()!=null?tobereplace(r.getImg_index(),0):"");
						obj.put("img_index_size", r.getImg_index_size()!=null?r.getImg_index_size():"");
						obj.put("img_list", r.getImg_list()!=null?tobereplace(r.getImg_list(),0):"");
						obj.put("img_list_size", r.getImg_list_size()!=null?r.getImg_list_size():"");
						obj.put("img_main", r.getImg_main()!=null?tobereplace(r.getImg_main(), 0):"");
						obj.put("img_main_size", r.getImg_main_size()!=null?r.getImg_main_size():"");
						obj.put("status", "0");
						if (list != null && !list.isEmpty()) {
							obj.put("url",list.get(0).getResource_url() != null ? CollectionUtil
													.toplaybereplace(list.get(0).getResource_url(), 0): "");
							obj.put("category", list.get(0).getResource_type());
						} else {
							obj.put("url", "");
							obj.put("category", "0");
						}
						data4.add(obj);
					}
					
				}
				data.put("audio", data1);
				data.put("video", data2);
				data.put("books", data3);
				data.put("games", data4);
				result = "1";
				message = initDataPool.getSP("2-4-214");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", data);
				hsm.put("datasign", EncoderHandler.encodeByMD5(data.toString()));
			}else{
				result = "2";
				message = initDataPool.getSP("2-4-213");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", "");
				hsm.put("datasign", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.fList执行了"+diff+"毫秒");
		
		return hsm;
	}

	public Map getPlay(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		
		String user_id = (String) request.getParameter("uid");
		
		Map hsm = new LinkedHashMap();
		try{
		Resource r = this.resourceDao.getPlay();
		if (r != null) {
				Favorite f = new Favorite();
				f.setUser_id(user_id);
				f.setResource_id(r.getId());
				int num = this.favoriteDao.findNum(f);
				List<ResourceInfo> list = this.resourceInfoDao
						.findByResourceId(r.getId());
				JSONObject obj = new JSONObject();
				obj.put("resource_id", r.getId());
				obj.put("content", r.getResource_content() != null ? r
						.getResource_content() : "");
				obj.put("summary", r.getResource_summary() != null ? r
						.getResource_summary() : "");
				obj.put("author", r.getResource_author() != null ? r
						.getResource_author() : "");
				obj.put("img_index", r.getImg_index() != null ? tobereplace(r
						.getImg_index(), 0) : "");
				obj.put("img_index_size", r.getImg_index_size() != null ? r
						.getImg_index_size() : "");
				obj.put("img_list", r.getImg_list() != null ? tobereplace(r
						.getImg_list(), 0) : "");
				obj.put("img_list_size", r.getImg_list_size() != null ? r
						.getImg_list_size() : "");
				obj.put("img_main", r.getImg_main() != null ? tobereplace(r
						.getImg_main(), 0) : "");
				obj.put("img_main_size", r.getImg_main_size() != null ? r
						.getImg_main_size() : "");
				obj.put("status", num+"");
				JSONArray objModel = new JSONArray();
				if (list != null && !list.isEmpty()) {
					obj.put("url",list.get(0).getResource_url() != null ? CollectionUtil
											.toplaybereplace(list.get(0).getResource_url(), 0): "");
					obj.put("category", list.get(0).getResource_type());
				} else {
					obj.put("url", "");
					obj.put("category", "0");
				}
				result = "1";
				message = initDataPool.getSP("2-4-214");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", obj);
			}else{
				result = "2";
				message = initDataPool.getSP("2-4-213");
				hsm.put("version", Constant.version);
				hsm.put("result", result);
				hsm.put("message", message);
				hsm.put("date", date);
				hsm.put("week", week);
				hsm.put("data", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("ResourceServiceImpl.fList执行了"+diff+"毫秒");
		
		return hsm;
	}
	
}
