/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 08/05/2018 16:38:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `age` int(10) NULL DEFAULT NULL,
  `birth_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, 'jane', 16, '2018-01-04');
INSERT INTO `person` VALUES (8, '34', 423, '2018-05-08');
INSERT INTO `person` VALUES (9, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (34, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (35, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (36, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (37, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (38, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (39, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (40, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (41, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (42, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (43, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (44, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (45, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (46, 'gavin', 12, NULL);
INSERT INTO `person` VALUES (47, 'gavin', 12, NULL);

SET FOREIGN_KEY_CHECKS = 1;
