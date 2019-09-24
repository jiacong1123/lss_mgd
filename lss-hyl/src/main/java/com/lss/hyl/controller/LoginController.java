package com.lss.hyl.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.hyl.base.BaseController;
import com.lss.hyl.base.ServiceManager;

@Controller
@RequestMapping("user")
public class LoginController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);

	/**
	 * 微信静默授权登录
	 * 
	 * @param code
	 * @param state
	 * @throws IOException
	 */
	@RequestMapping("login")
	public void login(String code, String state, String url) throws IOException {
		log.info("请求登录：code=" + code + ",state=" + state + "，url=" + url);
		if (ObjectUtil.isEmpty(code)) {
			// 获取code
			String redirect_uri = URLEncoder
					.encode("http://hylapi.leshasha.com/user/login");
			String str = URLEncoder.encode(url);
			String location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ SystemConstant.appid
					+ "&redirect_uri="
					+ redirect_uri
					+ "&response_type=code&scope=snsapi_base&state="
					+ url
					+ "#wechat_redirect";
			response.sendRedirect(location);
		}
		ReturnVo result = ServiceManager.userService.login(code);
		if (result.getResult().equals(ResponseCode.success)) {
			// 设置用户session
			this.request.getSession().setAttribute(
					SystemConstant.hylDoctorSession, result.getObj());
			response.sendRedirect(state);
		}
	}
}
