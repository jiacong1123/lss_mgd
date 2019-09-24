package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.User;
import com.lss.core.vo.admin.UserExeclVo;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.params.UserExeclParams;
import com.lss.core.vo.admin.params.UserParams;

public interface UserMapper extends AbstractMapper<User, Integer> {
	/**
	 * 验证手机号是否存在
	 * 
	 * @return
	 */
	int checkPhone(String phone);

	/**
	 * 获取用户数量
	 * 
	 * @param params
	 * @return
	 */
	int userCount(UserParams params);

	/**
	 * 用户列表
	 * 
	 * @param params
	 * @return
	 */
	List<UserVo> userList(UserParams params);

	/**
	 * 根据电话查询用户
	 * 
	 * @param phone
	 * @return
	 */
	UserVo queryByPhone(String phone);

	/**
	 * 查询用户
	 * 
	 * @param userid
	 * @return
	 */
	UserVo queryUser(Integer userid);
	
	/**
	 * 批量导出用户信息
	 * @param params
	 * @return
	 */
	List<UserExeclVo> exportExcel(UserExeclParams params);
	
	/**
	 * 根据微信查询用户
	 * 
	 * @param noWxAlias
	 * @return
	 * @author lhy 2019年6月20日
	 */
	UserVo queryByNoWxAlias(String noWxAlias);
	
	/**
	 * 验证微信是否存在
	 * 
	 * @return
	 */
	int checkNoWxAlias(String noWxAlias);
}