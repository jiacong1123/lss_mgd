package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.BagOrder;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.hyl.OrderParams;

/**
 * 产品包订单
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("bagorder")
public class BagOrderController extends BaseController {

	/**
	 * 订单列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody OrderParams params) {
		return ServiceManager.bagOrderService.list(params);
	}

	/**
	 * 确认支付
	 * 
	 * @return
	 */
	@RequestMapping("confirmpay")
	public ReturnVo confirmpay(@RequestBody BagOrder params) {
		return ServiceManager.bagOrderService.confirmpay(params.getOrderno());
	}
}
