<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<jsp:include page="/include/base.jsp" />
	<script type="text/javascript" src="<%=basePath %>js/cloud.js"></script>
	<head>
<script language="javascript">
$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
   		 $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  

document.onkeydown = function(e){
    if(!e) e = window.event;//火狐中是 window.event
    if((e.keyCode || e.which) == 13){
        document.getElementById("loginbtn").click();
    }
} 

function login(){
		var adminAccount=$("#adminAccount").val();
		var adminPwd=$("#adminPwd").val();
		if(adminAccount==''){
			$("#msg").html("帐号不能为空！");
			$("#adminAccount").focus();
			return;
		}
		if(adminPwd==''){
			$("#msg").html("密码不能为空！");
			$("#adminPwd").focus();
			return;
		}
		$.ajax({
             type: "post",
             url: "<%=basePath %>adminUser/tologin",
             data: {adminAccount:adminAccount,adminPwd:adminPwd},
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
             	var msg=data.res;
             	if(msg==-1){
             		$("#msg").html("帐号/密码不能为空！");
             	}else if(msg==1){
             		$("#msg").html("登录成功！");
             		window.location.href="<%=basePath %>main.jsp";
             	}else if(msg==2){
             		$("#msg").html("该帐号已被停用！");
             	}else if(msg==0){
             		$("#msg").html("帐号或密码错误！");
             	}else if(msg==-2){
             		$("#msg").html("异常："+data.su);
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

	<body
		style="background-color:#76C6E9; background-image: url(<%=basePath %>images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">

		<div id="mainBody">
			<div id="cloud1" class="cloud"></div>
			<div id="cloud2" class="cloud"></div>
		</div>


		<div class="logintop">
			<span>欢迎登录后台管理界面平台</span>
			<ul>
				<li><a href="main.jsp">回首页</a></li>
				<!-- 
				<li><a href="#">帮助</a></li>
				<li><a href="#">关于</a></li>
				 -->
			</ul>
		</div>

		<div class="loginbody">

			<span class="systemlogo"></span>

			<div class="loginbox">
				<ul>
					<li>
						<input name="" type="text" class="loginuser" value="admin"
							id="adminAccount" name="adminAccount"/>
					</li>
					<li>
						<input name="" type="password" class="loginpwd" value=""
							id="adminPwd" name="adminPwd" />
					</li>
					<li>
						<div style="float:left"><input id="loginbtn" name="loginbtn" type="button" class="loginbtn" value="登录" onclick="login()" /></div>
						<div style="float:left;margin-left:10px;"><span id="msg" style="color:red;"></span></div>
					</li>
				</ul>


			</div>

		</div>

		<div class="loginbm">
			版权所有 Copyright © 2015 绘本
		</div>


	</body>

</html>
