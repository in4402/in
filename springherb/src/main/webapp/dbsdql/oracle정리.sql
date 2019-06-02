--1. insert 구문
insert into 테이블명 (컬럼명, ..)
values(값, ..);

--예)
insert into pd(no, pdname, price)
values(pd_seq.nextval, '컴퓨터', 1500000);

commit;  --작업완료(메모리에 저장된 내용을 실제 데이터파일에도 적용)
--rollback; (작업취소)

--2. update
update 테이블명 
set 컬럼명1=값1, 컬럼명2=값2
where 조건

--예)
update pd
set pdname='마우스', price=17000
where no=3;

commit;

--3. delete
delete from 테이블명
where 조건

--예)
delete from pd
where no=3;

commit;

--4. select
select * from 테이블명
where 조건

select * from pd
where no=5;

--정렬하기
select * from pd
where price>=150000
order by no desc;  --내림차순 정렬

select * from pd
where price>=150000
order by no;  --오름차순 정렬


-- like

--이름이 '김'으로 시작하는 사람 조회하기
select * from student
where name like '김%';
--이름이 '수'로 끝나는 사람 
select * from student
where name like '%수';
--이름에 '재'가 포함되는 사람
select * from student
where name like '%재%';

------------------------------------------------
--table 만들기

CREATE TABLE   테이블명
(
	컬럼1	데이터타입,
	컬럼2	데이터타입,
	…
)

create table board
(
	no		number		primary key,	--번호
	name	varchar2(20)	not null,		--이름	
	pwd         varchar2(20)    not null,         	--비밀번호
	title		varchar2(100)	 null,		--제목
	regdate 	 date		default  sysdate,	--작성일
	readcount number		default 0	 null,	--조회수
	content	 clob			null				--내용
);

--sequence 만들기
CREATE SEQUENCE “시퀀스명”
           INCREMENT BY  -- 시퀀스가 증가되는 단위
           START WITH  -- 시퀀스 생성이 시작되는 값
           NOCACHE;


create sequence board_seq
increment by 1
start with 1
nocache;

drop table board;  --테이블 제거
drop sequence board_seq;  --시퀀스 제거


* 기본 키 정의하는 방법
[1] 인라인(inline) 표기방식
emp_id number(6,0) PRIMARY KEY

[2] 아웃라인(out-line) 표기방식
emp_id number(6,0),
name varchar2(20),
 CONSTRAINTS  “emp_pk”  PRIMARY KEY(emp_id, name)

* 제약조건 조회
select * from user_constraints 
order by table_name;

* 시퀀스 조회
select * from user_sequences; 

* 테이블 조회
select *  from user_tables;

* 프로시저 조회
select *  from user_procedures;

* 프로시저 내용 조회
select *  from user_source;

select text from user_source 
where name='PDUPDATE';
-----------------------------------------
--집계함수
select count(*) from reboard;   --레코드 갯수 구하기
select max(readcount) from reboard;  --최대값구하기
select min(readcount) from reboard; --최소값 구하기
select sum(readcount) from reboard;  --합계 구하기
select avg(readcount) from reboard;  --평균구하기

-------------------------------------------
--저장 프로시저
CREATE OR REPLACE PROCEDURE 프로시저명 
                 ( 파라미터1 	데이터타입, 
                   파라미터2 	데이터타입, …)
    IS [AS]
         변수선언부 …
    BEGIN
         처리내용 ….
    EXCEPTION 예외처리 …
    END;

----------------------------------
--view 만들기
create or replace view 뷰이름
as
처리 내용

예)
--sales, research 부서의 사원이름, job, hiredate 만 testuser 유저에게 보여 주도록 view 만들기
create or replace view v_emp1 
as
select ename, job, hiredate
from emp
where deptno in(30, 20) ;

--hr 유저에게 view 생성 권한을 주자
grant create view to hr;
--revoke create view from hr;


