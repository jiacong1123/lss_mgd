package com.lss.applets.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.applets.base.BaseController;
import com.lss.applets.base.ServiceManager;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.applets.UserInfoParams;

/**
 * 公共 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("common")
public class CommonController extends BaseController {
	/**
	 * 诊所菜单
	 * 
	 * @return
	 */
	@RequestMapping("dropmenu")
	public ReturnVo dropmenu() {
		return ServiceManager.commonService.dropmenu();
	}

	/**
	 * 标签
	 * 
	 * @return
	 */
	@RequestMapping("tags")
	public ReturnVo tags(@RequestBody WorkTag tag) {
		return ServiceManager.commonService.tags(tag.getType());
	}

	/**
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping("formsubmit")
	public ReturnVo formsubmit(@RequestBody UserInfoParams params) {
		return ServiceManager.commonService.formsubmit(params);
	}
}
