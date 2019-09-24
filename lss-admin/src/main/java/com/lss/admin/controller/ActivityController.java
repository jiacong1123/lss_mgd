package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.Activity;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ScienceParams;

/**
 * 活动
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController {

	/**
	 * 添加/编辑活动
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Activity params) {
		return ServiceManager.activityService.save(params);
	}

	/**
	 * 活动列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody ScienceParams params) {
		return ServiceManager.activityService.list(params);
	}

	/**
	 * 启用/禁用/删除活动
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody Activity params) {
		return ServiceManager.activityService.operate(params);
	}

	/**
	 * 活动详情
	 * 
	 * @return
	 */
	@RequestMapping("details")
	public ReturnVo details(@RequestBody Activity params) {
		return ServiceManager.activityService.details(params);
	}
}
