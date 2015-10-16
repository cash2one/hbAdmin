<%@ page language="java"  pageEncoding="utf-8"%>
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
  function updateState(adminAccount,adminState){
  		if(adminAccount==null || ''==adminAccount){
  			alert("管理员帐号不能为空！");
  			return false;
  		}
  		if(adminState=='1'){
  			adminState='0';
  		}else{
  			adminState='1';
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>adminUser/doupdateadminuserstate?callback=?",
             data: {
             		'adminAccount':adminAccount,
             		'adminState':adminState
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
  
  function deleteAdmin(adminAccount){
  		if(adminAccount==null || ''==adminAccount){
  			alert("管理员帐号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除管理员："+adminAccount+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>adminUser/todeleteadminuser?callback=?",
	             data: {
	             		'adminAccount':adminAccount
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
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
  
  function sel(){
  		$("#formsubmit").attr("action","<%=basePath %>adminUser/doadminuserlist");
  		$("#formsubmit").submit();
  }
  
  function exp(){
  		$("#formsubmit").attr("action","<%=basePath %>adminUser/expadminuserlist");
  		$("#formsubmit").submit();
  }
  </script>
  </head>
  
  <body>
  	<!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>帐号与权限</li>
	    <li>后台帐号列表</li>
	</ul>
    </div>
    <!-- 页面位置 -->

	<div class="rightinfo">
    <div class="itab">
  	<ul> 
    <li><a href="<%=basePath %>adminUser/toadminuserlist" class="selected">管理员列表</a></li> 
    <li><a href="<%=basePath %>adminUser/toaddadminuser">管理员添加</a></li> 
  	</ul>
    </div> 
	
	<!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post">
	    <label>管理员帐号：</label><input id="adminAccount" name="adminAccount" type="text" class="dfinput150" value="${adminAccount }"/><i></i>
	   <!--  <label>角色类型：</label>
	    <select id="adminRole" name="adminRole" class="select">
	    	<option value="all">全部</option>
	    	<c:if test="${rolelist1!=null}">
		   		<c:forEach items="${rolelist1}" var="AdminRole">
		   		<option value="<c:out value='${AdminRole.roleId }'/>" <c:if test="${AdminRole.roleId==adminRole}">selected</c:if>><c:out value="${AdminRole.roleName }"/></option>
		   		</c:forEach>
	   		</c:if>
	    </select>
	     -->
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    <c:if test="${adminUserList!=null}">
	    <label>&nbsp;</label><input name="" type="button" onclick="exp()" class="btn" value=" 导 出 "/>
	    </c:if>
	</form>
    </div>
    <!-- 查询条件 -->
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
        <th>管理员帐号</th>
        <th>姓名</th>
        <th>状 态</th>
        <th>创建时间</th> 
        <th>修改时间</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${adminUserList!=null}">
        	<c:forEach var="AdminUser" items="${adminUserList}">
        		<tr>
		        <td><c:out value="${AdminUser.adminAccount}"/></td>
		        <td><c:out value="${AdminUser.adminRealName}"/></td>
		        <td>
		        	<c:if test="${AdminUser.adminState=='0'}"><font style="color:red;">停用</font></c:if>
    				<c:if test="${AdminUser.adminState=='1'}"><font style="color:green;">启用</font></c:if>
		        </td>
		        <td><c:out value="${AdminUser.createdDate}"/></td>
		        <td><c:out value="${AdminUser.modifyDate}"/></td>
		        <td>
			        <a href="<%=basePath %>adminUser/toupdateadminuser?adminAccount=<c:out value='${AdminUser.adminAccount}'/>" class="tablelink">修改</a>     
			        <a href="javascript:updateState('<c:out value="${AdminUser.adminAccount}"/>','<c:out value="${AdminUser.adminState}"/>');" class="tablelink">
			        	<c:if test="${AdminUser.adminState=='0'}">启用</c:if>
    					<c:if test="${AdminUser.adminState=='1'}">停用</c:if>
			        </a>
			        <a href="javascript:deleteAdmin('<c:out value="${AdminUser.adminAccount}"/>')" class="tablelink">删除</a>
		        </td>
		        </tr>
        	</c:forEach>
        </c:if>
             
        </tbody>
    </table>
    <!-- 查询列表 -->
    ${pageinfo }
    </div>
    <script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>
