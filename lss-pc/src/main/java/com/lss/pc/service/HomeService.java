package com.lss.pc.service;

import com.lss.core.pojo.Banner;
import com.lss.core.pojo.News;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.applets.UserInfoParams;
import com.lss.core.vo.pc.HomeParams;

public interface HomeService {
	/**
	 * 首页banner
	 * 
	 * @return
	 */
	ReturnVo banner(Banner params);

	/**
	 * 新闻动态
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo news(HomeParams params);

	/**
	 * 诊所信息
	 * 
	 * @return
	 */
	ReturnVo clinicinfo();

	/**
	 * 新闻详情
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo newsdetails(News params);
	
	/**
	 * 用户表单信息提交
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo formsubmit(UserInfoParams params);
}
