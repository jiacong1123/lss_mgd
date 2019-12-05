package com.lss.admin.service;

import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.hyl.OrderParams;

public interface BagOrderService {
	/**
	 * 产品包订单列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(OrderParams params);

	/**
	 * 确认支付
	 * 
	 * @param orderno
	 * @return
	 */
	ReturnVo confirmpay(String orderno);
}
