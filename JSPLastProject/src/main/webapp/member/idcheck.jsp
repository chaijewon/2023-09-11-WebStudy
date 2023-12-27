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
  width: 300px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	
})
</script>
</head>
<body>
  <div class="container">
   <div class="row">
    <table class="table">
     <tr>
       <td>입력:<input type="text" size=15 class="input-sm" id="id">
         <input type="button" value="아이디중복체크" 
          class="btn btn-sm btn-success" id="idcheckBtn">
       </td>
     </tr>
     <tr>
       <td><span id="result"></span></td>
     </tr>
     <tr>
       <td class="text-center"><input type="button" class="btn btn-sm btn-danger" value="확인" style="display:none"></td>
     </tr>
    </table>
   </div>
  </div>
</body>
</html>