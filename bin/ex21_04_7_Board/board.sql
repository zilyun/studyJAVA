--------------------------------------------------------
-- board 계정 생성 
--------------------------------------------------------
	create user board identified by 1234;
	grant connect, resource, dba to board;
	
	-- 테이블 삭제 (휴지통에 저장)
	-- drop table 테이블명;
	-- 테이블 완전 삭제하기 (휴지통에 저장되지 않음)
	-- drop table 테이블명 purge;
	-- 휴지통 비우기
	-- purge recyclebin;
	
--------------------------------------------------------
--  DDL for Table BOARD
--------------------------------------------------------

  CREATE TABLE "BOARD"."BOARD" 
   (	"BOARD_NUM" NUMBER(5,0), 
	"BOARD_NAME" VARCHAR2(30 BYTE), 
	"BOARD_PASS" VARCHAR2(30 BYTE), 
	"BOARD_SUBJECT" VARCHAR2(300 BYTE), 
	"BOARD_CONTENT" VARCHAR2(4000 BYTE), 
	"BOARD_FILE" VARCHAR2(50 BYTE), 
	"BOARD_ORIGINAL" VARCHAR2(50 BYTE), 
	"BOARD_RE_REF" NUMBER(5,0), 
	"BOARD_RE_LEV" NUMBER(5,0), 
	"BOARD_RE_SEQ" NUMBER(5,0), 
	"BOARD_READCOUNT" NUMBER(5,0), 
	"BOARD_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C007094
--------------------------------------------------------

  CREATE UNIQUE INDEX "BOARD"."SYS_C007094" ON "BOARD"."BOARD" ("BOARD_NUM") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table BOARD
--------------------------------------------------------

  ALTER TABLE "BOARD"."BOARD" ADD PRIMARY KEY ("BOARD_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  
--------------------------------------------------------
--  DDL for Sequence BOARD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "BOARD"."BOARD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

   
--------------------------------------------------------
--  게시판 조회
--------------------------------------------------------
select * from board;  

--------------------------------------------------------
--  페이징 처리
--------------------------------------------------------
 select * 
 from 
 	(select rownum rnum, b.* 
  	 from (select * 
 		   from board 
 	  	   order by BOARD_RE_REF desc, BOARD_RE_SEQ asc) b 
  	 where rownum <= 10)
 where rnum between 1 and 10
 
 
delete from board where BOARD_NUM = 8

-- 글삭제 + 댓글 삭제 
select BOARD_NUM 
from board
where BOARD_RE_REF = 10
and BOARD_RE_LEV >=  1
and BOARD_RE_SEQ between 1 and 
NVL((select min(BOARD_RE_SEQ) - 1 -- 같은 레벨이 존재할 경우
     from board 
     where BOARD_RE_REF = 10 
     and BOARD_RE_LEV = 1 
     and BOARD_RE_SEQ > 1), 
    (select max(BOARD_RE_SEQ) -- 같은 레벨이 존재하지 않는 경우
     from board
     where BOARD_RE_REF = 10))
--->
delete from board 
where BOARD_NUM in (select BOARD_NUM  
				    from board 
				    where BOARD_RE_REF = ? 
				    and BOARD_RE_LEV  >= ? 
				    and BOARD_RE_SEQ between ? and 
				    NVL((select min(BOARD_RE_SEQ) - 1 
				  	     from board  
				  	     where BOARD_RE_REF = ?
				  	     and BOARD_RE_LEV   = ?
				         and BOARD_RE_SEQ   > ?),
				  	    (select max(BOARD_RE_SEQ) 
				         from board 
				  	     where BOARD_RE_REF = ?)))    
      