/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : gym

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 02/06/2022 15:51:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
qq:2012236442,24小时在线，欢迎联系

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser`  (
  `adminId` int NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adminPassword` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for chongzhi
-- ----------------------------
DROP TABLE IF EXISTS `chongzhi`;
CREATE TABLE `chongzhi`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Memberid` int NULL DEFAULT NULL,
  `Typeid` int NULL DEFAULT NULL,
  `Money` int NULL DEFAULT NULL,
  `ssmoney` int NULL DEFAULT NULL,
  `zlmoney` int NULL DEFAULT NULL,
  `Date` datetime NULL DEFAULT NULL,
  `czjine` int NULL DEFAULT NULL,
  `beizhu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `czStatic` int NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for coach
-- ----------------------------
DROP TABLE IF EXISTS `coach`;
CREATE TABLE `coach`  (
  `coachId` int NOT NULL AUTO_INCREMENT,
  `coachName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coachPhone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coachSex` int NULL DEFAULT NULL,
  `CoachAge` int NULL DEFAULT NULL,
  `CoachData` date NULL DEFAULT NULL,
  `Teach` int NULL DEFAULT NULL,
  `CoachWages` double NULL DEFAULT NULL,
  `CoachAddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CoachStatic` int NULL DEFAULT 0,
  PRIMARY KEY (`coachId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `eqId` int NOT NULL AUTO_INCREMENT,
  `eqName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eqText` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eqId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goodinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodinfo`;
CREATE TABLE `goodinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodsid` int NULL DEFAULT NULL,
  `memberid` int NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goodsId` int NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unitPrice` double NULL DEFAULT NULL,
  `sellPrice` double NULL DEFAULT NULL,
  `inventory` int NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`goodsId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for loos
-- ----------------------------
DROP TABLE IF EXISTS `loos`;
CREATE TABLE `loos`  (
  `loosId` int NOT NULL AUTO_INCREMENT,
  `loosName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loosImages` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loosAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loosjdate` datetime NULL DEFAULT NULL,
  `loosStatus` int NULL DEFAULT NULL,
  `scavenger` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scavengerPhone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReceiveName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ReceivePhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loosldate` datetime NULL DEFAULT NULL,
  `admin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`loosId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `MemberId` int NOT NULL AUTO_INCREMENT,
  `MemberName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MemberPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MemberSex` int NULL DEFAULT NULL,
  `MemberAge` int NULL DEFAULT NULL,
  `MemberTypes` int NULL DEFAULT NULL,
  `NenberDate` date NULL DEFAULT NULL,
  `Birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `memberStatic` int NULL DEFAULT NULL,
  `Memberbalance` float NULL DEFAULT 0,
  `Memberxufei` date NULL DEFAULT NULL,
  PRIMARY KEY (`MemberId`) USING BTREE,
  INDEX `fk-member-membertype`(`MemberTypes`) USING BTREE,
  CONSTRAINT `fk-member-membertype` FOREIGN KEY (`MemberTypes`) REFERENCES `membertype` (`TypeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for membertype
-- ----------------------------
DROP TABLE IF EXISTS `membertype`;
CREATE TABLE `membertype`  (
  `TypeId` int NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypeciShu` int NULL DEFAULT NULL,
  `TypeDay` int NULL DEFAULT NULL,
  `Typemoney` float NULL DEFAULT NULL,
  PRIMARY KEY (`TypeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for privatecoachinfo
-- ----------------------------
DROP TABLE IF EXISTS `privatecoachinfo`;
CREATE TABLE `privatecoachinfo`  (
  `pid` int NOT NULL AUTO_INCREMENT,
  `memberid` int NULL DEFAULT NULL,
  `coachid` int NULL DEFAULT NULL,
  `subjectid` int NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL,
  `countprice` double NULL DEFAULT NULL,
  `realprice` double NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'asdf',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `fk_pri_subject`(`subjectid`) USING BTREE,
  INDEX `fk_pri_coach`(`coachid`) USING BTREE,
  INDEX `fk_pri_member`(`memberid`) USING BTREE,
  CONSTRAINT `fk_pri_coach` FOREIGN KEY (`coachid`) REFERENCES `coach` (`coachId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pri_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`MemberId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pri_subject` FOREIGN KEY (`subjectid`) REFERENCES `subject` (`subId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subId` int NOT NULL AUTO_INCREMENT,
  `subname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sellingPrice` double NULL DEFAULT NULL,
  PRIMARY KEY (`subId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
