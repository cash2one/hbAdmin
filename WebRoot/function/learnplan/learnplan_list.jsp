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
    	$('#formsubmit').attr('action','<%=basePath %>learnplan/dolearnplanlist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>learnplan/explearnplanlist');   
    	$('#formsubmit').submit();
    }
    
    function deletelearnplan(id){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>learnplan/dodeletelearnplan",
	             data: {
	             		'id':id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
		             		alert(data.su);
		             	}else{
		             		alert(msg);
		             	}
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
	         if(succ==1){
	         	window.location.reload();
	         }  		
  		}
 	 }
    
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>学习计划</li>
	    <li>计划配置查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>learnplan/dolearnplanlist" class="selected">计划配置查询</a></li> 
	    <li><a href="<%=basePath %>learnplan/toinsertlearnplan">计划配置添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
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
    	<th>ID</th>
    	<th>计划内容</th>
    	<th>计划说明</th>
    	<th>看数目</th>
    	<th>听数目</th>
    	<th>读数目</th>
    	<th>玩数目</th>
    	<th>执行周期</th>
        <th>创建时间</th>
        <th>创建管理员</th>
    	<th>修改时间</th>
    	<th>修改管理员</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${learnplanList!=null}">
    	<c:forEach var="learnplan" items="${learnplanList}">
    		<tr>
    			<td><c:out value="${learnplan.id}"/></td>
				<td width="15%" title="<c:out value="${learnplan.plan_content}"/>">
					<div class="div_textflow150"><c:out value="${learnplan.plan_content}"/></div>
				</td>
				<td width="15%" title="<c:out value="${learnplan.plan_summary}"/>">
					<div class="div_textflow150"><c:out value="${learnplan.plan_summary}"/></div>
				</td>
				<td><c:out value="${learnplan.listen}"/></td>
				<td><c:out value="${learnplan.watch}"/></td>
				<td><c:out value="${learnplan.read}"/></td>
				<td><c:out value="${learnplan.play}"/></td>
				<td><c:out value="${learnplan.plan_weekday}"/></td>
				<td><c:out value="${learnplan.create_date}"/></td>
				<td><c:out value="${learnplan.create_adminuser}"/></td>
				<td><c:out value="${learnplan.update_date}"/></td>
				<td><c:out value="${learnplan.update_adminuser}"/></td>
    			<td>
    				<a href="<%=basePath %>learnplan/toupdatelearnplan?id=${learnplan.id}" class="tablelink">修改</a>     
    				<!-- <a href="javascript:deletelearnplan('<c:out value="${learnplan.id}"/>')" class="tablelink">删除</a> -->
    			</td>
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
