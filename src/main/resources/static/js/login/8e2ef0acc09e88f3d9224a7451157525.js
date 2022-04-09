window.onload=function(){
	document.getElementById("usm").focus()
}

let login_state = 1;

function updateState(obj){
	let login = document.getElementById("login");
	if(login_state === 1){
		login_state = 0;
		obj.innerHTML="login";
		login.value="注册";
	}else{
		login_state = 1;
		obj.innerHTML="registered";
		login.value="登录";
	}
}
function judgment(){
	if(!navigator.cookieEnabled) {
		alert("cookie 被禁用,请开启再使用本站");
		return;
	}
	let usm = document.getElementById("usm");
	if(!checkStr(usm.value,3,8)){
		alert("用户名只能由3~10位字符或数字组成");
		usm.value="";
		usm.focus();
		return
	}
	var pwd = document.getElementById("pwd");
	if(!checkStr(pwd.value,6,16)){
		alert("密码只能由6~16位字符或数字组成");
		pwd.value="";
		pwd.focus();
		return
	}
	pwd.value = md5(pwd.value);
	$.ajax({
		type:"POST",
		url:"/user/"+(login_state === 1 ? 'login' : 'registered'),
		dataType:"json",
		data:{"username":usm.value,"password":pwd.value},
		success:function(data){

			if(data.code === 500){
				alert(data.msg);
				usm.value="";
				pwd.value="";
				usm.focus();
				return
			}
			localStorage.access = data.access;
			window.location.href = "/";
		}
	})
}
document.getElementById("usm").addEventListener("keyup",function({keyCode}){
	if(keyCode === 13) document.getElementById("pwd").focus();
});
document.getElementById("pwd").addEventListener("keyup",function({keyCode}){
	if(keyCode === 13) judgment();
});