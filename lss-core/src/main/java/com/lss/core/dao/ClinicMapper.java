package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Clinic;
import com.lss.core.vo.admin.clinicMenu;
import com.lss.core.vo.admin.params.ClinicParams;
import com.lss.core.vo.pc.ClinicVo;

public interface ClinicMapper extends AbstractMapper<Clinic, Integer> {

	/**
	 * 诊所数量
	 * 
	 * @param params
	 * @return
	 */
	Integer clinicCount(ClinicParams params);

	/**
	 * 诊所列表
	 * 
	 * @param params
	 * @return
	 */
	List<Clinic> clinicList(ClinicParams params);

	/**
	 * 诊所菜单
	 * 
	 * @return
	 */
	List<clinicMenu> clinicMenuList();

	/**
	 * 首页诊所列表
	 * 
	 * @param params
	 * @return
	 */
	List<Clinic> homeClinicList(Clinic params);

	/**
	 * 查询诊所
	 * 
	 * @param clinicid
	 * @return
	 */
	ClinicVo queryByID(Integer clinicid);
}