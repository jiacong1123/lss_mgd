/**
 * 
 */
package com.lss.core.vo.phone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 
 * 类说明：连连外呼小号绑定结果。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月6日
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LlyhBindResultVo {
	/**
	 * 状态码 0 成功 1 无小号可用, 有小号配置但小号均被绑定 2 无小号配置, 无小号配置 3 主叫不存在 4 其他错
	 */
	private Integer code;
	/** 状态消息 */
	private String message;
	/** 绑定小号, code 为 0 时为有效小号 */
	private String number;
	/** 是否释放已有号码绑定, code 为 0 时为有效值 */
	private Boolean reuse;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Boolean getReuse() {
		return reuse;
	}

	public void setReuse(Boolean reuse) {
		this.reuse = reuse;
	}

	@Override
	public String toString() {
		return "LlyhBindResultVo [code=" + code + ", message=" + message + ", number=" + number + ", reuse=" + reuse
				+ "]";
	}

}
