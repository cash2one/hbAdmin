<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>kindeditor/themes/default/default.css" ></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>kindeditor/plugins/code/prettify.css" ></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=basePath %>kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript">
	var self;
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="message"]', {
			cssPath : '<%=basePath %>kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath %>kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '<%=basePath %>kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			width:'300px',
			height:'480px',
			resizeType:0,
			urlType:'domain',
			afterCreate : function() {
				self = this;
			}
			
		});
		prettyPrint();
	});	
	</script>
</head>
</html>