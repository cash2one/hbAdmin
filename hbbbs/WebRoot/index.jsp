<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.bbs.util.MD5"%>
<%@ page import="com.bbs.util.CookieUtil"%>
<%@ page import="com.bbs.memcahed.MemCached"%>
<%@ page import="com.bbs.util.Constant"%>
<%@ page import="com.bbs.util.HttpConnectionUtil,net.sf.json.JSONObject,net.sf.json.JSONArray,com.bbs.bean.Eye"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String type = request.getParameter("type");
	String datasign = request.getParameter("sign");
	String uid = request.getParameter("uid");
	String vid = (String) CookieUtil.getCookieValue("hbbbsmid",request);
	String user_id = null;
	if(vid!=null&&!"".equals(vid)){
		user_id = (String) MemCached.getMccObject(vid);
	}
	Map hsm = new LinkedHashMap();
	hsm.put("uid", uid);
	String sign = MD5.md5Api(hsm);
	String flag = "";
	if(sign.equals(datasign)||user_id!=null){
		flag = "1";
	}else{
		flag = "0";
	}
	String eye_model=Constant.EYE1;
	String eye_size=Constant.SIZE1;
	if(type!=null && !type.equals("")){
		if(type.equals("2")){
			eye_model=Constant.EYE2;
			eye_size=Constant.SIZE2;
		}
		if(type.equals("3")){
			eye_model=Constant.EYE3;
			eye_size=Constant.SIZE3;
		}
	}
	String url = Constant.APIURL+"?do=getBigEyeList&module_id="+eye_model+"&len="+eye_size+"&version="+Constant.VERSION+"&appid="+Constant.APPID;
	String json_data=HttpConnectionUtil.getData(url);
	JSONObject jsonObj=JSONObject.fromObject(json_data);
	//{"version":"1.0.0","result":"1","message":"未获取到数据","data":[]}
	Object data=jsonObj.get("data");
	int bool_eye=0;
	List<Eye> eyeList=new ArrayList<Eye>();
	if(data!=null && !String.valueOf(data).equals("") && JSONArray.fromObject(data).size()>0){
		bool_eye=1;
		JSONArray array=JSONArray.fromObject(data);
		Eye eye=null;
		for(int i=0;i<array.size();i++){
			JSONObject obj=array.getJSONObject(i);
			eye=new Eye();
			eye.setId(obj.getString("id"));
			eye.setImg_url(obj.getString("img_url"));
			eye.setContent(obj.getString("content")==null?"":obj.getString("content"));
			eye.setLink_url(obj.getString("link_url")==null?"":obj.getString("link_url"));
			eye.setSort(obj.getString("sort")==null?"":obj.getString("sort"));
			eyeList.add(eye);
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
    <title>爸妈学堂</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="./rs/css/main.css" type="text/css">
    <link rel="stylesheet" href="./rs/css/loading_2.css">
	<link rel="stylesheet" href="./rs/css/layer.css">
    <style>
        .bg{
            height: 200px;
            width: 100%;
            border: none;
            position: relative;

        }
        .list{
            width: 100%;
        }
        .main_image{
            width: 100%;
            height: 100%;
            border: none;
            position: relative;
        }
        .main_image>ul{
            padding: 0;
            margin: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            position: absolute;
            top: 0;
            left: 0;
        }
        .main_image>ul>li{
            width: 100%;
            height: 100%;
            list-style: none;
            display: block;
            float: left;
        }
        .main_image>ul>li>span{
            display: block;
            width: 100%;
            height: 100%;
            background-size: 100% 100%;
        }
        .mui-slider-indicator {
            position: absolute;
            bottom: 30px;
            width: 100%;
            text-align: center;
            background: 0 0;
        }
        .mui-slider-indicator .mui-indicator.mui-active{
            background-color: #ccde7a;
        }
        .mui-slider-indicator .mui-indicator {
            display: inline-block;
            width: 6px;
            height: 6px;
            margin: 1px 6px;
            cursor: pointer;
            background: #92b831;
            border-radius: 50%;

        }

        .mui-slider-title{
            position: absolute;
            bottom: 0;
            width: 100%;
            left: 0;
        }
        .mui-slider-title>div{
            background-color: rgba(0,0,0,.5);
            color: #fff;
            display: none;
            padding: 5px;
        }
        .mui-slider-title>div.mui-active{
            display: block;
        }


        .list .row{
            margin-bottom: 10px;
            overflow: hidden;
            list-style: none;
            position: relative;
            height: 100px;
            width: 100%;
            padding: 10px;
            background-color: #fff;
        }
        /*.list .row>*{
            height: 70px;
        }
        */
        .liheight{
        	height:50px;
        }
        .liheight1{
        	height:30px;
        	line-height:30px;
        }
		.liheight1>li{
			padding-right: 8px;
		}
        .list .row>.left{
            float: left;
            width: 40px;

        }
        .list .row>.left>img{
            display: block;
            width: 40px;
            height: 40px;
			border-radius: 20px;
        }
        .list .row>.left>span{
            text-align: center;
            display: block;
            line-height: 28px;
            color: #ccde7a;
        }
        .list .row>.right{
            float: right;
            width: 60px;
        }
        .list .row>.right>img{
            height: 100%;
            width: 100%;
            border: none;
        }
        .list .row>.center{
            position: absolute;
            left: 10px;
            right: 70px;

        }
        .list .row>.center>h1{
            line-height: 20px;
            height: 40px;
            padding: 0;
            margin: 0;
            font-size: 16px;
        }
        .list .he_1{height:50px;}
        .list .row>.center>ul{
            color: #a1a1a1;
            list-style: none;
        }
        .list .row>.center>ul>li{
            display: inline-block;
            margin-right: 5px;
            height: 20px;
            line-height: 20px;
            font-size: 12px;
        }
        .innerlist{
            position: relative;
        }

    </style>

</head>
<body class="bodypage" ontouchmove='return false;'>
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
    <div style="text-align: center;display: none" id="refresh_head" >
    <img src="rs/img/icon/loding.png">
    </div>
	<%if(bool_eye==1){ %>
	    <div class="bg">
	        <div class="main_image"  name='imgslider'><!--图片-->
	            <ul>
	            	<%for(int i=0;i<eyeList.size();i++){ %>
	            		<li>
	            			<span style="background-image: url(<%=eyeList.get(i).getImg_url() %>)" 
	            			onclick="javascript:window.location.href='<%if(!eyeList.get(i).getLink_url().equals("")){ %><%=eyeList.get(i).getLink_url()%><%}else{ %>javascript:void(0);<%} %>'">
	            			</span>
	            		</li>
	            	<%}%>
	            </ul>
	            <a href="javascript:;" id="btn_prev"></a>
	            <a href="javascript:;" id="btn_next"></a>
	        </div>
	        <div class="mui-slider-indicator" name="slidercontrol"><!--图片对应的点-->
	            <%for(int i=0;i<eyeList.size();i++){ %>
	            	<div class="mui-indicator <%if(i==0){ %>mui-active<%} %>"></div>	
	            <%}%>
	        </div>
	        <div class="mui-slider-title" name="slidertitle"><!--图片对应的文字-->
	        	<%for(int i=0;i<eyeList.size();i++){ %>
	            	<div class="mui-indicator <%if(i==0){ %>mui-active<%} %>"><%=eyeList.get(i).getContent() %></div>	
	            <%}%>
	        </div>
	    </div>
	<%} %>
    <div class="list" id="topList">
			<!-- 
            <ul class="row" >
                        <li class="left"><img src="./rs/img/pic_userHead.jpg"><span>左面</span></li>
                        <li class="right"><img src="./rs/img/pic_userHead_1.jpg"></li>
                        <li class="center">
                            <h1>如何阅读ASJASHJKH DO YOU SEEE?</h1>
                            <ul>
                                <li class="lineicon icon_ding">2小时前</li>
                                <li>阅读 1000</li>
                                <li>评论 300</li>
                            </ul>
                        </li>
                    </ul> -->

        </div>

        <div style="text-align: center;display: none" id="refresh_end" >
            <div class="load-container load8">
                <div class="loader">加载新数据...</div>
            </div>
        </div>

    </div>

</div>
<div id="bottom">
    <ul>
        <li class="mytz"><a href="mytopic.jsp"></a></li>
        <li class="back"><a href="objc://backMainScene"></a></li>
        <li class="ft" id="fx"></li>
    </ul>
    <div></div>
</div>
</body>
<script src="./rs/js/jquery-1.9.1.min.js"></script>
<script src="./rs/js/touchsilder.js"></script>
<script src="./rs/js/iscroll.js"></script>
<script src="./rs/js/layer.m.js"></script>
<script>
var type = "<%=type%>";
var index = "1";
var uid = "<%=uid%>";
var flag = "<%=flag%>";
if(flag=="1"){
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
	
	var listhtml = "";
	$.ajax({
		async:false,
        type: "GET",
        url:"<%=basePath %>scripts/req",
        data:{'do':"getTopicList",
        	  'topic_typeId':type,
        	  'index':index,
        	  'uid':uid
        },
        dataType: "json",
        error: function(request) {
        },
        success: function(data) {
        	if(data.su.result == "0"){
        		listhtml ="";
        	}else{
	            $.each(data.su.data, function (n, value) {
	            listhtml +="<div  class=\"row\"  data-class="+value.id+"><ul class=\"liheight\">";
	           /*listhtml +="<li class=\"left\">";
	            if(value.user_avatar!=null && value.user_avatar!='' && value.user_avatar!='null'){
		            listhtml +="<img src="+value.user_avatar+">";
	            }else{
	            	 listhtml +="<img src='./rs/img/pic_userHead.jpg'>";
	            }
	            if(value.user_nickname!=null && value.user_nickname!='' && value.user_nickname!='null'){
		            listhtml +="<span>"+value.user_nickname+"</span></li>"
	            }else{
	            	 listhtml +="<span></span></li>"
	            }
	            */
	            var title_width="";
	            var title_length=32;
	            listhtml +="<li style=\"float:right;height:50px;\">";
	            if(value.affix!="null"&&value.affix!=""){
	            	listhtml+="<img src="+value.affix+" width=\"50\" height=\"50\"/>";
	            	title_width="width:80%";
	            	title_length=26;
	            }
	            listhtml +="</li><li style=\"float:left;"+title_width+"\"><div class=\"he_1\">";
	            /*var label=value.label;
      			if(label!=null && label!=''){
      				if(label.substring(0, 1)=='1'){
      					listhtml+='<span class=\"lineicon icon_ding1\"></span>';
      				}
      				if(label.substring(1, 2)=='1'){
      					listhtml+='<span class=\"lineicon icon_jing1\"></span>';
      				}
      				if(label.substring(2, 3)=='1'){
      					listhtml+='<span class=\"lineicon icon_xin1\"></span>';
      				}
      			}
      			*/
      			var title_str=value.title;
      			if(title_str!=null && title_str.length>30){
      				title_str=title_str.substring(0,title_length)+"...";
      			}
	            listhtml +="<span style=\"font-size:16px;\">"+title_str+"</span></div>";
	            listhtml +="</li></ul><ul class=\"liheight1\" style=\"font-size: 12px;\">";
	            if(value.user_nickname!=null && value.user_nickname!='' && value.user_nickname!='null'){
	            	listhtml +="<li style='float:left;'>"+value.user_nickname+"</li>";
	            }
	            listhtml +="<li style='float:right;'>评论 "+value.countback+"</li>"
	            listhtml +="<li style='float:right;'>阅读 "+value.countbrowse+"</li>";
	            listhtml +="<li style='float:right;'>"+datediff(value.createtime)+"</li>";
	            listhtml +="</ul></div>";
	     		});
     		}
     		$('#refresh_end').hide();
	     	$("#topList").html(listhtml);
     	}
	})
	
	<%if(bool_eye==1 && eyeList.size()>1){ %>
    //初始化图片滑动
   window.test =  $('[name=imgslider]').touchSlider({
                flexible : true,
                speed : 200,
                btn_prev : $("#btn_prev"),
                btn_next : $("#btn_next"),
//                    paging : $(".flicking_con a"),
                counter : function (e){
                    $('[name=slidercontrol]>div').removeClass("mui-active").eq(e.current-1).addClass("mui-active");
                    $('[name=slidertitle]>div').removeClass("mui-active").eq(e.current-1).addClass("mui-active");
                }
            });
    //图片自动滚动
    setInterval(function(){
        $("#btn_next").click();
    },3000);
    
    <%}%>
    //列表滚动
    new iScroll($('.innerlist')[0], {
        desktopCompatibility:true,
        hScrollbar: false,
        onScrollMove:function(){
            if(this.y>20 && $('#refresh_head').is(':hidden')){
                $('#refresh_head').show();
                this.minScrollY = 0;

            }else if (this.y < (this.maxScrollY - 30) && $('#refresh_end').is(':hidden')){
            	if(this.y<-10){
	                $('#refresh_end').show();
	    //                this.refresh();
	                this.maxScrollY-=30;
	                
	                
	      //---------------------          
	        //if($('#refresh_end').is(':visible')){

               var listhtml2 = "";
               
               var  me = this;
               
               index = parseInt(index)+1;
			$.ajax({
				async:false,
		        type: "GET",
		        url:"<%=basePath %>scripts/req",
		        data:{'do':"getTopicList",
		        	  'topic_typeId':type,
		        	  'index':index,
				      'uid':uid
		        },
		        dataType: "json",
		        error: function(request) {
		        },
		        success: function(data) {
		        	if(data.su.result == "0"){
		        		listhtml2 ="";
		        		layer.open({
							    content: '没有更多了！',
							    style: 'text-align:center;background-color:#92B831; color:#fff; border:none;',
							    time: 2
							});
		        		index = parseInt(index)-1;
		        	}else{
			            $.each(data.su.data, function (n, value) {
			            
			            
			            	listhtml2 +="<div  class=\"row\"  data-class="+value.id+"><ul class=\"liheight\">";
				           /*listhtml2 +="<li class=\"left\">";
				            if(value.user_avatar!=null && value.user_avatar!='' && value.user_avatar!='null'){
					            listhtml2 +="<img src="+value.user_avatar+">";
				            }else{
				            	 listhtml2 +="<img src='./rs/img/pic_userHead.jpg'>";
				            }
				            if(value.user_nickname!=null && value.user_nickname!='' && value.user_nickname!='null'){
					            listhtml2 +="<span>"+value.user_nickname+"</span></li>"
				            }else{
				            	 listhtml2 +="<span></span></li>"
				            }
				            */
				            var title_width="";
				            var title_length=32;
				            listhtml2 +="<li style=\"float:right;height:50px;\">";
				            if(value.affix!="null"&&value.affix!=""){
				            	listhtml2+="<img src="+value.affix+" width=\"50\" height=\"50\"/>";
				            	title_width="width:80%";
				            	title_length=26;
				            }
				            listhtml2 +="</li><li style=\"float:left;"+title_width+"\"><div class=\"he_1\">";
				            /*var label=value.label;
			      			if(label!=null && label!=''){
			      				if(label.substring(0, 1)=='1'){
			      					listhtml2+='<span class=\"lineicon icon_ding1\"></span>';
			      				}
			      				if(label.substring(1, 2)=='1'){
			      					listhtml2+='<span class=\"lineicon icon_jing1\"></span>';
			      				}
			      				if(label.substring(2, 3)=='1'){
			      					listhtml2+='<span class=\"lineicon icon_xin1\"></span>';
			      				}
			      			}
			      			*/
			      			var title_str=value.title;
			      			if(title_str!=null && title_str.length>30){
			      				title_str=title_str.substring(0,title_length)+"...";
			      			}
				            listhtml2 +="<span style=\"font-size:16px;\">"+title_str+"</span></div>";
				            listhtml2 +="</li></ul><ul class=\"liheight1\" style=\"font-size: 12px;\">";
				            if(value.user_nickname!=null && value.user_nickname!='' && value.user_nickname!='null'){
				            	listhtml2 +="<li style='float:left;'>"+value.user_nickname+"</li>";
				            }
				            listhtml2 +="<li style='float:right;'>评论 "+value.countback+"</li>"
				            listhtml2 +="<li style='float:right;'>阅读 "+value.countbrowse+"</li>";
				            listhtml2 +="<li style='float:right;'>"+datediff(value.createtime)+"</li>";
				            listhtml2 +="</ul></div>";
						            
			            
			            
			     		});
		     		}
		     		 
			     	$("#topList").append(listhtml2);
			     	
			     	//帖子点击
				    $('.row').click(function(){
				    	var tid = $(this).attr("data-class");
				       window.location.href = 'topic.jsp?tid='+tid+'&type='+type;
				    });
							     	
			     	 setTimeout(function(){
                        me.refresh();
                    });
			     	
		     	}
			})
			
          //  }
	      //--------------------
                }
            }
        }
        ,onScrollEnd:function(){
        	if($('#refresh_head').is(':visible')){
            
            	$('#refresh_head').hide();
            	$('#refresh_end').hide();

            }
           
        	if(this.y<-30){
            	
            }
            
           $('#refresh_end').hide();
            
       }
    });

    //帖子点击
    $('.row').click(function(){
    	var tid = $(this).attr("data-class");
       window.location.href = 'topic.jsp?tid='+tid+'&type='+type;
    });
    
    //发帖
    $('#fx').click(function(){
       window.location.href = 'post.jsp?type='+type;
    });

});
}

</script>
<script type="text/javascript">
function datediff(date) {

    var returnhours = 0;

    var date1 = new Date(date.replace(/-/g, "/")).getTime();  //开始时间

    var date2 = new Date().getTime();    //结束时间

    var date3 = date2 - date1;  //时间差的毫秒数
    
    if (date3 >= 0) {

        //计算出相差天数

        var days = Math.floor(date3 / (24 * 3600 * 1000))

        //计算出小时数

        var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数

        var hours = Math.floor(leave1 / (3600 * 1000))

        //计算相差分钟数

        var leave2 = leave1 % (3600 * 1000)    //计算小时数后剩余的毫秒数

        var minutes = Math.floor(leave2 / (60 * 1000))

        returnhours = hours;

        if(parseInt(days)>=30) {
            returnhours = "1个月前";
        }else if(30>parseInt(days)&&parseInt(days)>0){
        	returnhours = days+"天前";
        }else{
        	if(parseInt(hours)>1){
        		returnhours = hours+"小时前";
        	}else{
        		returnhours = minutes+"分钟前";
        	}
        }
    } else {
        returnhours = "";
    }
    return returnhours;
}
</script>
</html>