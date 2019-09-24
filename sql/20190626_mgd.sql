# 工单移动端 允许输入微信号
ALTER TABLE lssdb.`ls_user` ADD COLUMN `no_wx_alias`  varchar(100) NULL COMMENT '微信号' AFTER `notes`;
# 工单移动端 工单来源 ：市场，ID要固定为80，前端写固定了
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`) VALUES (80,2, '市场');
# 删除旧的市场
DELETE from lssdb.`ls_worktag` where tagid=50 and type=2;


# drop table if exists ls_admin_login;

/*==============================================================*/
/* Table: ls_admin_login                                        */
/*==============================================================*/

create table lssdb.ls_admin_login
(
   id                   int not null auto_increment comment 'id',
   adminid              int comment '管理员ID',
   type                 varchar(12) comment '类型(WXGZH:微信公众号)',
   openid               varchar(64) comment '第三方唯一标识',
   create_date          datetime comment '创建时间',
   update_date          timestamp comment '修改时间',
   primary key (id)
);

alter table lssdb.ls_admin_login comment '管理员第三方登录信息';
 
 
# 工单来源新增二级来源  2019.07.03
ALTER TABLE lssdb.`ls_worktag`
ADD COLUMN `grade`  int NULL COMMENT '级别' AFTER `tagname`,
ADD COLUMN `parentid`  int NULL COMMENT '上级ID' AFTER `grade`;
# 原来的工单来源 级别为1 2019.07.03
UPDATE lssdb.`ls_worktag` SET grade=1 where type=2;
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '人人乐西丽店');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '人人乐前海店');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '人人乐月亮湾');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '名家富居');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '麒麟花园');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '家乐福中心城店');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '星海名城和兴万家');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '海雅中影');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '水湾中影');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '宝力方中影');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '香槟广场');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '华润万家');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '汇金花园');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '星海名城理家店');
INSERT INTO lssdb.`ls_worktag` (`grade`,`parentid`,`type`, `tagname`) VALUES (2,80,2, '转介绍');


# 客户来源新增二级 2019.07.03
ALTER TABLE lssdb.`ls_user`
ADD COLUMN `sourceid2`  int NULL COMMENT '2级来源id' AFTER `no_wx_alias`,
ADD COLUMN `sourcename2`  varchar(255) NULL COMMENT '2级来源名称' AFTER `sourceid2`;

 