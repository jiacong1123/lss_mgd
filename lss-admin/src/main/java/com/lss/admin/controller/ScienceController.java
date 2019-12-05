package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.Science;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ScienceParams;

/**
 * 口腔科普 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("science")
public class ScienceController extends BaseController {
	/**
	 * 添加/编辑科普
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Science params) {
		return ServiceManager.scienceService.save(params);
	}

	/**
	 * 科普列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody ScienceParams params) {
		return ServiceManager.scienceService.list(params);
	}

	/**
	 * 启用/禁用/删除科普
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody Science params) {
		return ServiceManager.scienceService.operate(params);
	}
}
