<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 20px; 
}
.row{
  margin: 0px auto;
  width: 450px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
     <table class="table">
      <tr>
       <td>
       입력:<input type="text" size=20 class="input-sm" id="dong">
       <input type="button" value="우편번호 검색" 
         class="btn btn-sm btn-info" id="postBtn">
       </td>
      </tr>
     </table>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
     <table class="table">
       
     </table>
    </div>
  </div>
</body>
</html>