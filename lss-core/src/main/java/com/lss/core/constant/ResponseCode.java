package com.lss.core.constant;

public class ResponseCode {
	/**
	 * 操作失败
	 */
	public static Integer failure = 0;
	public static String failureMsg = "操作失败";
	/**
	 * 操作成功
	 */
	public static Integer success = 1;
	public static String successMsg = "操作成功";
	/**
	 * 参数错误
	 */
	public static Integer parameterError = 2;
	public static String parameterErrorMsg = "参数错误";

	/**
	 * 没有权限
	 */
	public static Integer noPermission = 3;
	public static String noPermissionMsg = "没有权限";
	/**
	 * 系统异常
	 */
	public static Integer error = 4;
	public static String errorMsg = "系统异常";

	/**
	 * 没有登录
	 */
	public static Integer noLogin = 5;
	public static String noLoginMsg = "没有登录";
	
	/**
	 * 请求失败
	 */
	public static Integer requstFailure = 6;
	public static String requstFailureMsg = "请求API失败";
	
	/**
	 * 连连隐号token 校验失败
	 */
	public static Integer llyhUnkowToken = 7;
	public static String llyhUnkowTokenMsg = "token校验失败";
	
	/**
	 * 连连隐号参数错误
	 */
	public static Integer llyhParameterError = 8;
	public static String llyhParameterErrorMsg = "参数校验失败";
	
	/**
	 * 没有绑定第三方
	 */
	public static Integer openidNobind = 9;
	public static String openidNobindMsg = "没有绑定";
}
