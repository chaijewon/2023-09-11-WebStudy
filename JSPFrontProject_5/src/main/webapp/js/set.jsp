<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let index=1;
window.onload=function(){
	setInterval('change()', 500);
}
function change()
{
	index++;
	if(index>7)
		index=1
	
	let img=document.querySelector("img")
	img.src='../image/m'+index+".jpg";
}
</script>
</head>
<body>
  <img src="../image/m1.jpg" width=200 height="250">
</body>
</html>