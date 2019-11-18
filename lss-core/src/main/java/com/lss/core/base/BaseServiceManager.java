package com.lss.core.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lss.core.service.SmsService;
import com.lss.core.service.WechatService;

@Component
public class BaseServiceManager {
	public static SmsService smsService;

	public static WechatService wechatService;

	public SmsService getSmsService() {
		return smsService;
	}

	@Resource
	public void setSmsService(SmsService smsService) {
		BaseServiceManager.smsService = smsService;
	}

	public WechatService getWechatService() {
		return wechatService;
	}

	@Resource
	public void setWechatService(WechatService wechatService) {
		BaseServiceManager.wechatService = wechatService;
	}
}
