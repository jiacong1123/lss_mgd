package com.lss.admin.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.service.IBaikeService;
import com.lss.core.dto.BaikeDto;
import com.lss.core.dto.FindBaikePage;
import com.lss.core.vo.ReturnVo;

/**
 * 口腔百科
 * 
 * @author lhy 2019年6月27日
 *
 */
@RestController
@RequestMapping("baike")
public class BaikeController extends BaseController {
	@Resource
	IBaikeService baikeService;

	/**
	 * 添加/编辑口腔百科
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody BaikeDto params) {
		baikeService.addBaike(params);
		return ReturnVo.success(null);
	}

	/**
	 * 口腔百科列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody FindBaikePage params) {
		params.setNotStatus(-1);//删除的不查出
		return baikeService.findBaikePage(params);
	}

	/**
	 * 启用/禁用/删除口腔百科
	 * 
	 * @return
	 */
	@RequestMapping("edit")
	public ReturnVo edit(@RequestBody BaikeDto params) {
		baikeService.updateBaike(params);
		return ReturnVo.success(null);
	}

	/**
	 * 详情
	 * @param params
	 * @return
	 */
	@RequestMapping("details")
	public ReturnVo details(@RequestBody BaikeDto params) {
		BaikeDto bk = baikeService.findBaike(params);
		return ReturnVo.success(bk);
	}

}
