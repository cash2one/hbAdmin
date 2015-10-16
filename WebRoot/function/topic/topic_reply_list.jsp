<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <jsp:include page="/include/base.jsp" />
  <style type="text/css">
	
	.send {
		position:relative;
		width:700px;
		min-height:35px;
		background:#CED3D3;
		border-radius:5px; /* 圆角 */
		/*margin:30px auto 0;*/
		float:left;
		padding:5px;
		margin-bottom:15px;
		margin-left:10px;
	}
	.send .div{
		width:100%;
		float:left;
		margin-bottom:3px;
	}
	.send .center {
		position:relative;
		width:630px;
		min-height:35px;
		background:#FFFFFF;
		border-radius:5px; /* 圆角 */
		/*margin:30px auto 0;*/
		float:left;
		margin-top:3px;
		padding:2px;
	}
	
	.send .img {
		position:relative;
		width:630px;
		min-height:35px;
		/*background:#C9E3ED;*/
		border-radius:5px; /* 圆角 */
		/*margin:30px auto 0;*/
		float:left;
		margin-top:3px;
	}
	
	.send .img li{
		clear:none;
		float:left;
		padding:5px;
	}
	
	.send .img li img{
		width:140px;
		height:90px;
	}
		
	.send .title {
		position:relative;
		width:630px;
		min-height:35px;
		background:#C7EDCC;
		border-radius:5px; /* 圆角 */
		/*margin:30px auto 0;*/
		float:left;
		margin-top:3px;
		padding:2px;
	}
	
	.send .left{
		float:left;widht:50px;
		margin-right:6px;
	}
	
	.send .arrow {
		position:absolute;
		top:5px;
		/*right:-16px;  圆角的位置需要细心调试哦 */
		left:-16px;
		width:0;
		height:0;
		font-size:0;
		border:solid 8px;
		border-color:#FFFFFF #CED3D3 #FFFFFF #FFFFFF ;
	}
	
	.tzleft{
		width:50px;float:left;margin-right:5px;
		margin-left:20px;
	}
	
	.zhu_background{
		background:#D4E7F0;
	}
	
	.send .zhu {
		position:absolute;
		top:5px;
		/*right:-16px;  圆角的位置需要细心调试哦 */
		left:-16px;
		width:0;
		height:0;
		font-size:0;
		border:solid 8px;
		border-color:#FFFFFF #D4E7F0 #FFFFFF #FFFFFF ;
	}
</style>
  <script type="text/javascript">
   function sel(){
   		var topic_id=document.getElementById("topic_id").value;
   		if(topic_id==null || topic_id==''){
   			alert("请填写主贴ID！");
   			return false;
   		}
    	$('#formsubmit').attr('action','<%=basePath %>topicreply/dotopicreplylist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>topicreply/exptopicreplylist');   
    	$('#formsubmit').submit();
    }
    
    function updatestatus(id,status){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>topicreply/doupdatestatustopicreply",
             data: {
             		'id':id,
             		'status':status
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	var msg=data.res;
	             	if(msg=='1'){
	             		succ=1;
	             		alert(data.su);
	             	}else{
	             		alert(msg);
	             	}
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
         if(succ==1){
         	window.location.reload();
         }
  }
  
  
  function deletetopicreply(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>topicreply/dodeletetopicreply",
	             data: {
	             		'id':id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
		             		alert(data.su);
		             	}else{
		             		alert(msg);
		             	}
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
	         if(succ==1){
	         	window.location.reload();
	         }  		
  		}
 	 }
  
  	function oncheckboxRadio(obj){
		var check=document.getElementsByName("checkbox_label");
		for(var i=0 ;i<check.length;i++ ){
			check[i].checked=obj.checked;
		}
	}
	
	
	function updateLabelStatus(num,status){
		var check=document.getElementsByName("checkbox_label");
		var id_str='';
		var label_str=''
		for(var i=0 ;i<check.length;i++ ){
			if(check[i].checked){
				var v=check[i].value;
				id_str+=v.substring(0,v.indexOf("-"))+",";
				
				var label_status=v.substring(v.indexOf("-")+1);
		 	 	var start=num==0?'':label_status.substring(0,num);
		 	 	var end=num==2?'':label_status.substring(num+1);
		 	 	var label=start+status+end;
				label_str+=label+",";
			}
		}
 	 	
 	 	if(id_str==''){
 	 		alert("请勾选要修改的主贴");
 	 		return ;
 	 	}
 	 	
 	 	var urlstr="";
 	 	var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>topicreply/doupdatelabeltopicreply",
             data: {
             		'id':id_str,
             		'label':label_str
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	var msg=data.res;
	             	if(msg=='1'){
	             		succ=1;
	             		alert(data.su);
	             	}else{
	             		alert(msg);
	             	}
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
         if(succ==1){
         	window.location.reload();
         }
         if(succ==1){
			for(var i=0 ;i<check.length;i++ ){
				check[i].checked=false;
			}
         	window.location.reload();
         }
	}
	
	function oncheckboxRadio(obj){
		var check=document.getElementsByName("checkbox_id");
		for(var i=0 ;i<check.length;i++ ){
			check[i].checked=obj.checked;
		}
	}
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>帖子管理</li>
	    <li>回帖查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>topicreply/dotopicreplylist" class="selected">回帖查询</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>主贴ID：</label><input id="topic_id" name="topic_id" type="text" class="dfinput150" value="${topic_id }"/>
    	<label>回帖ID：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>楼层：</label><input id="sort" name="sort" type="text" class="dfinput150" value="${sort }"/>
    	<label>发帖用户ID：</label><input id="uid" name="uid" type="text" class="dfinput150" value="${uid }"/>
    	<br/><br/>
    	<label>回帖内容：</label><input id="content" name="content" type="text" class="dfinput150" value="${content }"/>
    	<label>状态：</label>
    	<select id="status" name="status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==status}">selected</c:if> >对内</option>
		   		<option value="1" <c:if test="${'1'==status}">selected</c:if> >对外</option>
		   		<option value="9" <c:if test="${'9'==status}">selected</c:if> >作废</option>
	    </select>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    &nbsp;
	    <c:if test="1!=1">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <c:if test="${topicList!=null}">
    	<div class="tzleft">
    		主贴
   	 	</div>
   	 	<div class="send zhu_background">
   	 		<div class="div">
	   	 		发帖人ID：${topicList.uid }，帖子ID：${topicList.id }，发帖IP：${topicList.ip }，
	   	 		IP所在地：${topicList.ip_address}
   	 		</div>
   	 		<div class="div">
	   	 		<div class="left">标题：</div>
	   	 		<div class="title">${topicList.title }</div>
   	 		</div>
   	 		<div class="div">
	   	 		<div class="left">内容：</div>
	   	 		<div class="center">${topicList.content}</div>
	   	 	</div>
   	 		<div class="div">
   	 		<div class="left">附件：</div>
			    <div class="img">
	   	 		<c:if test="${topicList.affix!=null && topicList.affix!=''}">
			    	<c:set value="${ fn:split(topicList.affix, ',') }" var="affix_arry" />
			    	<ul id = "img_list" >
			    	<c:forEach items="${affix_arry }" var="affix">
						<li>
							<img name="affix" src="${affix }"/>
			   			</li>
					</c:forEach>
					</ul>
			    </c:if>
			    </div>
			</div>
   	 		<div class="div">
   	 		发帖时间：${topicList.createtime } &nbsp; &nbsp; &nbsp;
   	 		评论数：${topicList.countbrowse }&nbsp; &nbsp; &nbsp; 
   	 		阅读数：${topicList.countback }&nbsp; &nbsp; &nbsp;
   	 		标签：
	   	 		<c:set var="xin"  value="${fn:substring(topicList.label, 0, 1)}" /> 
				<c:set var="jing"  value="${fn:substring(topicList.label, 1, 2)}" /> 
				<c:set var="ding"  value="${fn:substring(topicList.label, 2, 3)}" /> 
				<font style='<c:if test="${xin=='1'}">color:red;</c:if>'>顶</font>
				<font style='<c:if test="${jing=='1'}">color:red;</c:if>'>精</font>
				<font style='<c:if test="${ding=='1'}">color:red;</c:if>'>新</font>
   	 		&nbsp; &nbsp; &nbsp;
   	 		状态：
   	 			<c:if test="${topicList.status=='0'}"><font style="color:red;">对内</font></c:if>
 				<c:if test="${topicList.status=='1'}"><font style="color:green;">对外</font></c:if>
 				<c:if test="${topicList.status=='9'}"><font style="color:grey;">作废</font></c:if>
   	 		</div>
   	 		<div class="zhu"></div>
   	 	</div>
    </c:if>
   
    <c:if test="${topicreplyList!=null}">
    <div style="height:30px;width:100%;float:left;margin-bottom:10px;">
    &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
    ------------------------------------------------------------------------回帖------------------------------------------------------------------------
    <br />
     
    </div>
    <c:forEach var="topicreply" items="${topicreplyList}">
    <div style="width:100%;float:left;">
    	<div class="tzleft">
    		<c:if test="${topicreply.sort=='1'}">
    		沙发
    		</c:if>
    		<c:if test="${topicreply.sort=='2'}">
    		板凳
    		</c:if>
    		<c:if test="${topicreply.sort=='3'}">
    		地板
    		</c:if>
    		<c:if test="${topicreply.sort!='1' && topicreply.sort!='2' && topicreply.sort!='3'}">
    		第${topicreply.sort}楼
    		</c:if>
   	 	</div>
   	 	<div class="send">
   	 		<div class="div">
	   	 		回帖人ID：${topicreply.uid }，回帖ID：${topicreply.id }，回帖IP：${topicreply.ip }，
	   	 		IP所在地：${topicreply.ip_address}
   	 		</div>
   	 		<div class="div">
	   	 		<div class="left">内容：</div>
	   	 		<div class="center">${topicreply.content}</div>
   	 		</div>
   	 		<div class="div">
	   	 		回帖时间：${topicreply.createtime } &nbsp; &nbsp; &nbsp;
	   	 		状态：
	   	 			<c:if test="${topicreply.status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${topicreply.status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${topicreply.status=='9'}"><font style="color:grey;">作废</font></c:if>
   	 		</div>
   	 		<br/>
   	 		-------------------------------------------------------
   	 		<br/>
   	 		<div class="div">
	   	 		<div class="left">引用：</div>
	   	 		<div class="center">${topicreply.quote_content }</div>
   	 		</div>
   	 		<br/>
   	 		-------------------------------------------------------
   	 		<div class="div">
   	 			<div class="left">操作：</div>
		        <a style="display: <c:if test='${topicreply.status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topicreply.id}"/>','0');" class="tablelink">对内</a>
		        <a style="display: <c:if test='${topicreply.status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topicreply.id}"/>','1');" class="tablelink">对外</a>
		        <a style="display: <c:if test='${topicreply.status=="9"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topicreply.id}"/>','9');" class="tablelink">作废</a>
		        <a href="javascript:deletetopicreply('<c:out value="${topicreply.id}"/>')" class="tablelink">删除</a>
   	 		</div>
   	 		<div class="arrow"></div>
   	 	</div>
   	 	</div>
   	 </c:forEach>
    </c:if>
    
    
    <table id="myTable" class="tablelist display">
    <!-- 
    	<thead>
    	<tr>
        <th>ID</th>
    	<th>楼层</th>
    	<th>主贴ID</th>
    	<th>回复内容</th>
    	<th>回复IP地址</th>
    	<th>IP所在地</th>
    	<th>回帖用户ID</th>
    	<th>引用回复内容</th>
    	<th>回帖时间</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${topicreplyList!=null}">
    	<c:forEach var="topicreply" items="${topicreplyList}">
    		<tr>
    			<td><c:out value="${topicreply.id}"/></td>
				<td><c:out value="${topicreply.sort}"/></td>
				<td width="15%" title="<c:out value="${topicreply.content}"/>">
					<div class="div_textflow150">
						<c:out value="${topicreply.content}"/>
					</div>
				</td>
				<td><c:out value="${topicreply.topic_id}"/></td>
				<td><c:out value="${topicreply.ip}"/></td>
				<td><c:out value="${topicreply.ip_address}"/></td>
				<td><c:out value="${topicreply.uid}"/></td>
				<td><c:out value="${topicreply.quote_content}"/></td>
				<td><c:out value="${topicreply.createtime}"/></td>
    			<td>
    				<c:if test="${topicreply.status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${topicreply.status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${topicreply.status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
			        <a style="display: <c:if test='${topicreply.status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topicreply.id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${topicreply.status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topicreply.id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${topicreply.status=="9"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topicreply.id}"/>','9');" class="tablelink">作废</a>
			        <a href="javascript:deletetopicreply('<c:out value="${topicreply.id}"/>')" class="tablelink">删除</a>
    			</td>
    		</tr>
    	</c:forEach>
    	</c:if>
    	</tbody>
    	 -->
    </table>
    ${pageinfo}
    </div>
    <script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>
