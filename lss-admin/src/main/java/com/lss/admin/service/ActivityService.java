package com.lss.admin.service;

import com.lss.core.pojo.Activity;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ScienceParams;

public interface ActivityService {
	/**
	 * 添加/编辑活动
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo save(Activity params);
	
	/**
	 * 活动列表
	 * @param params
	 * @return
	 */
	ReturnVo list(ScienceParams params);
	
	/**
	 * 操作
	 * @param params
	 * @return
	 */
	ReturnVo operate(Activity params);
	
	/**
	 * 活动详情
	 * @param params
	 * @return
	 */
	ReturnVo details(Activity params);
}
