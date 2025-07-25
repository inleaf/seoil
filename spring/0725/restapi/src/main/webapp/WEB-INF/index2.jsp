<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><button id="execbtn">실행</button></div>
<div id="result"></div>
<script>
const execbtn = document.getElementById("execbtn");
const div = document.getElementById("result");
execbtn.addEventListener("click", (data)=>{
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "/objectjson", true);
	xhr.send();
	
	xhr.onload = function() {
	    if (xhr.status === 200) {
	        const result = JSON.parse(xhr.responseText);
	        console.log(result);
	        div.innerText=result["name"]+","+result["age"];
	    }else{
	    	console.error('요청 실패:',xhr.status);
	    }
	};
	xhr.onerror = function() {console.error('요청 중 오류 발생')}

});
</script>
</body>
</html>