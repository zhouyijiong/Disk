window.onload = function(){

}
function querySpace(){
	$.ajax({
		type:"POST",
		url:"/system/querySpace",
		dataType:"json",
		success:function(data){
			if(data.code === 500){
				alert(data.msg);
				return
			}
			console.log(data.space);
		}
	})
}
function queryCache(){
	$.ajax({
		type:"POST",
		url:"/system/queryCache",
		dataType:"json",
		success:function(data){
			if(data.code === 500){
				alert(data.msg);
				return
			}
			console.log(data.cache);
		}
	})
}
function queryFileByCategory(){

}