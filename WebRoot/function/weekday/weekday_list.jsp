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
    	$('#formsubmit').attr('action','<%=basePath %>weekday/doweekdaylist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>weekday/expweekdaylist');   
    	$('#formsubmit').submit();
    }
    
    function deleteweekday(id){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>weekday/dodeleteweekday",
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
	    <li>周期查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>weekday/doweekdaylist" class="selected">周期查询</a></li> 
	    <li><a href="<%=basePath %>weekday/toinsertweekday">周期添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>周期：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
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
    	<th>看数目</th>
    	<th>听数目</th>
    	<th>读数目</th>
    	<th>玩数目</th>
        <th>创建时间</th>
        <th>创建管理员</th>
    	<th>修改时间</th>
    	<th>修改管理员</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${weekdayList!=null}">
    	<c:forEach var="weekday" items="${weekdayList}">
    		<tr>
    			<td><c:out value="${weekday.id}"/></td>
				<td><c:out value="${weekday.start_date}"/></td>
				<td><c:out value="${weekday.end_date}"/></td>
				<td><c:out value="${weekday.listen}"/></td>
				<td><c:out value="${weekday.watch}"/></td>
				<td><c:out value="${weekday.read}"/></td>
				<td><c:out value="${weekday.play}"/></td>
				<td><c:out value="${weekday.create_date}"/></td>
				<td><c:out value="${weekday.create_admin}"/></td>
				<td><c:out value="${weekday.update_date}"/></td>
				<td><c:out value="${weekday.update_admin}"/></td>
    			<td>
    				<a href="<%=basePath %>weekday/toupdateweekday?id=${weekday.id}" class="tablelink">修改</a>     
    				<!-- <a href="javascript:deleteweekday('<c:out value="${weekday.id}"/>')" class="tablelink">删除</a> -->
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
