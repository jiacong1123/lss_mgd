/**
 * 
 */
package com.lss.core.vo.phone;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 
 * 类说明：短信记录.
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月5日
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LlyhSmsRecordListVo {
	/** token 工单系统提供 */
	private String token;
	/** 电话记录数组 */
	private List<LlyhSmsRecordVo> records;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<LlyhSmsRecordVo> getRecords() {
		return records;
	}

	public void setRecords(List<LlyhSmsRecordVo> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "LlyhSmsRecordListVo [token=" + token + ", records=" + records + "]";
	}

}
