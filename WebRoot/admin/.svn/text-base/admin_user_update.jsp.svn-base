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
  
   function updateAdmin(){
   		var adminAccount=$("#adminAccount").val();
   		var adminRealName=$("#adminRealName").val();
   		var adminEmail=$("#adminEmail").val();
   		var adminPhone=$("#adminPhone").val();
   		var adminMemo=$("#adminMemo").val();
   		var adminRole=$("#adminRole").val();
   		var reg = /\W/;
		if ("" == adminAccount || adminAccount.match(reg)!=null ||adminAccount.length>30){
			alert("帐号不能为空且格式为字母、数字组成,长度不能超过30!");
			$("#adminAccount").focus();
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
             url: "<%=basePath %>adminUser/doupdateadminuser?callback=?",
             data: {
             	adminAccount:adminAccount,
             	adminRealName:adminRealName,
             	adminEmail:adminEmail,
             	adminPhone:adminPhone,
             	adminMemo:adminMemo,
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
   
   function updatePassword(adminAccount){
   		if(adminAccount==null || ''==adminAccount){
   			alert("管理员帐号不能为空！");
   			return false;
   		}
   		$.ajax({
            type: "post",
            url: "<%=basePath %>adminUser/doupdateadminuserpwd?callback=?",
            data: {
            	adminAccount:adminAccount
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
	    <li>帐号修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
	    
	    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>adminUser/toadminuserlist">管理员列表</a></li> 
	    <li><a href="<%=basePath %>adminUser/toaddadminuser">添加管理员</a></li> 
	  	</ul>
	    </div> 
	    
	    <div id="tab1" class="tabson">
	     <div class="formtext">提示：重置密码后，密码改为123456</div>
	    <ul class="forminfo">
	    	<li><label>管理员帐号：<b>*</b></label><input type="text" id="adminAccount" name="adminAccount" size="31" class="dfinput" value="${AdminUser.adminAccount }" readonly="readonly"/></li>
		    <li><label>管理员密码：<b>*</b></label><input type="button" id="updatePwd" name="updatePwd" size="33" onclick="javascript:updatePassword('${AdminUser.adminAccount }');" value="重置密码" class="btn"/></li>
		    <li><label>管理员姓名：<b>*</b></label><input type="text" id="adminRealName" name="adminRealName" size="31" value="${AdminUser.adminRealName }" class="dfinput"/></li>
		    <li><label>管理员EMAIL：<b>*</b></label><input type="text" id="adminEmail" name="adminEmail" size="31" value="${AdminUser.adminEmail }" class="dfinput"/></li>
		    <li><label>管理员电话：<b></b></label><input type="text" id="adminPhone" name="adminPhone" size="31" value="${AdminUser.adminPhone }" class="dfinput"/></li>
		    <li><label>管理员备注：<b></b></label><textarea id="adminMemo" name="adminMemo" rows="2" cols="25"  class="textinput">${AdminUser.adminMemo }</textarea></li>
	    	<li><label>设置角色：<b>*</b></label>
	    	<div style="width:325px;padding:10px;overflow-x: hidden;overflow-y: auto; border: 1px solid; border-color: blue;">
					<table>
						<c:if test="${rolelist1!=null}">
						<%int index=0; %>
							<c:forEach items="${rolelist1}" var="TblAdminRole">
								<tr>
									<td width="40px">
										<input type="checkbox" name="rolecheck" id="<%=index%>check" value="${TblAdminRole.roleId}"/>
									</td>
									<td>
										<c:out value="${TblAdminRole.roleName}"></c:out>
									</td>
									<c:forEach items="${userrolelist}" var="role">
										<c:set var="r2" value="${role}"/>
										<c:if test="${TblAdminRole.roleId==role}">
											<script>
												var id=<%=index%>+"check";
												document.getElementById(id).checked=true;
											</script>
										</c:if>
									</c:forEach>
								</tr>
								<%index++; %>
							</c:forEach>
						</c:if>
					</table>
				</div>	
				<input type="hidden" id="adminRole" name="adminRole" value=""/>
	    	</li>
	    	<li><label><b></b></label><input type="button" id="create_account" name="create_account" value="修 改" onclick="javascript:toConfirmRole();updateAdmin();" class="btn"/></li>
	    	
	    </ul>
	    </div>
    </div>
  
  </body>
</html>
