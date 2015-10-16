<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
         $(".ui_timepicker2").datetimepicker({
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
    	$('#formsubmit').attr('action','<%=basePath%>adminUser/doadminloglist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').attr('action','<%=basePath%>adminUser/expadminloglist');   
    	$('#formsubmit').submit();
    }
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath%>index.jsp">首页</a></li>
	    <li>操作日志</li>
	    <li>操作日志列表</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="formtextlist">提示：只导出前5000行记录</div>
    <!-- 查询条件 -->    
    <div class="formbody" >
    	 <form id="formsubmit" action="" method="post" >
	    <label>后台帐号：</label><input id="adminAccount" name="adminAccount" type="text" class="dfinput150" value="${adminAccount }"/><i></i>
	    <label>操作业务：</label>
	    <select id="operService" name="operService" class="select select151" >
	    	<option value="all">全部</option>
	    	<c:if test="${servicelist!=null}">
		   		<c:forEach items="${servicelist}" var="service">
		   		<option value="<c:out value='${service}'/>" <c:if test="${service==operService}">selected</c:if>><c:out value="${service}"/></option>
		   		</c:forEach>
	   		</c:if>
	    </select>
	    <label>操作结果：</label>
	     <select id="openState" name="openState" class="select select151" >
	    	<option value="all">全部</option>
		   		<option value="1" <c:if test="${'1'==openState}">selected</c:if> >成功</option>
		   		<option value="0" <c:if test="${'0'==openState}">selected</c:if> >失败</option>
	    </select>
	    <label>时间段：</label>
	    <input type="text" name="startDate" class="ui_timepicker dfinput150" value="${startDate }"/>~
	    <input type="text" name="endDate" class="ui_timepicker2 dfinput150" value="${endDate }"/>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    &nbsp;
	    <c:if test="${adminLogList!=null}">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	    
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
    	<th>帐号名</th>
        <th>操作业务</th>
        <th>ip地址</th>
        <th>操作描述</th> 
        <th>操作日期</th>
        <th>操作结果</th>
        </tr>
        </thead>
        
        <tbody>
    	<c:if test="${adminLogList!=null}">
    	<c:forEach var="AdminLog" items="${adminLogList}">
    		<tr>
				<td><c:out value="${AdminLog.operAdmin}"/></td>
    			<td><c:out value="${AdminLog.operService}"/></td>
    			<td><c:out value="${AdminLog.operIp}"/></td>
    			<td width="40%" title="<c:out value="${AdminLog.operMemo}"/>"><div class="div_textflow500"><c:out value="${AdminLog.operMemo}"/></div></td>
    			<td><c:out value="${AdminLog.operDate}"/></td>
    			<td>
    				<c:if test="${AdminLog.openState=='0'}"><font style="color:red;">失败</font></c:if>
    				<c:if test="${AdminLog.openState=='1'}"><font style="color:green;">成功</font></c:if>
    			</td>
    		</tr>
    	</c:forEach>
    	</c:if>
    	</tbody>
    </table>
    ${pageinfo}
    </div>
    <script type="text/javascript" src="<%=basePath%>js/css.js"></script>
  </body>
</html>
