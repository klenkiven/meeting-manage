-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.5.5-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 meeting_manage 的数据库结构
CREATE DATABASE IF NOT EXISTS `meeting_manage` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `meeting_manage`;

-- 导出  表 meeting_manage.m_device 结构
CREATE TABLE IF NOT EXISTS `m_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `name` varchar(30) DEFAULT NULL COMMENT '设备名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型  0：固定   1：流动',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：占用   1：可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备信息';

-- 正在导出表  meeting_manage.m_device 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `m_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_device` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_meeting_device 结构
CREATE TABLE IF NOT EXISTS `m_meeting_device` (
  `meeting_id` bigint(20) NOT NULL COMMENT '会议ID',
  `device_id` bigint(20) NOT NULL COMMENT '设备ID',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会议与设备对应关系';

-- 正在导出表  meeting_manage.m_meeting_device 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `m_meeting_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_meeting_device` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_meeting_info 结构
CREATE TABLE IF NOT EXISTS `m_meeting_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会议ID',
  `room_id` bigint(20) NOT NULL COMMENT '会议室ID',
  `name` varchar(30) DEFAULT NULL COMMENT '会议名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '会议发起者ID',
  `create_time` bigint(20) DEFAULT NULL COMMENT '会议请求发起时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会议信息';

-- 正在导出表  meeting_manage.m_meeting_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `m_meeting_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_meeting_info` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_room 结构
CREATE TABLE IF NOT EXISTS `m_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会议室ID',
  `name` varchar(30) DEFAULT NULL COMMENT '会议室名称',
  `capacity` int(11) DEFAULT NULL COMMENT '容量',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='会议室信息';

-- 正在导出表  meeting_manage.m_room 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `m_room` DISABLE KEYS */;
INSERT INTO `m_room` (`id`, `name`, `capacity`, `status`) VALUES
	(1, '行勉楼A1', 100, 1),
	(2, '行勉楼A301', 10, 1),
	(5, '行知楼A218', 50, 0),
	(6, '软件学院楼301', 20, 1);
/*!40000 ALTER TABLE `m_room` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_room_device 结构
CREATE TABLE IF NOT EXISTS `m_room_device` (
  `room_id` bigint(20) NOT NULL COMMENT '会议室ID',
  `device_id` bigint(20) NOT NULL COMMENT '设备ID',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会议室与设备对应关系';

-- 正在导出表  meeting_manage.m_room_device 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `m_room_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_room_device` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_user_meeting 结构
CREATE TABLE IF NOT EXISTS `m_user_meeting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '员工工号',
  `meeting_id` bigint(20) DEFAULT NULL COMMENT '会议ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工与会议对应关系';

-- 正在导出表  meeting_manage.m_user_meeting 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `m_user_meeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_user_meeting` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_captcha 结构
CREATE TABLE IF NOT EXISTS `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';

-- 正在导出表  meeting_manage.sys_captcha 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_captcha` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

-- 正在导出表  meeting_manage.sys_menu 的数据：~27 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES
	(1, 0, '系统管理', NULL, NULL, 0, 'system', 0),
	(2, 1, '员工信息管理', 'sys/user', NULL, 1, 'admin', 1),
	(3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2),
	(4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3),
	(5, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0),
	(6, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0),
	(7, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0),
	(8, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0),
	(9, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0),
	(10, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0),
	(11, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0),
	(12, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0),
	(13, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0),
	(14, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0),
	(15, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0),
	(16, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0),
	(68, 0, '会议室管理', 'meeting/room', '', 1, 'shouye', 1),
	(72, 68, '查看', '', 'meeting:room:list,meeting:room:info', 2, '', 0),
	(73, 68, '新增', '', 'meeting:room:save,meeting:device:select', 2, '', 0),
	(74, 68, '修改', '', 'meeting:room:update', 2, '', 0),
	(75, 68, '删除', '', 'meeting:room:delete', 2, '', 0),
	(81, 0, '设备管理', 'meeting/device', '', 1, 'config', 3),
	(82, 81, '查看', '', 'meeting:device:list,meeting:device:info', 2, 'config', 3),
	(83, 81, '新增', '', 'meeting:device:save', 2, 'config', 3),
	(84, 81, '修改', '', 'meeting:device:update', 2, 'config', 3),
	(85, 81, '删除', '', 'meeting:device:delete', 2, 'config', 3),
	(86, 0, '会议预定', 'meeting/appointment', '', 1, 'tixing', 3);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- 正在导出表  meeting_manage.sys_role 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES
	(1, '系统管理员', '所有权限', 1, NULL),
	(2, '会议室管理员', '会议室管理员', 1, NULL),
	(3, '员工管理员', '员工管理员', 1, NULL),
	(5, '员工', '员工角色', 1, NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';

-- 正在导出表  meeting_manage.sys_role_menu 的数据：~26 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES
	(21, 3, -666666),
	(22, 2, 68),
	(23, 2, -666666),
	(52, 1, 1),
	(53, 1, 2),
	(54, 1, 5),
	(55, 1, 6),
	(56, 1, 7),
	(57, 1, 8),
	(58, 1, 3),
	(59, 1, 9),
	(60, 1, 10),
	(61, 1, 11),
	(62, 1, 12),
	(63, 1, 4),
	(64, 1, 13),
	(65, 1, 14),
	(66, 1, 15),
	(67, 1, 16),
	(68, 1, 68),
	(69, 1, 72),
	(70, 1, 73),
	(71, 1, 74),
	(72, 1, 75),
	(78, 1, 81),
	(79, 1, -666666);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '工号',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `sys_user_uid_uindex` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- 正在导出表  meeting_manage.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `uid`, `username`, `name`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES
	(1, 0, 'admin', '管理员', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@tyut4113.org', '18835497385', 1, 1, '2021-07-13 11:11:11'),
	(2, 2019006410, 'klenkiven', '王子龙', 'c66c54dc7a192d8ee9a3b776ce013154ec48f7805a71aaf0cad44ea522e742fa', 'blXKolMjxavxpWYVNHRN', 'wzl709@outlook.com', '18835497385', 1, 1, '2021-07-16 09:04:42');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

-- 正在导出表  meeting_manage.sys_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES
	(14, 2, 1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_user_token 结构
CREATE TABLE IF NOT EXISTS `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';

-- 正在导出表  meeting_manage.sys_user_token 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` (`user_id`, `token`, `expire_time`, `update_time`) VALUES
	(1, '6d40efe955027a804aa66f768b95224b', '2021-07-17 02:12:23', '2021-07-16 14:12:23');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
