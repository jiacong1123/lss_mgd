package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Banner;
import com.lss.core.vo.admin.params.BannerParams;

public interface BannerMapper extends AbstractMapper<Banner, Integer> {
	/**
	 * 后台查询数量
	 * 
	 * @return
	 */
	int adminCount(BannerParams params);

	/**
	 * 后台查询列表
	 * 
	 * @param params
	 * @return
	 */
	List<Banner> adminList(BannerParams params);

	/**
	 * 首页查询banner
	 * 
	 * @param params
	 * @return
	 */
	List<Banner> homeList(Banner params);
}