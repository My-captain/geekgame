/*
 Navicat Premium Data Transfer

 Source Server         : 本机使用
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : geek-01

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 26/06/2022 12:54:23
*/
use `geek-01`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_account2020
-- ----------------------------
DROP TABLE IF EXISTS `tb_account2020`;
CREATE TABLE `tb_account2020`  (
  `account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户：类型+日期+5位流水号',
  `owner` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户人',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户类型：01-个人账户，02-个人定期，03-对公户',
  `year` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开立年份',
  `open_time` timestamp(0) NULL DEFAULT NULL COMMENT '开立时间',
  `currency` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'CNY' COMMENT '币种',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_account2020
-- ----------------------------
INSERT INTO `tb_account2020` VALUES ('01202001010001', 'manbu11', '01', '2020', '2020-01-01 23:48:45', 'CNY', 9990.00, '2022-06-26 02:56:38');
INSERT INTO `tb_account2020` VALUES ('01202001010002', 'manbu12', '01', '2020', '2020-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:14:39');

-- ----------------------------
-- Table structure for tb_account2021
-- ----------------------------
DROP TABLE IF EXISTS `tb_account2021`;
CREATE TABLE `tb_account2021`  (
  `account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户：类型+日期+5位流水号',
  `owner` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户人',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户类型：01-个人账户，02-个人定期，03-对公户',
  `year` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开立年份',
  `open_time` timestamp(0) NULL DEFAULT NULL COMMENT '开立时间',
  `currency` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'CNY' COMMENT '币种',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_account2021
-- ----------------------------
INSERT INTO `tb_account2021` VALUES ('01202101010001', 'manbu21', '01', '2021', '2021-01-01 23:48:45', 'CNY', 9999.90, '2022-06-26 02:51:13');
INSERT INTO `tb_account2021` VALUES ('01202101010002', 'manbu22', '01', '2021', '2021-01-01 23:48:45', 'CNY', 10000.10, '2022-06-26 02:51:13');

-- ----------------------------
-- Table structure for tb_account2022
-- ----------------------------
DROP TABLE IF EXISTS `tb_account2022`;
CREATE TABLE `tb_account2022`  (
  `account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户：类型+日期+5位流水号',
  `owner` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户人',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户类型：01-个人账户，02-个人定期，03-对公户',
  `year` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开立年份',
  `open_time` timestamp(0) NULL DEFAULT NULL COMMENT '开立时间',
  `currency` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'CNY' COMMENT '币种',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_account2022
-- ----------------------------
INSERT INTO `tb_account2022` VALUES ('01202201010001', 'manbu31', '01', '2022', '2022-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:14:02');
INSERT INTO `tb_account2022` VALUES ('01202201010002', 'manbu32', '01', '2022', '2022-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:14:06');
INSERT INTO `tb_account2022` VALUES ('01202206260001', '个人私户01', '01', '2022', '2022-06-26 03:33:01', 'CNY', 100.00, '2022-06-26 03:33:01');
INSERT INTO `tb_account2022` VALUES ('01202206260011', '个人私户01', '01', '2022', '2022-06-26 03:36:27', 'CNY', 100.00, '2022-06-26 03:37:33');

SET FOREIGN_KEY_CHECKS = 1;
