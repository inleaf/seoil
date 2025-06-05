<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${not empty pb}">
<div class="container mt-5">
  <div class="card shadow-sm">
    <div class="card-header bg-primary text-white">
      <h3 class="mb-0">전화번호 상세보기</h3>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item"><img src="/img/${pb.imgfilename}" width="200" alt="${pb.imgfilename}"
      style="border-radius:50%; border:1px solid black"></li>
      <li class="list-group-item"><strong>아이디:</strong> ${pb.id}</li>
      <li class="list-group-item"><strong>이름:</strong> ${pb.name}</li>
      <li class="list-group-item"><strong>전화번호:</strong> ${pb.hp}</li>
      <li class="list-group-item"><strong>이메일:</strong> ${pb.email}</li>
      <li class="list-group-item"><strong>메모:</strong> ${pb.memo}</li>
    </ul>
  </div>
</div>
<div class="container text-center mt-3">
<button class="btn btn-dark me-5" onclick="location.href='/updateForm?id=${pb.id}'">수정</button>
<button class="btn btn-danger" onclick="location.href='/delete?id=${pb.id}'">삭제</button>
</div>
</c:if>

<c:if test="${empty pb}">
<script>
alert("찾는 데이터가 없습니다.");
location.href="/searchid.html";
</script>
</c:if>
</body>
</html>