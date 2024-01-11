package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.vo.*;
import com.sist.dao.*;
public class ReplyModel {
  private String[] tab={"","location","nature","shop","food"};
  @RequestMapping("reply/insert.do")
  public String reply_insert(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");  
	  }catch(Exception ex) {}
	  String type=request.getParameter("type");
	  String cno=request.getParameter("cno");
	  String msg=request.getParameter("msg");
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  String name=(String)session.getAttribute("name");
	  
	  ReplyVO vo=new ReplyVO();
	  vo.setType(Integer.parseInt(type));
	  vo.setCno(Integer.parseInt(cno));
	  vo.setId(id);
	  vo.setName(name);
	  vo.setMsg(msg);
	  
	  ReplyDAO dao=ReplyDAO.newInstance();
	  dao.replyInsert(vo);
	  return "redirect:../seoul/"+tab[Integer.parseInt(type)]+"_detail.do?no="+cno;
  }
  @RequestMapping("reply/delete.do")
  public String reply_delete(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String type=request.getParameter("type");//구분자 
	  String cno=request.getParameter("cno");//명소번호
	  String no=request.getParameter("no");//댓글 번호
	  ReplyDAO dao=ReplyDAO.newInstance();
	  dao.replyDelete(Integer.parseInt(no));
	  return "redirect:../seoul/"+tab[Integer.parseInt(type)]+"_detail.do?no="+cno;
  }
  @RequestMapping("reply/update.do")
  public String reply_update(HttpServletRequest request,
		  HttpServletResponse respons)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String type=request.getParameter("type");//구분자 
	  String cno=request.getParameter("cno");//명소번호
	  String no=request.getParameter("no");//댓글 번호
	  String msg=request.getParameter("msg");
	  ReplyDAO dao=ReplyDAO.newInstance();
	  dao.replyUpdate(Integer.parseInt(no), msg);
	  return "redirect:../seoul/"+tab[Integer.parseInt(type)]+"_detail.do?no="+cno;
  }
}
