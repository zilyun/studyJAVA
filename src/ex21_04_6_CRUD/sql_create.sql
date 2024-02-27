-- 1. hakjum 테이블을 생성합니다.
create table hakjum (
 LOWSCORE     NUMBER(3),
 HISCORE      NUMBER(5,2),
 GRADE        VARCHAR2(3),  -- 기본키
 CONSTRAINT hakjum_pk PRIMARY KEY(GRADE)
 );
 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (0, 59.99, 'F'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (60, 64.99, 'D0'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (65, 69.99, 'D+'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (70, 74.99, 'C0'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (75, 79.99, 'C+'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (80, 84.99, 'B0'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (85, 89.99, 'B+'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (90, 94.99, 'A0'); 
insert into HAKJUM(LOWSCORE, HISCORE, GRADE) values (95, 100, 'A+'); 
commit

delete from HAKJUM;
select * from hakjum; -- 데이터를 확인합니다.

-- 2. student 테이블을 생성합니다.
create table student(
 NO               NUMBER(3), -- 기본키
 NAME             VARCHAR2(21) not null, -- not null
 KOR              NUMBER(3) not null,  -- 0~100 사이의 값, not null
 MATH             NUMBER(3) not null, -- 0~100 사이의 값,not null
 ENG              NUMBER(3) not null, -- 0~100 사이의 값,not null
 TOT              NUMBER(3),
 AVG              NUMBER(5,2),
 GRADE            VARCHAR2(3),
 CONSTRAINT student_pk PRIMARY KEY(NO),
 CONSTRAINT student_kor_check check(KOR between 0 and 100),
 CONSTRAINT student_math_check check(MATH between 0 and 100),
 CONSTRAINT student_eng_check check(ENG between 0 and 100)
 );
 
 -- 3. 참조키를 추가해줍니다.
 ALTER TABLE student 
 ADD CONSTRAINT student_hakjum_fk 
 FOREIGN KEY(GRADE) REFERENCES hakjum(GRADE);
 
 -- 4. 시퀀스를 생성합니다.
 DROP SEQUENCE student_seq
 CREATE SEQUENCE student_seq
 		INCREMENT BY 1
 		START WITH 1
 		MINVALUE 1
 		MAXVALUE 999
 		NOCYCLE;
 		-- NOCACHE
 		-- NOORDER
 
 insert into student (NO, NAME, KOR, MATH, ENG, TOT, AVG, GRADE) 
 values(student_seq.NEXTVAL, 'zzz', 50, 40, 30, 120, 40, 
 (select GRADE from hakjum where 40 between lowscore and hiscore))	
 
 -- ; <- 다중행인 경우, 전체를 한 줄로 할 경우 ;를 뺄 것.

UPDATE student SET NAME = 'zihu4', KOR = 70, 
				   MATH = 70, ENG = 70, TOT = 210, AVG = 70, 
				   GRADE = (select GRADE from hakjum
				   			where 70 between lowscore and hiscore)
WHERE no = 4;

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 		