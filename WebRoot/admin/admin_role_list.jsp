<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="/include/base.jsp" />
  <head>
  <script type="text/javascript">
  	function toupdate(roleId,roleName){
  		if(roleId==null || ''==roleId){
  			alert("角色ID不能为空");
  			return false;
  		}
  		if(roleName==null || ''==roleName){
  			alert("角色名不能为空");
  			return false;
  		}
  		window.location.href="<%=basePath %>adminRole/toupdateadminrole?roleId="+roleId+"&roleName="+encodeURI(encodeURI(roleName));
  	}
  	
  	function deleterole(roleId,roleName){
  		if(roleId==null || ''==roleId){
  			alert("角色ID不能为空");
  			return false;
  		}
  		if(roleName==null || ''==roleName){
  			alert("角色名不能为空");
  			return false;
  		}
  		if(confirm("您确定要删除角色为："+roleName+"吗？")){
  			var succ=0;
  			$.ajax({
	             type: "post",
	             url: "<%=basePath %>adminRole/todeleteadminrole?callback=?",
	             data: {
	             		'roleId':roleId
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg==1){
		             		alert("删除角色成功！");
		             		succ=1;
		             	}else{
		             		alert(msg);
		             	}
		             	//$("#msg").html(msg);
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
  	
  	function sel(){
  		$("#formsubmit").attr("action","<%=basePath %>adminRole/doadminrolelist");
  		$("#formsubmit").submit();
  	}
  	
  	function exp(){
  		$("#formsubmit").attr("action","<%=basePath %>adminRole/expadminrolelist");
  		$("#formsubmit").submit();
  	}
  	
  	function updateState(roleId,roleState){
  		if(roleId==null || ''==roleId){
  			alert("角色ID不能为空！");
  			return false;
  		}
  		if(roleState=='1'){
  			roleState='0';
  		}else{
  			roleState='1';
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>adminRole/doupdateadminrolestatus?callback=?",
             data: {
             		'roleId':roleId,
             		'roleState':roleState
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	var msg=data.res;
	             	if(msg=='1'){
	             		succ=1;
	             		alert("状态修改成功！");
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
  </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>角色管理</li>
	    <li>角色列表</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>adminRole/toadminrolelist" class="selected">角色列表</a></li> 
	    <li><a href="<%=basePath %>adminRole/toaddadminrole">角色添加</a></li> 
	  	</ul>
	</div> 
    
    <!-- 查询条件 -->    
    <div class="formbody" >
    	<form id="formsubmit" action="" method="post">
	    <label>角色名：</label><input id="roleName" name="roleName" type="text" class="dfinput150" value="${roleName }"/><i></i>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    <c:if test="${roleList!=null}">
	    <label>&nbsp;</label><input name="" type="button" onclick="exp()" class="btn" value=" 导 出 "/>
	    </c:if>
	    </form>
    </div>
    <!-- 查询条件 -->
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
    	<th>角色ID</th>
        <th>角色名</th>
        <th>角色状态</th>
        <th>创建者</th> 
        <th>创建时间</th>
        <th>修改者</th>
        <th>修改时间</th>
        <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
    	<c:if test="${roleList!=null}">
    	<c:forEach var="role" items="${roleList}">
    		<tr>
    			<td><c:out value="${role.roleId}"/></td>
    			<td><c:out value="${role.roleName}"/></td>
    			<td>
    				<c:if test="${role.roleState=='0'}"><font style="color:red;">停用</font></c:if>
    				<c:if test="${role.roleState=='1'}"><font style="color:green;">启用</font></c:if>
    			</td>
    			<td><c:out value="${role.createAdmin}"/></td>
    			<td><c:out value="${role.createDate}"/></td>
    			<td><c:out value="${role.modifyAdmin}"/></td>
    			<td><c:out value="${role.modifyDate}"/></td>
    			<td>
    				<a href="javascript:toupdate('<c:out value="${role.roleId}"/>','<c:out value="${role.roleName}"/>');" class="tablelink">
    					修改
    				</a>  
    				<a href="javascript:updateState('<c:out value="${role.roleId}"/>','<c:out value="${role.roleState}"/>');" class="tablelink" >
    					<c:if test="${role.roleState=='1'}">停用</c:if>
    					<c:if test="${role.roleState=='0'}">启用</c:if>
    				</a>
    				<a href="javascript:deleterole('<c:out value="${role.roleId}"/>','<c:out value="${role.roleName}"/>');" class="tablelink">
    					删除
    				</a>
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
