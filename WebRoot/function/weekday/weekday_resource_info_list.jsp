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
    	$('#formsubmit').attr('action','<%=basePath %>weekdayresource/doweekdayresourceinfolist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>weekdayresource/expweekdayresourcelist');   
    	$('#formsubmit').submit();
    }
    
    function deleteweekdayresource(id){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>weekdayresource/dodeleteweekdayresource",
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
	    <li>每周推荐</li>
	    <li>每周推荐查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist" class="selected">每周推荐查询</a></li> 
	    <!-- 
	    <li><a href="<%=basePath %>weekdayresource/toinsertweekdayresource?weekday_id=${weekday_id }">每周推荐编辑</a></li>
	     --> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<!-- <label>周期ID：</label><input id="weekday_id" name="weekday_id" type="text" class="dfinput150" value="${weekday_id }"/>
    	 -->
    	<label>周期：</label>
    	<select id="weekday_id" name="weekday_id" class="select select180">
	    	<option value="all">全部</option>
	   		<c:if test="${wrlist!=null}">
	   			<c:forEach var="weekday" items="${wrlist}">
	   				<option value="${weekday.id }" <c:if test="${weekday.id==weekday_id}">selected</c:if> >${weekday.start_date }至${weekday.end_date }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	    <label>宝贝ID：</label><input id="baby_id" name="baby_id" type="text" class="dfinput150" value="${baby_id }"/>
	    <label>类型：</label>
    	<select id="resource_type_id" name="resource_type_id" class="select select151">
    		<option value="all">全部</option>
	   		<c:if test="${noabolish_resourcetype!=null}">
	   			<c:forEach var="resourcetype" items="${noabolish_resourcetype}">
	   				<option value="${resourcetype.id }" <c:if test="${resourcetype.id==resource_type_id}">selected</c:if> >${resourcetype.type_name }</option>
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
    	<th>ID</th>
        <th>创建时间</th>
        <th>创建管理员</th>
        <th>周期</th>
    	<th>开始时间</th>
    	<th>结束时间</th>
    	<th>宝贝ID</th>
    	<th>宝贝昵称</th>
    	<th>资源类型</th>
    	<th>资源ID</th>
        <th>资源名称</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${weekdayresourceList!=null}">
    	<c:forEach var="weekdayresource" items="${weekdayresourceList}">
    		<tr>
    			<td><c:out value="${weekdayresource.id}"/></td>
				<td><c:out value="${weekdayresource.create_date}"/></td>
				<td><c:out value="${weekdayresource.create_adminuser}"/></td>
    			<td><c:out value="${weekdayresource.weekday_id}"/></td>
				<td><c:out value="${weekdayresource.start_date}"/></td>
				<td><c:out value="${weekdayresource.end_date}"/></td>
				<td><c:out value="${weekdayresource.baby_id}"/></td>
				<td><c:out value="${weekdayresource.baby_nickname}"/></td>
				<td><c:out value="${weekdayresource.type_name}"/></td>
				<td><c:out value="${weekdayresource.resource_id}"/></td>
				<td><c:out value="${weekdayresource.resource_content}"/></td>
    			<td>
    				<a href="<%=basePath %>resource/toupdateresource?id=${weekdayresource.resource_id}" class="tablelink">资源详情</a>  
    				<!--  
    				<a href="javascript:deleteweekdayresource('<c:out value="${weekdayresource.id}"/>')" class="tablelink">删除</a>
    				 -->  
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
