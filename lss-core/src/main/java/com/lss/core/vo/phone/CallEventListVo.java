/**
 * 
 */
package com.lss.core.vo.phone;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 
 * 类说明：电话事件记录。
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
public class CallEventListVo {
	/** token 工单系统提供 */
	private String token;
	/** 事件记录数组 */
	private List<CallEventVo> events;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CallEventVo> getEvents() {
		return events;
	}

	public void setEvents(List<CallEventVo> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "CallEventListVo [token=" + token + ", events=" + events + "]";
	}

	 
}
