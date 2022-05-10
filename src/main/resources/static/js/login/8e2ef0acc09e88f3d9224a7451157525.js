let login_status = 0;
let login_url = '/user/login';
window.onload=function(){
	document.getElementById("usm").focus();
}
function updateState(obj){
	let current,loginView;
	if(++login_status % 2 === 0){
		loginView = '登录';
		current = 'registered';
		login_url = '/user/login';
	}else{
		loginView = '注册';
		current = 'login';
		login_url = '/user/registered';
	}
	let login = document.getElementById("login");
	obj.innerHTML = current;
	login.value = loginView;
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
	let pwd = document.getElementById("pwd");
	if(!checkStr(pwd.value,6,16)){
		alert("密码只能由6~16位字符或数字组成");
		pwd.value="";
		pwd.focus();
		return
	}
	pwd.value = md5(pwd.value);

	let data = new FormData();
	data.set("username",usm.value);
	data.set("password",pwd.value);

	// let map = new HashMap();
	// map.put("username",usm.value).put("password",pwd.value)
	ajax.post(login_url,data,function(response){
		if(response.code > 0){
			pwd.value = "";
			usm.focus();
			alert(response.message);
		}else{
			data = JSON.parse(response.data);
			localStorage.token = data.token;
			localStorage.access = data.access;
			//ajax.get('/management');
			// ajax.syncGet('/management');
			// window.location.href = "/";
		}
	});
}
document.getElementById("usm").addEventListener("keyup",function({keyCode}){
	if(keyCode === 13) document.getElementById("pwd").focus();
});
document.getElementById("pwd").addEventListener("keyup",function({keyCode}){
	if(keyCode === 13) judgment();
});