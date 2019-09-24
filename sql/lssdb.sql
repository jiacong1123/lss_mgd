/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : lssdb

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-04-12 16:06:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ls_activity
-- ----------------------------
DROP TABLE IF EXISTS `ls_activity`;
CREATE TABLE `ls_activity` (
  `actid` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `title` varchar(32) DEFAULT NULL COMMENT '活动标题',
  `image` varchar(256) DEFAULT NULL COMMENT '活动图片',
  `url` varchar(256) DEFAULT NULL COMMENT '活动外链',
  `content` text COMMENT '内容',
  `popup` int(11) DEFAULT NULL COMMENT '是否弹窗',
  `tcimage` varchar(256) DEFAULT NULL COMMENT '弹窗图片',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `start` date DEFAULT NULL COMMENT '开始时间',
  `end` date DEFAULT NULL COMMENT '结束时间',
  `status` int(11) DEFAULT NULL COMMENT '状态 0下架 1上架 -1删除',
  `views` int(11) DEFAULT '0' COMMENT '访问量',
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`actid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='活动表';

-- ----------------------------
-- Table structure for ls_admin
-- ----------------------------
DROP TABLE IF EXISTS `ls_admin`;
CREATE TABLE `ls_admin` (
  `adminid` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `clinicid` int(11) DEFAULT NULL COMMENT '诊所id',
  `doctorid` int(11) DEFAULT NULL COMMENT '医生id',
  `loginame` varchar(32) DEFAULT NULL COMMENT '登录名称',
  `loginpwd` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `status` int(11) DEFAULT '1' COMMENT '状态1正常 0禁用',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `logintime` datetime DEFAULT NULL COMMENT '最近登录时间',
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Table structure for ls_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `ls_admin_role`;
CREATE TABLE `ls_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `adminid` int(11) DEFAULT NULL COMMENT '管理员id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminid_roleid_unique` (`adminid`,`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='管理员角色表';

-- ----------------------------
-- Table structure for ls_bag_order
-- ----------------------------
DROP TABLE IF EXISTS `ls_bag_order`;
CREATE TABLE `ls_bag_order` (
  `orderno` varchar(32) NOT NULL COMMENT '订单号',
  `doctorid` int(11) DEFAULT NULL COMMENT '医生id',
  `bagid` int(11) DEFAULT NULL COMMENT '包id',
  `title` varchar(32) DEFAULT NULL COMMENT '包名称',
  `image` varchar(256) DEFAULT NULL COMMENT '图片',
  `price` double(18,2) DEFAULT NULL COMMENT '价格',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `amount` double(18,2) DEFAULT NULL COMMENT '总额',
  `status` int(11) DEFAULT '0' COMMENT '状态 0未支付 1已支付',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `paytype` int(11) DEFAULT NULL COMMENT '支付类型 1微信支付 2线下支付',
  `paytime` datetime DEFAULT NULL COMMENT '支付时间',
  `payorderno` varchar(64) DEFAULT NULL COMMENT '支付订单号',
  PRIMARY KEY (`orderno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生订单';

-- ----------------------------
-- Table structure for ls_banner
-- ----------------------------
DROP TABLE IF EXISTS `ls_banner`;
CREATE TABLE `ls_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `image` varchar(256) DEFAULT NULL COMMENT '图片',
  `url` varchar(256) DEFAULT NULL COMMENT '图片url',
  `type` int(11) DEFAULT NULL COMMENT '类型 1首页 2医生',
  `status` int(11) DEFAULT '1' COMMENT '状态 1启用 0禁用 -1删除',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='banner';

-- ----------------------------
-- Table structure for ls_clinic
-- ----------------------------
DROP TABLE IF EXISTS `ls_clinic`;
CREATE TABLE `ls_clinic` (
  `clinicid` int(11) NOT NULL AUTO_INCREMENT COMMENT '诊所id',
  `name` varchar(32) DEFAULT NULL COMMENT '诊所名称',
  `image` varchar(256) DEFAULT NULL COMMENT '诊所图片',
  `shortname` varchar(16) DEFAULT NULL COMMENT '诊所简称',
  `mainproject` varchar(64) DEFAULT NULL COMMENT '主治项目',
  `telephone` varchar(16) DEFAULT NULL COMMENT '诊所电话',
  `principal` varchar(16) DEFAULT NULL COMMENT '负责人',
  `phone` char(11) DEFAULT NULL COMMENT '负责人电话',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(64) DEFAULT NULL COMMENT '详细地址',
  `type` int(11) DEFAULT NULL COMMENT '类型 1自营 2合作',
  `description` varchar(256) DEFAULT NULL COMMENT '排班说明',
  `status` int(11) DEFAULT '1' COMMENT '状态 1正常',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `milieupicture` varchar(1024) DEFAULT NULL COMMENT '环境图',
  `devicepicture` varchar(1024) DEFAULT NULL COMMENT '设备图',
  PRIMARY KEY (`clinicid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='诊所表';

-- ----------------------------
-- Table structure for ls_doctor
-- ----------------------------
DROP TABLE IF EXISTS `ls_doctor`;
CREATE TABLE `ls_doctor` (
  `doctorid` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
  `clinicid` int(11) DEFAULT NULL COMMENT '诊所id',
  `name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `titleid` int(11) DEFAULT NULL COMMENT '职称',
  `photo` varchar(256) DEFAULT NULL COMMENT '头像',
  `goodat` varchar(256) DEFAULT NULL COMMENT '擅长',
  `synopsis` varchar(256) DEFAULT NULL COMMENT '简介',
  `status` int(11) DEFAULT '1' COMMENT '状态 1正常',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `ksid` int(11) DEFAULT NULL COMMENT '科室id',
  PRIMARY KEY (`doctorid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='医生表';

-- ----------------------------
-- Table structure for ls_doctor_bag
-- ----------------------------
DROP TABLE IF EXISTS `ls_doctor_bag`;
CREATE TABLE `ls_doctor_bag` (
  `bagid` int(11) NOT NULL AUTO_INCREMENT COMMENT '包id',
  `title` varchar(32) DEFAULT NULL COMMENT '包名称',
  `des` varchar(512) DEFAULT NULL COMMENT '包描述',
  `image` varchar(256) DEFAULT NULL COMMENT '图片',
  `price` double(18,2) DEFAULT NULL COMMENT '价格',
  `details` text COMMENT '详情',
  `status` int(11) DEFAULT NULL COMMENT '状态 1正常 0下架',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`bagid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='医生包';

-- ----------------------------
-- Table structure for ls_doctor_login
-- ----------------------------
DROP TABLE IF EXISTS `ls_doctor_login`;
CREATE TABLE `ls_doctor_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `doctorid` int(11) DEFAULT NULL COMMENT '医生id',
  `type` int(11) DEFAULT NULL COMMENT '类型 1公众号',
  `openid` varchar(64) DEFAULT NULL COMMENT 'openid',
  `unionid` varchar(64) DEFAULT NULL COMMENT 'unionid',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='医生第三方登录';

-- ----------------------------
-- Table structure for ls_doctor_user
-- ----------------------------
DROP TABLE IF EXISTS `ls_doctor_user`;
CREATE TABLE `ls_doctor_user` (
  `doctorid` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
  `clinicname` varchar(32) DEFAULT NULL COMMENT '诊所名称',
  `name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `jobtitle` varchar(32) DEFAULT NULL COMMENT '职称',
  `photo` varchar(256) DEFAULT NULL COMMENT '头像',
  `goodat` varchar(256) DEFAULT NULL COMMENT '擅长',
  `synopsis` varchar(256) DEFAULT NULL COMMENT '简介',
  `address` varchar(64) DEFAULT NULL COMMENT '详细地址',
  `status` int(11) DEFAULT '1' COMMENT '详细地址',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`doctorid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='医生用户';

-- ----------------------------
-- Table structure for ls_logs
-- ----------------------------
DROP TABLE IF EXISTS `ls_logs`;
CREATE TABLE `ls_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `adminid` int(11) DEFAULT NULL COMMENT '管理员id',
  `content` varchar(128) DEFAULT NULL COMMENT '操作内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Table structure for ls_news
-- ----------------------------
DROP TABLE IF EXISTS `ls_news`;
CREATE TABLE `ls_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(512) DEFAULT NULL COMMENT '副标题',
  `image` varchar(256) DEFAULT NULL COMMENT '主图',
  `content` text COMMENT '内容',
  `mcontent` text,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `status` int(11) DEFAULT '1' COMMENT '状态 1启用 0禁用 -1删除',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `clickvolume` int(11) DEFAULT '0' COMMENT '点击量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='新闻动态';

-- ----------------------------
-- Table structure for ls_pictures
-- ----------------------------
DROP TABLE IF EXISTS `ls_pictures`;
CREATE TABLE `ls_pictures` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) DEFAULT NULL COMMENT '产品id',
  `image` varchar(256) DEFAULT NULL COMMENT '产品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COMMENT='产品图片';

-- ----------------------------
-- Table structure for ls_popedom
-- ----------------------------
DROP TABLE IF EXISTS `ls_popedom`;
CREATE TABLE `ls_popedom` (
  `popeid` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(16) DEFAULT NULL COMMENT '权限名称',
  `icon` varchar(16) DEFAULT NULL COMMENT '图标',
  `url` varchar(64) DEFAULT NULL COMMENT '权限路径',
  `level` int(11) DEFAULT NULL COMMENT '菜单等级',
  `parentid` int(11) DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`popeid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for ls_product
-- ----------------------------
DROP TABLE IF EXISTS `ls_product`;
CREATE TABLE `ls_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `classid` int(11) DEFAULT NULL COMMENT '分类id',
  `title` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `des` varchar(64) DEFAULT NULL COMMENT '描述',
  `image` varchar(256) DEFAULT NULL COMMENT '主图',
  `costprice` double(18,2) DEFAULT NULL COMMENT '原价',
  `price` double(18,2) DEFAULT NULL COMMENT '现价',
  `details` text COMMENT '产品详情',
  `status` int(11) DEFAULT NULL COMMENT '状态 0下架 1上架 -1删除',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Table structure for ls_role
-- ----------------------------
DROP TABLE IF EXISTS `ls_role`;
CREATE TABLE `ls_role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rolename` varchar(32) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for ls_role_popedom
-- ----------------------------
DROP TABLE IF EXISTS `ls_role_popedom`;
CREATE TABLE `ls_role_popedom` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  `popeid` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Table structure for ls_science
-- ----------------------------
DROP TABLE IF EXISTS `ls_science`;
CREATE TABLE `ls_science` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `answer` text COMMENT '回答',
  `doctorid` int(11) DEFAULT NULL,
  `answertime` datetime DEFAULT NULL COMMENT '回答时间',
  `status` int(11) DEFAULT NULL COMMENT '状态 0下架 1商家 -1 删除',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8 COMMENT='口腔科普';

-- ----------------------------
-- Table structure for ls_user
-- ----------------------------
DROP TABLE IF EXISTS `ls_user`;
CREATE TABLE `ls_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信昵称',
  `province` varchar(16) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `photo` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `sourceid` int(11) DEFAULT NULL COMMENT '来源id',
  `sourcedate` date DEFAULT NULL COMMENT '来源日期',
  `status` int(11) DEFAULT '1' COMMENT '状态 1正常',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `phone_unique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1092 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for ls_workorder
-- ----------------------------
DROP TABLE IF EXISTS `ls_workorder`;
CREATE TABLE `ls_workorder` (
  `orderno` varchar(32) NOT NULL COMMENT '工单号',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `status` int(11) DEFAULT '0' COMMENT '工单状态 0未分配 1待跟进 2已预约 3已到店 4已完成 5已关闭',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `clinicid` int(11) DEFAULT NULL COMMENT '诊所id',
  `projectid` int(11) DEFAULT NULL COMMENT '预约项目id',
  `adminid` int(11) DEFAULT NULL COMMENT '所属人员id',
  `complaint` varchar(128) DEFAULT NULL COMMENT '主诉',
  `worknotes` text COMMENT '工单备注',
  `level` char(2) DEFAULT NULL COMMENT '意愿等级',
  `returndate` datetime DEFAULT NULL COMMENT '计划回访日期',
  `reservedate` date DEFAULT NULL COMMENT '预约日期',
  `reservetime` varchar(16) DEFAULT NULL COMMENT '预约时间',
  `closereason` varchar(256) DEFAULT NULL COMMENT '关闭原因',
  `doctorid` int(11) DEFAULT NULL COMMENT '医生id',
  `arrivaltime` datetime DEFAULT NULL COMMENT '到店时间',
  `followup` int(11) DEFAULT '0' COMMENT '跟进次数',
  `pid` int(11) DEFAULT NULL COMMENT '产品id',
  `isclue` int(11) DEFAULT '0' COMMENT '是否是线索 1是0否',
  `usertype` varchar(40) DEFAULT NULL COMMENT '用户类型',
  `usertypename` varchar(40) DEFAULT NULL COMMENT '用户类型名称',
  `cluestatus` int(11) DEFAULT '0' COMMENT '线索状态0可抢1已被抢2冻结中',
  `ordertype` int(11) DEFAULT '1' COMMENT '接诊类型 1接诊 2派单',
  `clinicname` varchar(40) DEFAULT NULL COMMENT '诊所名称',
  `visitingtime` datetime DEFAULT NULL COMMENT '接诊时间',
  PRIMARY KEY (`orderno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单表';

-- ----------------------------
-- Table structure for ls_workrecord
-- ----------------------------
DROP TABLE IF EXISTS `ls_workrecord`;
CREATE TABLE `ls_workrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `orderno` varchar(32) DEFAULT NULL COMMENT '工单号',
  `adminid` int(11) DEFAULT NULL COMMENT '管理员id',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `talktime` int(11) DEFAULT NULL COMMENT '通话时长',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=754 DEFAULT CHARSET=utf8 COMMENT='工单进度表';

-- ----------------------------
-- Table structure for ls_worktag
-- ----------------------------
DROP TABLE IF EXISTS `ls_worktag`;
CREATE TABLE `ls_worktag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `type` int(11) DEFAULT NULL COMMENT '类型 1预约项目 2工单来源 3职称 4 医生科室 5换新请求路径',
  `tagname` varchar(32) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='工单标签';
SET FOREIGN_KEY_CHECKS=1;
