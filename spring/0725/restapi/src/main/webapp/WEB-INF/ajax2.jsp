<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
//contentType 속성과 headers{Content-Type}은 같은 속성이지만 차이점은
//contentType은 기본적인 속성이며 application/x-www-form-urlencoded default 값으로 가짐
//header에 있는 Content-Type은 사용자가 수동으로 설정을 하는 속성으로
//우선순위는 header에 있는 Content-Type 우선 순위를 가짐
$.ajax({
	  url: "/insert",
	  type: "post",                  
	  //contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	  //@RequestParam ?속성=값, application/x-www-form-urlencode 사용
	  //객체로 전달이 가능, 문자열 객체는 전달이 안됨
	  headers:{"Content-Type":"application/x-www-form-urlencoded",}, //{}값이 전달 됨
	  data:{name: "hongkildong", age:"20"},
	  
	  //content type이 application/json 일 경우 @RequestBody 사용
	  //객체로 전달이 불가하며 문자열의 객체로 전달해야함
	  //headers:{"Content-Type":"application/json",}, //{}는 값 전달 안됨
	  //data:JSON.stringify({name: "hongkildong", age:"20"}),
	  success: function(result) {
		  console.log(result);
	    $("#result").text(result);
	  },
	  error: function(xhr, status, error) {
	    console.log("에러:", error);
	  }
	});
</script>
</head>
<body>
<div id="result">결과 표시</div>
</body>
</html>