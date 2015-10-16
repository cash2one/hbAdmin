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
		<link href="<%=basePath %>js/uploadify.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath %>js/swfobject.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.0.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
		  $("#uploadify1").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue1',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:jpg或gif', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
		    	if(value=="2"){
		    		alert("图片尺寸不正确");
		    	}else{
			   　   alert("文件:" + fileObj.name + "上传成功");
			  		$("#hobby_img").val(value); 
			  		$("#see_hobby_img").attr("src",value);  
	                $("#see_hobby_img").show();
                }
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		 });
		 
		 function uploasFile1(){
		 	  jQuery('#uploadify1').uploadifyUpload() 	 		 
		 }
		 
   function addAdmin(){
   		var id=$("#id").val();
   		var hobby_content=$("#hobby_content").val();
   		var hobby_summary=$("#hobby_summary").val();
   		var hobby_img=$("#hobby_img").val();
   		var hobby_status=$("#hobby_status").val();
   		var index=$("#index").val();
   		var level_id ='';
		var level_obj=document.getElementsByName("level_id");
		for(var i=0 ;i<level_obj.length;i++ ){
			if(level_obj[i].checked){
				var v=level_obj[i].value;
				level_id+=v+",";
			}
		}
		if(""==hobby_content){
			alert("爱好内容不能为空");
			$("#hobby_content").focus();
			return false;
		}
		if(level_id==null || ''==level_id){
  	 		alert("请选择阶段！");
  	 		return;
  	 	}
		if(""==hobby_summary){
			alert("爱好简介不能为空");
			$("#hobby_summary").focus();
			return false;
		}
		if(""==hobby_img){
			alert("请上传图片");
			$("#hobby_img").focus();
			return false;
		}
		if(""==hobby_status){
			alert("请选择状态");
			$("#hobby_status").focus();
			return false;
		}
		$.ajax({
             type: "post",
             url: "<%=basePath %>globalHobby/update?callback=?",
             data: {
             	'id':id,
             	'hobby_content':hobby_content,
             	'hobby_summary':hobby_summary,
             	'hobby_img':hobby_img,
             	'hobby_status':hobby_status,
             	'index':index,
             	'level_id':level_id
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
             		window.location.href="<%=basePath %>globalHobby/search?index="+index;
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
					兴趣修改
				</li>
			</ul>
		</div>
		<!-- 页面位置 -->

		<div class="rightinfo">

			<div class="itab">
				<ul>
					<li>
						<a href="<%=basePath %>globalHobby/search">兴趣查询</a>
					</li>
					<li>
						<a class="selected">兴趣修改</a>
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
							爱好内容：
							<b>*</b>
						</label>
						<input type="text" id="hobby_content" name="hobby_content" size="30" value="${gh.hobby_content }"
							class="dfinput150" />
						<input type="hidden" id="index" name="index" size="30" value="${index }"
						class="dfinput150" />
						<input type="hidden" id="id" name="id" size="30" value="${gh.id }"
						class="dfinput150" />
					</li>
					<li><label>阶段：<b>*</b></label>
						<fieldset style="padding:15px;width:77%;border:1px solid #c7c7c7;">
						    <legend>勾选阶段</legend>
						   	<c:if test="${gllist!=null}">
								<c:forEach var="gl" items="${gllist}">
									<input  type="checkbox"  name="level_id" value="${gl.id }" 
									<c:if test="${ghaList!=null}">
										<c:forEach var="gha" items="${ghaList}">
											<c:if test="${gl.id==gha.level_id}"> checked="checked"</c:if>
										</c:forEach>
									</c:if>
									/>${gl.level_content }&nbsp;&nbsp;&nbsp;&nbsp;
					   			</c:forEach>
								
							</c:if>
						</fieldset>
					</li>
					<li>
						<label>
							爱好简介：
							<b>*</b>
						</label>
						<input type="text" id="hobby_summary" name="hobby_summary" size="30" value="${gh.hobby_summary }"
							class="dfinput150" />
					</li>
					<li>
						<label>
							图片说明：
							<b>*</b>
						</label>
						<input type="hidden" id="hobby_img" name="hobby_img" value="${gh.hobby_img }" class="dfinput150" readonly >
						<div style="padding-left:118px;">
						<div id="fileQueue1"></div> </br>
							<input type="file" name="uploadify1" id="uploadify1" />
							<p style="">
								<a href="javascript:uploasFile1()">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "hobby_img_list" style="padding-left:118px;">
							<img id="see_hobby_img" src="${gh.hobby_img }" width="150" height="150">
						</div>
					</li>
					<li>
						<label>
							状态：
							<b>*</b>
						</label>
						<select id="hobby_status" name="hobby_status" class="select select151">
							<option value="0" <c:if test="${gh.hobby_status=='0' }">selected</c:if>>
								不显示
							</option>
							<option value="1" <c:if test="${gh.hobby_status==1 }">selected</c:if>>
								显示
							</option>
							<option value="9" <c:if test="${gh.hobby_status==9 }">selected</c:if>>
								作废
							</option>
						</select>
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
