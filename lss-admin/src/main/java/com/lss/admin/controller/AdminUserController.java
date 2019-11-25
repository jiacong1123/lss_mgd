package com.lss.admin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.RolePopedom;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.EcPhoneUtil;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.EcGetUserVo;
import com.lss.core.vo.admin.params.AdminParams;
import com.lss.core.vo.admin.params.PersonParams;
import com.lss.core.vo.admin.params.PopedomParams;
import com.lss.core.vo.admin.params.RoleParams;

/**
 * 管理员 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("admin")
public class AdminUserController extends BaseController {

	private static final String GET_USERID_URL = "https://open.workec.com/user/findUserInfoById";
	private static String GET_EC_TOKEN_KEY = "getECtoken";

	/**
	 * 添加/编辑管理员
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Admin admin) {
		return ServiceManager.adminService.save(admin);
	}

	/**
	 * 管理员列表
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody AdminParams params) {
		return ServiceManager.adminService.list(params);
	}

	/**
	 * 启用/禁用/删除
	 * 
	 * @return
	 */
	@RequestMapping("operate")
	public ReturnVo operate(@RequestBody Admin admin) {
		return ServiceManager.adminService.operate(admin);
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping("rolelist")
	public ReturnVo rolelist() {
		return ServiceManager.adminService.rolelist();
	}

	/**
	 * 编辑用户角色
	 * 
	 * @return
	 */
	@RequestMapping("editrole")
	public ReturnVo editrole(@RequestBody RoleParams params) {
		return ServiceManager.adminService.editrole(params);
	}

	/**
	 * 权限列表
	 * 
	 * @return
	 */
	@RequestMapping("popedomlist")
	public ReturnVo popedomlist() {
		return ServiceManager.adminService.popedomlist();
	}

	/**
	 * 编辑角色权限
	 * 
	 * @return
	 */
	@RequestMapping("editpopedom")
	public ReturnVo editpopedom(@RequestBody PopedomParams params) {
		return ServiceManager.adminService.editpopedom(params);
	}

	/**
	 * 角色权限
	 * 
	 * @return
	 */
	@RequestMapping("rolepopedom")
	public ReturnVo rolepopedom(@RequestBody RolePopedom params) {
		return ServiceManager.adminService.rolepopedom(params);
	}

	/**
	 * 菜单列表
	 * 
	 * @return
	 */
	@RequestMapping("menulist")
	public ReturnVo menulist() {
		return ServiceManager.adminService.menulist(this.loginAdmin);
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("changepassword")
	public ReturnVo changepassword(@RequestBody AdminParams params) {
		return ServiceManager.adminService.changepassword(params, this.loginAdmin);
	}

	/**
	 * 标签
	 * 
	 * @return
	 */
	@RequestMapping("tags")
	public ReturnVo tags(@RequestBody WorkTag tag) {
		return ServiceManager.workOrderService.tags(tag);
	}

	/**
	 * 人员(不带权限查所有人)
	 * 
	 * @return
	 */
	@RequestMapping("personnel")
	public ReturnVo personnel(@RequestBody PersonParams params) {
		return ServiceManager.adminService.personnel(params);
	}

	/**
	 * 人员列表(带权限)
	 * 
	 * @return
	 */
	@RequestMapping("personnelList")
	public ReturnVo personnelList() {
		PersonParams params = new PersonParams();
		return ServiceManager.adminService.personnelList(params, loginAdmin);
	}

	/**
	 * 员工绑定话机及小号
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.05.08
	 */
	@RequestMapping("bindPhone")
	public ReturnVo bindPhone(@RequestBody Admin params) {
		return ServiceManager.adminService.updateCallerNos(params);
	}

	/**
	 * 获取EC用户ID
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.05.08
	 */
	@RequestMapping("getEcUserId")
	public ReturnVo getEcUserId(@RequestBody Admin params) {
		ReturnVo returnVo = new ReturnVo();
		String value = RedisUtil.getString(params.getPhone());
		if(StringUtils.isNotEmpty(value)) {
			returnVo.setResult(ResponseCode.success);
			returnVo.setMsg(ResponseCode.successMsg);
		}else {
			try {
				String token = EcPhoneUtil.getToken(GET_EC_TOKEN_KEY);
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("account", params.getPhone());
				JSONObject post = EcPhoneUtil.httpPost(GET_USERID_URL,jsonParam,token);
				EcGetUserVo ecGetUserVo = post.getObject("data", EcGetUserVo.class);
				int f_user_id = ecGetUserVo.getF_user_id();
				params.setEcUserId(f_user_id+"");
				//存入redis
				RedisUtil.setString(params.getPhone(), params.getEcUserId());
				ServiceManager.adminService.updateByPhone(params);
				returnVo.setResult(ResponseCode.success);
				returnVo.setMsg(ResponseCode.successMsg);
			} catch (Exception e) {
				returnVo.setResult(ResponseCode.failure);
				returnVo.setMsg(ResponseCode.failureMsg);
			}
		}
		return returnVo;
	}

}
