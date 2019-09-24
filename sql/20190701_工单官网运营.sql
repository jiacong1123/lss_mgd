# banner新增小图，新增“百科”类型
ALTER TABLE lssdb.`ls_banner`
MODIFY COLUMN `type`  int(11) NULL DEFAULT NULL COMMENT '类型 1首页 2医生 3百科' AFTER `url`,
ADD COLUMN `small_icon`  varchar(128) NULL COMMENT '小图' AFTER `createtime`;
# 新闻动态新增小图，新增“3公益”类型，新增链接（直接跳第三方）
ALTER TABLE lssdb.`ls_news` MODIFY COLUMN `type`  int(11) NULL DEFAULT NULL COMMENT '类型（1.新闻动态 2.部落动态 3公益.活动）' AFTER `mcontent`,
ADD COLUMN `small_icon`  varchar(128) NULL COMMENT '小图' AFTER `clickvolume`,
ADD COLUMN `url`    varchar(128) NULL COMMENT '链接' AFTER `small_icon`;
# 新增口腔百科
CREATE TABLE lssdb.`ls_baike` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(512) DEFAULT NULL COMMENT '副标题',
  `image` varchar(256) DEFAULT NULL COMMENT '主图',
  `content` text COMMENT '内容',
  `type` int(11) DEFAULT NULL COMMENT '类型（1.口腔百科）',
  `status` int(11) DEFAULT '1' COMMENT '状态 1启用 0禁用 -1删除',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `clickvolume` int(11) DEFAULT '0' COMMENT '点击量',
  `small_icon` varchar(128) DEFAULT NULL COMMENT '小图',
  `url` varchar(128) DEFAULT NULL COMMENT '链接',
  `lables` varchar(256) DEFAULT NULL COMMENT '标签，多个则英文逗号分割 （隐适美、矫正、种植、贴面）',
  PRIMARY KEY (`id`)
) COMMENT='口腔百科';

# 新增口腔百科菜单
INSERT INTO lssdb.`ls_popedom` (`popeid`, `name`, `icon`, `url`, `level`, `parentid`) VALUES (30, '口腔百科', NULL, '/operatemanagement/OralAn', 2, 17);