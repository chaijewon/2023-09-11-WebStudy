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

-- ��� �б�
/*
    IN  = ����Ŭ�� ���� ���� 
    OUT = ���� ������� �޴´� 
          NUMBER / VARCHAR2 / CURSOR (List)
    ���ν����� ���� : �������� ���� 
          �����͸� ���� ��� = �Ű������� ���� (������:out)
    ���ó: �ݺ����� ���� (�ߺ� ����)     
    ����) 
      CREATE [OR REPLACE] PROCEDURE pro_name(
         �Ű����� 
      )
      IS
        �������� ���� 
      BEGIN
        �����ϴ� SQL���� 
      END;
       /
      
*/
CREATE OR REPLACE PROCEDURE replyListData(
   pNo NUMBER,
   pType NUMBER,
   pResult OUT SYS_REFCURSOR
)
IS
  -- �������� 
BEGIN
  -- SQL������ ����
  OPEN pResult FOR
    SELECT no,type,cno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') FROM reply_all
    WHERE type=pType AND cno=pNo
    ORDER BY no DESC;
END;
/
-- ��� �߰� 
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
-- ��� ���� 
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
-- ��� ����
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
