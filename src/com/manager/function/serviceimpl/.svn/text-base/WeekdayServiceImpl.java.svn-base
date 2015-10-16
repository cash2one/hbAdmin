package com.manager.function.serviceimpl;

import java.util.List;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.WeekdayDao;
import com.manager.function.entity.Weekday;
import com.manager.function.service.WeekdayService;
import com.manager.util.CollectionUtil;

public class WeekdayServiceImpl implements WeekdayService {
	
	@Autowired
	private WeekdayDao weekdayDao;
	

	public int deleteWeekday(String id) {
		Weekday Weekday=new Weekday();
		Weekday.setId(id);
		return this.weekdayDao.deleteWeekday(Weekday);
	}

	public int findWeekdayCount(Weekday Weekday) {
		return this.weekdayDao.findWeekdayCount(Weekday);
	}

	public List<Weekday> findWeekdayList(Weekday Weekday, int pageNo, int pageSize) {
		return setWeekdayJSON(this.weekdayDao.findWeekdayList(Weekday, pageNo, pageSize));
	}
	
	public List<Weekday> findWeekdayList(Weekday Weekday) {
		return this.weekdayDao.findWeekdayList(Weekday);
	}

	public List<Weekday> findWeekdayList() {
		return this.weekdayDao.findWeekdayList(null);
	}
	
	public int insertWeekday(Weekday Weekday) {
		String weekday_config=getWeekdayJSON(Weekday);
		Weekday.setWeekday_config(weekday_config);
		return this.weekdayDao.insertWeekday(Weekday);
	}
	
	public int updateWeekday(Weekday Weekday) {
		String weekday_config=getWeekdayJSON(Weekday);
		Weekday.setWeekday_config(weekday_config);
		return this.weekdayDao.updateWeekday(Weekday);
	}
	
	public String getWeekdayJSON(Weekday weekday){
		JSONObject jobj=new JSONObject();
		jobj.put("listen", weekday.getListen());
		jobj.put("watch", weekday.getWatch());
		jobj.put("read", weekday.getRead());
		jobj.put("play", weekday.getPlay());
		return jobj.toString();
	}
	
	public List<Weekday> setWeekdayJSON(List<Weekday> wlist){
		if(wlist!=null && wlist.size()>0){
			for(int i=0;i<wlist.size();i++){
				String config=wlist.get(i).getWeekday_config();
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

	public List<Weekday> SelectWeekdayList(){
		return this.weekdayDao.SelectWeekdayList();
	}
	
	public Weekday findWeekdayOne(String id){
		Weekday Weekday=new Weekday();
		Weekday.setId(id);
		List<Weekday> WeekdayList=this.weekdayDao.findWeekdayList(Weekday);
		if(WeekdayList!=null && WeekdayList.size()>0){
			List<Weekday> wlist=setWeekdayJSON(WeekdayList);
			return wlist.get(0);
		}
		return null;
	}
	
	public int checkWeekday(String weekday_id){
		Weekday Weekday=new Weekday();
		Weekday.setId(weekday_id);
		Weekday w=this.weekdayDao.findWeekday(Weekday);
		if(w!=null && !"".equals(w.getId())){
			return 1;
		}
		return 0;
	}
	
	public int checkWeekday(String start_date,String end_date){
		Weekday w=new Weekday();
		w.setStart_date(start_date);
		w.setEnd_date(end_date);
		List<Weekday> wList=this.weekdayDao.findWeekdayList(w);
		int ii=0;
		if(wList!=null && wList.size()>0){
			ii=wList.size();
		}
		return ii;
	}
	
	public int checkWeekday(String id,String start_date,String end_date){
		Weekday w=new Weekday();
		w.setStart_date(start_date);
		w.setEnd_date(end_date);
		List<Weekday> bewsTypeList=this.weekdayDao.findWeekdayList(w);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					Weekday bmt=bewsTypeList.get(i);
					if(id.equals(bmt.getId())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}


	/**
	 * @return the WeekdayDao
	 */
	public WeekdayDao getWeekdayDao() {
		return weekdayDao;
	}

	/**
	 * @param WeekdayDao the WeekdayDao to set
	 */
	public void setWeekdayDao(WeekdayDao weekdayDao) {
		this.weekdayDao = weekdayDao;
	}

}
