package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.ReserveDAO;

import java.util.*;
import com.sist.vo.*;
import java.text.*;
public class ReserveModel {
  @RequestMapping("reserve/reserve_main.do")
  public String reserve_main(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../reserve/reserve_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("reserve/food_list.do")
  public String food_list(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  List<FoodVO> list=ReserveDAO.reserveFoodListData(Integer.parseInt(no));
	  request.setAttribute("list", list);
	  return "../reserve/food_list.jsp";
  }
  @RequestMapping("reserve/food_date.do")
  public String food_date(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  // 예약 가능한 날 
	  String fno=request.getParameter("fno");
	  System.out.println("fno:"+fno);
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	  String today=sdf.format(new Date());
	  StringTokenizer st=new StringTokenizer(today,"-");
	  String sy=st.nextToken();
	  String sm=st.nextToken();
	  String sd=st.nextToken();
	  int year=Integer.parseInt(sy);
	  int month=Integer.parseInt(sm);
	  int day=Integer.parseInt(sd);
	  
	  // 요일 
	  String[] strWeek={"일","월","화","수","목","금","토"};
	  Calendar cal=Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DATE, 1);
	  
	  // 요일 구하기 
	  int week=cal.get(Calendar.DAY_OF_WEEK);
	  int lastday=cal.getActualMaximum(Calendar.DATE);
	  week=week-1;
	  
	  request.setAttribute("year", year);
	  request.setAttribute("month", month);
	  request.setAttribute("day", day);
	  request.setAttribute("week", week);
	  request.setAttribute("lastday", lastday);
	  request.setAttribute("strWeek", strWeek);
	  // 예약 가능한 날 => DB연동 
	  String rdays=ReserveDAO.reserveDays(Integer.parseInt(fno));
	  int[] rday=new int[32];
	  System.out.println("rdays:"+rdays);
	  st=new StringTokenizer(rdays,",");
	  // 3,5,6,8,12,15,20,24,27,28,29
	  while(st.hasMoreTokens())
	  {
		  int a=Integer.parseInt(st.nextToken());
		  if(a>=day)
		  {
			  rday[a]=1;
		  }
	  }
	  System.out.println(Arrays.toString(rday));
	  request.setAttribute("rday", rday);
	  return "../reserve/date.jsp";
  }
  
}
