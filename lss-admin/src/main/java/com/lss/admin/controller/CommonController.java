package com.lss.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.ServiceManager;
import com.lss.core.base.BaseServiceManager;
import com.lss.core.base.MapperManager;
import com.lss.core.pojo.BagOrder;
import com.lss.core.pojo.DoctorLogin;
import com.lss.core.util.DateUtils;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.FormUserParams;

/**
 * 公共 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("common")
public class CommonController {

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
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping("formsubmit")
	public ReturnVo formsubmit(@RequestBody FormUserParams params) {
		return ServiceManager.commonService.formsubmit(params);
	}

	/**
	 * 表单提交发送短信
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("sendfssms")
	public ReturnVo sendFormSubmitSMS(@RequestBody FormUserParams params) {
		return ServiceManager.commonService.sendFormSubmitSMS(params);
	}

	@RequestMapping("cs")
	public String cs(String orderno) {
		try {
			BagOrder order = MapperManager.bagOrderMapper
					.selectByPrimaryKey(orderno);
			String access_token = BaseServiceManager.wechatService
					.getHylAccess_token();
			if (ObjectUtil.isNotEmpty(access_token)) {
				String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
						+ access_token;
				// 查询用户openid
				DoctorLogin user = MapperManager.doctorLoginMapper
						.queryByUserid(order.getDoctorid());
				String json = "{\"touser\":\""
						+ "oSynx04Xn1lJC9xWebLDXqU0yo3M"
						+ "\",\"template_id\":\"PUx0hC_sTCUhvT5YFjxjvvnwNll4uG9OFxHWwTNe-_s\",\"data\":{\"first\": {\"value\":\"您好，您的订单已经支付成功！\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\""
						+ order.getOrderno()
						+ "\",\"color\":\"#173177\"},\"keyword2\": {\"value\":\""
						+ order.getTitle()
						+ "\",\"color\":\"#173177\"},\"keyword3\": {\"value\":\""
						+ String.valueOf(order.getAmount())
						+ "元\",\"color\":\"#173177\"},\"keyword4\": {\"value\":\""
						+ DateUtils.formatDateTime(order.getPaytime())
						+ "\",\"color\":\"#173177\"},\"remark\":{\"value\":\"感谢您的信任，我们会尽快跟进您的订单，请保持手机畅通！\",\"color\":\"#173177\"}}}";
				String result = HttpRequestHelper.sendJsonPost(url, json);
				log.info(result);
			}
		} catch (Exception e) {
			log.error("发送支付成功模板消息错误", e);
		}
		return "success";
	}
}
