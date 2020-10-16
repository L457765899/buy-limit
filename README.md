# buy-limit
优惠限购功能

```she
容器方式运行，不用下载代码
1.将docker-compose目录拷贝到有docker-compose环境的机器上
2.直接使用在docker-compose目录下执行 docker-compose up -d 就可直接运行

开发工具idea中运行
1.需要手动创建下面的数据库表
2.使用maven命令运行 mvn spring-boot:run
```

提供一个简单的测试页面 http://localhost:8080/

![测试页面](1.png)

数据库使用mysql，建表语句如下

```sql
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
```

