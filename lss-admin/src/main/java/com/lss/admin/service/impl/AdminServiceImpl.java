package com.lss.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.AdminService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.dao.IAdminLoginDao;
import com.lss.core.dto.AdminLoginDto;
import com.lss.core.dto.FindAdminLoginPage;
import com.lss.core.emus.AdminLoginType;
import com.lss.core.emus.WorkTagType;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.AdminLogin;
import com.lss.core.pojo.AdminRole;
import com.lss.core.pojo.RolePopedom;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.FormatUtil;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.HttpUtil;
import com.lss.core.util.MD5;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.RedisUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.AdminListVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.PopedomVo;
import com.lss.core.vo.admin.params.AdminParams;
import com.lss.core.vo.admin.params.PersonParams;
import com.lss.core.vo.admin.params.PopedomParams;
import com.lss.core.vo.admin.params.RoleParams;
import com.lss.core.vo.ztc.ZtcImGuidMember;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Resource
	private IAdminLoginDao adminLoginDao;
	
	@Override
	public ReturnVo login(AdminParams params) {
		ReturnVo result = new ReturnVo();
		// 验证图片验证码
		params.setCode(params.getCode().toUpperCase());
		String servercode = RedisUtil.getString(SystemConstant.lssAdminImgcode
				+ params.getCode());
		if (servercode == null || !servercode.equals(params.getCode())) {
			result.setResult(ResponseCode.failure);
			result.setMsg("验证码错误");
			return result;
		}
		// 查询用户信息
		params.setPassword(MD5.encrypt(params.getPassword()));
		Admin admin = MapperManager.adminMapper.loginAdmin(params);
		if (admin != null) {
			//1.绑定第三方openid
			bingOpenId(admin, params);
			//2.校验状态及完成登录信息封装
			if (admin.getStatus().intValue() == 1) {
				LoginAdmin vo = new LoginAdmin();
				vo.setLoginame(params.getName());//登录名
				vo.setAdminid(admin.getAdminid());
				vo.setName(admin.getName());
				vo.setClinicid(admin.getClinicid());
				vo.setDoctorid(admin.getDoctorid());
				vo.setPhone(admin.getPhone());
				vo.setEcUserId(admin.getEcUserId());
				// 查询角色id
				List<Integer> list = MapperManager.adminRoleMapper
						.queryRoleids(admin.getAdminid());
				if (ObjectUtil.isEmpty(list)) {
					result.setResult(ResponseCode.failure);
					result.setMsg("该用户还未分配角色，请联系管理员！");
					return result;
				}
				if (list.contains(1))
					vo.setIsadmin(1);
				vo.setRoles(list);
				// TODO 查询权限

				// 删除图片验证码
				RedisUtil.delete(SystemConstant.lssAdminImgcode
						+ params.getCode());
				// 修改最近登录时间
				Admin model = new Admin();
				model.setAdminid(admin.getAdminid());
				model.setLogintime(new Date());
				MapperManager.adminMapper.updateByPrimaryKeySelective(model);
				result.setObj(vo);
				result.setResult(ResponseCode.success);
				result.setMsg(ResponseCode.successMsg);
				
				//最后登录直通车API 2019.05.13 lhy
				if(!AdminLoginType.WXGZH.name().equals(params.getType())) {//微信公众号不需要登录客户直通车
					vo.setGuidMember(ztcLogin(params));
				}
			} else {
				result.setResult(ResponseCode.failure);
				result.setMsg("账户被禁用");
			}
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg("用户名或密码错误");
		}
		return result;
	}

	@Override
	public ReturnVo save(Admin admin) {
		//修改组织则同步修改其名称，否则都不修改
		if (ObjectUtil.isNotEmpty(admin.getOrgId()) && ObjectUtil.isEmpty(admin.getOrgName())) {
			throw new LssException(ResponseCode.parameterError, "组织不能为空");
		} else if(ObjectUtil.isEmpty(admin.getOrgId())){
			admin.setOrgName(null);
		}
		ReturnVo result = new ReturnVo();
		String msg = checkAdmin(admin);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (admin.getAdminid() == null || admin.getAdminid().intValue() <= 0) {
			if (ObjectUtil.isEmpty(admin.getLoginpwd())) {
				// 默认123456
				admin.setLoginpwd("123456");
			}
			// 验证用户名是否存在
			if (MapperManager.adminMapper.checkLoginame(admin.getLoginame()) > 0) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg("用户名已存在");
				return result;
			}
			// 添加
			admin.setLoginpwd(MD5.encrypt(admin.getLoginpwd()));
			admin.setCreatetime(new Date());
			res = MapperManager.adminMapper.insertSelective(admin);
		} else {
			if ("".equals(admin.getLoginpwd()))
				admin.setLoginpwd(null);
			if (ObjectUtil.isNotEmpty(admin.getLoginpwd())) {
				admin.setLoginpwd(MD5.encrypt(admin.getLoginpwd()));
			}
			admin.setLoginame(null);
			// 编辑
			res = MapperManager.adminMapper.updateByPrimaryKeySelective(admin);
		}
		if (res == 1) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	/**
	 * 验证admin信息
	 * 
	 * @param admin
	 * @return
	 */
	private String checkAdmin(Admin admin) {
		if (admin == null)
			return ResponseCode.parameterErrorMsg;
		if (ObjectUtil.isEmpty(admin.getLoginame()))
			return "请填写登录名称";
		/*
		 * if (ObjectUtil.isEmpty(admin.getLoginpwd())) return "请填写登录密码";
		 */
		if (ObjectUtil.isEmpty(admin.getName()))
			return "请填写真实姓名";
		if (ObjectUtil.isEmpty(admin.getPhone()))
			return "请填写手机号码";
		else {
			if (!FormatUtil.matchMobile(admin.getPhone()))
				return "手机号码格式错误";
		}
		return "";
	}

	@Override
	public ReturnVo list(AdminParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.adminMapper.adminCount(params));
		List<AdminListVo> list = MapperManager.adminMapper.adminList(params);
		if (ObjectUtil.isNotEmpty(list)) {
			for (AdminListVo admin : list) {
				// 查询角色列表
				admin.setRoles(MapperManager.roleMapper.getRoles(admin
						.getAdminid()));
			}
		}
		result.setObj(list);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo operate(Admin admin) {
		ReturnVo result = new ReturnVo();
		if (MapperManager.adminMapper.operate(admin) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo rolelist() {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.roleMapper.roleList());
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo editrole(RoleParams params) {
		ReturnVo result = new ReturnVo();
		if (params.getAdminid() == null
				|| ObjectUtil.isEmpty(params.getRoleids())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		// 删除所有的角色
		MapperManager.adminRoleMapper.deleteRoles(params.getAdminid());
		for (Integer roleid : params.getRoleids()) {
			AdminRole role = new AdminRole();
			role.setAdminid(params.getAdminid());
			role.setRoleid(roleid);
			MapperManager.adminRoleMapper.insertSelective(role);
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo popedomlist() {
		ReturnVo result = new ReturnVo();
		List<PopedomVo> list = MapperManager.popedomMapper.popedomList(null);
		result.setObj(popedomGroup(list));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	/**
	 * 权限分组
	 * 
	 * @param list
	 * @return
	 */
	private List<PopedomVo> popedomGroup(List<PopedomVo> list) {
		if (ObjectUtil.isEmpty(list))
			return list;
		// 一级菜单
		List<PopedomVo> aMenu = new ArrayList<PopedomVo>();
		for (PopedomVo popedom : list) {
			if (popedom.getLevel().intValue() == 1) {
				aMenu.add(popedom);
			}
		}
		// 二级菜单
		if (!aMenu.isEmpty()) {
			for (PopedomVo apopedom : aMenu) {
				List<PopedomVo> bMenu = new ArrayList<PopedomVo>();
				for (PopedomVo popedom : list) {
					if (apopedom.getPopeid().equals(popedom.getParentid())) {
						bMenu.add(popedom);
					}
				}
				apopedom.setList(bMenu);
			}
		}
		return aMenu;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo editpopedom(PopedomParams params) {
		ReturnVo result = new ReturnVo();
		if (params.getRoleid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		// 删除角色所有的权限
		MapperManager.rolePopedomMapper.deleteByRoleid(params.getRoleid());
		if (ObjectUtil.isNotEmpty(params.getPopeids())) {
			for (Integer popeid : params.getPopeids()) {
				RolePopedom popedom = new RolePopedom();
				popedom.setRoleid(params.getRoleid());
				popedom.setPopeid(popeid);
				MapperManager.rolePopedomMapper.insertSelective(popedom);
			}
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo rolepopedom(RolePopedom params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.rolePopedomMapper.queryRolePopedom(params
				.getRoleid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo menulist(LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		List<PopedomVo> list;
		if (admin.getIsadmin() == 1) {
			// 系统管理员查询所有菜单
			list = MapperManager.popedomMapper.popedomList(null);
		} else {
			list = MapperManager.popedomMapper.popedomList(admin.getRoles());
		}
		result.setObj(popedomGroup(list));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo changepassword(AdminParams params, LoginAdmin loginAdmin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOldpassword(), params.getPassword())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		// 验证旧密码
		Admin admin = MapperManager.adminMapper.selectByPrimaryKey(loginAdmin
				.getAdminid());
		if (!admin.getLoginpwd().equals(MD5.encrypt(params.getOldpassword()))) {
			result.setResult(ResponseCode.failure);
			result.setMsg("密码错误");
			return result;
		}
		Admin temp = new Admin();
		temp.setAdminid(loginAdmin.getAdminid());
		temp.setLoginpwd(MD5.encrypt(params.getPassword()));
		if (MapperManager.adminMapper.updateByPrimaryKeySelective(temp) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo personnel(PersonParams params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.adminMapper.queryPersonnels(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo updateCallerNos(Admin admin) {
		Admin updateAdmin=new Admin();
		updateAdmin.setAdminid(admin.getAdminid());
		updateAdmin.setCallerNos(admin.getCallerNos());
		updateAdmin.setTransferNo(admin.getTransferNo());
		MapperManager.adminMapper.updateByPrimaryKeySelective(updateAdmin);
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo loginByWx(String code) {
		try {
			ReturnVo result = new ReturnVo();
			if (ObjectUtil.isEmpty(code)) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg(ResponseCode.parameterErrorMsg);
				return result;
			}
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
					+ SystemConstant.lesasaAppid
					+ "&secret="
					+ SystemConstant.lesasaSecret
					+ "&code=" + code + "&grant_type=authorization_code";
			String json = HttpRequestHelper.sendGet(url, null);
			log.info("code{},微信请求结果：{}",code,json);
			if (ObjectUtil.isNotEmpty(json)) {
				JSONObject object = JSONObject.parseObject(json);
				String openid = object.getString("openid");
				if (ObjectUtil.isNotEmpty(openid)) {
					// 1.根据openid查询用户id
					AdminLoginDto adminLoginParam=new AdminLoginDto();
					adminLoginParam.setType(AdminLoginType.WXGZH.name());
					adminLoginParam.setOpenid(openid);
					AdminLoginDto adminLoginDto = ServiceManager.adminLoginService.findAdminLoginByOpenid(adminLoginParam);
					if (adminLoginDto == null) {
						// 2.1未找到绑定openid的用户
						result.setObj(adminLoginParam);
						result.setResult(ResponseCode.noLogin);
						result.setMsg(ResponseCode.noLoginMsg);
					}else {
						//2.2找到绑定的管理员完成登录
						Admin admin = MapperManager.adminMapper.selectByPrimaryKey(adminLoginDto.getAdminid());
						LoginAdmin loginAdmin=loginByWx(admin, adminLoginDto);
						// 返回登录信息
						result.setObj(loginAdmin);
						result.setResult(ResponseCode.success);
						result.setMsg(ResponseCode.successMsg);
					}
					
					return result;
				}
			}
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
			return result;
		} catch (Exception e) {
			log.error("微信登录失败", e);
			throw e;
		}
	}
	
	private LoginAdmin loginByWx(Admin admin,AdminLoginDto adminLoginDto) {
		if (admin == null) {
			throw new LssException(ResponseCode.failure, "openId绑定的账户不存在");
		}
		LoginAdmin vo = new LoginAdmin();
		if (admin.getStatus().intValue() == 1) {
			vo.setLoginame(admin.getName());// 登录名
			vo.setAdminid(admin.getAdminid());
			vo.setName(admin.getName());
			vo.setClinicid(admin.getClinicid());
			vo.setDoctorid(admin.getDoctorid());
			// 查询角色id
			List<Integer> list = MapperManager.adminRoleMapper.queryRoleids(admin.getAdminid());
			if (ObjectUtil.isEmpty(list)) {
				throw new LssException(ResponseCode.failure, "该用户还未分配角色，请联系管理员！");
			}
			if (list.contains(1))
				vo.setIsadmin(1);
			vo.setRoles(list);

			// 修改最近登录时间
			Admin model = new Admin();
			model.setAdminid(admin.getAdminid());
			model.setLogintime(new Date());
			MapperManager.adminMapper.updateByPrimaryKeySelective(model);
			
			// 修改公众号登录时间
			AdminLogin wxLogin=new AdminLogin();
			wxLogin.setId(adminLoginDto.getId());
			wxLogin.setUpdateDate(new Date());
			adminLoginDao.updateByPrimaryKeySelective(wxLogin);
			
			// 公众号登录不用登录客户直通车 
		} else {
			throw new LssException(ResponseCode.failure, "账户被禁用");
		}
		return vo;
	}
	
	
	/**
	 * 管理员绑定第三方
	 * @param admin
	 * @param params
	 */
	private void bingOpenId(Admin admin,AdminParams params) {
		if (admin != null && StringUtils.isNotEmpty(params.getOpenid())) {
			//1.检测是否绑定
			FindAdminLoginPage findAdminLoginPage = new FindAdminLoginPage();
			findAdminLoginPage.setType(params.getType());
			findAdminLoginPage.setOpenid(params.getOpenid());
			int count = adminLoginDao.findAdminLoginPageCount(findAdminLoginPage);
			//2.未绑定则进行绑定
			if (count == 0) {
				AdminLoginDto bindInfo = new AdminLoginDto();
				bindInfo.setAdminid(admin.getAdminid());
				bindInfo.setType(params.getType());
				bindInfo.setOpenid(params.getOpenid());
				ServiceManager.adminLoginService.addAdminLogin(bindInfo);
			}
		}
	}
	
	/***
	 * 客户直通车登录
	 * @param params
	 * @return
	 */
	private ZtcImGuidMember ztcLogin(AdminParams params) {
		ZtcImGuidMember guidMember = null;
		//登录直通车API 2019.05.13 lhy
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginame", params.getName());
		String loginpwd= params.getPassword().substring(0, 8);
		map.put("loginpwd", MD5.encrypt(loginpwd));
		map.put("appKey", "Lss_"+System.currentTimeMillis());
		WorkTag tag = MapperManager.workTagMapper
				.queryByType(WorkTagType.ztc_url.getType());
		
		String json=null;
		try {
			json = HttpUtil.post(tag.getTagname() + "/api-os/lss/gd/imToken.do",
					map);
			if (ObjectUtil.isEmpty(json)) {
				throw new Exception("登录客户直通车失败");
			}
			JSONObject obj = JSONObject.parseObject(json);
			if (!obj.getBooleanValue("result")) {
				throw new Exception("登录客户直通车失败");
			}
			JSONObject member =obj.getJSONObject("returnObject");
			guidMember=new ZtcImGuidMember();
			guidMember.setCode(member.getString("code"));
			guidMember.setMemberNameMerchant(member.getString("memberNameMerchant"));
			guidMember.setMemberNoMerchant(member.getString("memberNoMerchant"));
			guidMember.setMemberNoGuid(member.getString("memberNoGuid"));
			guidMember.setMemberNameGuid(member.getString("memberNameGuid"));
			guidMember.setToken(member.getString("token"));
			//登录客户直通车成功。
			log.info("员工登录IM成功，Im导购信息:{}",guidMember);
		} catch (Exception e) {
			log.error("登录客户直通车失败,json={}",json);
			log.error("登录客户直通车失败!",e);
		}//登录直通车API 2019.05.13 lhy end
		return guidMember;
	}

	@Override
	public List<Integer> selectGroupAdminids(Integer adminid) {
		// 电销组长
		Admin findAdmin = MapperManager.adminMapper.selectByPrimaryKey(adminid);
		if (findAdmin.getOrgId() != null) {
			// 下属及同组员工
			List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
			if (adminids.size() == 0) {// 没下属，则给一个不存在的账号 避免拿了所有员工数据
				adminids.add(-1);
			}
			return adminids;
		} else {
			throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
		}
				
	}

	
	@Override
	public ReturnVo personnelList(PersonParams params, LoginAdmin loginAdmin) {
		ReturnVo result = new ReturnVo();
		//电销组长可以查看本组全部成员
		if(loginAdmin.getRoles().contains(9)) {
			//下属及同组员工 
			Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
			if (findAdmin.getOrgId() != null) {
				//下属及同组员工 
				List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
				if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
					adminids.add(-1); 
				}
				params.setAdminids(adminids);
				params.setOrgid(findAdmin.getOrgId());
			}else {
				if(loginAdmin.getAdminid()!=1) {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			}
		}else if(loginAdmin.getRoles().contains(1)) {
			//管理员要看所有用户
			params.setAdminids(null);
		}else {
			//电销人员只能查看自己
			params.setAdminid(loginAdmin.getAdminid());
		}
		result.setObj(MapperManager.adminMapper.queryPersonnelsList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public void updateByPhone(Admin params) {
		MapperManager.adminMapper.updateByPhone(params);
	}

	@Override
	public List<String> selectUserIds() {
		return MapperManager.adminMapper.selectUserIds();
	}
	
}
