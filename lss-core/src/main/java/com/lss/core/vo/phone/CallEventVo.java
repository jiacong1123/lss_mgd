/**
 * 
 */
package com.lss.core.vo.phone;

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
public class CallEventVo {
	/** 话单唯一标识 */
	private String recordId;
	/**
	 * 电话事件类型与场景 DIALING_OUTBOUND, 外呼去电拨打事件 DIALING_UNKNOWNOUTCALL, 陌生去电拨打 事件
	 * DIALING_INBOUND, 客户来电拨打事件 DIALING_CHANNEL, 渠道来电拨打事件 DIALING_UNKNOWN, 陌生来电拨打事件
	 */
	private String type;
	/** 发生时间 */
	private String time;
	/** 小号 */
	private String transferNo;
	/** 被叫显示号码 */
	private String showNo;
	/**
	 * 扩展信息 JSON, 根据事件类型 渠道来电包含, channel 渠道标识， "{\"channel\":\"xxx\"}" 其他类型空值
	 */
	private String callInfo;
	/** 顾问编号 */
	private String empId;
	/** 顾问号码 */
	private String empNo;
	/**
	 * 顾问扩展信息 JSON 如果根据员工编号/empId/callerId， 能查 到有效员工扩展信息则使用， 否则包含 empName 顾问姓名 和
	 * deptName 部门 名称（不包括部门架构）
	 */
	private String empInfo;
	/**
	 * 客户编号, 根据记录类型 外呼电话， 客户 Id 其他 ， 如果此记录中客户号码对应唯一客 户（客户 ID）， 传递客户 Id，
	 * 如果对应多条客户记录，传递最近联系的 客户 Id 如果没有对应客户， 传递空
	 */
	private String cusId;
	/** 客户号码 */
	private String cusNo;
	/**
	 * 客户扩展信息 JSON, 根据记录类型 外呼电话， 客户扩展信息 其他， 如果此记录中客户号码对应唯一客 户（客户 ID）， 则传递该客户扩展信息
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getCallInfo() {
		return callInfo;
	}

	public void setCallInfo(String callInfo) {
		this.callInfo = callInfo;
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

	public String getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(String cusInfo) {
		this.cusInfo = cusInfo;
	}

	@Override
	public String toString() {
		return "CallEventVo [recordId=" + recordId + ", type=" + type + ", time=" + time + ", transferNo=" + transferNo
				+ ", showNo=" + showNo + ", callInfo=" + callInfo + ", empId=" + empId + ", empNo=" + empNo
				+ ", empInfo=" + empInfo + ", cusId=" + cusId + ", cusNo=" + cusNo + ", cusInfo=" + cusInfo + "]";
	}

}
