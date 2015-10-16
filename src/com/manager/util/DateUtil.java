package com.manager.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Miller
 * @version 0.6
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
    private static String shortDateFormatPatten = "yyyy/MM/dd";
    private static String longDateFormatPatten = "yyyy/MM/dd HH:mm:ss:SS";
    private static String shortDateFormatPatten2="yyyy-MM-dd";
    private static String shortDateFormatPatten3="yyyy-MM-dd HH:mm:ss:SS";
    private static String shortDateFormatPatten4="yyyyMMddHHmmss";
    private static String shortDateFormatPatten5="yyyyMMdd";
    private static String shortDateFormatPatten6="yyyy-MM-dd HH:mm:ss";
   
    private static String DateFormatPatten2="yyyy-MM";
    private static String DateFormatPatten3="HH:mm";
    private static String DateFormatPatten4="yyyy-MM-dd HH:mm";
    private static String DateFormatPatten5="yyyyMMdd";
    private static String DateFormatPatten6="yyyy-MM-dd HH:mm:ss";
    
    public DateUtil() {
    }

    public static Date getCurrentDate() {
        GregorianCalendar ca = new GregorianCalendar();
        return ca.getTime();
    }

    public static String getCurrentDateAsString(String reqFormatPatten) {
        String formatPatten = shortDateFormatPatten;
        if (reqFormatPatten != null)
            formatPatten = reqFormatPatten;
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten);
        return dateFormat.format(getCurrentDate());
    }

    public static Date parse1(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten);
        return dateFormat.parse(dateString);
    }
    public static Date parse2(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten2);
        return dateFormat.parse(dateString);
    }
    public static Date parse3(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten3);
        return dateFormat.parse(dateString);
    }
    ////////////////////////
    public static Date parse4(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten4);
        return dateFormat.parse(dateString);
    }
    
    public static Date parse5(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten5);
        return dateFormat.parse(dateString);
    }
    
    public static Date parse6(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten6);
        return dateFormat.parse(dateString);
    }
    
    public static String format(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten);
        return dateFormat.format(date);
    }
    public static String formatDateYYYYMM(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatPatten2);
        return dateFormat.format(date);
    }
    
    public static String formatTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatPatten3);
        return dateFormat.format(date);
    }
    
    public static String formatTimeHHMM(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatPatten4);
        return dateFormat.format(date);
    }
    
    public static String formatDate3(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten3);
        return dateFormat.format(date);
    }
    
    public static String formatDate5(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatPatten5);
        return dateFormat.format(date);
    }
    
    public static String formatDate6(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatPatten6);
        return dateFormat.format(date);
    }
 ////////////----------------------
    public static String formatshort(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten2);
        return dateFormat.format(date);
    }
    public static String format(String dateStr, int offset) {
    	Date date = new Date(dateStr);
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DATE,offset);
        DateFormat dateFormat = new SimpleDateFormat(shortDateFormatPatten);
        return dateFormat.format(c.getTime());
    }
    
    public static String formatLong(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longDateFormatPatten);
        return dateFormat.format(date);
    }
    
    public static String newyear(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String year = TimeFull(String.valueOf(c.get(Calendar.YEAR)));
    	String newyear=year.substring(2,4);
    	return newyear;
    }
    
    public static String year(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String year = TimeFull(String.valueOf(c.get(Calendar.YEAR)));
    	return year;
    }
    
    public static String shortyear(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String year = TimeFull(String.valueOf(c.get(Calendar.YEAR))); 
    	String newyear=year.substring(2,4);
    	return newyear;
    }
    
    public static String month(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String month = TimeFull(String.valueOf(c.get(Calendar.MONTH)+1));
    	return month;
    }

    public static String minute(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String minute = TimeFull(String.valueOf(c.get(Calendar.MINUTE)));
    	return minute;
    }
    
    public static String second(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String second = TimeFull(String.valueOf(c.get(Calendar.SECOND)));
    	return second;
    }
    public static String misecond(Date date) {
    	
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String	misecond = TimeFull2(String.valueOf(c.get(Calendar.MILLISECOND)));
        return misecond;
    }
    public static String hour(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String hour = TimeFull(String.valueOf(c.get(Calendar.HOUR)));
    	return hour;
    }
    public static String date(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String day = TimeFull(String.valueOf(c.get(Calendar.DATE)));
    	return day;
    }
    
    public static Date dateOffset(Date date, int offset) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DATE,offset);
        return c.getTime();
    }
    
    public static Date yearOffset(Date date, int offset){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.YEAR,offset);
        return c.getTime();
    }
    
    public static Date monthOffset(Date date, int offset){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MONTH,offset);
        return c.getTime();
    }
    
    public static Date hourOffset(Date date, int offset){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.HOUR,offset);
        return c.getTime();
    }
    
    public static Date minOffset(Date date, int offset){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MINUTE,offset);
        return c.getTime();
    }
    
    public static boolean isLastDayOfMonth(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int year = c.get(Calendar.YEAR);
    	int month = c.get(Calendar.MONTH);
    	int day = c.get(Calendar.DAY_OF_MONTH);
    	
    	c.set(year,month+1,1);
    	c.add(Calendar.DATE,-1);
    	int maxDay = c.get(Calendar.DAY_OF_MONTH);
    	
    	if(maxDay == day)
    		return true;
    	return false;
    }
    
    public static String calcHMS(Date beginday,Date endday ) {
        int hours, minutes, seconds;
        int timeInSeconds=ElapsedMillis(beginday,endday).intValue();
        String remintime=null;
        hours =(timeInSeconds / 3600);
        if(hours>24)
        	remintime=hours/24+1+"��";
        else
        {
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        timeInSeconds = timeInSeconds - (minutes * 60);
        seconds = timeInSeconds;
        remintime=hours+1 + "Сʱ";
        }
        return  remintime;
     }
    
    public static Long ElapsedMillis(Date beginday,Date endday)
    {
    	long l1 = beginday.getTime();
        long l2 = endday.getTime();
        long difference = l2 - l1;
        Long seconds = difference / 1000;
        return seconds;
    }

    /**
     * ����λ��
     * @return java.lang.String
     * @param str java.lang.String
     */
    private static String TimeFull(String str) {
        if (str.length() < 2)
          str = "0".concat(str);
        return str;
    }
    
    private static String TimeFull2(String str) {
        if (str.length() < 2)
          str = "00".concat(str);
        if(str.length()<3)
          str = "0".concat(str);
        return str;
    }
    
    /*
     * @得到月的最后一天.
     */
    
    //jia de ff................
    public static String LastDayOfMonth(int j){
    	int k=0;
		if(j==1) k=1;
		if(j==2) k=0;
		if(j==3) k=-1;
    	
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int year = c.get(Calendar.YEAR);
    	int month = c.get(Calendar.MONTH)+k;
    	int day = c.get(Calendar.DAY_OF_MONTH);
    	c.set(year,month,1);
    	c.add(Calendar.DATE,-1);
    	int maxDay = c.get(Calendar.DAY_OF_MONTH);
    	
    	return year+"-"+month+"-"+maxDay;
    }
    /*
     * @得到月的第一天.
     */
    
    public static String FirstDayOfMonth(int j){
    	
    	int k=0;
		if(j==1) k=1;
		if(j==2) k=0;
		if(j==3) k=-1;
    	
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	
    	int year = c.get(Calendar.YEAR );
    	int month = c.get(Calendar.MONTH )+k;
    	int day = c.get(Calendar.DAY_OF_MONTH );
    	
    	c.set(year,month,1);
    	int minDay = c.get(Calendar.DAY_OF_MONTH);
    	
    	return year+"-"+month+"-"+minDay;
    }
    
	/**
	 * 生命周期
	 * 
	 * @param start
	 * @param end
	 * @return 0 D 0 H
	 */
	public static long Interval(Date start, Date end)
	{
		// 计算离最后登录相差时间 几天 几小时
		long days = 0;
		long hours = 0;
		
		if(start!=null && end!=null)			
		{
			long HOUR = 60L * 60L * 1000L;
			long allhours = (end.getTime() - start.getTime()) / HOUR;
			days = allhours / 24;
			hours = allhours - 24 * (days);
		}
		return days;
	}
	
	/**
	 * 获得2时间相差多少分钟
	 * 
	 * @param start
	 * @param end
	 * @return 0 D 0 H
	 */
	public static long getminute(Date start, Date end)
	{
		// 计算离最后登录相差时间几分钟 
		long minutes = 0;
		
		if(start!=null && end!=null)			
		{
			long MINUTE =  60L * 1000L;
			long allminutes = (end.getTime() - start.getTime()) / MINUTE;
			minutes = allminutes;
		}
		return minutes;
	}
      
	public static void main(String[] args) {
		Date date = new Date();
	    try {
	      date = parse3("2008-7-1 12:14:00:00");
	    }
	    catch (ParseException e) {
	      e.printStackTrace();
	    }

	    Date nowdate = new Date();

	    Calendar c = Calendar.getInstance();

	    c.setTime(nowdate);
	    c.add(2, -7);
	}
}