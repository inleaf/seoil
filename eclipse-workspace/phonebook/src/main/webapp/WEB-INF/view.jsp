<%@page import="phonebook.Phonebook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<% Phonebook pb = (Phonebook)request.getAttribute("pb"); %>
<%if(pb!=null){ %>
<div class="container mt-5">
  <div class="card shadow-sm">
    <div class="card-header bg-primary text-white">
      <h3 class="mb-0">전화번호 상세보기</h3>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item"><strong>아이디:</strong> <%= pb.getId() %></li>
      <li class="list-group-item"><strong>이름:</strong> <%= pb.getName() %></li>
      <li class="list-group-item"><strong>전화번호:</strong> <%= pb.getHp() %></li>
      <li class="list-group-item"><strong>이메일:</strong> <%= pb.getEmail() %></li>
      <li class="list-group-item"><strong>메모:</strong> <%= pb.getMemo() %></li>
    </ul>
  </div>
</div>
<button onclick="location.href='/phonebook/updateform?id=<%=pb.getId()%>'">수정</button>
<button onclick="location.href='/phonebook/delete?id=<%=pb.getId()%>'">삭제</button>
<%} else {%>
<script>
alert("찾는 데이터가 없습니다.");
location.href="/searchid.html";
</script>
<%} %>
</body>
</html>