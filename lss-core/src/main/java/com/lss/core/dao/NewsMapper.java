package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.News;
import com.lss.core.vo.admin.params.BannerParams;
import com.lss.core.vo.pc.HomeParams;

public interface NewsMapper extends AbstractMapper<News, Integer> {
	/**
	 * 后台查询数量
	 * 
	 * @param params
	 * @return
	 */
	int adminCount(BannerParams params);

	/**
	 * 后台查询列表
	 * 
	 * @param params
	 * @return
	 */
	List<News> adminList(BannerParams params);

	/**
	 * 首页新闻动态数量
	 * 
	 * @return
	 */
	int homeCount(HomeParams params);

	/**
	 * 首页新闻动态列表
	 * 
	 * @param params
	 * @return
	 */
	List<News> homeList(HomeParams params);

}