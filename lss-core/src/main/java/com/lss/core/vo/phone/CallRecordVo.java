/**
 * 
 */
package com.lss.core.vo.phone;

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
public class CallRecordVo {
	/** 话单唯一标识 */
	private String recordId;
	/**
	 * 记录类型 OutBound_Call, 外呼电话 OutBound_Unkown, 陌生去电 InBound_Call, 客户电话
	 * InBound_Call_Channel, 渠道电话 Unknow_Call, 陌生电话
	 */
	private String type;
	/** 小号 */
	private String transferNo;
	/** 被叫显示号码 */
	private String showNo;
	/**
	 * 通话状态 ANSWERED(通话成功) BUSY(被叫忙) NO_ANSWER(被叫无应答) REJECT(被叫拒接) HANGUP(主叫提前挂机)
	 * INVALID_NUMBER(空号, 连连后台 使用 信号异常) POWER_OFF(关机) UNAVAILABLE(暂时无法接听, 连连 后台使用
	 * 信号异常) SUSPEND(停机) BLACK(黑名单号码) OTHER(其他失败情形, 连连后台使用 信号异常)
	 */
	private String result;
	/** 通话开始时间 */
	private String startTime;
	/** 通话结束时间 */
	private String endTime;
	/** 通话时长, 大于 0 已接通, 否则未接通 */
	private Integer duration;
	/**
	 * 录音地址, HTTP 地址, MP3 格式 录音托管期间, 地址有效 录音处理会引入一定延迟, 话单生成后地 址不是立即生效
	 */
	private String recordingUrl;
	/**
	 * 通话扩展信息 JSON, 根据记录类型 渠道电话包含 channel 渠道标识, "{\"channel\":\"xxx\"}" 其他类型空值
	 */
	private String callInfo;
	/** 顾问编号 */
	private String empId;
	/**
	 * 顾问号码, 根据记录类型 外呼电话，外呼号码 其他类型，接听号码
	 */
	private String empNo;
	/** 顾问号码归属地 */
	private String empNoArea;
	/**
	 * 顾问扩展信息 JSON 如果根据员工编号/empId/callerId， 能查到有效员工扩展信息则使用， 否则 包含 empName 顾问姓名 和
	 * deptName 部门名称（不包括部门架 构）
	 */
	private String empInfo;
	/**
	 * 客户编号, 根据记录类型 外呼电话， 客户 Id 其他 ， 如果此记录中客户号码对应唯一 客户（客户 ID）， 传递客户 Id，
	 * 如果对应多条客户记录，传递最近联系 的客户 Id 如果没有对应客户， 传递空
	 */
	private String cusId;
	/** 客户号码 */
	private String cusNo;
	/** 客户号码归属地 */
	private String cusNoArea;
	/**
	 * 客户扩展信息 JSON, 根据记录类型 外呼电话， 客户扩展信息 其他， 如果此记录中客户号码对应唯一 客户（客户 ID）， 传递该客户扩展信 息
	 * 如果对应多条客户记录，传递最近联系 的客户扩展信息 (挂机短信中相应的变 量也使用该客户记录 如果没有对应客户， 传递空 （挂机短
	 * 信中相应变量也会留空不替换）
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getRecordingUrl() {
		return recordingUrl;
	}

	public void setRecordingUrl(String recordingUrl) {
		this.recordingUrl = recordingUrl;
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
		return "CallRecordVo [recordId=" + recordId + ", type=" + type + ", transferNo=" + transferNo + ", showNo="
				+ showNo + ", result=" + result + ", startTime=" + startTime + ", endTime=" + endTime + ", duration="
				+ duration + ", recordingUrl=" + recordingUrl + ", callInfo=" + callInfo + ", empId=" + empId
				+ ", empNo=" + empNo + ", empNoArea=" + empNoArea + ", empInfo=" + empInfo + ", cusId=" + cusId
				+ ", cusNo=" + cusNo + ", cusNoArea=" + cusNoArea + ", cusInfo=" + cusInfo + "]";
	}

}
