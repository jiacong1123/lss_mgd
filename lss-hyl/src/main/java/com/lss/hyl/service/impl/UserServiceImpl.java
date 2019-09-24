package com.lss.hyl.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lss.core.base.BaseServiceManager;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.pojo.BagOrder;
import com.lss.core.pojo.DoctorBag;
import com.lss.core.pojo.DoctorLogin;
import com.lss.core.pojo.DoctorUser;
import com.lss.core.util.FormatUtil;
import com.lss.core.util.GenerateOrderno;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.RandomString;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.hyl.BindPhoneParams;
import com.lss.core.vo.hyl.OrderParams;
import com.lss.hyl.base.ServiceManager;
import com.lss.hyl.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	public ReturnVo login(String code) {
		try {

			ReturnVo result = new ReturnVo();
			if (ObjectUtil.isEmpty(code)) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg(ResponseCode.parameterErrorMsg);
				return result;
			}
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
					+ SystemConstant.appid
					+ "&secret="
					+ SystemConstant.secret
					+ "&code=" + code + "&grant_type=authorization_code";
			String json = HttpRequestHelper.sendGet(url, null);
			if (ObjectUtil.isNotEmpty(json)) {
				JSONObject object = JSONObject.parseObject(json);
				String openid = object.getString("openid");
				if (ObjectUtil.isNotEmpty(openid)) {
					// 查询用户id
					DoctorLogin doctor = MapperManager.doctorLoginMapper
							.queryByOpenid(openid);
					if (doctor == null) {
						// 用户不存在添加
						Date dt = new Date();
						DoctorUser user = new DoctorUser();
						user.setCreatetime(dt);
						MapperManager.doctorUserMapper.insertSelective(user);
						doctor = new DoctorLogin();
						doctor.setDoctorid(user.getDoctorid());
						doctor.setType(1);
						doctor.setOpenid(openid);
						doctor.setCreatetime(dt);
						MapperManager.doctorLoginMapper.insertSelective(doctor);
					}
					// 返回登录信息
					result.setObj(doctor.getDoctorid());
					result.setResult(ResponseCode.success);
					result.setMsg(ResponseCode.successMsg);
					return result;
				}
			}
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
			return result;
		} catch (Exception e) {
			log.error("微信登录失败", e);
			throw e;
		}
	}

	@Override
	public ReturnVo info(Integer doctorid) {
		ReturnVo result = new ReturnVo();
		DoctorUser user = MapperManager.doctorUserMapper
				.selectByPrimaryKey(doctorid);
		if (user != null) {
			// 格式化手机号
			if (ObjectUtil.isNotEmpty(user.getPhone())) {
				user.setPhone(FormatUtil.formatMobile(user.getPhone()));
			}
		}
		result.setObj(user);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo update(DoctorUser user) {
		ReturnVo result = new ReturnVo();
		// 不让修改手机号
		user.setPhone(null);
		user.setStatus(null);
		user.setCreatetime(null);
		if (MapperManager.doctorUserMapper.updateByPrimaryKeySelective(user) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo sendsms(BindPhoneParams params) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getPhone())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("手机号不能为空");
			return result;
		} else {
			if (!FormatUtil.matchMobile(params.getPhone())) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg("手机号格式错误");
				return result;
			}
		}
		// 生成验证码
		String code = RandomString.generateStringNum(4);
		ReturnVo res = BaseServiceManager.smsService.lssSendSMS(
				params.getPhone(), "【乐莎莎】您的验证码是" + code);
		if (res.getResult().intValue() == 0) {
			// 写入缓存
			RedisUtil.setString(SystemConstant.lssSmsCode + params.getPhone(),
					code, 180);
			result.setResult(ResponseCode.success);
			result.setMsg(res.getMsg());
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(res.getMsg());
		}
		return result;
	}

	@Override
	public ReturnVo bindphone(BindPhoneParams params) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getPhone())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("手机号不能为空");
			return result;
		} else {
			if (!FormatUtil.matchMobile(params.getPhone())) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg("手机号格式错误");
				return result;
			}
		}
		if (ObjectUtil.isEmpty(params.getCode())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("验证码不能为空");
			return result;
		}
		// 查询验证码
		String tempCode = RedisUtil.getString(SystemConstant.lssSmsCode
				+ params.getPhone());
		if (!params.getCode().equals(tempCode)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("验证码错误");
			return result;
		}
		DoctorUser user = MapperManager.doctorUserMapper
				.selectByPrimaryKey(params.getDoctorid());
		if (ObjectUtil.isEmpty(user.getPhone())) {
			user.setPhone(params.getPhone());
			if (MapperManager.doctorUserMapper
					.updateByPrimaryKeySelective(user) > 0) {
				RedisUtil.delete(SystemConstant.lssSmsCode + params.getPhone());
				result.setResult(ResponseCode.success);
				result.setMsg(ResponseCode.successMsg);
			} else {
				result.setResult(ResponseCode.failure);
				result.setMsg(ResponseCode.failureMsg);
			}
		} else {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("您已绑定过手机号");
		}
		return result;
	}

	@Override
	public ReturnVo myorders(OrderParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.bagOrderMapper.getBagOrderCount(params));
		result.setObj(MapperManager.bagOrderMapper.getBagOrderList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo placeorder(BagOrder order) {
		ReturnVo result = new ReturnVo();
		DoctorBag bag = MapperManager.doctorBagMapper.selectByPrimaryKey(order
				.getBagid());
		if (bag == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (order.getPaytype().intValue() == 2) {
			// 线下转账方式 只添加一遍订单
			if (MapperManager.bagOrderMapper.queryOrder(order) > 0) {
				result.setResult(ResponseCode.success);
				result.setMsg(ResponseCode.successMsg);
				return result;
			}
		}
		String orderno = GenerateOrderno.getInstance().GenerateOrder();
		order.setOrderno(orderno);
		order.setTitle(bag.getTitle());
		order.setImage(bag.getImage());
		order.setPrice(bag.getPrice());
		order.setCount(1);
		order.setAmount(bag.getPrice());
		order.setStatus(0);
		order.setCreatetime(new Date());
		if (MapperManager.bagOrderMapper.insertSelective(order) > 0) {
			if (order.getPaytype().intValue() == 1) {
				result.setObj(ServiceManager.commonService.wxPay(order));
			}
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}
}
