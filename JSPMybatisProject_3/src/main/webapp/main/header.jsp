<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../member/login.do">Login</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <c:if test="${sessionScope.id!=null }">
        <li><a href="../reserve/reserve_main.do">맛집예약</a></li>
      </c:if>
      <li><a href="#">스토어</a></li>
      <c:if test="${sessionScope.id!=null }">
       <c:if test="${sessionScope.admin=='n' }">
        <li><a href="../mypage/mypage_home.do">마이페이지</a></li>
       </c:if>
       <c:if test="${sessionScope.admin=='y' }">
        <li><a href="../adminpage/admin_home.do">관리자페이지</a></li>
       </c:if>
      </c:if>
    </ul>
  </div>
</nav>
</body>
</html>