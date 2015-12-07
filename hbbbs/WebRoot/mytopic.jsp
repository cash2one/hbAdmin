<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.bbs.util.Constant"%>
<%@ page import="com.bbs.util.CookieUtil"%>
<%@ page import="com.bbs.memcahed.MemCached"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String uid = "0";
	String uuid = CookieUtil.getCookieValue("hbbbsmid", request);
	if (uuid != null && !"".equals(uuid)) {
		Object userObj = MemCached.getMccObject(uuid);
		if (userObj != null)
			uid = String.valueOf(userObj);
	}

	String find_do = "getMyTopicList";
	String version = Constant.VERSION;
	String appid = Constant.APPID;
	String url = Constant.APIURL;
	String index = "1";
%>

<!DOCTYPE html>
<html>
	<head>
		<title>我的帖子</title>
		<meta charset="UTF-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="./rs/css/main.css" type="text/css">
		<link rel="stylesheet" href="./rs/css/loading_2.css">
		<link rel="stylesheet" href="./rs/css/layer.css">
		<script src="./rs/js/jquery-1.9.1.min.js"></script>
		<script src="./rs/js/touchsilder.js"></script>
		<script src="./rs/js/iscroll.js"></script>
		<script src="./rs/js/layer.m.js"></script>
		<script type="text/javascript" src="./rs/js/public.js"></script>
		<style>
body {
	min-height: 100%;
}

.mybody {
	padding: 0%;
	position: absolute;
	width: 100%;
	height: auto;
	min-height: 100%;
	top: 50px;
	background-color: #eeeef0;
}

label,input,textarea {
	display: block;
}

.icon_back {
	width: 50px;
	height: 50px;
	float: left;
	margin-left: 5%;
}

#head h1 {
	margin: 0;
	line-height: 50px;
	font-size: 16px;
	text-align: center;
}

#head {
	position: absolute;
	top: 0;
	width: 100%;
	height: 55px;
	background-color: #fff;
	background-image: url(rs/img/menu_list-5.png);
	background-size: auto 100%;
	background-position: center center;
	background-repeat: no-repeat;
}

.innerlist {
	min-height: 100%;
}

.row {
	overflow: hidden;
	margin-bottom: 10px;
	background-color: #fff;
	padding: 5px;
}

.row img {
	float: right;
	margin-left: 10px;
	width: 40px;
	height: 40px;
}

.row h1 {
	margin: 0;
	font-size: 16px;
}

.row>ul {
	color: #a1a1a1;
	font-size: 12px;
	line-height: 20px;
}

.row>ul>li {
	float: right;
	margin-left: 10px;
}

.row>ul>li.name {
	float: left;
	margin-left: 5px;
}
</style>
		<script type="text/javascript">
     function find(index, callback){
    	var succ='';
	    $.ajax({
	             type: "post",
	             url: "<%=url%>",
	             data: {
	             		'do':'<%=find_do%>',
	             		'uid':'<%=uid%>',
	             		'version':'<%=version%>',
	             		'appid':'<%=appid%>',
	             		'index':index
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
             			var result=data['result'];
             			if(result=='1'){
	             			var topic_arry=data['data'];
	             			if(topic_arry!=null && topic_arry!=''){
	             				$("#index").val(index);
	             				var topic_html="";
								for (i=0;i<topic_arry.length ;i++ ) 
								{ 
									var topic=topic_arry[i];
									topic_html+='<div class="row"><ul  name="'+topic['id']+'" onclick="clickUl(this)"  style="height: 45px;">';
									var title_width="";
									if(topic['affix']!=null && topic['affix']!=''){
							       		topic_html+='<img src="'+topic['affix']+'">';
							       		title_width="width:83%;";
			             			}
							        //topic_html+='<h1>'+topic['title']+'</h1>'
							        var title_str=topic['title'];
							        if(title_str!=null && title_str.length>30){
							         	title_str=title_str.substring(0,30)+"...";
							        }
							        topic_html+='<h1 style="word-wrap:break-word;'+title_width+'">'+title_str+'</h1>'
							        +' </ul>   <ul>';
							        
							        var user_nickname=topic['user_nickname'];
							        if(user_nickname!=null && user_nickname!='' && user_nickname!='null'){
								        topic_html+='<li class="name">'+user_nickname+'</li>';
							        }else{
							        	 topic_html+='<li class="name"></li>';
							        }
							        
							        topic_html+='<li>评论 '+topic['countback']+'</li>'
							        +'        <li>阅读 '+topic['countbrowse']+'</li>'
							        +'        <li>'+datediff(topic['createtime'])+'</li>'
							        +'</ul></div>';
									
	             				}
	             				$("#topic_list").append(topic_html);
	             				 window.myscroll.refresh();
	             			}
						}else if(result=='error'){
							layer.open({
							    content: data['message'],
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
						}else{
							if(parseInt(index)>1){
								layer.open({
								    content: '没有更多了',
								    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
								    time: 2
								});
							}
						}
						$('#refresh_end').hide();
                    callback && callback();
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	layer.open({
					    content: "异常:"+errorThrown,
					    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
					    time: 2
					});
	             }
         });
    }
  	
  	
    </script>
	</head>
	<body onload="find(1)" ontouchmove='return false;'>
		<header id="head">
		<a href="javascript:history.go(-1)">
		<div name='back' class="blockicon icon_back"></div>
		</a>
		<!--<h1>我的帖子</h1>-->
		</header>
		<div id="body" class="mybody">
			<input type="hidden" id="index" name="index" value="<%=index%>" />
			<div class="innerlist">
				<div style="text-align: center; display: none" id="refresh_head">
					<img src="rs/img/icon/loding.png">
				</div>

				<div class="list"  id="topic_list"></div>
				<div style="height: 1px"></div>

				<div style="text-align: center; display: none" id="refresh_end">
					<div class="load-container load8">
						<div class="loader">
							加载新数据...
						</div>
					</div>
				</div>
				
			</div>

		</div>

	</body>
	<script src="./rs/js/jquery-1.9.1.min.js"></script>
	<script src="./rs/js/touchsilder.js"></script>
	<script src="./rs/js/iscroll.js"></script>
	<script>
$(function(){
    //列表滚动
    window.myscroll =  new iScroll($('.innerlist')[0], {
        desktopCompatibility:true ,
        hScrollbar: false,
        onScrollMove:function(){
            if(this.y>20 && $('#refresh_head').is(':hidden')){
                $('#refresh_head').show();
                this.minScrollY = 0;

            }else if (this.y < (this.maxScrollY - 30) && $('#refresh_end').is(':hidden')){
                $('#refresh_end').show();
    //                this.refresh();
                this.maxScrollY-=30;
            }
        }
        ,onScrollEnd:function(){
        	var index1=$("#index").val();
        	if(index1==null || index1=='') index1="1";
        	var num=parseInt(index1);
            if($('#refresh_head').is(':visible')){
            	$('#refresh_head').hide();
            	$('#refresh_end').hide();
            }else if($('#refresh_end').is(':visible')){
            	var  me = this;
            	num=num+1;
	            find(num);
            }
        }
    });
});



</script>
</html>