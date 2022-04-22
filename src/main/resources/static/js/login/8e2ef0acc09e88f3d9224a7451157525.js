let login_status;
let login_url;
const login_status_array = [
	new LoginStatusVo('登录','login','/user/login'),
	new LoginStatusVo('注册','registered','/user/registered')
]
window.onload=function(){
	login_status = 0;
	login_url = '/user/login'
	document.getElementById("usm").focus();
}
class LoginStatusVo{
	constructor(status,viewInfo,url){
		this.status = status;
		this.viewInfo = viewInfo;
		this.url = url;
	}
}
function updateState(obj){
	let loginStatus = login_status_array[++login_status % login_status_array.length];
	let login = document.getElementById("login");
	login_url = loginStatus.url;
	obj.innerHTML = loginStatus.viewInfo;
	login.value = loginStatus.status;
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

	if(!ajax.post(login_url,data)){
		usm.value="";
		pwd.value="";
		usm.focus();
		localStorage.access = data.access;
		window.location.href = "/";
	}
}
document.getElementById("usm").addEventListener("keyup",function({keyCode}){
	if(keyCode === 13) document.getElementById("pwd").focus();
});
document.getElementById("pwd").addEventListener("keyup",function({keyCode}){
	if(keyCode === 13) judgment();
});