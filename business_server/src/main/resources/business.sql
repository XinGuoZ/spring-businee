/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : business

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/11/2020 23:59:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) NULL DEFAULT NULL,
  `dep_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_parent` tinyint(1) NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `remove_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0-未删除 1-已删除',
  `create_at` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL COMMENT '员工编号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `gender` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `idCard` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `wedlock` enum('已婚','未婚','离异') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `nationId` int(8) NULL DEFAULT NULL COMMENT '民族',
  `nativePlace` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `politicId` int(8) NULL DEFAULT NULL COMMENT '政治面貌',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `departmentId` int(11) NULL DEFAULT NULL COMMENT '所属部门',
  `jobLevelId` int(11) NULL DEFAULT NULL COMMENT '职称ID',
  `posId` int(11) NULL DEFAULT NULL COMMENT '职位ID',
  `engageForm` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聘用形式',
  `tiptopDegree` enum('博士','硕士','本科','大专','高中','初中','小学','其他') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历',
  `specialty` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属专业',
  `school` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `beginDate` date NULL DEFAULT NULL COMMENT '入职日期',
  `workState` enum('在职','离职') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '在职' COMMENT '在职状态',
  `workID` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `contractTerm` double NULL DEFAULT NULL COMMENT '合同期限',
  `conversionTime` date NULL DEFAULT NULL COMMENT '转正日期',
  `notWorkDate` date NULL DEFAULT NULL COMMENT '离职日期',
  `beginContract` date NULL DEFAULT NULL COMMENT '合同起始日期',
  `endContract` date NULL DEFAULT NULL COMMENT '合同终止日期',
  `workAge` int(11) NULL DEFAULT NULL COMMENT '工龄',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `remove_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0-未删除 1-已删除',
  `create_at` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL,
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组件路径',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标样式',
  `keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否激活',
  `require_auth` tinyint(1) NULL DEFAULT NULL COMMENT '权限',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级菜单',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '可用不可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (6, '/sys', '/sys', 'Home', '系统管理', 'fa fa-windows', NULL, 1, 0, 1);
INSERT INTO `menu` VALUES (7, '/sys/menu', '/sys/menu', 'Menu', '菜单', 'fa fa-windows', NULL, 1, 6, 1);
INSERT INTO `menu` VALUES (8, '/sys/role', '/sys/role', 'Role', '权限', 'fa fa-windows', NULL, 1, 6, 1);
INSERT INTO `menu` VALUES (9, '/user', '/user', 'Home', '用户管理', 'fa fa-windows', NULL, 1, 0, 1);
INSERT INTO `menu` VALUES (10, '/user/sysUser', '/user/sysUser', 'SysUser', '用户', 'fa fa-windows', NULL, 1, 9, 1);
INSERT INTO `menu` VALUES (11, '/user/department', '/user/department', 'Department', '部门', 'fa fa-windows', NULL, 1, 9, 1);
INSERT INTO `menu` VALUES (12, '/sys/manager', '/sys/manager', 'Manager', '系统管理员', 'fa fa-windows', NULL, 1, 6, 1);

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` bigint(20) NOT NULL,
  `mid` bigint(20) NULL DEFAULT NULL,
  `rid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (12312331, 12, 6);
INSERT INTO `menu_role` VALUES (26121221, 10, 6);
INSERT INTO `menu_role` VALUES (123122312, 6, 6);
INSERT INTO `menu_role` VALUES (123122313, 7, 6);
INSERT INTO `menu_role` VALUES (123122314, 8, 6);
INSERT INTO `menu_role` VALUES (123122315, 9, 6);
INSERT INTO `menu_role` VALUES (1231223157, 11, 6);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nameZh` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (6, 'ROLE_admin', '系统管理员');

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) NULL DEFAULT NULL,
  `dep_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_parent` tinyint(1) NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `remove_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0-未删除 1-已删除',
  `create_at` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES (1, '超级管理员', 0, '.1', 1, 1, '', '0', '0', '2020-11-07 23:58:41');
INSERT INTO `sys_group` VALUES (2, '普通管理员', 1, '.1.2', 1, 1, NULL, '0', '0', '2020-11-08 00:03:07');
INSERT INTO `sys_group` VALUES (3, '总公司管理', 1, '.1.3', 1, 1, NULL, '0', '0', '2020-11-08 00:57:16');
INSERT INTO `sys_group` VALUES (93, '一级管理', 2, '.1.2.93', 1, 0, NULL, '0', '2020-11-06 23:34:16', '2020-11-08 00:03:10');
INSERT INTO `sys_group` VALUES (94, '二级管理', 2, '.1.2.94', 1, 1, NULL, '0', '2020-11-06 23:36:40', '2020-11-08 00:59:50');
INSERT INTO `sys_group` VALUES (95, '1312', 1, '.1.95', 1, 1, NULL, '1', '2020-11-08 00:56:01', '2020-11-08 00:56:33');
INSERT INTO `sys_group` VALUES (96, '12312', 95, '.1.95.96', 1, 0, NULL, '1', '2020-11-08 00:56:25', '2020-11-08 00:56:31');
INSERT INTO `sys_group` VALUES (97, '公司管理', 3, '.1.3.97', 1, 0, NULL, '0', '2020-11-08 00:57:16', '2020-11-08 00:57:16');
INSERT INTO `sys_group` VALUES (98, '小组管理', 94, '.1.2.94.98', 1, 0, NULL, '0', '2020-11-08 00:59:50', '2020-11-08 00:59:50');
INSERT INTO `sys_group` VALUES (99, NULL, 94, '.1.2.94.99', 1, 0, NULL, '1', '2020-11-08 12:48:54', '2020-11-08 12:49:06');
INSERT INTO `sys_group` VALUES (100, NULL, 94, '.1.2.94.100', 1, 0, NULL, '1', '2020-11-08 12:48:57', '2020-11-08 12:49:09');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL COMMENT 'hrID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) NULL DEFAULT 1,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `remove_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0-未删除 1-已删除',
  `create_at` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '分组id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (12312341213123, '系统管理员', '18568887789', '029-82881234', '深圳南山', 1, 'admin1', '123', 'http://thirdwx.qlogo.cn/mmopen/icGs6iaDL9HHcibh7DLgu4b1CCD9Cnfa5VIqibgHC66tN6hrJSo4noK73GZlyruDbicaD4EK6UQuc8ibOdMyE1rKA1YNdO7pWkZBics/132', 'ces', '0', '0', '2020-11-08 02:01:49', 1);
INSERT INTO `user_info` VALUES (12312341243123, '系统管理员', '18568887789', '029-82881234', '深圳南山', 1, 'admin', '123', 'http://thirdwx.qlogo.cn/mmopen/icGs6iaDL9HHcibh7DLgu4b1CCD9Cnfa5VIqibgHC66tN6hrJSo4noK73GZlyruDbicaD4EK6UQuc8ibOdMyE1rKA1YNdO7pWkZBics/132', 'ces', '0', '0', '2020-11-08 02:01:49', 1);
INSERT INTO `user_info` VALUES (123123411213123, '系统管理员', '18568887789', '029-82881234', '深圳南山', 1, 'admin12', '123', 'http://thirdwx.qlogo.cn/mmopen/icGs6iaDL9HHcibh7DLgu4b1CCD9Cnfa5VIqibgHC66tN6hrJSo4noK73GZlyruDbicaD4EK6UQuc8ibOdMyE1rKA1YNdO7pWkZBics/132', 'ces', '0', '0', '2020-11-08 02:01:49', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(11) NOT NULL,
  `user_id` bigint(11) NULL DEFAULT NULL,
  `rid` bigint(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 12312341243123, 6);

SET FOREIGN_KEY_CHECKS = 1;
