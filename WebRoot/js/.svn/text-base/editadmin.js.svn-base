
var url_flag = true;
function display(obj1, obj2) {
	if (!url_flag) {
		url_flag = true;
		return;
	}
	if (document.getElementById(obj2).style.display == "none") {
		document.getElementById(obj1).style.color = "blue";
		document.getElementById(obj1).style.backgroundImage = "url(images/widget_minus.gif)";
		document.getElementById(obj2).style.display = "block";
	} else {
		document.getElementById(obj1).style.color = "black";
		document.getElementById(obj1).style.backgroundImage = "url(images/widget_plus.gif)";
		document.getElementById(obj2).style.display = "none";
	}
}
function checkAll(obj, id1, id2) {
	url_flag = false;
	var id1 = document.getElementById(id1);
	var id2 = document.getElementById(id2);
	if (id2.style.display == "none") {
		id1.style.color = "blue";
		id1.style.backgroundImage = "url(images/widget_minus.gif)";
		id2.style.display = "block";
	}
	var id2childs = id2.childNodes;
	if (obj.checked) {
		for (var i = 0; i < id2childs.length; i++) {
			var div = id2childs[i];
			var input = div.childNodes[0];
			input.checked = true;
		}
	} else {
		for (var i = 0; i < id2childs.length; i++) {
			var div = id2childs[i];
			var input = div.childNodes[0];
			input.checked = false;
		}
	}
}
function toConfirmRights() {
	setmyselectvalue();
	var str = document.getElementById("myselectvalue").value;
	document.getElementById("admin_rights").value = str;
	document.getElementById("error_admin_rights").innerHTML = "";
}

function toResetPWD() {
	var admin_account = document.getElementById("admin_account").value;
	var str = window.confirm("\u662f\u5426\u8981\u91cd\u7f6e\u8be5\u7ba1\u7406\u5458\u5e10\u53f7\u7684\u5bc6\u7801\uff1f");
	if (str) {
		AdminAjax.resetAdminPwd(admin_account, callBackReset);
	} else {
		return;
	}
}
function callBackReset(data) {
	if ("1010" == data) {
		alert("\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
	} else {
		if ("f" == data) {
			alert("\u91cd\u7f6e\u5931\u8d25");
		} else {
			alert("\u91cd\u7f6e\u6210\u529f\n\u65b0\u5bc6\u7801\u4e3a:" + data + "\n\u8bf7\u59a5\u5584\u4fdd\u5b58\u6216\u53ca\u65f6\u4fee\u6539");
		}
	}
}

function checkSAll(obj, obj1) {
	var o1 = document.getElementById(obj1);
	var o1Node = o1.childNodes;
			//二级菜单
	for (var i = 0; i < o1Node.length; i++) {
		var node1 = o1Node[i].childNodes[0].childNodes[0];
		if (obj.checked) {
			node1.checked = true;
		} else {
			node1.checked = false;
		}
		var node2 = o1Node[i].childNodes[1].childNodes;
				//3级菜单
		for (var j = 0; j < node2.length; j++) {
			var subnode2 = node2[j].childNodes[0];
			if (obj.checked) {
				subnode2.checked = true;
			} else {
				subnode2.checked = false;
			}
		}
	}
}



function checkSubAll(obj) {
	var subobj = document.getElementsByName('p'+obj.id);
	if(subobj.length>0)
	{
		for (var i = 0; i < subobj.length; i++) {
			subobj[i].checked = obj.checked;
			checkSubAll(subobj[i]);
		}
	}
}


function selectsub(obj,selectobj){
	for (var i = 0; i < selectobj.length; i++) {
			var s = selectobj[i].value.split(',');
			if(s.length==2 && s[1]!=null && s[1]!='' && s[1]==obj.id)
				selectobj[i].checked = obj.checked;
		}
}


function lookRight() {
	var tablerights = document.getElementById("rights_div");
	var rights = document.getElementById("rights");
	if (tablerights.style.display == "" && rights.style.display == "") {
		tablerights.style.display = "none";
		rights.style.display = "none";
	} else {
		tablerights.style.display = "";
		rights.style.display = "";
	}
}
function editrole() {
	var admin_rights = document.getElementById("admin_rights").value;
	var rolename = document.getElementById("rolename").value;
	var rolestate = document.getElementById("state").value;
	var roleid = document.getElementById("roleid").value;
	var admin = document.getElementById("admin").value;
	if (rolename == "") {
		alert("\u89d2\u8272\u540d\u4e0d\u80fd\u4e3a\u7a7a\uff01");
		return;
	}
	var but = document.getElementById("close");
	but.disabled = true;
	but.value = "\u4fee\u6539\u4e2d...";
	AdminAjax.updateRole(roleid, rolename, rolestate, admin_rights, admin, true, updateRoleBack);
}
function updateRoleBack(obj) {
	if (obj == "ok") {
		alert("\u4fee\u6539\u6210\u529f");
		window.close();
		opener.document.location.reload();
	} else {
		alert(obj);
	}
}
	
function deletetyype(obj) {
	if (obj == "ok") {
		alert("\u5220\u9664\u6210\u529f");
		window.close();
		if (opener) {
			opener.location.reload();
		}
	} else {
		alert(obj);
	}
}
function selectall(obj) {
	var chkobj = document.getElementById("tdtree").getElementsByTagName("input");       
	 for(var k = 0, len = chkobj.length; k < len; k++)    
	 {        
		  var t = chkobj[k].type;        
		  if(chkobj[k].id!='checkall' && t=="checkbox")        
		  {
				chkobj[k].checked=obj.checked;
		  }
	 }
}

function setmyselectvalue(){
	var str="";
	var chkobj = document.getElementById("tdtree").getElementsByTagName("input");       
	 for(var k = 0, len = chkobj.length; k < len; k++)    
	 {        
		  var t = chkobj[k].type;        
		  if(chkobj[k].id!='checkall' && t=="checkbox")        
		  {
				if(chkobj[k].checked)
				{
					var pcheck = document.getElementsByName('p'+chkobj[k].id);
					if(pcheck.length<=0)
						str=str+chkobj[k].id+";";
				}
		  }
	 }
	if(str.length>0)
		str = str.substr(0,str.length-1);
	document.getElementById('myselectvalue').value=str;	
}

