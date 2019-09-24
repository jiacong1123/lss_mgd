/**
 * 
 */
package com.lss.core.exception;

/**
 * 
 * 
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月7日
 */
public class UploadException extends RuntimeException {
	public UploadException(String message) {
		super(message);
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}
}
