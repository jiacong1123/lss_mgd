package com.lss.hyl.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.wxpay.sdk.WXPayUtil;
import com.lss.core.base.BaseServiceManager;
import com.lss.core.constant.WxPayConfig;
import com.lss.core.pojo.DoctorBag;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.hyl.base.BaseController;
import com.lss.hyl.base.ServiceManager;

/**
 * 公共 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("common")
public class CommonController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(CommonController.class);

	/**
	 * 获取上传token
	 * 
	 * @return
	 */
	@RequestMapping("uploadToken")
	public ReturnVo uploadToken() {
		return ServiceManager.commonService.uploadToken();
	}

	/**
	 * 产品包列表
	 * 
	 * @return
	 */
	@RequestMapping("baglist")
	public ReturnVo baglist() {
		return ServiceManager.commonService.baglist();
	}

	/**
	 * 产品包详情
	 * 
	 * @return
	 */
	@RequestMapping("bagdetails")
	public ReturnVo bagdetails(@RequestBody DoctorBag params) {
		return ServiceManager.commonService.bagdetails(params);
	}

	/**
	 * @Description:微信支付 回调
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("wxpaynotify")
	public void wxNotify() throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();
		String resultdata = sb.toString();
		if (ObjectUtil.isNotEmpty(resultdata)) {
			Map<String, String> valideData = WXPayUtil.xmlToMap(resultdata);
			// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
			if (WXPayUtil.isSignatureValid(sb.toString(), WxPayConfig.key)) {
				// 【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
				// 返回码
				String return_code = valideData.get("return_code");
				if ("SUCCESS".equals(return_code)) {
					String result_code = valideData.get("result_code");
					if ("SUCCESS".equals(result_code)) {
						String orderno = valideData.get("out_trade_no");
						Double amount = Double.parseDouble(valideData
								.get("total_fee")) / 100;
						String transaction_id = valideData
								.get("transaction_id");
						try {
							if (ServiceManager.commonService.payBack(orderno,
									amount, transaction_id)) {
								response.getWriter()
										.write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
								return;
							}
						} catch (Exception e) {
							log.error("支付处理业务逻辑错误：" + e.toString());
						}
					}
				}
			}
		}
		// 返回错误信息
		response.getWriter()
				.write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
	}
	
	@RequestMapping("cs")
	public void cs()
	{
		String access_token = BaseServiceManager.wechatService
				.getHylAccess_token();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ access_token;
		String json = "{\"button\":[{    \"type\":\"view\",\"name\":\"进入好牙乐    \",\"url\":\"http://hyl.leshasha.com\"}]}";
		String result= HttpRequestHelper.sendJsonPost(url, json);
		System.out.println(result);
	}
}
