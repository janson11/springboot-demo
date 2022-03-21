DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id    BIGINT(20) NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);


CREATE TABLE `user`.`person`
(
    `id`      bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`    varchar(20) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '名字',
    `age`     bigint COMMENT '年龄',
    `address` varchar(100) CHARACTER SET utf8mb4 COMMENT '地址',
    PRIMARY KEY (`id`)
) COMMENT='个人表';

DELETE
FROM user;

INSERT INTO user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Tom', 28, 'test3@baomidou.com'),
       (4, 'Sandy', 21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');