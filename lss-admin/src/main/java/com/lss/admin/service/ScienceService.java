package com.lss.admin.service;

import com.lss.core.pojo.Science;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ScienceParams;

public interface ScienceService {
	
	/**
	 * 添加/编辑科普
	 * @param params
	 * @return
	 */
	ReturnVo save(Science params);
	
	/**
	 * 科普列表
	 * @param params
	 * @return
	 */
	ReturnVo list(ScienceParams params);
	
	/**
	 * 启用/禁用/删除科普
	 * @param params
	 * @return
	 */
	ReturnVo operate(Science params);
}
