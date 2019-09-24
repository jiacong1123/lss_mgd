package com.lss.hyl.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lss.hyl.service.CommonService;
import com.lss.hyl.service.UserService;

/**
 * service 代理工厂
 * 
 * @author MMHEART
 *
 */
@Component
public class ServiceManager {

	public static CommonService commonService;

	public static UserService userService;

	public CommonService getCommonService() {
		return commonService;
	}

	@Resource
	public void setCommonService(CommonService commonService) {
		ServiceManager.commonService = commonService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		ServiceManager.userService = userService;
	}
}
