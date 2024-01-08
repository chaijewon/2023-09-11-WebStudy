package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.ReserveDAO;

import java.util.*;
import com.sist.vo.*;
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
  
}
