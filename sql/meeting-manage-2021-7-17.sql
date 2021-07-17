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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='设备信息';

-- 正在导出表  meeting_manage.m_device 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `m_device` DISABLE KEYS */;
INSERT INTO `m_device` (`id`, `name`, `type`, `status`) VALUES
	(1, '投影仪P001', 0, 3),
	(2, '白板B001', 0, 2),
	(3, '话筒H001', 1, 1),
	(4, '话筒H002', 1, 1);
/*!40000 ALTER TABLE `m_device` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_meeting_device 结构
CREATE TABLE IF NOT EXISTS `m_meeting_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) NOT NULL COMMENT '设备ID',
  `meeting_id` bigint(20) NOT NULL COMMENT '会议ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='会议与设备对应关系';

-- 正在导出表  meeting_manage.m_meeting_device 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `m_meeting_device` DISABLE KEYS */;
INSERT INTO `m_meeting_device` (`id`, `device_id`, `meeting_id`) VALUES
	(2, 3, 5),
	(3, 4, 5),
	(12, 3, 10),
	(13, 3, 11);
/*!40000 ALTER TABLE `m_meeting_device` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_meeting_info 结构
CREATE TABLE IF NOT EXISTS `m_meeting_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会议ID',
  `room_id` bigint(20) NOT NULL COMMENT '会议室ID',
  `name` varchar(30) DEFAULT NULL COMMENT '会议名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '会议发起者ID',
  `create_time` datetime DEFAULT NULL COMMENT '会议请求发起时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='会议信息';

-- 正在导出表  meeting_manage.m_meeting_info 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `m_meeting_info` DISABLE KEYS */;
INSERT INTO `m_meeting_info` (`id`, `room_id`, `name`, `start_time`, `end_time`, `create_user_id`, `create_time`) VALUES
	(3, 2, '会议管理系统庆功会', '2021-07-19 09:00:00', '2021-07-19 10:00:00', 2, '2021-07-16 21:36:42'),
	(5, 2, '会议管理系统--设备管理', '2021-07-18 08:00:00', '2021-07-18 08:30:00', 1, '2021-07-17 11:20:23'),
	(10, 6, 'test', '2021-07-18 08:00:00', '2021-07-18 08:30:00', 1, '2021-07-17 11:30:26'),
	(11, 6, 'test1', '2021-07-18 09:00:00', '2021-07-18 09:30:00', 1, '2021-07-17 11:30:35');
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

-- 正在导出表  meeting_manage.m_room_device 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `m_room_device` DISABLE KEYS */;
INSERT INTO `m_room_device` (`room_id`, `device_id`) VALUES
	(2, 1);
/*!40000 ALTER TABLE `m_room_device` ENABLE KEYS */;

-- 导出  表 meeting_manage.m_user_meeting 结构
CREATE TABLE IF NOT EXISTS `m_user_meeting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '员工工号',
  `meeting_id` bigint(20) DEFAULT NULL COMMENT '会议ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='员工与会议对应关系';

-- 正在导出表  meeting_manage.m_user_meeting 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `m_user_meeting` DISABLE KEYS */;
INSERT INTO `m_user_meeting` (`id`, `uid`, `meeting_id`) VALUES
	(18, 1001, 5),
	(19, 1002, 5),
	(20, 0, 5),
	(33, 1001, 10),
	(34, 0, 10),
	(35, 1001, 11),
	(36, 0, 11),
	(38, 1001, 3),
	(39, 2019006410, 3);
/*!40000 ALTER TABLE `m_user_meeting` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_captcha 结构
CREATE TABLE IF NOT EXISTS `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';

-- 正在导出表  meeting_manage.sys_captcha 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
INSERT INTO `sys_captcha` (`uuid`, `code`, `expire_time`) VALUES
	('8bb2e424-794e-4f2d-8ea6-30a3ab23c32f', '63gnp', '2021-07-17 12:25:09');
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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

-- 正在导出表  meeting_manage.sys_menu 的数据：~28 rows (大约)
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
	(81, 0, '设备管理', 'meeting/device', '', 1, 'config', 2),
	(82, 81, '查看', '', 'meeting:device:list,meeting:device:info', 2, 'config', 3),
	(83, 81, '新增', '', 'meeting:device:save', 2, 'config', 3),
	(84, 81, '修改', '', 'meeting:device:update', 2, 'config', 3),
	(85, 81, '删除', '', 'meeting:device:delete', 2, 'config', 3),
	(86, 0, '会议预定', 'meeting/appointment', 'sys:user:select,meeting:room:recommend,sys:device:select,sys:room:info,meeting:device:select', 1, 'tixing', 4),
	(87, 0, '我发起的会议', 'meeting/meeting', '', 1, 'pinglun', 3);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- 正在导出表  meeting_manage.sys_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES
	(1, '系统管理员', '所有权限', 1, NULL),
	(5, '员工', '员工角色', 1, NULL),
	(6, '员工', '员工角色', 1, '2021-07-16 15:19:55');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';

-- 正在导出表  meeting_manage.sys_role_menu 的数据：~35 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES
	(108, 6, 86),
	(109, 6, 87),
	(110, 6, -666666),
	(111, 5, 86),
	(112, 5, 87),
	(113, 5, -666666),
	(114, 1, 1),
	(115, 1, 2),
	(116, 1, 5),
	(117, 1, 6),
	(118, 1, 7),
	(119, 1, 8),
	(120, 1, 3),
	(121, 1, 9),
	(122, 1, 10),
	(123, 1, 11),
	(124, 1, 12),
	(125, 1, 4),
	(126, 1, 13),
	(127, 1, 14),
	(128, 1, 15),
	(129, 1, 16),
	(130, 1, 68),
	(131, 1, 72),
	(132, 1, 73),
	(133, 1, 74),
	(134, 1, 75),
	(135, 1, 81),
	(136, 1, 82),
	(137, 1, 83),
	(138, 1, 84),
	(139, 1, 85),
	(140, 1, 86),
	(141, 1, 87),
	(142, 1, -666666);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- 正在导出表  meeting_manage.sys_user 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `uid`, `username`, `name`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES
	(1, 0, 'admin', '管理员', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@tyut4113.org', '18835497385', 1, 1, '2021-07-13 11:11:11'),
	(2, 2019006410, 'klenkiven', '王子龙', 'c66c54dc7a192d8ee9a3b776ce013154ec48f7805a71aaf0cad44ea522e742fa', 'blXKolMjxavxpWYVNHRN', 'wzl709@outlook.com', '18835497385', 1, 1, '2021-07-16 09:04:42'),
	(16, 1001, 'zhangsan', '张三', '0212f7ffec2bcc264fd396ed003f6b69ab30355c112dd3dcc52ea1b5103eb1bd', 'sTtqECGgoKhRIjIfhblW', 'zhangsan@tyut4113.org', '12312341234', 1, 2, '2021-07-16 15:36:52'),
	(17, 1002, 'lisi', '李四', '455cac37d9db4c9d28fa5dfa3fc7b0dc9cf2f7676595b683b4acff851adfb5c8', 'JMujVruNUIexJTbsBIYX', 'lisi@qq.com', '12312341234', 1, 2, '2021-07-16 15:51:14');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 meeting_manage.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

-- 正在导出表  meeting_manage.sys_user_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES
	(15, 2, 1),
	(16, 16, 6),
	(17, 17, 6);
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

-- 正在导出表  meeting_manage.sys_user_token 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` (`user_id`, `token`, `expire_time`, `update_time`) VALUES
	(1, 'e57077a840ffceb34f026c6d0cf79db9', '2021-07-18 00:17:09', '2021-07-17 12:17:09'),
	(2, '01c02b5bda2b9d3ca76b69f632737c40', '2021-07-18 00:02:29', '2021-07-17 12:02:29'),
	(16, '586811a5e0294b756a648a6c6e21d570', '2021-07-18 00:15:58', '2021-07-17 12:15:58');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
