package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.RolePopedom;

public interface RolePopedomMapper extends AbstractMapper<RolePopedom, Integer> {
	/**
	 * 删除角色权限
	 * 
	 * @return
	 */
	int deleteByRoleid(Integer roleid);

	/**
	 * 查询角色权限id
	 * 
	 * @param roleid
	 * @return
	 */
	List<Integer> queryRolePopedom(Integer roleid);
}