<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width: 100%
}
</style>
</head>
<body>
  <table class="table">
    <caption class="text-center"><h3>${year }년도 ${month }월</h3></caption>
    <tr class="danger">
      <c:forEach var="strWeek" items="${strWeek }">
        <td class="text-center">${strWeek }</td>
      </c:forEach>
      <c:set var="week" value="${week }"/>
      <c:forEach var="i" begin="1" end="${lastday }">  
       <c:if test="${i==1 }">
        <tr>
         <td class="text-center">&nbsp;</td>
       </c:if>
        <c:if test="${rday[i]==1}">
         <td class="text-center danger rdays">${i }</td>
        </c:if>
        <c:if test="${rday[i]==0}">
         <td class="text-center">${i }</td>
        </c:if>
        <c:set var="week" value="${week+1 }"/>
        <c:if test="${week>6 }">
          <c:set var="week" value="0"/>
          </tr>
          <tr>
        </c:if>
      </c:forEach>
    </tr>
  </table>
</body>
</html>