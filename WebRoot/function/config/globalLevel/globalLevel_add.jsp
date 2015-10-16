<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<jsp:include page="/include/base.jsp" />
		<jsp:include page="/include/date.jsp" />
		<script type="text/javascript">
   function addAdmin(){
   		var level_content=$("#level_content").val();
   		var level_summary=$("#level_summary").val();
   		var level_status=$("#level_status").val();
   		var index=$("#index").val();
   		var sort = $("#sort").val();
   		var regnum = new RegExp("^[0-9]*$");
		if(""==level_content){
			alert("等级名称不能为空");
			$("#level_content").focus();
			return false;
		}
		if(""==level_summary){
			alert("等级简介不能为空");
			$("#level_summary").focus();
			return false;
		}
		if(""!=sort&&!regnum.test(sort)){
			alert("排序ID为数字！");
			$("#sort").focus();
			return false;
		}
		if(""==level_status){
			alert("请选择状态");
			$("#level_status").focus();
			return false;
		}
		$.ajax({
             type: "post",
             url: "<%=basePath %>globalLevel/add?callback=?",
             data: {
             	'level_content':level_content,
             	'level_summary':level_summary,
             	'level_status':level_status,
             	'index':index,
             	'sort':sort
             },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
             	var res=data.res;
             	var msg=data.info;
             	if(res=='0'){
             		alert(msg);
             	}else if(res=='1'){
             		alert(msg);
             		window.location.href="<%=basePath %>globalLevel/search?index="+index;
             	}
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
   }
  </script>
	</head>

	<body>
		<!-- 页面位置 -->
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="<%=basePath %>index.jsp">首页</a>
				</li>
				<li>
					全局配置
				</li>
				<li>
					阶段添加
				</li>
			</ul>
		</div>
		<!-- 页面位置 -->

		<div class="rightinfo">

			<div class="itab">
				<ul>
					<li>
						<a href="<%=basePath %>globalLevel/search">阶段查询</a>
					</li>
					<li>
						<a href="<%=basePath %>globalLevel/toadd?index=${index }" class="selected">阶段添加</a>
					</li>
				</ul>
			</div>

			<div id="tab1" class="tabson">
				<div class="formtext">
					提示：无
				</div>
				<ul class="forminfo">
					<li>
						<label>
							等级名称：
							<b>*</b>
						</label>
						<input type="text" id="level_content" name="level_content" size="30"
							class="dfinput150" />
						<input type="hidden" id="index" name="index" size="30" value="${index }"
						class="dfinput150" />
					</li>
					<li>
						<label>
							等级简介：
							<b>*</b>
						</label>
						<input type="text" id="level_summary" name="level_summary" size="50"
							class="dfinput150" />
					</li>
					<li>
						<label>
							排序：
							<b></b>
						</label>
						<input type="text" id="sort" name="sort" size="8"
							class="dfinput150" />
					</li>
					<li>
						<label>
							状态：
							<b>*</b>
						</label>
						<select id="level_status" name="level_status" class="select select151">
							<option value="">
								请选择
							</option>
							<option value="0">
								不显示
							</option>
							<option value="1">
								显示
							</option>
						</select>
					</li>
					<li>
						<label>
							<b></b>
						</label>
						<input type="button" id="create_account" name="create_account"
							value="添 加" onclick="javascript:addAdmin();" class="btn" />
					</li>

				</ul>
			</div>
		</div>

	</body>
</html>
