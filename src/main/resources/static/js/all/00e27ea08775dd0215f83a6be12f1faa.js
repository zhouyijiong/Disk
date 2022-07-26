const ajax = new Ajax();
const byteArray = ['B','KB','MB','GB','TB','PB','EB','ZB','YB','BB','NB','DB','CB'];

function checkStr(str,min,max){
	let pattern=/^[0-9a-zA-Z]*$/;
	return(str.length>=min&&str.length<=max)&&pattern.test(str)
}

function checkNum(str,min,max){
	let pattern=/^[0-9]*$/;
	return(str.length >= min && str.length <= max) && pattern.test(str)
}

function checkMail(str){
	let reg=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	return reg.test(str)
}

function fileSizeFormat(size){
	let index = 0;
	while(size > 1023){
		size >>= 10;
		++index;
	}
	return size.toFixed(2) + byteArray[index];
}

function setCookie(name,value,day){
	let exp = new Date();
	exp.setTime(exp.getTime() + day * 60 * 60 * 24 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toUTCString();
}

function getCookie(name){
	var arr,reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	return (arr = document.cookie.match(reg)) ? unescape(arr[2]) : null;
}

function delCookie(name){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
    if(cval != null)document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function logout(){
	$.ajax({
		type:"POST",
		url:"/file/logout",
		dataType:"json",
		success:function(data){
			window.location.href="/"
		}
	})
}