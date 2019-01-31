/*
 Navicat Premium Data Transfer

 Source Server         : 172.27.103.24
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 172.27.103.24:3306
 Source Schema         : policy

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 31/01/2019 16:27:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cron
-- ----------------------------
DROP TABLE IF EXISTS `cron`;
CREATE TABLE `cron`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `cron_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态1 有效 0无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for sme_policy_spider
-- ----------------------------
DROP TABLE IF EXISTS `sme_policy_spider`;
CREATE TABLE `sme_policy_spider`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '政策标题',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '政策内容，可能存储整个div带HTMl标记的内容',
  `strip_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简化的政策内容',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '成文时间',
  `publish_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文单位',
  `publish_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文字号',
  `publish_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文链接',
  `from_site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转载网站',
  `from_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转载网站地址',
  `forward_time` datetime(0) NULL DEFAULT NULL COMMENT '转载时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录生成时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录更新时间',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政策类别',
  `attachment` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件情况，如果有附件，此处存储附件的链接地址',
  `spider_module` smallint(5) NULL DEFAULT NULL COMMENT '爬取模块记录，1最新政策原文，2政策解读，3行业热点，4地方政府，5部位动态，6焦点新闻，7新闻各区',
  `article_reading` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '如果是模块为政策原文，且有政策解读的，填写政策原文对应的解读的题目',
  `policy_level` smallint(5) NULL DEFAULT NULL COMMENT '政策级别，0国家级，1市级，2区级',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
