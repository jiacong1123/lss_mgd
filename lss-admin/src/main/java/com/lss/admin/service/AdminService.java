package com.lss.admin.service;

import java.util.List;

import com.lss.core.pojo.Admin;
import com.lss.core.pojo.RolePopedom;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.params.AdminParams;
import com.lss.core.vo.admin.params.PersonParams;
import com.lss.core.vo.admin.params.PopedomParams;
import com.lss.core.vo.admin.params.RoleParams;

public interface AdminService {

	/**
	 * 登录
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo login(AdminParams params);

	/**
	 * 添加/编辑管理员
	 * 
	 * @param admin
	 * @return
	 */
	ReturnVo save(Admin admin);

	/**
	 * 管理员列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(AdminParams params);

	/**
	 * 启用/禁用/删除
	 * 
	 * @param admin
	 * @return
	 */
	ReturnVo operate(Admin admin);

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	ReturnVo rolelist();

	/**
	 * 编辑用户角色
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo editrole(RoleParams params);

	/**
	 * 权限列表
	 * 
	 * @return
	 */
	ReturnVo popedomlist();

	/**
	 * 编辑角色权限
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo editpopedom(PopedomParams params);

	/**
	 * 角色权限
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo rolepopedom(RolePopedom params);

	/**
	 * 菜单列表
	 * 
	 * @param admin
	 * @return
	 */
	ReturnVo menulist(LoginAdmin admin);

	/**
	 * 修改密码
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo changepassword(AdminParams params, LoginAdmin loginAdmin);

	/**
	 * 查询人员列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo personnel(PersonParams params);
	
	/**
	 * 员工绑定话机及小号
	 * @param admin
	 * @return
	 */
	ReturnVo updateCallerNos(Admin admin);
	
	/**
	 * 微信登录
	 * 
	 * @param code 微信公众号授权code
	 * @return
	 * @author lhy 2019.06.19
	 */
	ReturnVo loginByWx(String code);
	
	/**
	 *   根据电销组长查整组成员的id
	 * @param adminid
	 * @return
	 */
	List<Integer> selectGroupAdminids(Integer adminid);

	/**
	 * 查询人员列表
	 * @param params
	 * @param loginAdmin
	 * @return
	 */
	ReturnVo personnelList(PersonParams params, LoginAdmin loginAdmin);
}
