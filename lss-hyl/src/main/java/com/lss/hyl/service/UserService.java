package com.lss.hyl.service;

import com.lss.core.pojo.BagOrder;
import com.lss.core.pojo.DoctorUser;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.hyl.BindPhoneParams;
import com.lss.core.vo.hyl.OrderParams;

public interface UserService {
	/**
	 * 微信登录
	 * 
	 * @param code
	 * @return
	 */
	ReturnVo login(String code);

	/**
	 * 用户信息
	 * 
	 * @param doctorid
	 * @return
	 */
	ReturnVo info(Integer doctorid);

	/**
	 * 编辑用户信息
	 * 
	 * @param user
	 * @return
	 */
	ReturnVo update(DoctorUser user);

	/**
	 * 发送绑定手机验证码
	 * 
	 * @return
	 */
	ReturnVo sendsms(BindPhoneParams params);

	/**
	 * 绑定手机号
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo bindphone(BindPhoneParams params);

	/**
	 * 我的订单
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo myorders(OrderParams params);

	/**
	 * 下单
	 * 
	 * @param order
	 * @return
	 */
	ReturnVo placeorder(BagOrder order);
}
