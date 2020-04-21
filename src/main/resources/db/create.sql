DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `user_group`;
DROP TABLE IF EXISTS `message`;
DROP TABLE IF EXISTS `message_user`;

CREATE TABLE `user`(
  user_id bigint(20) NOT NULL COMMENT '用户id',
  user_name varchar(255) NOT NULL COMMENT '用户名称',
  phone bigint(20) NOT NULL COMMENT '手机号',
  primary key (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_group`(
  group_id bigint(20) NOT NULL COMMENT '用户id',
  user_id bigint(20) NOT NULL COMMENT '用户id',
  primary key (`group_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `message`(
  message_id bigint(20) NOT NULL COMMENT '消息id',
  message varchar(255) NOT NULL COMMENT '消息内容',
  message_type tinyint(2) NOT NULL COMMENT '消息类型，1:系统消息；2:群发消息',
  primary key (`message_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `message_user`(
  user_id bigint(20) NOT NULL COMMENT '用户id',
  message_id bigint(20) NOT NULL COMMENT '消息id',
  primary key (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


