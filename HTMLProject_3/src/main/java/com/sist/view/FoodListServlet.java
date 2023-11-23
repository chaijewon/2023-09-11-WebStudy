package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
@WebServlet("/FoodListServlet")
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 브라우저를 통해서 요청시에 처리해서 브라우저로 HML을 전송하는 메소드
		// 톰캣에 의해 자동 호출 
		// 매소드 영역 => JSP다 => service
		// JSP => 실행 => class변경 => 컴파일해서 실행 
		// 전송타입 => html,xml,json
		response.setContentType("text/html;charset=UTF-8");
		//                       text/xml , text/plain
		// HTML전송 
		PrintWriter out=response.getWriter();
		//              --------------------- 접속한 클라이언트 브라우저
		FoodDAO dao=FoodDAO.newInstance();
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.foodTotalPage();
		List<FoodVO> list=dao.foodListData(curpage);
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=food/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>맛집 1000Top</h1>");
		out.println("<table class=table_content width=800>");
		out.println("<tr>");
		out.println("<th width=10%>순위</th>");
		out.println("<th width=15%></th>");
		out.println("<th width=20%>업체명</th>");
		out.println("<th width=15%>업종</th>");
		out.println("<th width=40%>주소</th>");
		out.println("</tr>");
		for(FoodVO vo:list)
		{
			out.println("<tr class=dataTr>");
			out.println("<td width=10%>"+vo.getFno()+"</td>");
			out.println("<td width=15%><img src="+vo.getPoster()+" width=35 height=35></td>");
			out.println("<td width=20%><a href=food/main.jsp?fno="+vo.getFno()+">"+vo.getName()+"</a></td>");
			out.println("<td width=15%>"+vo.getType()+"</td>");
			out.println("<td width=40%>"+vo.getAddress()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<table class=table_content width=800>");
		out.println("<tr>");
		out.println("<td align=center>");
		out.println("<a href=\"FoodListServlet?page="+(curpage>1?curpage-1:curpage)+"\">이전</a>");
		out.println(curpage+" page / ");
		out.println(totalpage+" pages");
		out.println("<a href=\"FoodListServlet?page="+(curpage<totalpage?curpage+1:curpage)+"\">다음</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}






