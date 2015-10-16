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
    	$('#formsubmit').attr('action','<%=basePath %>userlearnplan/dolearnplanstatistics');   
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
	    <li>学习计划统计</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>userlearnplan/tolearnplanstatistics" class="selected">学习计划统计</a></li> 
	    <li><a href="<%=basePath %>userlearnplan/tolearnplanstatistics_curve" >学习计划统计曲线图</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <div class="formtextlist">提示：周末两天开启的计划，归类到下周参与</div>
    <form id="formsubmit" action="" method="post" >
    	<label>计划：</label>
    	<select id="plan_id" name="plan_id" class="select select180">
	   		<c:if test="${planList!=null}">
	   			<c:forEach var="plan" items="${planList}">
	   				<option value="${plan.id }" <c:if test="${plan.id==plan_id}">selected</c:if> >${plan.id }-${plan.plan_content }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	    <label>时间：</label>
    	<select id="monday_date" name="monday_date" class="select select180">
	   		<c:if test="${statisticsDate!=null}">
	   			<c:forEach var="plandate" items="${statisticsDate}">
	   				<option value="${plandate.monday_date }" <c:if test="${plandate.monday_date==monday_date}">selected</c:if> >${plandate.monday_date}</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	    ~
	    <select id="sunday_date" name="sunday_date" class="select select180">
	   		<c:if test="${statisticsDate!=null}">
	   			<c:forEach var="plandate" items="${statisticsDate}">
	   				<option value="${plandate.sunday_date }" <c:if test="${plandate.sunday_date==sunday_date}">selected</c:if> >${plandate.sunday_date}</option>
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
    	<th>计划ID</th>
    	<th>周期</th>
    	<th>参与宝贝总数</th>
        <th>完成计划宝贝总数</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${userlearnplanList!=null}">
    	<c:forEach var="userlearnplan" items="${userlearnplanList}">
    		<tr>
    			<td><c:out value="${userlearnplan.plan_id}"/></td>
				<td><c:out value="${userlearnplan.statistics_cycle}"/></td>
				<td><c:out value="${userlearnplan.statistics_baby}"/></td>
				<td><c:out value="${userlearnplan.statistics_finish}"/></td>
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
