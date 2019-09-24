package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lss.admin.service.BagOrderService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.BagOrder;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.hyl.OrderParams;

@Service
public class BagOrderServiceImpl implements BagOrderService {

	@Override
	public ReturnVo list(OrderParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.bagOrderMapper.adminBagOrderCount(params));
		result.setObj(MapperManager.bagOrderMapper.adminBagOrderList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo confirmpay(String orderno) {
		ReturnVo result = new ReturnVo();
		BagOrder order = MapperManager.bagOrderMapper
				.selectByPrimaryKey(orderno);
		if (order == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (order.getStatus().intValue() == 1 || order.getPaytype() != 2) {
			result.setResult(ResponseCode.failure);
			result.setMsg("订单已支付");
			return result;
		}
		BagOrder params = new BagOrder();
		params.setOrderno(orderno);
		params.setPaytime(new Date());
		if (MapperManager.bagOrderMapper.confirmPayment(params) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

}
