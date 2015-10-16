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
    	$('#formsubmit').attr('action','<%=basePath %>resourcetype/doresourcetypelist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>resourcetype/expresourcetypelist');   
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
             url: "<%=basePath %>resourcetype/doupdatestatusresourcetype",
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
  
  function deleteresourcetype(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>resourcetype/dodeteleresourcetype",
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
	    <li>资源管理</li>
	    <li>资源类型查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>resourcetype/toresourcetypelist" class="selected">资源类型查询</a></li> 
	    <!-- 
	    <li><a href="<%=basePath %>resourcetype/toinsertresourcetype">资源类型添加</a></li> 
	     -->
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>序列号：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>状态：</label>
    	<select id="type_status" name="type_status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==type_status}">selected</c:if> >对内</option>
		   		<option value="1" <c:if test="${'1'==type_status}">selected</c:if> >对外</option>
		   		<option value="9" <c:if test="${'9'==type_status}">selected</c:if> >作废</option>
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
        <th>类型序列号</th>
    	<th>类型名称</th>
    	<th>创建时间</th>
    	<th>创建管理员</th>
        <th>更新时间</th>
        <th>更新管理员</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${resourceTypeList!=null}">
    	<c:forEach var="resourcetype" items="${resourceTypeList}">
    		<tr>
    			<td><c:out value="${resourcetype.id}"/></td>
				<td><c:out value="${resourcetype.type_name}"/></td>
				<td><c:out value="${resourcetype.create_date}"/></td>
				<td><c:out value="${resourcetype.create_adminuser}"/></td>
				<td><c:out value="${resourcetype.update_date}"/></td>
				<td><c:out value="${resourcetype.update_adminuser}"/></td>
    			<td>
    				<c:if test="${resourcetype.type_status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${resourcetype.type_status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${resourcetype.type_status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
    				<a href="<%=basePath %>resourcetype/toupdateresourcetype?id=<c:out value='${resourcetype.id}'/>" class="tablelink">修改</a>     
			        <a style="display: <c:if test='${resourcetype.type_status=="0"}'>none</c:if>" href="javascript:updatetypestatus('<c:out value="${resourcetype.id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${resourcetype.type_status=="1"}'>none</c:if>" href="javascript:updatetypestatus('<c:out value="${resourcetype.id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${resourcetype.type_status=="9"}'>none</c:if>" href="javascript:updatetypestatus('<c:out value="${resourcetype.id}"/>','9');" class="tablelink">作废</a>
			        <!-- <a href="javascript:deleteType('<c:out value="${resourcetype.id}"/>')" class="tablelink">删除</a> -->
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
