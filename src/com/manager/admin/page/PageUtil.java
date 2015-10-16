package com.manager.admin.page;

import javax.servlet.http.HttpServletRequest;


public class PageUtil {

	public static final int PAGECOUNT=10;
	
    public static String pageUtil(int dataCount,int currentIndex,String urlpath,HttpServletRequest request) throws Exception {
    	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
    //
    int pageSize=PAGECOUNT;
	//计算一共显示多少页
	String none = "";
	int pageNum = dataCount % pageSize == 0 ? dataCount / pageSize : dataCount / pageSize + 1;
	StringBuffer bff = new StringBuffer();
	bff.append("<div class=\"pagin\">");
	bff.append("<div id=\"content_table_info\" class=\"message\">");
	bff.append("共 <i class=\"blue\">"+ dataCount +"</i> 条记录，共<i class=\"blue\">"+pageNum+"</i>页，当前显示第&nbsp;<i class=\"blue\">"+currentIndex+"&nbsp;</i>页");
	bff.append("</div>");
	if(pageNum == 0){
	    none = "none";
	}
	bff.append("<ul class=\"paginList\"  style=\"display:"+none+"\" > ");
	
	bff.append("<li class=\"paginItem\"><a href=\""+basePath+ urlpath +"&index=" +1+ "\"><span class=\"\">首页</span></a></li>");
	int sortindex = 1;
	int sortend = 7;
	if(pageNum<=7){
	    sortindex = 1;
	    sortend = pageNum;
	}else{
	    if(currentIndex>=7){
		sortindex = currentIndex -3;
		sortend = currentIndex + 3 > pageNum ? pageNum : currentIndex + 3;
	    }
	}
	for(int i=sortindex; i<=sortend; i++){
	    if(i == currentIndex){
		bff.append("<li class=\"paginItem current\"><a href=\""+basePath+ urlpath +"&index=" +i+ "\">"+i+"</a></li>");
	    }else{
		bff.append("<li class=\"paginItem\"><a href=\""+basePath+ urlpath +"&index=" +i+ "\">"+i+"</a></li>");
	    }
	}
	bff.append(" <li class=\"paginItem\"><a href=\""+basePath+ urlpath +"&index=" +pageNum+ "\"><span class=\"\">尾页</span></a></li>");
	bff.append("</div>");
	return bff.toString();
    }
}
