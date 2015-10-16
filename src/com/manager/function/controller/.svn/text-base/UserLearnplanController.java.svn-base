package com.manager.function.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.admin.page.PageUtil;
import com.manager.admin.service.AdminLogService;
import com.manager.function.entity.Learnplan;
import com.manager.function.entity.UserLearnplan;
import com.manager.function.service.LearnplanService;
import com.manager.function.service.UserLearnplanService;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;

@RequestMapping("userlearnplan")
@Controller
public class UserLearnplanController {

	private Logger logger = Logger.getLogger(UserLearnplanController.class);

	@Autowired
	private AdminLogService adminLogService;

	@Autowired
	private UserLearnplanService userLearnplanService;
	@Autowired
	private LearnplanService learnplanService;
	
	
	public void selectCondition(HttpServletRequest request){
		List<UserLearnplan> statisticsDate=this.userLearnplanService.get_statistics_date();
		request.setAttribute("statisticsDate", statisticsDate);
		List<Learnplan> planList=this.learnplanService.findLearnplanList();
		request.setAttribute("planList", planList);
	}
	
	
	/**
	 * 进入学习计划统计页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tolearnplanstatistics")
	public String toInsertUserLearnplan(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			selectCondition(request);
			return "function/statistics/learnplan_statistics";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 进入学习计划统计曲线图页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tolearnplanstatistics_curve")
	public String toUserLearnplan_curve(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			selectCondition(request);
			return "function/statistics/learnplan_statistics_curve";
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 学习计划统计曲线图查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dolearnplanstatistics_curve")
	public String doLearnplanStatistics_curve(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String plan_id=(String)request.getParameter("plan_id");
			String monday_date=(String)request.getParameter("monday_date");
			String sunday_date=(String)request.getParameter("sunday_date");
			String cIndex=request.getParameter("index");
			plan_id=plan_id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(plan_id.getBytes("ISO-8859-1"),"UTF-8"):plan_id,"UTF-8");
			monday_date=monday_date==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(monday_date.getBytes("ISO-8859-1"),"UTF-8"):monday_date,"UTF-8");
			sunday_date=sunday_date==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(sunday_date.getBytes("ISO-8859-1"),"UTF-8"):sunday_date,"UTF-8");
			
			request.setAttribute("plan_id", plan_id);
			request.setAttribute("monday_date", monday_date);
			request.setAttribute("sunday_date", sunday_date);
			
			UserLearnplan UserLearnplan=new UserLearnplan();
			
			if(CollectionUtil.checkNull(plan_id) && !"all".equals(plan_id)){
				UserLearnplan.setPlan_id(plan_id);
			}
			if(CollectionUtil.checkNull(monday_date) && !"all".equals(monday_date)){
				UserLearnplan.setMonday_date(monday_date);
			}
			if(CollectionUtil.checkNull(sunday_date) && !"all".equals(sunday_date)){
				UserLearnplan.setSunday_date(sunday_date);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/userlearnplan/dolearnplanstatistics_curve?plan_id="+java.net.URLEncoder.encode(plan_id,"UTF-8")
			+"&monday_date="+java.net.URLEncoder.encode(monday_date,"UTF-8")
			+"&sunday_date="+java.net.URLEncoder.encode(sunday_date,"UTF-8");
			//获得该页集合
			List<UserLearnplan> UserLearnplanList=this.userLearnplanService.get_statistics("3",UserLearnplan);
			String ioc=UserLearnplanList==null?null:getIOC(UserLearnplanList);
			
			request.setAttribute("UserLearnplan_IOC", ioc);
			
			selectCondition(request);
			
			//记录日志
			memo+="学习计划统计曲线图查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_qjtj, memo, state, request);
			
		}catch(Exception e){
			memo+="学习计划统计曲线图查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_qjtj, memo, state, request);
		}
		return "function/statistics/learnplan_statistics_curve";
	}
	
	//拼接曲线公用方法
	public static String getIOC(List<UserLearnplan> list){
		StringBuffer bf=new StringBuffer();
		int num=list.size();
		 bf.append("<chart caption='学习计划统计(统计记录行数"+num+",每行记录间隔7天)' outCnvbaseFontSize='12' xAxisName='宝贝数/周期'  subcaption='' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='2' divLineAlpha='50' divLineColor='0879BF' divLineIsDashed='1' alternateHGridColor='053EC9' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='0879BF'  shadowAlpha='40' labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,74BDE8' bgAngle='270' >");
//		bf.append("<chart caption='一个月爱传世充值曲线图(￥)' outCnvbaseFontSize='12'   subcaption=' ' xAxisName='月份/日期' yAxisName='充值金额' yAxisMinValue='15000' numberPrefix='$' showValues='0' alternateHGridColor='FCB541' alternateHGridAlpha='20' divLineColor='FCB541' divLineAlpha='50' canvasBorderColor='666666' baseFontColor='666666' lineColor='FCB541'>");
		
		 bf.append("<categories>");
		 int x=1;
		 if(num>20){
			 x=num/20;
		 }
		for (int i = list.size()-1; i>=0 ;i--) {
			UserLearnplan u= list.get(i);
			if(i%x==0){
				bf.append("<category label='"+u.getStatistics_cycle()+"'/>");
			}else{
				bf.append("<category label=''/>");
				
			}
		}
		bf.append("</categories>");
		bf.append("<dataset seriesName='参与宝贝' color='175D08' anchorBorderColor='175D08' anchorBgColor='175D08'>");			
		for (int i = list.size()-1; i>=0 ;i--) {
			UserLearnplan u= list.get(i);
			bf.append("<set value='"+u.getStatistics_baby()+"'/>");
		}
		bf.append("</dataset>");
		
		bf.append("<dataset seriesName='完成宝贝' color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C'>");			
		for (int i = list.size()-1; i>=0 ;i--) {
			UserLearnplan u= list.get(i);
			bf.append("<set value='"+u.getStatistics_finish()+"'/>");
		}
		bf.append("</dataset>");
		
		bf.append("<styles><definition><style name='CaptionFont' type='font' size='12' /></definition> <application><apply toObject='CAPTION' styles='CaptionFont'/><apply toObject='SUBCAPTION' styles='CaptionFont' /></application></styles>");
//		bf.append("<styles><definition><style name='Anim1' type='animation' param='_xscale' start='0' duration='1' /><style name='Anim2' type='animation' param='_alpha' start='0' duration='0.6' /><style name='DataShadow' type='Shadow' alpha='40'/></definition><application><apply toObject='DIVLINES' styles='Anim1' /><apply toObject='HGRID' styles='Anim2' /><apply toObject='DATALABELS' styles='DataShadow,Anim2' /></application></styles>");
		
		bf.append("</chart>");
		return bf.toString();
	}
		
	
	
	/**
	 * 学习计划统计查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dolearnplanstatistics")
	public String doLearnplanStatistics(Model model,HttpServletRequest request,HttpServletResponse response){
		int state=0;
		String memo="";
		try{
			
			String plan_id=(String)request.getParameter("plan_id");
			String monday_date=(String)request.getParameter("monday_date");
			String sunday_date=(String)request.getParameter("sunday_date");
			String cIndex=request.getParameter("index");
			plan_id=plan_id==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(plan_id.getBytes("ISO-8859-1"),"UTF-8"):plan_id,"UTF-8");
			monday_date=monday_date==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(monday_date.getBytes("ISO-8859-1"),"UTF-8"):monday_date,"UTF-8");
			sunday_date=sunday_date==null?"":java.net.URLDecoder.decode(cIndex!=null?new String(sunday_date.getBytes("ISO-8859-1"),"UTF-8"):sunday_date,"UTF-8");
			
			request.setAttribute("plan_id", plan_id);
			request.setAttribute("monday_date", monday_date);
			request.setAttribute("sunday_date", sunday_date);
			
			UserLearnplan UserLearnplan=new UserLearnplan();
			
			if(CollectionUtil.checkNull(plan_id) && !"all".equals(plan_id)){
				UserLearnplan.setPlan_id(plan_id);
			}
			if(CollectionUtil.checkNull(monday_date) && !"all".equals(monday_date)){
				UserLearnplan.setMonday_date(monday_date);
			}
			if(CollectionUtil.checkNull(sunday_date) && !"all".equals(sunday_date)){
				UserLearnplan.setSunday_date(sunday_date);
			}
			
			//当前页数
			int currentIndex=1;
			if(CollectionUtil.checkNull(cIndex)){
				currentIndex=Integer.parseInt(cIndex);
			}
			request.setAttribute("index", currentIndex);
			//url
			String url="/userlearnplan/dolearnplanstatistics?plan_id="+java.net.URLEncoder.encode(plan_id,"UTF-8")
			+"&monday_date="+java.net.URLEncoder.encode(monday_date,"UTF-8")
			+"&sunday_date="+java.net.URLEncoder.encode(sunday_date,"UTF-8");
			//总行数
			int dataCount=this.userLearnplanService.get_statistics_count("2", UserLearnplan);
			//获得该页集合
			List<UserLearnplan> UserLearnplanList=this.userLearnplanService.get_statistics("2",UserLearnplan,(currentIndex-1)*PageUtil.PAGECOUNT,PageUtil.PAGECOUNT);
			//获取翻页拼接html
			String pageinfo = PageUtil.pageUtil(dataCount, currentIndex, url, request);
		    request.setAttribute("pageinfo", pageinfo);
		    
			if(UserLearnplanList!=null && UserLearnplanList.size()>0){
				request.setAttribute("userlearnplanList", UserLearnplanList);
			}
			
			selectCondition(request);
			
			//记录日志
			memo+="学习计划统计查询";
			state=1;
			this.adminLogService.addAdminLog(Constant.log_qjtj, memo, state, request);
			
		}catch(Exception e){
			memo+="学习计划统计查询,异常："+e.getMessage();
			logger.error(memo,e);
			this.adminLogService.addAdminLog(Constant.log_qjtj, memo, state, request);
		}
		return "function/statistics/learnplan_statistics";
	}
	
	

	public AdminLogService getAdminLogService() {
		return adminLogService;
	}

	public void setAdminLogService(AdminLogService adminLogService) {
		this.adminLogService = adminLogService;
	}


	/**
	 * @return the UserLearnplanService
	 */
	public UserLearnplanService getUserLearnplanService() {
		return userLearnplanService;
	}


	/**
	 * @param UserLearnplanService the UserLearnplanService to set
	 */
	public void setUserLearnplanService(UserLearnplanService userLearnplanService) {
		this.userLearnplanService = userLearnplanService;
	}

}
