create database IF NOT EXISTS db_test;
use db_test;
-- 第一个数据源表 
create table if not exists t_test_source_0(
	id bigint primary key PRIMARY KEY AUTO_INCREMENT,
    test_char varchar(120) not null,
    test_int  int(10) not null default 0,
    test_text text,
    test_float float default 0.0,
    test_double double default 0.0,
    birth_date date,
    deletedperson2person2 varchar(5) default 'false' not null
);
-- 第二个数据源表
create table if not exists t_test_source_1(
	id bigint primary key PRIMARY KEY AUTO_INCREMENT,
    test_char2 varchar(120) not null,
    test_int2  int(10) not null default 0,
    test_text2 text,
    test_float2 float default 0.0,
    test_double2 double default 0.0,
    birth_date2 date,
    deleted2 varchar(5) default 'false' not null
);

-- 用来接收 来自 databus 监听的结果
create table if not exists t_test_check_1(
	id bigint primary key PRIMARY KEY AUTO_INCREMENT,
    test_char varchar(120) not null,
    test_int  int(10) not null default 0,
    test_text text,
    test_float float default 0.0,
    test_double double default 0.0,
    birth_date date,
    deleted varchar(5) default 'false' not null,
    test_char2 varchar(120) not null,
    test_int2  int(10) not null default 0,
    test_text2 text,
    test_float2 float default 0.0,
    test_double2 double default 0.0,
    birth_date2 date,
    deleted2 varchar(5) default 'false' not null
);


insert into t_test_source_0 value(28,'aa', 0,'a13udfg5', 0.5,0.6,'2018-4-8', 'false');
insert into t_test_source_0 value(3,'aa', 0,'a13udfg5', 0.5,0.6,'2018-4-8', 'false');
insert into t_test_source_1 value(28,'aa', 0,'a13udfg5', 0.5,0.6,'2018-4-8', 'false');
insert into t_test_source_1 value(3,'aa', 0,'a13udfg5', 0.5,0.6,'2018-4-8', 'false');
insert into t_test_source_1 value(28,'bb','a13udfg5','eee','2018-4-8','0');

PURGE MASTER LOGS BEFORE DATE_SUB( NOW( ), INTERVAL 3 DAY);
show binlog events;
show binlog events in 'mysql-bin.000019';
show binary logs;
show master status;
SHOW SLAVE STATUS;

show variables like '%server_id%';
show variables like 'log_bin';
show variables like 'log_%';
show variables like 'server_id';
show global variables like 'binlog_format';
set global binlog_format=ROW;
show global variables like 'binlog_checksum'; 
set global binlog_checksum=none;
-- drop database db_test;
-- drop table t_test;
