/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.227.1
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 192.168.227.1:3306
 Source Schema         : buy

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/10/2020 19:46:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE `buy` CHARACTER SET 'utf8mb4';

USE buy;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id自增',
  `name` varchar(32) NOT NULL COMMENT '商品名字',
  `stock_count` int(11) NOT NULL COMMENT '库存数量',
  `special_count` int(11) NOT NULL COMMENT '特价商品数量',
  `user_discount_count` int(11) NOT NULL COMMENT '前几个用户享受优惠',
  `saled_count` int(11) NOT NULL COMMENT '已售数量',
  `price` int(11) NOT NULL COMMENT '原价价格',
  `special_price` int(11) NOT NULL COMMENT '特价价格',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

CREATE TABLE `buy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购买信息id',
  `user_id` int(11) NOT NULL COMMENT '关联用户id',
  `goods_id` int(11) NOT NULL COMMENT '关联商品id',
  `count` int(11) NOT NULL COMMENT '用户已购买数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `buy_user_goods_index` (`user_id`,`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户购买商品的数量';

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id自增',
  `user_id` int(11) NOT NULL COMMENT '关联用户id',
  `goods_id` int(11) NOT NULL COMMENT '关联商品id',
  `info` varchar(64) NOT NULL COMMENT '订单快照信息',
  `all_count` int(11) NOT NULL COMMENT '总购买数量all_count=special_count+original_count',
  `special_count` int(11) NOT NULL COMMENT '特价商品数量',
  `original_count` int(11) NOT NULL COMMENT '原价商品数量',
  `all_price` int(11) NOT NULL COMMENT '总价格',
  PRIMARY KEY (`id`),
  KEY `order_user_id_index` (`user_id`),
  KEY `order_goods_id_index` (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

INSERT INTO `goods`(name, stock_count, special_count, user_discount_count, saled_count, price, special_price) VALUES ('测试商品A', 100, 20, 3, 0, 10, 6);
INSERT INTO `goods`(name, stock_count, special_count, user_discount_count, saled_count, price, special_price) VALUES ('测试商品B', 100, 20, 3, 0, 10, 6);

SET FOREIGN_KEY_CHECKS = 1;
