package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.Banner;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.BannerParams;

/**
 * banner controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("banner")
public class BannerController extends BaseController {
	/**
	 * 添加/编辑banner
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Banner params) {
		return ServiceManager.bannerService.save(params);
	}

	/**
	 * banner列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody BannerParams params) {
		return ServiceManager.bannerService.list(params);
	}

	/**
	 * 启用/禁用/删除banner
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody Banner params) {
		return ServiceManager.bannerService.operate(params);
	}
}
