package com.lss.admin.service;

import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.FormUserParams;

public interface CommonService {
	/**
	 * 获取上传token
	 * 
	 * @return
	 */
	ReturnVo uploadToken();

	/**
	 * 用户表单信息提交
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo formsubmit(FormUserParams params);

	/**
	 * 表单提交发送短信
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo sendFormSubmitSMS(FormUserParams params);
}
