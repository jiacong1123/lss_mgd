/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/5/8 星期三 16:56:38                        */
/*==============================================================*/

/*==============================================================*/
/* Table: ls_call_event                                         */
/*==============================================================*/
create table lssdb.ls_call_event
(
   id                   int not null auto_increment comment 'id',
   record_id            varchar(40) comment '话单唯一标识',
   type                 varchar(30) comment '电话事件类型与场景(DIALING_OUTBOUND, 外呼去电拨打事件；DIALING_UNKNOWNOUTCALL, 陌生去电拨打事件；DIALING_INBOUND, 客户来电拨打事件；DIALING_CHANNEL, 渠道来电拨打事件；DIALING_UNKNOWN, 陌生来电拨打事件)',
   time                 varchar(20) comment '发生时间',
   transfer_no          varchar(20) comment '小号',
   show_no              varchar(20) comment '被叫显示号码',
   call_info            varchar(200) comment '扩展信息 JSON',
   emp_id               varchar(40) comment '顾问编号',
   emp_no               varchar(20) comment '顾问号码',
   emp_info             varchar(200) comment '顾问扩展信息 JSON',
   cus_id               varchar(40) comment '客户编号',
   cus_no               varchar(20) comment '客户号码',
   cus_info             varchar(200) comment '客户扩展信息 JSON',
   create_time          datetime comment '创建时间',
   update_date          datetime comment '修改时间',
   primary key (id)
);

alter table lssdb.ls_call_event comment '电话事件';

/*==============================================================*/
/* Table: ls_call_record                                        */
/*==============================================================*/
create table lssdb.ls_call_record
(
   id                   int not null auto_increment comment 'id',
   record_id            varchar(40) comment '话单唯一标识',
   type                 varchar(40) comment '记录类型 OutBound_Call, 外呼电话；OutBound_Unkown, 陌生去电； InBound_Call, 客户电话； InBound_Call_Channel, 渠道电话； Unknow_Call, 陌生电话；',
   transfer_no          varchar(20) comment '小号',
   show_no              varchar(20) comment '被叫显示号码',
   ll_result            varchar(20) comment '通话状态 ANSWERED(通话成功) ；BUSY(被叫忙)；NO_ANSWER(被叫无应答)；REJECT(被叫拒接)；HANGUP(主叫提前挂机)；INVALID_NUMBER(空号, 连连后台使用 信号异常)；POWER_OFF(关机)；UNAVAILABLE(暂时无法接听, 连连后台使用 信号异常)；SUSPEND(停机)；BLACK(黑名单号码)；OTHER(其他失败情形, 连连后台使用信号异常)',
   start_time           varchar(20) comment '通话开始时间',
   end_time             varchar(20) comment '通话结束时间',
   duration             int comment '通话时长, 大于 0 已接通, 否则未接通',
   recording_url        varchar(128) comment '录音地址, HTTP 地址, MP3 格式',
   call_info            varchar(200) comment '通话扩展信息 JSON,',
   emp_id               varchar(40) comment '顾问编号',
   emp_no               varchar(20) comment '顾问号码,',
   emp_no_area          varchar(40) comment '顾问号码归属地',
   emp_info             varchar(200) comment '顾问扩展信息 JSON',
   cus_id               varchar(40) comment '客户编号',
   cus_no               varchar(20) comment '客户号码',
   cus_no_area          varchar(40) comment '客户号码归属地',
   cus_info             varchar(200) comment '客户扩展信息',
   process_status       varchar(10) comment '数据处理状态（INIT:初始 ;PROCESSED:已处理 ）',
   admin_id             int comment '管理员ID',
   admin_name           varchar(64) comment '管理员名称',
   admin_phone          varchar(20) comment '管理员手机',
   user_id              int comment '用户ID',
   user_name            varchar(64) comment '用户名称',
   workrecord_id        int comment '工单进度ID',
   orderno              varchar(32) comment '工单号',
   lss_record_url       varchar(128) comment '自存录音URL',
   create_time          datetime comment '创建时间',
   update_date          datetime comment '修改时间',
   primary key (id)
);

alter table lssdb.ls_call_record comment '通话记录';

/*==============================================================*/
/* Table: ls_org                                                */
/*==============================================================*/
create table lssdb.ls_org
(
   id                   int not null auto_increment comment 'id',
   org_name             varchar(100) comment '机构名称',
   parent_id            int comment '上级机构',
   parent_ids           varchar(2000) comment '所有上级机构(英文逗号分割)',
   grade                varchar(20) comment '机构级别',
   remark               varchar(200) comment '备注',
   index_no             int comment '排序',
   create_date          datetime comment '创建时间',
   update_date          timestamp comment '修改时间',
   primary key (id)
);

alter table lssdb.ls_org comment '机构';

/*==============================================================*/
/* Table: ls_sms_record                                         */
/*==============================================================*/
create table lssdb.ls_sms_record
(
   id                   int not null auto_increment comment 'id',
   record_id            varchar(40) comment '话单唯一标识',
   type                 varchar(40) comment '记录类型记录类型 OUTBOUND, 外发短信；OUTBOUND_UNKNOWN, 陌生外发短信；INBOUND, 客户回复短信；INBOUND_CHANNEL, 渠道短信；INBOUND_UNKNOWN, 陌生短信',
   transfer_no          varchar(20) comment '小号',
   show_no              varchar(20) comment '被叫显示号码',
   ll_result            varchar(20) comment '短信状态 SENT, 成功发送 ；INVALID_SHOW_NUMBER, 显示号码不合法；INVALID_RECEIVER_NUMBER, 接收号码非手机号；OTHER, 其他失败',
   transfer_time        varchar(20) comment '短信发送时间',
   content              varchar(256) comment '短信内容',
   sms_info             varchar(200) comment '短信扩展信息 JSON',
   emp_id               varchar(40) comment '顾问编号',
   emp_no               varchar(20) comment '顾问号码,',
   emp_no_area          varchar(40) comment '顾问号码归属地',
   emp_info             varchar(200) comment '顾问扩展信息 JSON',
   cus_id               varchar(40) comment '客户编号',
   cus_no               varchar(20) comment '客户号码',
   cus_no_area          varchar(40) comment '客户号码归属地',
   cus_info             varchar(200) comment '客户扩展信息',
   process_status       varchar(10) comment '数据处理状态（INIT:初始 ;PROCESSED:已处理 ）',
   admin_id             int comment '管理员ID',
   admin_name           varchar(64) comment '管理员名称',
   admin_phone          varchar(20) comment '管理员手机',
   user_id              int comment '用户ID',
   user_name            varchar(64) comment '用户名称',
   workrecord_id        int comment '工单进度ID',
   orderno              varchar(32) comment '工单号',
   lss_record_url       varchar(128) comment '自存录音URL',
   create_time          datetime comment '创建时间',
   update_date          datetime comment '修改时间',
   primary key (id)
);

alter table lssdb.ls_sms_record comment '短信记录';


# 跟踪记录 新增通话相关状态 ok
ALTER TABLE lssdb.`ls_workrecord`
ADD COLUMN `status`  int(11) NULL COMMENT '状态（1成功 0失败）' AFTER `createtime`,
ADD COLUMN `remark`  varchar(200) NULL COMMENT '备注' AFTER `status`,
ADD COLUMN `record_url`  varchar(128) NULL COMMENT '录音地址';

# 员工登记话机号码 ok
ALTER TABLE lssdb.`ls_admin`
ADD COLUMN `caller_nos`  varchar(160) NULL COMMENT '话机号码，多个英文逗号分割， 电话号码, 固话带区号' AFTER `logintime`,
ADD COLUMN `transfer_no`  varchar(160) NULL COMMENT '小号';

# 客户添加微信，如果直通车已加了起微信则关联该字段 不使用
#ALTER TABLE lssdb.`ls_user`
#ADD COLUMN `shop_wx`  varchar(50) NULL COMMENT '客户直通车终端微信' AFTER `notes`,
#ADD COLUMN `member_no`  varchar(40) NULL COMMENT '客户直通车客户编号' AFTER `shop_wx`;

# 帐号关联部门 ok 2019.05.21  
ALTER TABLE lssdb.`ls_admin`
ADD COLUMN `org_id`  int NULL COMMENT '所属部门id',
ADD COLUMN `org_name`  varchar(32) NULL COMMENT '所属部门名称',
ADD COLUMN `no_wx`  varchar(50) NULL COMMENT '微信号' ;

# 微信昵称长度修改 ok
ALTER TABLE lssdb.`ls_user`
MODIFY COLUMN `wechat`  varchar(50)  NULL DEFAULT NULL COMMENT '微信昵称';

# 工单成交金额 ok
ALTER TABLE lssdb.`ls_workorder`
ADD COLUMN `amount`  bigint NULL COMMENT '成交金额（分为单位）' AFTER `visitingtime`;

# 工单新增菜单 ok
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (28, '(外)呼叫记录', NULL, '/worktaskmanagement/callrecord', 2, 4);

# 工单新增 客户直通车 API访问地址 /结尾 ，不用拼接api-os/api ok 测试环境http://rw.kehuzhitongche.com/ ，线上http://juke.leshasha.com/
INSERT INTO lssdb.`ls_worktag` (`type`, `tagname`) VALUES (6, 'http://juke.leshasha.com/');

# 工单地址配置,用于校验登录是否合法  /结尾  ok 测试环境http://rw.kehuzhitongche.com/lss-admin/，线上http://adminapi.lesasa.com/
INSERT INTO confcenter.`cc_system_params` (`SYS_PARAM_NAME`, `GROUP_NAME`, `SYS_PARAM_VALUE`, `REMARK`, `SYSTEM_ALIAS_NAME`, `ONLY_ADMIN_MODIFY`) 
VALUES ('gdHost', 'lssgd', 'http://adminapi.lesasa.com/', '乐莎莎工单_URL配置', 'lss', '1');

# 跟踪记录 新增通话相关状态 ok 2019-05-14 19:17:25  
ALTER TABLE lssdb.`ls_workrecord`
ADD COLUMN `remark2`  varchar(2000) NULL COMMENT '备注2,微信聊天时则记录终端及客户微信信息' ;

# 帐号关联部门 ok 2019.05.21  
INSERT INTO lssdb.`ls_org` (`id`, `org_name`, `parent_id`, `parent_ids`, `grade`, `remark`, `index_no`, `create_date`, `update_date`) VALUES (1, '深圳乐莎莎科技有限公司', 0, ',0,', NULL, '顶级机构', 0, '2019-5-21 11:42:40', '2019-5-21 11:43:02');
# 帐号 ok 2019.05.21  
INSERT INTO lssdb.`ls_role` (`roleid`, `rolename`) VALUES (9, '电销组长');


# 工单新增菜单 ok
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (29, '组织结构', NULL, '/systemsetting/organization', 2, 1);

