# 工单标签选项数据 2019.08.05
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (101,7, '地域归属',1,null);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (102,7, '号码状态',1,null);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (103,7, '客户进度',1,null);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (104,7, '咨询项目',1,null);
# 标签选项：地域归属
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (105,7, '外地',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (106,7, '龙岗区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (107,7, '盐田区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (108,7, '坪山区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (109,7, '大鹏新区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (110,7, '宝安区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (111,7, '光明区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (112,7, '南山区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (113,7, '龙华区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (114,7, '福田区',2,101);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (115,7, '罗湖区',2,101);
# 标签选项：号码状态
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (116,7, '未接',2,102);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (117,7, '号码错误',2,102);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (118,7, '空号',2,102);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (119,7, '没有咨询过',2,102);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (120,7, '关机',2,102);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (121,7, '无法接通',2,102);
# 标签选项：客户进度
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (122,7, '已到店',2,103);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (123,7, '已交定金',2,103);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (124,7, '已成交',2,103);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (125,7, '已在外院成交',2,103);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (126,7, '同行',2,103);
# 标签选项：咨询项目
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (127,7, '金属',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (128,7, '陶瓷',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (129,7, '舌侧',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (130,7, '正雅',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (131,7, '时代天使',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (132,7, '隐适美',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (133,7, '登腾',2,104);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (134,7, '诺贝尔',2,104);

# 工单标签 2019.08.05
ALTER TABLE lssdb.`ls_workorder`
ADD COLUMN `lablenames`  varchar(256) NULL COMMENT '标签名称，多个则英文逗号分割' AFTER `amount`,
ADD COLUMN `lableremarks`  varchar(1280) NULL COMMENT '标签备注，json存贮用于前后端解析' AFTER `lablenames`;
 
 
# 客户标签 2019.08.05
ALTER TABLE lssdb.`ls_user`
ADD COLUMN `lablenames`  varchar(256) NULL COMMENT '标签名称，多个则英文逗号分割' AFTER `sourcename2`,
ADD COLUMN `lableremarks`  varchar(1280) NULL COMMENT '标签备注，json存贮用于前后端解析' AFTER `lablenames`;
 
# 预约项目 2019.08.08
# 1.修改项目：“牙齿矫正”修改为：正畸，“牙齿美容”修改为：美白，“牙齿种植”修改为：种植，“常规治疗”修改为：治疗；
# 2.新增项目：洗牙，补牙，拔牙，拔智齿，口腔检查，其它
select * from lssdb.ls_worktag t where t.type=1;
UPDATE lssdb.ls_worktag SET tagname='正畸' where tagname='牙齿矫正' AND type=1;
UPDATE lssdb.ls_worktag SET tagname='美白' where tagname='牙齿美容' AND type=1;
UPDATE lssdb.ls_worktag SET tagname='种植' where tagname='牙齿种植' AND type=1;
UPDATE lssdb.ls_worktag SET tagname='治疗' where tagname='常规治疗' AND type=1;
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (135,1, '洗牙',NULL,NULL);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (136,1, '补牙',NULL,NULL);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (137,1, '拔牙',NULL,NULL);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (138,1, '拔智齿',NULL,NULL);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (139,1, '口腔检查',NULL,NULL);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (140,1, '修复',NULL,NULL);
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`,grade,parentid) VALUES (141,1, '其它',NULL,NULL);



# 2019.08.12  工单来源
# INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`) VALUES (150,2, 'OPPO');

# 工单新增菜单 
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (31, '全部工单', NULL, '/worktaskmanagement/allworktask', 2, 4);

 