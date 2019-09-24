package com.lss.hyl.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.core.pojo.BagOrder;
import com.lss.core.pojo.DoctorUser;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.hyl.BindPhoneParams;
import com.lss.core.vo.hyl.OrderParams;
import com.lss.hyl.base.BaseController;
import com.lss.hyl.base.ServiceManager;

/**
 * 用户 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	/**
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping("info")
	public ReturnVo info() {
		return ServiceManager.userService.info(doctorid);
	}

	/**
	 * 编辑用户
	 * 
	 * @return
	 */
	@RequestMapping("update")
	public ReturnVo update(@RequestBody DoctorUser user) {
		user.setDoctorid(doctorid);
		return ServiceManager.userService.update(user);
	}

	/**
	 * 发送绑定手机验证码
	 * 
	 * @return
	 */
	@RequestMapping("sendsms")
	public ReturnVo sendsms(@RequestBody BindPhoneParams params) {
		params.setDoctorid(doctorid);
		return ServiceManager.userService.sendsms(params);
	}

	/**
	 * 绑定手机
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("bindphone")
	public ReturnVo bindphone(@RequestBody BindPhoneParams params) {
		params.setDoctorid(doctorid);
		return ServiceManager.userService.bindphone(params);
	}

	/**
	 * 我的订单
	 * 
	 * @return
	 */
	@RequestMapping("myorders")
	public ReturnVo myorders(@RequestBody OrderParams params) {
		params.setDoctorid(doctorid);
		return ServiceManager.userService.myorders(params);
	}

	/**
	 * 下单
	 * 
	 * @return
	 */
	@RequestMapping("placeorder")
	public ReturnVo placeorder(@RequestBody BagOrder order) {
		order.setDoctorid(doctorid);
		return ServiceManager.userService.placeorder(order);
	}
}
