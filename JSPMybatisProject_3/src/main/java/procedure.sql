--SELECT * FROM tab;
CREATE TABLE reply_all(
   no NUMBER,
   type NUMBER,
   cno NUMBER,
   id VARCHAR2(20),
   name VARCHAR2(51) CONSTRAINT ra_name_nn NOT NULL,
   msg CLOB CONSTRAINT ra_msg_nn NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   CONSTRAINT ra_no_pk PRIMARY KEY(no),
   CONSTRAINT ra_id_fk FOREIGN KEY(id)
   REFERENCES project_member(id)
);
CREATE SEQUENCE ra_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
    
DESC reply_all;

-- 댓글 읽기
/*
    IN  = 오라클에 값을 전송 
    OUT = 실행 결과값을 받는다 
          NUMBER / VARCHAR2 / CURSOR (List)
    프로시저의 단점 : 리턴형이 없다 
          데이터를 받을 경우 = 매개변수로 선언 (포인터:out)
    사용처: 반복적인 구간 (중복 제거)     
    형식) 
      CREATE [OR REPLACE] PROCEDURE pro_name(
         매개변수 
      )
      IS
        지역변수 설정 
      BEGIN
        수행하는 SQL문장 
      END;
       /
      
*/
CREATE OR REPLACE PROCEDURE replyListData(
   pNo NUMBER,
   pType NUMBER,
   pResult OUT SYS_REFCURSOR
)
IS
  -- 지역변수 
BEGIN
  -- SQL문장을 실행
  OPEN pResult FOR
    SELECT no,type,cno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') FROM reply_all
    WHERE type=pType AND cno=pNo
    ORDER BY no DESC;
END;
/
-- 댓글 추가 
CREATE OR REPLACE PROCEDURE replyInsert(
   pType reply_all.type%TYPE,
   pCno reply_all.cno%TYPE,
   pId reply_all.id%TYPE,
   pName reply_all.name%TYPE,
   pMsg reply_all.msg%TYPE
)
IS
BEGIN
  INSERT INTO reply_all VALUES(
    ra_no_seq.nextval,pType,pCno,pId,pName,pMsg,SYSDATE
  );
  COMMIT;
END;
/
-- 댓글 수정 
CREATE OR REPLACE PROCEDURE replyUpdate(
   pNo reply_all.no%TYPE,
   pMsg reply_all.msg%TYPE
)
IS
BEGIN
   UPDATE reply_all SET
   msg=pMsg
   WHERE no=pNo;
   COMMIT;
END;
/
-- 댓글 삭제
CREATE OR REPLACE PROCEDURE replyDelete(
   pNo reply_all.no%TYPE
)
IS
BEGIN
  DELETE FROM reply_all
  WHERE no=pNo;
  COMMIT;
END;
/

SHOW error;
