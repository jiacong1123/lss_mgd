package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.AdminRole;

public interface AdminRoleMapper extends AbstractMapper<AdminRole, Integer> {

	/**
	 * 查询用户角色
	 * 
	 * @param adminid
	 * @return
	 */
	List<Integer> queryRoleids(Integer adminid);

	/**
	 * 删除用户角色
	 * 
	 * @param adminid
	 * @return
	 */
	int deleteRoles(Integer adminid);
}