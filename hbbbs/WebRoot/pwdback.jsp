<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.bbs.util.CookieUtil"%>
<%@page import="com.bbs.memcahed.MemCached"%>
<%@ page import="com.bbs.util.Constant"%>
<%@ page import="com.bbs.util.MD5"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String email=request.getParameter("email");
	String time=request.getParameter("time");
	String version=request.getParameter("version");
	String appId=request.getParameter("appId");
	String sign=request.getParameter("sign");
	
	String c_appid = Constant.APPID;
	String c_version = Constant.VERSION;
	String c_url = Constant.APIURL;
	
	boolean bool=false;
	String msg="";
	
	SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Date time_date=adf.parse(time);
	Date now_date=new Date();
	
	if(email==null || email.equals("") ||
		time==null || time.equals("") ||
		version==null || version.equals("") ||
		appId==null || appId.equals("") ||
		sign==null || sign.equals("")){
		bool=false;
		msg="连接参数异常，请重新发送邮件！";
	}else if(time_date.getTime()<now_date.getTime()){
		bool=false;
		msg="连接已过期，请重新发送邮件！";
	}else{
		Map<String, String> parmsmap=new HashMap<String,String>();
		parmsmap.put("email",email);
		parmsmap.put("time",time);
		parmsmap.put("version",version);
		parmsmap.put("appId",appId);
		String sign_self=MD5.md5Api(parmsmap);
		
		if(sign!=null && sign_self.equals(sign)){
			bool=true;
		}else{
			bool=false;
			msg="连接参数异常，请重新发送邮件！";
		}
	}
	
	
	
%>

<!DOCTYPE html>
<html>
<head>
    <title>重置密码-绘本树</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="./rs/css/layer.css">
	<script src="./rs/js/jquery-1.9.1.min.js"></script>
	<script src="./rs/js/layer.m.js"></script>
    <style>
		.share_bg{
			background-color:#eee;
		}
		#body_share{
			width:320px;
			margin:0 auto;
			background-color:#fff;
			margin-top:80px;
			border:1px solid #ccc;
			border-radius:20px;
		}
		
		h3{text-align:center;}
		
		.img_logo{
			text-align:center;
			width:108px;
			height:108px;
			background-image:url(rs/img/czmm.png);
			margin:50px auto 0px auto;
			border-radius:20px;
		}
		.share_bg div{
			font-size:18px;
			color:#888888;
		}
		.share_bg_title{
			width: 100%;
			line-height:60px;
			text-align:center;
			font-size:24px;
			color:#fff;
			font-weight:bold;
		}
		.share_bottom{
			width: 60%;
			height:60px;
			margin:30px auto;
			background-color:#92b831;
			color:#fff;
			line-height:60px;
			border-radius:20px;
			font-weight:bold;
			text-align:center;
		}
		.new-password{
			height: 20px;
  			width: 80%;
 			margin-bottom: 10px;
  			padding: 10px;
			border: #979797 1px solid;
		}
    </style>
    
</head>
<body class="share_bg">
    <div id="body_share">
    <div class="img_logo"></div>
    	<%if(bool==true){ %>
	        <h3><br></h3><h3>重设密码</h3>
	        <div class="share_bg_title">
	            <input type="password" id="pwd1" name="pwd1" class="new-password" placeholder="输入新密码" maxlength="20"/>
	            <input type="password" id="pwd2" name="pwd2" class="new-password" placeholder="确认密码" maxlength="20"/>
	        </div>
	        <a id="chongzhi" href="javascript:void(0);" onclick="chognzhi()">
	        	<div class="share_bottom" style="color:#fff; font-size:18px;">重设密码</div>
	        </a>
        <%}else{ %>
        	<h3><br></h3><h3><%=msg %></h3>
        <%} %>
    </div>
</body>

 <script type="text/javascript">
    function chognzhi(){
		  var pwd=document.getElementById("pwd1").value;
		  var pwd2=document.getElementById("pwd2").value;
		  if(pwd==null || pwd==""){
		  		layer.open({
			    content: "密码不能为空！",
			    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
			    time: 2
				});
				return false;
		  }
		  if(pwd2==null || pwd2==""){
		  		layer.open({
			    content: "确认密码不能为空！",
			    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
			    time: 2
				});
				return false;
		  }
		  if(pwd!=pwd2){
		  		layer.open({
			    content: "两次密码必须要一致！",
			    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
			    time: 2
				});
				return false;
		  }
		  
		  
		  var but=document.getElementById('chongzhi');
			but.disabled=true;
			var result='';
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>scripts/req",
	             data: {
	             		'do':'getPwdBack',
	             		'pwd':pwd,
	             		'email':'<%=email%>'
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
             			result=data['res'];
             			if(result=='1'){
             				layer.open({
							    content: data['su']['message'],
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
             			}else{
							layer.open({
							    content: data['su']['message'],
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
						}
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	layer.open({
					    content: "异常:"+errorThrown,
					    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
					    time: 2
					});
	             }
	         });
	       but.disabled=false;
	       
	       if(result=='1'){
	       		window.location.href="pwdbacksucc.jsp";
	       }
	       
    }
</script>	
</html>