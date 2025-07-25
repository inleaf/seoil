<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$.ajax({
	//url:"http:// 172.16.15.72:8888/api/members", //동일한 주소가 아니므로 문제발생
	url: "https://jsonplaceholder.typicode.com/users/2",
	//동일하지 않은 경우에도 header에서 cors를 허락하면 가능
	//site에서 cors origin이 허락된 사이트임
	method:"get",
	success: function(response){
		console.log(response);
	}
});
</script>
</head>	
<body>

</body>
</html>