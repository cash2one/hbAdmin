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
  	function toConfirmRole(){
		var strcheck="";
		var rolecheck = document.getElementsByName('rolecheck');
		for(var i=0;i<rolecheck.length;i++){
			var r=rolecheck[i];
			if(r.checked){
				strcheck+=r.value+";";
			}
		}
		document.getElementById('adminRole').value=strcheck;
	}
  
   function addAdmin(){
   		var adminAccount=$("#adminAccount").val();
   		var adminAccount2=$("#adminAccount2").val();
   		var adminPwd=$("#adminPwd").val();
   		var adminPwd2=$("#adminPwd2").val();
   		var adminRealName=$("#adminRealName").val();
   		var adminEmail=$("#adminEmail").val();
   		var adminPhone=$("#adminPhone").val();
   		var adminMemo=$("#adminMemo").val();
   		var adminState=1;
   		var adminRole=$("#adminRole").val();
   		var reg = /\W/;
		if ("" == adminAccount || adminAccount.match(reg)!=null ||adminAccount.length>30){
			alert("帐号不能为空且格式为字母、数字组成,长度不能超过30!");
			$("#adminAccount").focus();
			return false;
		}
		if(adminAccount!=adminAccount2){
			alert("帐号与确认帐号必须要一致!");
			$("#adminAccount").focus();
			return false;
		}
		if(""==adminPwd||adminPwd.length>300){
			alert("密码不能为空");
			$("#adminPwd").focus();
			return false;
		}
		if(adminPwd!=adminPwd2){
			alert("密码与确认密码必须要一致!");
			$("#adminPwd").focus();
			return false;
		}
		if(""==adminRealName||adminRealName.length>25){
			alert("管理员姓名不能为空，且长度不能超过25");
			$("#adminRealName").focus();
			return false;
		}
		if(""==adminEmail||adminEmail.length>60){
			alert("邮箱不能为空");
			$("#adminEmail").focus();
			return false;
		}
		if(""==adminRole){
			alert("请选择管理员角色");
			return false;
		}
		$.ajax({
             type: "post",
             url: "<%=basePath %>adminUser/doaddadminuser?callback=?",
             data: {
             	adminAccount:adminAccount,
             	adminPwd:adminPwd,
             	adminRealName:adminRealName,
             	adminEmail:adminEmail,
             	adminPhone:adminPhone,
             	adminMemo:adminMemo,
             	adminState:adminState,
             	adminRole:adminRole
             },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
             	var msg=data.res;
             	alert(msg);
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
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
	    <li>帐号添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
	    
	    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>adminUser/toadminuserlist">管理员列表</a></li> 
	    <li><a href="<%=basePath %>adminUser/toaddadminuser"  class="selected">管理员添加</a></li> 
	  	</ul>
	    </div> 
	    
	    <div id="tab1" class="tabson">
	    <div class="formtext">提示：帐号为不可修改列</div>
	    <ul class="forminfo">
	    	<li><label>管理员帐号：<b>*</b></label><input type="text" id="adminAccount" name="adminAccount" size="31" class="dfinput"/></li>
		    <li><label>确认管理员帐号：<b>*</b></label><input type="text" id="adminAccount2" name="adminAccount2" size="31" class="dfinput"/></li>
		    <li><label>管理员密码：<b>*</b></label><input type="password" id="adminPwd" name="adminPwd" size="33"  class="dfinput"/></li>
		    <li><label>确认管理员密码：<b>*</b></label><input type="password" id="adminPwd2" name="adminPwd2" size="33" class="dfinput"/></li>
		    <li><label>管理员姓名：<b>*</b></label><input type="text" id="adminRealName" name="adminRealName" size="31" class="dfinput"/></li>
		    <li><label>管理员EMAIL：<b>*</b></label><input type="text" id="adminEmail" name="adminEmail" size="31" class="dfinput"/></li>
		    <li><label>管理员电话：<b></b></label><input type="text" id="adminPhone" name="adminPhone" size="31" class="dfinput"/></li>
		    <li><label>管理员备注：<b></b></label><textarea id="adminMemo" name="adminMemo" rows="2" cols="25" class="textinput"></textarea></li>
	    	<li><label>设置角色：<b>*</b></label>
	    	<div style="width:325px;padding:10px;overflow-x: hidden;overflow-y: auto; border: 1px solid; border-color: blue;">
					<table>
						<c:if test="${rolelist1!=null}">
							<c:forEach items="${rolelist1}" var="TblAdminRole">
								<tr>
									<td width="40px">
										<input type="checkbox" name="rolecheck" id="rolecheck" value="${TblAdminRole.roleId}"/>
									</td>
									<td>
										<c:out value="${TblAdminRole.roleName}"></c:out>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>	
				<input type="hidden" id="adminRole" name="adminRole" value=""/>
	    	</li>
	    	<li><label><b></b></label><input type="button" id="create_account" name="create_account" value="添 加" onclick="javascript:toConfirmRole();addAdmin();" class="btn"/></li>
	    	
	    </ul>
	    </div>
    </div>
  
  </body>
</html>
