package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Doctor;
import com.lss.core.vo.admin.DoctorMenu;
import com.lss.core.vo.admin.DoctorVo;
import com.lss.core.vo.admin.params.DoctorParams;
import com.lss.core.vo.pc.DoctorListVo;
import com.lss.core.vo.pc.HomeParams;

public interface DoctorMapper extends AbstractMapper<Doctor, Integer> {

	/**
	 * 后台查询医生数量
	 * 
	 * @param params
	 * @return
	 */
	Integer doctorCount(DoctorParams params);

	/**
	 * 后台查询医生列表
	 * 
	 * @param params
	 * @return
	 */
	List<DoctorVo> doctorList(DoctorParams params);

	/**
	 * 根据诊所查询医生
	 * 
	 * @param clinicid
	 * @return
	 */
	List<DoctorMenu> getDoctorMenus(Integer clinicid);

	/**
	 * 查询诊所医生
	 * 
	 * @param clinicid
	 * @return
	 */
	List<Doctor> getDoctorsList(Integer clinicid);

	/**
	 * 首页获取医生数量
	 * 
	 * @param params
	 * @return
	 */
	int homeCount(HomeParams params);

	/**
	 * 首页获取医生列表
	 * 
	 * @param params
	 * @return
	 */
	List<DoctorVo> homeList(HomeParams params);

	/**
	 * 查询医生信息
	 * 
	 * @param doctorid
	 * @return
	 */
	DoctorVo queryByID(Integer doctorid);

	/**
	 * 其他医生（随机其他几位医生）
	 * 
	 * @return
	 */
	List<DoctorListVo> otherList(Integer doctorid);

	/**
	 * 首页门诊医生
	 * 
	 * @param clinicid
	 * @return
	 */
	List<DoctorListVo> clinicDoctorList(Integer clinicid);
}