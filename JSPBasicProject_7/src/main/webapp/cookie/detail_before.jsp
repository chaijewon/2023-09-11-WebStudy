<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String fno=request.getParameter("fno");
    //1. Cookie생성 
    Cookie cookie=new Cookie("food_"+fno,fno);
    //2. 저장 경로 설정 
    cookie.setPath("/");
    //3. 저장 기간 설정 
    cookie.setMaxAge(60*60*24);
    //4. 브라우저로 전송 
    response.addCookie(cookie);
    
    //5. 상세보기로 이동 
    response.sendRedirect("detail.jsp?fno="+fno);
    // sendRedirect는 GET방식 => 요청값 전송시 반드시 ?
%>