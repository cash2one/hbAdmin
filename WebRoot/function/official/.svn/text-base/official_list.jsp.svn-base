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
    	$('#formsubmit').attr('action','<%=basePath %>official/doofficiallist');   
    	$('#formsubmit').submit();
    }
    
    
  function deleteofficial(id){
  		if(id==null || ''==id){
  			alert("ID不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除ID为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>official/dodeteleofficial",
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
	    <li>官方贴管理</li>
	    <li>官方贴查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>official/doofficiallist" class="selected">官方贴查询</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>用户ID：</label><input id="yonghuid" name="yonghuid" type="text" class="dfinput150" value="${yonghuid }"/>
    	<label>页数：</label><input id="page" name="page" type="text" class="dfinput150" value="${page }"/>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
        <th>ID</th>
    	<th>用户ID</th>
    	<th>部落ID</th>
    	<th>帖子标题</th>
    	<th>查看数</th>
    	<th>评论数</th>
        <th>发帖时间</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${tieziList!=null}">
    	<c:forEach var="tiezi" items="${tieziList}">
    		<tr>
				<td><c:out value="${tiezi.id}"/></td>
    			<td><c:out value="${tiezi.yonghuid}"/></td>
				<td><c:out value="${tiezi.buluoid}"/></td>
				<td><c:out value="${tiezi.biaoti}"/></td>
				<td><c:out value="${tiezi.chakanshu}"/></td>
				<td><c:out value="${tiezi.pinglunshu}"/></td>
				<td><c:out value="${tiezi.created}"/></td>
				<td><c:out value="${tiezi.zhuangtai}"/></td>
    			<td>
    				<a href="<%=basePath %>official/toupdateofficial?id=<c:out value='${tiezi.id}'/>&yonghuid=<c:out value='${tiezi.yonghuid}'/>" class="tablelink">修改</a>     
    			</td>
    		</tr>
    	</c:forEach>
    	</c:if>
    	</tbody>
    </table>
    </div>
    <script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>
