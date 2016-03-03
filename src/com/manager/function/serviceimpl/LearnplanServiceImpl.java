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
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.manager.function.controller.GlobalHobbyController;
import com.manager.function.dao.BabyDao;
import com.manager.function.dao.FavoriteDao;
import com.manager.function.dao.GlobalPropertyDao;
import com.manager.function.dao.GlobalScoreDao;
import com.manager.function.dao.LearnplanDao;
import com.manager.function.dao.ResourceDao;
import com.manager.function.dao.ResourceInfoDao;
import com.manager.function.dao.ScoreLogDao;
import com.manager.function.dao.UserBillingDao;
import com.manager.function.dao.UserLearnPlanDao;
import com.manager.function.entity.Baby;
import com.manager.function.entity.Favorite;
import com.manager.function.entity.GlobalProperty;
import com.manager.function.entity.GlobalScore;
import com.manager.function.entity.Learnplan;
import com.manager.function.entity.Resource;
import com.manager.function.entity.ResourceInfo;
import com.manager.function.entity.ScoreLog;
import com.manager.function.entity.UserBilling;
import com.manager.function.entity.UserLearnplan;
import com.manager.function.service.LearnplanService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.EncoderHandler;
import com.manager.util.Utils;
import com.manager.util.Xml;

public class LearnplanServiceImpl implements LearnplanService {
	
	private Logger logger = Logger.getLogger(LearnplanServiceImpl.class);
	
	private LearnplanDao learnplanDao;
	
	private UserLearnPlanDao userLearnplanDao;
	
	private InitDataPool initDataPool;
	
	private ResourceDao resourceDao;

	private ResourceInfoDao resourceInfoDao;
	
	private GlobalScoreDao globalScoreDao;
	
	private ScoreLogDao scoreLogDao;
	
	private UserBillingDao userBillingDao;
	
	private GlobalPropertyDao globalPropertyDao;
	
	private FavoriteDao favoriteDao;
	
	private BabyDao babyDao;
	
	public BabyDao getBabyDao() {
		return babyDao;
	}
	
	public void setBabyDao(BabyDao babyDao) {
		this.babyDao = babyDao;
	}
	
	public FavoriteDao getFavoriteDao() {
		return favoriteDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public GlobalPropertyDao getGlobalPropertyDao() {
		return globalPropertyDao;
	}

	public void setGlobalPropertyDao(GlobalPropertyDao globalPropertyDao) {
		this.globalPropertyDao = globalPropertyDao;
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

	public int deleteLearnplan(String id) {
		Learnplan Learnplan=new Learnplan();
		Learnplan.setId(id);
		return this.learnplanDao.deleteLearnplan(Learnplan);
	}

	public int findLearnplanCount(Learnplan Learnplan) {
		return this.learnplanDao.findLearnplanCount(Learnplan);
	}

	public List<Learnplan> findLearnplanList(Learnplan Learnplan, int pageNo, int pageSize) {
		return setLearnplanJSON(this.learnplanDao.findLearnplanList(Learnplan, pageNo, pageSize));
	}
	
	public List<Learnplan> findLearnplanList(Learnplan Learnplan) {
		return this.learnplanDao.findLearnplanList(Learnplan);
	}

	public List<Learnplan> findLearnplanList() {
		return this.learnplanDao.findLearnplanList(null);
	}
	
	public int insertLearnplan(Learnplan Learnplan) {
		String plan_config=getLearnplanJSON(Learnplan);
		Learnplan.setPlan_config(plan_config);
		return this.learnplanDao.insertLearnplan(Learnplan);
	}
	
	public int updateLearnplan(Learnplan Learnplan) {
		String plan_config=getLearnplanJSON(Learnplan);
		Learnplan.setPlan_config(plan_config);
		return this.learnplanDao.updateLearnplan(Learnplan);
	}
	
	public String getLearnplanJSON(Learnplan Learnplan){
		JSONObject jobj=new JSONObject();
		jobj.put("listen", Learnplan.getListen());
		jobj.put("watch", Learnplan.getWatch());
		jobj.put("read", Learnplan.getRead());
		jobj.put("play", Learnplan.getPlay());
		return jobj.toString();
	}
	
	public List<Learnplan> setLearnplanJSON(List<Learnplan> wlist){
		if(wlist!=null && wlist.size()>0){
			for(int i=0;i<wlist.size();i++){
				String config=wlist.get(i).getPlan_config();
				if(CollectionUtil.checkNull(config)){
					JSONObject jsonobj=JSONObject.fromObject(config);
					if(jsonobj.getString("listen")!=null)wlist.get(i).setListen(jsonobj.getString("listen"));
					if(jsonobj.getString("watch")!=null)wlist.get(i).setWatch(jsonobj.getString("watch"));
					if(jsonobj.getString("read")!=null)wlist.get(i).setRead(jsonobj.getString("read"));
					if(jsonobj.getString("play")!=null)wlist.get(i).setPlay(jsonobj.getString("play"));
				}
			}
			return wlist;
		}
		return null;
	}

	
	public Learnplan findLearnplanOne(String id){
		Learnplan Learnplan=new Learnplan();
		Learnplan.setId(id);
		List<Learnplan> LearnplanList=this.learnplanDao.findLearnplanList(Learnplan);
		if(LearnplanList!=null && LearnplanList.size()>0){
			List<Learnplan> wlist=setLearnplanJSON(LearnplanList);
			return wlist.get(0);
		}
		return null;
	}

	@Transactional
	public Map learnplan(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));

		String result  = "0";
		String message = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
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
		        hsm.put("data", "");
		        hsm.put("datasign", "");
				return hsm;
			}else{
				flag = true;
			}
			
			if(flag){
				//获取plan_id
				int num = this.userLearnplanDao.getPlanId(baby_id);
				boolean isexit = false;
				if(num==0){
					num = 1;
				}
				String plan_id = num +"";
				
				List<Resource> ls = new ArrayList<Resource>();
				
				boolean res1 = false;
				
				if(num>0){
					//判断是否完成阅读计划
					UserLearnplan ulp = new UserLearnplan();
					ulp.setBaby_id(baby_id);
					int unfinishedNum = this.userLearnplanDao.isFinush(ulp);
					if(unfinishedNum==0){
						res1 = false;
						Resource r = new Resource();
						r.setBaby_id(baby_id);
						r.setPlan_id(plan_id);
						ls = this.resourceDao.getlearnplan(r);
					}else{
						res1 = true;
						if(num>1){
							plan_id = (num+1) +"";
						}
						
						//获取符合条件的List
						List<Resource> lsModel = this.resourceDao.learnplan(baby_id);
						
						List<Resource> ls1 = new ArrayList<Resource>();
						List<Resource> ls2 = new ArrayList<Resource>();
						List<Resource> ls3 = new CopyOnWriteArrayList<Resource>();
						List<Resource> lsModel3 = new ArrayList<Resource>();
						List<Resource> ls4 = new ArrayList<Resource>();
						List<Resource> huiben = this.resourceDao.huiben(baby_id);
						
						//分类
						if(lsModel!=null){
						for(Resource r : lsModel){
							if(r.getResource_type_id().equals("1")){
								ls1.add(r);
							}else if(r.getResource_type_id().equals("2")){
								ls2.add(r);
							}else if(r.getResource_type_id().equals("5")){
								ls4.add(r);
							}
						}
						}
						for(Resource rr:huiben){
							ls3.add(rr);
						}
						
						//获取阶段对应的属性
						GlobalProperty gpModel = new GlobalProperty();
						gpModel.setBaby_id(baby_id);
						
						List<GlobalProperty> gpList = this.globalPropertyDao.findByBabyId(gpModel);
						List<GlobalProperty> gpModelList = new ArrayList<GlobalProperty>();
						
						//必选属性
						List<GlobalProperty> bixuangpList = new ArrayList<GlobalProperty>();
						//三个可选项
						GlobalProperty gps = new GlobalProperty();
						gps.setBaby_id(baby_id);
						List<GlobalProperty> NobixuangpList = this.globalPropertyDao.findByLevel(gps);
						for(GlobalProperty gp:gpList){
							if(gp.getProperty_type().equals("1")){
								bixuangpList.add(gp);
							}else{
								gpModelList.add(gp);
							}
						}
						for(int n =0;n<NobixuangpList.size();n++){
							GlobalProperty gp = gpModelList.get(n);
							gpModelList.remove(gp);
						}
						
						//获取阅读配置
						Learnplan lp = this.learnplanDao.findOne(plan_id);
						if(lp==null){
							result  = "2";
							message = initDataPool.getSP("2-4-216");
							hsm.put("version", Constant.version);
					        hsm.put("result", result);
					        hsm.put("message", message);
					        hsm.put("date", date);
					        hsm.put("data", "");
					        hsm.put("datasign", "");
							return hsm;
						}
						String plan_weekday = lp.getPlan_weekday();
						int plan_weekday_num = Integer.parseInt(plan_weekday);
						
						String plan_config = lp.getPlan_config();
						JSONObject jo = JSONObject.fromObject(plan_config);
						
						int listen = Integer.parseInt(jo.getString("listen"));
						int see = Integer.parseInt(jo.getString("watch"));
						int read = Integer.parseInt(jo.getString("read"));
						int play = Integer.parseInt(jo.getString("play"));
						
						//选书
						String ids = "";
						//选择可选项
						for(GlobalProperty gp:NobixuangpList){
							int kexuan = Integer.parseInt(gp.getProperty_num());
							//计数
							int d = 0;
							//属性id
							String id = gp.getId();
							if(ls3!=null){
								List<Resource> rlist=new ArrayList<Resource>();
								Iterator it = ls3.iterator();
								while(it.hasNext()){
								Resource r = (Resource) it.next();
								List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
								if(list!=null){
									
									for(ResourceInfo ri :list){
										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
											rlist.add(r);
										}
										
									}
									
									
//									for(ResourceInfo ri :list){
//										//
//										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<kexuan){
//											d++;
//											ls3.remove(r);
//											r.setProperty_id(id);
//											r.setReplenish("0");
//											lsModel3.add(r);
//											if(ids.equals("")){
//												ids += r.getId();
//											}else {
//												ids +=","+r.getId();
//											}
//										}
//									}
								}
								}
								
								kexuan=rlist.size()>kexuan?kexuan:rlist.size();
								for(int nn=0;nn<kexuan;nn++){
									Random rand = new Random();
									Resource r1 = rlist.get(rand.nextInt(rlist.size()));
									
									ls3.remove(r1);
									r1.setProperty_id(id);
									r1.setReplenish("0");
									lsModel3.add(r1);
									if(ids.equals("")){
										ids += r1.getId();
									}else {
										ids +=","+r1.getId();
									}
									
									rlist.remove(r1);
								}
								
								
								
							}
						}
						//选择必选项
						for(GlobalProperty gp:bixuangpList){
							int bixuan = Integer.parseInt(gp.getProperty_num());
							//计数
							int d = 0;
							//属性id
							String id = gp.getId();
							if(ls3!=null){
								List<Resource> rlist=new ArrayList<Resource>();
								Iterator it = ls3.iterator();
								while(it.hasNext()){
								Resource r = (Resource) it.next();
								List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
								if(list!=null){
//									for(ResourceInfo ri :list){
//										//
//										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<bixuan){
//											d++;
//											ls3.remove(r);
//											r.setProperty_id(id);
//											r.setReplenish("0");
//											lsModel3.add(r);
//											if(ids.equals("")){
//												ids += r.getId();
//											}else {
//												ids +=","+r.getId();
//											}
//										}
//									}
									
									
									for(ResourceInfo ri :list){
										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
											rlist.add(r);
										}
										
									}
								}
								}
								
								
								bixuan=rlist.size()>bixuan?bixuan:rlist.size();
								for(int nn=0;nn<bixuan;nn++){
									Random rand = new Random();
									Resource r1 = rlist.get(rand.nextInt(rlist.size()));
									
									ls3.remove(r1);
									r1.setProperty_id(id);
									r1.setReplenish("0");
									lsModel3.add(r1);
									if(ids.equals("")){
										ids += r1.getId();
									}else {
										ids +=","+r1.getId();
									}
									
									rlist.remove(r1);
								}
								
							}
						}
						if(lsModel3.size()<read*plan_weekday_num){
						//补充项
						for(GlobalProperty gp:gpModelList){
							if(lsModel3.size()>=read*plan_weekday_num){
								break;
							}
							int buquan = Integer.parseInt(gp.getProperty_num());
							//计数
							int d = 0;
							//属性id
							String id = gp.getId();
							if(ls3!=null){
								List<Resource> rlist=new ArrayList<Resource>();
								Iterator it = ls3.iterator();
								while(it.hasNext()){
								Resource r = (Resource) it.next();
								List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
								if(list!=null){
//									for(ResourceInfo ri :list){
//										//
//										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<buquan){
//											d++;
//											if(lsModel3.size()>=read*plan_weekday_num){
//												break;
//											}
//											ls3.remove(r);
//											r.setProperty_id(id);
//											r.setReplenish("1");
//											lsModel3.add(r);
//											if(ids.equals("")){
//												ids += r.getId();
//											}else {
//												ids +=","+r.getId();
//											}
//										}
//									}
									
									
									for(ResourceInfo ri :list){
										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
											rlist.add(r);
										}
										
									}
									
								}
								}
								
								int cha=read*plan_weekday_num-lsModel3.size();
								buquan=cha>buquan?buquan:cha;
								buquan=rlist.size()>buquan?buquan:rlist.size();
								for(int nn=0;nn<buquan;nn++){
									Random rand = new Random();
									Resource r1 = rlist.get(rand.nextInt(rlist.size()));
									
									ls3.remove(r1);
									r1.setProperty_id(id);
									r1.setReplenish("0");
									lsModel3.add(r1);
									if(ids.equals("")){
										ids += r1.getId();
									}else {
										ids +=","+r1.getId();
									}
									
									rlist.remove(r1);
								}
								
							}
						}
						}
//						//本阶段书不够数据补全
//						if(lsModel3.isEmpty()||lsModel3.size()<read*plan_weekday_num){
//							List<GlobalProperty> bqgpList = this.globalPropertyDao.findByBabyId(gpModel);//属性补全
//							List<Resource> bqls = new CopyOnWriteArrayList<Resource>();
//							List<Resource> bqlsModel = new CopyOnWriteArrayList<Resource>();//绘本补全
//							Resource rModel = new Resource();
//							rModel.setId(ids);
//							rModel.setBaby_id(baby_id);
//							bqlsModel = this.resourceDao.huibenbuquan(rModel);
//							for(Resource rrr:bqlsModel){
//								bqls.add(rrr);
//							}
//							for(GlobalProperty gp:bqgpList){
//								//计数
//								int d = 0;
//								//属性id
//								String id = gp.getId();
//								if(bqls!=null){
//									Iterator it = bqls.iterator();
//									while(it.hasNext()){
//									Resource r = (Resource) it.next();
//									List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
//										for(ResourceInfo ri :list){
//											//
//											if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<buquan){
//												d++;
//												if(lsModel3.size()>=read*plan_weekday_num){
//													break;
//												}
//												bqls.remove(r);
//												r.setProperty_id(id);
//												lsModel3.add(r);
//											}
//										}
//									}
//								}
//							}
//						}
						
						String listen_id = "";
						String see_id = "";
						String read_id = "";
						String play_id = "";
						
						for(int a = 0; a< listen*plan_weekday_num;a++){
							if(ls1.isEmpty()){
								int len = listen*plan_weekday_num - a;
								Resource r = new Resource();
								r.setId(listen_id);
								r.setResource_type_id("1");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquan(r);
								if(list!=null){
									for(int c = 0;c<list.size();c++){
										Resource re = list.get(c);
										int b = (a+c)/listen+1;
										re.setNum(b);
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
							int b = a/listen+1;
							re.setNum(b);
							ls.add(re);
							ls1.remove(re);
						}
					
					
						for(int a = 0; a< see*plan_weekday_num;a++){
							if(ls2.isEmpty()){
								int len = see*plan_weekday_num - a;
								Resource r = new Resource();
								r.setId(see_id);
								r.setResource_type_id("2");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquan(r);
								if(list!=null){
									for(int c = 0;c<list.size();c++){
										Resource re = list.get(c);
										int b = (a+c)/listen+1;
										re.setNum(b);
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
							int b = a/see+1;
							re.setNum(b);
							ls.add(re);
							ls2.remove(re);
						}
						
						for(int a = 0; a< read*plan_weekday_num;a++){
							if(lsModel3.isEmpty()){
								break;
							}
							Random rand = new Random();
							Resource re = lsModel3.get(rand.nextInt(lsModel3.size()));
							if(a==0){
								read_id += re.getId();
							}else {
								read_id +=","+re.getId();
							}
							int b = a/read+1;
							re.setNum(b);
							ls.add(re);
							lsModel3.remove(re);
						}
						
						for(int a = 0; a< play*plan_weekday_num;a++){
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
							int b = a/play+1;
							re.setNum(b);
							ls.add(re);
							ls4.remove(re);
						}
						
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
							UserLearnplan ulpModel = new UserLearnplan();
							ulp_id = this.userLearnplanDao.getId()+"";
							ulpModel.setId(ulp_id);
							ulpModel.setBaby_id(baby_id);
							ulpModel.setResource_id(r.getId());
							ulpModel.setPlan_id(plan_id);
							ulpModel.setPlan_status("0");
							ulpModel.setPeoperty_id(r.getProperty_id());
							ulpModel.setReplenish(r.getReplenish());
							ulpModel.setPlan_weekday(r.getNum()+"");
							
							if(r.getNum()==1){
								//周六
								if(week==6){
									this.userLearnplanDao.add1(ulpModel);
								}else if(week==7){
									//周日
									this.userLearnplanDao.add2(ulpModel);
								}else{
									//周一到周五
									this.userLearnplanDao.add(ulpModel);
								}
							}else{
								this.userLearnplanDao.add(ulpModel);
							}
						}else{
							ulp_id = r.getUlp_id();
						}
						
						UserLearnplan usp = this.userLearnplanDao.findOne(ulp_id);
						
						Favorite f = new Favorite();
						f.setUser_id(user_id);
						f.setResource_id(r.getId());
						
						int fnum = this.favoriteDao.findNum(f);
						
						List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
						if(type_id.equals("1")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("fstatus", fnum>0?"1":"0");
							data1.add(obj);
						}else if(type_id.equals("2")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							}
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("fstatus", fnum>0?"1":"0");
							data2.add(obj);
						}else if(type_id.equals("4")){
							list = this.resourceInfoDao.findByResourceId(r.getId());
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("property", r.getProperty_id()!=null?r.getProperty_id():"");
							obj.put("fstatus", fnum>0?"1":"0");
							data3.add(obj);
						}else if(type_id.equals("5")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
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
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("data", !objModel.isEmpty()?objModel:"");
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
					hsm.put("datasign","");
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
			hsm.put("datasign","");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("LearnplanServiceImpl.learnplan执行了"+diff+"毫秒");
		
		return hsm;
	
	}

	@Transactional
	public Map learnplanpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));

		String result  = "0";
		String message = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		try{
			String baby_id = (String) request.getParameter("baby_id");
			String user_id = (String) request.getParameter("uid");
			String isOpen = (String) request.getParameter("isnewplan");
			Baby bbbb= this.babyDao.findOne(baby_id);
			String languages = bbbb.getBaby_language();
			int language = 0;
			if(languages!=null)
				language = Integer.parseInt(languages);
			
			logger.info("language_id: "+language);
			boolean flag = false;
			if(baby_id==null||"".equals(baby_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-211");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", data);
		        hsm.put("datasign", "");
				return hsm;
			}else{
				flag = true;
			}
			
			if(flag){
				//获取plan_id
				int num = this.userLearnplanDao.getPlanId(baby_id);
				//boolean isexit = false;
				int planNum = num;
				if(num==0){
					num = 1;
				}
				String plan_id = num +"";
				
				List<Resource> ls = new ArrayList<Resource>();
				
				boolean res1 = false;
				
				if(num>0){
					//判断是否完成阅读计划
					//UserLearnplan ulp = new UserLearnplan();
					//ulp.setBaby_id(baby_id);
					//int unfinishedNum = this.userLearnplanDao.isFinush(ulp);
					//if(unfinishedNum==0){
					if( isOpen.equals("0") && planNum != 0 ){
						res1 = false;
						Resource r = new Resource();
						r.setBaby_id(baby_id);
						r.setPlan_id(plan_id);
						ls = this.resourceDao.getlearnplan(r);
						
						logger.info("old_plan");
					}else if(isOpen.equals("1") || (isOpen.equals("0") && planNum == 0)){
						res1 = true;
		
						if(planNum>0){
							plan_id = (num+1) +"";
						}
						logger.info("new_plan: "+plan_id);
						//获取符合条件的List
						List<Resource> lsModel = this.resourceDao.learnplan(baby_id);
						
						List<Resource> ls1 = new ArrayList<Resource>();
						List<Resource> ls1last = new ArrayList<Resource>();
						List<Resource> ls1next = new ArrayList<Resource>();
						List<Resource> ls2 = new ArrayList<Resource>();
						List<Resource> ls2last = new ArrayList<Resource>();
						List<Resource> ls2next = new ArrayList<Resource>();
						List<Resource> ls4 = new ArrayList<Resource>();
						List<Resource> ls4last = new ArrayList<Resource>();
						List<Resource> ls4next = new ArrayList<Resource>();
						
						List<Resource> ls3 = new CopyOnWriteArrayList<Resource>();
						List<Resource> lsModel3 = new ArrayList<Resource>();
						List<Resource> huiben = this.resourceDao.huiben(baby_id);
						
						//分类
						if(lsModel!=null){
							for(Resource r : lsModel){
								if(r.getResource_type_id().equals("1"))
								{
									if(r.getLanguage_level().equals(language+""))
									{
										ls1.add(r);
									}else if(language-1>0)
									{
										if(r.getLanguage_level().equals((language-1)+""))
											ls1last.add(r);
									}else if(language+1<7)
									{
										if(r.getLanguage_level().equals((language+1)+""))
											ls1next.add(r);
									}
								}else if(r.getResource_type_id().equals("2"))
								{
									if(r.getLanguage_level().equals(language+""))
									{
										ls2.add(r);
									}else if(language-1>0)
									{
										if(r.getLanguage_level().equals((language-1)+""))
											ls2last.add(r);
									}else if(language+1<7)
									{
										if(r.getLanguage_level().equals((language+1)+""))
											ls2next.add(r);
									}
								}else if(r.getResource_type_id().equals("5"))
								{
									if(r.getLanguage_level().equals(language+""))
									{
										ls4.add(r);
									}else if(language-1>0)
									{
										if(r.getLanguage_level().equals((language-1)+""))
											ls4last.add(r);
									}else if(language+1<7)
									{
										if(r.getLanguage_level().equals((language+1)+""))
											ls4next.add(r);
									}
								}
							}
						}
						
						for(Resource rr:huiben){
							ls3.add(rr);
						}
						
						//获取阶段对应的属性
						GlobalProperty gpModel = new GlobalProperty();
						gpModel.setBaby_id(baby_id);
						
						List<GlobalProperty> gpList = this.globalPropertyDao.findByBabyId(gpModel);
						List<GlobalProperty> gpModelList = new ArrayList<GlobalProperty>();
						
						//必选属性
						List<GlobalProperty> bixuangpList = new ArrayList<GlobalProperty>();
						//三个可选项
						GlobalProperty gps = new GlobalProperty();
						gps.setBaby_id(baby_id);
						List<GlobalProperty> NobixuangpList = this.globalPropertyDao.findByLevel(gps);
						for(GlobalProperty gp:gpList){
							if(gp.getProperty_type().equals("1")){
								bixuangpList.add(gp);
							}else{
								gpModelList.add(gp);
							}
						}
						for(int n =0;n<NobixuangpList.size();n++){
							GlobalProperty gp = gpModelList.get(n);
							gpModelList.remove(gp);
						}
						
						//获取阅读配置
						Learnplan lp = this.learnplanDao.findOne("1");
						if(lp==null){
							result  = "2";
							message = initDataPool.getSP("2-4-216");
							hsm.put("version", Constant.version);
					        hsm.put("result", result);
					        hsm.put("message", message);
					        hsm.put("date", date);
					        hsm.put("data", data);
					        hsm.put("datasign", "");
							return hsm;
						}
						String plan_weekday = lp.getPlan_weekday();
						int plan_weekday_num = Integer.parseInt(plan_weekday);
						
						String plan_config = lp.getPlan_config();
						JSONObject jo = JSONObject.fromObject(plan_config);
						
						int listen = Integer.parseInt(jo.getString("listen"));
						int see = Integer.parseInt(jo.getString("watch"));
						int read = Integer.parseInt(jo.getString("read"));
						int play = Integer.parseInt(jo.getString("play"));
						
						//选书
						String ids = "";
						//选择可选项
						for(GlobalProperty gp:NobixuangpList){
							int kexuan = Integer.parseInt(gp.getProperty_num());
							//计数
							int d = 0;
							//属性id
							String id = gp.getId();
							
							if(ls3!=null)
							{
								List<Resource> rlist=new ArrayList<Resource>();
								List<Resource> rlistlast=new ArrayList<Resource>();
								List<Resource> rlistnext=new ArrayList<Resource>();
								Iterator it = ls3.iterator();
								while(it.hasNext())
								{
									Resource r = (Resource) it.next();
									List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
									if(list!=null)
									{									
										for(ResourceInfo ri :list)
										{
											if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id))
											{
												if(r.getLanguage_level().equals(language+""))
												{
													rlist.add(r);
												}else if(language-1>0)
												{
													if(r.getLanguage_level().equals((language-1)+""))
														rlistlast.add(r);
												}else if(language+1<7)
												{
													if(r.getLanguage_level().equals((language+1)+""))
														rlistnext.add(r);
												}
											}							
										}
									}
								}
								
								//kexuan=rlist.size()>kexuan?kexuan:rlist.size();
								for(int nn=0;nn<kexuan;nn++){
									if(rlist.isEmpty() && rlistlast.isEmpty() && rlistnext.isEmpty())
										break;
									if(!rlist.isEmpty())
									{
										Random rand = new Random();
										Resource r1 = rlist.get(rand.nextInt(rlist.size()));
										
										ls3.remove(r1);
										r1.setProperty_id(id);
										r1.setReplenish("0");
										lsModel3.add(r1);
										if(ids.equals("")){
											ids += r1.getId();
										}else {
											ids +=","+r1.getId();
										}									
										rlist.remove(r1);
									}else if(!rlistlast.isEmpty())
									{
										Random rand = new Random();
										Resource r1 = rlistlast.get(rand.nextInt(rlistlast.size()));
										
										ls3.remove(r1);
										r1.setProperty_id(id);
										r1.setReplenish("0");
										lsModel3.add(r1);
										if(ids.equals("")){
											ids += r1.getId();
										}else {
											ids +=","+r1.getId();
										}									
										rlistlast.remove(r1);
									}else if(!rlistnext.isEmpty())
									{
										Random rand = new Random();
										Resource r1 = rlistnext.get(rand.nextInt(rlistnext.size()));
										
										ls3.remove(r1);
										r1.setProperty_id(id);
										r1.setReplenish("0");
										lsModel3.add(r1);
										if(ids.equals("")){
											ids += r1.getId();
										}else {
											ids +=","+r1.getId();
										}									
										rlistnext.remove(r1);
									}
								}								
							}
						}
						//选择必选项
						for(GlobalProperty gp:bixuangpList){
							int bixuan = Integer.parseInt(gp.getProperty_num());
							//计数
							int d = 0;
							//属性id
							String id = gp.getId();
							if(ls3!=null){
								List<Resource> rlist=new ArrayList<Resource>();
								List<Resource> rlistlast=new ArrayList<Resource>();
								List<Resource> rlistnext=new ArrayList<Resource>();
								Iterator it = ls3.iterator();
								while(it.hasNext())
								{
									Resource r = (Resource) it.next();
									List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
									if(list!=null){
//									
										for(ResourceInfo ri :list){
											if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
												if(r.getLanguage_level().equals(language+""))
												{
													rlist.add(r);
												}else if(language-1>0)
												{
													if(r.getLanguage_level().equals((language-1)+""))
														rlistlast.add(r);
												}else if(language+1<7)
												{
													if(r.getLanguage_level().equals((language+1)+""))
														rlistnext.add(r);
												}
											}										
										}
									}
								}
								
								
								//bixuan=rlist.size()>bixuan?bixuan:rlist.size();
								for(int nn=0;nn<bixuan;nn++){
									if(rlist.isEmpty() && rlistlast.isEmpty() && rlistnext.isEmpty())
										break;
									if(!rlist.isEmpty())
									{
										Random rand = new Random();
										Resource r1 = rlist.get(rand.nextInt(rlist.size()));
										
										ls3.remove(r1);
										r1.setProperty_id(id);
										r1.setReplenish("0");
										lsModel3.add(r1);
										if(ids.equals("")){
											ids += r1.getId();
										}else {
											ids +=","+r1.getId();
										}
										rlist.remove(r1);
									}else if(!rlistlast.isEmpty())
									{
										Random rand = new Random();
										Resource r1 = rlistlast.get(rand.nextInt(rlistlast.size()));
										
										ls3.remove(r1);
										r1.setProperty_id(id);
										r1.setReplenish("0");
										lsModel3.add(r1);
										if(ids.equals("")){
											ids += r1.getId();
										}else {
											ids +=","+r1.getId();
										}
										rlistlast.remove(r1);
									}else if(!rlistnext.isEmpty())
									{
										Random rand = new Random();
										Resource r1 = rlistnext.get(rand.nextInt(rlistnext.size()));
										
										ls3.remove(r1);
										r1.setProperty_id(id);
										r1.setReplenish("0");
										lsModel3.add(r1);
										if(ids.equals("")){
											ids += r1.getId();
										}else {
											ids +=","+r1.getId();
										}
										rlistnext.remove(r1);
									}
								}								
							}
						}
												
						if(lsModel3.size()<read*plan_weekday_num)
						{
							//补充项
							for(GlobalProperty gp:gpModelList)
							{
								if(lsModel3.size()>=read*plan_weekday_num){
									break;
								}
								int buquan = Integer.parseInt(gp.getProperty_num());
								//计数
								int d = 0;
								//属性id
								String id = gp.getId();
								if(ls3!=null)
								{
									List<Resource> rlist=new ArrayList<Resource>();
									List<Resource> rlistlast=new ArrayList<Resource>();
									List<Resource> rlistnext=new ArrayList<Resource>();
									Iterator it = ls3.iterator();
									while(it.hasNext())
									{
										Resource r = (Resource) it.next();
										List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
										if(list!=null)
										{
											for(ResourceInfo ri :list)
											{
												if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id))
												{									
													if(r.getLanguage_level().equals(language+""))
													{
														rlist.add(r);
													}else if(language-1>0)
													{
														if(r.getLanguage_level().equals((language-1)+""))
															rlistlast.add(r);
													}else if(language+1<7)
													{
														if(r.getLanguage_level().equals((language+1)+""))
															rlistnext.add(r);
													}
												}									
											}
										}
									}
								
									int cha=read*plan_weekday_num-lsModel3.size();
									buquan=cha;//>buquan?buquan:cha;
									//buquan=rlist.size()>buquan?buquan:rlist.size();
									for(int nn=0;nn<buquan;nn++){
										if(rlist.isEmpty() && rlistlast.isEmpty() && rlistnext.isEmpty())
											break;
										if(!rlist.isEmpty())
										{
											Random rand = new Random();
											Resource r1 = rlist.get(rand.nextInt(rlist.size()));
										
											ls3.remove(r1);
											r1.setProperty_id(id);
											r1.setReplenish("0");
											lsModel3.add(r1);
											if(ids.equals("")){
											ids += r1.getId();
											}else {
												ids +=","+r1.getId();
											}
										
											rlist.remove(r1);
										}else if(!rlistlast.isEmpty())
										{
											Random rand = new Random();
											Resource r1 = rlistlast.get(rand.nextInt(rlistlast.size()));
										
											ls3.remove(r1);
											r1.setProperty_id(id);
											r1.setReplenish("0");
											lsModel3.add(r1);
											if(ids.equals("")){
											ids += r1.getId();
											}else {
												ids +=","+r1.getId();
											}
										
											rlistlast.remove(r1);
										}else if(!rlistnext.isEmpty())
										{
											Random rand = new Random();
											Resource r1 = rlistnext.get(rand.nextInt(rlistnext.size()));
										
											ls3.remove(r1);
											r1.setProperty_id(id);
											r1.setReplenish("0");
											lsModel3.add(r1);
											if(ids.equals("")){
											ids += r1.getId();
											}else {
												ids +=","+r1.getId();
											}
										
											rlistnext.remove(r1);
										}
									}								
								}
							}
						}
						
						
						String listen_id = "";
						String see_id = "";
						String read_id = "";
						String play_id = "";
						
						for(int a = 0; a< listen*plan_weekday_num;a++){
							if(ls1.isEmpty() && ls1last.isEmpty() && ls1next.isEmpty()){
								int len = listen*plan_weekday_num - a;
								Resource r = new Resource();
								r.setId(listen_id);
								r.setResource_type_id("1");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquanpre(r);
								if(list!=null){
									int bl = 0;
									for(int c = 0;c<list.size();c++){
										if(bl>=len)
										{
											break;
										}else
										{
											Resource re = list.get(c);
											if(re.getLanguage_level().equals(language+""))
											{
												int b = (a+bl)/listen+1;
												re.setNum(b);
												ls.add(re);
												bl++;
											}
										}
									}
									
									for(int c = 0;c<list.size();c++){
										if(bl>=len)
										{
											break;
										}else
										{
											Resource re = list.get(c);
											if(language-1>0)
											{
												if(re.getLanguage_level().equals((language)-1+""))
												{
													int b = (a+bl)/listen+1;
													re.setNum(b);
													ls.add(re);
													bl++;
												}
											}
										}
									}
									
									for(int c = 0;c<list.size();c++){
										if(bl>=len)
										{
											break;
										}else
										{
											Resource re = list.get(c);
											if(language+1<7)
											{
												if(re.getLanguage_level().equals((language+1)+""))
												{
													int b = (a+bl)/listen+1;
													re.setNum(b);
													ls.add(re);
													bl++;
												}
											}
										}
									}
								}
								break;
							}
							if(!ls1.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls1.get(rand.nextInt(ls1.size()));
								if(a==0){
									listen_id += re.getId();
								}else {
									listen_id +=","+re.getId();
								}
								int b = a/listen+1;
								re.setNum(b);
								ls.add(re);
								ls1.remove(re);
							}else if(!ls1last.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls1last.get(rand.nextInt(ls1last.size()));
								if(a==0){
									listen_id += re.getId();
								}else {
									listen_id +=","+re.getId();
								}
								int b = a/listen+1;
								re.setNum(b);
								ls.add(re);
								ls1last.remove(re);
							}else if(!ls1next.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls1next.get(rand.nextInt(ls1next.size()));
								if(a==0){
									listen_id += re.getId();
								}else {
									listen_id +=","+re.getId();
								}
								int b = a/listen+1;
								re.setNum(b);
								ls.add(re);
								ls1next.remove(re);
							}
							
						}
					
					
						for(int a = 0; a< see*plan_weekday_num;a++){
							if(ls2.isEmpty() && ls2last.isEmpty() && ls2next.isEmpty()){
								int len = see*plan_weekday_num - a;
								Resource r = new Resource();
								r.setId(see_id);
								r.setResource_type_id("2");
								r.setBaby_id(baby_id);
								r.setLen(len);
								List<Resource> list = this.resourceDao.buquanpre(r);
								if(list!=null){
									int bl = 0;
									for(int c = 0;c<list.size();c++){
										if(bl>=len)
										{
											break;
										}else
										{
											Resource re = list.get(c);
											if(re.getLanguage_level().equals(language+""))
											{
												int b = (a+bl)/listen+1;
												re.setNum(b);
												ls.add(re);
												bl++;
											}
										}
									}
									
									for(int c = 0;c<list.size();c++){
										if(bl>=len)
										{
											break;
										}else
										{
											Resource re = list.get(c);
											if(language-1>0)
											{
												if(re.getLanguage_level().equals((language-1)+""))
												{
													int b = (a+bl)/listen+1;
													re.setNum(b);
													ls.add(re);
													bl++;
												}
											}
										}
									}
									
									for(int c = 0;c<list.size();c++){
										if(bl>=len)
										{
											break;
										}else
										{
											Resource re = list.get(c);
											if(language+1<7)
											{
												if(re.getLanguage_level().equals((language+1)+""))
												{
													int b = (a+bl)/listen+1;
													re.setNum(b);
													ls.add(re);
													bl++;
												}
											}
										}
									}
								}
								break;
							}
							if(!ls2.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls2.get(rand.nextInt(ls2.size()));
								if(a==0){
									see_id += re.getId();
								}else {
									see_id +=","+re.getId();
								}
								int b = a/see+1;
								re.setNum(b);
								ls.add(re);
								ls2.remove(re);
							}else if(!ls2last.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls2last.get(rand.nextInt(ls2last.size()));
								if(a==0){
									see_id += re.getId();
								}else {
									see_id +=","+re.getId();
								}
								int b = a/see+1;
								re.setNum(b);
								ls.add(re);
								ls2last.remove(re);
							}else if(!ls2next.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls2next.get(rand.nextInt(ls2next.size()));
								if(a==0){
									see_id += re.getId();
								}else {
									see_id +=","+re.getId();
								}
								int b = a/see+1;
								re.setNum(b);
								ls.add(re);
								ls2next.remove(re);
							}
							
						}
						
						for(int a = 0; a< read*plan_weekday_num;a++){
							if(lsModel3.isEmpty()){
								break;
							}
							Random rand = new Random();
							Resource re = lsModel3.get(rand.nextInt(lsModel3.size()));
							if(a==0){
								read_id += re.getId();
							}else {
								read_id +=","+re.getId();
							}
							int b = a/read+1;
							re.setNum(b);
							ls.add(re);
							lsModel3.remove(re);
						}
						
						for(int a = 0; a< play*plan_weekday_num;a++){
							if(ls4.isEmpty() && ls4last.isEmpty() && ls4next.isEmpty()){
								break;
							}
							if(!ls4.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls4.get(rand.nextInt(ls4.size()));
								if(a==0){
									play_id += re.getId();
								}else {
									play_id +=","+re.getId();
								}
								int b = a/play+1;
								re.setNum(b);
								ls.add(re);
								ls4.remove(re);
							}else if(!ls4last.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls4last.get(rand.nextInt(ls4last.size()));
								if(a==0){
									play_id += re.getId();
								}else {
									play_id +=","+re.getId();
								}
								int b = a/play+1;
								re.setNum(b);
								ls.add(re);
								ls4last.remove(re);
							}else if(!ls4next.isEmpty())
							{
								Random rand = new Random();
								Resource re = ls4next.get(rand.nextInt(ls4next.size()));
								if(a==0){
									play_id += re.getId();
								}else {
									play_id +=","+re.getId();
								}
								int b = a/play+1;
								re.setNum(b);
								ls.add(re);
								ls4next.remove(re);
							}				
						}					
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
							UserLearnplan ulpModel = new UserLearnplan();
							ulp_id = this.userLearnplanDao.getId()+"";
							ulpModel.setId(ulp_id);
							ulpModel.setBaby_id(baby_id);
							ulpModel.setResource_id(r.getId());
							ulpModel.setPlan_id(plan_id);
							ulpModel.setPlan_status("0");
							ulpModel.setPeoperty_id(r.getProperty_id());
							ulpModel.setReplenish(r.getReplenish());
							ulpModel.setPlan_weekday(r.getNum()+"");
							
							if(r.getNum()==1){
								//周六
								if(week==6){
									this.userLearnplanDao.add1(ulpModel);
								}else if(week==7){
									//周日
									this.userLearnplanDao.add2(ulpModel);
								}else{
									//周一到周五
									this.userLearnplanDao.add(ulpModel);
								}
							}else{
								this.userLearnplanDao.add(ulpModel);
							}
						}else{
							ulp_id = r.getUlp_id();
						}
						
						UserLearnplan usp = this.userLearnplanDao.findOne(ulp_id);
						
						Favorite f = new Favorite();
						f.setUser_id(user_id);
						f.setResource_id(r.getId());
						
						int fnum = this.favoriteDao.findNum(f);
						
						List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
						String language_re = this.resourceInfoDao.getResLanguage(r.getId())+"";
						if(type_id.equals("1")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
							data1.add(obj);
						}else if(type_id.equals("2")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							}
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
							data2.add(obj);
						}else if(type_id.equals("4")){
							list = this.resourceInfoDao.findByResourceId(r.getId());
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							
							obj.put("img_start", r.getStart_img()!=null?tobereplace(r.getStart_img(), 0):"");
							obj.put("img_start_size",r.getStart_img_size()!=null?r.getStart_img_size():"");
							obj.put("start_content", r.getStart_content()!=null?r.getStart_content():"");
							obj.put("img_read", r.getRead_img()!=null?tobereplace(r.getRead_img(), 0):"");
							obj.put("img_read_size",r.getRead_img_size()!=null?r.getRead_img_size():"");
							obj.put("read_content", r.getRead_content()!=null?r.getRead_content():"");
							obj.put("img_practise", r.getLian_img()!=null?tobereplace(r.getLian_img(), 0):"");
							obj.put("img_practise_size",r.getLian_img_size()!=null?r.getLian_img_size():"");
							obj.put("practise_content", r.getLian_content()!=null?r.getLian_content():"");
							obj.put("book_content", r.getBook_content()!=null?r.getBook_content():"");
							
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("property", r.getProperty_id()!=null?r.getProperty_id():"");
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
							data3.add(obj);
						}else if(type_id.equals("5")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
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
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
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
					hsm.put("data", data);
					hsm.put("datasign","");
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
			hsm.put("data", data);
			hsm.put("datasign","");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("LearnplanServiceImpl.learnplan执行了"+diff+"毫秒");
		
		return hsm;
	
	}
	
	@Transactional
	public Map relearnplan(HttpServletRequest request) throws Exception {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));


		String result  = "0";
		String message = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
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
		        hsm.put("data", "");
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}else{
				flag = true;
			}
			
			List<Resource> ls = new ArrayList<Resource>();
			
			if(flag){
				//获取plan_id
				int num = this.userLearnplanDao.getPlanId(baby_id);
				if(num==0){
					result  = "2";
					message = initDataPool.getSP("2-4-220");
					hsm.put("version", Constant.version);
			        hsm.put("result", result);
			        hsm.put("message", message);
			        hsm.put("date", date);
			        hsm.put("data", "");
			        hsm.put("datasign", md5(hsm,appKey));
					return hsm;
				}
				String plan_id = num +"";
				
				//
				UserLearnplan ulp = new UserLearnplan();
				ulp.setBaby_id(baby_id);
				ulp.setPlan_id(plan_id);
				
				//删除已有计划
				this.userLearnplanDao.delete(ulp);
				
				//获取符合条件的List
				List<Resource> lsModel = this.resourceDao.learnplan(baby_id);
				
				List<Resource> ls1 = new ArrayList<Resource>();
				List<Resource> ls2 = new ArrayList<Resource>();
				List<Resource> ls3 = new CopyOnWriteArrayList<Resource>();
				List<Resource> lsModel3 = new ArrayList<Resource>();
				List<Resource> ls4 = new ArrayList<Resource>();
				List<Resource> huiben = this.resourceDao.huiben(baby_id);
				
				//分类
				if(lsModel!=null){
				for(Resource r : lsModel){
					if(r.getResource_type_id().equals("1")){
						ls1.add(r);
					}else if(r.getResource_type_id().equals("2")){
						ls2.add(r);
					}else if(r.getResource_type_id().equals("5")){
						ls4.add(r);
					}
				}
				}
				for(Resource rr:huiben){
					ls3.add(rr);
				}
				
				//获取阶段对应的属性
				GlobalProperty gpModel = new GlobalProperty();
				gpModel.setBaby_id(baby_id);
				
				List<GlobalProperty> gpList = this.globalPropertyDao.findByBabyId(gpModel);
				List<GlobalProperty> gpModelList = new ArrayList<GlobalProperty>();
				
				//必选属性
				List<GlobalProperty> bixuangpList = new ArrayList<GlobalProperty>();
				//三个可选项
				GlobalProperty gps = new GlobalProperty();
				gps.setBaby_id(baby_id);
				List<GlobalProperty> NobixuangpList = this.globalPropertyDao.findByLevel(gps);
				for(GlobalProperty gp:gpList){
					if(gp.getProperty_type().equals("1")){
						bixuangpList.add(gp);
					}else{
						gpModelList.add(gp);
					}
				}
				for(int n =0;n<NobixuangpList.size();n++){
					GlobalProperty gp = gpModelList.get(n);
					gpModelList.remove(gp);
				}
				
				//获取阅读配置
				Learnplan lp = this.learnplanDao.findOne(plan_id);
				if(lp==null){
					result  = "2";
					message = initDataPool.getSP("2-4-216");
					hsm.put("version", Constant.version);
			        hsm.put("result", result);
			        hsm.put("message", message);
			        hsm.put("date", date);
			        hsm.put("data", "");
			        hsm.put("datasign", "");
					return hsm;
				}
				
				String plan_weekday = lp.getPlan_weekday();
				int plan_weekday_num = Integer.parseInt(plan_weekday);
				
				String plan_config = lp.getPlan_config();
				JSONObject jo = JSONObject.fromObject(plan_config);
				
				int listen = Integer.parseInt(jo.getString("listen"));
				int see = Integer.parseInt(jo.getString("watch"));
				int read = Integer.parseInt(jo.getString("read"));
				int play = Integer.parseInt(jo.getString("play"));
				
				//选书
				String ids = "";
				//选择可选项
				for(GlobalProperty gp:NobixuangpList){
					int kexuan = Integer.parseInt(gp.getProperty_num());
					//计数
					int d = 0;
					//属性id
					String id = gp.getId();
					if(ls3!=null){
						List<Resource> rlist=new ArrayList<Resource>();
						Iterator it = ls3.iterator();
						while(it.hasNext()){
						Resource r = (Resource) it.next();
						List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
						if(list!=null){
							
							for(ResourceInfo ri :list){
								if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
									rlist.add(r);
								}
								
							}
							
						}
						}
						
						kexuan=rlist.size()>kexuan?kexuan:rlist.size();
						for(int nn=0;nn<kexuan;nn++){
							Random rand = new Random();
							Resource r1 = rlist.get(rand.nextInt(rlist.size()));
							
							ls3.remove(r1);
							r1.setProperty_id(id);
							r1.setReplenish("0");
							lsModel3.add(r1);
							if(ids.equals("")){
								ids += r1.getId();
							}else {
								ids +=","+r1.getId();
							}
							
							rlist.remove(r1);
						}
						
					}
				}
				//选择必选项
				for(GlobalProperty gp:bixuangpList){
					int bixuan = Integer.parseInt(gp.getProperty_num());
					//计数
					int d = 0;
					//属性id
					String id = gp.getId();
					if(ls3!=null){
						List<Resource> rlist=new ArrayList<Resource>();
						Iterator it = ls3.iterator();
						while(it.hasNext()){
						Resource r = (Resource) it.next();
						List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
						if(list!=null){
							for(ResourceInfo ri :list){
								if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
									rlist.add(r);
								}
								
							}
//							for(ResourceInfo ri :list){
//								//
//								if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<bixuan){
//									d++;
//									ls3.remove(r);
//									r.setProperty_id(id);
//									r.setReplenish("0");
//									lsModel3.add(r);
//									if(ids.equals("")){
//										ids += r.getId();
//									}else {
//										ids +=","+r.getId();
//									}
//								}
//							}
						}
						}
						
						bixuan=rlist.size()>bixuan?bixuan:rlist.size();
						for(int nn=0;nn<bixuan;nn++){
							Random rand = new Random();
							Resource r1 = rlist.get(rand.nextInt(rlist.size()));
							
							ls3.remove(r1);
							r1.setProperty_id(id);
							r1.setReplenish("0");
							lsModel3.add(r1);
							if(ids.equals("")){
								ids += r1.getId();
							}else {
								ids +=","+r1.getId();
							}
							
							rlist.remove(r1);
						}
					}
				}
				if(lsModel3.size()<read*plan_weekday_num){
				//补充项
				for(GlobalProperty gp:gpModelList){
					
					if(lsModel3.size()>=read*plan_weekday_num){
						break;
					}
					
					
					int buquan = Integer.parseInt(gp.getProperty_num());
					//计数
					int d = 0;
					//属性id
					String id = gp.getId();
					if(ls3!=null){
						List<Resource> rlist=new ArrayList<Resource>();
						Iterator it = ls3.iterator();
						while(it.hasNext()){
						Resource r = (Resource) it.next();
						List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
						if(list!=null){
							
							
							for(ResourceInfo ri :list){
								if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
									rlist.add(r);
								}
								
							}
							
							
							
							
//							for(ResourceInfo ri :list){
//								//
//								if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<buquan){
//									d++;
//									if(lsModel3.size()>=read*plan_weekday_num){
//										break;
//									}
//									ls3.remove(r);
//									r.setProperty_id(id);
//									r.setReplenish("1");
//									lsModel3.add(r);
//									if(ids.equals("")){
//										ids += r.getId();
//									}else {
//										ids +=","+r.getId();
//									}
//								}
//							}
						}
						}
						
						int cha=read*plan_weekday_num-lsModel3.size();
						buquan=cha>buquan?buquan:cha;
						buquan=rlist.size()>buquan?buquan:rlist.size();
						for(int nn=0;nn<buquan;nn++){
							Random rand = new Random();
							Resource r1 = rlist.get(rand.nextInt(rlist.size()));
							
							ls3.remove(r1);
							r1.setProperty_id(id);
							r1.setReplenish("0");
							lsModel3.add(r1);
							if(ids.equals("")){
								ids += r1.getId();
							}else {
								ids +=","+r1.getId();
							}
							
							rlist.remove(r1);
						}
					}
				}
				
				}
//				//本阶段书不够数据补全
//				if(lsModel3.isEmpty()||lsModel3.size()<read*plan_weekday_num){
//					List<GlobalProperty> bqgpList = this.globalPropertyDao.findByBabyId(gpModel);//属性补全
//					List<Resource> bqls = new CopyOnWriteArrayList<Resource>();
//					List<Resource> bqlsModel = new CopyOnWriteArrayList<Resource>();//绘本补全
//					Resource rModel = new Resource();
//					rModel.setId(ids);
//					rModel.setBaby_id(baby_id);
//					bqlsModel = this.resourceDao.huibenbuquan(rModel);
//					for(Resource rrr:bqlsModel){
//						bqls.add(rrr);
//					}
//					for(GlobalProperty gp:bqgpList){
//						//计数
//						int d = 0;
//						//属性id
//						String id = gp.getId();
//						if(bqls!=null){
//							Iterator it = bqls.iterator();
//							while(it.hasNext()){
//							Resource r = (Resource) it.next();
//							List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
//								for(ResourceInfo ri :list){
//									//
//									if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)&&d<buquan){
//										d++;
//										if(lsModel3.size()>=read*plan_weekday_num){
//											break;
//										}
//										bqls.remove(r);
//										r.setProperty_id(id);
//										lsModel3.add(r);
//									}
//								}
//							}
//						}
//					}
//				}
				
				String listen_id = "";
				String see_id = "";
				String read_id = "";
				String play_id = "";
				
				for(int a = 0; a< listen*plan_weekday_num;a++){
					if(ls1.isEmpty()){
						int len = listen*plan_weekday_num - a;
						Resource r = new Resource();
						r.setId(listen_id);
						r.setResource_type_id("1");
						r.setBaby_id(baby_id);
						r.setLen(len);
						List<Resource> list = this.resourceDao.buquan(r);
						if(list!=null){
							for(int c = 0;c<list.size();c++){
								Resource re = list.get(c);
								int b = (a+c)/listen+1;
								re.setNum(b);
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
					int b = a/listen+1;
					re.setNum(b);
					ls.add(re);
					ls1.remove(re);
				}
			
			
				for(int a = 0; a< see*plan_weekday_num;a++){
					if(ls2.isEmpty()){
						int len = see*plan_weekday_num - a;
						Resource r = new Resource();
						r.setId(see_id);
						r.setResource_type_id("2");
						r.setBaby_id(baby_id);
						r.setLen(len);
						List<Resource> list = this.resourceDao.buquan(r);
						if(list!=null){
							for(int c = 0;c<list.size();c++){
								Resource re = list.get(c);
								int b = (a+c)/listen+1;
								re.setNum(b);
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
					int b = a/see+1;
					re.setNum(b);
					ls.add(re);
					ls2.remove(re);
				}
				
				for(int a = 0; a< read*plan_weekday_num;a++){
					if(lsModel3.isEmpty()){
						break;
					}
					Random rand = new Random();
					Resource re = lsModel3.get(rand.nextInt(lsModel3.size()));
					if(a==0){
						read_id += re.getId();
					}else {
						read_id +=","+re.getId();
					}
					int b = a/read+1;
					re.setNum(b);
					ls.add(re);
					lsModel3.remove(re);
				}
				
				for(int a = 0; a< play*plan_weekday_num;a++){
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
					int b = a/play+1;
					re.setNum(b);
					ls.add(re);
					ls4.remove(re);
				}
				
				if(ls!=null&&!ls.isEmpty()){
					JSONArray data1 = new JSONArray();
					JSONArray data2 = new JSONArray();
					JSONArray data3 = new JSONArray();
					JSONArray data4 = new JSONArray();
					for(int i = 0;i<ls.size();i++){
						Resource r = ls.get(i);
						type_id = r.getResource_type_id();
						String ulp_id = r.getUlp_id();
						UserLearnplan ulpModel = new UserLearnplan();
						ulp_id = this.userLearnplanDao.getId()+"";
						ulpModel.setId(ulp_id);
						ulpModel.setBaby_id(baby_id);
						ulpModel.setResource_id(r.getId());
						ulpModel.setPlan_id(plan_id);
						ulpModel.setPlan_status("0");
						ulpModel.setPeoperty_id(r.getProperty_id());
						ulpModel.setPlan_weekday(r.getNum()+"");
						ulpModel.setReplenish(r.getReplenish());
						
						if(r.getNum()==1){
							//周六
							if(week==6){
								this.userLearnplanDao.add1(ulpModel);
							}else if(week==7){
								//周日
								this.userLearnplanDao.add2(ulpModel);
							}else{
								//周一到周五
								this.userLearnplanDao.add(ulpModel);
							}
						}else{
							this.userLearnplanDao.add(ulpModel);
						}
						
						UserLearnplan usp = this.userLearnplanDao.findOne(ulp_id);
						
						Favorite f = new Favorite();
						f.setUser_id(user_id);
						f.setResource_id(r.getId());
						
						int fnum = this.favoriteDao.findNum(f);
						
						List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
						if(type_id.equals("1")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("status", "0");
							obj.put("spend_minute", "");
							obj.put("fstatus", fnum>0?"1":"0");
							data2.add(obj);
						}else if(type_id.equals("4")){
							list = this.resourceInfoDao.findByResourceId(r.getId());
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("data", !objModel.isEmpty()?objModel:"");
							obj.put("property", r.getProperty_id()!=null?r.getProperty_id():"");
							obj.put("fstatus", fnum>0?"1":"0");
							data3.add(obj);
						}else if(type_id.equals("5")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("spend_minute", "");
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
							obj.put("fstatus", fnum>0?"1":"0");
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
        logger.info("LearnplanServiceImpl.relearnplan执行了"+diff+"毫秒");
		
		return hsm;
	
	}
	
	@Transactional
	public Map relearnplanpre(HttpServletRequest request) throws Exception {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));


		String result  = "0";
		String message = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){
			week = 7;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		JSONObject data = new JSONObject();
		String type_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		Map hsm = new LinkedHashMap();
		try{
			String baby_id = (String) request.getParameter("baby_id");
			String user_id = (String) request.getParameter("uid");
			Baby bbbb= this.babyDao.findOne(baby_id);
			String languages = bbbb.getBaby_language();
			int language = 0;
			if(languages!=null)
				language = Integer.parseInt(languages);
			
			boolean flag = false;
			if(baby_id==null||"".equals(baby_id)){
				result  = "2";
				message = initDataPool.getSP("2-4-211");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        hsm.put("date", date);
		        hsm.put("data", data);
		        hsm.put("datasign", md5(hsm,appKey));
				return hsm;
			}else{
				flag = true;
			}
			
			List<Resource> ls = new ArrayList<Resource>();
			
			if(flag){
				//获取plan_id
				int num = this.userLearnplanDao.getPlanId(baby_id);
				if(num==0){
					result  = "2";
					message = initDataPool.getSP("2-4-220");
					hsm.put("version", Constant.version);
			        hsm.put("result", result);
			        hsm.put("message", message);
			        hsm.put("date", date);
			        hsm.put("data", data);
			        hsm.put("datasign", md5(hsm,appKey));
					return hsm;
				}
				String plan_id = num +"";
				
				//
				UserLearnplan ulp = new UserLearnplan();
				ulp.setBaby_id(baby_id);
				ulp.setPlan_id(plan_id);
				
				//删除已有计划
				this.userLearnplanDao.delete(ulp);
				
				//获取符合条件的List
				List<Resource> lsModel = this.resourceDao.learnplan(baby_id);
				
				List<Resource> ls1 = new ArrayList<Resource>();
				List<Resource> ls1last = new ArrayList<Resource>();
				List<Resource> ls1next = new ArrayList<Resource>();
				List<Resource> ls2 = new ArrayList<Resource>();
				List<Resource> ls2last = new ArrayList<Resource>();
				List<Resource> ls2next = new ArrayList<Resource>();
				List<Resource> ls4 = new ArrayList<Resource>();
				List<Resource> ls4last = new ArrayList<Resource>();
				List<Resource> ls4next = new ArrayList<Resource>();
				
				List<Resource> ls3 = new CopyOnWriteArrayList<Resource>();
				List<Resource> lsModel3 = new ArrayList<Resource>();
				List<Resource> huiben = this.resourceDao.huiben(baby_id);
				
				//分类
				if(lsModel!=null){
					for(Resource r : lsModel){
						if(r.getResource_type_id().equals("1"))
						{
							if(r.getLanguage_level().equals(language+""))
							{
								ls1.add(r);
							}else if(language-1>0)
							{
								if(r.getLanguage_level().equals((language-1)+""))
									ls1last.add(r);
							}else if(language+1<7)
							{
								if(r.getLanguage_level().equals((language+1)+""))
									ls1next.add(r);
							}
						}else if(r.getResource_type_id().equals("2"))
						{
							if(r.getLanguage_level().equals(language+""))
							{
								ls2.add(r);
							}else if(language-1>0)
							{
								if(r.getLanguage_level().equals((language-1)+""))
									ls2last.add(r);
							}else if(language+1<7)
							{
								if(r.getLanguage_level().equals((language+1)+""))
									ls2next.add(r);
							}
						}else if(r.getResource_type_id().equals("5"))
						{
							if(r.getLanguage_level().equals(language+""))
							{
								ls4.add(r);
							}else if(language-1>0)
							{
								if(r.getLanguage_level().equals((language-1)+""))
									ls4last.add(r);
							}else if(language+1<7)
							{
								if(r.getLanguage_level().equals((language+1)+""))
									ls4next.add(r);
							}
						}
					}
				}
				for(Resource rr:huiben){
					ls3.add(rr);
				}
				
				//获取阶段对应的属性
				GlobalProperty gpModel = new GlobalProperty();
				gpModel.setBaby_id(baby_id);
				
				List<GlobalProperty> gpList = this.globalPropertyDao.findByBabyId(gpModel);
				List<GlobalProperty> gpModelList = new ArrayList<GlobalProperty>();
				
				//必选属性
				List<GlobalProperty> bixuangpList = new ArrayList<GlobalProperty>();
				//三个可选项
				GlobalProperty gps = new GlobalProperty();
				gps.setBaby_id(baby_id);
				List<GlobalProperty> NobixuangpList = this.globalPropertyDao.findByLevel(gps);
				for(GlobalProperty gp:gpList){
					if(gp.getProperty_type().equals("1")){
						bixuangpList.add(gp);
					}else{
						gpModelList.add(gp);
					}
				}
				for(int n =0;n<NobixuangpList.size();n++){
					GlobalProperty gp = gpModelList.get(n);
					gpModelList.remove(gp);
				}
				
				//获取阅读配置
				Learnplan lp = this.learnplanDao.findOne(plan_id);
				if(lp==null){
					result  = "2";
					message = initDataPool.getSP("2-4-216");
					hsm.put("version", Constant.version);
			        hsm.put("result", result);
			        hsm.put("message", message);
			        hsm.put("date", date);
			        hsm.put("data", data);
			        hsm.put("datasign", "");
					return hsm;
				}
				
				String plan_weekday = lp.getPlan_weekday();
				int plan_weekday_num = Integer.parseInt(plan_weekday);
				
				String plan_config = lp.getPlan_config();
				JSONObject jo = JSONObject.fromObject(plan_config);
				
				int listen = Integer.parseInt(jo.getString("listen"));
				int see = Integer.parseInt(jo.getString("watch"));
				int read = Integer.parseInt(jo.getString("read"));
				int play = Integer.parseInt(jo.getString("play"));
				
				//选书
				String ids = "";
				//选择可选项
				for(GlobalProperty gp:NobixuangpList){
					int kexuan = Integer.parseInt(gp.getProperty_num());
					//计数
					int d = 0;
					//属性id
					String id = gp.getId();
					if(ls3!=null){
						List<Resource> rlist=new ArrayList<Resource>();
						List<Resource> rlistlast=new ArrayList<Resource>();
						List<Resource> rlistnext=new ArrayList<Resource>();
						Iterator it = ls3.iterator();
						while(it.hasNext())
						{
							Resource r = (Resource) it.next();
							List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
							if(list!=null)
							{									
								for(ResourceInfo ri :list)
								{
									if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id))
									{
										if(r.getLanguage_level().equals(language+""))
										{
											rlist.add(r);
										}else if(language-1>0)
										{
											if(r.getLanguage_level().equals((language-1)+""))
												rlistlast.add(r);
										}else if(language+1<7)
										{
											if(r.getLanguage_level().equals((language+1)+""))
												rlistnext.add(r);
										}
									}							
								}
							}
						}
						
						//kexuan=rlist.size()>kexuan?kexuan:rlist.size();
						for(int nn=0;nn<kexuan;nn++){
							if(rlist.isEmpty() && rlistlast.isEmpty() && rlistnext.isEmpty())
								break;
							if(!rlist.isEmpty())
							{
								Random rand = new Random();
								Resource r1 = rlist.get(rand.nextInt(rlist.size()));
								
								ls3.remove(r1);
								r1.setProperty_id(id);
								r1.setReplenish("0");
								lsModel3.add(r1);
								if(ids.equals("")){
									ids += r1.getId();
								}else {
									ids +=","+r1.getId();
								}									
								rlist.remove(r1);
							}else if(!rlistlast.isEmpty())
							{
								Random rand = new Random();
								Resource r1 = rlistlast.get(rand.nextInt(rlistlast.size()));
								
								ls3.remove(r1);
								r1.setProperty_id(id);
								r1.setReplenish("0");
								lsModel3.add(r1);
								if(ids.equals("")){
									ids += r1.getId();
								}else {
									ids +=","+r1.getId();
								}									
								rlistlast.remove(r1);
							}else if(!rlistnext.isEmpty())
							{
								Random rand = new Random();
								Resource r1 = rlistnext.get(rand.nextInt(rlistnext.size()));
								
								ls3.remove(r1);
								r1.setProperty_id(id);
								r1.setReplenish("0");
								lsModel3.add(r1);
								if(ids.equals("")){
									ids += r1.getId();
								}else {
									ids +=","+r1.getId();
								}									
								rlistnext.remove(r1);
							}
						}								
					}
				}
				//选择必选项
				for(GlobalProperty gp:bixuangpList){
					int bixuan = Integer.parseInt(gp.getProperty_num());
					//计数
					int d = 0;
					//属性id
					String id = gp.getId();
					if(ls3!=null){
						List<Resource> rlist=new ArrayList<Resource>();
						List<Resource> rlistlast=new ArrayList<Resource>();
						List<Resource> rlistnext=new ArrayList<Resource>();
						Iterator it = ls3.iterator();
						while(it.hasNext())
						{
							Resource r = (Resource) it.next();
							List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
							if(list!=null){
//							
								for(ResourceInfo ri :list){
									if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id)){
										if(r.getLanguage_level().equals(language+""))
										{
											rlist.add(r);
										}else if(language-1>0)
										{
											if(r.getLanguage_level().equals((language-1)+""))
												rlistlast.add(r);
										}else if(language+1<7)
										{
											if(r.getLanguage_level().equals((language+1)+""))
												rlistnext.add(r);
										}
									}										
								}
							}
						}
						
						
						//bixuan=rlist.size()>bixuan?bixuan:rlist.size();
						for(int nn=0;nn<bixuan;nn++){
							if(rlist.isEmpty() && rlistlast.isEmpty() && rlistnext.isEmpty())
								break;
							if(!rlist.isEmpty())
							{
								Random rand = new Random();
								Resource r1 = rlist.get(rand.nextInt(rlist.size()));
								
								ls3.remove(r1);
								r1.setProperty_id(id);
								r1.setReplenish("0");
								lsModel3.add(r1);
								if(ids.equals("")){
									ids += r1.getId();
								}else {
									ids +=","+r1.getId();
								}
								rlist.remove(r1);
							}else if(!rlistlast.isEmpty())
							{
								Random rand = new Random();
								Resource r1 = rlistlast.get(rand.nextInt(rlistlast.size()));
								
								ls3.remove(r1);
								r1.setProperty_id(id);
								r1.setReplenish("0");
								lsModel3.add(r1);
								if(ids.equals("")){
									ids += r1.getId();
								}else {
									ids +=","+r1.getId();
								}
								rlistlast.remove(r1);
							}else if(!rlistnext.isEmpty())
							{
								Random rand = new Random();
								Resource r1 = rlistnext.get(rand.nextInt(rlistnext.size()));
								
								ls3.remove(r1);
								r1.setProperty_id(id);
								r1.setReplenish("0");
								lsModel3.add(r1);
								if(ids.equals("")){
									ids += r1.getId();
								}else {
									ids +=","+r1.getId();
								}
								rlistnext.remove(r1);
							}
						}								
					}
				}
				
				if(lsModel3.size()<read*plan_weekday_num)
				{
					//补充项
					for(GlobalProperty gp:gpModelList)
					{
						if(lsModel3.size()>=read*plan_weekday_num){
							break;
						}
						int buquan = Integer.parseInt(gp.getProperty_num());
						//计数
						int d = 0;
						//属性id
						String id = gp.getId();
						if(ls3!=null)
						{
							List<Resource> rlist=new ArrayList<Resource>();
							List<Resource> rlistlast=new ArrayList<Resource>();
							List<Resource> rlistnext=new ArrayList<Resource>();
							Iterator it = ls3.iterator();
							while(it.hasNext())
							{
								Resource r = (Resource) it.next();
								List<ResourceInfo> list = this.resourceInfoDao.findPropertyId(r.getId());
								if(list!=null)
								{
									for(ResourceInfo ri :list)
									{
										if(ri.getProperty_id()!=null&&ri.getProperty_id().equals(id))
										{									
											if(r.getLanguage_level().equals(language+""))
											{
												rlist.add(r);
											}else if(language-1>0)
											{
												if(r.getLanguage_level().equals((language-1)+""))
													rlistlast.add(r);
											}else if(language+1<7)
											{
												if(r.getLanguage_level().equals((language+1)+""))
													rlistnext.add(r);
											}
										}									
									}
								}
							}
						
							int cha=read*plan_weekday_num-lsModel3.size();
							buquan=cha;//>buquan?buquan:cha;
							//buquan=rlist.size()>buquan?buquan:rlist.size();
							for(int nn=0;nn<buquan;nn++){
								if(rlist.isEmpty() && rlistlast.isEmpty() && rlistnext.isEmpty())
									break;
								if(!rlist.isEmpty())
								{
									Random rand = new Random();
									Resource r1 = rlist.get(rand.nextInt(rlist.size()));
								
									ls3.remove(r1);
									r1.setProperty_id(id);
									r1.setReplenish("0");
									lsModel3.add(r1);
									if(ids.equals("")){
									ids += r1.getId();
									}else {
										ids +=","+r1.getId();
									}
								
									rlist.remove(r1);
								}else if(!rlistlast.isEmpty())
								{
									Random rand = new Random();
									Resource r1 = rlistlast.get(rand.nextInt(rlistlast.size()));
								
									ls3.remove(r1);
									r1.setProperty_id(id);
									r1.setReplenish("0");
									lsModel3.add(r1);
									if(ids.equals("")){
									ids += r1.getId();
									}else {
										ids +=","+r1.getId();
									}
								
									rlistlast.remove(r1);
								}else if(!rlistnext.isEmpty())
								{
									Random rand = new Random();
									Resource r1 = rlistnext.get(rand.nextInt(rlistnext.size()));
								
									ls3.remove(r1);
									r1.setProperty_id(id);
									r1.setReplenish("0");
									lsModel3.add(r1);
									if(ids.equals("")){
									ids += r1.getId();
									}else {
										ids +=","+r1.getId();
									}
								
									rlistnext.remove(r1);
								}
							}								
						}
					}
				}
				
				String listen_id = "";
				String see_id = "";
				String read_id = "";
				String play_id = "";
				
				for(int a = 0; a< listen*plan_weekday_num;a++){
					if(ls1.isEmpty() && ls1last.isEmpty() && ls1next.isEmpty()){
						int len = listen*plan_weekday_num - a;
						Resource r = new Resource();
						r.setId(listen_id);
						r.setResource_type_id("1");
						r.setBaby_id(baby_id);
						r.setLen(len);
						List<Resource> list = this.resourceDao.buquanpre(r);
						if(list!=null){
							int bl = 0;
							for(int c = 0;c<list.size();c++){
								if(bl>=len)
								{
									break;
								}else
								{
									Resource re = list.get(c);
									if(re.getLanguage_level().equals(language+""))
									{
										int b = (a+bl)/listen+1;
										re.setNum(b);
										ls.add(re);
										bl++;
									}
								}
							}
							
							for(int c = 0;c<list.size();c++){
								if(bl>=len)
								{
									break;
								}else
								{
									Resource re = list.get(c);
									if(language-1>0)
									{
										if(re.getLanguage_level().equals((language-1)+""))
										{
											int b = (a+bl)/listen+1;
											re.setNum(b);
											ls.add(re);
											bl++;
										}
									}
								}
							}
							
							for(int c = 0;c<list.size();c++){
								if(bl>=len)
								{
									break;
								}else
								{
									Resource re = list.get(c);
									if(language+1<7)
									{
										if(re.getLanguage_level().equals((language+1)+""))
										{
											int b = (a+bl)/listen+1;
											re.setNum(b);
											ls.add(re);
											bl++;
										}
									}
								}
							}
						}
						break;
					}
					if(!ls1.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls1.get(rand.nextInt(ls1.size()));
						if(a==0){
							listen_id += re.getId();
						}else {
							listen_id +=","+re.getId();
						}
						int b = a/listen+1;
						re.setNum(b);
						ls.add(re);
						ls1.remove(re);
					}else if(!ls1last.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls1last.get(rand.nextInt(ls1last.size()));
						if(a==0){
							listen_id += re.getId();
						}else {
							listen_id +=","+re.getId();
						}
						int b = a/listen+1;
						re.setNum(b);
						ls.add(re);
						ls1last.remove(re);
					}else if(!ls1next.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls1next.get(rand.nextInt(ls1next.size()));
						if(a==0){
							listen_id += re.getId();
						}else {
							listen_id +=","+re.getId();
						}
						int b = a/listen+1;
						re.setNum(b);
						ls.add(re);
						ls1next.remove(re);
					}
					
				}
			
			
				for(int a = 0; a< see*plan_weekday_num;a++){
					if(ls2.isEmpty() && ls2last.isEmpty() && ls2next.isEmpty()){
						int len = see*plan_weekday_num - a;
						Resource r = new Resource();
						r.setId(see_id);
						r.setResource_type_id("2");
						r.setBaby_id(baby_id);
						r.setLen(len);
						List<Resource> list = this.resourceDao.buquanpre(r);
						if(list!=null){
							int bl = 0;
							for(int c = 0;c<list.size();c++){
								if(bl>=len)
								{
									break;
								}else
								{
									Resource re = list.get(c);
									if(re.getLanguage_level().equals(language+""))
									{
										int b = (a+bl)/listen+1;
										re.setNum(b);
										ls.add(re);
										bl++;
									}
								}
							}
							
							for(int c = 0;c<list.size();c++){
								if(bl>=len)
								{
									break;
								}else
								{
									Resource re = list.get(c);
									if(language-1>0)
									{
										if(re.getLanguage_level().equals((language-1)+""))
										{
											int b = (a+bl)/listen+1;
											re.setNum(b);
											ls.add(re);
											bl++;
										}
									}
								}
							}
							
							for(int c = 0;c<list.size();c++){
								if(bl>=len)
								{
									break;
								}else
								{
									Resource re = list.get(c);
									if(language+1<7)
									{
										if(re.getLanguage_level().equals((language+1)+""))
										{
											int b = (a+bl)/listen+1;
											re.setNum(b);
											ls.add(re);
											bl++;
										}
									}
								}
							}
						}
						break;
					}
					if(!ls2.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls2.get(rand.nextInt(ls2.size()));
						if(a==0){
							see_id += re.getId();
						}else {
							see_id +=","+re.getId();
						}
						int b = a/see+1;
						re.setNum(b);
						ls.add(re);
						ls2.remove(re);
					}else if(!ls2last.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls2last.get(rand.nextInt(ls2last.size()));
						if(a==0){
							see_id += re.getId();
						}else {
							see_id +=","+re.getId();
						}
						int b = a/see+1;
						re.setNum(b);
						ls.add(re);
						ls2last.remove(re);
					}else if(!ls2next.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls2next.get(rand.nextInt(ls2next.size()));
						if(a==0){
							see_id += re.getId();
						}else {
							see_id +=","+re.getId();
						}
						int b = a/see+1;
						re.setNum(b);
						ls.add(re);
						ls2next.remove(re);
					}
					
				}
				
				for(int a = 0; a< read*plan_weekday_num;a++){
					if(lsModel3.isEmpty()){
						break;
					}
					Random rand = new Random();
					Resource re = lsModel3.get(rand.nextInt(lsModel3.size()));
					if(a==0){
						read_id += re.getId();
					}else {
						read_id +=","+re.getId();
					}
					int b = a/read+1;
					re.setNum(b);
					ls.add(re);
					lsModel3.remove(re);
				}
				
				for(int a = 0; a< play*plan_weekday_num;a++){
					if(ls4.isEmpty() && ls4last.isEmpty() && ls4next.isEmpty()){
						break;
					}
					if(!ls4.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls4.get(rand.nextInt(ls4.size()));
						if(a==0){
							play_id += re.getId();
						}else {
							play_id +=","+re.getId();
						}
						int b = a/play+1;
						re.setNum(b);
						ls.add(re);
						ls4.remove(re);
					}else if(!ls4last.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls4last.get(rand.nextInt(ls4last.size()));
						if(a==0){
							play_id += re.getId();
						}else {
							play_id +=","+re.getId();
						}
						int b = a/play+1;
						re.setNum(b);
						ls.add(re);
						ls4last.remove(re);
					}else if(!ls4next.isEmpty())
					{
						Random rand = new Random();
						Resource re = ls4next.get(rand.nextInt(ls4next.size()));
						if(a==0){
							play_id += re.getId();
						}else {
							play_id +=","+re.getId();
						}
						int b = a/play+1;
						re.setNum(b);
						ls.add(re);
						ls4next.remove(re);
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
						String ulp_id = r.getUlp_id();
						UserLearnplan ulpModel = new UserLearnplan();
						ulp_id = this.userLearnplanDao.getId()+"";
						ulpModel.setId(ulp_id);
						ulpModel.setBaby_id(baby_id);
						ulpModel.setResource_id(r.getId());
						ulpModel.setPlan_id(plan_id);
						ulpModel.setPlan_status("0");
						ulpModel.setPeoperty_id(r.getProperty_id());
						ulpModel.setPlan_weekday(r.getNum()+"");
						ulpModel.setReplenish(r.getReplenish());
						
						if(r.getNum()==1){
							//周六
							if(week==6){
								this.userLearnplanDao.add1(ulpModel);
							}else if(week==7){
								//周日
								this.userLearnplanDao.add2(ulpModel);
							}else{
								//周一到周五
								this.userLearnplanDao.add(ulpModel);
							}
						}else{
							this.userLearnplanDao.add(ulpModel);
						}
						
						UserLearnplan usp = this.userLearnplanDao.findOne(ulp_id);
						
						Favorite f = new Favorite();
						f.setUser_id(user_id);
						f.setResource_id(r.getId());
						
						int fnum = this.favoriteDao.findNum(f);
						
						List<ResourceInfo> list = this.resourceInfoDao.findByResourceId(r.getId());
						String language_re = this.resourceInfoDao.getResLanguage(r.getId())+"";
						if(type_id.equals("1")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
							data1.add(obj);
						}else if(type_id.equals("2")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("data", objModel);
							obj.put("status", "0");
							obj.put("spend_minute", "");
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
							data2.add(obj);
						}else if(type_id.equals("4")){
							list = this.resourceInfoDao.findByResourceId(r.getId());
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							
							obj.put("img_start", r.getStart_img()!=null?tobereplace(r.getStart_img(), 0):"");
							obj.put("img_start_size",r.getStart_img_size()!=null?r.getStart_img_size():"");
							obj.put("start_content", r.getStart_content()!=null?r.getStart_content():"");
							obj.put("img_read", r.getRead_img()!=null?tobereplace(r.getRead_img(), 0):"");
							obj.put("img_read_size",r.getRead_img_size()!=null?r.getRead_img_size():"");
							obj.put("read_content", r.getRead_content()!=null?r.getRead_content():"");
							obj.put("img_practise", r.getLian_img()!=null?tobereplace(r.getLian_img(), 0):"");
							obj.put("img_practise_size",r.getLian_img_size()!=null?r.getLian_img_size():"");
							obj.put("practise_content", r.getLian_content()!=null?r.getLian_content():"");
							obj.put("book_content", r.getBook_content()!=null?r.getBook_content():"");
							
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
							obj.put("status", r.getPlan_status()!=null?r.getPlan_status():"0");
							obj.put("spend_minute", r.getSpend_minute()!=null?r.getSpend_minute():"");
							obj.put("data", objModel);
							obj.put("property", r.getProperty_id()!=null?r.getProperty_id():"");
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
							data3.add(obj);
						}else if(type_id.equals("5")){
							JSONObject obj = new JSONObject();
							obj.put("weekday", usp.getPlan_weekday());
							obj.put("between", usp.getPlan_date());
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
							obj.put("spend_minute", "");
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
							obj.put("data", objModel);
							obj.put("fstatus", fnum>0?"1":"0");
							obj.put("language", language_re!=null?language_re:"");
							obj.put("between_age", r.getBetween_age()!=null?r.getBetween_age():"");
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
					hsm.put("data", data);
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
			hsm.put("data", data);
			hsm.put("datasign", "");
			throw new RuntimeException(date.toString());
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("LearnplanServiceImpl.relearnplan执行了"+diff+"毫秒");
		
		return hsm;
	
	}

	@Transactional
	public Map updatelearnplan(HttpServletRequest request){
		
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
				
				UserLearnplan upModel = this.userLearnplanDao.findOne(id);
				if(upModel.getPlan_status().equals("0")){
				
					//更新状态
					UserLearnplan ulp = new UserLearnplan();
					ulp.setId(id);
					ulp.setSpend_minute(spend_minute);
					ulp.setPlan_status("1");
					this.userLearnplanDao.update(ulp);
					
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
			throw new RuntimeException("updatelearnplan---积分增加出现异常");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("LearnplanServiceImpl.updatelearnplan执行了"+diff+"毫秒");
        
		return hsm;
	}
	
	public LearnplanDao getLearnplanDao() {
		return learnplanDao;
	}

	public void setLearnplanDao(LearnplanDao learnplanDao) {
		this.learnplanDao = learnplanDao;
	}

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public UserLearnPlanDao getUserLearnplanDao() {
		return userLearnplanDao;
	}

	public void setUserLearnplanDao(UserLearnPlanDao userLearnplanDao) {
		this.userLearnplanDao = userLearnplanDao;
	}
	
	public String tobereplace(String message, int in) throws Exception {
		String path = GlobalHobbyController.class.getClassLoader().getResource("").toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF/Config.xml";
		String bereplace = Xml.getXmlTagValue(path, "messageimagebereplace");
		String replace = Xml.getXmlTagValue(path, "messageimagereplace");
		if (in == 0) {
			if(CollectionUtil.checkNull(message)){
			message = message.replaceAll(replace, bereplace);
			}else{
				message = "";
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
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public ResourceInfoDao getResourceInfoDao() {
		return resourceInfoDao;
	}

	public void setResourceInfoDao(ResourceInfoDao resourceInfoDao) {
		this.resourceInfoDao = resourceInfoDao;
	}

	public UserBillingDao getUserBillingDao() {
		return userBillingDao;
	}

	public void setUserBillingDao(UserBillingDao userBillingDao) {
		this.userBillingDao = userBillingDao;
	}

	
}
