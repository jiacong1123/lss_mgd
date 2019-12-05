package com.lss.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.dto.AdminLoginDto;
import com.lss.core.util.RedisUtil;
import com.lss.core.util.ValidacationCodeUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.AdminParams;

/**
 * 管理员登录 controller
 * 
 * @author MyPC
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

	/**
	 * 获取登录图片验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("imgcode")
	public void getImage() throws IOException {
		// 生成验证码图片
		ValidacationCodeUtil image = ValidacationCodeUtil.Instance();
		String code = image.getVerificationCodeValue().toUpperCase();
		RedisUtil.setString(SystemConstant.lssAdminImgcode + code, code, 180);
		// 禁止图像缓存
		this.response.setHeader("Pragma", "no-cache");
		this.response.setHeader("Cache-Control", "no-cache");
		this.response.setDateHeader("Expires", 0);
		this.response.setContentType("image/png");
		// 输出图片
		ServletOutputStream out = this.response.getOutputStream();
		BufferedImage img = ImageIO.read(image.getImage());
		ImageIO.write(img, "png", out);
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("login")
	public ReturnVo login(@RequestBody AdminParams params) {
		//1.登录过程，如果是微信公众号登录则绑定其openid
		AdminLoginDto openIdInfo = (AdminLoginDto) session.getAttribute(SystemConstant.lssAdminSessionOpenId);
		if (openIdInfo != null) {
			params.setType(openIdInfo.getType());
			params.setOpenid(openIdInfo.getOpenid());
		}
		//2.登录并绑定
		ReturnVo result = ServiceManager.adminService.login(params);
		if (result.getResult().equals(ResponseCode.success)) {
			// 设置用户session
			this.request.getSession().setAttribute(
					SystemConstant.lssAdminSession, result.getObj());
			result.setTicket("JSESSIONID=" + this.request.getSession().getId());
		}
		return result;
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loginout")
	public ReturnVo loginout() {
		ReturnVo result = new ReturnVo();
		this.request.getSession().removeAttribute(
				SystemConstant.lssAdminSession);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}
}
