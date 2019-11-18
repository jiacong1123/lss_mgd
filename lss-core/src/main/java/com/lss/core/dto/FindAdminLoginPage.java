package com.lss.core.dto;

import com.lss.core.vo.PageParams;

public class FindAdminLoginPage extends PageParams {
	/** 管理员ID */
	private Integer adminid;
	/** 类型(WXGZH:微信公众号) */
	private String type;

	/** 第三方唯一标识 */
	private String openid;

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
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

}
