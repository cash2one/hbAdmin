<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	        <h3><br></h3><h3>重设密码成功</h3>
    </div>
</body>

 
</html>