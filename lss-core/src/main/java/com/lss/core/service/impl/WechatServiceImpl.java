package com.lss.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lss.core.constant.SystemConstant;
import com.lss.core.service.WechatService;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.RedisUtil;

@Service
public class WechatServiceImpl implements WechatService {

	private static final Logger log = LoggerFactory
			.getLogger(WechatServiceImpl.class);

	@Override
	public String getHylAccess_token() {
		try {
			String access_token = RedisUtil
					.getString(SystemConstant.hylAccess_token);
			if (ObjectUtil.isEmpty(access_token)) {
				String result = HttpRequestHelper.sendGet(
						"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
								+ SystemConstant.appid + "&secret="
								+ SystemConstant.secret, null);
				log.info("获取access_token：" + result);
				JSONObject json = JSONObject.parseObject(result);
				access_token = json.getString("access_token");
				if (ObjectUtil.isNotEmpty(access_token)) {
					RedisUtil.setString(SystemConstant.hylAccess_token,
							access_token, 5400);
				}
			}
			return access_token;
		} catch (Exception e) {
			log.error("获取Access_token错误", e);
		}
		return null;
	}

	@Override
	public String getLesasaAccess_token() {
		try {
			String access_token = RedisUtil
					.getString(SystemConstant.lesasaAccess_token);
			if (ObjectUtil.isEmpty(access_token)) {
				String result = HttpRequestHelper.sendGet(
						"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
								+ SystemConstant.lesasaAppid + "&secret="
								+ SystemConstant.lesasaSecret, null);
				JSONObject json = JSONObject.parseObject(result);
				if (json != null) {
					access_token = json.getString("access_token");
					if (ObjectUtil.isNotEmpty(access_token)) {
						RedisUtil.setString(SystemConstant.lesasaAccess_token,
								access_token, 6500);
					}
				}
			}
			return access_token;
		} catch (Exception e) {
			log.error("获取乐莎莎Access_token错误", e);
		}
		return null;
	}

	@Override
	public String getLssJsapi_ticket() {

		try {
			String jsapi_ticket = RedisUtil
					.getString(SystemConstant.lesasajsapi_ticket);
			if (ObjectUtil.isEmpty(jsapi_ticket)) {
				String access_token = getLesasaAccess_token();
				String result = HttpRequestHelper.sendGet(
						"https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
								+ access_token + "&type=jsapi", null);
				JSONObject json = JSONObject.parseObject(result);
				if (json != null) {
					jsapi_ticket = json.getString("ticket");
					if (ObjectUtil.isNotEmpty(jsapi_ticket)) {
						RedisUtil.setString(SystemConstant.lesasajsapi_ticket,
								jsapi_ticket, 6500);
					}
				}
			}
			return jsapi_ticket;
		} catch (Exception e) {
			log.error("获取乐莎莎jsapi_ticket错误", e);
		}
		return null;
	}
}
