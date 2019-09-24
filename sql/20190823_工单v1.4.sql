#  ================  pcv.1.4.0 ================================ 

# 删掉用户不存在的工单数据 
DELETE from  lssdb.ls_workorder where userid not in (
SELECT userid from lssdb.ls_user 
);
# 新增字段：应收+实收+欠收+新分配时间+最新跟进时间+最新跟进情况 v1.4 2019.08.09
ALTER TABLE lssdb.`ls_workorder`
MODIFY COLUMN `amount`  bigint(20) NULL DEFAULT NULL COMMENT '实收金额（分为单位）' AFTER `visitingtime`,
ADD COLUMN `receivablesamt`  bigint NULL COMMENT '应收金额(分为单位）' AFTER `lableremarks`,
ADD COLUMN `debtamt`  bigint NULL COMMENT '欠收金额(分为单位）' AFTER `receivablesamt`,
ADD COLUMN `followuptime`  datetime NULL COMMENT '最新跟进时间' AFTER `debtamt`,
ADD COLUMN `allottime`  datetime NULL COMMENT '分配时间' AFTER `followuptime`,
ADD COLUMN `followupremarks`   varchar(512) NULL COMMENT '最新跟进备注' AFTER `allottime`;

ALTER TABLE lssdb.`ls_workorder`
ADD COLUMN `pay_time` datetime NULL DEFAULT NULL COMMENT '收费时间' AFTER `receivablesamt` ;
 
# 将最新的跟进记录同步到工单中
UPDATE lssdb.ls_workorder t1, (
SELECT * from lssdb.ls_workrecord w where w.id in(
SELECT MAX(t.id)  from lssdb.ls_workrecord t  GROUP BY t.orderno 
) )
 t SET t1.followuptime = t.createtime , t1.followupremarks=t.content where t1.orderno=t.orderno ;

# 将最新的分配时间同步到工单中
 UPDATE lssdb.ls_workorder t1 ,(
		SELECT t.* from (
		SELECT w.orderno,COUNT(1)  c ,MAX(w.createtime) allottime from lssdb.ls_workrecord w where w.content in('分配','新增分配') GROUP BY w.orderno,w.adminid
		)t  
	) t2 SET t1.allottime=t2.allottime where t1.orderno= t2.orderno;
  

# 工单收费记录
# drop table if exists lssdb.ls_order_pay;
 
/*==============================================================*/
/* Table: ls_order_pay                                          */
/*==============================================================*/
create table lssdb.ls_order_pay
(
   id                   int not null auto_increment comment 'id',
   adminid              int comment '管理员ID',
   orderno              varchar(32) comment '工单号',
   receivablesamt       bigint comment '应收（分为单位）',
   debtamt              bigint comment '欠收（分为单位）',
   amount               bigint comment '实收（分为单位）',
   pay_time             datetime comment '收费时间',
   create_date          datetime comment '创建时间',
   update_date          datetime comment '修改时间',
   remark               varchar(500) comment '备注',
   first_pay            varchar(6) comment '是否首次收费(Y是，N否)',
   adminname            varchar(64) comment '登记人姓名',
   primary key (id)
);

alter table lssdb.ls_order_pay comment '工单收费记录';


#  ================  pcv.1.4.2 ================================

# drop table if exists lssdb.ls_st_order;
/*==============================================================*/
/* Table: ls_st_order                                           */
/*==============================================================*/
create table lssdb.ls_st_order
(
   id                   int not null auto_increment comment 'id',
   adminid              int comment '管理员ID',
   adminname            varchar(64) comment '登记人姓名',
   st_date              datetime comment '统计月份',
   allot_user_count     int comment '月客户数（当月分配的客户数）',
   reserve_user_count   int comment '新到店数（当月到店的客户）',
   his_reserve_count    int comment '历史到店数（累计到店-当月到店）',
   all_reserve_count    int comment '总到店数:累计到店（新客到店数+历史到店数）',
   mon_reserve_rate     decimal(10,2) comment '新客到店率（新客到店数÷月客户数）',
   all_reserve_rate     decimal(10,2) comment '综合到店率（总到店数÷月客户数（本月分配的客户））',
   big_deal_count       int comment '大项成交数（大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交））',
   big_deal_rate        decimal(10,2) comment '成交率（大项）（大项目成交数÷总到店数）',
   all_deal_amt         bigint comment '业绩完成（分为单位）（状态（已完成）+全部项目实收金额（大项目+其他））',
   create_date          datetime comment '创建时间',
   update_date          datetime comment '修改时间',
   remark               varchar(256) comment '备注',
   primary key (id)
);

alter table lssdb.ls_st_order comment '员工工单月成交统计';

ALTER TABLE `ls_st_order`
MODIFY COLUMN `allot_user_count`  int(11) NULL DEFAULT 0 COMMENT '月客户数（当月分配的客户数）' AFTER `st_date`,
MODIFY COLUMN `reserve_user_count`  int(11) NULL DEFAULT 0 COMMENT '新到店数（当月到店的客户）' AFTER `allot_user_count`,
MODIFY COLUMN `his_reserve_count`  int(11) NULL DEFAULT 0 COMMENT '历史到店数（累计到店-当月到店）' AFTER `reserve_user_count`,
MODIFY COLUMN `all_reserve_count`  int(11) NULL DEFAULT 0 COMMENT '总到店数:累计到店（新客到店数+历史到店数）' AFTER `his_reserve_count`,
MODIFY COLUMN `mon_reserve_rate`  decimal(10,4) NULL DEFAULT 0 COMMENT '新客到店率（新客到店数÷月客户数）' AFTER `all_reserve_count`,
MODIFY COLUMN `all_reserve_rate`  decimal(10,4) NULL DEFAULT 0 COMMENT '综合到店率（总到店数÷月客户数（本月分配的客户））' AFTER `mon_reserve_rate`,
MODIFY COLUMN `big_deal_count`  int(11) NULL DEFAULT 0 COMMENT '大项成交数（大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交））' AFTER `all_reserve_rate`,
MODIFY COLUMN `big_deal_rate`  decimal(10,4) NULL DEFAULT 0 COMMENT '成交率（大项）（大项目成交数÷总到店数）' AFTER `big_deal_count`,
MODIFY COLUMN `all_deal_amt`  bigint(20) NULL DEFAULT 0 COMMENT '业绩完成（分为单位）（状态（已完成）+全部项目实收金额（大项目+其他））' AFTER `big_deal_rate`,
MODIFY COLUMN `create_date`  datetime NULL COMMENT '创建时间' AFTER `all_deal_amt`;




# drop table if exists lssdb.ls_st_call;

/*==============================================================*/
/* Table: ls_st_call                                            */
/*==============================================================*/
create table lssdb.ls_st_call
(
   id                   int not null auto_increment comment 'id',
   adminid              int comment '管理员ID',
   adminname            varchar(64) comment '登记人姓名',
   st_date              datetime comment '统计日期',
   call_count           int comment '通话次数',
   duration             int comment '通话时长',
   avg_duration         int comment '平均时长',
   create_date          datetime comment '创建时间',
   update_date          datetime comment '修改时间',
   remark               varchar(256) comment '备注',
   primary key (id)
);

alter table lssdb.ls_st_call comment '员工电联统计';

# 今天之前的历史电联数据统计
INSERT INTO lssdb.ls_st_call(st_date,call_count,duration,adminid,adminname,avg_duration,create_date)
 
SELECT DATE_FORMAT(t.create_time,'%Y%m%d') as st_date,  count(*) as call_count ,SUM(t.duration) duration,t.admin_id as adminid,t1.`name` as adminname,ROUND(SUM(t.duration)/ count(*)) as avg_duration , SYSDATE() as create_date from
lssdb.ls_admin t1 JOIN 
 lssdb.ls_call_record t 
on t1.adminid=t.admin_id
where t.admin_id is NOT NULL AND t.duration>0  AND t.create_time< CURDATE()
 AND  DATE_FORMAT(t.create_time,'%Y%m%d') not in( SELECT c.st_date from lssdb.ls_st_call c )
 GROUP BY t.admin_id,DATE_FORMAT(t.create_time,'%Y%m%d') ;

# 工单新增菜单  
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (32, '报表管理','ordered-list', '/reportmanagement', 1, NULL);
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (33, '成交统计', NULL, '/reportmanagement/dealstatistics', 2, 32);
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (34, '电联统计', NULL, '/reportmanagement/itustatistics', 2, 32);

 