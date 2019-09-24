package com.lss.core.dto;

import java.io.Serializable;
import java.util.Date;

public class SmsRecordDto implements Serializable {

	/** id */
	private Integer id;

	/** 话单唯一标识 */
	private String recordId;

	/**
	 * 记录类型记录类型 OUTBOUND, 外发短信；OUTBOUND_UNKNOWN, 陌生外发短信；INBOUND,
	 * 客户回复短信；INBOUND_CHANNEL, 渠道短信；INBOUND_UNKNOWN, 陌生短信
	 */
	private String type;

	/** 小号 */
	private String transferNo;

	/** 被叫显示号码 */
	private String showNo;

	/**
	 * 短信状态 SENT, 成功发送 ；INVALID_SHOW_NUMBER, 显示号码不合法；INVALID_RECEIVER_NUMBER,
	 * 接收号码非手机号；OTHER, 其他失败
	 */
	private String llResult;

	/** 短信发送时间 */
	private String transferTime;

	/** 短信内容 */
	private String content;

	/** 短信扩展信息 JSON */
	private String smsInfo;

	/** 顾问编号 */
	private String empId;

	/** 顾问号码, */
	private String empNo;

	/** 顾问号码归属地 */
	private String empNoArea;

	/** 顾问扩展信息 JSON */
	private String empInfo;

	/** 客户编号 */
	private String cusId;

	/** 客户号码 */
	private String cusNo;

	/** 客户号码归属地 */
	private String cusNoArea;

	/** 客户扩展信息 */
	private String cusInfo;

	/** 数据处理状态（INIT:初始 ;PROCESSED:已处理 ） */
	private String processStatus;

	/** 管理员ID */
	private Integer adminId;

	/** 管理员名称 */
	private String adminName;

	/** 管理员手机 */
	private String adminPhone;

	/** 用户ID */
	private Integer userId;

	/** 用户名称 */
	private String userName;

	/** 工单进度ID */
	private Integer workrecordId;

	/** 工单号 */
	private String orderno;

	/** 自存录音URL */
	private String lssRecordUrl;

	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId == null ? null : recordId.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo == null ? null : transferNo.trim();
	}

	public String getShowNo() {
		return showNo;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo == null ? null : showNo.trim();
	}

	public String getLlResult() {
		return llResult;
	}

	public void setLlResult(String llResult) {
		this.llResult = llResult == null ? null : llResult.trim();
	}

	public String getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime == null ? null : transferTime.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getSmsInfo() {
		return smsInfo;
	}

	public void setSmsInfo(String smsInfo) {
		this.smsInfo = smsInfo == null ? null : smsInfo.trim();
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId == null ? null : empId.trim();
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo == null ? null : empNo.trim();
	}

	public String getEmpNoArea() {
		return empNoArea;
	}

	public void setEmpNoArea(String empNoArea) {
		this.empNoArea = empNoArea == null ? null : empNoArea.trim();
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo == null ? null : empInfo.trim();
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId == null ? null : cusId.trim();
	}

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo == null ? null : cusNo.trim();
	}

	public String getCusNoArea() {
		return cusNoArea;
	}

	public void setCusNoArea(String cusNoArea) {
		this.cusNoArea = cusNoArea == null ? null : cusNoArea.trim();
	}

	public String getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(String cusInfo) {
		this.cusInfo = cusInfo == null ? null : cusInfo.trim();
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus == null ? null : processStatus.trim();
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName == null ? null : adminName.trim();
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone == null ? null : adminPhone.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Integer getWorkrecordId() {
		return workrecordId;
	}

	public void setWorkrecordId(Integer workrecordId) {
		this.workrecordId = workrecordId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno == null ? null : orderno.trim();
	}

	public String getLssRecordUrl() {
		return lssRecordUrl;
	}

	public void setLssRecordUrl(String lssRecordUrl) {
		this.lssRecordUrl = lssRecordUrl == null ? null : lssRecordUrl.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
