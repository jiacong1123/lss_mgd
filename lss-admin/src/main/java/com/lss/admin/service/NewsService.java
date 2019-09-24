package com.lss.admin.service;

import com.lss.core.pojo.News;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.BannerParams;

public interface NewsService {
	/**
	 * 添加/编辑新闻动态
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo save(News params);

	/**
	 * 新闻动态列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(BannerParams params);

	/**
	 * 启用/禁用/删除新闻动态
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo operate(News params);
}
