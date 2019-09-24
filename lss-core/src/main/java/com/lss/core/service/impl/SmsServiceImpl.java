package com.lss.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lss.core.service.SmsService;
import com.lss.core.util.HttpUtil;
import com.lss.core.vo.ReturnVo;

@Service
public class SmsServiceImpl implements SmsService {

	private static final Logger log = LoggerFactory
			.getLogger(SmsServiceImpl.class);

	@Override
	public ReturnVo lssSendSMS(String phone, String text) {
		ReturnVo res = new ReturnVo();
		res.setResult(-1);
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("apikey", "190e7d32a532d335c83fb4dd3db7eeab");
			params.put("text", text);
			params.put("mobile", phone);
			String result = HttpUtil.post(
					"https://sms.yunpian.com/v2/sms/single_send.json", params);
			JSONObject obj = JSONObject.parseObject(result);
			int code = obj.getIntValue("code");
			String msg = obj.getString("msg");
			res.setResult(code);
			res.setMsg(msg);
		} catch (Exception e) {
			log.error("乐莎莎发送短信错误", e);
		}
		return res;
	}
}
