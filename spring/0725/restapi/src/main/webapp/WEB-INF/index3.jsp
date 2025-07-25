<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><button id="insertexecbtn">입력실행</button></div>
<div id="result"></div>
<script>
const execbtn = document.getElementById("insertexecbtn");
const div = document.getElementById("result");
var xhr = new XMLHttpRequest();
xhr.onload = function() {
    if (xhr.status === 200  || xhr.status === 201) {
        const result = xhr.responseText;
        console.log(result);
        div.innerHTML=result;
        //result["username"]+"<br>"+result["password"]+"<br>"+result["email"];
    }else{
    	console.error('요청 실패:',xhr.status);
    }
};
xhr.onerror = function() {console.error('요청 중 오류 발생')}
execbtn.addEventListener("click", (d)=>{
	xhr.open("post", "/api/member", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	let data=JSON.stringify({"username":"admin", 
			"password":"1234", 
			"email":"admin@naver.com"});
	xhr.send(data);
	
	//xhr.onReadyStateChange
});
</script>
</body>
</html>