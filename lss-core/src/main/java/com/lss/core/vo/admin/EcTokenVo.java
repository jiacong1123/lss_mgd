package com.lss.core.vo.admin;
/**
 * EC获取token响应实体类
 * @author Administrator
 *
 */
public class EcTokenVo {

	private String accessToken;
	
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
