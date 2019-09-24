package com.lss.core.dao;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.DoctorLogin;

public interface DoctorLoginMapper extends AbstractMapper<DoctorLogin, Integer> {
	/**
	 * 查询by、openid
	 * 
	 * @return
	 */
	DoctorLogin queryByOpenid(String openid);
	
	/**
	 * 查询 by userid
	 * @param userid
	 * @return
	 */
	DoctorLogin queryByUserid(Integer doctorid);
}