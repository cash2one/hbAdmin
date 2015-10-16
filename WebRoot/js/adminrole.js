var flag_admin_account = false;
		var flag_admin_account_exist = false;
		function checkAdmin_Account(obj){
			var admin_account = obj.value;
			var reg = /\W/;
			if ("" != admin_account && null == admin_account.match(reg)){
				document.getElementById('error_' + obj.id).innerHTML = "帐号格式正确";
				flag_admin_account = true;
			}else if (null != admin_account.match(reg)){
				document.getElementById('error_' + obj.id).innerHTML = "帐号格式错误";
				flag_admin_account = false;
				return;
			}else{
				document.getElementById('error_' + obj.id).innerHTML = "";
				flag_admin_account = false;
				return;
			}
			if (obj.id == "adminAccount"){
				//AdminAjax.checkAdminAccount(admin_account,checkAdminAccountCallBack);
			}
		}
		function checkAdminAccountCallBack(data){
			if("1010" == data){
				alert("服务器繁忙，请稍后再试");
			}else{
				if("s" == data){
					document.getElementById('error_adminAccount').innerHTML = "<font color=blue>帐号可以创建</font>";
					flag_admin_account_exist = true;
				}else if ("f" == data){
					document.getElementById('error_adminAccount').innerHTML = "<font color=blue>帐号已存在</font>";
					flag_admin_account_exist = false;
				}
			}
		}
		function toAddAdmin_Account(){
			var admin_account = document.getElementById("adminAccount").value;
			var admin_account_confirm = document.getElementById("adminAccountConfirm").value;
			var admin_account_pwd = document.getElementById("adminAccountPwd").value;
			var admin_account_pwd_confirm = document.getElementById("adminAccountPwdConfirm").value;
			var admin_checkrole = document.getElementById("adminCheckrole").value;
			var admin_account_realname=document.getElementById('adminAccountRealname').value;
			var admin_account_email=document.getElementById('adminAccountEmail').value;
			var admin_checkpf=document.getElementById('admin_checkpf').value;
			
			if (admin_account == ""){
				document.getElementById("adminAccount").focus();
				document.getElementById("error_adminAccount").innerHTML = "<font color=blue>帐号不能为空</font>";
				return;
			}
			if (admin_account_confirm == ""){
				document.getElementById("adminAccountConfirm").focus();
				document.getElementById("error_adminAccountConfirm").innerHTML = "<font color=blue>确认帐号不能为空</font>";
				return;
			}
			if (admin_account_pwd == ""){
				document.getElementById("adminAccountPwd").focus();
				document.getElementById("error_adminAccountPwd").innerHTML = "<font color=blue>密码不能为空</font>";
				return;
			}
			if (admin_account_pwd_confirm == ""){
				document.getElementById("adminAccountPwdConfirm").focus();
				document.getElementById("error_adminAccountPwdConfirm").innerHTML = "<font color=blue>确认密码不能为空</font>";
				return;
			}
			if (admin_account_pwd != admin_account_pwd_confirm){
				document.getElementById("error_adminAccountPwdConfirm").innerHTML = "<font color=blue>两次密码不一样</font>";
				return;
			}
			if (admin_checkrole == ""){
				document.getElementById("error_adminRights").innerHTML = "<font color=blue>必须设置角色</font>";
				return;
			}
			if (admin_account_realname == ""){
				document.getElementById("error_adminAccountName").innerHTML = "<font color=blue>管理员姓名必填</font>";
				return;
			}
			if (admin_account_email == ""){
				document.getElementById("error_adminAccountEmail").innerHTML = "<font color=blue>管理员email必填</font>";
				return;
			}
			if ((admin_account == admin_account_confirm) && flag_admin_account && flag_admin_account_exist){
				document.admin_account_add.submit();
			}else if (admin_account != admin_account_confirm){
				document.getElementById('error_adminAccountConfirm').innerHTML = "<font color=blue>两次帐号输入不同</font>";
			}
		}
		var url_flag = true;
		function display(obj1, obj2)
		{
			if (!url_flag){
				url_flag = true;
				return;
			}
			if(document.getElementById(obj2).style.display=="none") {
				document.getElementById(obj1).style.color="blue";
				document.getElementById(obj1).style.backgroundImage="url(/images/widget_minus.gif)";
				document.getElementById(obj2).style.display="block";
			}
			else {
				document.getElementById(obj1).style.color="black";
				document.getElementById(obj1).style.backgroundImage="url(/images/widget_plus.gif)";
				document.getElementById(obj2).style.display="none";
			}
		}
		function checkAll(obj,id1,id2){
		/*
		var s1=document.getElementsByName("selectSValue");
		var s2=document.getElementsByName("selectValue");
		
		if(obj.checked){
			for(var i=0;i<s1.length;i++){
				s1[i].checked=true;
			}
			for(var i=0;i<s2.length;i++){
				s2[i].checked=true;
			}
		}else{
			for(var i=0;i<s1.length;i++){
				s1[i].checked=false;
			}
			for(var i=0;i<s2.length;i++){
				s2[i].checked=false;
			}	
		}
		}*/
		//alert('id1='+id1+'  '+' id2 ='+id2);
	
			url_flag = false;
			var id1 = document.getElementById(id1);
			var id2 = document.getElementById(id2);
			if(id2.style.display=="none"){
				id1.style.color="blue";
				id1.style.backgroundImage="url(/images/widget_minus.gif)";
				id2.style.display="block";
			}
			var id2childs = id2.childNodes;
			if (obj.checked){
				for(var i = 0;i < id2childs.length;i++){
					var div = id2childs[i];
					var input = div.childNodes[0];
					input.checked = true;
				}
			}else{
				for(var i = 0;i < id2childs.length;i++){
					var div = id2childs[i];
					var input = div.childNodes[0];
					input.checked = false;
				}
			}
			}
		
		function toConfirmRights(){
		/*
			var rights = document.getElementById("rights");

			var rightsChild = rights.childNodes[0].childNodes;
			if(rights.childNodes[0].tagName!="UL")
				rightsChild = rights.childNodes[1].childNodes;
			var str = ";";
			//alert('rightsChild.length='+rightsChild.length);
			for(var i = 0;i < rightsChild.length;i++){
				if (rightsChild[i].tagName == "SPAN"){
					var spanChild = rightsChild[i].childNodes;
					for(var j = 0;j < spanChild.length;j++){
						var div = spanChild[j];
						var input = div.childNodes[0];
						if (input.checked){
							str = str + input.id + ";";
						}
					}
				}
			}*/
			var right=document.getElementsByName("selectValue");
			var str="";
			for(var i=0;i<right.length;i++){
				if(right[i].checked){
					str+=right[i].value+";";
				}
			}
			//alert('str='+str);
			//alert('str='+str);
			document.getElementById("adminRights").value = str;
			document.getElementById('rightsDiv').style.display='none'
			document.getElementById("error_adminRights").innerHTML = "";
		}
		
		function checkSAll(obj,obj1){
			var o1=document.getElementById(obj1);
			var o1Node=o1.childNodes;
			//二级菜单
			for(var i=0;i<o1Node.length;i++){
				var node1=o1Node[i].childNodes[0].childNodes[0];
				if(obj.checked)
					node1.checked=true;
				else
					node1.checked=false;	
					
				var node2=o1Node[i].childNodes[1].childNodes;
				//3级菜单
				for(var j=0;j<node2.length;j++){
					var subnode2=node2[j].childNodes[0];
						if(obj.checked)
							subnode2.checked=true;
						else
							subnode2.checked=false;	
				}
			}			
		}