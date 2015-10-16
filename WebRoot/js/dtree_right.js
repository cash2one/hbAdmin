
function checkSubAll(obj) {
	var subobj = document.getElementsByName("p" + obj.id);
	if (subobj.length > 0) {
		for (var i = 0; i < subobj.length; i++) {
			subobj[i].checked = obj.checked;
			checkSubAll(subobj[i]);
		}
	}
}
function selectall(obj) {
	var chkobj = document.getElementById("tdtree").getElementsByTagName("input");
	for (var k = 0, len = chkobj.length; k < len; k++) {
		var t = chkobj[k].type;
		if (chkobj[k].id != "checkall" && t == "checkbox") {
			chkobj[k].checked = obj.checked;
		}
	}
}
function setmyselectvalue() {
	var str = "";
	var chkobj = document.getElementById("tdtree").getElementsByTagName("input");
	for (var k = 0, len = chkobj.length; k < len; k++) {
		var t = chkobj[k].type;
		if (chkobj[k].id != "checkall" && t == "checkbox") {
			if (chkobj[k].checked) {
				var pcheck = document.getElementsByName("p" + chkobj[k].id);
				if (pcheck.length <= 0) {
					str = str + chkobj[k].id + ";";
				}
			}
		}
	}
	if (str.length > 0) {
		str = str.substr(0, str.length - 1);
	}
	document.getElementById("myselectvalue").value = str;
}
function toConfirmRights() {
	setmyselectvalue();
	var str = document.getElementById("myselectvalue").value;
	document.getElementById("adminRights").value = str;
}

