package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.dto.OrgDto;
import com.lss.core.pojo.Admin;
import com.lss.core.vo.admin.AdminListVo;
import com.lss.core.vo.admin.PersonnelVo;
import com.lss.core.vo.admin.params.AdminParams;
import com.lss.core.vo.admin.params.PersonParams;

public interface AdminMapper extends AbstractMapper<Admin, Integer> {
	/**
	 * 登录
	 * 
	 * @param params
	 * @return
	 */
	Admin loginAdmin(AdminParams params);

	/**
	 * 管理员数量
	 * 
	 * @param params
	 * @return
	 */
	int adminCount(AdminParams params);

	/**
	 * 管理员列表
	 * 
	 * @param params
	 * @return
	 */
	List<AdminListVo> adminList(AdminParams params);

	/**
	 * 操作
	 * 
	 * @param admin
	 * @return
	 */
	int operate(Admin admin);

	/**
	 * 查询用户名是否存在
	 * 
	 * @param loginame
	 * @return
	 */
	int checkLoginame(String loginame);

	/**
	 * 根据姓名查看管理员
	 * 
	 * @param name
	 * @return
	 */
	Admin queryByName(String name);
	
	/**
	 * 查询人员列表
	 * @param params
	 * @return
	 */
	List<PersonnelVo> queryPersonnels(PersonParams params);
	
	List<Admin> selectAll();
	
	Admin selectByLoginname(String loginame);
	
	/**
	 * 查询该机构下的所有员工ID
	 * @param parentOrgId
	 * @return
	 */
	List<Integer> selectByOrgId(Integer parentOrgId);
	
	
	/**
	 * 管理员列表
	 * 
	 * @param params
	 * @return
	 */
	List<Admin> findAdminList(AdminParams params);
	
	/**
	 * 数量
	 * @param params
	 * @return
	 */
	int findAdminListCount(AdminParams params);
	
	
	/**
	 * 查询同机构及其组长员工ID
	 * @param parentOrgId
	 * @return
	 */
	List<Integer> selectByOrg(OrgDto orgDto);

	/**
	 * @param params
	 * @return
	 */
	List<PersonnelVo> queryPersonnelsList(PersonParams params);

	void updateByPhone(Admin params);

	List<String> selectUserIds();

	Admin selectAdminIdByUserId(int userId);

	Admin selectAdminIdByMicroUserId(String microUserId);
}