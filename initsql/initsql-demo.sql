CREATE SCHEMA IF NOT EXISTS druid_demo_1;
CREATE SCHEMA IF NOT EXISTS druid_demo_1_slave;
CREATE SCHEMA IF NOT EXISTS druid_demo_1_slave_2;
CREATE SCHEMA IF NOT EXISTS druid_demo_2;
CREATE SCHEMA IF NOT EXISTS jiguang;

use druid_demo_1;

DROP TABLE IF EXISTS `ORDER1`;
CREATE TABLE `ORDER1` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ORDER2
-- ----------------------------
DROP TABLE IF EXISTS `ORDER2`;
CREATE TABLE `ORDER2` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` bigint(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use druid_demo_1_slave;

DROP TABLE IF EXISTS `ORDER1`;
CREATE TABLE `ORDER1` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ORDER2
-- ----------------------------
DROP TABLE IF EXISTS `ORDER2`;
CREATE TABLE `ORDER2` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` bigint(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use druid_demo_1_slave_2;

DROP TABLE IF EXISTS `ORDER1`;
CREATE TABLE `ORDER1` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ORDER2
-- ----------------------------
DROP TABLE IF EXISTS `ORDER2`;
CREATE TABLE `ORDER2` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` bigint(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use druid_demo_2;

DROP TABLE IF EXISTS `ORDER1`;
CREATE TABLE `ORDER1` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ORDER2
-- ----------------------------
DROP TABLE IF EXISTS `ORDER2`;
CREATE TABLE `ORDER2` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ORDER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` bigint(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use jiguang;

DROP TABLE IF EXISTS `db_route`;
CREATE TABLE `DB_ROUTE` (
  `USER_ID` bigint(20) NOT NULL,
  `DS` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;