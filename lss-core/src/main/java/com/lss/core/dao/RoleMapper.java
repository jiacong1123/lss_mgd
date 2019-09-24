package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Role;

public interface RoleMapper extends AbstractMapper<Role, Integer> {
	/**
	 * 查询用户角色列表
	 * 
	 * @return
	 */
	List<Role> getRoles(Integer adminid);

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	List<Role> roleList();
}