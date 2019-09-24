package com.lss.core.service;

import com.lss.core.vo.ReturnVo;

public interface SmsService {
	/**
	 * 标签乐莎莎发送短信
	 * 
	 * @param phone
	 * @param text
	 * @return
	 */
	ReturnVo lssSendSMS(String phone, String text);
}
