<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      JSP => Java Server Page : 서버에서 실행하는 자바 파일
      ====
      ==========>  교재 생략 : 자바 , 데이터베이스 , 제공하는 예제
                   1/3      =================
      1. 지시자 
         <%@ page %>
           contentType : 브라우저에 전송 => 파일형식 
               = text/html;charset=UTF-8
               = text/xml;charset=UTF-8
               = text/plain;charset=UTF-8
               ====> html,xml,json
               ====> 자바 변경 ==> response.setContentType()
            ***errorPage : error가 날 경우에 지정된 파일로 이동 
            isErrorPage: true/false => exception 객체 사용시 
            ***import : 외부클래스를 읽어 올때 => 여러번 사용
               => JSP에서만 => MVC에서는 사용하지 않는다 
            buffer : 8kb ==> 증가 2배 권장 
                                 ====== 
         <%@ taglib %> => JSTL / EL
      2. 스크립트 
         <% %> : 일반 자바 (main안에 소스 코딩) 
                 => 제공하는 태그를 이용해서 사용 
         <%= %> : 데이터 출력 
                 => ${}
                 => 가급적이면 <% %>를 제거
         JSP <=====> JSP
                      |
                     DAO  ==> Model 1방식 => 재사용이 없다 
                              ========== 2002~2004
         ***JSP ==== Controller === Model === DAO
                                ======= 자바(재사용성)
                              Model 2방식 => 현재 (98%)
         ===> 단점 : Controller에 집중 
                    ========== 분할 => ***Doamin방식 ==> MSA
                    ========== 대기업 
         *** 
                    
      3. 내장 객체 (MVC에서도 사용)
         request / response / **session / application
         out / pageContext 
      4. JSP액션 태그 
         <jsp:useBean> <jsp:setProperty> <jsp:include>
      ================== 기본 과정 
      5. EL 
      6. JSTL
      7. MVC
      ================== 중급 과정 
      8. XML 
      9. 어노테이션 
      ================== 고급 과정 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>