--1. insert ����
insert into ���̺�� (�÷���, ..)
values(��, ..);

--��)
insert into pd(no, pdname, price)
values(pd_seq.nextval, '��ǻ��', 1500000);

commit;  --�۾��Ϸ�(�޸𸮿� ����� ������ ���� ���������Ͽ��� ����)
--rollback; (�۾����)

--2. update
update ���̺�� 
set �÷���1=��1, �÷���2=��2
where ����

--��)
update pd
set pdname='���콺', price=17000
where no=3;

commit;

--3. delete
delete from ���̺��
where ����

--��)
delete from pd
where no=3;

commit;

--4. select
select * from ���̺��
where ����

select * from pd
where no=5;

--�����ϱ�
select * from pd
where price>=150000
order by no desc;  --�������� ����

select * from pd
where price>=150000
order by no;  --�������� ����


-- like

--�̸��� '��'���� �����ϴ� ��� ��ȸ�ϱ�
select * from student
where name like '��%';
--�̸��� '��'�� ������ ��� 
select * from student
where name like '%��';
--�̸��� '��'�� ���ԵǴ� ���
select * from student
where name like '%��%';

------------------------------------------------
--table �����

CREATE TABLE   ���̺��
(
	�÷�1	������Ÿ��,
	�÷�2	������Ÿ��,
	��
)

create table board
(
	no		number		primary key,	--��ȣ
	name	varchar2(20)	not null,		--�̸�	
	pwd         varchar2(20)    not null,         	--��й�ȣ
	title		varchar2(100)	 null,		--����
	regdate 	 date		default  sysdate,	--�ۼ���
	readcount number		default 0	 null,	--��ȸ��
	content	 clob			null				--����
);

--sequence �����
CREATE SEQUENCE ����������
           INCREMENT BY  -- �������� �����Ǵ� ����
           START WITH  -- ������ ������ ���۵Ǵ� ��
           NOCACHE;


create sequence board_seq
increment by 1
start with 1
nocache;

drop table board;  --���̺� ����
drop sequence board_seq;  --������ ����


* �⺻ Ű �����ϴ� ���
[1] �ζ���(inline) ǥ����
emp_id number(6,0) PRIMARY KEY

[2] �ƿ�����(out-line) ǥ����
emp_id number(6,0),
name varchar2(20),
 CONSTRAINTS  ��emp_pk��  PRIMARY KEY(emp_id, name)

* �������� ��ȸ
select * from user_constraints 
order by table_name;

* ������ ��ȸ
select * from user_sequences; 

* ���̺� ��ȸ
select *  from user_tables;

* ���ν��� ��ȸ
select *  from user_procedures;

* ���ν��� ���� ��ȸ
select *  from user_source;

select text from user_source 
where name='PDUPDATE';
-----------------------------------------
--�����Լ�
select count(*) from reboard;   --���ڵ� ���� ���ϱ�
select max(readcount) from reboard;  --�ִ밪���ϱ�
select min(readcount) from reboard; --�ּҰ� ���ϱ�
select sum(readcount) from reboard;  --�հ� ���ϱ�
select avg(readcount) from reboard;  --��ձ��ϱ�

-------------------------------------------
--���� ���ν���
CREATE OR REPLACE PROCEDURE ���ν����� 
                 ( �Ķ����1 	������Ÿ��, 
                   �Ķ����2 	������Ÿ��, ��)
    IS [AS]
         ��������� ��
    BEGIN
         ó������ ��.
    EXCEPTION ����ó�� ��
    END;

----------------------------------
--view �����
create or replace view ���̸�
as
ó�� ����

��)
--sales, research �μ��� ����̸�, job, hiredate �� testuser �������� ���� �ֵ��� view �����
create or replace view v_emp1 
as
select ename, job, hiredate
from emp
where deptno in(30, 20) ;

--hr �������� view ���� ������ ����
grant create view to hr;
--revoke create view from hr;


