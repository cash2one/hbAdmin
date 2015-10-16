<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/include/base.jsp" />
  <head>
<script type="text/javascript">
function toChangePwd(){
	var oldPwd=$("#oldPwd").val();
	var adminUserPwd=$("#adminUserPwd").val();
	var adminUserPwds=$("#adminUserPwds").val();
	
	if(oldPwd==''){
		$("#msg").html("请输入旧密码");
		return false;
	}
	
	if(adminUserPwd=='' || adminUserPwds==''){
		$("#msg").html("请输入新密码或确认密码");
		return false;
	}
	
	if (adminUserPwd != adminUserPwds){
		$("#msg").html("新密码与确认密码不一样");
		return false;
	}
	
	$.ajax({
        type: "post",
        url: "<%=basePath %>adminUser/updatepwd?callback=?",
        data: {'oldPwd':oldPwd,'adminUserPwd':adminUserPwd,'adminUserPwds':adminUserPwds},
        dataType: "json",
        async:	false,
        success: function(data,textStatus){
        	var msg=data.res;
        	if(msg==1){
        		$("#msg").html("密码修改成功！");
        	}else{
        		$("#msg").html(msg);
        	}
        	
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
	    <li>管理员密码修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="formbody">
    
    <div class="formtitle"><span>管理员密码修改</span></div>
    <ul class="forminfo">
    <li><label>管理员帐号：</label><input id="adminAccount" name="adminAccount"  type="text" readonly="readonly" class="dfinput" value="<%=session.getAttribute("admin_account") %>"/><i></i></li>
    <li><label>旧密码：</label><input type="password" id="oldPwd" name="oldPwd" class="dfinput" value="${oldPwd}"/><i></i></li>
    <li><label>新密码：</label><input type="password" id="adminUserPwd" name="adminUserPwd" class="dfinput"  value="${adminUserPwd}"/></li>
    <li><label>确认新密码：</label><input type="password" id="adminUserPwds" name="adminUserPwds" class="dfinput" value="${adminUserPwds}"/></li>
    <li><label>&nbsp;</label>
    <input type="button" value="  修 改  " id="add" class="btn" onclick="javascript:toChangePwd();" />
	<input type="button" value="返回首页" class="btn" onclick="javascript:window.location.href='<%=basePath %>index.jsp'" />
	<b id="msg" style="color:red"></b>
    </li>
    </ul>
    
    </div>
    
    
  </body>
</html>
