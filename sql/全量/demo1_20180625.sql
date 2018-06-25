/*
Navicat MySQL Data Transfer

Source Server         : loc
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : demo1

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-06-25 14:57:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(100) DEFAULT '' COMMENT '用户姓名',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '123456', null, '2018-06-25 10:50:06', 'admin', '2018-06-25 10:50:14', 'admin');
INSERT INTO `sys_user` VALUES ('2', 'test', 'test', '123456', null, '2018-06-25 10:50:32', 'admin', '2018-06-25 10:50:38', 'admin');
