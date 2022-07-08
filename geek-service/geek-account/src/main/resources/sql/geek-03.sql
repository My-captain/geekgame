/*
 Navicat Premium Data Transfer

 Source Server         : 本机使用
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : geek-03

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 26/06/2022 12:54:36
*/
use `geek-03`;
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
INSERT INTO `tb_account2020` VALUES ('03202001010001', 'zhen11', '03', '2020', '2020-01-01 23:48:45', 'CNY', 10010.00, '2022-06-26 02:56:38');
INSERT INTO `tb_account2020` VALUES ('03202001010002', 'zhen12', '03', '2020', '2020-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:15:25');

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
INSERT INTO `tb_account2021` VALUES ('03202101010001', 'zhen21', '03', '2021', '2021-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:15:34');
INSERT INTO `tb_account2021` VALUES ('03202101010002', 'zhen22', '03', '2021', '2021-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:15:39');

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
INSERT INTO `tb_account2022` VALUES ('03202201010001', 'zhen31', '03', '2022', '2022-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:15:57');
INSERT INTO `tb_account2022` VALUES ('03202201010002', 'zhen32', '03', '2022', '2022-01-01 23:48:45', 'CNY', 10000.00, '2022-06-26 00:16:01');
INSERT INTO `tb_account2022` VALUES ('03202206260001', '对公账户户', '03', '2022', '2022-06-26 03:37:01', 'CNY', 1001.00, '2022-06-26 03:37:01');
INSERT INTO `tb_account2022` VALUES ('03202206260002', '对公账户户1', '03', '2022', '2022-06-26 03:41:04', 'CNY', 10000.00, '2022-06-26 03:41:04');
INSERT INTO `tb_account2022` VALUES ('03202206260003', '对公账户户122', '03', '2022', '2022-06-26 03:48:54', 'CNY', 10000.00, '2022-06-26 03:48:54');
INSERT INTO `tb_account2022` VALUES ('03202206260004', '对公账户234', '03', '2022', '2022-06-26 12:50:29', 'CNY', 10000.00, '2022-06-26 12:50:29');

SET FOREIGN_KEY_CHECKS = 1;
