/**
 * 
 */
package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.util.MD5;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;

/**
 * 
 * 
 * 类说明：客户直通车对接。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月13日
 */
@RestController
@RequestMapping("ztc")
public class KhztcController extends BaseController{
	
	/**
	 * 获取员工认证信息
	 * @return
	 */
	@RequestMapping("getAdmin")
	public ReturnVo findAdmin(Admin param) {
		if (ObjectUtil.isEmpty(param.getLoginame())) {
			throw new LssException(ResponseCode.parameterError, "登录名为空");
		}
		if (ObjectUtil.isEmpty(param.getLoginpwd())) {
			throw new LssException(ResponseCode.parameterError, "登录密码为空");
		}
		Admin admin=MapperManager.adminMapper.selectByLoginname(param.getLoginame());
		
		if (admin == null) {
			throw new LssException(ResponseCode.failure, "未找到该用户");
		}
		String loginpwd= MD5.encrypt(admin.getLoginpwd().substring(0, 8));
		
		if (!loginpwd.equals(param.getLoginpwd())) {
			throw new LssException(ResponseCode.failure, "密码不正确");
		}
		Admin rtAdmin=new Admin();
		rtAdmin.setAdminid(admin.getAdminid());
		rtAdmin.setLoginame(admin.getLoginame());
		
		ReturnVo result=new ReturnVo();
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		result.setObj(rtAdmin);
		return result;
	}
}
