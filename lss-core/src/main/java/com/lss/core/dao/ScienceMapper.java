package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Science;
import com.lss.core.vo.admin.ScienceVo;
import com.lss.core.vo.admin.params.ScienceParams;

public interface ScienceMapper extends AbstractMapper<Science, Integer> {
	/**
	 * 后台获取科普数量
	 * 
	 * @return
	 */
	int adminCount(ScienceParams params);

	/**
	 * 后台获取科普列表
	 * 
	 * @param params
	 * @return
	 */
	List<ScienceVo> adminList(ScienceParams params);
}