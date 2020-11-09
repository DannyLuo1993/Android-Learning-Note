# SQL基础知识

### 1.1 MySQL基础、入门知识



### 1.1.1 MySQL的目录结构

**a. 默认安装目录中文件夹的作用**

*  bin文件夹

里面放的是可执行文件

* doc文件夹

放的是文档

* include文件夹

放的是头文件

* lib文件夹

放的是jar包，依赖库

* share文件夹

字符集和语言相关信息

**b. ProgramData目录中文件夹的作用**

数据库指的是这个文件夹，数据表指的是Data文件夹下文件

* Data 

保存数据库和数据表信息

* my.ini

MySQL的配置信息



### 1.1.2 数据库管理系统

指一种操作和管理维护数据库的大型软件。MySQL就是一个数据库管理系统软件，安装了MySQL的电脑，我们叫它数据库服务器。

MySQL服务器就是一台安装了MySQL软件的设备。

一台服务器里安装的MySQL数据库服务器软件管理着多个数据库，数据库中有多张表。表中存放了多条数据记录。

**java 中类与数据库表的关系**

a. 类 对应 表

b. 成员变量 对应 表中字段

c. New 对象 对应 表中的一条记录



### 1.1.3 SQL的概念

SQL是一种结构化查询语言（Structured Query Language）。

* SQL的作用

是所有关系型数据库的统一查询规范，不同的关系型数据库都支持SQL；

所有关系型数据库都可以使用SQL

不同数据库之间的SQL有一些区别（方言）



### 1.1.4 SQL通用语法

* SQL语句可以单行或者多行书写，以分号结尾

* 可以使用空格和缩进增加语句的可读性
* MySQL中使用SQL不区分大小写，一般关键字大写，数据库名、表名、列名小写。
* 注释方式一共三种： `SHOW DATABASES; -- 单行注释`  `SHOW DATABASES; \*多行注释*\` `SHOW DATABASES; #单行注释`



### 1.1.5 SQL的分类

DDL (Data Definition Language) 用于操作数据库和定义表

DML(Data Manipuation Language) 对于表的数据进行增删改操作

DQL(Data Query Language) 用于查询表中的数据

DCL (Data Control Language) 用于定义数据库的访问权限



## 1.2 DDL - 数据定义语言

对数据库的操作分类包含： CRUD

**C -- CREATE 创建数据库&数据表；**

**R -- RETRIEVE 查询；**

**U -- UPDATE 修改；**

**D -- DROP 删除；**



`A. CREATE DATABASE database-name CHARACTER SET charset-name `

通过制定数据库名和编码字符集名创建一个数据库

`B. USE database-name`

切换数据库到指定的数据库名

`C. SELECT DATABASE();`

查询当前使用的数据库名；



### 1.2.1 MySQL自带数据库介绍

* information_schema

信息数据库 保存的是其他数据库的信息

* mysql

MySQL核心数据库，保存的是用户和权限

* performance_schema

保存性能相关数据，监控MySQL的性能

* sys

记录了DBA所需要的一些信息，更方便的让DBA快速了解数据库的 运行情况。（DBA数据库管理员）



### 1.2.2 DDL操作数据库 — 修改与删除

`ALTER DATABASE database-name CHARACTER SET charset-name`

修改指定数据库的字符集为指定字符集名。

`DROP DATABASE database-name`

将数据库从MySQL中永久地删除。



### 1.2.3 MySQL的常见数据类型

`CREATE TABLE table-name { property-type property-name(length),  }；`

语法特点， 最后一个字段结尾不要加逗号。

MySQL中的常见数据类型

int 整型

double 浮点型

varchar 字符串型

date 日期类型，yyyy-MM-dd，只有年月日

datetime

char 字符串型

* varchar类型 和 char类型的区别

varchar是可变长度的，存储字符串时，只使用所需的空间；

char类型的长度是固定的，定义时指明char的长度；

例如保存密码时可以使用char类型；



### 1.2.4 DDL操作数据表 - 创建&查看

```sql
-- 创建分类表
-- 选择要使用的数据库
USE database1;
CREATE TABLE category(
	cid INT,
	cname VARCHAR(20)
	
);

-- 创建测试表
CREATE TABLE test1(
	tid INT,
    tdate TIME
); 

--快速创建一个表结构相同的表（复制）
CREATE TABLE test2 LIKE test1;

--查看表结构
DESC test1;

-- 查看当前数据库中所有的数据表名
SHOW TABLES;

-- 查看创建表的 sql
SHOW CREATE TABLE category;

```



### 1.2.5 DDL操作数据表 - 删除 & 修改

`A. DROP TABLE table-name`

`B. DROP TABLE IF EXISTS table-name`

`C.RENAME TABLE old-table-name TO new-table-name `

功能：修改表名称为新名字

`D.ALTER TABLE table-name CHARACTER SET charset-name `

功能： 修改表的字符集名称为新名字

`E. ALTER TABLE table-name ADD 字段名称 字段类型（长度） `

功能：向表中添加列信息

`F. ALTER TABLE table-name modify 字段名称 字段类型`

功能：修改表中字段信息（类型或长度）

`G. ALTER TABLE table-name CHANGE 旧列名 新列名 类型（长度）`

功能：修改表中列的名称

`H. ALTER TABLE table-name DROP 列名`

功能： 删除表中对应的列



 

* performance_schema

保存

* sys

记录了DBA

## 1.3 DML - INSERT INTO

`INSERT INTO 表名（字段名1，字段名2...） values（字段名1， 字段名2...）`

```mysql
-- 方式一 插入全部字段 将所有字段名都写出来
INSERT INTO student (sid,sname,age,sex,address) VALUE(1,'孙悟空',18,'男','花果山');

-- 方式二 插入全部字段， 不写字段名
INSERT INTO student VALUES(2,'孙悟饭',5,'男','地球');

-- 方式三 插入指定字段
INSERT INTO student（sid, sname） VALUES(3,'蜘蛛精');
```

**注意事项：**

a. 值与字段必须对应；

b. 插入char varchar 和 date类型时，必须使用引号包裹

c. 如果插入空值，可以不写或者写null



### 1.3.2 DML - 修改表中数据

`UPDATE sheet-name SET column-name = value`

`UPDATE sheet-name SET column-name = value [where条件表达式： 字段名 = 值]`

```mysql
UPDATE student SET sex = '男' WHERE sid = 1
```



### 1.3.3 DML - 删除表中数据

`DELETE FROM sheet-name [WHERE = ?];`

```mysql
DELETE FROM student WHERE sid = 6;
```

**两种删除所有数据的方式**

-- delete from 表； 不推荐，对表中数据逐条删除，效率低。

-- truncate table 表； 推荐， 删除整张表，然后再创建一个一模一样的新表。



## 1.4 DQL - SELECT...FROM

**查询操作不会对数据库的数据进行修改**



### 1.4.1  查询表中数据

SELECT column-name FROM sheet-name

```mysql
-- 查询emp表中的所有数据
SELECT * FROM emp；

-- 查询所有数据，只显示id 和 name
SELECT eid， ename FROM emp;

-- 查询所有的数据，并将列名改为中文
SELECT 
	eid AS '编号'
	ename AS '姓名'
	
-- 查询并合并重复的字段（column-name）数据
SELECT DISTUNCT column-name FROM emp；

-- 查询并按条件显示数据
SELECT salary+1000 AS column-name FROM emp；

```



### 1.4.2 排序查询  - ORDER BY

语法结构： SELECT 字段名 FROM 表名 【WHERE 字段名 = 值】 ORDER BY 字段名称 [ASC/DESC]

默认 ASC （升序排序）

* 组合排序

如果第一个字段的值相同，就按第二个字段的值进行排序



### 1.4.3 聚合函数

作用：将一列数据作为一个整体，进行纵向的计算。

* COUNT

统计记录数, 忽略空值的列数据。

* SUM



* MAX



* MIN



* AVG

语法格式： SELECT 聚合函数（字段名） FROM 表名 【WHERE 条件】

```mysql
SELECT
	SUM(salary) AS '总薪水',
	MAX(salary) '',
	MIN(salary) '',
	AVG(salary) '',
FROM emp
```

