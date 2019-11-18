package com.lss.core.constant;

public class WxPayConfig {
	//appid
	public static final String appid = "wxb88b44b094c7950e";								
	//微信支付的商户id
	public static final String mch_id = "1521763091";
	//微信支付的商户密钥
	public static final String key = "QT6zG6MXSD2w5pYTOKCCnNnm9cuEuTTK";
	//支付成功后的服务器回调url
	public static final String notify_url = "http://hylapi.leshasha.com/common/wxpaynotify";
	//签名方式，固定值
	public static final String SIGNTYPE = "MD5";
	//交易类型，小程序支付的固定值为JSAPI
	public static final String TRADETYPE = "JSAPI";
}
