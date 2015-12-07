function panduan_weixin(){
		 if(isWeiXin()){
			document.getElementById('weixin_tishi').style.display = "";
			//window.location.href="https://appsto.re/cn/yS8J7.i";
		}else{
			document.getElementById('xiazai').href="https://appsto.re/cn/yS8J7.i";
		}
}
function isWeiXin(){
	var ua = navigator.userAgent.toLowerCase();  
	if(ua.match(/MicroMessenger/i)=="micromessenger") {  
		return true;  
	} else {  
		return false;  
	}  
}


function datediff(date) {
	    var returnhours = 0;
	    var date1 = new Date(date.replace(/-/g, "/")).getTime();  //开始时间
	    var date2 = new Date().getTime();    //结束时间
	    var date3 = date2 - date1;  //时间差的毫秒数
	    if (date3 >= 0) {
	        //计算出相差天数
	        var days = Math.floor(date3 / (24 * 3600 * 1000))
	        //计算出小时数
	        var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
	        var hours = Math.floor(leave1 / (3600 * 1000))
	        //计算相差分钟数
	        var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
	        var minutes = Math.floor(leave2 / (60 * 1000))
	        returnhours = hours;
	        if(parseInt(days)>=30) {
	            returnhours = "1个月前";
	        }else if(30>parseInt(days)&&parseInt(days)>0){
	        	returnhours = days+"天前";
	        }else{
	        	if(parseInt(hours)>1){
	        		returnhours = hours+"小时前";
	        	}else{
	        		returnhours = minutes+"分钟前";
	        	}
	        }
	    } else {
	        //layer.alert('时间添错了，亲！');
	        returnhours=date;
	    }
	    return returnhours;
	}
	function clickUl(e){
	       var tid = $(e).attr('name');
	       window.location.href = 'topic.jsp?tid='+tid;
	}