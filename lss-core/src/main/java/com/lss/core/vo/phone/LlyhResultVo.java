/**
 * 
 */
package com.lss.core.vo.phone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 
 * 类说明：连连隐号记录推送后返回结果。
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月5日
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LlyhResultVo {
	/**
	 * 结果码 0 成功, 全部当成功(不看 errIds) 1 错误, 参考 errIds, 如果 errIds 缺失或空, 本批次话单记录全部 当失败处理,
	 * 如果 errIds 非空且有效, 把 errIds 定义的话单记录 当失败处理
	 */
	private int result;
	/** 结果消息 */
	private String message;
	/** 问题话单标识, 用于查询重推 */
	private String[] errIds;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getErrIds() {
		return errIds;
	}

	public void setErrIds(String[] errIds) {
		this.errIds = errIds;
	}
	
	public static LlyhResultVo success() {
		LlyhResultVo vo=new LlyhResultVo();
		vo.setResult(0);
		vo.setMessage("成功");
		
		return vo;
	}

	public static LlyhResultVo failed() {
		LlyhResultVo vo=new LlyhResultVo();
		vo.setResult(1);
		vo.setMessage("失败");
		
		return vo;
	}
}
