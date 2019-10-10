package com.lss.pc.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.core.dto.BaikeDto;
import com.lss.core.dto.FindBaikePage;
import com.lss.core.pojo.Banner;
import com.lss.core.pojo.News;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.applets.UserInfoParams;
import com.lss.core.vo.pc.HomeParams;
import com.lss.pc.base.BaseController;
import com.lss.pc.base.ServiceManager;
import com.lss.pc.service.IBaikeService;

/**
 * 首页 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("home")
public class HomeController extends BaseController {
	@Resource
	IBaikeService baikeService;

	/**
	 * 
	 * 首页banner
	 * 
	 * @return
	 */
	@RequestMapping("banner")
	public ReturnVo banner(@RequestBody Banner params) {
		return ServiceManager.homeService.banner(params);
	}

	/**
	 * 新闻动态
	 * 
	 * @return
	 */
	@RequestMapping("news")
	public ReturnVo news(@RequestBody HomeParams params) {
		return ServiceManager.homeService.news(params);
	}

	/**
	 * 诊所信息
	 * 
	 * @return
	 */
	@RequestMapping("clinicinfo")
	public ReturnVo clinicinfo() {
		return ServiceManager.homeService.clinicinfo();
	}

	/**
	 * 新闻详情
	 * 
	 * @return
	 */
	@RequestMapping("newsdetails")
	public ReturnVo newsdetails(@RequestBody News params) {
		return ServiceManager.homeService.newsdetails(params);
	}

	/**
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping("formsubmit")
	public ReturnVo formsubmit(@RequestBody UserInfoParams params) {
		return ServiceManager.homeService.formsubmit(params);
	}

	/**
	 * 百科 列表
	 * 
	 * @return
	 * @author lhy 2019年6月27日
	 */
	@RequestMapping("baike")
	public ReturnVo baikeList(@RequestBody FindBaikePage params) {
		params.setStatus(1);//只查启用的
		return baikeService.findBaikePage(params);
	}

	/**
	 * 百科 列表
	 * 
	 * @return
	 * @author lhy 2019年6月27日
	 */
	@RequestMapping("baike/details")
	public ReturnVo baikeDetails(@RequestBody BaikeDto params) {
		BaikeDto bk = baikeService.findBaike(params);
		return ReturnVo.success(bk);
	}
}
