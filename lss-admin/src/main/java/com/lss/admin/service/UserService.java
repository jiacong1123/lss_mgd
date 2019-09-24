package com.lss.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.params.UserExeclParams;
import com.lss.core.vo.admin.params.UserParams;

public interface UserService {
	/**
	 * 添加/编辑用户
	 * 
	 * @param user
	 * @return
	 */
	ReturnVo save(User user);

	/**
	 * 用户列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(UserParams params);

	/**
	 * 批量导入客户信息
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	ReturnVo batchadd(MultipartFile file, LoginAdmin loginAdmin)
			throws Exception;

	/**
	 * 添加客户信息
	 * 
	 * @param temp
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	int insertUser(List<String> temp, List<WorkTag> sourcelist,
			List<WorkTag> projectlist, LoginAdmin loginAdmin);

	/**
	 * 验证用户信息
	 * 
	 * @param user
	 * @return
	 */
	String checkUser(User user);

	/**
	 * 批量导出客服信息
	 * 
	 * @param params
	 * @param response
	 */
	void batchexport(UserExeclParams params, HttpServletResponse response);
	
	/**
	 * 删除用户信息
	 * 
	 * @param user
	 * @return
	 * @author lhy 2019.05.16 
	 */
	ReturnVo deleteUser(User user, LoginAdmin admin);
	
	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @author lhy 2019.08.07
	 */
	void updateUser(User user);
}
