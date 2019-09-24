package com.lss.applets.service;

import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.applets.UserInfoParams;

public interface CommonService {
	/**
	 * 诊所菜单
	 * 
	 * @return
	 */
	ReturnVo dropmenu();

	/**
	 * 标签
	 * 
	 * @param tag
	 * @return
	 */
	ReturnVo tags(Integer type);

	/**
	 * 用户表单信息提交
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo formsubmit(UserInfoParams params);
}
