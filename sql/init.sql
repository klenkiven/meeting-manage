CREATE DATABASE meeting_manage;
USE meeting_manage;

-- 菜单
CREATE TABLE `sys_menu` (
                            `menu_id` bigint NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint COMMENT '父菜单ID，一级菜单为0',
                            `name` varchar(50) COMMENT '菜单名称',
                            `url` varchar(200) COMMENT '菜单URL',
                            `perms` varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `type` int COMMENT '类型   0：目录   1：菜单   2：按钮',
                            `icon` varchar(50) COMMENT '菜单图标',
                            `order_num` int COMMENT '排序',
                            PRIMARY KEY (`menu_id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='菜单管理';

-- 系统用户
CREATE TABLE `sys_user` (
                            `user_id` bigint NOT NULL AUTO_INCREMENT,
                            `uid` bigint NOT NULL COMMENT '工号',
                            `username` varchar(50) NOT NULL COMMENT '用户名',
                            `name` varchar(50) COMMENT '姓名',
                            `password` varchar(100) COMMENT '密码',
                            `salt` varchar(20) COMMENT '盐',
                            `email` varchar(100) COMMENT '邮箱',
                            `mobile` varchar(100) COMMENT '手机号',
                            `status` tinyint COMMENT '状态  0：禁用   1：正常',
                            `create_user_id` bigint(20) COMMENT '创建者ID',
                            `create_time` datetime COMMENT '创建时间',
                            PRIMARY KEY (`user_id`),
                            UNIQUE INDEX (`username`),
                            UNIQUE INDEX (`uid`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='系统用户';

-- 系统用户Token
CREATE TABLE `sys_user_token` (
                                  `user_id` bigint(20) NOT NULL,
                                  `token` varchar(100) NOT NULL COMMENT 'token',
                                  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
                                  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`user_id`),
                                  UNIQUE KEY `token` (`token`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='系统用户Token';

-- 系统验证码
CREATE TABLE `sys_captcha` (
                               `uuid` char(36) NOT NULL COMMENT 'uuid',
                               `code` varchar(6) NOT NULL COMMENT '验证码',
                               `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
                               PRIMARY KEY (`uuid`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='系统验证码';

-- 角色
CREATE TABLE `sys_role` (
                            `role_id` bigint NOT NULL AUTO_INCREMENT,
                            `role_name` varchar(100) COMMENT '角色名称',
                            `remark` varchar(100) COMMENT '备注',
                            `create_user_id` bigint(20) COMMENT '创建者ID',
                            `create_time` datetime COMMENT '创建时间',
                            PRIMARY KEY (`role_id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint COMMENT '用户ID',
                                 `role_id` bigint COMMENT '角色ID',
                                 PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_menu` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `role_id` bigint COMMENT '角色ID',
                                 `menu_id` bigint COMMENT '菜单ID',
                                 PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='角色与菜单对应关系';

-- ----------------------------------------------------------------------- --
--                                  会议室管理                               --
-- ----------------------------------------------------------------------- --

-- 会议室信息
CREATE TABLE `m_room` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '会议室ID',
                          `name` varchar(30) COMMENT '会议室名称',
                          `capacity` int COMMENT '容量',
                          `status` tinyint COMMENT '状态  0：禁用   1：正常',
                          PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='会议室信息';

-- 会议信息
CREATE TABLE `m_meeting_info` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '会议ID',
                          `room_id` bigint NOT NULL COMMENT '会议室ID',
                          `name` varchar(30) COMMENT '会议名称',
                          `start_time` datetime COMMENT '开始时间',
                          `end_time` datetime COMMENT '结束时间',
                          `create_user_id` bigint COMMENT '会议发起者ID',
                          `create_time` bigint COMMENT '会议请求发起时间',
                          PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='会议信息';

-- 设备信息
CREATE TABLE `m_device` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '设备ID',
                                  `name` varchar(30) COMMENT '设备名称',
                                  `type` tinyint COMMENT '类型  0：固定   1：流动',
                                  `status` tinyint COMMENT '状态  0：占用   1：可用',
                                  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='设备信息';

-- 员工与会议对应关系
CREATE TABLE `m_user_meeting` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `uid` bigint COMMENT '员工工号',
                                 `meeting_id` bigint COMMENT '会议ID',
                                 PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='员工与会议对应关系';

-- 会议室与设备对应关系
CREATE TABLE `m_room_device` (
                                    `room_id` bigint COMMENT '会议室ID',
                                    `device_id` bigint COMMENT '设备ID',
                                    PRIMARY KEY (`device_id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='会议室与设备对应关系';

-- 会议与设备对应关系
CREATE TABLE `m_meeting_device` (
                                  `meeting_id` bigint COMMENT '会议ID',
                                  `device_id` bigint COMMENT '设备ID',
                                  PRIMARY KEY (`device_id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='会议与设备对应关系';



-- 初始数据
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
