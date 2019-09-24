package com.lss.hyl.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.core.base.BaseServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.RandomString;
import com.lss.core.util.Sha1Util;
import com.lss.core.vo.ReturnVo;
import com.lss.hyl.base.BaseController;

/**
 * 乐莎莎公众号 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("common")
public class LesasaController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(LesasaController.class);

	/**
	 * 乐莎莎调用js-sdk config
	 * 
	 * @return
	 */
	@RequestMapping("lsswxconfig")
	public ReturnVo lsswxconfig(String url) {
		ReturnVo result = new ReturnVo();
		String jsapi_ticket = BaseServiceManager.wechatService
				.getLssJsapi_ticket();
		if (ObjectUtil.isNotEmpty(jsapi_ticket)) {
			// 签名
			String noncestr = RandomString.generateString(16);
			String timestamp = String
					.valueOf(System.currentTimeMillis() / 1000);

			log.info("jsapi_ticket-" + jsapi_ticket);
			log.info("noncestr-" + noncestr);
			log.info("timestamp-" + timestamp);
			log.info("url-" + url);

			String signature = Sha1Util.getSha1("jsapi_ticket=" + jsapi_ticket
					+ "&noncestr=" + noncestr + "&timestamp=" + timestamp
					+ "&url=" + url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("appId", SystemConstant.lesasaAppid);
			map.put("timestamp", timestamp);
			map.put("nonceStr", noncestr);
			map.put("signature", signature);
			result.setObj(map);
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

}
