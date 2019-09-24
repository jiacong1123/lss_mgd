package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.DoctorBag;

public interface DoctorBagMapper extends AbstractMapper<DoctorBag, Integer> {

	/**
	 * 获取医生包列表
	 * 
	 * @return
	 */
	List<DoctorBag> getDoctorBags();
}