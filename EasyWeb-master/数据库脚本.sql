/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.34-log : Database - easyweb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`easyweb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `easyweb`;

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(128) NOT NULL,
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `user_name` varchar(128) DEFAULT NULL,
  `client_id` varchar(128) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_access_token` */

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(128) DEFAULT NULL,
  `clientId` varchar(128) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_approvals` */

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `client_name` varchar(128) DEFAULT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `raw_client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端';

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`client_name`,`resource_ids`,`client_secret`,`raw_client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('397fd05f-3bfd-4205-a641-aaf0f8522744','管理后台','easyweb','$2a$10$OUkrfzhX8AwqSnTKFyoWKe9IPDkhkTDF/Bu39RvdAgoT7i3ID.3Ou','2777e2f6-60a7-4a92-b02b-be92b52ab763','DEFAULT','password,refresh_token,client_credentials','urn:ietf:wg:oauth:2.0:oob','CLIENT',NULL,NULL,'{}','');

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `authentication_id` varchar(128) NOT NULL,
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `user_name` varchar(128) DEFAULT NULL,
  `client_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_client_token` */

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(128) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_code` */

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_refresh_token` */

/*Table structure for table `sys_authorities` */

DROP TABLE IF EXISTS `sys_authorities`;

CREATE TABLE `sys_authorities` (
  `authority` varchar(128) NOT NULL COMMENT '授权标识',
  `authority_name` varchar(128) NOT NULL COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限';

/*Data for the table `sys_authorities` */

insert  into `sys_authorities`(`authority`,`authority_name`,`create_time`) values ('delete:/v1/authorities/role','移除角色权限','2018-12-19 23:10:15'),('delete:/v1/oauth/client/{clientId}','delete','2018-12-19 23:10:15'),('delete:/v1/role/{id}','删除角色','2018-12-19 23:10:15'),('get:/v1/authorities','查询所有权限','2018-12-19 23:10:15'),('get:/v1/oauth/client','list','2018-12-19 23:10:15'),('get:/v1/oauth/client/{clientId}','get','2018-12-19 23:10:15'),('get:/v1/role','查询所有角色','2018-12-19 23:10:15'),('get:/v1/userInfo','获取个人信息','2018-12-19 23:10:15'),('post:/v1/authorities/role','给角色添加权限','2018-12-19 23:10:15'),('post:/v1/authorities/sync','同步权限','2018-12-19 23:10:15'),('post:/v1/oauth/client','add','2018-12-19 23:10:15'),('post:/v1/role','添加角色','2018-12-19 23:10:15'),('post:/v1/user','添加用户','2018-12-19 23:10:15'),('post:/v1/user/query','查询所有用户','2018-12-19 23:10:15'),('put:/v1/oauth/client/{clientId}','update','2018-12-19 23:10:15'),('put:/v1/role','修改角色','2018-12-19 23:10:15'),('put:/v1/user','修改用户','2018-12-19 23:10:15'),('put:/v1/user/psw','修改自己密码','2018-12-19 23:10:15'),('put:/v1/user/psw/{id}','重置密码','2018-12-19 23:10:15'),('put:/v1/user/state','修改用户状态','2018-12-19 23:10:15');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `comments` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`comments`,`create_time`,`update_time`) values (1,'管理员','管理员','2018-12-19 23:11:29','2018-12-19 23:11:29'),(2,'普通用户','普通用户','2018-12-19 23:12:09','2018-12-19 23:12:09');

/*Table structure for table `sys_role_authorities` */

DROP TABLE IF EXISTS `sys_role_authorities`;

CREATE TABLE `sys_role_authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `authority` varchar(128) NOT NULL COMMENT '权限id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_role_permission_pm` (`authority`),
  KEY `FK_sys_role_permission_role` (`role_id`),
  CONSTRAINT `sys_role_authorities_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限';

/*Data for the table `sys_role_authorities` */

insert  into `sys_role_authorities`(`id`,`role_id`,`authority`,`create_time`) values (1,1,'delete:/v1/authorities/role','2018-12-19 23:11:32'),(3,1,'delete:/v1/oauth/client/{clientId}','2018-12-19 23:11:35'),(4,1,'delete:/v1/role/{id}','2018-12-19 23:11:36'),(5,1,'get:/v1/authorities','2018-12-19 23:11:37'),(6,1,'get:/v1/oauth/client','2018-12-19 23:11:37'),(7,1,'get:/v1/oauth/client/{clientId}','2018-12-19 23:11:38'),(8,1,'get:/v1/role','2018-12-19 23:11:40'),(9,1,'get:/v1/userInfo','2018-12-19 23:11:41'),(10,1,'post:/v1/authorities/role','2018-12-19 23:11:41'),(11,1,'post:/v1/authorities/sync','2018-12-19 23:11:42'),(12,1,'post:/v1/oauth/client','2018-12-19 23:11:42'),(13,1,'post:/v1/role','2018-12-19 23:11:43'),(14,1,'post:/v1/user','2018-12-19 23:11:44'),(15,1,'post:/v1/user/query','2018-12-19 23:11:44'),(16,1,'put:/v1/oauth/client/{clientId}','2018-12-19 23:11:46'),(17,1,'put:/v1/role','2018-12-19 23:11:46'),(18,1,'put:/v1/user','2018-12-19 23:11:46'),(19,1,'put:/v1/user/psw','2018-12-19 23:11:47'),(20,1,'put:/v1/user/psw/{id}','2018-12-19 23:11:47'),(21,1,'put:/v1/user/state','2018-12-19 23:11:50'),(22,2,'get:/v1/authorities','2018-12-19 23:12:35'),(23,2,'get:/v1/oauth/client','2018-12-19 23:12:40'),(24,2,'get:/v1/oauth/client/{clientId}','2018-12-19 23:12:41'),(25,2,'get:/v1/role','2018-12-19 23:13:06'),(26,2,'get:/v1/userInfo','2018-12-19 23:13:10'),(28,2,'post:/v1/oauth/client','2018-12-19 23:13:21'),(29,2,'post:/v1/role','2018-12-19 23:13:22'),(30,2,'post:/v1/user','2018-12-19 23:13:23'),(31,2,'post:/v1/user/query','2018-12-19 23:13:25'),(32,2,'put:/v1/user/psw','2018-12-19 23:13:40');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nick_name` varchar(20) NOT NULL COMMENT '昵称',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) NOT NULL DEFAULT '男' COMMENT '性别',
  `phone` varchar(12) DEFAULT NULL COMMENT '手机号',
  `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
  `email_verified` int(1) DEFAULT '0' COMMENT '邮箱是否验证，0未验证，1已验证',
  `true_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1冻结',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`username`),
  KEY `FK_sys_user` (`true_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`nick_name`,`avatar`,`sex`,`phone`,`email`,`email_verified`,`true_name`,`id_card`,`birthday`,`department_id`,`state`,`create_time`,`update_time`) values (1,'admin','$2a$10$H4DFdvsvGIpzls1ZAkyHPOep2bMZJW7i5Uvkj5ekRIBXIPJH.7aHm','管理员',NULL,'男','12345678901',NULL,0,NULL,NULL,NULL,NULL,0,'2018-12-19 23:30:05','2018-12-19 23:30:19'),(2,'demo','$2a$10$lvHbU7xSHZePp7EOZQP08e06OFNWttKAlDzZZfe9gd5d9.tL1WgZW','Demo01',NULL,'男','12345678901',NULL,0,NULL,NULL,NULL,NULL,0,'2018-12-19 23:31:25','2018-12-19 23:31:25');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_user_role` (`user_id`),
  KEY `FK_sys_user_role_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`create_time`) values (1,1,1,'2018-12-19 23:30:06'),(2,2,2,'2018-12-19 23:31:25');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
