package com.lss.admin.controller.wx;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.dto.WxLoginDto;
import com.lss.core.exception.LssException;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;

/**
 * 微信公众号静默授权登录。<p>
 * 当前采用的【方案二】
 * @author lhy 2019.06.26
 *
 */
@Controller
@RequestMapping("wx")
public class LoginController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 【方案一】工单管理员微信静默授权登录。(该模式适用于 前端请求接口未做代理，用的跨域请求后台接口的，其会话请求头保持一致。),且该工程部署对应的域名已微信授权
	 * 第一步：用户打开链接：http://adminapi.lesasa.com/wx/login?url=授权成功后需跳转的链接地址
	 * 第二步：微信授权后带上code，并把第一步的url设置到state参数再访问该接口 第三步：根据微信code查openid进行登录
	 * 第三步：根据微信code查openid进行登录,并跳转到第一步指定的url,之后可用“wx/getAdmin”拿登录者信息
	 *  
	 * @param code
	 * @param state
	 * @param 静默授权获取openid后跳转的URL
	 * @throws IOException
	 */
	@RequestMapping("login")
	public void login(String code, String state, String url) throws IOException {
//		String requrl = "http://" + request.getServerName() // 服务器地址
//				+ ":" + request.getServerPort() // 端口号
//				+ request.getContextPath() // 项目名称
//				+ request.getServletPath() // 请求页面或其他地址
//				+ "?" + (request.getQueryString()); // 参数
//		log.info("requrl:{}" , requrl);
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		log.info("contextPath:{},servletPath:{}", contextPath, servletPath);
		log.info("请求登录：code=" + code + ",state=" + state + "，url=" + url);
		if (ObjectUtil.isEmpty(code)) {// 第一步：用户打开链接
			// 获取code
			String redirect_uri = URLEncoder.encode("http://adminapi.lesasa.com" + contextPath + "/wx/login");
//			String str = URLEncoder.encode(url);
			log.debug("redirect_uri:{}", redirect_uri);
			if (url == null) {
				url = "";
			}
			String location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + SystemConstant.lesasaAppid
					+ "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=" + url
					+ "#wechat_redirect";
			response.sendRedirect(location);// 第二步：微信授权:拿code
		} else {
			// 第三步：登录
			ReturnVo result = ServiceManager.adminService.loginByWx(code);

			String ticket = "JSESSIONID=" + this.request.getSession().getId();
			if (StringUtils.isEmpty(state)) {
				state = "";
			}
			// 帶上sessionId
			state = state.endsWith("?") ? (state + "&ticket=" + ticket) : (state + "?ticket=" + ticket);
			if (result.getResult().equals(ResponseCode.success)) {// 登录成功，设置用户session
				this.request.getSession().setAttribute(SystemConstant.lssAdminSession, result.getObj());
				response.sendRedirect(state);
			} else if (result.getResult().equals(ResponseCode.noLogin)) {
				// openId未绑定则把openid设置到当前会话，并跳转，用于手动登录进行绑定
				// value= AdminLoginDto
				this.request.getSession().setAttribute(SystemConstant.lssAdminSessionOpenId, result.getObj());
				response.sendRedirect(state);
			} else {
				throw new LssException(result.getResult(), result.getMsg());
			}
		}
	}

	/**
	 * 【方案一】工单管理员微信静默授权登录成功后获取当前登录的管理员信息
	 * 
	 * @param code
	 * @param state
	 * @throws IOException
	 */
	@RequestMapping("getAdmin")
	@ResponseBody
	public ReturnVo getAdminInfo() throws IOException {
		ReturnVo result = ReturnVo.success(loginAdmin);
		result.setTicket("JSESSIONID=" + this.request.getSession().getId());
		return result;
	}

	/**
	 * 【方案一】静默授权不指定登录后界面默认跳转
	 * 
	 * @throws IOException
	 */
	@RequestMapping("")
	@ResponseBody
	public ReturnVo wxLoginResult(String ticket) throws IOException {
		ReturnVo result = ReturnVo.success(null);
		result.setTicket("JSESSIONID=" + this.request.getSession().getId());
		return result;
	}

	/**
	 * 【方案二】获取微信静默授权地址。
	 * 
	 * @param url 微信授权跳转的地址
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("auth")
	@ResponseBody
	public ReturnVo authUrl(@RequestBody WxLoginDto params) throws IOException {
		if (ObjectUtil.isEmpty(params.getUrl())) {
			throw new LssException(ResponseCode.parameterError, ResponseCode.parameterErrorMsg);
		}
		String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + SystemConstant.lesasaAppid
				+ "&redirect_uri=" + params.getUrl() + "&response_type=code&scope=snsapi_base&state="
				+ "#wechat_redirect";
		ReturnVo result =  ReturnVo.success(authUrl);
		
		return result;
	}
	
	/**
	 * 【方案二】根据微信code登录(该模式适用于 前端做了代理，其会话请求头保持一致。)
	 * 
	 * @param code 微信code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("code/login")
	@ResponseBody
	public ReturnVo loginByWxCode(@RequestBody WxLoginDto wxLoginDto) throws IOException {
		ReturnVo result = ServiceManager.adminService.loginByWx(wxLoginDto.getCode());
		result.setTicket("JSESSIONID=" + this.request.getSession().getId());
		if (result.getResult().equals(ResponseCode.success)) {
			// 设置用户session
			this.request.getSession().setAttribute(
					SystemConstant.lssAdminSession, result.getObj());
//			result.setTicket("JSESSIONID=" + this.request.getSession().getId());
		}else if (result.getResult().equals(ResponseCode.noLogin)) {
			// openId未绑定则把openid设置到当前会话，用于手动登录进行绑定
			// value= AdminLoginDto
			this.request.getSession().setAttribute(SystemConstant.lssAdminSessionOpenId, result.getObj());
		}
		return result;
	}
}
