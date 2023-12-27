package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dbcp.*;
public class MemberDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateDBCPConnection dbconn=
		     new CreateDBCPConnection();
   private static MemberDAO dao;
   
   public static MemberDAO newInstance()
   {
	   if(dao==null)
		   dao=new MemberDAO();
	   return dao;
   }
   // ID중복체크 
   public int memberIdCheck(String id)
   {
	   int count=0;
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT COUNT(*) FROM project_member "
				     +"WHERE id=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   count=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return count;
   }
   /*try
   {
	   conn=dbconn.getConnection();
   }catch(Exception ex)
   {
	   ex.printStackTrace();
   }
   finally
   {
	   dbconn.disConnection(conn, ps);
   }*/
   public List<ZipcodeVO> postfind(String dong)
   {
	   List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT zipcode,sido,gugun,"
				     +"dong,NVL(bunji,' ') "
				     +"FROM zipcode "
				     +"WHERE dong LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, dong);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   ZipcodeVO vo=new ZipcodeVO();
			   vo.setZipcode(rs.getString(1));
			   vo.setSido(rs.getString(2));
			   vo.setGugun(rs.getString(3));
			   vo.setDong(rs.getString(4));
			   vo.setBunji(rs.getString(5));
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return list;
   }
   public int postfindCount(String dong)
   {
	   int count=0;
	   try
	   {
		   conn=dbconn.getConnection();
		   String sql="SELECT COUNT(*) "
				     +"FROM zipcode "
				     +"WHERE dong LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, dong);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   count=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbconn.disConnection(conn, ps);
	   }
	   return count;
   }
}
