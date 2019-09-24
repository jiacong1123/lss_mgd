package com.lss.hyl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.lss.core.base.BaseServiceManager;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.constant.WxPayConfig;
import com.lss.core.pojo.BagOrder;
import com.lss.core.pojo.DoctorBag;
import com.lss.core.pojo.DoctorLogin;
import com.lss.core.util.DateUtils;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.hyl.service.CommonService;
import com.qiniu.util.Auth;

@Service
public class CommonServiceImpl implements CommonService {
	private static final Logger log = LoggerFactory
			.getLogger(CommonServiceImpl.class);

	@Override
	public ReturnVo uploadToken() {
		ReturnVo result = new ReturnVo();
		// 先从缓存获取
		String token = RedisUtil.getString(SystemConstant.uploadToken);
		if (ObjectUtil.isEmpty(token)) {
			// 获取上传token
			Auth auth = Auth.create(SystemConstant.qiniuAccessKey,
					SystemConstant.qiniuSecretKey);
			token = auth.uploadToken("images", null, 86400, null);// 默认 24小时过期
			RedisUtil.setString(SystemConstant.uploadToken, token, 82800);// 防止失效存储23小时
		}
		result.setObj(token);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo baglist() {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.doctorBagMapper.getDoctorBags());
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo bagdetails(DoctorBag params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.doctorBagMapper.selectByPrimaryKey(params
				.getBagid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public Map<String, String> wxPay(BagOrder order) {
		try {
			// 查询医生信息
			DoctorLogin doctor = MapperManager.doctorLoginMapper
					.selectByPrimaryKey(order.getDoctorid());
			// 商品名称
			String body = "购买-" + order.getTitle();
			Map<String, String> data = new HashMap<String, String>();
			data.put("body", body);
			data.put("out_trade_no", order.getOrderno());
			String amount = String.valueOf((int) (order.getAmount() * 100));
			data.put("total_fee", amount);
			data.put("spbill_create_ip", "127.0.0.1");
			data.put("notify_url", WxPayConfig.notify_url);
			data.put("trade_type", WxPayConfig.TRADETYPE);
			data.put("openid", doctor.getOpenid());

			MyConfig config = new MyConfig();
			WXPay wxPay = new WXPay(config);
			Map<String, String> map = wxPay.unifiedOrder(data);
			String return_code = map.get("return_code");// 返回状态码

			if ("SUCCESS".equals(return_code)) {
				Map<String, String> response = new HashMap<String, String>();// 返回给小程序端需要的参数
				String prepay_id = map.get("prepay_id");// 返回的预付单信息
				response.put("appId", WxPayConfig.appid);
				response.put("nonceStr", map.get("nonce_str"));
				response.put("package", "prepay_id=" + prepay_id);
				response.put("signType", WxPayConfig.SIGNTYPE);
				String timeStamp = String
						.valueOf(System.currentTimeMillis() / 1000);
				response.put("timeStamp", timeStamp);// 这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
				// 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
				String paySign = WXPayUtil.generateSignature(response,
						WxPayConfig.key);
				response.put("paySign", paySign);
				return response;
			}
		} catch (Exception e) {
			log.error("支付统一下单错误:", e);
		}
		return null;
	}

	@Override
	public boolean payBack(String orderno, Double amount, String transaction_id) {
		// 查询订单
		BagOrder order = MapperManager.bagOrderMapper
				.selectByPrimaryKey(orderno);
		// 没有支付过
		if (order != null && order.getStatus().intValue() == 0) {
			// 验证金额
			if (amount.equals(order.getAmount())) {
				Date dt = new Date();
				// 修改订单状态
				BagOrder params = new BagOrder();
				params.setOrderno(orderno);
				params.setPaytime(dt);
				params.setPayorderno(transaction_id);
				if (MapperManager.bagOrderMapper.upPayStatus(params) > 0) {
					// TODO 支付成功发送推送
					sendTemplate(order);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 发送支付成功模板消息
	 */
	private void sendTemplate(BagOrder order) {
		try {
			String access_token = BaseServiceManager.wechatService
					.getHylAccess_token();
			if (ObjectUtil.isNotEmpty(access_token)) {
				String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
						+ access_token;
				// 查询用户openid
				DoctorLogin user = MapperManager.doctorLoginMapper
						.queryByUserid(order.getDoctorid());
				String json = "{\"touser\":\""
						+ user.getOpenid()
						+ "\",\"template_id\":\"PUx0hC_sTCUhvT5YFjxjvvnwNll4uG9OFxHWwTNe-_s\",\"data\":{\"first\": {\"value\":\"您好，您的订单已经支付成功！\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\""
						+ order.getOrderno()
						+ "\",\"color\":\"#173177\"},\"keyword2\": {\"value\":\""
						+ order.getTitle()
						+ "\",\"color\":\"#173177\"},\"keyword3\": {\"value\":\""
						+ String.valueOf(order.getAmount())
						+ "元\",\"color\":\"#173177\"},\"keyword4\": {\"value\":\""
						+ DateUtils.formatDateTime(order.getCreatetime())
						+ "\",\"color\":\"#173177\"},\"remark\":{\"value\":\"感谢您的信任，我们会尽快跟进您的订单，请保持手机畅通！\",\"color\":\"#173177\"}}}";
				String result = HttpRequestHelper.sendJsonPost(url, json);
				log.info(result);
			}
		} catch (Exception e) {
			log.error("发送支付成功模板消息错误", e);
		}
	}
}
