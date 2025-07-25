<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
// $(선택자).ready(); -> $(선택자).ready(익명함수); //jquery는 모두 함수로 이루어져있다.
/*
$(document).ready(function(){
	alert('test');
});
*/
//jquery 축약형
//$(); -> $(익명함수) ->$(function(){})

/*
$.ajax({
  url: "/api/members"
  type: "GET",                  
  dataType: "json", 
  success: function(response) {
    console.log("성공:", response);
  },
  error: function(xhr, status, error) {
    console.log("에러:", error);
  }
});
*/
//문자열이 리턴인 값 확인: dataType: 'json' 오류 발생
/*
$.ajax({
  url: "/myname"
  type: "GET",                  
  dataType: "text", 
  success: function(response) {
	console.log(response);
    console.log("성공:", response);
  },
  error: function(xhr, status, error) {
    console.log("에러:", error);
  }
});
*/
/*
$.ajax({
	  url: "/api/members",
	  type: "GET",                  
	  dataType: "json", 
	  success: function(response) {
		console.log(response[0]);
		console.log(response[0]["username"]);
		console.log(response[0]["password"]);
		console.log(response[0]["email"]);
	    console.log("성공:", response);
	    //응답이 json 즉 객체일 때는 객체를 분해하는 방법(0번째 배열 출력)
	  },
	  error: function(xhr, status, error) {
	    console.log("에러:", error);
	  }
	});
*/
/*
$.ajax({
	  url: "/myjson",
	  type: "GET",                  
	  dataType: 'json', //text, json(문자열 json형태는 datatype에 따라 자동 변환)
	  success: function(response) {
	    console.log(response);
	    console.log(typeof response);
	    console.log(response["name"]);
	    let obj=JSON.parse(response);
	    console.log(obj["name"]);
	  },
	  error: function(xhr, status, error) {
	    console.log("에러:", error);
	  }
	});
*/
</script>
<script>
//아래 코드 동작 안함
/*
$("button").click(function(){
	alert('button click!!');
});
*/
/*
$(function(){
	$("button").click(function(){
		//alert('button click!!');
		$.ajax({
			  url: "/myjson",
			  type: "GET",                  
			  dataType: 'text', //text, json(문자열 json형태는 datatype에 따라 자동 변환)
			  success: function(response) {
			    $("#result").text(response);
			  },
			  error: function(xhr, status, error) {
			    console.log("에러:", error);
			  }
			});
	});
});
*/

/*
$(function(){
	$("button").click(function(){
		//$("#result").load("/myjson");
		$.get("/myjson", function(data, status){
			$("#result").text("Data: " + data + "\nStatus: "+ status);
		});
	});
});
*/

$(function(){
	$("button").click(function(){
		$.get("/api/members", function(data){
			//객체의 내용을 확인할 때 문자열로 변환하는 JSON.stringify() 활용
			$("#result").text(JSON.stringify(data));
		});
	});
});
</script>
</head>
<body>
<button>실행 버튼</button><br>
<div id="result">결과 표시</div>
</body>
</html>