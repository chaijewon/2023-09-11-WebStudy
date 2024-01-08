package com.sist.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class ReserveDAO {
  private Connection conn;
  private PreparedStatement ps;
  private static SqlSessionFactory ssf=
		        CommonsDataBase.getSsf();
  private static ReserveDAO dao;
  
  /*public static ReserveDAO newInstance()
  {
	  if(dao==null)
		  dao=new ReserveDAO();
	  return dao;
  }*/
  public static List<FoodVO> reserveFoodListData(int no)
  {
	  SqlSession session=ssf.openSession();
	  Map map=new HashMap();
	  map.put("no", no);
	  List<FoodVO> list=
			  session.selectList("reserveFoodListData",map);
	  session.close();
	  return list;
  }
}
