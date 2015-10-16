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
    	$('#formsubmit').attr('action','<%=basePath %>resourceinfo/doresourceinfolist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>resourceinfo/expresourceinfolist');   
    	$('#formsubmit').submit();
    }
    
    function updatetypestatus(id,type_status){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		//if(type_status=='1'){
  		//	type_status='0';
  		//}else{
  		//	type_status='1';
  		//}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>resourceinfo/doupdatestatusresourceinfo",
             data: {
             		'id':id,
             		'type_status':type_status
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
  
  function deleteresourceinfo(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>resourceinfo/dodeleteresourceinfo",
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
  
  	function add(resource_id){
  		var resource_type='';
  		var succ=0;
  		$.ajax({
	             type: "post",
	             url: "<%=basePath %>resourceinfo/check_info_add",
	             data: {
	             		'resource_id':resource_id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
		             		resource_type=data.resource_type;
		             	}else if(msg=='0'){
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
	  		window.location.href="<%=basePath%>resourceinfo/toinsertresourceinfo?id="+resource_id+"&resource_type="+resource_type;
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
	    <li>资源管理</li>
	    <li>资源详情查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	  	<li><a href="<%=basePath%>resource/doresourcelist?id=<c:out value='${resource.id}'/>">资源查询</a></li> 
	    <li><a href="<%=basePath %>resource/toupdateresource?id=<c:out value='${resource.id}'/>" >资源修改</a></li> 
	    <li><a href="<%=basePath %>resourceinfo/doresourceinfolist?resource_id=${resource.id }" class="selected">${resource.type_name } 资源详情查询</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
	    <label>&nbsp;</label><input name="" type="button" onclick="add('${resource.id }')" class="btn" value="添加"/>
	    &nbsp;
	    <c:if test="1!=1">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	     <input type="hidden" id="resource_id" name="resource_id" value="${resource.id }"  class="dfinput" />
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
	        <th>序列号</th>
	    	<th>资源ID</th>
	    	<th>详情类型</th>
	    	<c:if test="${resource.resource_type_id=='3'}">
	    	<th>动画集数</th>
	    	</c:if>
	    	<th>资源文件</th>
	    	<c:if test="${resource.resource_type_id=='1'}">
	    	<th>歌词文件</th>
	    	</c:if>
	    	<c:if test="${resource.resource_type_id!='1' && resource.resource_type_id!='2'}">
	    	<th>排序</th>
	    	</c:if>
	    	<th>创建时间</th>
	    	<th>创建管理员</th>
	        <th>更新时间</th>
	        <th>更新管理员</th>
	        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${resourceinfoList!=null}">
    	<c:forEach var="resourceinfo" items="${resourceinfoList}">
    		<tr>
    			<td><c:out value="${resourceinfo.id}"/></td>
				<td><c:out value="${resourceinfo.resource_id}"/></td>
				<td>
					<c:if test="${resourceinfo.resource_type=='1'}">音频</c:if>
					<c:if test="${resourceinfo.resource_type=='2'}">视频</c:if>
					<c:if test="${resourceinfo.resource_type=='3'}">图片</c:if>
					<c:if test="${resourceinfo.resource_type=='4'}">HTML5</c:if>
				</td>
				<c:if test="${resource.resource_type_id=='3'}">
				<td><c:out value="${resourceinfo.set_number}"/></td>
				</c:if>
				<td width="20%" title="<c:out value="${resourceinfo.resource_url}"/>">
				<c:if test="${resourceinfo.resource_type=='1' || resourceinfo.resource_type=='2' || resourceinfo.resource_type=='4'}">
					<a href="<c:out value="${resourceinfo.resource_url}"/>" target= "_blank">
						<div class="div_textflow200"><c:out value="${resourceinfo.resource_url}"/></div>
					</a>
				</c:if>
				<c:if test="${resourceinfo.resource_type=='3'}">
					<a href="<c:out value="${resourceinfo.resource_url}"/>" target= "_blank">
						<img src="<c:out value="${resourceinfo.resource_url}"/>" alt="" width="100" heigth="61"/>
					</a>
				</c:if>
				</td>
				<c:if test="${resource.resource_type_id=='1'}">
				<td width="20%" title="<c:out value="${resourceinfo.resource_lyrics}"/>">
					<a href="<c:out value="${resourceinfo.resource_lyrics}"/>" target= "_blank">
						<div class="div_textflow200"><c:out value="${resourceinfo.resource_lyrics}"/></div>
					</a>
				</td>
				</c:if>
				<c:if test="${resource.resource_type_id!='1' && resource.resource_type_id!='2'}">
				<td><c:out value="${resourceinfo.resource_sort}"/></td>
				</c:if>
				<td><c:out value="${resourceinfo.create_date}"/></td>
				<td><c:out value="${resourceinfo.create_adminuser}"/></td>
				<td><c:out value="${resourceinfo.update_date}"/></td>
				<td><c:out value="${resourceinfo.update_adminuser}"/></td>
    			<td>
    				<a href="<%=basePath %>resourceinfo/toupdateresourceinfo?id=<c:out value='${resourceinfo.id}'/>&resource_id=<c:out value='${resource.id}'/>" class="tablelink">修改</a>     
			       <!-- <a href="javascript:deleteresourceinfo('<c:out value="${resourceinfo.id}"/>')" class="tablelink">删除</a> --> 
    			</td>
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
