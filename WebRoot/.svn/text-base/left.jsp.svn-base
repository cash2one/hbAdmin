<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/include/base.jsp" />
  <head>
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
	
})	
</script>
  </head>
  
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>管理菜单</div>
    
    <dl class="leftmenu">
    
    <dd>
    <a href="<%=basePath %>index.jsp" target="rightFrame">
    <div class="title"><span><img src="<%=basePath %>images/leftico01.png" /></span>首页</div>
    </a>
    	<ul class="menuson">
       
        </ul>    
    </dd>
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>注册用户</div>
    <ul class="menuson">
    	<li><cite></cite><a href="<%=basePath %>user/douserlist" target="rightFrame">用户管理</a><i></i></li>
    </ul>
    </dd>  
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>全局统计</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>user/douserstatistics" target="rightFrame">注册用户统计</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>weekdayresource/doweekdaystatistics" target="rightFrame">每周推荐统计</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>userlearnplan/tolearnplanstatistics" target="rightFrame">学习计划统计</a><i></i></li>
    </ul>
    </dd> 
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>资源管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>resourcetype/doresourcetypelist" target="rightFrame">资源类型</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>resource/doresourcelist"  target="rightFrame">资源信息</a><i></i></li>
    </ul>
    </dd>  
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>全局配置</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>globalLevel/search" target="rightFrame">阶段</a><i></i></li>
        <!-- <li><cite></cite><a href="<%=basePath %>globalHobby/search" target="rightFrame">兴趣</a><i></i></li> -->
        <li><cite></cite><a href="<%=basePath %>globalProperty/search" target="rightFrame">属性</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>globallanguage/dogloballanguagelist"  target="rightFrame">语言难度</a><i></i></li>
    </ul>
    </dd> 
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>每周推荐</div>
    <ul class="menuson">
       <!-- <li><cite></cite><a href="<%=basePath %>weekdayresource/doweekdayresourcelist" target="rightFrame">推荐统计</a><i></i></li> --> 
        <li><cite></cite><a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist" target="rightFrame">每周推荐</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>weekday/doweekdaylist" target="rightFrame">周期配置</a><i></i></li>
    </ul>
    </dd>  
    
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>学习计划</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>learnplan/dolearnplanlist" target="rightFrame">计划配置</a><i></i></li>
    </ul>
    </dd> 
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>搜索管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>searchkeyword/dosearchkeywordlist" target="rightFrame">搜索关键字</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>searchlogs/dosearchlogslist" target="rightFrame">搜索日志</a><i></i></li>
    </ul>
    </dd> 
    
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>帖子管理</div>
    <ul class="menuson">
    	<li><cite></cite><a href="<%=basePath %>topictype/dotopictypelist" target="rightFrame">帖子类型</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>topiclist/dotopiclistlist" target="rightFrame">主贴管理</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>topicreply/totopicreplylist" target="rightFrame">回帖管理</a><i></i></li>
    </ul>
    </dd> 
    
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>广告图管理</div>
    <ul class="menuson">
    	<li><cite></cite><a href="<%=basePath %>bigeyemodule/dobigeyemodulelist" target="rightFrame">广告图模块</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>bigeye/dobigeyelist" target="rightFrame">广告图</a><i></i></li>
    </ul>
    </dd>
    
    <dd><div class="title"><span><img src="<%=basePath %>images/leftico04.png" /></span>系统管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=basePath %>adminRole/toadminrolelist" target="rightFrame">角色管理</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>adminUser/toadminuserlist"  target="rightFrame">帐号与权限</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>adminUser/toadminloglist" target="rightFrame">操作日志</a><i></i></li>
    </ul>
    </dd>  
    
 </dl>
    
</body>
</html>
