package com.lss.core.emus;

/**
 * 工单管理第三方登录类型
 * 
 * @author lhy 2019.06.19
 *
 */
public enum AdminLoginType {

	/** 微信公众号 */
	WXGZH("微信公众号");

	private String name;

	AdminLoginType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
