<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.bbs.util.CookieUtil"%>
<%@page import="com.bbs.memcahed.MemCached"%>
<%@ page import="com.bbs.util.Constant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String type = request.getParameter("type");
	String vid = (String) CookieUtil.getCookieValue("hbbbsmid",request);
	String uid = (String) MemCached.getMccObject(vid);
	String appid = Constant.APPID;
	String version = Constant.VERSION;
	String url = Constant.APIURL;
%>

<!DOCTYPE html>
<html>
<head>
    <title>成长分享</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="./rs/css/main.css" type="text/css">
    <link rel="stylesheet" href="./rs/css/layer.css">
    <style>
        body{
            min-height: 100%;
        }
        .mybody{
            padding: 5%;
            position: absolute;
            width: 100%;
            height: auto;
            top: 50px;
        }
        label,input,textarea{
            display: block;
        }
    </style>
</head>
<body ontouchmove='return false;'>
<header id="head">
    <ul id="TopicType">
    <!-- 
        <li class="active" data-class="1"><a>爸妈学堂</a></li>
        <li data-class="2"><a>成长分享</a></li>
        <li data-class="3"><a>互助交流</a></li> -->
    </ul>
</header>

<div class="mybody">
    <form method="post" id="myform" action="<%=url%>" enctype="multipart/form-data">
        <input style="display: none" id="file1" type="file" name="file1">
        <label>标题</label>
        <input name="title" id="title"/>
        <input type="hidden" name="do" id="do" value="addTopicList"/>
        <input type="hidden" name="topic_typeId" id="topic_typeId" value="<%=type %>"/>
        <input type="hidden" name="uid" id="uid" value="<%=uid %>"/>
        <input type="hidden" name="appid" id="appid" value="<%=appid %>"/>
        <input type="hidden" name="version" id="version" value="<%=version %>"/>
        <label>内容</label>
        <textarea name="content" id="content" style="height: 100px"></textarea>
        <table style="width: 100%">
            <tr>
                <td><label>图片</label></td>
                <td><img id="img" width="80" style="float: right"></td>
                <td><div id="btselect" class="blockicon icon_img" style="width: 40px;height: 35px;float: right"></div></td>
            </tr>
        </table>
        <div>
        <img style="float: left;width: 100px;" src="./rs/img/btn_back.png" width="100%" onclick="javascript:history.go(-1)"/>
        <img style="float: right;width: 100px;" src="./rs/img/btn_ok.png" width="100%" name='tosubmit' id="tosubmit"/>
        </div>
        <!--<table style="margin: 0 auto" name='submit'>-->
            <!--<tr><td>发布</td><td><div class="blockicon icon_img" style="width: 40px;height: 35px;float: right"></div></td></tr>-->
        <!--</table>-->
    </form>
</div>

</body>
<script src="./rs/js/jquery-1.9.1.min.js"></script>
<script src="./rs/js/layer.m.js"></script>
<script src="./rs/js/jquery.form.min.js"></script>
<script>
var type = "<%=type%>";
var uid = "<%=uid %>";
$(function(){
	
	var html = "";
	$.ajax({
		async:false,
        type: "GET",
        url:"<%=basePath %>scripts/req",
        data:{'do':"getTopicType"},
        dataType: "json",
        error: function(request) {
        	
        },
        success: function(data) {
        	if(data.su.result == "0"){
        		html = "";
        	}else{
	            $.each(data.su.data, function (n, value) {
	            if(typeof(type) == "undefined"){
		            if(n==0){
		            	type = value.id;
		            }
	            }
	            if(value.id==type){
	            	html +="<li class=\"active\">";
	            }else{
	            	html +="<li>";
	            }
	            html += "<a>"+value.name+"</a></li>";
	     		});
     		}
	     	$("#TopicType").html(html);
     	}
	})
});
var fileinfo;
    $('[name=tosubmit]').click(function(){
    
    	$('#myform').ajaxSubmit(function(data){

                result=data['result'];
             			if(result=='1'){
             				layer.open({
							    content: data['message'],
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
							window.location.href = 'index.jsp?type='+type+'&uid='+uid;
             			}else{
							layer.open({
							    content: data['message'],
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
						}
        });
        
    });
    var btselect = document.getElementById('btselect');
    var file = document.getElementById('file1');
    var img = document.getElementById('img');
    btselect.onclick = function(){
        file.click();
    };
    file.addEventListener('change', function(){
    	var flag = false;
        fileinfo = file.files[0];
        if(checkfile(fileinfo)){
        
        	var name = fileinfo.name;

			var ext = name.substr(name.lastIndexOf(".")).toLowerCase();//获取文件名
			
			if(ext=='.jpg'||ext=='.png'||ext=='.bmp'||ext=='.gif'){
		       flag = true;
		    }else{
		    	layer.open({
				    content: '请上传正确的图片',
				    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
				    time: 2
				});
				return false;
		    }
		    if(flag){
		    	addfile();
		    }
			
        }
    });
    function checkfile(fileinfo){
        return true;
    }
    function addfile(){
    	 console.log(fileinfo);
            var reader = new FileReader();
            reader.onload = function(e){
//               img.style.backgroundImage = 'url('+e.target.result+')';
                img.src = e.target.result;
            }
            reader.readAsDataURL(fileinfo);
    }
</script>

</html>