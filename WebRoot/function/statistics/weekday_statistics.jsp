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
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript">
   function sel(){
    	$('#formsubmit').attr('action','<%=basePath %>weekdayresource/doweekdaystatistics');   
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
	    <li>全局统计</li>
	    <li>每周推荐统计</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>weekdayresource/doweekdaystatistics" class="selected">每周推荐统计</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>周期：</label>
    	<select id="weekday_id" name="weekday_id" class="select select180">
	    	<option value="all">全部</option>
	   		<c:if test="${wrlist!=null}">
	   			<c:forEach var="wk" items="${wrlist}">
	   				<option value="${wk.id }" <c:if test="${wk.id==weekday_id}">selected</c:if> >${wk.start_date }至${wk.end_date }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
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
    	<th>周期ID</th>
    	<th>开始时间</th>
    	<th>结束时间</th>
        <th>宝贝数目</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${weekdayresourceList!=null}">
    	<c:forEach var="weekday" items="${weekdayresourceList}">
    		<tr>
    			<td><c:out value="${weekday.weekday_id}"/></td>
				<td><c:out value="${weekday.start_date}"/></td>
				<td><c:out value="${weekday.end_date}"/></td>
				<td><c:out value="${weekday.baby_id}"/></td>
    		</tr>
    	</c:forEach>
    	</c:if>
    	<c:if test="${requestScope.msg!=null}">
			<tr >
				<td colspan="6" style="color:red;">${requestScope.msg}</td>
			</tr>
		</c:if>
    	</tbody>
    </table>
    ${pageinfo}
    </div>
    <script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>
