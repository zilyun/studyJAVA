-- board 내용 삭제
-- delete from board;
-- board 백업
--create table boardBackup
--as
--select * from board;
desc board;
select * from board;
select count(*) from board where BOARD_NUM = 1 AND BOARD_PASS = '1234';
select BOARD_NUM 
 from board
 where BOARD_RE_REF = 10
 and BOARD_RE_LEV >=  1
 and BOARD_RE_SEQ between 1 and 
 NVL((select min(BOARD_RE_SEQ) - 1 
      from board 
      where BOARD_RE_REF = 10 
      and BOARD_RE_LEV = 1 
      and BOARD_RE_SEQ > 1), 
     (select max(BOARD_RE_SEQ)
      from board
      where BOARD_RE_REF = 10));

drop table board;
create table board
as
select * from boardBackup;