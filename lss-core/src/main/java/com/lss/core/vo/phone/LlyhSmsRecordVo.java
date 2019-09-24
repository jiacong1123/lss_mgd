/**
 * 
 */
package com.lss.core.vo.phone;

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
public class LlyhSmsRecordVo {
	/** 话单唯一标识 */
	private String recordId;
	/**
	 * 记录类型 OUTBOUND, 外发短信 OUTBOUND_UNKNOWN, 陌生外发短信 INBOUND, 客户回复短信 INBOUND_CHANNEL,
	 * 渠道短信 INBOUND_UNKNOWN, 陌生短信
	 */
	private String type;
	/** 小号 */
	private String transferNo;
	/** 接收方显示号码 */
	private String showNo;
	/**
	 * 短信状态 SENT, 成功发送 INVALID_SHOW_NUMBER, 显示号码不 合法 INVALID_RECEIVER_NUMBER, 接收号
	 * 码非手机号 OTHER, 其他失败
	 */
	private String result;
	/** 短信发送时间 */
	private String transferTime;
	/** 短信内容 */
	private String content;
	/**
	 * 短信扩展信息 JSON, 根据记录类型 渠道电话包含 channel 渠道标识, "{\"channel\":\"xxx\"}" 其他类型空值
	 */
	private String smsInfo;
	/** 顾问编号 */
	private String empId;
	/** 顾问号码 */
	private String empNo;
	/** 顾问号码归属地 */
	private String empNoArea;
	/**
	 * 顾问扩展信息 JSON 如果根据员工编号/empId/callerId， 能 查到有效员工扩展信息则使用， 否则包含 empName 顾问姓名 和
	 * deptName 部门 名称（不包括部门架构）
	 */
	private String empInfo;
	/**
	 * 客户编号, 根据记录类型 外发短信， 客户 Id 其他 ， 如果此记录中客户号码对应唯一 客户（客户 ID）， 传递客户 Id
	 * 如果对应多条客户记录，传递最近联系的 客户 Id 如果没有对应客户， 传递空
	 */
	private String cusId;
	/** 客户号码 */
	private String cusNo;
	/** 客户号码归属地 */
	private String cusNoArea;
	/**
	 * 客户扩展信息 JSON, 根据记录类型 外发短信， 客户扩展信息 其他， 如果此记录中客户号码对应唯一客 户（客户 ID）， 传递该客户扩展信息
	 * 如果对应多条客户记录，传递最近联系的 客户扩展信息 如果没有对应客户， 传递空
	 */
	private String cusInfo;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	public String getShowNo() {
		return showNo;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSmsInfo() {
		return smsInfo;
	}

	public void setSmsInfo(String smsInfo) {
		this.smsInfo = smsInfo;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpNoArea() {
		return empNoArea;
	}

	public void setEmpNoArea(String empNoArea) {
		this.empNoArea = empNoArea;
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public String getCusNoArea() {
		return cusNoArea;
	}

	public void setCusNoArea(String cusNoArea) {
		this.cusNoArea = cusNoArea;
	}

	public String getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(String cusInfo) {
		this.cusInfo = cusInfo;
	}

	@Override
	public String toString() {
		return "LlyhSmsRecordVo [recordId=" + recordId + ", type=" + type + ", transferNo=" + transferNo + ", showNo="
				+ showNo + ", result=" + result + ", transferTime=" + transferTime + ", content=" + content
				+ ", smsInfo=" + smsInfo + ", empId=" + empId + ", empNo=" + empNo + ", empNoArea=" + empNoArea
				+ ", empInfo=" + empInfo + ", cusId=" + cusId + ", cusNo=" + cusNo + ", cusNoArea=" + cusNoArea
				+ ", cusInfo=" + cusInfo + "]";
	}

}
