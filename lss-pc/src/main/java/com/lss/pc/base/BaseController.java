package com.lss.pc.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.lss.core.constant.ResponseCode;
import com.lss.core.vo.ReturnVo;

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

	@ModelAttribute
	public void constractReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
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
		log.error("请求参数转换异常：", e);
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
	public void exceptionHandle(Exception e) throws IOException {
		log.error("系统异常：", e);
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.error);
		result.setMsg(ResponseCode.errorMsg);
		this.response.setContentType("text/html; charset=UTF-8");
		this.response.getWriter().write(JSON.toJSONString(result));
	}
}
