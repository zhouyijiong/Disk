window.onload=function(){
    let username = localStorage.access;
    if(username) document.getElementById("access").innerHTML = username;
}
function access(){
    window.location.href = "/management";
}