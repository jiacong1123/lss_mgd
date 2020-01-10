package com.lss.core.vo.admin.params;

import java.util.List;

import com.lss.core.vo.PageParams;

public class AdminParams extends PageParams {
	private String name;
	private String password;
	private String code;
	private String phone;
	private String oldpassword;
	
	/** 第三方登录： 类型(WXGZH:微信公众号) 2019.06.20 lhy add */
    private String type;
    /** 第三方登录： 第三方唯一标识 2019.06.20 lhy add*/
    private String openid;
	/** 下级员工集合 */
	private List<Integer> adminids;
    /** 员工名称*/
    private String adminName;
    /** 部门名称*/
    private String orgName;
    
    
    
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "".equals(name.trim()) ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = "".equals(password.trim()) ? null : password.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = "".equals(code.trim()) ? null : code.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = "".equals(phone.trim()) ? null : phone.trim();
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = "".equals(oldpassword.trim()) ? null : oldpassword
				.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public List<Integer> getAdminids() {
		return adminids;
	}

	public void setAdminids(List<Integer> adminids) {
		this.adminids = adminids;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
}
