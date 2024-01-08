<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.trs').click(function(){
		let poster=$(this).attr("data-poster");
		let name=$(this).attr("data-name");
		let fno=$(this).attr("data-fno");
		$('#food_image').attr("src",poster);
		$("#food_name").text(name)
	})
})
</script>
</head>
<body>
  <table class="table">
    <c:forEach var="vo" items="${list }">
      <tr data-poster="https://www.menupan.com${vo.poster }" data-no="${vo.fno }" data-name="${vo.name }" class="trs">
       <td class="text-center">
        <img src="https://www.menupan.com${vo.poster }" style="width: 30px;height: 30px">
       </td>
       <td>${vo.name }</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>