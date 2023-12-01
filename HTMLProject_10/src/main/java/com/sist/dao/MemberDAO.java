package com.sist.dao;
// 회원 , 로그인
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
public class MemberDAO {
	   private Connection conn;
	   private PreparedStatement ps;
	   private static MemberDAO dao;
	   public void getConnection()
	   {
		   try
		   {
			   Context init=new InitialContext();
			   Context c=(Context)init.lookup("java://comp/env");
			   DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			   conn=ds.getConnection();
		   }catch(Exception ex) {}
	   }
	   public void disConnection()
	   {
		   try
		   {
			   if(ps!=null) ps.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   public static MemberDAO newInstance()
	   {
		   if(dao==null)
			   dao=new MemberDAO();
		   return dao;
	   }
	   
	   // 기능 처리 
	   public MemberVO memberLogin(String id,String pwd)
	   {
		   MemberVO vo=new MemberVO();
		   try
		   {
			   //1. 주소값 얻기 
			   getConnection();
			   //2. SQL문장 전송 
			   String sql="SELECT COUNT(*) FROM member_servlet "
					     +"WHERE id=?";
			   /*
			    *   로그인 처리 
			    *   1. id가 존재여부 확인 => COUNT()
			    *   2. id(X) => 종료 => NOID
			    *      id(O)
			    *      3. PWD를 확인 
			    *         pwd(X) => 종료 => NOPWD
			    *         pwd(O) => 종료 => OK => 화면 이동 (MainServlet)
			    *         ======================
			    *          개인 정보중에 프로그램 종료시까지 유지해야
			    *          되는 데이터를 세션에 저장 
			    *                     === 모든 서블릿/JSP 필요시마다 사용이 가능 
			    *                     === 전역변수 
			    *          세션 / 쿠키 ==> 저장공간
			    *                |브라우저에 저장 (보안 취약) 
			    *          |서버에 저장 (보안)
			    *           1) 저장 
			    *           2) 수정 
			    *           3) 일부 삭제 
			    *           4) 모든 정보 삭제
			    *           5) 저장기간 설정 
			    */
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, id);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   int count=rs.getInt(1);
			   rs.close();
			   if(count==0) // ID가 없는 상태 
			   {
				   vo.setMsg("NOID");
			   }
			   else // ID가 있는 상태 
			   {
				   sql="SELECT pwd,name FROM member_servlet "
					  +"WHERE id=?";
				   ps=conn.prepareStatement(sql);
				   ps.setString(1, id);
				   // 3,4 => 10
				   rs=ps.executeQuery();
				   rs.next();
				   String db_pwd=rs.getString(1);
				   String name=rs.getString(2);
				   rs.close();
				   if(db_pwd.equals(pwd))
				   {
					   vo.setMsg("OK");
					   vo.setName(name);
				   }
				   else
				   {
					   vo.setMsg("NOPWD");
				   }
			   }
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   // 반환 
			   disConnection();
		   }
		   return vo;
	   }
}
