DROP database if exists test_orders_01;
CREATE database test_orders_01 character SET utf8mb4;

USE test_orders_01;

CREATE TABLE `orders`
(
    `id`      int(11) DEFAULT NULL AUTO_INCREMENT COMMENT '订单编号',
    `user_id` int(16) DEFAULT NULL COMMENT '用户编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';



DROP database if exists test_orders_02;
CREATE database test_orders_02 character SET utf8mb4;

USE test_orders_02;

CREATE TABLE `orders`
(
    `id`      int(11) DEFAULT NULL AUTO_INCREMENT COMMENT '订单编号',
    `user_id` int(16) DEFAULT NULL COMMENT '用户编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

ALTER TABLE `test_orders`.`orders` ADD COLUMN `create_time` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT NOW() AFTER `user_id`;
ALTER TABLE `test_orders_01`.`orders` ADD COLUMN `create_time` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT NOW() AFTER `user_id`;
ALTER TABLE `test_orders_02`.`orders` ADD COLUMN `create_time` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT NOW() AFTER `user_id`;


CREATE TABLE `users`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
    `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
)ENGINE=InnoDB AUTO_INCREMENT= 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';
