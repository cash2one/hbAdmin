<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="/include/base.jsp" />
<jsp:include page="/include/date.jsp" />
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript">
   $(function () {
        $(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            changeYear: true,showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        })
    })
   function sel(){
    	$('#formsubmit').attr('action','<%=basePath %>searchlogs/dosearchlogslist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>searchlogs/expsearchlogslist');   
    	$('#formsubmit').submit();
    }
    
   
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>搜索管理</li>
	    <li>搜索日志查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>searchlogs/dosearchlogslist" class="selected">搜索日志查询</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>序列号：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>IP：</label><input id="search_ip" name="search_ip" type="text" class="dfinput150" value="${search_ip }"/>
    	<label>用户ID：</label><input id="user_id" name="user_id" type="text" class="dfinput150" value="${user_id }"/>
    	<label>时间段：</label>
	    <input type="text" name="startDate" class="ui_timepicker dfinput150" value="${startDate }"/>~
	    <input type="text" name="endDate" class="ui_timepicker dfinput150" value="${endDate }"/>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    &nbsp;
	    <c:if test="1!=1">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	    
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
        <th>序列号</th>
    	<th>用户ID</th>
        <th>搜索值</th>
        <th>IP地址</th>
        <th>搜索时间</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${searchLogsList!=null}">
    	<c:forEach var="searchlogs" items="${searchLogsList}">
    		<tr>
    			<td><c:out value="${searchlogs.id}"/></td>
				<td><c:out value="${searchlogs.user_id}"/></td>
				<td><c:out value="${searchlogs.search_value}"/></td>
				<td><c:out value="${searchlogs.search_ip}"/></td>
				<td><c:out value="${searchlogs.search_date}"/></td>
    		</tr>
    	</c:forEach>
    	</c:if>
    	</tbody>
    </table>
    ${pageinfo}
    </div>
    <script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>
