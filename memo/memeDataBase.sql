drop database if exists meme;
create database if not exists memo default character set utf8 collate utf8_general_ci;

use memo;

drop table if exists memo_data;
-- 便签数据
create table if not exists memo_data(
  id int primary key auto_increment comment '便签编号',
  group_id int not null comment '便签组编号',
  title varchar(32) not null comment '便签标题',
  content varchar(1024) default '记录美好一天，从三言两语开始' comment '便签内容',
  is_protected char(1) not null default '0' comment '是否私密，0：私密，1：公开',
  backgroup enum('WHITE', 'RED', 'BLUE', 'GREEN', 'YELLOW', 'ORANGE') default 'WHITE' comment '便签背景颜色',
  is_remind datetime comment '设置提醒时间',
  createdTime datetime not null comment '创建时间',
  modify_time timestamp comment '最近一次的修改时间'
)engine innodb;

-- 插入默认存在的欢迎便签
insert into memo_data(id, group_id, title, content, backgroup, createdTime) value(
  1, 1, '使用手册', '本条便签介绍如何使用便签系统...', 'WHITE', now()
);

-- 标签分享时的提示信息模板数据
drop table if exists memo_share;
create table if not exists memo_share(
  id int primary key auto_increment comment '分享便签的编号',
  info_id int comment '便签实际的编号',
  mark varchar(32) comment '分享时的备注信息',
  shar_time datetime comment '分享时间'
)engine innodb;

-- 添加一条默认的分享信息
insert into memo_share values (
  1,
  1,
  '特别有意思的一款便签APP',
   now()
);