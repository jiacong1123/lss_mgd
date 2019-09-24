package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Activity;
import com.lss.core.vo.admin.params.ScienceParams;

public interface ActivityMapper extends AbstractMapper<Activity, Integer> {
	/**
	 * 后台获取活动数量
	 * 
	 * @return
	 */
	int adminCount(ScienceParams params);

	/**
	 * 后台获取活动列表
	 * 
	 * @param params
	 * @return
	 */
	List<Activity> adminList(ScienceParams params);
}