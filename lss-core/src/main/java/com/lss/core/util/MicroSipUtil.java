package com.lss.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lss.core.vo.admin.EcPhoneVo;

/**
 * 超脑云呼叫线路
 * 
 * @author Administrator
 *
 */
public class MicroSipUtil {

	// 超脑云常量
	private static String URL = "http://cc.yunhus.com:4434/";
	private static String appid = "ONO9MOP17HCTXRYYRRQW5FBZAL8NU766";
	private static String accesskey = "7YSFA490ZWNPVBRDNPLS3QQ5U2LBCNG8";
	// 获取token
	private static String GET_TOKEN_SERVICE = "App.Sip_Auth.Login";
	// 通话
	private static String MAKE_CALL_SERVICE = "App.Sip_Call.MakeCall";
	// 获取通话记录
	private static String GET_CALL_RECORD = "App.Sip_Cdr.GetBill";
	// 获取通话录音
	private static String GET_RECORD_FILE = "App.Sip_Cdr.GetRecodeFile";

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public static String getToken() {
		String token = RedisUtil.getString(URL);
		try {
			if (StringUtils.isEmpty(token)) {
				// 1、创建HttpClient
				org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
				// 2、创建get或post请求方法
				PostMethod method = new PostMethod(URL);
				// 3、设置编码
				httpClient.getParams().setContentCharset("UTF-8");
				// 4、设置请求消息头，为表单方式提交
				method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				// 5、设置参数
				method.setParameter("service", GET_TOKEN_SERVICE);
				method.setParameter("appid", appid);
				method.setParameter("accesskey", accesskey);
				// 6、执行提交
				httpClient.executeMethod(method);
				JSONObject jsonObject = JSONObject.parseObject(method.getResponseBodyAsString());
				if (null != jsonObject) {
					int ret = (int) jsonObject.get("ret");
					if (200 == ret) {
						JSONObject data = jsonObject.getJSONObject("data");
						JSONObject result = data.getJSONObject("result");
						token = result.getString("token");
						RedisUtil.setString(URL,token,RedisUtil.EXRP_HOUR);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 拨号
	 * 
	 * @param phoneVo
	 * @return
	 */
	public static JSONObject call(EcPhoneVo phoneVo) {
		JSONObject jsonObject = new JSONObject();
		try {
			String token = getToken();
			// 1、创建HttpClient
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			// 2、创建get或post请求方法
			PostMethod method = new PostMethod(URL);
			// 3、设置编码
			httpClient.getParams().setContentCharset("UTF-8");
			// 4、设置请求消息头，为表单方式提交
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// 5、设置参数
			method.setParameter("service", MAKE_CALL_SERVICE);
			method.setParameter("token", token);
			method.setParameter("extnumber", phoneVo.getExtnumber());
			method.setParameter("destnumber", phoneVo.getCallPhone());
			method.setParameter("syncflag", "3");
			method.setParameter("direction", "3");
			method.setParameter("callmethod", "0");
			// 6、执行提交
			httpClient.executeMethod(method);
			jsonObject = JSONObject.parseObject(method.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取通话记录
	 * 
	 * @return
	 */
	public static JSONObject getRecord() {
		JSONObject jsonObject = new JSONObject();
		try {
			String token = getToken();
			// 1、创建HttpClient
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			// 2、创建get或post请求方法
			PostMethod method = new PostMethod(URL);
			// 3、设置编码
			httpClient.getParams().setContentCharset("UTF-8");
			// 4、设置请求消息头，为表单方式提交
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// 5、设置参数
			method.setParameter("service", GET_CALL_RECORD);
			method.setParameter("token", token);
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
			String date = sft.format(new Date());
			method.setParameter("starttime", date + " 00:00:00");
			method.setParameter("endtime", date + " 23:59:59");
			method.setParameter("syncflag", "3");
			method.setParameter("direction", "3");
			method.setParameter("callmethod", "0");
			// 6、执行提交
			httpClient.executeMethod(method);
			jsonObject = JSONObject.parseObject(method.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取通话录音地址
	 * @param fileName
	 * @return
	 */
	public static JSONObject getSoundRecord(String fileName) {
		JSONObject jsonObject = new JSONObject();
		try {
			String token = getToken();
			// 1、创建HttpClient
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			// 2、创建get或post请求方法
			PostMethod method = new PostMethod(URL);
			// 3、设置编码
			httpClient.getParams().setContentCharset("UTF-8");
			// 4、设置请求消息头，为表单方式提交
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// 5、设置参数
			method.setParameter("service", GET_RECORD_FILE);
			method.setParameter("token", token);
			method.setParameter("filename", fileName);
			method.setParameter("syncflag", "3");
			method.setParameter("direction", "3");
			method.setParameter("callmethod", "0");
			// 6、执行提交
			httpClient.executeMethod(method);
			jsonObject = JSONObject.parseObject(method.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
