<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/include/base.jsp" />
<html>
  <head>
	<script type="text/javascript">
	$(function(){	
		//顶部导航切换
		$(".nav li a").click(function(){
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
			
		})	
	})	
	</script>
  </head>
  
<body style="background:url(<%=basePath %>images/topbg.jpg) repeat-x; ">
 
    <div class="topleft">
     
    <a href="main.html" target="_parent"><img src="<%=basePath %>images/logo.png" title="系统首页" /></a><!-- -->
    </div>
   <!-- 
    <ul class="nav">
    <li><a href="main.jsp?class=dd_yonghu"  class="selected"><img src="images/icon01.png" title="权限管理" /><h2>权限管理</h2></a></li>
    <li><a href="imgtable.html" target="rightFrame"><img src="images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="imglist.html"  target="rightFrame"><img src="images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="tools.html"  target="rightFrame"><img src="images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="computer.html" target="rightFrame"><img src="images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="tab.html"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
     -->      
    <div class="topright">    
    <ul>
    <!-- 
    <li><span><img src="<%=basePath %>images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
     -->
    <li><a href="<%=basePath %>adminUser/dologout" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span><%=session.getAttribute("admin_account") %></span>
    <!-- 
    <i>消息</i>
    <b>5</b>
     -->
    </div>    
    
    </div>

</body>
</html>