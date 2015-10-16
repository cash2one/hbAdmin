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
   		var id=$("#id").val();
   		var property_name=$("#property_name").val();
   		var property_status=$("#property_status").val();
   		var index=$("#index").val();
   		var level_id=$("#level_id").val();
   		var property_type=$("#property_type").val();
   		var sort = $("#sort").val();
   		var property_num = $("#property_num").val();
   		var regnum = new RegExp("^[0-9]*$");
		if(""==property_name){
			alert("属性名称不能为空");
			$("#property_name").focus();
			return false;
		}
		if(level_id==null || ''==level_id){
  	 		alert("请选择阶段！");
  	 		$("#level_id").focus();
  	 		return;
  	 	}
  	 	if(property_type==null || ''==property_type){
  	 		alert("请选择属性类型！");
  	 		$("#property_type").focus();
  	 		return;
  	 	}
		if(""==property_status){
			alert("请选择状态");
			$("#property_status").focus();
			return false;
		}
		if(""!=sort&&!regnum.test(sort)){
			alert("排序ID为数字！");
			$("#sort").focus();
			return false;
		}
		if(""!=property_num&&!regnum.test(property_num)){
			alert("请输入数字！");
			$("#sort").focus();
			return false;
		}
		$.ajax({
             type: "post",
             url: "<%=basePath %>globalProperty/update?callback=?",
             data: {
             	'property_name':property_name,
             	'property_status':property_status,
             	'index':index,
             	'id':id,
             	'sort':sort,
             	'level_id':level_id,
             	'property_type':property_type,
             	'property_num':property_num
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
             		window.location.href="<%=basePath %>globalProperty/search?index="+index;
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
					属性添加
				</li>
			</ul>
		</div>
		<!-- 页面位置 -->

		<div class="rightinfo">

			<div class="itab">
				<ul>
					<li>
						<a href="<%=basePath %>globalProperty/search">属性查询</a>
					</li>
					<li>
						<a class="selected">属性修改</a>
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
							属性名称：
							<b>*</b>
						</label>
						<input type="text" id="property_name" name="property_name" size="30" value="${gp.property_name }"
							class="dfinput150" />
						<input type="hidden" id="index" name="index" size="30" value="${index }"
						class="dfinput150" />
						<input type="hidden" id="id" name="id" size="30" value="${gp.id }"
						class="dfinput150" />
					</li>
					<li><label>阶段：<b>*</b></label>
						<select id="level_id" name="level_id" class="select select151">
							<option value="">
								请选择
							</option>
							<c:if test="${gllist!=null}">
								<c:forEach var="gl" items="${gllist}" varStatus="vs">
									<option value="${gl.id }" <c:if test="${gp.level_id==gl.id }">selected</c:if>>
										${gl.level_content }
									</option>
					   			</c:forEach>
							</c:if>
						</select>
					</li>
					<li><label>类型：<b>*</b></label>
						<select id="property_type" name="property_type" class="select select151">
							<option value="1" <c:if test="${gp.property_type==1 }">selected</c:if>>
								必选项
							</option>
							<option value="2" <c:if test="${gp.property_type==2 }">selected</c:if>>
								兴趣项
							</option>
						</select>
					</li>
					<li>
						<label>
							状态：
							<b>*</b>
						</label>
						<select id="property_status" name="property_status" class="select select151">
							<option value="0" <c:if test="${gp.property_status=='0' }">selected</c:if>>
								不显示
							</option>
							<option value="1" <c:if test="${gp.property_status==1 }">selected</c:if>>
								显示
							</option>
							<option value="9" <c:if test="${gp.property_status==9 }">selected</c:if>>
								作废
							</option>
						</select>
					</li>
					<li>
						<label>
							排序：
							<b></b>
						</label>
						<input type="text" id="sort" name="sort" size="8" value="${gp.sort }"
							class="dfinput150" />
					</li>
					<li>
						<label>
							书本数：
							<b></b>
						</label>
						<input type="text" id="property_num" name="property_num" size="8" value="${gp.property_num }"
							class="dfinput150" />
					</li>
					<li>
						<label>
							<b></b>
						</label>
						<input type="button" id="create_account" name="create_account"
							value="修 改" onclick="javascript:addAdmin();" class="btn" />
					</li>

				</ul>
			</div>
		</div>

	</body>
</html>
