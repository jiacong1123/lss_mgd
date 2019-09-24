package com.lss.admin.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;

/**
 * 基础controller
 * 
 * @author SWWH
 *
 */
public class BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(BaseController.class);

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	protected LoginAdmin loginAdmin;

	@ModelAttribute
	public void constractReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		LoginAdmin admin = (LoginAdmin) session
				.getAttribute(SystemConstant.lssAdminSession);
		if (admin != null) {
			this.loginAdmin = admin;
		}
	}

	/**
	 * 参数转换异常
	 * 
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void paramsNotReadable(HttpMessageNotReadableException e)
			throws IOException {
		log.error("请求参数转换异常",e);
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.parameterError);
		result.setMsg(ResponseCode.parameterErrorMsg);
		this.response.setContentType("text/html; charset=UTF-8");
		this.response.getWriter().write(JSON.toJSONString(result));
	}

	/**
	 * 捕获controller所有异常
	 * 
	 * @param ex
	 * @throws IOException
	 */
	@ExceptionHandler(Exception.class)
	public void exceptionHandle(Exception ex) throws IOException {
		log.error("异常",ex);
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.error);
		result.setMsg(ResponseCode.errorMsg);
		this.response.setContentType("text/html; charset=UTF-8");
		this.response.getWriter().write(JSON.toJSONString(result));
	}
	
	/**
	 * 参数转换异常
	 * 
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(LssException.class)
	public void lssException(HttpServletRequest request,
			HttpServletResponse response,LssException e)
			throws IOException {
		log.error("操作异常",e);
		
		if(ResponseCode.llyhUnkowToken.equals(e.getExceptionCode())) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		}else {
			ReturnVo result = new ReturnVo();
			result.setResult(e.getExceptionCode());
			result.setMsg(e.getExceptionInfo());
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(result));
		}
	}
}
