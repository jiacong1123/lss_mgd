package com.lss.core.dto;

import java.io.Serializable;

/**
 * 微信公众号登录
 * 
 * @author Administrator
 *
 */
public class WxLoginDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
