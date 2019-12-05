/**
 * 
 */
package com.lss.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lss.admin.service.PhoneService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.exception.LssException;
import com.lss.core.vo.phone.CallEventListVo;
import com.lss.core.vo.phone.CallRecordListVo;
import com.lss.core.vo.phone.LlyhResultVo;
import com.lss.core.vo.phone.LlyhSmsRecordListVo;

/**
 * 
 * 
 * 类说明：连连隐号记录推送相关接口。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月5日
 */
@RestController
@RequestMapping("llyh")
public class LlyhRecordController {
	
	private static final Logger log = LoggerFactory.getLogger(LlyhRecordController.class);

	@Resource
	private PhoneService phoneService;
	
	public String llyhData(HttpServletRequest request,Long now) {
		String data = null;
		try {
			log.info("连连隐号推送[{}]，开始获取报文。",now);
			ServletInputStream in = request.getInputStream();
			data = inputStream2String(in);
			log.info("连连隐号推送[{}]报文：{}" , now,data);
			return data;
		} catch (IOException e) {
			throw new LssException(ResponseCode.failure, ResponseCode.failureMsg);
		}
		 
	}

	private String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException {
		if (in == null)
			return "";
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}


	@RequestMapping("/callRecord")
	public LlyhResultVo callRecord(HttpServletRequest request) {
		Long now = System.currentTimeMillis();
		log.info("连连隐号推送 话单录音:{}", now);
		String text = llyhData(request,now);
		CallRecordListVo records= JSON.parseObject(text, CallRecordListVo.class);
		LlyhResultVo resultVo = null;
		resultVo = phoneService.saveCallRecord(records);
		log.info("连连隐号推送 话单录音:{}，处理完成！", now);
		return resultVo;
	}

	@RequestMapping("/smsRecord")
	public LlyhResultVo smsRecord(HttpServletRequest request) {
		Long now = System.currentTimeMillis();
		log.info("连连隐号推送 短信记录:{}", now);
		String text = llyhData(request,now);
		LlyhSmsRecordListVo records = JSON.parseObject(text, LlyhSmsRecordListVo.class);
		
		LlyhResultVo resultVo = null;
		resultVo = phoneService.saveSmsRecord(records);
		log.info("连连隐号推送 短信记录:{}，处理完成！", now);
		return resultVo;
	}

	@RequestMapping("/eventRecord")
	public LlyhResultVo eventRecord(HttpServletRequest request) {
		Long now = System.currentTimeMillis();
		log.info("连连隐号推送 话单事件:{}", now);
		String text = llyhData(request,now);
		CallEventListVo callEventListVo = JSON.parseObject(text, CallEventListVo.class);
		LlyhResultVo resultVo = null;
		resultVo = phoneService.saveEventRecord(callEventListVo);
		log.info("连连隐号推送 话单事件:{}，处理完成！", now);
		return resultVo;
	}

	/**
	 * 参数转换异常
	 * 
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(LssException.class)
	public void lssException(HttpServletRequest request, HttpServletResponse response, LssException e)
			throws IOException {
		log.error("操作异常", e);

		if (ResponseCode.llyhUnkowToken.equals(e.getExceptionCode())) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		} else if (ResponseCode.llyhParameterError.equals(e.getExceptionCode())) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
		} else {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 捕获controller所有异常
	 * 
	 * @param ex
	 * @throws IOException
	 */
	@ExceptionHandler(Exception.class)
	public void exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws IOException {
		log.error("异常", ex);
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}
}
