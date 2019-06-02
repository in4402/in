--drop table zipcode cascade constraint;
create table zipcode
(
 zipcode varchar2(5),
 sido varchar2(100),
 gugun varchar2(100),
 dong varchar2(200),
 startbunji varchar2(100),
 endbunji varchar2(100),
 seq number primary key
);

select * from zipcode;


--권한 테이블
--drop table authority  cascade constraint;
create table authority
(
	authCode           varchar2(30)  primary key ,  --권한코드
	authName          varchar2(100)  not null , --권한명
	authDesc           varchar2(200)  null , --권한설명
	regdate		date  default sysdate
);

INSERT INTO authority VALUES ('ADMIN'   ,'관리자'     , '', SYSDATE);

--drop table member cascade constraint;
create table member
(
    no           number        primary key,
    userid     varchar2(20)   unique not null,
    name      varchar2(20)    not null,
    pwd        varchar2(15)    not null,
    email       varchar2(50)    null,
    hp            varchar2(20)    null,
    --tel             varchar2(20)    null,
    zipcode    varchar2(5)        null,
    address    varchar2(200)   null,
    addressDetail    varchar2(100)   null,
    regdate     date        default sysdate,
    mileage        number   default 0,
    outdate		date        null
);

--drop sequence member_seq;
create sequence member_seq
increment by 1
start with 1
nocache;

select * from member;

