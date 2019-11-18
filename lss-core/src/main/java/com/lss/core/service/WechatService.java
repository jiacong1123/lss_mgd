package com.lss.core.service;

public interface WechatService {
	/**
	 * 获取好牙乐Access_token
	 * 
	 * @return
	 */
	String getHylAccess_token();

	/**
	 * 获取乐莎莎公众号Access_token
	 * 
	 * @return
	 */
	String getLesasaAccess_token();
	
	
	/**
	 * 获取乐莎莎公众号jsapi_ticket
	 * 
	 * @return
	 */
	String getLssJsapi_ticket();
}
