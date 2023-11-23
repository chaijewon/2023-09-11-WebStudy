<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9965c727d3306713c47391be682e4be9&libraries=services"></script>
<script type="text/javascript">
$(function(){
	$('#like').click(function(){
		//alert("click");
		let str=$('#like').val();
		console.log(str)
		str=str.substring(str.indexOf('(')+1,str.lastIndexOf(')'));
		$('#like').val("좋아요("+(Number(str)+1)+")")
	});
});
</script>
</head>
<body>
        <%
        String fno=request.getParameter("fno");
		FoodDAO dao=FoodDAO.newInstance();
		FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=table.css>");
		out.println("<script type=\"text/javascript\" src=\"//dapi.kakao.com/v2/maps/sdk.js?appkey=9965c727d3306713c47391be682e4be9&libraries=services\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<table class=table_content width=800>");
		out.println("<tr>");
		out.println("<td width=30% align=center rowspan=7>");
		out.println("<img src="+vo.getPoster()+" style=\"width:240px;height:420px\">");
		out.println("</td>");
		out.println("<td width=70% colspan=2><h3>"+vo.getName()+"</h3></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th width=15% >업종");
		out.println("</th>");
		out.println("<td width=55% >"+vo.getType());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% >전화번호");
		out.println("</th>");
		out.println("<td width=55% >"+vo.getPhone());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% >주소");
		out.println("</th>");
		out.println("<td width=55% >"+vo.getAddress());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% >평점");
		out.println("</th>");
		out.println("<td width=55% >"+vo.getScore());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% >테마");
		out.println("</th>");
		out.println("<td width=55% >");
		out.println("<ul>");
		StringTokenizer st=new StringTokenizer(vo.getTheme(),",");
		while(st.hasMoreTokens())
		{
			out.println("<li>"+st.nextToken()+"</li>");
		}
		out.println("</ul>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% >좌석");
		out.println("</th>");
		out.println("<td width=55% >"+vo.getSeat());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=3>"+vo.getContent());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=3 align=right>");
		out.println("<input type=button value=좋아요(10) id=like>"); 
		out.println("<input type=button value=찜하기>"); 
		out.println("<input type=button value=예약하기>"); 
		out.println("<input type=button value=목록 onclick=\"javascript:history.back()\">");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
	  %>
	  <div style="height:30px"></div>
	   <table class="table_content" width=800>
	   <tr>
	    <td>
        <div id="map" style="width:100%;height:350px;"></div>
        <script>
		var mapContainer = document.getElementById('map'),
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), 
		        level: 3 
		    };  
		
		   
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		
		var geocoder = new kakao.maps.services.Geocoder();
		
		
		geocoder.addressSearch('<%=vo.getAddress()%>', function(result, status) {
		
		    
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		       
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;"><%=vo.getName()%></div>'
		        });
		        infowindow.open(map, marker);
		
		       
		        map.setCenter(coords);
		    } 
		});    
		</script>
		</td>
		</tr>
	 </table>
</body>
</html>