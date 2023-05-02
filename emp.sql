/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : emp

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2021-09-23 23:01:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '1234');

-- ----------------------------
-- Table structure for applicants
-- ----------------------------
DROP TABLE IF EXISTS `applicants`;
CREATE TABLE `applicants` (
  `applicant_id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `age` varchar(16) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `job_exp` text,
  `is_selected` int(11) DEFAULT NULL,
  PRIMARY KEY (`applicant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applicants
-- ----------------------------
INSERT INTO `applicants` VALUES ('1', '刘力发', '男', '25', '运营专员', '福州师范学院', '本科', '市场营销', '大四期间在一个互联网公司实习半年', '0');
INSERT INTO `applicants` VALUES ('5', '刘志聪', '男', '27', '网络工程师', '华中科技大学', '本科', '网络工程', '在武汉天河科技有限公司任职一年，任网络工程职位', '0');
INSERT INTO `applicants` VALUES ('6', '黄立成', '男', '23', '运维工程师', '华中科技大学', '本科', '网络工程', '实习过半年', '0');

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `att_day` varchar(255) DEFAULT NULL,
  `checkin` datetime DEFAULT NULL,
  `checkout` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`att_id`),
  KEY `fk_att_emp` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '3', '2021-07-11', '2021-07-11 08:45:01', '2021-07-11 18:41:36', '正常');
INSERT INTO `attendance` VALUES ('2', '3', '2021-07-12', '2021-07-12 08:12:43', '2021-07-12 18:18:33', '正常');
INSERT INTO `attendance` VALUES ('3', '3', '2021-07-13', '2021-07-13 14:15:48', null, '迟到');
INSERT INTO `attendance` VALUES ('4', '3', '2021-07-14', '2021-07-15 08:54:57', '2021-07-15 18:54:54', '迟到');
INSERT INTO `attendance` VALUES ('5', '4', '2021-07-11', '2021-07-13 08:35:21', '0021-01-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('6', '2', '2021-07-11', '2021-07-13 08:52:12', '0021-01-13 17:56:21', '早退');
INSERT INTO `attendance` VALUES ('7', '4', '2021-07-12', '2021-07-13 08:35:21', '0021-01-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('8', '2', '2021-07-12', '2021-07-13 08:52:12', '0021-01-13 17:56:21', '早退');
INSERT INTO `attendance` VALUES ('9', '4', '2021-07-13', '2021-07-13 08:35:21', '0021-01-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('10', '2', '2021-07-13', '2021-07-13 08:52:12', '0021-01-13 19:56:21', '正常');
INSERT INTO `attendance` VALUES ('11', '4', '2021-07-14', '2021-07-13 08:35:21', '0021-01-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('12', '2', '2021-07-14', '2021-07-13 08:52:12', '0021-01-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('13', '1', '2021-07-14', '2021-07-13 08:52:12', '0021-01-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('14', '1', '2021-07-11', '2021-07-13 08:52:12', '0021-01-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('15', '4', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('16', '5', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('17', '6', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('18', '7', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('19', '4', '2021-07-18', '2021-07-18 09:13:14', '0021-01-18 18:03:22', '迟到');
INSERT INTO `attendance` VALUES ('20', '5', '2021-07-18', '2021-07-18 08:52:12', '0021-01-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('21', '6', '2021-07-18', '2021-07-18 09:23:18', '0021-01-18 18:03:22', '迟到');
INSERT INTO `attendance` VALUES ('22', '7', '2021-07-18', '2021-07-18 08:52:12', '0021-01-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('23', '4', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('24', '5', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('25', '6', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('26', '7', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('27', '4', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('28', '5', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('29', '6', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('30', '7', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('31', '1', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('32', '2', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('33', '3', '2021-07-15', '2021-07-15 08:52:12', '0021-01-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('34', '1', '2021-07-18', '2021-07-18 08:52:12', '0021-01-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('35', '2', '2021-07-18', '2021-07-18 08:52:12', '0021-01-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('36', '3', '2021-07-18', '2021-07-18 08:52:12', '0021-01-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('37', '1', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('38', '2', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('39', '3', '2021-07-19', '2021-07-19 08:52:12', '0021-01-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('40', '1', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('41', '2', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('42', '3', '2021-07-20', '2021-07-20 08:52:12', '0021-01-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('43', '5', '2021-07-11', '2021-07-13 08:52:12', '0021-01-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('44', '6', '2021-07-11', '2021-07-13 08:52:12', '0021-01-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('45', '7', '2021-07-11', '2021-07-13 08:52:12', '0021-01-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('46', '5', '2021-07-12', '2021-07-12 08:52:12', '0021-01-12 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('47', '6', '2021-07-12', '2021-07-12 08:52:12', '0021-01-12 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('48', '7', '2021-07-12', '2021-07-12 08:52:12', '0021-01-12 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('49', '2', '2021-07-21', '2021-07-21 16:07:51', null, '迟到');
INSERT INTO `attendance` VALUES ('50', '3', '2021-07-21', '2021-07-21 16:08:56', '2021-07-21 16:09:54', '迟到|早退');
INSERT INTO `attendance` VALUES ('90', '3', '2021-09-11', '2021-09-11 08:45:01', '2021-09-11 18:41:36', '正常');
INSERT INTO `attendance` VALUES ('91', '3', '2021-09-12', '2021-09-12 08:12:43', '2021-09-12 18:18:33', '正常');
INSERT INTO `attendance` VALUES ('92', '3', '2021-09-13', '2021-09-13 14:15:48', null, '迟到');
INSERT INTO `attendance` VALUES ('93', '3', '2021-09-14', '2021-09-15 08:54:57', '2021-09-15 18:54:54', '迟到');
INSERT INTO `attendance` VALUES ('94', '4', '2021-09-11', '2021-09-13 08:35:21', '0021-04-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('95', '2', '2021-09-11', '2021-09-13 08:52:12', '0021-04-13 17:56:21', '早退');
INSERT INTO `attendance` VALUES ('96', '4', '2021-09-12', '2021-09-13 08:35:21', '0021-04-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('97', '2', '2021-09-12', '2021-09-13 08:52:12', '0021-04-13 17:56:21', '早退');
INSERT INTO `attendance` VALUES ('98', '4', '2021-09-13', '2021-09-13 08:35:21', '0021-04-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('99', '2', '2021-09-13', '2021-09-13 08:52:12', '0021-04-13 19:56:21', '正常');
INSERT INTO `attendance` VALUES ('100', '4', '2021-09-14', '2021-09-13 08:35:21', '0021-04-13 18:35:21', '正常');
INSERT INTO `attendance` VALUES ('101', '2', '2021-09-14', '2021-09-13 08:52:12', '0021-04-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('102', '1', '2021-09-14', '2021-09-13 08:52:12', '0021-04-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('103', '1', '2021-09-11', '2021-09-13 08:52:12', '0021-04-13 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('104', '4', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('105', '5', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('106', '6', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('107', '7', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('108', '4', '2021-09-18', '2021-09-18 09:13:14', '0021-04-18 18:03:22', '迟到');
INSERT INTO `attendance` VALUES ('109', '5', '2021-09-18', '2021-09-18 08:52:12', '0021-04-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('110', '6', '2021-09-18', '2021-09-18 09:23:18', '0021-04-18 18:03:22', '迟到');
INSERT INTO `attendance` VALUES ('111', '7', '2021-09-18', '2021-09-18 08:52:12', '0021-04-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('112', '4', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('113', '5', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('114', '6', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('115', '7', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('116', '4', '2021-09-20', '2021-09-20 08:52:12', '0021-04-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('117', '5', '2021-09-20', '2021-09-20 08:52:12', '0021-04-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('118', '6', '2021-09-20', '2021-09-20 08:52:12', '0021-04-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('119', '7', '2021-09-20', '2021-09-20 08:52:12', '0021-04-20 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('120', '1', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('121', '2', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('122', '3', '2021-09-15', '2021-09-15 08:52:12', '0021-04-15 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('123', '1', '2021-09-18', '2021-09-18 08:52:12', '0021-04-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('124', '2', '2021-09-18', '2021-09-18 08:52:12', '0021-04-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('125', '3', '2021-09-18', '2021-09-18 08:52:12', '0021-04-18 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('126', '1', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('127', '2', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');
INSERT INTO `attendance` VALUES ('128', '3', '2021-09-19', '2021-09-19 08:52:12', '0021-04-19 18:03:22', '正常');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_no` varchar(32) DEFAULT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `salary` double(10,2) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '2108101', '王嘉仪', '销售部', '1990-06-13', '女', '销售专员', '4500.00', '100', '1234', '1', '1');
INSERT INTO `employee` VALUES ('2', '0811303', '邱晓光', '运营部', '1985-07-17', '男', '运营组长', '8550.00', '100', '1234', '2', '1');
INSERT INTO `employee` VALUES ('3', '1201202', '陈萱', '行政部', '1989-12-20', '女', '行政专员', '3600.00', '100', '1234', '1', '1');
INSERT INTO `employee` VALUES ('4', '1907309', '李斌腾', '运营部', '1989-09-21', '男', '运营专员', '5860.00', '100', '1234', '1', '1');
INSERT INTO `employee` VALUES ('5', '2003321', '张宝瑶', '运营部', '1986-08-05', '女', '运营专员', '5700.00', '100', '1234', '1', '1');
INSERT INTO `employee` VALUES ('6', '2011322', '王利明', '运营部', '1993-08-30', '男', '运营专员', '3600.00', '100', '1234', '1', '1');
INSERT INTO `employee` VALUES ('7', '2009312', '梁玲玲', '运营部', '1994-09-08', '女', '客服专员', '3800.00', '100', '1234', '1', '1');
INSERT INTO `employee` VALUES ('8', '0904711', '梁伟强', '研发部', '1987-06-13', '男', '研发主管', '12500.00', '100', '1234', '1', '1');

-- ----------------------------
-- Table structure for metting
-- ----------------------------
DROP TABLE IF EXISTS `metting`;
CREATE TABLE `metting` (
  `metting_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `location` varchar(255) DEFAULT NULL,
  `metting_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`metting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of metting
-- ----------------------------
INSERT INTO `metting` VALUES ('1', '销售部年终总结大会', '总结2020年工作，表彰绩效优异的同事', '19楼大会议室', '2021-07-13 10:30:00');
INSERT INTO `metting` VALUES ('2', '运营部2021年度工作计划会议', '制定运营部2021年工作计划，每个小组长需要做计划汇报', '3号会议室', '2021-07-22 13:30:00');
INSERT INTO `metting` VALUES ('4', '2020年会', '2020年会及晚宴', '中国大酒店5楼大厅', '2021-07-29 19:00:00');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `salary_id` int(11) NOT NULL AUTO_INCREMENT,
  `salary_date` varchar(32) DEFAULT NULL,
  `base_amt` double DEFAULT NULL,
  `meal_amt` double DEFAULT NULL,
  `house_amt` double DEFAULT NULL,
  `full_att` double DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `subsidy` double DEFAULT NULL,
  `fine` double DEFAULT NULL,
  `fine_detail` varchar(255) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `act_amount` double DEFAULT NULL,
  PRIMARY KEY (`salary_id`),
  KEY `fk_sal_emp` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('1', '2021-03', '3800', '220', '150', '180', '230', '100', '0', '无', '1', '4220');
INSERT INTO `salary` VALUES ('2', '2021-03', '5300', '220', '150', '180', '345.3', '550', '0', '', '2', '6054.7');
INSERT INTO `salary` VALUES ('3', '2021-09', '3900', '220', '150', '180', '192.1', '100', '180', '未按时上交报告', '7', '4177.9');
INSERT INTO `salary` VALUES ('4', '2021-03', '3800', '220', '150', '180', '71.25', '390.5', '0', '无', '3', '4669.25');
INSERT INTO `salary` VALUES ('5', '2021-02', '3800', '220', '150', '180', '73.8', '412.6', '0', '无', '3', '4688.8');

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `train_id` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  `trainer` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `summary` text,
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES ('2', '钉钉自动办公培训', '陈萱', '全休员工', '2021-09-28 13:30:00', '2021-09-29 12:00:00', '1', '全体员工经过学习与实操，已经掌握钉钉无纸化办公的使用技巧');
INSERT INTO `train` VALUES ('3', '社保新规学习', '刘学明', '人力资源部', '2021-05-10 09:30:00', '2021-05-10 17:30:00', '0', null);
INSERT INTO `train` VALUES ('4', '自动化运营', '邱晓光', '运营部全体员工', '2021-09-27 13:30:00', '2021-09-28 12:00:00', '1', '这是总结');
INSERT INTO `train` VALUES ('5', '测试培训', '测试', '人力资源部', '2021-09-28 13:30:00', '2021-09-29 13:30:00', '0', null);
SET FOREIGN_KEY_CHECKS=1;
