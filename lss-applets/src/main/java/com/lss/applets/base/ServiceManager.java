package com.lss.applets.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lss.applets.service.CommonService;
import com.lss.applets.service.HomeService;

/**
 * service 代理工厂
 * 
 * @author MMHEART
 *
 */
@Component
public class ServiceManager {
	public static HomeService homeService;

	public static CommonService commonService;

	public HomeService getHomeService() {
		return homeService;
	}

	@Resource
	public void setHomeService(HomeService homeService) {
		ServiceManager.homeService = homeService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	@Resource
	public void setCommonService(CommonService commonService) {
		ServiceManager.commonService = commonService;
	}
}
