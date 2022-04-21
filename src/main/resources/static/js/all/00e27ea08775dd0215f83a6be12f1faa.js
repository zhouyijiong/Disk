let GET = "GET";
let POST = "POST";
let JSON = "json";


function form_data(){
	return new FormData();
}

function param_check(param,type){
	if(typeof param !== type) throw "parameter must be " + type;
}

function hashcode(str){
	param_check(str,'string');
	let hash = 0,i,chr,len;
	if(str.length === 0) return hash;
	for(i=0,len=str.length;i<len;i++){
		chr = str.charCodeAt(i);
		hash = ((hash << 5) - hash) + chr;
		hash |= 0;
	}
	return hash;
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

function ajax(type,url,dataType,formData){
	let result = true;
	$.ajax({
		contentType:false,processData:false,
		type:type,url:url,dataType:dataType,data:formData,
		success:function(data){
			if(data.code >= 4000){
				result = false;
				alert(data.msg);
			}
		}
	})
	return result;
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