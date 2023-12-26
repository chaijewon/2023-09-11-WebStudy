let httpRequest=null;
// 브라우저 내장 객체를 생성하는 함수 
// XMLHttpRequest => 서버에 요청 전송 => 서버로부터 결과값을 받는 클래스
function getXMLHttpRequest()
{
	// window.ActiveObject => IE
	if(window.XMLHttpRequest) // => 기타 브라우저 (크롬, FF)
	{
		return new XMLHttpRequest();
	}
	else
	{
		return null;
	}
}
// 서버에 요청 => find.jsp?fd=aaa
/*
      $.ajax({
		  type: post
		  url: result.jsp => result.jsp?no=1
		  data: {no:1}
		  success:function(res){
			  res=>실행 결과물 => html,json,일반문자
			  => resText , resXML 
		  }
	  })
*/
function sendRequest(url,params,callback,method)
{
	// ?name=aaa&id=aaa
    // XMLHttpRequest얻기 
    httpRequest=getXMLHttpRequest();
    // 전송 방식 처리 <form method="past">
    let httpMethod=method?method:'GET' // null => GET
    if(httpMethod!='GET' && httpMethod!='POST')
    {
		httpMethod='GET'
	}
	// params => null이거나 ''일 경우
	// GET => url?   => send()
	let httpParams=(params==null||params=='')?null:params
	let httpUrl=url;
	
	// GET 방식 
	if(httpMethod=='GET' && httpParams!=null)
	{
		httpUrl=httpUrl+"?"+httpParams
		// a.jsp?no=1
	}
	
	// 연결 
	httpRequest.open(httpMethod,httpUrl,true)
	// true => 비동기 , false=동기 
	httpRequest.setRequestHeader('Content-Type',
	      "application/x-www-form-urlencoded")
    // 콜백 함수 지정 => 자동호출 => 실행 결과값을 받는다 
    httpRequest.onreadystatechange=callback
    // 데이터를 전송 
    httpRequest.send(httpMethod=='POST'?httpParams:null)
}






