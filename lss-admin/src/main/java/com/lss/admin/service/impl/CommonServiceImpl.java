package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lss.admin.service.CommonService;
import com.lss.core.base.BaseServiceManager;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.FormatUtil;
import com.lss.core.util.GenerateOrderno;
import com.lss.core.util.MD5;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.RandomString;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.params.FormUserParams;
import com.qiniu.util.Auth;

@Service
public class CommonServiceImpl implements CommonService {

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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo formsubmit(FormUserParams params) {
		ReturnVo result = new ReturnVo();
		String msg = checkUserInfo(params);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		// 验证短信验证码
		if (ObjectUtil.isNotEmpty(params.getCode())) {
			String code = RedisUtil
					.getString(SystemConstant.lssSmsCodeFormSubmit
							+ params.getPhone());
			if (!params.getCode().equals(code)) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg("验证码错误");
				return result;
			}
		}
		// 查询用户信息
		UserVo user = MapperManager.userMapper.queryByPhone(params.getPhone());
		if (user == null) {
			Date dt = new Date();
			// 插入用户信息
			User model = new User();
			model.setPhone(params.getPhone());
			model.setPassword(MD5.encrypt("123456"));
			model.setName(params.getName());
			model.setNotes(params.getUrl());
			// 查询来源
			model.setSourceid(getSourceid(params.getUrl()));
			model.setSourcedate(dt);
			model.setCreatetime(dt);
			if (MapperManager.userMapper.insertSelective(model) > 0) {
			  // 2019-11-06 新需求:线上生产的客户数据不插入待分配工单里边,生产一个状态为8线上新导入工单
			  WorkOrder order = new WorkOrder(); String orderno = GenerateOrderno.getInstance().GenerateOrder(); order.setOrderno(orderno);
			  order.setUserid(model.getUserid());
			  order.setProjectid(getProjectid(params.getUrl())); order.setCreatetime(dt);
			  order.setWorknotes(params.getNotes());
			  order.setStatus(8);
			  MapperManager.workOrderMapper.insertSelective(order);
			}
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	/**
	 * 根据请求url获取来源
	 * 
	 * @param url
	 * @return
	 */
	private Integer getSourceid(String url) {
		// 查询来源
		WorkTag tagPar = new WorkTag();
		tagPar.setType(2);
		if (ObjectUtil.isEmpty(url))
			tagPar.setTagname("微信小程序");
		else {//http://m.lesasa.wang/163/zj/zj_sz_meitu.html
			if (url.indexOf("wsd") != -1) {
				tagPar.setTagname("微思顿");
			}else if(url.indexOf("meitu")!= -1){
				tagPar.setTagname("美图");
			}else if (url.indexOf("wps") != -1) {
				tagPar.setTagname("WPS");
			}else if(url.indexOf("oppo")!= -1){
				tagPar.setTagname("OPPO");
			}else if(url.indexOf("fenghuang")!= -1){
				tagPar.setTagname("凤凰");
			}else if(url.indexOf("163/zj/zj_sz_meitu.html")!= -1){
				tagPar.setTagname("美图");
			}else if(url.indexOf("163/zj/zj_test_dongfang.html")!= -1){
				tagPar.setTagname("东方头条");
			}else if(url.indexOf("163/zj/zj_sz_uc.html")!= -1){
				tagPar.setTagname("UC信息流");
			}else if(url.indexOf("163/zj/zj_sz_tui.html")!= -1){
				tagPar.setTagname("推啊");
			}else if (url.indexOf("baidu") != -1) {
				tagPar.setTagname("百度信息流");
			} else if (url.indexOf("meiyou") != -1) {
				tagPar.setTagname("美柚");
			} else if (url.indexOf("XMLY") != -1) {
				tagPar.setTagname("喜马拉雅");
			} else if (url.indexOf("fuyi") != -1) {
				tagPar.setTagname("扶翼");
			} else if (url.indexOf("zhihu") != -1) {
				tagPar.setTagname("知乎");
			} else if (url.indexOf("momo") != -1) {
				tagPar.setTagname("陌陌");
			} else if (url.indexOf("sougou") != -1) {
				tagPar.setTagname("搜狗信息流");
			} else if (url.indexOf("pyq") != -1) {
				tagPar.setTagname("朋友圈广告");
			} else if (url.indexOf("fensitong") != -1) {
				tagPar.setTagname("粉丝通");
			} else if (url.indexOf("pay") != -1) {
				tagPar.setTagname("支付后广告");
			}else if (url.indexOf("/zwcs.leshasha.com/imovie20190426") != -1) {
				tagPar.setTagname("爱电影网");
			} else if (url.indexOf("zwcs.leshasha.com") != -1) {
				tagPar.setTagname("影院活动");
			} else if (url.indexOf("wifi") != -1) {
				tagPar.setTagname("wifi万能钥匙");
			} else if (url.indexOf("wlkk") != -1) {
				tagPar.setTagname("微鲤看看");
			} else if (url.indexOf("aiqiyi") != -1) {
				tagPar.setTagname("爱奇艺");
			} else if (url.indexOf("/sem/") != -1) {
				tagPar.setTagname("sem");
			} else if (url.indexOf("shunzhi") != -1) {
				tagPar.setTagname("瞬知");
			} else if (url.indexOf("www.lesasa.com") != -1
					|| url.indexOf("www.leshasha.com") != -1) {
				tagPar.setTagname("官网");
			}else if (url.indexOf("wy") != -1) {
				tagPar.setTagname("网易信息流");
			}else if (url.indexOf("marking") != -1) {
				tagPar.setTagname("市场部_异业合作");
			}else {
				tagPar.setTagname("官网");
			}
		}
		WorkTag tag = MapperManager.workTagMapper.getWorkTag(tagPar);
		if (tag != null)
			return tag.getTagid();
		return null;
	}

	/**
	 * 根据请求url获取来源
	 * 
	 * @param url
	 * @return
	 */
	private Integer getProjectid(String url) {
		// 预约项目
		if (url.indexOf("zj") != -1) {
			return 1;
		} else if (url.indexOf("zz") != -1) {
			return 2;
		} else if (url.indexOf("tm") != -1) {
			return 3;
		} else {
			return null;
		}
	}

	/**
	 * 校验用户信息
	 * 
	 * @param params
	 * @return
	 */
	private String checkUserInfo(FormUserParams params) {
		if (ObjectUtil.isEmpty(params.getPhone()))
			return "请填写手机号";
		else {
			if (!FormatUtil.matchMobile(params.getPhone()))
				return "手机格式错误";
		}
		return "";
	}

	@Override
	public ReturnVo sendFormSubmitSMS(FormUserParams params) {
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
			RedisUtil.setString(
					SystemConstant.lssSmsCodeFormSubmit + params.getPhone(),
					code, 300);
			result.setResult(ResponseCode.success);
			result.setMsg("发送成功");
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(res.getMsg());
		}
		return result;
	}
}
