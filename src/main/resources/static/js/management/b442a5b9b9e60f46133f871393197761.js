var id,info;window.onload=function(){var vars=window.location.search.substring(1).split("&");for(i=0;i<vars.length;i++){var pair=vars[i].split("=");switch(pair[0]){case"id":id=pair[1];break;case"info":info=pair[1];break}}$.ajax({type:"POST",url:"/file/receiveLink",dataType:"json",data:{"id":id,"info":info},success:function(data){if(data.error){alert(data.error);logout()}else{document.getElementById("after-left-top-info1").innerHTML=data.fileName;document.getElementById("after-left-after-panel-info").innerHTML=fileSizeFormat(data.fileSize);var date=new Date(data.shareTime);var lastTime=new Date()>date?"永久":date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate();document.getElementById("after-left-top-info2").innerHTML="到期时间 : "+lastTime;if(data.sharePwd){document.getElementById("after-left-top-input").style.display="block"}}}})};function download(){if(!checkExtractCode()){var code=document.getElementById("extractCode");$.ajax({type:"POST",url:"/file/getDownloadLink",dataType:"json",data:{"id":id,"info":info,"code":code.value},success:function(data){code.value="";if(data.error){alert(data.error);logout();return}if(data.exception){alert(data.exception);return}if(data.message){alert(data.message);code.value="";code.focus();return}window.location.href=data.downloadLink}})}}function save(){if(!checkExtractCode()){var code=document.getElementById("extractCode");$.ajax({type:"POST",url:"/file/saveLinkFile",dataType:"json",data:{"id":id,"info":info,"code":code.value},success:function(data){if(data.error){if(data.error="信息丢失"){alert("请登录账户");window.location.href="/"}else{alert(data.error);logout()}return}if(data.message){alert(data.message);code.value="";code.focus();return}alert("保存成功")}})}}function checkExtractCode(){var state=document.getElementById("after-left-top-input").style.display;var code=document.getElementById("extractCode");if(state=="block"&&code.value==""){alert("请输入提取码");code.focus();return true}return false};
