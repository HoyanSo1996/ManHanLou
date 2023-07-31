CREATE DATABASE MHL;
USE MHL;

# employee表
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	empId VARCHAR(32) UNIQUE NOT NULL DEFAULT '',
	`password` CHAR(32) NOT NULL DEFAULT '', 
	`name` VARCHAR(32) NOT NULL DEFAULT '',
	job VARCHAR(50) NOT NULL DEFAULT '',
	createTime DATETIME,
	updateTime DATETIME
);
INSERT INTO employee VALUES
	(NULL, '10001', MD5('123456'), '张三丰', '经理', NOW(), NOW()),
	(NULL, '10002', MD5('123456'), '小龙女', '服务员', NOW(), NOW()),
	(NULL, '10003', MD5('123456'), '张无忌', '收银员', NOW(), NOW()),
	(NULL, '10004', MD5('123456'), '杨过', '经理', NOW(), NOW());


# diningTable表
CREATE TABLE diningTable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	state INT DEFAULT 0,   # 餐桌状态: 0为空,1为在用状态
	orderName VARCHAR(32) NOT NULL DEFAULT '',   # 预定人的名字
	orderTel VARCHAR(11) NOT NULL DEFAULT ''
);
INSERT INTO diningTable VALUES
	(NULL, '0', '', ''),
	(NULL, '0', '', ''),
	(NULL, '0', '', ''),
	(NULL, '0', '', ''),
	(NULL, '0', '', ''),
	(NULL, '0', '', ''),
	(NULL, '0', '', ''),
	(NULL, '0', '', '');
	

#菜谱
CREATE TABLE menu (
	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键,作为菜谱编号(唯一)
	NAME VARCHAR(50) NOT NULL DEFAULT '',#菜品名称
	TYPE VARCHAR(50) NOT NULL DEFAULT '', #菜品种类
	price DOUBLE NOT NULL DEFAULT 0#价格
)CHARSET=utf8; 
INSERT INTO menu VALUES(NULL, '八宝饭', '主食类', 10);
INSERT INTO menu VALUES(NULL, '叉烧包', '主食类', 20);
INSERT INTO menu VALUES(NULL, '宫保鸡丁', '热菜类', 30);
INSERT INTO menu VALUES(NULL, '山药拨鱼', '凉菜类', 14);
INSERT INTO menu VALUES(NULL, '银丝卷', '甜食类', 9);
INSERT INTO menu VALUES(NULL, '水煮鱼', '热菜类', 26);
INSERT INTO menu VALUES(NULL, '甲鱼汤', '汤类', 100);
INSERT INTO menu VALUES(NULL, '鸡蛋汤', '汤类', 16);


#账单流水, 考虑可以分开结账, 并考虑将来分别统计各个不同菜品的销`menu``menu`售情况
CREATE TABLE bill (
	id INT PRIMARY KEY AUTO_INCREMENT,   #自增主键
	billId VARCHAR(50) NOT NULL DEFAULT '',  #账单号可以按照自己规则生成 UUID
	diningTableId INT NOT NULL DEFAULT 0, #餐桌
	menuId INT NOT NULL DEFAULT 0,  #菜品的编号, 也可以使用外键
	nums SMALLINT NOT NULL DEFAULT 0,  #份数
	money DOUBLE NOT NULL DEFAULT 0,   #金额
	billDate DATETIME NOT NULL ,  #订单日期
	state VARCHAR(50) NOT NULL DEFAULT '' # 状态 '未结账' , '已结账', '挂单'
)CHARSET=utf8;

SELECT * FROM bill