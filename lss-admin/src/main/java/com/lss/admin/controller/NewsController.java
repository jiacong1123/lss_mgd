package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.News;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.BannerParams;

/**
 * 新闻动态 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("news")
public class NewsController extends BaseController {
	/**
	 * 添加/编辑新闻动态
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody News params) {
		return ServiceManager.newsService.save(params);
	}

	/**
	 * 新闻动态列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody BannerParams params) {
		return ServiceManager.newsService.list(params);
	}

	/**
	 * 启用/禁用/删除新闻动态
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody News params) {
		return ServiceManager.newsService.operate(params);
	}
}
