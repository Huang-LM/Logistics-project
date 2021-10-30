/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : team1802

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/10/2021 08:42:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `parentId` int(11) NOT NULL COMMENT '父级id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionaries
-- ----------------------------
BEGIN;
INSERT INTO `sys_dictionaries` VALUES (1, '运输方式', 0, '2021-03-09 22:34:12', '2021-03-09 22:41:52');
INSERT INTO `sys_dictionaries` VALUES (2, '出发港口', 0, '2021-03-16 10:30:59', '2021-03-16 10:31:10');
INSERT INTO `sys_dictionaries` VALUES (3, '货物类型', 0, '2021-03-09 22:35:06', '2021-03-09 22:42:25');
INSERT INTO `sys_dictionaries` VALUES (4, '空运', 1, '2021-03-09 22:38:31', '2021-03-09 22:42:57');
INSERT INTO `sys_dictionaries` VALUES (5, '海运', 1, '2021-03-09 22:41:07', '2021-03-09 22:45:38');
INSERT INTO `sys_dictionaries` VALUES (6, '火箭运', 1, '2021-03-09 22:46:52', '2021-03-09 22:46:52');
INSERT INTO `sys_dictionaries` VALUES (7, 'CA1786', 4, '2021-03-09 22:47:15', '2021-03-09 22:47:15');
INSERT INTO `sys_dictionaries` VALUES (8, '敦刻尔克号', 5, '2021-03-09 22:47:59', '2021-03-09 22:47:59');
INSERT INTO `sys_dictionaries` VALUES (9, '东风', 6, '2021-03-09 22:48:10', '2021-03-09 22:48:10');
INSERT INTO `sys_dictionaries` VALUES (10, '日用品', 3, '2021-03-09 22:48:27', '2021-03-09 22:48:27');
INSERT INTO `sys_dictionaries` VALUES (11, '电子设备', 3, '2021-03-09 22:48:37', '2021-03-09 22:48:37');
INSERT INTO `sys_dictionaries` VALUES (12, '危险物品', 3, '2021-03-09 22:48:50', '2021-03-09 22:48:50');
INSERT INTO `sys_dictionaries` VALUES (13, '食品', 3, '2021-03-15 12:44:30', '2021-03-15 12:44:30');
INSERT INTO `sys_dictionaries` VALUES (14, '液体', 3, '2021-03-15 14:55:14', '2021-03-15 14:56:02');
INSERT INTO `sys_dictionaries` VALUES (18, 'HY2784', 4, '2021-03-16 10:04:25', '2021-03-16 10:04:24');
INSERT INTO `sys_dictionaries` VALUES (20, '到达港口', 0, '2021-03-16 10:31:24', '2021-03-16 10:31:24');
INSERT INTO `sys_dictionaries` VALUES (21, '天津港', 2, '2021-03-16 10:33:01', '2021-03-16 10:33:01');
INSERT INTO `sys_dictionaries` VALUES (22, '摩尔曼斯克', 20, '2021-03-16 10:33:16', '2021-03-16 10:33:16');
INSERT INTO `sys_dictionaries` VALUES (23, '冰岛', 20, '2021-03-16 10:33:26', '2021-03-16 10:33:26');
INSERT INTO `sys_dictionaries` VALUES (24, '秦皇岛', 2, '2021-03-16 10:33:42', '2021-03-16 10:33:42');
INSERT INTO `sys_dictionaries` VALUES (26, 'SE1234', 4, '2021-03-19 01:07:50', '2021-03-19 14:07:50');
COMMIT;

-- ----------------------------
-- Table structure for sys_logistics
-- ----------------------------
DROP TABLE IF EXISTS `sys_logistics`;
CREATE TABLE `sys_logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `verify` int(1) NOT NULL DEFAULT '0' COMMENT '审核值',
  `logistics_number` int(20) DEFAULT NULL COMMENT '物流编号',
  `logistics_way` varchar(255) NOT NULL COMMENT '物流运输方式 ',
  `logistics_way_number` varchar(255) DEFAULT NULL COMMENT '物流运输工具编号',
  `mailing_type` varchar(255) NOT NULL COMMENT '货物类型',
  `mailing_name` varchar(255) NOT NULL COMMENT '寄件人姓名',
  `mailing_phone` varchar(255) NOT NULL COMMENT '寄件人电话',
  `mailing_address` varchar(255) NOT NULL COMMENT '寄件地址',
  `shipping_name` varchar(255) NOT NULL COMMENT '收件人姓名',
  `shipping_phone` varchar(255) NOT NULL COMMENT '收货人电话',
  `shipping_address` varchar(255) NOT NULL COMMENT '收货地址',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `shipping_time` timestamp NULL DEFAULT NULL COMMENT '预计到达时间',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_logistics
-- ----------------------------
BEGIN;
INSERT INTO `sys_logistics` VALUES (22, 1, 1156395500, '空运', 'CA1786', '电子设备', 'ww', '123', '天津工业大学', 'wq', '421', '天津工业大学', '123', '2021-03-25 05:43:12', '2021-03-18 05:40:54', '2021-03-18 18:43:14');
INSERT INTO `sys_logistics` VALUES (23, 1, 1226716335, '空运', 'SE1234', '危险物品', '1213', '123', '天津工业大学', '123', '1244', '天津工业大学', '12344', '2021-03-26 01:14:40', '2021-03-19 01:12:34', '2021-03-19 14:14:44');
COMMIT;

-- ----------------------------
-- Table structure for sys_logistics_state
-- ----------------------------
DROP TABLE IF EXISTS `sys_logistics_state`;
CREATE TABLE `sys_logistics_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `state_id` int(11) NOT NULL COMMENT '状态id',
  `logistics_id` int(11) NOT NULL COMMENT '物流id',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_logistics_state
-- ----------------------------
BEGIN;
INSERT INTO `sys_logistics_state` VALUES (29, 1, 1156395500, '2021-03-18 05:41:39', '2021-03-18 18:41:38');
INSERT INTO `sys_logistics_state` VALUES (30, 3, 1156395500, '2021-03-18 05:42:45', '2021-03-18 18:42:45');
INSERT INTO `sys_logistics_state` VALUES (31, 4, 1156395500, '2021-03-18 05:42:52', '2021-03-18 18:42:52');
INSERT INTO `sys_logistics_state` VALUES (32, 5, 1156395500, '2021-03-18 05:42:56', '2021-03-18 18:42:56');
INSERT INTO `sys_logistics_state` VALUES (33, 1, 1226716335, '2021-03-19 01:13:40', '2021-03-19 14:13:39');
INSERT INTO `sys_logistics_state` VALUES (34, 2, 1226716335, '2021-03-19 01:14:22', '2021-03-19 14:14:22');
INSERT INTO `sys_logistics_state` VALUES (35, 3, 1226716335, '2021-03-19 01:14:26', '2021-03-19 14:14:26');
INSERT INTO `sys_logistics_state` VALUES (36, 4, 1226716335, '2021-03-19 01:14:29', '2021-03-19 14:14:29');
COMMIT;

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `parentId` int(11) NOT NULL COMMENT '父id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_menus` VALUES (11, '用户管理', '/user', 'user', '数据库人员的增删改查', 0, '2020-12-29 14:19:31', '2021-03-10 14:41:24');
INSERT INTO `sys_menus` VALUES (12, '权限管理', '/manager/jurisdiction', 'menu', '角色列表：角色的增删改查，赋予角色权限', 0, '2020-12-29 14:19:28', '2021-03-10 14:42:36');
INSERT INTO `sys_menus` VALUES (13, '审核管理', '/manager/examine', 'coordinate', '货主用户审核,货代公司管理', 0, '2020-12-29 14:19:27', '2021-03-10 14:42:37');
INSERT INTO `sys_menus` VALUES (14, '数据字典管理', '/manager/data_dictionary', 's-management', '管理系统下拉列表的，如货物类型，港口类型', 0, '2020-12-29 14:19:25', '2021-03-10 14:42:38');
INSERT INTO `sys_menus` VALUES (15, '数据分析', '/manager/data_analysis', 's-marketing', '大概图表，文字之类展示，这一块也可以是公用的', 0, '2020-12-29 14:19:24', '2021-03-10 14:42:40');
INSERT INTO `sys_menus` VALUES (21, '货物管理', 'goodsManager', 'box', NULL, 0, '2021-03-08 13:57:57', '2021-03-14 16:48:49');
INSERT INTO `sys_menus` VALUES (31, '物流管理', '/shipManager', 'folder', NULL, 0, '2021-03-08 14:02:15', '2021-03-14 16:48:56');
INSERT INTO `sys_menus` VALUES (41, '信息服务', '/message', 'tickets', NULL, 0, '2021-03-08 14:03:29', '2021-03-14 16:49:03');
INSERT INTO `sys_menus` VALUES (111, '用户列表', '/showUser', NULL, '用户列表', 11, '2021-03-01 12:19:12', '2021-03-01 12:34:17');
INSERT INTO `sys_menus` VALUES (121, '角色权限', '/rolePower', NULL, '角色的增删改查，赋予角色权限', 12, '2021-03-06 18:15:12', '2021-03-06 19:17:23');
INSERT INTO `sys_menus` VALUES (122, '用户角色', '/roleUser', NULL, '更新修改用户角色的页面', 12, '2021-03-06 18:17:30', '2021-03-06 18:18:36');
INSERT INTO `sys_menus` VALUES (131, '用户审核', '/checkUser', NULL, '想要寄货的人的信息审核，是否允许他寄货', 13, '2020-12-29 14:19:21', '2021-03-03 14:30:15');
INSERT INTO `sys_menus` VALUES (141, '字典管理', '/dictionaryManager', NULL, '数据字典管理', 14, '2021-03-10 15:17:49', '2021-03-10 15:18:23');
INSERT INTO `sys_menus` VALUES (151, '货物统计', '/goodsStatistics', NULL, '统计不同的运输方式所占用的时间', 15, '2020-12-29 14:19:16', '2021-03-09 16:03:06');
INSERT INTO `sys_menus` VALUES (152, '货流高峰', '/goodsPeck', NULL, '每月或每季度，运输最繁忙的时间段，运输量最大的地区', 15, '2020-12-29 14:19:12', '2021-03-09 16:03:27');
INSERT INTO `sys_menus` VALUES (211, '寄送货物', '/shipApplication', NULL, '申请寄件', 21, '2021-03-08 13:58:49', '2021-03-08 14:21:19');
INSERT INTO `sys_menus` VALUES (212, '货物查询', '/shipInquiry', NULL, '查看货寄到哪里了', 21, '2021-03-08 13:59:33', '2021-03-08 14:21:23');
INSERT INTO `sys_menus` VALUES (311, '审核货物', '/reviewGoods', NULL, '审核货主寄的货物，决定寄不寄，并填写物流号，同时选择货物的运输方式', 31, '2021-03-08 14:02:46', '2021-03-09 14:18:06');
INSERT INTO `sys_menus` VALUES (411, '货物信息管理', '/recordedInfo', NULL, '货到哪了就更新货物位置', 41, '2021-03-08 14:03:48', '2021-03-09 16:03:46');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', '系统管理员', '2021-01-20 22:00:59', '2021-03-06 18:34:08');
INSERT INTO `sys_role` VALUES (2, '货主', '想要寄件的人', '2021-02-22 14:41:50', '2021-03-06 18:34:58');
INSERT INTO `sys_role` VALUES (3, '物流管理员', '审核货物，并安排货物去向的', '2021-01-20 22:01:11', '2021-03-06 20:13:29');
INSERT INTO `sys_role` VALUES (4, '信息服务员', '更新货物状态地址的', '2021-03-06 20:13:57', '2021-03-06 20:13:57');
INSERT INTO `sys_role` VALUES (7, '超级管理员', '就是超级', '2021-03-14 16:46:30', '2021-03-18 14:56:55');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menus`;
CREATE TABLE `sys_role_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menus` VALUES (301, 7, 41, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (302, 7, 411, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (303, 7, 11, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (304, 7, 111, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (305, 7, 12, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (306, 7, 121, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (307, 7, 122, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (308, 7, 13, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (309, 7, 131, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (310, 7, 14, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (311, 7, 141, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (312, 7, 15, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (313, 7, 151, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (314, 7, 152, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (315, 7, 21, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (316, 7, 211, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (317, 7, 212, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (318, 7, 31, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (319, 7, 311, '2021-03-18 13:47:35', '2021-03-18 13:47:35');
INSERT INTO `sys_role_menus` VALUES (347, 3, 31, '2021-03-18 14:35:54', '2021-03-18 14:35:54');
INSERT INTO `sys_role_menus` VALUES (348, 3, 311, '2021-03-18 14:35:54', '2021-03-18 14:35:54');
INSERT INTO `sys_role_menus` VALUES (349, 2, 21, '2021-03-18 14:36:12', '2021-03-18 14:36:12');
INSERT INTO `sys_role_menus` VALUES (350, 2, 211, '2021-03-18 14:36:12', '2021-03-18 14:36:12');
INSERT INTO `sys_role_menus` VALUES (351, 2, 212, '2021-03-18 14:36:12', '2021-03-18 14:36:12');
INSERT INTO `sys_role_menus` VALUES (352, 4, 41, '2021-03-18 14:55:30', '2021-03-18 14:55:30');
INSERT INTO `sys_role_menus` VALUES (353, 4, 411, '2021-03-18 14:55:30', '2021-03-18 14:55:30');
INSERT INTO `sys_role_menus` VALUES (381, 1, 11, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (382, 1, 111, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (383, 1, 12, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (384, 1, 121, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (385, 1, 122, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (386, 1, 13, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (387, 1, 131, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (388, 1, 14, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (389, 1, 141, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (390, 1, 15, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (391, 1, 151, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (392, 1, 152, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (393, 1, 21, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (394, 1, 211, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (395, 1, 212, '2021-03-19 14:06:17', '2021-03-19 14:06:17');
INSERT INTO `sys_role_menus` VALUES (396, 15, 13, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
INSERT INTO `sys_role_menus` VALUES (397, 15, 131, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
INSERT INTO `sys_role_menus` VALUES (398, 15, 14, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
INSERT INTO `sys_role_menus` VALUES (399, 15, 141, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
INSERT INTO `sys_role_menus` VALUES (400, 15, 15, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
INSERT INTO `sys_role_menus` VALUES (401, 15, 151, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
INSERT INTO `sys_role_menus` VALUES (402, 15, 152, '2021-04-21 21:48:39', '2021-04-21 21:48:39');
COMMIT;

-- ----------------------------
-- Table structure for sys_state
-- ----------------------------
DROP TABLE IF EXISTS `sys_state`;
CREATE TABLE `sys_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `state` varchar(11) NOT NULL COMMENT '事件状态',
  `state_info` varchar(255) NOT NULL COMMENT '事件状态描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_state
-- ----------------------------
BEGIN;
INSERT INTO `sys_state` VALUES (1, '已下单', '您的订单已通过审核', '2021-03-11 13:43:16', '2021-03-11 13:43:16');
INSERT INTO `sys_state` VALUES (2, '已发货', '等待快递员揽收', '2021-03-11 13:44:30', '2021-03-18 22:22:58');
INSERT INTO `sys_state` VALUES (3, '已揽收', '快递员揽收成功', '2021-03-11 13:45:50', '2021-03-18 22:22:59');
INSERT INTO `sys_state` VALUES (4, '运输中', '正在运输', '2021-03-11 13:46:27', '2021-03-11 13:46:27');
INSERT INTO `sys_state` VALUES (5, '已到达', '货物到达指定地点', '2021-03-11 13:46:48', '2021-03-11 13:50:03');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `gender` tinyint(3) DEFAULT NULL COMMENT '性别：女0，男1',
  `email` varchar(255) NOT NULL COMMENT '邮箱地址',
  `verify` bit(1) NOT NULL DEFAULT b'0' COMMENT '审核值：未通过0，通过为1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `username_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'ming', '1', '123456758912', 1, '8473749888@qq.com', b'1', '2020-12-28 17:56:46', '2021-03-19 14:05:27');
INSERT INTO `sys_user` VALUES (2, 'fmx', '1', '145646', 1, '2449616719@qq.com', b'1', '2020-12-31 10:49:41', '2021-03-18 10:36:03');
INSERT INTO `sys_user` VALUES (3, 'zcm', '1', '123456', 1, '1213125222@qq.com', b'1', '2021-03-13 20:33:09', '2021-03-18 11:35:28');
INSERT INTO `sys_user` VALUES (4, 'zhl', '1', '123', 1, '1213125222@qq.com', b'1', '2021-03-03 21:31:30', '2021-03-19 13:39:00');
INSERT INTO `sys_user` VALUES (5, 'wang', '1', '44321', 0, 'mekatop@qq.com', b'1', '2021-03-18 10:02:31', '2021-03-19 13:39:56');
INSERT INTO `sys_user` VALUES (6, 'cr', '123', '12345', 1, '32434@qq.com', b'0', '2021-03-17 21:31:51', '2021-03-18 15:32:25');
INSERT INTO `sys_user` VALUES (44, 'www', '123', '123', 1, '135315@qq.com', b'0', '2021-03-18 02:22:05', '2021-03-18 15:32:29');
INSERT INTO `sys_user` VALUES (46, '123', '12', '1345', 1, '847374988@qq.com', b'1', '2021-03-19 01:03:44', '2021-03-19 14:15:54');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_logistics
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_logistics`;
CREATE TABLE `sys_user_logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `logistics_id` int(11) NOT NULL COMMENT '物流id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_logistics
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_logistics` VALUES (14, 22, 1, '2021-03-18 18:40:54', '2021-03-18 18:40:54');
INSERT INTO `sys_user_logistics` VALUES (15, 23, 1, '2021-03-19 14:12:34', '2021-03-19 14:12:34');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `roles_id` int(11) NOT NULL COMMENT '角色id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_roles` VALUES (25, 1, 7, '2021-03-18 10:08:20', '2021-03-19 14:11:57');
INSERT INTO `sys_user_roles` VALUES (26, 2, 2, '2021-03-18 10:08:27', '2021-03-18 10:08:27');
INSERT INTO `sys_user_roles` VALUES (27, 3, 3, '2021-03-18 10:08:53', '2021-03-18 10:08:53');
INSERT INTO `sys_user_roles` VALUES (28, 4, 4, '2021-03-18 10:08:58', '2021-03-18 10:08:58');
INSERT INTO `sys_user_roles` VALUES (29, 5, 7, '2021-03-18 10:10:13', '2021-03-18 14:56:46');
INSERT INTO `sys_user_roles` VALUES (30, 43, 2, '2021-03-18 10:31:51', '2021-03-18 10:31:51');
INSERT INTO `sys_user_roles` VALUES (31, 44, 2, '2021-03-18 15:22:05', '2021-03-18 15:22:05');
INSERT INTO `sys_user_roles` VALUES (32, 45, 2, '2021-03-18 18:32:18', '2021-03-18 18:32:18');
INSERT INTO `sys_user_roles` VALUES (33, 46, 2, '2021-03-19 14:03:44', '2021-03-19 14:03:44');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
