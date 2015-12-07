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
	String uuid=CookieUtil.getCookieValue("hbbbsmid",request);
	String type = request.getParameter("type"); 
	if(uuid!=null && !"".equals(uuid)){
		Object userObj=MemCached.getMccObject(uuid);
		if(userObj!=null) uid=String.valueOf(userObj);
	}
	String topic_id = request.getParameter("tid");
	String find_do = "findTopicReplyList";
	String version = Constant.VERSION;
	String appid = Constant.APPID;
	String url = Constant.APIURL;
	String index = "1";

%>

<!DOCTYPE html>
<html>
	<head>
		<title>帖子</title>
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

.wordwrap{
word-wrap:break-word;
}

#body {
	
}

.mybody {
	position: absolute;
	width: 100%;
	height: auto;
	top: 50px;
}

h1 {
	margin: 0;
	padding: 10px;
	background-color: #fff;
	color: #000;
	font-size: 16px;
}

img.photo {
	width: 100%;
	display: block;
	border: none;
}

p {
	text-indent: 2em;
	margin: 0;
	padding: 5px;
	background-color: #fff;
	margin-bottom: 5px;
}

.list {
	padding-top: 5px;
	padding-bottom: 5px;
}

.list .row {
	margin-bottom: 5px;
	overflow: hidden;
	list-style: none;
	position: relative;
	height: auto;
	width: 100%;
	padding: 5px;
	background-color: #fff;
}

.list .row>* {
	height: 100%;
}

.list .row>.left {
	float: left;
	width: 40px;
	margin-right: 10px;
}

.list .row>.left>img {
	display: block;
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

.list .row>.left>span {
	text-align: center;
	display: block;
	line-height: 28px;
	color: #adff2f;
}

.list .row>.right {
	float: right;
	margin-left: 10px;
}

.list .row>.center {
	
}

.list .row>.huifu {
	width: 96%;
	background-color: #eee;
	margin: 10px auto;
	border-radius: 5px;
	padding: 10px;
}

.list .row>.center>span {
	display: block;
	text-align: right;
	font-size: 12px;
	color: #ccc;
}

.list .row>.center>h1 {
	line-height: 20px;
	min-height: 20px;
	margin: 0;
	font-size: 16px;
	background-color: #fff;
	color: #000;
}

.list .row>li>img {
	width: 40px;
	float: right;
}

.huifutxt {
	
}

.info {
	font-size: 12px;
	color: #8DB521;
	height: 30px;
	line-height: 30px;
	background-color: #FFF;
}

.info>li {
	float: right;
	display: inline-block;
}

.info>li>span{
  margin-right: 10px;
}
  
.info>li.name {
	float: left;
	margin-left: 10px;
}

#bottom .huifu_bd {
	width: 61%;
	float: left;
	margin: 10px 8px 0px 5px;
}

#bottom .huifu_btn {
	background-image: url(./rs/img/icon/menu_list_4.png);
	background-size: 100% 100%;
	width: 50px;
	height: 55px;
	float: left;
}

#topic_content{float: left;  padding: 10px;width: 100%;text-indent: 0;}
</style>

		<script type="text/javascript">
     function find(index,la){
     	//添加回复，下滑处理
		if(la=='1' && $("div[name^=topic_page_]:last ul").length<10){
        		//alert(index+"----------"+$("div[name^=topic_page_]:last ul").length);
        		//var page_name=$("div[name^=topic_page_]:last").attr("name");
        		//alert(page_name+"-------"+index);
        		index=parseInt(index)-1;
        		$("div[name^=topic_page_]:last").remove();
        		document.getElementById("la").value="0";
        }
     
    	var succ='';
	    $.ajax({
	             type: "post",
	             url: "<%=url%>",
	             data: {
	             		'do':'<%=find_do%>',
	             		'topic_id':'<%=topic_id%>',
	             		'version':'<%=version%>',
	             		'appid':'<%=appid%>',
	             		'index':index
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
             			var result=data['result'];
             			if(result=='1'){
	             			//主贴---------------
	             			var topic_data=data['data'];
	             			$("#topic_title").html(topic_data['title']);
	             			$("#topic_content").html(topic_data['content']);
	             			$("#topic_countbrowse").html(topic_data['countbrowse']);
	             			$("#topic_countback").html(topic_data['countback']);
	             			$("#topic_createtime").html(datediff(topic_data['createtime']));
	             			$("#topic_name").html(topic_data['user_nickname']);
	             			var affix=topic_data['affix'];
	             			if(affix!=null && affix!=''){
								var affix_html=' <img class="photo" src="'+affix+'"/>';
								$("#topic_affix").html(affix_html);
	             			}else{
	             				$("#topic_affix").html("");
	             			}
	             			window.myscroll.refresh();
	             			/*
	             			var label=topic_data['label'];
	             			if(label!=null && label!=''){
	             				var label_html='';
	             				if(label.substring(0, 1)=='1'){
	             					label_html+='<li class="lineicon icon_ding">&nbsp;</li>';
	             				}
	             				if(label.substring(1, 2)=='1'){
	             					label_html+='<li class="lineicon icon_jing">&nbsp;</li>';
	             				}
	             				if(label.substring(2, 3)=='1'){
	             					label_html+='<li class="lineicon icon_xin">&nbsp;</li>';
	             				}
	             				$("#topic_label").html(label_html);
	             			}
	             			*/
							//获取回帖----------------
							if(la=='1' && $("div[name^=topic_page_]:last ul").length>=10){
								$("#index").val(index);
							}
							var reply_arry=topic_data['data'];
							if(reply_arry!=null && reply_arry!=''){
								var reply_html='<div name="topic_page_'+index+'">';
								for (i=0;i<reply_arry.length ;i++ ) 
								{ 
									var reply=reply_arry[i];
									
									var sort=reply['sort'];
									if(sort=='1'){
										sort="沙发";
									}else if(sort=='2'){
										sort="板凳";
									}else if(sort=='3'){
										sort="地板";
									}else{
										sort="第"+sort+"楼";
									}
									
									 reply_html+='<ul class="row">'
						                +'<li class="left">';
						                
						             //var user_avatar=reply['user_avatar'];
						             //if(user_avatar!=null && user_avatar!='' && user_avatar!='null'){
							        //     reply_html+='<img src="'+reply['user_avatar']+'"/>';
						             //}else{
						             //	reply_html+='<img src="./rs/img/pic_userHead.jpg"/>'
						            // }
						             
						             var user_nickname=reply['user_nickname'];
						             if(user_nickname!=null && user_nickname!='' && user_nickname!='null'){
							              reply_html+='<span>'+reply['user_nickname']+'</span></li>';
						             }else{
						             	 reply_html+='<span></span></li>';
						             }
						             
						             reply_html+='<li class="center">'
						                +'    <span>'+sort+' '+datediff(reply['createtime'])+'</span>'
						                +'    <h1 class="wordwrap">'+reply['content']+'</h1>'
						                +'</li>';
						             
						             var quote_content=reply['quote_content'];
									 if(quote_content!=null && quote_content!=''){
										reply_html+='<li class="huifu">'+quote_content +'</li>';
									 }   
						               
					              	 reply_html+='<li><img src="./rs/img/icon/icon_pl.png" onclick="clickUser(\''+reply['sort']+'\',\''+reply['content']+'\')"/></li></ul>';
																
								}
								reply_html+='</div>';
								if(index=='1'){
									$("#topic_reply").html(reply_html);
								}else{
									$("#topic_reply").append(reply_html);
								}
								 window.myscroll.refresh();
							}else{
								if(parseInt(index)>1 ){
									layer.open({
									    content: '没有更多了',
									    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
									    time: 2
									});
								}
							}
							$('#refresh_end').hide();
						}else{
							layer.open({
							    content: data['message'],
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
    }
  	
  	function clickUser(sort,quote_content){
  		sort="回复第"+sort+"楼";
  		quote_content='<b>'+sort+'</b><br/>'+quote_content;
  		$("#quote_content").val(quote_content);
  		
  		$("#huifutxt").attr("placeholder",sort);
  		$("#huifutxt").focus();
  	}
  	
  	function clickreply(){
  		var uid=document.getElementById("uid").value;
  		if(uid==null || uid==''){
  			layer.open({
			    content: "请先登录！",
			    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
			    time: 2
			});
  			return false;
  		}else{
  			var topic_id=document.getElementById("topic_id").value;
  			var quote_content=document.getElementById("quote_content").value;
  			var huifutxt=document.getElementById("huifutxt").value;
  			var reply_center=huifutxt.replace(/(^\s*)|(\s*$)/g,'');
  			if(huifutxt!=null && huifutxt!='' && reply_center!=''){
  				huifutxt=huifutxt.replace(/(^\s*)|(\s*$)/g,'');
  			}else{
  				layer.open({
				    content: "请输入回复内容！",
				    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
				    time: 2
				});
  				return false;
  			}
  			
  			var but=document.getElementById('huifu');
			but.disabled=true;
			var result='';
			$.ajax({
	             type: "post",
	            url: "<%=url%>",
	             data: {
	             		'do':'addTopicReply',
	             		'version':'<%=version%>',
	             		'appid':'<%=appid%>',
	             		'content':reply_center,
	             		'topic_id':topic_id,
	             		'uid':uid,
	             		'quote_content':quote_content
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
             			result=data['result'];
             			if(result=='1'){
             				layer.open({
							    content: data['message'],
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
             			}else{
							layer.open({
							    content: data['message'],
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
		   		document.getElementById("la").value="1";
		   		$("#huifutxt").attr("placeholder",'回复楼主');
		   		document.getElementById("huifutxt").value="";
		   		document.getElementById("quote_content").value="";
		   }
	  	}
  	}
  	
    </script>
	</head>
	<body class="bodypage" onload="find(1,0)" ontouchmove='return false;'>
		<header id="head">
		<ul id="TopicType">
	    <!-- 
	        <li class="active" data-class="1"><a>爸妈学堂</a></li>
	        <li data-class="2"><a>成长分享</a></li>
	        <li data-class="3"><a>互助交流</a></li> -->
	    </ul>
		</header>
		<div id="body">
			<div class="innerlist">
				<div style="text-align: center; display: none" id="refresh_head">
					<img src="rs/img/icon/loding.png">
				</div>
				<h1 id="topic_title" class="wordwrap">
				</h1>
				<ul class="info">
					<!-- <li class="name lineicon">
						<ul id="topic_label"></ul>
					</li> -->
					<li style="float:left;margin-left:10px;">
						<font id="topic_name"></font>
					</li>
					<li style="float:right;margin-right:10px;">
						<span>
						<font id="topic_createtime"></font>
						</span>
						<span>
						阅读
						<font id="topic_countbrowse">0</font>
						</span>
						<span>
						评论
						<font id="topic_countback">0</font>
						</span>
					</li>
				</ul>
				<div id="topic_affix" style="background-color:#fff">
				</div>
				<p id="topic_content" class="wordwrap">
				</p>
				<div class="list" id="topic_reply">
					<ul class="row">
	                <li class="left"></li>
	                <li class="center">
	                    <span></span>
	                    <h1 style="text-align:center">暂无回帖</h1>
	                </li>
	                <li></li>
	            </ul>
				</div>
				<div style="height: 30px"></div>

				<div style="text-align: center; display: none" id="refresh_end">
					<div class="load-container load8">
						<div class="loader">
							加载新数据...
						</div>
					</div>
				</div>
				
				
			</div>

		</div>

		<div id="bottom">
			<input type="hidden" id="la" name="la" value="0" />
			<input type="hidden" id="index" name="index" value="<%=index%>" />
			<input type="hidden" id="uid" name="uid" value="<%=uid%>" />
			<input type="hidden" id="topic_id" name="topic_id" value="<%=topic_id%>" />
			<input type="hidden" id="quote_content" name="quote_content" />
			<div class="huifu_bd">
				<input name='huifutxt' class="huifutxt" id="huifutxt"
					placeholder="回复楼主" />
			</div>
			<a id="huifu" href="javascript:void(0);" onclick="clickreply()">
				<div name='huifu' class="huifu_btn">
				</div> </a>
			<div style="float:left;width:60px;height: 55px;" onclick="javascript:history.go(-1)">
				<img src='./rs/img/menu_list_1.png' width="50"/>
			</div>
			<div></div>
		</div>


	</body>
	<script>
var type = "<%=type%>";
var uid = "<%=uid%>";
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
	            if(typeof(type) == "undefined"||type==''||type=='null'){
		            if(n==0){
		            	type = value.id;
		            }
	            }
	            if(value.id==type){
	            	html +="<li data-class=\""+value.id+"\" class=\"active\">";
	            }else{
	            	html +="<li data-class=\""+value.id+"\">";
	            }
	            html += "<a>"+value.name+"</a></li>";
	     		});
     		}
     		$('#refresh_end').hide();
	     	$("#TopicType").html(html);
	     	
	     	var hearul = $("#head ul li");
			  $.each(hearul, function(index2, val) {
			     $(this).click(function(){
			        hearul.removeClass('active').eq(index2).addClass('active');
			        var attr = $(this).attr("data-class");
			        type = attr;
			        window.location.href = 'index.jsp?type='+type+'&uid='+uid;
			     })
			  });
     	}
	})

   
    //列表滚动
    window.myscroll =  new iScroll($('.innerlist')[0], {

        desktopCompatibility:true
        ,onScrollMove:function(){
            if(this.y>20 && $('#refresh_head').is(':hidden')){
                $('#refresh_head').show();
                this.minScrollY = 0;
            }else if (this.y < (this.maxScrollY -30) && $('#refresh_end').is(':hidden')){
                $('#refresh_end').show();
    //                this.refresh();
                this.maxScrollY-=30;
            }
        }
        ,onScrollEnd:function(){
        	var index=$("#index").val();
        	var la=$("#la").val();
        	if(index==null || index=='') index=1;
        	var num=parseInt(index);
            if($('#refresh_head').is(':visible')){
            	$('#refresh_head').hide();
            	$('#refresh_end').hide();
            }else if($('#refresh_end').is(':visible')){
            	var  me = this;
            	num=num+1;
	            find(num,la);
	           // setTimeout(function(){
               //     me.refresh();
               // });

            }
        }
    });


});



</script>

</html>