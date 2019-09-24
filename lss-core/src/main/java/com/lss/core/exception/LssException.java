package com.lss.core.exception;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：业务系统异常处理类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author Administrator 2019.05.06
 *   
 */
public class LssException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3664225596755566456L;
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 */
//	public LssException(String exceptionInfo) {
//		super(exceptionInfo);
//		this.setExceptionInfo(exceptionInfo);
//	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 */
	public LssException(Integer exceptionCode, String exceptionInfo) {
		super(exceptionCode + "-->" + exceptionInfo);
		this.setExceptionCode(exceptionCode);
		this.setExceptionInfo(exceptionInfo);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param throwable the throwable
	 */
//	public LssException(String exceptionInfo, Throwable throwable) {
//		super(exceptionInfo, throwable);
//		this.setExceptionInfo(exceptionInfo);
//	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param throwable the throwable
	 */
	public LssException(Integer exceptionCode, String exceptionInfo, Throwable throwable) {
		super(exceptionCode + "-->" + exceptionInfo, throwable);
		this.setExceptionCode(exceptionCode);
		this.setExceptionInfo(exceptionInfo); 
	}
	
	
	
	/** * 错误代码. */
	private Integer exceptionCode;
	
	/** * 错误描述. */
	private String exceptionInfo;
	
	/**
	 * Gets the * 错误代码.
	 *
	 * @return the * 错误代码
	 */
	public Integer getExceptionCode() {
		return exceptionCode;
	}
	
	/**
	 * Sets the * 错误代码.
	 *
	 * @param exceptionCode the new * 错误代码
	 */
	public void setExceptionCode(Integer exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	/**
	 * Gets the * 错误描述.
	 *
	 * @return the * 错误描述
	 */
	public String getExceptionInfo() {
		return exceptionInfo;
	}
	
	/**
	 * Sets the * 错误描述.
	 *
	 * @param exceptionInfo the new * 错误描述
	 */
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

}
