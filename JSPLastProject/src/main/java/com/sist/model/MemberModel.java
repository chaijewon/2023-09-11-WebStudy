package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.ZipcodeVO;

public class MemberModel {
  @RequestMapping("member/join.do")
  public String member_join(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../member/join.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("member/idcheck.do")
  public String member_idcheck(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  return "../member/idcheck.jsp";
  }
  
  @RequestMapping("member/idcheck_ok.do")
  public void member_idcheck_ok(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String id=request.getParameter("id");
	  MemberDAO dao=MemberDAO.newInstance();
	  int count=dao.memberIdCheck(id);
	  System.out.println("ID check:"+count);
	  try
	  {
		  // Ajax로 값을 전송 
		  PrintWriter out=response.getWriter();
		  out.write(String.valueOf(count));
	  }catch(Exception ex){}
  }
  
  @RequestMapping("member/postfind.do")
  public String member_postfind(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  return "../member/postfind.jsp";
  }
  
  @RequestMapping("member/postfind_ok.do")
  public void member_postfind_ok(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String dong=request.getParameter("dong");
	  System.out.println("dong:"+dong);
	  MemberDAO dao=MemberDAO.newInstance();
	  int count=dao.postfindCount(dong);
	  // JSON변경 
	  // VO => {} ==> JSONObject
	  // List => [{},{}...] ==> JSONArray
	  JSONArray arr=new JSONArray();//[]
	  //[{count:0},]
	  if(count==0)
	  {
		  JSONObject obj=new JSONObject();
		  obj.put("count", count);
		  arr.add(obj);
	  }
	  else
	  {
		  int i=0;
		  List<ZipcodeVO> list=dao.postfind(dong);
		  for(ZipcodeVO vo:list)
		  {
			  JSONObject obj=new JSONObject();
			  // {zipcode:111,address:'...',count:2},{}
			  obj.put("zipcode", vo.getZipcode());
			  obj.put("address", vo.getAddress());
			  if(i==0)
			  {
				  obj.put("count", count);
			  }
			  arr.add(obj);
			  i++;
		  }
	  }
	  System.out.println(arr.toJSONString());
	  try
	  {
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  PrintWriter out=response.getWriter();
		  out.write(arr.toJSONString());
	  }catch(Exception ex) {}
	  
	  
  }
}
