<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let keyword=document.querySelector("#keyword");
	let table=document.querySelector("#user-table");
	keyword.addEventListener('keyup',function(){
		let v=keyword.value;
		let trs=document.querySelectorAll('#user-table > tbody > tr')
		for(let t of trs)
		{
			t.style.display='none'
		}
	    let data=document.querySelectorAll('#user-table > tbody > tr > td:nth-child(2n):contains("'+v+'")')
	    for(let t of data)
	    {
	    	t.style.display=''
	    }
	})
}
</script>
</head>
<body>
  <table width="500">
    <tr>
      <td>
       <input type=text id="keyword" size=20>
      </td>
    </tr>
  </table>
  <table width=500 border=1 id="user-table">
  <thead>
   <tr>
     <th>이름</th>
     <th>주소</th>
   </tr>
   </thead>
   <tdody>
   <tr>
     <td>홍길동</td>
     <td>서울</td>
   </tr>
   <tr>
     <td>박문수</td>
     <td>부산</td>
   </tr>
   <tr>
     <td>홍길수</td>
     <td>경기</td>
   </tr>
   <tr>
     <td>춘향이</td>
     <td>인천</td>
   </tr>
   <tr>
     <td>심청이</td>
     <td>인천</td>
   </tr>
   <tr>
     <td>이순신</td>
     <td>강원</td>
   </tr>
   <tr>
     <td>강감찬</td>
     <td>제주</td>
   </tr>
   <tr>
     <td>을지문덕</td>
     <td>경기</td>
   </tr>
   <tr>
     <td>소서노</td>
     <td>서울</td>
   </tr>
   <tr>
     <td>이산</td>
     <td>부산</td>
   </tr>
   </tdody>
  </table>
</body>
</html>