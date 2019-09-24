/**
 * 
 */
package com.lss.core.vo.phone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 
 * 类说明：连连隐号绑定对象。
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
public class LlyhBindVo {

	/** token 连连隐号系统提供 */
	private String token;
	/**
	 * 绑定类型 PER_PERSON, 顾问配置小号外呼 PER_PERSON_ASSIGNED, 顾问配置小号外呼-指定 小号
	 */
	private String type;
	/** 主叫编号 */
	private String callerId;
	/** 主叫号码数组, 电话号码, 固话带区号, 至少一个 */
	private String[] callerNos;
	/** 被叫编号 */
	private String calledId;
	/** 被叫号码, 电话号码, 固话带区号, 不为空 */
	private String calledNo;
	/** 扩展字段 ,可存绑定操作的ID，用于追踪电话录音记录 */
	private String payload;
	/** 当绑定类型为 PER_PERSON_ASSIGNED 时, 不为空, 指定绑定小号,他绑定类型, 忽略该字段 */
	private String transferNo;
	/** 顾问联系方式：用于填充到 关机短信中  */
	private String agentContact;
	/** 主叫扩展信息  */
	private String callerInfo;
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCallerId() {
		return callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

	public String[] getCallerNos() {
		return callerNos;
	}

	public void setCallerNos(String[] callerNos) {
		this.callerNos = callerNos;
	}

	public String getCalledId() {
		return calledId;
	}

	public void setCalledId(String calledId) {
		this.calledId = calledId;
	}

	public String getCalledNo() {
		return calledNo;
	}

	public void setCalledNo(String calledNo) {
		this.calledNo = calledNo;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

//	public String getAgentContact() {
//		return agentContact;
//	}

	public void setAgentContact(String agentContact) {
		this.agentContact = agentContact;
	}

	public String getCallerInfo() {
		if (StringUtils.isNotEmpty(agentContact)) {
			Map<String, Object> data = new HashMap<>();
			data.put("agentContact", agentContact);
			callerInfo = JSON.toJSONString(data);
		}
		return callerInfo;
	}

	public void setCallerInfo(String callerInfo) {
		this.callerInfo = callerInfo;
	}

	@Override
	public String toString() {
		return "LlyhBindVo [token=" + token + ", type=" + type + ", callerId=" + callerId + ", callerNos="
				+ Arrays.toString(callerNos) + ", calledId=" + calledId + ", calledNo=" + calledNo + ", payload="
				+ payload + ", transferNo=" + transferNo + "]";
	}

}
