package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.dbcp.*;
import com.sist.vo.MemberVO;
public class MemberDAO {
   private Connection conn;
   private PreparedStatement ps; 
   private CreateDBCPConnection dbconn=
		      new CreateDBCPConnection();
   private static MemberDAO dao;
   
   //1. 로그인 
   public MemberVO isLogin(String id,String pwd)
   {
	   //String에서 변경 => 암호화/복호화
	   MemberVO vo=new MemberVO();
	   try
	   {
		   conn=dbconn.getConnection();
		   // dbconn.getConnection() => this.conn은 null
		   // id가 존재여부 확인 
		   String sql="SELECT COUNT(*) FROM jspMember "
				     +"WHERE id=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);// 'hong'
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   int count=rs.getInt(1);
		   rs.close();
		   // id => pwd확인 
		   if(count==0) //ID가 없는 상태 
		   {
			   vo.setMsg("NOID");
		   }
		   else
		   {
			   // session에 저장할 데이터 
			   sql="SELECT id,name,admin,pwd "
			      +"FROM jspMember "
				  +"WHERE id=?";
			   // "WHERE id='"+id +"'"=> 오류 발생 
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, id);
			   rs=ps.executeQuery();
			   rs.next();
			   String db_id=rs.getString(1);
			   String name=rs.getString(2);
			   String admin=rs.getString(3);
			   String db_pwd=rs.getString(4);
			   System.out.println("id="+id+",db_id="+db_id);
			   System.out.println("pwd="+pwd+",db_pwd="+db_pwd);
			   
			   
			   if(db_pwd.equals(pwd))
               {
	               vo.setMsg("OK");// session저장 
	               vo.setId(db_id);
	               vo.setName(name);
	               vo.setAdmin(admin);
	               
               }
			   else
			   {
				   vo.setMsg("NOPWD");
			   }
			   rs.close();
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return vo;
   }
}
