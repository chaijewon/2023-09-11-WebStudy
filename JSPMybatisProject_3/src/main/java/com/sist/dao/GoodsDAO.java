package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class GoodsDAO {
  /*
   *   <select id="goodsListData" resultType="GoodsVO" parameterType="hashmap">
	    SELECT no,goods_poster,goods_name,goods_price,num
	    FROM (SELECT no,goods_poster,goods_name,goods_price,rownum as num
	    FROM (SELECT no,goods_poster,goods_name,goods_price
	    FROM ${tab_name} ORDER BY no ASC))
	    WHERE num BETWEEN #{start} AND #{end}
	   </select>
   */
	private static SqlSessionFactory ssf=
			      CommonsDataBase.getSsf();
	/*
	 *public List<BoardVO> boardListData(int page)
      {
	   List<BoardVO> list=
			   new ArrayList<BoardVO>();
	   try
	   {
		   conn=dbconn.getConnection();
		   ssf.openSession()
		   String sql=SELECT no,goods_poster,goods_name,goods_price,num
	                 FROM (SELECT no,goods_poster,goods_name,goods_price,rownum as num
	                 FROM (SELECT no,goods_poster,goods_name,goods_price
	                 FROM ${tab_name} ORDER BY no ASC))
	                 WHERE num BETWEEN #{start} AND #{end}
		   ps=conn.prepareStatement(sql);
		   int rowSize=10;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setInt(1, start); ==> parameterType
		   ps.setInt(2, end);
		   
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   BoardVO vo=new BoardVO();
			   vo.setNo(rs.getInt(1));
			   vo.setSubject(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setDbday(rs.getString(4));
			   vo.setHit(rs.getInt(5));
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
	 */
	// 기능 설정 
	public static List<GoodsVO> goodsListData(Map map)
	{
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("goodsListData",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	/*
	 *  <select id="goodsTotalPage" resultType="int" parameterType="hashmap">
		    SELECT CEIL(COUNT(*)/20.0)
		    FROM ${tab_name}
		  </select>
	 */
	public static int goodsTotalPage(Map map)
	{
		int total=0;
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			total=session.selectOne("goodsTotalPage",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	/*
	 *   <select id="goodsDetailData" resultType="GoodsVO"
		   parameterType="hashmap">
		   SELECT *
		   FROM #{tab_name}
		   WHERE no=#{no}
		  </select>
		  
		  자동 구현 
		  =======
		  return형 = resultType
		  매개변수 = parameterType
		  메소드명 = id
		  
		  1. xml 
		  2. annotation : Spring
		  3. 실무 = XML + Annotation 
	 */
	public static GoodsVO goodsDetailData(Map map)
	{
		GoodsVO vo=new GoodsVO();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			vo=session.selectOne("goodsDetailData",map);
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	/*
	 *  <select id="cartIsGoodsCount" resultType="int" parameterType="CartVO">
    SELECT COUNT(*)
    FROM cart
    WHERE goods_no=#{goods_no} AND type=#{type} AND issale!=1
    AND id=#{id}
  </select>
  <update id="cartGoodsUpdate" parameterType="CartVO">
    UPDATE cart SET
    amount=amount+#{amount}
    WHERE goods_no=#{goods_no} AND type=#{type} AND id=#{id} 
  </update>
  <!-- 
    cart_no NUMBER,
   goods_no NUMBER,
   TYPE NUMBER,
   amount NUMBER,
   price NUMBER,
   id VARCHAR2(20),
   ischeck NUMBER DEFAULT 0,
   issale NUMBER DEFAULT 0,
   regdate DATE DEFAULT SYSDATE
   -->
  <insert id="cartGoodsInsert" parameterType="CartVO">
    INSERT INTO cart VALUES(
     (SELECT NVL(MAX(cart_no)+1,1) FROM cart),
     #{goods_no},#{type},#{amount},#{price},#{id},
     0,0,SYSDATE
    )
  </insert>
	 */
	public static void cartInsert(CartVO vo)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			// autocommit
			int count=session.selectOne("cartIsGoodsCount",vo);
			// 구매가 안된 상품이 있는지 확인 
			if(count!=0)
			{
				// 존재한다면 => 수량만 증가 
				session.update("cartGoodsUpdate",vo);
			}
			else
			{
				// 없다면 => 추가 
				session.insert("cartGoodsInsert",vo);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	/*
  <select id="mypageGoodsCartData" resultMap="cartMap" parameterType="hashmap">
    SELECT cart_no,goods_no,amount,regdate,issale,ischeck,price,
     (SELECT goods_poster FROM ${tab_name} WHERE no=cart.goods_no) as goods_poster,
     (SELECT goods_name FROM ${tab_name} WHERE no=cart.goods_no) as goods_name,
     (SELECT goods_price FROM ${tab_name} WHERE no=cart.goods_no) as goods_price
    FROM cart
    WHERE id=#{id} AND issale!=1
    ORDER BY cart_no DESC
  </select>
	 */
	public static List<CartVO> mypageGoodsCartData(Map map)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mypageGoodsCartData",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void cartBuyInsert(CartVO vo)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.insert("cartBuyInsert",vo);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public static List<CartVO> mypageGoodsBuyData(Map map)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mypageGoodsBuyData",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void cartDelete(int cart_no)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.delete("cartDelete",cart_no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	/*
	 *  <update id="goodsCartBuy" parameterType="int">
		   UPDATE cart SET
		   issale=1
		   WHERE cart_no=#{cart_no}
		  </update>
	 */
	public static void goodsCartBuy(int cart_no)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.update("goodsCartBuy",cart_no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	
}
