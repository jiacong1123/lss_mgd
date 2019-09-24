package com.lss.hyl.service;

import java.util.Map;

import com.lss.core.pojo.BagOrder;
import com.lss.core.pojo.DoctorBag;
import com.lss.core.vo.ReturnVo;

public interface CommonService {
	/**
	 * 获取上传token
	 * 
	 * @return
	 */
	ReturnVo uploadToken();

	/**
	 * 产品包列表
	 * 
	 * @return
	 */
	ReturnVo baglist();

	/**
	 * 产品包详情
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo bagdetails(DoctorBag params);

	/**
	 * 微信支付统一下单
	 * 
	 * @param orderno
	 * @return
	 */
	Map<String, String> wxPay(BagOrder order);

	/**
	 * 支付回调
	 * 
	 * @param orderno
	 * @param amount
	 * @return
	 */
	boolean payBack(String orderno, Double amount, String transaction_id);
}
