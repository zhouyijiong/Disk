const GET = "GET",POST = "POST",JSON = "json";
const ajax = new Ajax();
function form_data(){
	return new FormData();
}

function checkStr(str,min,max){
	var pattern=/^[0-9a-zA-Z]*$/;
	return(str.length>=min&&str.length<=max)&&pattern.test(str)
}

function checkNum(str,min,max){
	var pattern=/^[0-9]*$/;
	return(str.length>=min&&str.length<=max)&&pattern.test(str)
}

function checkMail(str){
	var reg=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	return reg.test(str)
}

function fileSizeFormat(size){
	var sizeInfo="";
	if(size<1024){
		sizeInfo=size+" B"
	}else{
		if(size<1048576){
			size/=1024;
			sizeInfo=size.toFixed(2)+" KB"
		}else{
			if(size<1073741824){
				size/=1024;
				size/=1024;
				sizeInfo=size.toFixed(2)+" MB"
			}else{
				if(size<1099511627776){
					size/=1024;
					size/=1024;
					size/=1024;
					sizeInfo=size.toFixed(2)+" GB"
				}else{
					if(size<9999999999999){
						size/=1024;
						size/=1024;
						size/=1024;
						size/=1024;
						sizeInfo=size.toFixed(2)+" TB"
					}
				}
			}
		}
	}
	return sizeInfo
}

function setCookie(name,value){
	var Days = 7;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 7 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
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