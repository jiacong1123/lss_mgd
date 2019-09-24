package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.User;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.UserExeclParams;
import com.lss.core.vo.admin.params.UserParams;

/**
 * 客户 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	/**
	 * 添加/编辑用户
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody User user) {
		return ServiceManager.userService.save(user);
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody UserParams params) {
		return ServiceManager.userService.list(params);
	}

	/**
	 * 批量添加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("batchadd")
	public ReturnVo batchadd(@RequestParam("upfile") MultipartFile file)
			throws Exception {
		return ServiceManager.userService.batchadd(file, loginAdmin);
	}

	/**
	 * 批量导出
	 */
	@RequestMapping("batchexport")
	public void batchexport(UserExeclParams params) {
		ServiceManager.userService.batchexport(params, response);
	}
	
	/**
	 * 删除用户
	 * @author lhy 2019.05.16
	 */
	@RequestMapping("remove")
	public ReturnVo remove(@RequestBody User params) {
		return ServiceManager.userService.deleteUser(params,loginAdmin);
	}
	
}
