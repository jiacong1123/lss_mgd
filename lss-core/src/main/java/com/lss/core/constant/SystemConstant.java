package com.lss.core.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统常量定义
 * 
 * @author SWWH
 *
 */
public class SystemConstant {

	/**
	 * 域名白名单
	 */
	public static List<String> domainWhiteList = new ArrayList<String>();

	/**
	 * lss_admin session key
	 */
	public final static String lssAdminSession = "lssAdminSession";
	/**
	 * lss_admin openid session key
	 */
	public final static String lssAdminSessionOpenId = "lssAdminSessionOpenId";

	/**
	 * 后台图片验证码前缀
	 */
	public final static String lssAdminImgcode = "lssAdminImgcode";

	/**
	 * 七牛 AccessKey
	 */
	public final static String qiniuAccessKey = "lHlUeNFZK88O-ZBaoG5NhmYG4QsrwoQroPHpDXLL";

	/**
	 * 七牛 qiniuSecretKey
	 */
	public final static String qiniuSecretKey = "Z5Y0_x9BVolVv3l8a74B3KHaxetFdvGXEg_n1VTe";

	/** 七牛域名 */
	//public final static String qiniuBucketHostName="http://images.lesasa.com";
	
	/** 七牛域名 */
	public final static String qiniuBucketHostName="http://images.lesasa.wang";
	
	/** 七牛上傳文件空間名 */
	public final static String qiniuBucket="images";
	
	
	/**
	 * 上传token
	 */
	public final static String uploadToken = "uploadToken";

	/**
	 * 好医乐公众号appid
	 */
	public final static String appid = "wxb88b44b094c7950e";

	/**
	 * 好医乐公众号secret
	 */
	public final static String secret = "f7c0833555019dab113547d95f35d43c";

	/**
	 * lss_admin session key
	 */
	public final static String hylDoctorSession = "hylDoctorSession";

	/**
	 * 乐莎莎短信验证码前缀
	 */
	public final static String lssSmsCode = "lssSmsCode_";

	/**
	 * 乐莎莎客户表单提交验证码前缀
	 */
	public final static String lssSmsCodeFormSubmit = "lssSmsCodeFormSubmit_";

	/**
	 * 好牙乐公众号Access_token
	 */
	public final static String hylAccess_token = "hyl_Access_token";

	/**
	 * 乐莎莎公众号Access_token
	 */
	public final static String lesasaAccess_token = "lesasa_Access_token";
	
	/**
	 * 乐莎莎公众号appid
	 */
	public final static String lesasaAppid = "wx93f3ac202e6c1dcc";

	/**
	 * 乐莎莎公众号secret
	 */
	public final static String lesasaSecret = "962d89dcda8fdcf5dc0ea414a2bd45de";
	
	/**
	 * 乐莎莎公众号jsapi_ticket
	 */
	public final static String lesasajsapi_ticket = "lesasa_jsapi_ticket";
	
	/**
	 * 工单7天未跟进则关闭
	 */
	public final static int WORK_ORDER_CLOSE_DAY = -7;
	
	/**
	 * 工单5天前未跟进则提醒
	 */
	public final static int WORK_ORDER_WILL_CLOSE_DAY = -5;
	
	static {
		// 初始化域名白名单
		domainWhiteList.add("http://bms.lesasa.com");
		domainWhiteList.add("http://m.lesasa.wang");
		domainWhiteList.add("http://www.lesasa.wang");
		domainWhiteList.add("http://www.lesasa.com");
		domainWhiteList.add("http://hyl.leshasha.com");
		domainWhiteList.add("http://zwcs.leshasha.com");
		domainWhiteList.add("http://www.leshasha.com");
		domainWhiteList.add("http://m.leshasha.com");
		domainWhiteList.add("http://m.lesasa.com");
		domainWhiteList.add("http://rw.kehuzhitongche.com");//外网测试环境
		domainWhiteList.add("http://121.201.66.202");//外网测试环境
		//2019-9-20新增白名单
		domainWhiteList.add("http://bms.lesasa.wang");
	}
}
