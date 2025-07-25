<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script>
var xhr = new XMLHttpRequest();
xhr.open("GET", "/api/member?username=user1", true); // true = 비동기
xhr.onreadystatechange = function() {
  if (xhr.readyState == 4 && xhr.status == 200) {
    console.log(xhr.responseText); // 응답 처리
  }
};
xhr.send();
</script>


</html>