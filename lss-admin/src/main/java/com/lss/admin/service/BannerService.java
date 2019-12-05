package com.lss.admin.service;

import com.lss.core.pojo.Banner;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.BannerParams;

public interface BannerService {
	/**
	 * 添加/编辑banner
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo save(Banner params);

	/**
	 * banner列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(BannerParams params);

	/**
	 * 启用/禁用/删除banner
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo operate(Banner params);
}
