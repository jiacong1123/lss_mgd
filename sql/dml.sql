# 新增来源 2019.04.22
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'网易信息流'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'爱电影网'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'东方头条'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'UC信息流'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'推啊'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'美图'	FROM lssdb.ls_worktag;
# 2019.08.02 新增来源
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'b站'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'蜂巢'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'爱问代投'	FROM lssdb.ls_worktag;
INSERT lssdb.ls_worktag(tagid,type,tagname) SELECT MAX(tagid)+1,2,'吃喝玩乐在深圳'	FROM lssdb.ls_worktag;
# 2019.08.12  工单来源
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`) VALUES (150,2, 'OPPO');
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`) VALUES (151,2, '凤凰');
INSERT INTO lssdb.`ls_worktag` (tagid,`type`, `tagname`) VALUES (152,2, '微思顿');