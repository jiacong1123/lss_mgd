/**
 * 
 */
package com.lss.core.vo.phone;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 
 * 类说明：电话记录
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
public class CallRecordListVo {
	/** token 工单系统提供 */
	private String token;
	/** 电话记录数组 */
	private List<CallRecordVo> records;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CallRecordVo> getRecords() {
		return records;
	}

	public void setRecords(List<CallRecordVo> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "CallRecordListVo [token=" + token + ", records=" + records + "]";
	}

}
