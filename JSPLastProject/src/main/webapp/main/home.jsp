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
<div class="wrapper row3">
  <div id="slider" class="clear"> 
    <div class="flexslider basicslider">
      <ul class="slides">
        <c:forEach var="i" begin="1" end="5">
          <li><a href="#"><img class="radius-10" src="../images/demo/slides/${i }.jpg" style="width:978px;height:400px"></a></li>
        </c:forEach>
      </ul>
    </div>
    </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="#">오늘의 맛집</a></h6>
          <p>단대호수를 바라보고있는 전망으로 최고인 카페 커피볶는집카페마리스를 소개한다.
3층까지 모두다 카페이며 3층까지 테라스좌석이 마련되어서 나가서 풍경을보면 단대호수가 쫙 펼쳐져있다.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="#">오늘의 레시피</a></h6>
          <p>깻잎도 쪄먹을 수 있는거 아시나요? 깻잎찜에 두부 쌈장이면 밥 한그릇 뚝딱!</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">오늘의 서울 여행</a></h6>
          <p>덕수궁 안에는 한옥과 양옥의 건축 양식이 어우러진 건물 정관헌이 있다. 로마네스크 양식의 석조 기둥과 베란다가 있으며 기둥 상부에 우리의 전통 문양을 새겨넣었다. 고종이 연회를 열거나 차를 즐겨 마시던 장소로 전한다. 어떤 의미에서는 우리나라 최초의 카페라고 할 수 있다.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
    <h2 class="sectiontitle">인기 한식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${aList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="#">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 양식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${bList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="#">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 중식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${cList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="#">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 일식 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${dList }">
         <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width: 300px;height: 185px">
            <figcaption><a href="#">${vo.name}</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">최근 방문 맛집</h2>
    <div class="inline">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
        <img class="radius-10" src="../images/demo/100x100.gif" alt="">
    </div>
    <div class="clear"></div>
  </main>
</div>
</body>
</html>